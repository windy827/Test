package com.geniusgithub.lazyloaddemo;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.autoset.jni.http.AudioEntity;
import com.geniusgithub.lazyloaddemo.cache.OldImageLoader;
import com.larkiv.larksmart7618.R;
import com.larkiv.larksmart7618.R.color;
import com.tts168.autoset.tools.player.PlayerTools;






public class LoaderAdapter extends BaseAdapter{

	private static final String TAG = "LoaderAdapter";
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	
	private OldImageLoader mImageLoader;
	private int mCount;
	private Context mContext;
	int index=-1;//初始化没有任何Item被选中
//	private String[] urlArrays;
	
	ArrayList<AudioEntity>info;
	public LoaderAdapter( Context context, ArrayList<AudioEntity>info) {
		this.info=info;
		this.mCount = info.size();
		this.mContext = context;
		//urlArrays = url;
		mImageLoader = new OldImageLoader(context);
	}
	
	public OldImageLoader getImageLoader(){
		return mImageLoader;
	}
	public void SetFocus(int index) {
		this.index = index;
		// this.notifyDataSetChanged();
		this.notifyDataSetInvalidated();// 刷新界面
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
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.iv_back.setVisibility(View.INVISIBLE);
		String url = "";
		url = this.info.get(position).getIcon();
		String title=this.info.get(position).getTitle();
		Log.d("URLICON",url);
		int time=(int) this.info.get(position).getDuration();
		viewHolder.tv_time.setVisibility(View.VISIBLE);
		viewHolder.tv_time.setText(PlayerTools.getSongDurations(time));
		
		viewHolder.mImageView.setImageResource(R.drawable.webmusic);
		

		if(this.index==position){
			viewHolder.tv_time.setTextColor(mContext.getResources().getColor(color.blue));
			viewHolder.mTextView.setTextColor(mContext.getResources().getColor(color.blue));
		}else{
			viewHolder.tv_time.setTextColor(mContext.getResources().getColor(color.gray66_color));
			viewHolder.mTextView.setTextColor(mContext.getResources().getColor(color.gray99_color));
		}
		if (!mBusy) {
			mImageLoader.DisplayImage("",url, viewHolder.mImageView, false);
			viewHolder.mTextView.setText(title);
		} else {
			mImageLoader.DisplayImage("",url, viewHolder.mImageView, false);		
			//viewHolder.mTextView.setText("--" + position + "--FLING");
		}
		return convertView;
	}

	static class ViewHolder {
		TextView mTextView;
		TextView tv_time;
		ImageView mImageView;
		ImageView iv_back;
	}
}
