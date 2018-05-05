package com.geniusgithub.lazyloaddemo.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

public class OldImageLoader {

	private MemoryCache memoryCache = new MemoryCache();
	private AbstractFileCache fileCache;
	private Map<ImageView, String> imageViews = Collections
			.synchronizedMap(new WeakHashMap<ImageView, String>());
	//ArrayList<Bitmap>al_bitmaps=null;
	// �̳߳�
	private ExecutorService executorService=null;
	Context context;
	Bitmap bitmap=null ;
	ArrayList<PhotosLoader> list_PhotosLoader=new ArrayList<PhotosLoader>();
	public OldImageLoader(Context context) {
		this.context=context;
		//al_bitmaps=new ArrayList<Bitmap>();
		fileCache = new FileCache(context);
		//executorService = Executors.newFixedThreadPool(1);
		initExecutorService();
	}
	/**
	 * ��ʼ���̳߳�
	 */
public void initExecutorService(){
	
	if(executorService==null){
		executorService = Executors.newFixedThreadPool(1);
	}
		
	
	
}
	// ����Ҫ�ķ���
	/**
	 * 
	 * @param fileName
	 * @param url
	 * @param imageView
	 * @param isLoadOnlyFromCache
	 */
	public void DisplayImage(String fileName,String url, ImageView imageView, boolean isLoadOnlyFromCache) {
		imageViews.put(imageView, url);
		// �ȴ��ڴ滺���в���

		bitmap = memoryCache.get(url);
		if (bitmap != null&&!bitmap.isRecycled()){
			imageView.setImageBitmap(bitmap);
			//al_bitmaps.add(bitmap);
		}
			
		else if (!isLoadOnlyFromCache){
			
			// ��û�еĻ��������̼߳���ͼƬ
			queuePhoto(fileName,url, imageView);
		}
	}

	private void queuePhoto(String fileName,String url, ImageView imageView) {
		PhotoToLoad p = new PhotoToLoad(fileName,url, imageView);
		if(executorService==null||executorService.isShutdown()){
			executorService=Executors.newFixedThreadPool(1);
		}
		PhotosLoader photosLoader=new PhotosLoader(p);
		list_PhotosLoader.add(photosLoader);
		executorService.submit(photosLoader);
	}

	private Bitmap getBitmap(String fileName,String imageURL) {
		//File f = fileCache.getFile(url);
		
		// �ȴ��ļ������в����Ƿ���
File f = fileCache.getFile(imageURL);
		
		// �ȴ��ļ������в����Ƿ���
		bitmap= null;
		if (f != null && f.exists()){
			bitmap = decodeFile(f);
		}
		if (bitmap != null){
			//al_bitmaps.add(b);
			memoryCache.put(imageURL, bitmap);
			return bitmap;
		}
		// ����ָ����url������ͼƬ
		try {
			
			URL imageUrl = new URL(imageURL);
			HttpURLConnection conn = (HttpURLConnection) imageUrl
					.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setInstanceFollowRedirects(true);
			InputStream is = conn.getInputStream();
			OutputStream os = new FileOutputStream(f);
			CopyStream(is, os);
			os.close();
			bitmap = decodeFile(f);
			//al_bitmaps.add(bitmap);
			return bitmap;
		} catch (Exception ex) {
			Log.e("", "getBitmap catch Exception...\nmessage = " + ex.getMessage());
			return null;
		}
		/**
		 * ����һ���Ա��ػ���Ĳ���
		 */
//		Bitmap bitmap=null;
//		String bitmapName = imageURL
//				.substring(imageURL.lastIndexOf("/") + 1);
//		File cacheDir = new File(fileName);
//		File[] cacheFiles = cacheDir.listFiles();
//		int i = cacheFiles.length;
//		if (null != cacheFiles) {
//			for (; i < cacheFiles.length; i++) {
//				if (bitmapName.equals(cacheFiles[i].getName())) {
//					break;
//				}
//			}
//
//			if (i < cacheFiles.length) {
//				BitmapFactory.Options opt = new BitmapFactory.Options();
//				//opt.inJustDecodeBoundss = true;
//				opt.inPreferredConfig = Bitmap.Config.RGB_565;
//				 // 1.���������Ļ�Ŀ��.
//                int windowWidth = ((Activity) this.context).getWindowManager().getDefaultDisplay().getWidth();
//                int windowHeight = ((Activity) this.context).getWindowManager().getDefaultDisplay().getHeight();
//                //2. ����ͼƬ�Ŀ��.
//                // 2.�������ͼƬ�Ŀ��.
//               
//                // ���� ��ȥ�����Ľ���λͼ ���������ص��ڴ� ֻ�ǻ�ȡ���ͼƬ�Ŀ����Ϣ
//                opt.inJustDecodeBounds = true;
//                BitmapFactory.decodeFile( fileName + bitmapName, opt);
//                int bitmapHeight = opt.outHeight;
//                int bitmapWidth = opt.outWidth;
//
//                if (bitmapHeight > windowHeight || bitmapWidth > windowWidth) {
//                    int scaleX = bitmapWidth/windowWidth;
//                    int scaleY = bitmapHeight/windowHeight;
//                    if(scaleX>scaleY){//����ˮƽ����ı�������
//                    	opt.inSampleSize = scaleX;
//                    }else{//������ֱ����ı�������
//                    	opt.inSampleSize = scaleY;
//                    }
//
//                }else{//���ͼƬ���ֻ���ĻС ��ȥ������.
//                	opt.inSampleSize = 1;
//                }
//                //��λͼ����������ȥ����ͼƬ
//                opt.inJustDecodeBounds = false;
//				opt.inPurgeable = true;
//				opt.inInputShareable = true;
//				bitmap=BitmapFactory.decodeFile(fileName + bitmapName, opt);
//			}
//		if(i>=cacheFiles.length){
//			// ����ָ����url������ͼƬ
//			try {
//				File dir = new File(fileName);
//				if (!dir.exists()) {
//					dir.mkdirs();
//				}
//
//				File bitmapFile = new File(fileName
//						+ imageURL.substring(imageURL.lastIndexOf("/") + 1));
//				if (!bitmapFile.exists()) {
//					try {
//						bitmapFile.createNewFile();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				
//				URL imageUrl = new URL(imageURL);
//				HttpURLConnection conn = (HttpURLConnection) imageUrl
//						.openConnection();
//				conn.setConnectTimeout(30000);
//				conn.setReadTimeout(30000);
//				conn.setInstanceFollowRedirects(true);
//				InputStream is = conn.getInputStream();
//				OutputStream os = new FileOutputStream(fileName
//						+ imageURL.substring(imageURL.lastIndexOf("/") + 1));
//				CopyStream(is, os);
//				os.close();
//				bitmap = decodeFile(bitmapFile);
//				
//				
//		}
//		catch (Exception ex) {
//			Log.e("", "getBitmap catch Exception...\nmessage = " + ex.getMessage());
//			return null;
//		}
		//}
		//}
		//return bitmap;
	}

