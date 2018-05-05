package com.asyloadImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tts168.autoset.tools.Tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * ͼƬ���첽���ز������ص����صĻ������
 * 
 * @author Ԭ��
 * 
 */
public class AsyncBitmapLoader {
	/**
	 * �ڴ�ͼƬ�����û���
	 */
	private HashMap<String, SoftReference<Bitmap>> imageCache = null;
	// �̳߳�
	private ExecutorService executorService;
	
	Context context;

	public void clearCache() {
	
		imageCache.clear();
	}

	public AsyncBitmapLoader(Context context) {
		this.context=context;
		imageCache = new HashMap<String, SoftReference<Bitmap>>();
		executorService = Executors.newFixedThreadPool(2);
	}

	public Bitmap loadBitmap(final String fileName, final ImageView imageView,
			final String imageURL, final ImageCallBack imageCallBack) {
		// ���ڴ滺���У��򷵻�Bitmap����
		if (imageCache.containsKey(imageURL)) {
			SoftReference<Bitmap> reference = imageCache.get(imageURL);
			Bitmap bitmap = reference.get();
			if (bitmap != null) {
				return bitmap;
			}
		} else {
			/**
			 * ����һ���Ա��ػ���Ĳ���
			 */
			String bitmapName = imageURL
					.substring(imageURL.lastIndexOf("/") + 1);
			File cacheDir = new File(fileName);
			File[] cacheFiles = cacheDir.listFiles();
			int i = 0;
			if (null != cacheFiles) {
				for (; i < cacheFiles.length; i++) {
					if (bitmapName.equals(cacheFiles[i].getName())) {
						break;
					}
				}

				if (i < cacheFiles.length) {
					BitmapFactory.Options opt = new BitmapFactory.Options();
					//opt.inJustDecodeBoundss = true;
					opt.inPreferredConfig = Bitmap.Config.RGB_565;
					 // 1.���������Ļ�Ŀ��.
	                int windowWidth = ((Activity) this.context).getWindowManager().getDefaultDisplay().getWidth();
	                int windowHeight = ((Activity) this.context).getWindowManager().getDefaultDisplay().getHeight();
	                //2. ����ͼƬ�Ŀ��.
	                // 2.�������ͼƬ�Ŀ��.
	               
	                // ���� ��ȥ�����Ľ���λͼ ���������ص��ڴ� ֻ�ǻ�ȡ���ͼƬ�Ŀ����Ϣ
	                opt.inJustDecodeBounds = true;
	                BitmapFactory.decodeFile( fileName + bitmapName, opt);
	                int bitmapHeight = opt.outHeight;
	                int bitmapWidth = opt.outWidth;

	                if (bitmapHeight > windowHeight || bitmapWidth > windowWidth) {
	                    int scaleX = bitmapWidth/windowWidth;
	                    int scaleY = bitmapHeight/windowHeight;
	                    if(scaleX>scaleY){//����ˮƽ����ı�������
	                    	opt.inSampleSize = scaleX;
	                    }else{//������ֱ����ı�������
	                    	opt.inSampleSize = scaleY;
	                    }

	                }else{//���ͼƬ���ֻ���ĻС ��ȥ������.
	                	opt.inSampleSize = 1;
	                }
	                //��λͼ����������ȥ����ͼƬ
	                opt.inJustDecodeBounds = false;
					opt.inPurgeable = true;
					opt.inInputShareable = true;
					return BitmapFactory.decodeFile(fileName + bitmapName, opt);
				}
			}
		}

		final Handler handler = new Handler() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see android.os.Handler#handleMessage(android.os.Message)
			 */
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				imageCallBack.imageLoad(imageView, (Bitmap) msg.obj);
			}
		};

		// ��������ڴ滺���У�Ҳ���ڱ��أ���jvm���յ����������߳�����ͼƬ
		executorService.submit(new PhotosLoader(imageURL, fileName, handler));
		return null;
	}

	class PhotosLoader implements Runnable {
		String imageURL;
		Handler handler;
		String fileName;

		public PhotosLoader(String imageURL, String fileName, Handler handler) {
			this.imageURL = imageURL;
			this.handler = handler;
			this.fileName = fileName;
		}

		@Override
		public void run() {
			InputStream bitmapIs = HttpUtils.getStreamFromURL(imageURL);

			Bitmap bitmap = BitmapFactory.decodeStream(bitmapIs);
			imageCache.put(imageURL, new SoftReference<Bitmap>(bitmap));
			Message msg = handler.obtainMessage(0, bitmap);
			handler.sendMessage(msg);

			File dir = new File(fileName);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File bitmapFile = new File(fileName
					+ imageURL.substring(imageURL.lastIndexOf("/") + 1));
			if (!bitmapFile.exists()) {
				try {
					bitmapFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(bitmapFile);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public interface ImageCallBack {
		public void imageLoad(ImageView imageView, Bitmap bitmap);
	}
}