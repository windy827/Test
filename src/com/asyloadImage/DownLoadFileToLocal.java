package com.asyloadImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

/**
 * Http下载文件到本地
 * 
 * @author 袁剑
 * 
 */
public class DownLoadFileToLocal {

	// 线程池
	private ExecutorService executorService;

	public DownLoadFileToLocal() {
		executorService = Executors.newFixedThreadPool(2);
	}

	public void loadImage(String imageURL, String fileName) {
		executorService.submit(new PhotosLoader(imageURL, fileName));
	}

	class PhotosLoader implements Runnable {
		String imageURL;
		Handler handler;
		String fileName;

		public PhotosLoader(String imageURL, String fileName) {
			this.imageURL = imageURL;
			this.fileName = fileName;
		}

		@Override
		public void run() {
			downLoadImage(imageURL, fileName);
		}
	}

	/**
	 * 下载图片到本地的方法
	 * 
	 * @param fileURL
	 *            图片URL路径
	 * @param fileName
	 *            图片下载到本地的文件夹路径
	 */
	public static void downLoadImage(String fileURL, String fileName) {

		/**
		 * 加上一个对本地缓存的查找
		 */
		String bitmapName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
		File cacheDir = new File(fileName);
		File[] cacheFiles = cacheDir.listFiles();
		int i = 0;
		if (null != cacheFiles) {
			for (; i < cacheFiles.length; i++) {
				if (bitmapName.equals(cacheFiles[i].getName())) {
					break;
				}
			}

		}

		if (i < cacheFiles.length && null != cacheFiles) {
			Log.d("FILEISEXIST", bitmapName + "已经存在了");
		} else {
			InputStream bitmapIs = HttpUtils.getStreamFromURL(fileURL);

			Bitmap bitmap = BitmapFactory.decodeStream(bitmapIs);
			File dir = new File(fileName);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File bitmapFile = new File(fileName
					+ fileURL.substring(fileURL.lastIndexOf("/") + 1));
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

}
