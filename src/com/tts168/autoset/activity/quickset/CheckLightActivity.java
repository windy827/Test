package com.tts168.autoset.activity.quickset;

import android.app.Activity;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.RingerModelTools;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.playmusic.MyMediaPlayer;
import com.tts168.autoset.tools.viewtools.TextViewTools;
import com.tts168.autoset.view.title.TitleView;

/**
 * 检查WIFI灯是否闪烁
 * @author 袁剑
 *
 */

public class CheckLightActivity  extends MyBaseActivity implements OnClickListener{
	TitleView titleView;//标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE="检查设备";
	
	public static final String ActivityName="CheckLightActivity";
	private TextView tv_noflash;
	Button bt_fskset;//进入FSK配置
	Button bt_apset;//进入AP配置
//	MyMediaPlayer myplayer;
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
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
//	myplayer.release();
}
	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if(!Tools.CurrentActivityName.equals(getActivityName())){
			
			//MyApplication.asyncBitmapLoader.initExecutorService();[用到异步加载的地方需要使用]
			
			refresh();
		}
	}
	/**
	 * 提供给外部的刷新接口
	 */
		public static void refresh(){
		
		}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		RingerModelTools.SetRingAndVibrate(MyApplication.getInstance().getCur_Activity());
		final int half_voice=(int) ((MyApplication.getInstance().getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC))*Tools.DEFAULT_VOLUME_PERCENT);
		//Tools.CUR_VOL=Tools. mAudioManager.getStreamVolume(AudioManager.STREAM_DTMF);
		Log.d("StreamVolume", "当前音量为："+MyApplication.getInstance().getAudioManager().getStreamVolume(AudioManager.STREAM_MUSIC));
		Log.d("StreamVolume", "总音量为："+MyApplication.getInstance().getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		int curV=MyApplication.getInstance().getAudioManager().getStreamVolume(AudioManager.STREAM_MUSIC);
		int total=MyApplication.getInstance().getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		if((curV*10)/total<4){
			MyApplication.getInstance().getAudioManager().setStreamVolume(AudioManager.STREAM_MUSIC, half_voice,0);//0代表不作任何处理，可使用AudioManager.FLAG_PLAY_...参数
		}
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		tv_noflash=(TextView) findViewById(R.id.tv_checklight_noflash);
		TextViewTools.setTextUnderLine(tv_noflash);//设置下划线
		bt_fskset=(Button) findViewById(R.id.bt_checklight_FSK);
//		myplayer=new MyMediaPlayer(this, R.raw.checklight);
//		myplayer.start();
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_fskset.setOnClickListener(this);
		tv_noflash.setOnClickListener(this);
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
		return R.layout.activity_checklight;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//防暴力点击
				switch(v.getId()){
				case R.id.tv_checklight_noflash:
					//进入没有WIFI灯闪烁
//					myplayer.release();
					ActivitySetting.startUnfinishedActivity(this, NoLightFlashActivity.class, null, false);
					break;
				case R.id.bt_checklight_FSK:
					//进入FSK的配置
//					myplayer.release();
					ActivitySetting.startUnfinishedActivity(this, WifiSetActivity.class, null, false);
					break;
				}
		
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//保存操作
		 
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
