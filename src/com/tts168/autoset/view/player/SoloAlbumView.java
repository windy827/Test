package com.tts168.autoset.view.player;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.adapter.player.SoloAlbumListViewAdapter;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.view.MyBaseActivityView;
/**
 * 个人专辑除去标题栏部分
 * @author 袁剑
 *
 */
public class SoloAlbumView extends MyBaseActivityView{

	ListView lv_info;
	TextView tv_album_name;
	ImageView iv_add;
	
	public SoloAlbumView(MyBaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.iv_activity_soloalbum_add:
			ToastTools.short_Toast(activity, "添加操作");
			break;
		}
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		tv_album_name=(TextView) activity.findViewById(R.id.tv_activity_soloalbum_title);
		iv_add=(ImageView) activity.findViewById(R.id.iv_activity_soloalbum_add);
		lv_info=(ListView) activity.findViewById(R.id.lv_activity_soloalbum);
		ArrayList<HashMap<String, Object>>info=new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> temp=new HashMap<String, Object>();
		temp.put(PlayerTools.PlayerKeys.KEY_ALBUM_NAME, "轻音乐");
		temp.put(PlayerTools.PlayerKeys.KEY_ALBUM_CREATETIME, "2014-10-09");
		info.add(temp);
		
		HashMap<String, Object> temp1=new HashMap<String, Object>();
		temp1.put(PlayerTools.PlayerKeys.KEY_ALBUM_NAME, "校园歌曲");
		temp1.put(PlayerTools.PlayerKeys.KEY_ALBUM_CREATETIME, "2015-02-03");
		info.add(temp1);
		tv_album_name.setText("个人专辑"+"【"+info.size()+"】");
		SoloAlbumListViewAdapter sa=new SoloAlbumListViewAdapter(activity,  info, tv_album_name);
		lv_info.setAdapter(sa);
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		iv_add.setOnClickListener(this);
	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub
		
	}

}
