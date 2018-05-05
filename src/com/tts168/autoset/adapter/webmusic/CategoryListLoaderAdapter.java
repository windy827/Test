package com.tts168.autoset.adapter.webmusic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.asyloadImage.AsyncBitmapLoader;
import com.asyloadImage.AsyncBitmapLoader.ImageCallBack;
import com.autoset.jni.http.AudioEntity;
import com.geniusgithub.lazyloaddemo.cache.OldImageLoader;
import com.larkiv.larksmart7618.R;
import com.larkiv.larksmart7618.R.color;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


import com.tts168.autoset.activity.webmusic.AnimateFirstDisplayListener;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.tools.quickset.DeviceSelectTools;






public class CategoryListLoaderAdapter extends BaseAdapter{

	private static final String TAG = "CategoryListLoaderAdapter";
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}
	public ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	private DisplayImageOptions options;
	

	private int mCount;
	private Context mContext;
	int index=-1;//初始化没有任何Item被选中
	private String[] urlArrays;
	String fileName;
	ArrayList<AudioEntity>info;
	ArrayList<Bitmap>al_Bitmaps;//记录用于回收的图片
	ListView actualListView;
	public CategoryListLoaderAdapter( Context context, ArrayList<AudioEntity>info,String fileName,ListView actualListView ) {
		this.info=info;
		this.fileName=fileName;
		this.mCount = (this.info==null?0:this.info.size());
		this.mContext = context;
		this.actualListView=actualListView;
		al_Bitmaps=new ArrayList<Bitmap>();
		
		options = new DisplayImageOptions.Builder()
		.bitmapConfig(Bitmap.Config.RGB_565)
		.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位 
		.showImageOnLoading(R.drawable.default_small)
		.showImageForEmptyUri(R.drawable.default_small)
		.showImageOnFail(R.drawable.load_failed)
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示 
		
		.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
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
	public void SetFocus(int index) {
		this.index = index;
		//this.notifyDataSetChanged();
		
		this.notifyDataSetInvalidated();// 刷新界面
		//this.actualListView.setSelection(index);
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
			viewHolder.tv_time = (TextView) convertView
					.findViewById(R.id.tv_listitem_activity_webmusicfirst_time);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.iv_listitem_activity_webmusicfirst_info);
			viewHolder.iv_back = (ImageView) convertView
					.findViewById(R.id.iv_listitem_activity_webmusicfirst_detail);
			viewHolder.iv_back.setVisibility(View.INVISIBLE);
		
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		String url = "";
		url = this.info.get(position).getIcon();
		String title=this.info.get(position).getTitle();
		Log.d("URLICON",url);
		int time=(int) this.info.get(position).getDuration();
		viewHolder.tv_time.setVisibility(View.VISIBLE);
		viewHolder.tv_time.setText(PlayerTools.getSongDurations(time));
		
		//viewHolder.mImageView.setImageResource(R.drawable.webmusic);
		

		if(this.index==position){
			viewHolder.tv_time.setTextColor(mContext.getResources().getColor(color.blue));
			viewHolder.mTextView.setTextColor(mContext.getResources().getColor(color.blue));
		}else{
			viewHolder.tv_time.setTextColor(mContext.getResources().getColor(color.gray66_color));
			viewHolder.mTextView.setTextColor(mContext.getResources().getColor(color.gray99_color));
		}
		viewHolder.mTextView.setText(title);
		 if (!mBusy) {
			// MyApplication.asyncBitmapLoader.DisplayImage(this.fileName, url, viewHolder.mImageView, false);
			 //asyncBitmapLoader.getNewsImage(url, viewHolder.mImageView);
			} 
		ImageLoader.getInstance().displayImage(url, viewHolder.mImageView,  MyApplication.options, animateFirstListener);
// Bitmap bitmap=asyncBitmapLoader.loadBitmap(this.fileName,viewHolder.mImageView, url, new ImageCallBack() {  
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
//             }    
//             else    
//             {    
//            	 viewHolder.mImageView.setImageBitmap(bitmap);    
//             }    
//	
		return convertView;
	}

	 class ViewHolder {
		TextView mTextView;
		TextView tv_time;
		ImageView mImageView;
		ImageView iv_back;
	}
	
}
