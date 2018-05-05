package com.geniusgithub.lazyloaddemo.cache;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MemInfo;
import com.tts168.autoset.tools.commen.MyApplication;

import android.graphics.Bitmap;
import android.util.Log;

public class MemoryCache {

	private static final String TAG = "MemoryCache";
	// ���뻺��ʱ�Ǹ�ͬ������
	// LinkedHashMap���췽�������һ������true�������map���Ԫ�ؽ��������ʹ�ô������ٵ������У���LRU
	// �����ĺô������Ҫ�������е�Ԫ���滻�����ȱ������������ʹ�õ�Ԫ�����滻�����Ч��
	private Map<String, Bitmap> cache = Collections
			.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 0.75f, true));
	// ������ͼƬ��ռ�õ��ֽڣ���ʼ0����ͨ���˱����ϸ���ƻ�����ռ�õĶ��ڴ�
	private long size = 0;// current allocated size
	// ����ֻ��ռ�õ������ڴ�
	private long limit = 1000000;// max memory in bytes

	public MemoryCache() {
		// use 25% of available heap size
		setLimit(Runtime.getRuntime().maxMemory() / 10);
	}

	public void setLimit(long new_limit) {
		limit = new_limit;
		//Log.d(TAG, "MemoryCache will use up to " + limit / 1024. / 1024. + "MB"+"****ʣ���ڴ�**"+MemInfo.getmem_UNUSED(Tools.c) /1024/ 1024);
	}

	public Bitmap get(String id) {
		try {
			if (!cache.containsKey(id))
				return null;
			return cache.get(id);
		} catch (NullPointerException ex) {
			return null;
		}
	}

	public void put(String id, Bitmap bitmap) {
		try {
			if (cache.containsKey(id)){
				size -= getSizeInBytes(cache.get(id));
			}
			else{
				cache.put(id, bitmap);
				size += getSizeInBytes(bitmap);
				checkSize();
				
			}
			
			
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

	/**
	 * �ϸ���ƶ��ڴ棬��������������滻�������ʹ�õ��Ǹ�ͼƬ����
	 * 
	 */
	private void checkSize() {
		Log.d(TAG, "MemoryCache will use up to " + limit / 1024. / 1024. + "MB"+"****ʣ���ڴ�**"+MemInfo.getmem_UNUSED(MyApplication.getInstance().getCur_Activity()) );
		Log.i(TAG, "cache size=" + size + " length=" + cache.size());
		if (Math.abs(size) > (limit)) {
			// �ȱ����������ʹ�õ�Ԫ��
			Iterator<Entry<String, Bitmap>> iter = cache.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Bitmap> entry = iter.next();
				size -= getSizeInBytes(entry.getValue());
				//clear();
//				Bitmap temp=entry.getValue();
//				temp.recycle();
//				temp=null;
//				System.gc();
				
				iter.remove();
				if (Math.abs(size) <= (limit))
					break;
			}
			Log.i(TAG, "Clean cache. New size " + cache.size());
		}
	}

	public void clear() {
		cache.clear();
	}

	/**
	 * ͼƬռ�õ��ڴ�
	 * 
	 * [url=home.php?mod=space&uid=2768922]@Param[/url] bitmap
	 * 
	 * @return
	 */
	long getSizeInBytes(Bitmap bitmap) {
		if (bitmap == null)
			return 0;
		return bitmap.getRowBytes() * bitmap.getHeight();
	}
}