	// decode���ͼƬ���Ұ����������Լ����ڴ����ģ��������ÿ��ͼƬ�Ļ����СҲ�������Ƶ�
	private Bitmap decodeFile(File f) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 400;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE
						|| height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	// Task for the queue
	private class PhotoToLoad {
		public String url;
		public ImageView imageView;
		String fileName;
		public PhotoToLoad(String fileName,String u, ImageView i) {
			url = u;
			imageView = i;
			this.fileName=fileName;
		}
	}

	class PhotosLoader implements Runnable {
		PhotoToLoad photoToLoad;

		boolean isRunning=true;
		PhotosLoader(PhotoToLoad photoToLoad) {
			isRunning=true;
			this.photoToLoad = photoToLoad;
		}

		public void stopWork(){
			isRunning=false;
		}
		@Override
		public void run() {
			while(isRunning){
				isRunning=false;
				if (imageViewReused(photoToLoad))
					return;
				bitmap = getBitmap(photoToLoad.fileName,photoToLoad.url);
				memoryCache.put(photoToLoad.url, bitmap);
				if (imageViewReused(photoToLoad))
					return;
				BitmapDisplayer bd = new BitmapDisplayer(bitmap, photoToLoad);
				// ���µĲ�������UI�߳���
				Activity a = (Activity) photoToLoad.imageView.getContext();
				a.runOnUiThread(bd);
				
			}
			
		}
	}

	/**
	 * ��ֹͼƬ��λ
	 * 
	 * @param photoToLoad
	 * @return
	 */
	boolean imageViewReused(PhotoToLoad photoToLoad) {
		String tag = imageViews.get(photoToLoad.imageView);
		if (tag == null || !tag.equals(photoToLoad.url))
			return true;
		return false;
	}

	// ������UI�߳��и��½���
	class BitmapDisplayer implements Runnable {
		Bitmap bitmap;
		PhotoToLoad photoToLoad;

		public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
			bitmap = b;
			photoToLoad = p;
		}

		public void run() {
			if (imageViewReused(photoToLoad))
				return;
			if (bitmap != null)
				photoToLoad.imageView.setImageBitmap(bitmap);
	
		}
	}

	public void clearCache(boolean clearMem,boolean clearFile) {
		if(clearMem){
			memoryCache.clear();
		}
		if(clearFile){
			fileCache.clear();
		}
		
		
	} 
/**
 * ����ͼƬ
 */
	public void recyleBitmaps(){
		 //memoryCache.clear();
		 //System.gc();
		//executorService.shutdownNow();
		for(PhotosLoader photosLoader:list_PhotosLoader){
			photosLoader.stopWork();
		}
//		if(bitmap!=null&&!bitmap.isRecycled()){
//			bitmap.recycle();
//			bitmap=null;
//		}
//		System.gc();
	}
	public static void CopyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
			Log.e("", "CopyStream catch Exception...");
		}
	}
}