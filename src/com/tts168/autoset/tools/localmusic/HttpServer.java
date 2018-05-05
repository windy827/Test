package com.tts168.autoset.tools.localmusic;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.HttpStatus;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.BasicHttpProcessor;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteOrder;
import java.util.Locale;
/**
 * 
 * @author 黄坚
 * @date 20150714
 *
 */
public class HttpServer {
	
	private static final String TAG = HttpServer.class.getName();

    private static HttpServer instance;
    
    public static HttpServer getInstance(Context ctx) {
    	synchronized(HttpServer.class) {
	        if (null == instance) {
	            instance = new HttpServer(ctx);
	        }
	        return instance;
    	}
    }

    public static void destroy() {
    	synchronized(HttpServer.class) {
	        if (instance != null) {
	            instance.shutDown();
	            instance = null;
	        }
    	}
    }
    
    private static String httpEncode(String value) {
        String result = "";
        try {
            result = URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private HttpHandlerThread mThread;

    private HttpServer(Context ctx) {
        mThread = new HttpHandlerThread(ctx);
        mThread.start();
    }

    private void shutDown() {
        mThread.exitNow();
    }

    public String getServerIp() {
        return mThread.getServerIp();
    }

    public int getServerPort() {
        return mThread.getServerPort();
    }
    
    public String generateURL(String file) {
    	return mThread.generateURL(file);
    }

    private static class HttpServiceHandler implements HttpRequestHandler {
        @Override
        public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
            String method = request.getRequestLine().getMethod();
            method = method.toUpperCase(Locale.ENGLISH);
            if (!method.equals("GET")) {
                throw new MethodNotSupportedException(method + " NOT SUPPROT");
            }

            String url = request.getRequestLine().getUri();
            int pos = url.lastIndexOf('?');
            if (pos < 0 || pos >= url.length() - 1) {
                response.setStatusCode(HttpStatus.SC_NOT_FOUND);
                StringEntity entity = new StringEntity("404 NOT FOUND");
                response.setEntity(entity);
                return;
            }

            url = url.substring(pos + 1);
            pos = url.lastIndexOf('=');
            if (pos < 0 || pos >= url.length() - 1) {
                response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                StringEntity entity = new StringEntity("INVALID REQUEST");
                response.setEntity(entity);
                return;
            }

            url = url.substring(pos + 1);
            url = URLDecoder.decode(url, "UTF-8");

            Log.d(TAG, "request file: " + url);
            File file = new File(url);
            long fileSize = file.length();

            if (!file.exists() || !file.canRead()) {
                response.setStatusCode(HttpStatus.SC_NOT_FOUND);
                StringEntity entity = new StringEntity("404 NOT FOUND");
                response.setEntity(entity);
                return;
            }

            response.setHeader("Accept-Ranges", "bytes");
            Header header = request.getFirstHeader("Range");
            if (null == header) {
                response.setHeader("Content-Length", String.valueOf(fileSize));
                response.setStatusCode(HttpStatus.SC_OK);
                FileEntity entity = new FileEntity(file, "audio/mp3");
                response.setEntity(entity);
                return;
            }

            String range = header.getValue().trim();
            pos = range.indexOf('=');
            long start = 0;
            long end = fileSize - 1;
            if (pos >= 0 && pos < range.length() - 1) {
            	range = range.substring(pos + 1);
            	pos = range.indexOf('-');
            	if (pos >= 0) {
            		try {
                        String str = range.substring(0, pos);
                        if (str.length() > 0) {
                            start = Long.parseLong(str.trim());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            		
            		try {
            			if (pos < range.length() - 1) {
                            String str = range.substring(pos + 1);
                            end = Long.parseLong(str.trim());
                        }
            		} catch (Exception e) {
                        e.printStackTrace();
                    }
            	}
            }

            if (start > end) {
                response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                StringEntity entity = new StringEntity("INVALID REQUEST");
                response.setEntity(entity);
                return;
            }

            range = String.format(Locale.CHINA, 
            		"bytes %d-%d/%d", start, end, fileSize);
            response.setHeader("Content-Length", String.valueOf(end + 1 - start));
            response.setHeader("Content-Range", range);
            DataProducer producer = new DataProducer(file, start, end + 1);
            EntityTemplate entity = new EntityTemplate(producer);
            response.setEntity(entity);
        };

        private static class DataProducer implements ContentProducer {

            private File mFile;
            private long mStart;
            private long mEnd;

            public DataProducer(File file, long start, long end) {
                mFile = file;
                mStart = start;
                mEnd = end;
            }

            @Override
            public void writeTo(OutputStream output) throws IOException {
                long total = mEnd - mStart;
                long wrSize = 0;
                FileInputStream istream = new FileInputStream(mFile);
                istream.skip(mStart);
                byte[] buffer = new byte[8192];

                while (wrSize < total) {
                    int perWrite = buffer.length;
                    if (perWrite > total - wrSize) {
                        perWrite = (int)(total - wrSize);
                    }
                    
                    int ret = istream.read(buffer, 0, perWrite);
                    if (ret <= 0) {
                        istream.close();
                        throw new IOException();
                    }
                    
                    output.write(buffer, 0, ret);
                    total += ret;
                }
                istream.close();
            }
        }
    };

    private static class HttpHandlerThread extends Thread {

    	private Context      mContext;
        private ServerSocket mServerSock;
        private HttpParams   mHttpPrams;
        private HttpService  mHttpService;
        private boolean      mExitNow;

        public HttpHandlerThread(Context ctx) {
            super();
            mContext = ctx;
            
        	try {
				mServerSock = new ServerSocket(1028);
				Log.d(TAG, "server run at " + getServerIp() +
						":" + getServerPort());
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

        	HttpProcessor httpProc = new BasicHttpProcessor();
            HttpRequestHandlerRegistry reqistry = new HttpRequestHandlerRegistry();
            
            mExitNow = false;
            mHttpPrams = new BasicHttpParams();
            mHttpService = new HttpService(httpProc,
                    new DefaultConnectionReuseStrategy(),
                    new DefaultHttpResponseFactory());

            mHttpPrams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
            mHttpPrams.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);
            reqistry.register("*", new HttpServiceHandler());

            mHttpService.setParams(this.mHttpPrams);
            mHttpService.setHandlerResolver(reqistry);
        }
        
        public void exitNow() {
        	Log.d(TAG, "http server exit by called exitNow()");
        	synchronized(this) {
        		this.mExitNow = true;
        		try {
					this.mServerSock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }

        public int getServerPort() {
        	synchronized(this) {
        		if (null == mServerSock) {
        			Log.d(TAG, "server socket is not ready");
        			return 0;
        		}
        		return mServerSock.getLocalPort();
        	}
        }
        /**
         * 根据文件路径生成可播放的URL
         * @param file
         * @return
         */
        public String generateURL(String file) {
        	return String.format(Locale.CHINA, "http://%s:%d/?f=%s",
        			getServerIp(),
        			getServerPort(),
        			httpEncode(file));
        }

        public String getServerIp() {
        	String srv = Context.WIFI_SERVICE;
        	 WifiManager wifi = (WifiManager) mContext.getSystemService(srv);  
             WifiInfo info = wifi.getConnectionInfo();
             if (null == info) {
            	 return "";
             }
             
             int ipAddr = info.getIpAddress();
             if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            	 ipAddr = Integer.reverseBytes(ipAddr);
             }

             byte[] bytes = BigInteger.valueOf(ipAddr).toByteArray();
             try {
            	 InetAddress inetAddr = InetAddress.getByAddress(bytes);
            	 return inetAddr.getHostAddress();
             } catch (Exception e) {
            	 e.printStackTrace();
            	 return "";
             }
        }

        @Override
        public void run() {
        	Log.d(TAG, "begin to run http handler thread");
            while (true) {
            	if (null == mServerSock) {
        			Log.d(TAG, "cannot run thread, server socket is not ready");
        			break;
        		}
            	
            	synchronized(this) {
            		if (mExitNow) {
            			Log.d(TAG, "exit handler thread by checking mExitNow");
            			break;
            		}
            	}
            	
                try {
                    Socket socket = mServerSock.accept();
                    if (null == socket || !socket.isConnected()) {
                    	Log.d(TAG, "exit handler thread because socket is not connected");
                        break;
                    }

                    DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
                    conn.bind(socket, mHttpPrams);

                    Thread worker = new WorkerThread(mHttpService, conn);
                    worker.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }

            try {
                mServerSock.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "end of http handler thread");
        }
    };

    private static class WorkerThread extends Thread {

        private HttpService          mHttpService;
        private HttpServerConnection mConnection;

        public WorkerThread(HttpService service, HttpServerConnection conn) {
            super();

            mHttpService = service;
            mConnection = conn;
        }

        @Override
        public void run() {
            HttpContext context = new BasicHttpContext(null);
            try {
                mHttpService.handleRequest(mConnection, context);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    mConnection.shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}