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
 * ���WIFI���Ƿ���˸
 * @author Ԭ��
 *
 */

public class CheckLightActivity  extends MyBaseActivity implements OnClickListener{
	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE="����豸";
	
	public static final String ActivityName="CheckLightActivity";
	private TextView tv_noflash;
	Button bt_fskset;//����FSK����
	Button bt_apset;//����AP����
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
			
			//MyApplication.asyncBitmapLoader.initExecutorService();[�õ��첽���صĵط���Ҫʹ��]
			
			refresh();
		}
	}
	/**
	 * �ṩ���ⲿ��ˢ�½ӿ�
	 */
		public static void refresh(){
		
		}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		RingerModelTools.SetRingAndVibrate(MyApplication.getInstance().getCur_Activity());
		final int half_voice=(int) ((MyApplication.getInstance().getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC))*Tools.DEFAULT_VOLUME_PERCENT);
		//Tools.CUR_VOL=Tools. mAudioManager.getStreamVolume(AudioManager.STREAM_DTMF);
		Log.d("StreamVolume", "��ǰ����Ϊ��"+MyApplication.getInstance().getAudioManager().getStreamVolume(AudioManager.STREAM_MUSIC));
		Log.d("StreamVolume", "������Ϊ��"+MyApplication.getInstance().getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		int curV=MyApplication.getInstance().getAudioManager().getStreamVolume(AudioManager.STREAM_MUSIC);
		int total=MyApplication.getInstance().getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		if((curV*10)/total<4){
			MyApplication.getInstance().getAudioManager().setStreamVolume(AudioManager.STREAM_MUSIC, half_voice,0);//0�������κδ�����ʹ��AudioManager.FLAG_PLAY_...����
		}
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		tv_noflash=(TextView) findViewById(R.id.tv_checklight_noflash);
		TextViewTools.setTextUnderLine(tv_noflash);//�����»���
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
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//���������
				switch(v.getId()){
				case R.id.tv_checklight_noflash:
					//����û��WIFI����˸
//					myplayer.release();
					ActivitySetting.startUnfinishedActivity(this, NoLightFlashActivity.class, null, false);
					break;
				case R.id.bt_checklight_FSK:
					//����FSK������
//					myplayer.release();
					ActivitySetting.startUnfinishedActivity(this, WifiSetActivity.class, null, false);
					break;
				}
		
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//�������
		 
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
