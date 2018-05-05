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
 * Http�����ļ�������
 * 
 * @author Ԭ��
 * 
 */
public class DownLoadFileToLocal {

	// �̳߳�
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
	 * ����ͼƬ�����صķ���
	 * 
	 * @param fileURL
	 *            ͼƬURL·��
	 * @param fileName
	 *            ͼƬ���ص����ص��ļ���·��
	 */
	public static void downLoadImage(String fileURL, String fileName) {

		/**
		 * ����һ���Ա��ػ���Ĳ���
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
			Log.d("FILEISEXIST", bitmapName + "�Ѿ�������");
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
