package com.tts168.autoset.activity.localmusic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.http.AudioEntity;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.adapter.localmusic.MusicListViewAdapter;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MemInfo;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.localmusic.HttpServer;
import com.tts168.autoset.tools.localmusic.LocalMusicTools;
import com.tts168.autoset.tools.localmusic.MusicLoader;
import com.tts168.autoset.tools.localmusic.MusicLoader.MusicInfo;
import com.tts168.autoset.tools.localmusic.SearchFileThread;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.tools.webmusic.WebMusicTools;

import com.tts168.autoset.view.localmusic.MusicSortListDialog;
import com.tts168.autoset.view.player.PlayerView;
import com.tts168.autoset.view.title.TitleView;

public class LocalMusicActivity extends MyBaseActivity implements
		OnClickListener {
	TitleView titleView;// 标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE = "手机音乐";

	public static final String ActivityName = "LocalMusicActivity";
	public static final int MSG_WHAT_UPDATE = 0x01;

	public  MusicListViewAdapter adapter;
	/**
	 * 音乐播放器小插件
	 */
	public PlayerView pv;
	public MusicSortListDialog msDialog;// 本地音乐选择
	int size = 0;
	
	ArrayList<MusicInfo>adpter_info;
	public static final int FLAG_MUSIC_LOCAL=0x02;//本地音乐
	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}

	@Override
	public void rightToLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if (!Tools.CurrentActivityName.equals(getActivityName())) {

			// MyApplication.asyncBitmapLoader.initExecutorService();[用到异步加载的地方需要使用]

			
			pv.setPlayerContent(Tools.answerHelperEntity);
			refresh();
		}
	}


	/**
	 * 提供给外部的刷新接口
	 */
	public  void refresh() {
		if (PlayerTools.PlayerViewTools.current_index >= 0) {
			if(PlayerTools.focusNeedChange){
				msDialog.adapter.SetFocus(PlayerTools.PlayerViewTools.current_index);// 设置焦点
				msDialog.sortListView.setSelection(PlayerTools.PlayerViewTools.current_index);
			}
			
		}
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
//		if(PlayerTools.FLAG_MUSIC!=FLAG_MUSIC_LOCAL){
//			PlayerTools.PlayerViewTools.current_index = -1;
//		}
		
		titleView = new TitleView(this);
		
		titleView.setSaveText(Tools.TITLE_REFRESH);
		titleView.setTitle(TITLE);
		adpter_info = new ArrayList<MusicInfo>();
		// 创建音乐播放列表界面
		pv = new PlayerView((MyBaseActivity) MyApplication.getInstance().getCur_Activity());
		pv.setPlayerContent(Tools.answerHelperEntity);
		MusicLoader loader=MusicLoader.instance(getContentResolver());
		
		adpter_info=(ArrayList<MusicInfo>) loader.getMusicList();
		msDialog = new MusicSortListDialog(this, adpter_info);
		msDialog.getView();

		refresh();
	}

	
	

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getContentViewID() {
		// TODO Auto-generated method stub
		return R.layout.activity_localmusic;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);// 防暴力点击
		switch (v.getId()) {

		}

	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		MusicLoader loader=MusicLoader.instance(getContentResolver());
		
		adpter_info=(ArrayList<MusicInfo>) loader.getMusicList();
		
		msDialog.getView();

		refresh();
	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		super.onBackPressed();

	}
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// // TODO Auto-generated method stub
	// String path=(String) SearchFileThread.al_info.get(position).get("path");
	// String url=HttpServer.getInstance(instatance).generateURL(path);
	// LocalMusicTools.playLocalMusic(Tools.Current_SocketIP, url);
	// }
}
