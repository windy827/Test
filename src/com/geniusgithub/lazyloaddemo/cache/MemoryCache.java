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
	// 放入缓存时是个同步操作
	// LinkedHashMap构造方法的最后一个参数true代表这个map里的元素将按照最近使用次数由少到多排列，即LRU
	// 这样的好处是如果要将缓存中的元素替换，则先遍历出最近最少使用的元素来替换以提高效率
	private Map<String, Bitmap> cache = Collections
			.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 0.75f, true));
	// 缓存中图片所占用的字节，初始0，将通过此变量严格控制缓存所占用的堆内存
	private long size = 0;// current allocated size
	// 缓存只能占用的最大堆内存
	private long limit = 1000000;// max memory in bytes

	public MemoryCache() {
		// use 25% of available heap size
		setLimit(Runtime.getRuntime().maxMemory() / 10);
	}

	public void setLimit(long new_limit) {
		limit = new_limit;
		//Log.d(TAG, "MemoryCache will use up to " + limit / 1024. / 1024. + "MB"+"****剩余内存**"+MemInfo.getmem_UNUSED(Tools.c) /1024/ 1024);
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
	 * 严格控制堆内存，如果超过将首先替换最近最少使用的那个图片缓存
	 * 
	 */
	private void checkSize() {
		Log.d(TAG, "MemoryCache will use up to " + limit / 1024. / 1024. + "MB"+"****剩余内存**"+MemInfo.getmem_UNUSED(MyApplication.getInstance().getCur_Activity()) );
		Log.i(TAG, "cache size=" + size + " length=" + cache.size());
		if (Math.abs(size) > (limit)) {
			// 先遍历最近最少使用的元素
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
	 * 图片占用的内存
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
