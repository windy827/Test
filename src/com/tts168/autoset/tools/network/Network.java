package com.tts168.autoset.tools.network;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.deviceInfo.DeviceInfoEntity;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.udp.SearchJsonOption;
import com.autoset.jni.udp.SearchRetrunEntity;
import com.autoset.jni.udp.SearchSendEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;
import com.autoset.json.MyTools;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.database.GetOrUpdateDisConnect;
import com.tts168.autoset.tools.FuncSwitch;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.MyLogTools;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.VersionTools;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.others.NetWorkUtils;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.others.wifi.NetManager;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.tcpAndudp.UDPSendContent;
import com.tts168.autoset.tools.tcpAndudp.WifiWatchTools;
import com.tts168.autoset.tools.tcpAndudp.tcpsever.TCPThread;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class Network {

	private static final String TAG = Network.class.getName();

	private static final int UDP_FREQUENCY = 1000;
	private static final int TCP_FREQUENCY = 3000;
	private static final int TCP_CONNECT_TIMEOUT = 3000;
	// private static String
	// OFFLINE_ALART="手机与设备断开连接，请检查手机与设备是否在同一WIFI或者设备是否已经断开WIFI连接了！";
	private static final String OFFLINE_ALART = "手机与设备断开连接，请检查设备是否已经断开WIFI连接了！";
	private static Network mInstance;

	public static Network getInstance(Context ctx) {
		synchronized (Network.class) {
			if (null == mInstance && ctx != null) {
				mInstance = new Network(ctx);
			}
			return mInstance;
		}
	}

	public static void destroy() {
		synchronized (Network.class) {
			if (mInstance != null) {
				mInstance.cleanup();
				mInstance = null;
			}
		}
	}

	public static void delay(long ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			// don't care!
		}
	}

	public static void closeResource(Closeable obj) {
		try {
			if (obj != null) {
				obj.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class DeviceNode {
		public SearchRetrunEntity mEntity;
		public Socket mSocket;
		public long mLastActive;
		public long mLastSend;
		public boolean mExitNow;
		public TcpThread mThread;
	};

	private class SendTask extends AsyncTask<Void, Void, Void> {

		private TcpThread mThread;
		private byte[] mData;
		private boolean mFinish;

		public SendTask(TcpThread thread, byte[] data) {
			this.mThread = thread;
			this.mData = data;
			this.mFinish = false;
		}

		@Override
		protected Void doInBackground(Void... params) {
			if (mThread != null) {
				mThread.sendRequest(mData, 0, mData.length);
			} else {
				mThread = null;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			synchronized (this) {
				this.mFinish = true;
			}
		}

		public boolean isFinish() {
			synchronized (this) {
				return this.mFinish;
			}
		}
	};

	private Context   mContext;
	private long      mUdpFreq;
	private boolean   mExitNow;
	private Handler   mHandler;
	private Runnable  mRunnable;
	private UdpThread mUdpThread;
	private HashMap<String, DeviceNode> mNodes;

	private Network(Context ctx) {
		ctx = MyApplication.getInstance();
		synchronized (TCPTools.class) {
			if (TCPTools.tcpThread == null) {
				TCPTools.tcpThread = new TCPThread(ctx);
			}
		}

		this.mContext = ctx;
		this.mUdpFreq = UDP_FREQUENCY;
		this.mExitNow = false;
		this.mHandler = new Handler(ctx.getMainLooper());
		this.mNodes = new HashMap<String, DeviceNode>();
		
		if(mUdpThread==null){
			mUdpThread = new UdpThread();
			mUdpThread.start();

		}else {
			if (!mUdpThread.isActived()) {
				mUdpThread = new UdpThread();
				mUdpThread.start();
			}
		}
		
		mRunnable = new Runnable() {

			private void sendHeartBeat(String socket) {
				ArrayList<String> names = new ArrayList<String>();
				String content=new AutoSetJsonTools()
				.setGetDomainJsonObject(JsonParseOption.GET_HEARTBEATDATE,
						new String[]{DeviceInfoEntity.DOMAIN_DEVICE_INFO});
				try {
				JSONObject main=new JSONObject(content);
				
					main.put("timeD", GetAndSetTime.setTime());
					MyLogTools.d("NETWORKSENDHEART",main.toString());
					names.add(main.toString());
					TCPTools.sendTcp(names, socket, false);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//TCPTools.sendTcpByDomain(names, socket);
			}

			@Override
			public void run() {
				long now = System.currentTimeMillis();
				synchronized (Network.this) {
					if (!mUdpThread.isActived()) {
						mUdpThread = new UdpThread();
						mUdpThread.start();
					}
					
					ArrayList<DeviceNode> nodes = new ArrayList<DeviceNode>();
					for (DeviceNode node : mNodes.values()) {
						synchronized (node) {
							if (node.mLastActive <= 0) { // thread not start yet!
								continue;
							}

							if (now - node.mLastActive >= TCP_FREQUENCY && now - node.mLastSend > TCP_FREQUENCY) {
								node.mLastSend = now;
								if (node.mSocket != null) {
									sendHeartBeat(node.mEntity.getIp());
								}
							}

							if (now - node.mLastActive >= 3 * TCP_FREQUENCY) {
								 MyLogTools.d("SOCKETTIMEOUT","连接超时！！！");

								nodes.add(node);
							}
						}
					}

					for (DeviceNode node : nodes) {
						removeDeviceNode(node, true,false);
					}
				}
				mHandler.postDelayed(this, 200);
			}

		};
		if(FuncSwitch.SWITCH_TCP_HEARTBEAT){
			this.mHandler.post(mRunnable);
			MyLogTools.d(FuncSwitch.TAG, "心跳开关开启");
		}else{
			MyLogTools.d(FuncSwitch.TAG, "心跳开关关闭");
		}
		
	}

	public void setUdpFrequency(long time) {
		synchronized (this) {
			this.mUdpFreq = 1000;// time;
		}
	}

	/**
	 * 清除设备记录
	 */
	public void clearNodes() {
		synchronized (this) {
			for (DeviceNode node : mNodes.values()) {
				synchronized (node) {
					node.mExitNow = true;
					
					MyLogTools.d("LONGTCPTEST","clearNodes mExitNow"+node.mExitNow);
				}
			}
			this.mNodes.clear();
		}
	}
	/**
	 * 清除node记录
	 */
	public void clearNodesrecored() {
		synchronized (this) {
			this.mNodes.clear();
		}
	}
/**
 * 关闭掉所有的socket
 */
	public void closeAllSocket(){
		for (DeviceNode node : mNodes.values()) {
			synchronized (node) {
				 closeSocket(node.mSocket);
			}
		}
		mUdpThread.closeSocket();
	}
	public void closeSocket(Socket socket){
		
		try {
			if (socket != null)  
			{  
			    socket.shutdownInput();  
			    socket.shutdownOutput();  
			              
			    InputStream in = socket.getInputStream();  
			    OutputStream ou = socket.getOutputStream();   
			    try{  
			        in.close();  
			        ou.close();  
			    }  
			    catch (IOException e)  
			    {  
			                  
			    }  
			  //关闭socket  
				socket.close();
			}  
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * 得到记录的设备个数
	 * 
	 * @return
	 */
	public int getNodeSize() {
		synchronized (this) {
			return this.mNodes.size();
		}
	}


	public void refreshDevice() {
		ArrayList<String> domainName = new ArrayList<String>();
		domainName.add(GeneralEntity.DOMAIN_NAME);
		synchronized (this) {
			for (DeviceNode node : mNodes.values()) {
				if (null == node.mSocket) {
					continue;
				}
				TCPTools.sendTcpByDomain(domainName, node.mEntity.getIp());
			}
		}
	}

	public boolean sendToHost(String socketIP, byte[] data) {
		synchronized (this) {
			
			String ip =socketIP;
			
			DeviceNode node = mNodes.get(ip);
			if (null == node) {
				return false;
			}
			final SendTask task = new SendTask(node.mThread, data);
			task.execute((Void) null);
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					if (!task.isFinish()) {
						task.cancel(true);
						MyLogTools.d(TAG, "Task not finish in a certain time");
					}
				}
			}, 1000);
		}

		return true;
	}

	private void cleanup() {
		synchronized (this) {
			this.mExitNow = true;
			this.mHandler.removeCallbacksAndMessages(null);
			for (DeviceNode node : mNodes.values()) {
				synchronized (node) {
					node.mExitNow = true;
					MyLogTools.d("LONGTCPTEST","cleanup mExitNow289"+node.mExitNow);
				}
			}
		}
	}

	/**
	 * 根据设备返回的IP地址决定是否需要进行TCP连接
	 * @param entity
	 */
	private void onFoundEntity(SearchRetrunEntity entity) {
		synchronized (this) {
			if (this.mNodes.containsKey(entity.getIp())) {
				return;
			}
			MyLogTools.d("UDPTCOCONNECTTIME","收到UDP的时间并建立连接"+System.currentTimeMillis());
			DeviceNode node = new DeviceNode();
			node.mEntity = entity;
			node.mSocket = null;
			node.mLastActive = 0;
			node.mLastSend = 0;
			node.mExitNow = false;
			node.mThread = new TcpThread(node);
			mNodes.put(entity.getIp(), node);
			node.mThread.start();
		}
	}

	private void removeDeviceNode(DeviceNode node, boolean restart, boolean needAlart) {
		SearchRetrunEntity entity = node.mEntity;
		synchronized (this) {
			String socketIP = Tools.Current_SocketIP;
			final String  curDevName= Tools.Current_DeviceName;
			final String nodeIp=node.mEntity.getIp();
			if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
				//删除掉不可用的数据
				int index=-1;
				for(int i=0;i<TerminalTools.adapter_infoByIP.size();i++){
					TerminalAdapterEntity temp=TerminalTools.adapter_infoByIP.get(i);
					if(temp.getSocketIp().equals(socketIP)){
						index=i;
						break;
					}
				}
				if(index>=0){
					TerminalTools.adapter_infoByIP.remove(index);
					
					mHandler.post(new Runnable() {
						@Override
						public void run() {	
							if( Tools.Current_SocketIP.equals(nodeIp)){
								Tools.Current_DeviceName="";
								if(FuncSwitch.SWITCH_TCP_RECORD_DISCONNECT){
									GetOrUpdateDisConnect.recordDisConnect();
								}
								NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), "设备"+curDevName+"已断开连接！！！");
							}
							
							
							}
						});
					
				}
			}
			if (socketIP.length()>0 && needAlart) {				
				String ip = socketIP;
				for (DeviceNode dev : mNodes.values()) {
					synchronized (dev) {
						//String ip2 = dev.mEntity.getIp();
						if (ip.equals(node.mEntity.getIp())) {
							mHandler.post(new Runnable() {
								@Override
								public void run() {	
									Activity aa=(Activity)MyApplication.getInstance().getCur_Activity();
									
									if(!aa.isFinishing()){
										if(Tools.CurrentActivityName.equals(SearchDevicesActivity.ActivityName)||Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
											return;
										} 
										else{
											//当前设备断开提示
											if(FuncSwitch.SWITCH_TCP_RECORD_DISCONNECT){
												GetOrUpdateDisConnect.recordDisConnect();
											}
	 										
											NotifyDialog.SimpleAlertDialog_NetAlart(MyApplication.getInstance().getCur_Activity(),
													OFFLINE_ALART);
										}
										
									}else{
										SharedPreferences shared;

										shared = new SharedConfig(MyApplication.getInstance().getCur_Activity()).GetConfig(); // 得到配置文件
										WifiAdmin wa;// Wifi操作类
										wa = new WifiAdmin(MyApplication.getInstance().getCur_Activity());
										String wifiinfo = wa.GetBSSID();

										shared.edit()
												.putString(SharedConfig.KEY_WIFIBSSID, wifiinfo)
												.commit();
									
										TerminalTools.adapter_infoByIP.clear();
										ActivitySetting.startActivity((Activity) MyApplication.getInstance().getCur_Activity(),
												SearchDevicesActivity.class, null,true);
									}
									
								}
							});
							break;
						}
					}
				}
			}
			
			synchronized (node) {
				node.mExitNow = true;
				MyLogTools.d("LONGTCPTEST","cleanup mExitNow 400"+node.mExitNow);
			}

			for (DeviceNode dev : mNodes.values()) {
				if (dev == node) {
					mNodes.remove(entity.getIp());
					break;
				}
			}

			if (restart) {
				onFoundEntity(node.mEntity);
			}
		}
	}

	private class TcpThread extends Thread {

		private DeviceNode mNode;
		private InputStream mInStream;
		private OutputStream mOutStream;

		public TcpThread(DeviceNode node) {
			mNode = node;
			mInStream = null;
			mOutStream = null;
		}

		public boolean sendRequest(byte[] data, int offset, int count) {
			boolean result = false;
			synchronized (this) {
				if (mOutStream != null) {
					try {
						mOutStream.write(data, offset, count);
						result = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					mOutStream = null;
				}
			}
			return result;
		}

		@Override
		public void run() {
			final String ip = mNode.mEntity.getIp();
			int port = mNode.mEntity.getPort();
			Socket socket = null;
			MyLogTools.d("UDPTCPSIZE","TCP建立连接："+GetAndSetTime.setTime());
			MyLogTools.d(TAG, "start tcp thread for " + ip);
			try {
				socket = new Socket();
				SocketAddress addr = new InetSocketAddress(ip, port);
				socket.connect(addr, TCP_CONNECT_TIMEOUT);
				socket.setSoTimeout(100);

				mInStream = socket.getInputStream();
				synchronized (this) {
					mOutStream = socket.getOutputStream();
				}
				synchronized (mNode) {
					mNode.mSocket = socket;
					mNode.mLastActive = System.currentTimeMillis();
				}
			} catch (Exception e) {
				e.printStackTrace();
				MyLogTools.d(TAG, e.getMessage());

				removeDeviceNode(mNode, false,true);
				if (mInStream != null) {
					try {
						mInStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				mInStream = null;

				synchronized (this) {
					if (mOutStream != null) {
						try {
							mOutStream.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					mOutStream = null;
				}
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				MyLogTools.d(TAG, "cannot connect to " + ip);
				return;
			}
			
			mHandler.post(new Runnable(){
				@Override
				public void run() {
					if(Tools.Current_SocketIP.length()>0  &&
							Tools.Current_SocketIP.equals(ip)){
						Tools.Current_SocketIP = ip;
						if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
							NotifyDialog.dialogBuilder.dismiss();
							NotifyDialog.dialogBuilder=null;
						}
					}
				}
			});

			ArrayList<String> names = new ArrayList<String>();
			names.add(GeneralEntity.DOMAIN_NAME);
			TCPTools.sendTcpByDomain(names, ip);

			//LogTools.d("UDPTCPSIZE","发送了gernal 请求"+GetAndSetTime.setTime());
			while (true) {
				synchronized (mNode) {
					if (mNode.mExitNow) {
						break;
					}
				}

				byte[] header = new byte[8];
				if (!readExactly(header, header.length)) {
					break;
				}

				ByteBuffer buffer = ByteBuffer.wrap(header, 4, 4);
				buffer.order(ByteOrder.BIG_ENDIAN);
				int datalen = buffer.getInt();
				if(datalen>20000){
					int a=100;
					MyLogTools.d("LONGTCPTEST","接收到的长度为："+datalen);
				}
				
				if (datalen > 1024 * 1024 * 10) { // bigger than 10M
					break;
				}

				byte[] data = new byte[datalen];
				if (!readExactly(data, data.length)) {
					break;
				}

				try {
					synchronized (TCPTools.class) {
						String jsonStr = new String(data, "UTF-8");
						Handler handler = TCPTools.tcpThread.handler;
						Message msg = handler.obtainMessage();
						Bundle bundle = new Bundle();
						bundle.putString(TCPThread.Handler_MSG, jsonStr);
						if(datalen>20000){
							MyLogTools.d("LONGTCPTEST","接收到的数据为："+jsonStr);
						}
						
						msg.what = 0x1;
						msg.obj = ip;
						msg.setData(bundle);
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					// never happen!
				}

				synchronized (mNode) {
					mNode.mLastActive = System.currentTimeMillis();
				}
			}

			boolean restart = true;
			synchronized (mNode) {
				if (mNode.mExitNow) {
					restart = false;
				}
			}

			removeDeviceNode(mNode, restart, restart);
			if (mInStream != null) {
				try {
					mInStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			mInStream = null;

			synchronized (this) {
				if (mOutStream != null) {
					try {
						mOutStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				mOutStream = null;
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			MyLogTools.d(TAG, "exit tcp thread " + ip);
		}

		private boolean readExactly(byte[] data, int length) {
			int curSize = 0;
			while (curSize < length) {
				synchronized (mNode) {
					if (mNode.mExitNow) {
						break;
					}
				}

				try {
					int rdLen = mInStream.read(data, curSize, length - curSize);
					if (rdLen < 0) {
						break;
					}
					curSize += rdLen;
				} catch (SocketTimeoutException e) {
					// It is OK!
				} catch (Exception e) {
					e.printStackTrace();
					MyLogTools.d(TAG, e.getMessage());
					break;
				}
			}

			return curSize == length;
		}

	};

	private class UdpThread extends Thread {
		
		private DatagramSocket mSocket;
		private long           mLastUpdate;
		private boolean        mKillSell;
		
		public UdpThread() {
			mSocket = null;
			mLastUpdate = 0;
			mKillSell = false;
		}
		
		public void closeSocket() {
			synchronized(this) {
				if (mSocket != null) {
					try {
						mSocket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					mSocket = null;
				}
			}
		}
		
		public boolean isActived() {
			synchronized(this) {
				if (mLastUpdate > 0) {
					long now = System.currentTimeMillis();
					if (now - mLastUpdate > 10000) {
						mKillSell = true;
						closeSocket();
						return false;
					}
				}
			}
			return true;
		}

		private byte[] getNotifyString() {
			if (!new NetManager(mContext).isOpenNetwork()) {
				return null;
			}

			String ip = NetWorkUtils.getLocalIpAddress(mContext);
			String devId = "AE-RT-OP-12";
			String version = VersionTools.getVersionName(mContext);
			SearchSendEntity entity = new SearchSendEntity(ip, devId, version,GetAndSetTime.setTime());
			String data = SearchJsonOption.setUDPSearch(entity);
			int flag = UDPSendContent.SEND_FLAG_JSON;

			byte[] content = MyTools.getJNIUseByte(data);
			return UDPSendContent.getSendDate(flag, content);
		}

		public String getBSSID() {
			final WifiAdmin wa;// Wifi操作类
			wa = new WifiAdmin(mContext);
			String bssid = wa.GetBSSID();
			if (bssid == null) {
				bssid = "";
			}
			return bssid;
		}

		@Override
		public void run() {
			int port = Tools.UDP_SEND_TARGET_PORT;
			long lastSend = 0;				
			byte[] data = null;
			InetAddress addr = null;
			DatagramPacket packet = null;
			DatagramSocket socket = null;
			
			boolean checkSSID = false;
			String ssid = "";
			
			MyLogTools.d(TAG, "start udp thread!");
			while (true) {
				if(socket!=null){
					socket.close();
				}
				synchronized (Network.this) {
					synchronized(this) {
						if (mExitNow || mKillSell) {
							break;
						}
					}
				}
				
				synchronized(this) {
					this.mLastUpdate = System.currentTimeMillis();
				}
				
				try {
					addr = InetAddress.getByName("255.255.255.255");
					socket = new DatagramSocket();
					socket.setSoTimeout(100);
				} catch (Exception e) {
					e.printStackTrace();
					MyLogTools.d(TAG, "cannot create socket: " + e.getMessage());
					
					delay(1000);
					continue;
				}
			
				while (true) {
					long freq = UDP_FREQUENCY;
					synchronized (Network.this) {
						synchronized(this) {
							if (mExitNow || mKillSell) {
								break;
							}
						}					
						freq = mUdpFreq;
					}
					
					synchronized(this) {
						this.mLastUpdate = System.currentTimeMillis();
						this.mSocket = socket;
					}
					
					long now = System.currentTimeMillis();
					if (now - lastSend >= freq) {
						data = getNotifyString();
						if (null == data) {
							checkSSID = true;
							mHandler.post(new Runnable(){
								@Override
								public void run() {
									TerminalTools.adapter_infoByIP.clear();
									if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
										((TerminalActivity)MyApplication.getInstance().getCur_Activity()).searchDevice2Refresh();;
									}
								}
							});
							lastSend = now;
							break;
						}
						
						MyLogTools.d("UDPSENDDATASIZE","UDP数据大小为"+data.length);
						packet = new DatagramPacket(
								data, data.length, addr, port);
						try {
							socket.send(packet);
							MyLogTools.d("UDPRECIVEEMSG",GetAndSetTime.setTime());
						} catch (IOException e) {
							e.printStackTrace();
							MyLogTools.d(TAG, "cannot send udp packet: "
									+ e.getMessage());
						}
						
						if (ssid.length() < 1) {
							ssid = getBSSID(); 
						}
						if (checkSSID) {
							String ssid2 = getBSSID();
							if (!ssid.equals(ssid2)) {
								mHandler.post(new Runnable(){
									@Override
									public void run() {
										if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
											((TerminalActivity)MyApplication.getInstance().getCur_Activity()).searchDevice2Refresh();;
										}
										if(Tools.Current_SocketIP.length()==0||Tools.CurrentActivityName.equals(SearchDevicesActivity.ActivityName)){
											return;
										}
										if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
											NotifyDialog.dialogBuilder.dismiss();
											NotifyDialog.dialogBuilder=null;
										}
										Activity aa=(Activity)MyApplication.getInstance().getCur_Activity();
										if(!aa.isFinishing()){
											if(FuncSwitch.SWITCH_TCP_RECORD_DISCONNECT){
												GetOrUpdateDisConnect.recordDisConnect();
											}
											NotifyDialog.SimpleAlertDialog_NetAlart(MyApplication.getInstance().getCur_Activity(), WifiWatchTools.Alarm__STATE_WIFICHANGE1+WifiWatchTools.Alarm__STATE_WIFICHANGE2);
										}else{
											SharedPreferences shared;

											shared = new SharedConfig(MyApplication.getInstance().getCur_Activity()).GetConfig(); // 得到配置文件
											WifiAdmin wa;// Wifi操作类
											wa = new WifiAdmin(MyApplication.getInstance().getCur_Activity());
											String wifiinfo = wa.GetBSSID();

											shared.edit()
													.putString(SharedConfig.KEY_WIFIBSSID, wifiinfo)
													.commit();
										
											TerminalTools.adapter_infoByIP.clear();
											ActivitySetting.startActivity((Activity) MyApplication.getInstance().getCur_Activity(),
													SearchDevicesActivity.class, null,true);
										}
										
									}
								});
							}
							checkSSID = false;
						}
						lastSend = now;
					}
					
					try {
						data = new byte[4096];
						packet = new DatagramPacket(data, data.length);
						socket.receive(packet);
						
						data = packet.getData();
						int headerlen = 8;
						MyLogTools.d("getUDPString",new String(data, "utf-8").trim());
						int offset = packet.getOffset() + headerlen;
						int length = packet.getLength() - headerlen;
						
						String jsonStr = new String(data, offset, length, "utf-8");						
						
						SearchRetrunEntity entity = null;
						entity = SearchJsonOption.getSearchRetrunEntity(jsonStr);
						entity.setIp(packet.getAddress().getHostAddress());
						
						MyLogTools.d("UDPANDTCP",jsonStr);
						
						MyLogTools.d("UDPRECIVEEMSG","cSendTime="+entity.getcSendTime()+"****"+"dReciveTime="
						         +entity.getdReciveTime()+"*****"+"dSendTime="+entity.getdSendTime()+"*****"+"CellphoneReciveTime="+entity.getCellphoneReciveTime());
						//if(!NetWorkUtils.getLocalIpAddress(mContext).equals(packet.getAddress().getHostAddress())){
							onFoundEntity(entity);
						//}
						
					} catch(SocketTimeoutException e) {
						// it is OK!
					} catch(Exception e) {
						e.printStackTrace();
						MyLogTools.d(TAG, "udp socket exception: " + e.getMessage());
					}
				}
			}
			
			MyLogTools.d(TAG, "udp thread exit!");
		}
	};

}
