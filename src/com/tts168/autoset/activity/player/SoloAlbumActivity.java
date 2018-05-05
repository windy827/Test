package com.tts168.autoset.activity.player;

import android.app.Activity;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.view.player.SoloAlbumView;
import com.tts168.autoset.view.title.TitleView;

/**
 * 个人专辑
 * @author 袁剑
 *
 */
public class SoloAlbumActivity extends MyBaseActivity{

	public static final String TITLE="个人专辑";
	TitleView tv;//标题栏
	SoloAlbumView sav;//出去标题栏的其他View部分
	public static final String ActivityName="SoloAlbumActivity";
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
		
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		tv=new TitleView((Activity)MyApplication.getInstance().getCur_Activity());
		tv.setTitle(TITLE);
		sav=new SoloAlbumView((MyBaseActivity)MyApplication.getInstance().getCur_Activity());
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
		return R.layout.activity_soloalbum;
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
