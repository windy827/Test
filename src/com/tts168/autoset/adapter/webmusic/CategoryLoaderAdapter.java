package com.tts168.autoset.adapter.webmusic;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asyloadImage.AsyncBitmapLoader;
import com.asyloadImage.AsyncBitmapLoader.ImageCallBack;
import com.autoset.jni.http.AudioEntity;
import com.autoset.jni.http.CategoryEntity;
import com.geniusgithub.lazyloaddemo.cache.OldImageLoader;
import com.larkiv.larksmart7618.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tts168.autoset.activity.webmusic.AnimateFirstDisplayListener;
import com.tts168.autoset.tools.commen.MyApplication;




/**
 * 分类列表
 * @author 袁剑
 *
 */

public class CategoryLoaderAdapter extends BaseAdapter{

	private static final String TAG = "CategoryLoaderAdapter";
	private boolean mBusy = false;
	public ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	private DisplayImageOptions options;
	
	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
		
	}

	private int mCount;
	private Context mContext;
//	private String[] urlArrays;
	
	ArrayList<CategoryEntity>info;
	String fileName;
	ArrayList<Bitmap>al_Bitmaps;//记录用于回收的图片
	/**
	 * 
	 * @param context
	 * @param info
	 * @param fileName 本地缓存的图片存放的文件夹名称
	 */
	public CategoryLoaderAdapter( Context context, ArrayList<CategoryEntity>info,String fileName) {
		this.info=info;
		this.mCount = (this.info==null?0:this.info.size());
		this.mContext = context;
		this.fileName=fileName;
		//urlArrays = url;
		al_Bitmaps=new ArrayList<Bitmap>();
		options = new DisplayImageOptions.Builder()
		.bitmapConfig(Bitmap.Config.RGB_565)
		.showImageOnLoading(R.drawable.default_small)
		.showImageForEmptyUri(R.drawable.default_small)
		.showImageOnFail(R.drawable.load_failed)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.build();
		
	}

	/**
	 * 在退出Activity的时候回收图片
	 */
	public void recycleBitmaps(){
		for(int i=0;i<al_Bitmaps.size();i++){
			Bitmap bitmap=al_Bitmaps.get(i);
			 if(bitmap!= null&& !bitmap.isRecycled())  {
				 bitmap.recycle();
				 bitmap=null;
				
			 }
		}
		 System.gc();
	}
	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.listitem_webfirst, null);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.tv_listitem_activity_webmusicfirst_name);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.iv_listitem_activity_webmusicfirst_info);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String url = "";
		url = this.info.get(position).getIcon();
		String title=this.info.get(position).getTitle();
		Log.d("URLICON",url);
		 viewHolder.mTextView.setText(title);
		 if (!mBusy) {
			 //MyApplication.asyncBitmapLoader.DisplayImage(this.fileName, url, viewHolder.mImageView, false);
			// asyncBitmapLoader.getNewsImage(url, viewHolder.mImageView);
			} 
		 ImageLoader.getInstance().displayImage(url, viewHolder.mImageView,  MyApplication.options, animateFirstListener);
//		 Bitmap bitmap=asyncBitmapLoader.loadBitmap(this.fileName,viewHolder.mImageView, url, new ImageCallBack() {  
//             
//             @Override  
//             public void imageLoad(ImageView imageView, Bitmap bitmap) {  
//                 // TODO Auto-generated method stub  
//                 imageView.setImageBitmap(bitmap);  
//                 al_Bitmaps.add(bitmap);
//           
//             }  
//         });  
//          if(bitmap == null)    
//             {    
//        	  //viewHolder.mImageView.setImageResource(R.drawable.default_small);
//        	 
//             }    
//             else    
//             {    
//            	 viewHolder.mImageView.setImageBitmap(bitmap);    
//             }    
		return convertView;
	}

	 class ViewHolder {
		TextView mTextView;
		ImageView mImageView;
	}
}
