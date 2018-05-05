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
 * 图片的异步加载并且下载到本地的缓存机制
 * 
 * @author 袁剑
 * 
 */
public class AsyncBitmapLoader {
	/**
	 * 内存图片软引用缓冲
	 */
	private HashMap<String, SoftReference<Bitmap>> imageCache = null;
	// 线程池
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
		// 在内存缓存中，则返回Bitmap对象
		if (imageCache.containsKey(imageURL)) {
			SoftReference<Bitmap> reference = imageCache.get(imageURL);
			Bitmap bitmap = reference.get();
			if (bitmap != null) {
				return bitmap;
			}
		} else {
			/**
			 * 加上一个对本地缓存的查找
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
					 // 1.计算出来屏幕的宽高.
	                int windowWidth = ((Activity) this.context).getWindowManager().getDefaultDisplay().getWidth();
	                int windowHeight = ((Activity) this.context).getWindowManager().getDefaultDisplay().getHeight();
	                //2. 计算图片的宽高.
	                // 2.计算出来图片的宽高.
	               
	                // 设置 不去真正的解析位图 不把他加载到内存 只是获取这个图片的宽高信息
	                opt.inJustDecodeBounds = true;
	                BitmapFactory.decodeFile( fileName + bitmapName, opt);
	                int bitmapHeight = opt.outHeight;
	                int bitmapWidth = opt.outWidth;

	                if (bitmapHeight > windowHeight || bitmapWidth > windowWidth) {
	                    int scaleX = bitmapWidth/windowWidth;
	                    int scaleY = bitmapHeight/windowHeight;
	                    if(scaleX>scaleY){//按照水平方向的比例缩放
	                    	opt.inSampleSize = scaleX;
	                    }else{//按照竖直方向的比例缩放
	                    	opt.inSampleSize = scaleY;
	                    }

	                }else{//如果图片比手机屏幕小 不去缩放了.
	                	opt.inSampleSize = 1;
	                }
	                //让位图工厂真正的去解析图片
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

		// 如果不在内存缓存中，也不在本地（被jvm回收掉），则开启线程下载图片
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