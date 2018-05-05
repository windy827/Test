package com.tts168.autoset.adapter.alart;

import java.util.ArrayList;
import java.util.HashMap;

import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.play.PlayItemEntity;
import com.autoset.jni.play.PlayOptions;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 显示铃声列表的界面的Adapter【以后如果涉及到想分类，可以添加筛选按钮】
 * 
 * @author 袁剑
 * 
 */
public class ChooseRingAdapter extends BaseAdapter {

	ListView currentListView;// 当前用来显示的ListView控件
	private ArrayList<HashMap<String, Object>> info;// 取得到的这次阅览的文字信息，包括设备名称
	ChooseRingAdapter instatnce;
	LayoutInflater lf;
	private Context c;
	TextView tv_deviceName;
	/**
	 * 先前按下的播放歌曲对应的索引，为-1默认为没有点击过
	 */
	public static int preSongIndex = -1;
	/**
	 * 当前页面的音乐播放的状态
	 */
	public static boolean isPlay = false;

	public static boolean isPlays[];
	boolean mBusy;
	SparseArray< View> map = new SparseArray< View>();
	

	/**
	 * 
	 * @param c
	 * @param info
	 */
	public ChooseRingAdapter(Context c, ArrayList<HashMap<String, Object>> info,ListView lv) {
		this.c = c;
		this.currentListView=lv;
		instatnce = this;
		this.info = info;
		lf = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		isPlays = new boolean[this.info.size()];

	}
	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	public class MyView {

		private TextView tv_ring;// 声音名称
		private TextView tv_ringinfo;// 声音名称
		private RelativeLayout rl_ring;// 声音试听的RelativeLayout
		private TextView iv_ring;// 声音图标

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (this.info==null?0:this.info.size());
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return info.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyView mv;
		if (map.get(position) == null) {
			mv = new MyView();
			convertView = lf.inflate(R.layout.listviewitem_alart_rings, null);

			mv.tv_ring = (TextView) convertView
					.findViewById(R.id.tv_listviewitem_rings);
			
			mv.tv_ringinfo = (TextView) convertView
			.findViewById(R.id.tv_listviewitem_ringsInfo);
//			mv.rl_ring = (RelativeLayout) convertView
//					.findViewById(R.id.rl_listitem_ring_listen);
			mv.iv_ring = (TextView) convertView
					.findViewById(R.id.iv_listviewitem_rings1);

			map.put(position, convertView);
			convertView.setTag(mv);
		} else {
			convertView = map.get(position);
			mv = (MyView) convertView.getTag();

		}
		String ring = info.get(position).get(AlartCommenSetTools.KEY_SONGNAME)
				.toString();
		
		String ringinfo = info.get(position).get(AlartCommenSetTools.KEY_SONGINFO)
				.toString();
		String songpath = info.get(position)
				.get(AlartCommenSetTools.KEY_SONGPATH).toString();
		mv.tv_ring.setText(ring);
		mv.tv_ringinfo.setText(ringinfo);

//		if (!mBusy) {
//			
//		}else{
//			if (isPlays[position]) {
//				mv.iv_ring.setBackgroundResource(R.drawable.awakemusic_play);
//			} else {
//				mv.iv_ring.setBackgroundResource(R.drawable.awakemusic_pause);
//			}
//			mv.iv_ring.setOnClickListener(new AlartSoundImageViewOnClickListener(
//					songpath, position));
//		}
		mv.iv_ring.setOnClickListener(new AlartSoundImageViewOnClickListener(
				songpath, position,mv.iv_ring));
//		mv.rl_ring.setOnTouchListener(new AlartSoundImageViewOnTouchListener(
//				songpath, position));
		return convertView;
	}
	private class AlartSoundImageViewOnClickListener implements OnClickListener {
		String ring;
		int index;
		boolean isMoveFlag=false;
		TextView rl;
		public AlartSoundImageViewOnClickListener(String songpath, int index,TextView rl) {
			this.ring = songpath;
			this.index = index;
			isMoveFlag=false;
			this.rl=rl;

		}



		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//防暴力点击
			if(currentListView.isEnabled()){
				
			
			PreventViolence.preventClick(currentListView, PreventViolence.LONG_TIME);//防暴力点击
			switch(v.getId()){
			case R.id.iv_listviewitem_rings1:
				
				ArrayList<String> content = new ArrayList<String>();
				ArrayList<PlayItemEntity> playItems = new ArrayList<PlayItemEntity>();
				PlayItemEntity playItemEntity = new PlayItemEntity(
						PlayItemEntity.TYPE_LOCAL, ring);
				playItems.add(playItemEntity);
				String urlsend = PlayOptions.setPlay(new String[]{info.get(index).get(AlartCommenSetTools.KEY_SONGNAME).toString()},playItems,
						PlayEntity.METHOD,false);
				content.add(urlsend);
				//preSongIndex = index;
				//isPlays = new boolean[info.size()];
				isPlays[index] = isPlay;
				TCPTools.sendTcp(content, Tools.Current_SocketIP,true);
				ChooseRingAdapter.this.notifyDataSetChanged();
				//this.rl.setBackgroundResource(R.drawable.sound_normal);
				break;
			}
			}
		}

	}

}
