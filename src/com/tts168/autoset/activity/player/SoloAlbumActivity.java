package com.tts168.autoset.activity.player;

import android.app.Activity;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.view.player.SoloAlbumView;
import com.tts168.autoset.view.title.TitleView;

/**
 * ����ר��
 * @author Ԭ��
 *
 */
public class SoloAlbumActivity extends MyBaseActivity{

	public static final String TITLE="����ר��";
	TitleView tv;//������
	SoloAlbumView sav;//��ȥ������������View����
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
