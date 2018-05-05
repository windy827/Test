package com.tts168.autoset.activity.quickset;

import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.spec.IvParameterSpec;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hellojni.FSKOptions;
import com.example.hellojni.GetWave;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.RingerModelTools;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.playmusic.MyMediaPlayer;
import com.tts168.autoset.tools.quickset.WifiSetRemindTools;
import com.tts168.autoset.tools.viewtools.TextViewTools;
import com.tts168.autoset.view.MyWifiView;
import com.tts168.autoset.view.title.TitleView;
import com.yytx.samrtcloudsdk.tools.wifi.LSFSKSet;

/**
 * 发送声波界面
 * @author 袁剑
 *
 */

public class SendFSKActivity  extends MyBaseActivity implements OnClickListener{
	TitleView titleView;//标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE="发送声波";
	
	public static final String ActivityName="SendFSKActivity";
	private TextView tv_sendfsk1,tv_sendfsk2;
	private TextView tv_activity_fsksend_faild;//接收失败
	private Button bt_sendfsk;//发送FSK声波
	private Button bt_next;//进入联网等待
	
	private ImageView  anim_sound;
	private AnimationDrawable anim;
	Object ob;
	
	private ImageView iv_sending;
	private AnimationDrawable anim_sending;
	Object ob_sending;
	private Timer timer_send;
	private static final int MSG_BT_ENABLE=0x01;//按钮可用
	private static final int MSG_BT_UNABLE=0x02;//发送完后按钮不可用
	
	//点击发送按钮进行配置
		private String TEXT_WAITSENDING1="点击发送声波按钮";
		private String TEXT_WAITSENDING2="";
	//发送声波中
	private String TEXT_SENDING1="正在发送声波";
	private String TEXT_SENDING2="请稍候...";
	//发送声波完成
	private String TEXT_SEND_FINISH1="是否听到设备发出";
	private String TEXT_SEND_FINISH2="接收成功的提示？";
	
	
	public static final String TEXT_NEXT="下一步";
	public static final String TEXT_RECIEVE="听到\"接收成功\"";
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
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		tv_sendfsk1=(TextView) findViewById(R.id.tv_activity_fsksend1);
		tv_sendfsk2=(TextView) findViewById(R.id.tv_activity_fsksend2);
		tv_activity_fsksend_faild=(TextView) findViewById(R.id.tv_activity_fsksend_faild);
		bt_sendfsk=(Button) findViewById(R.id.bt_activity_fsksend_send_clean);
		bt_next=(Button) findViewById(R.id.bt_activity_fsksend_success);
		
		
		//正在发送声波动画
		iv_sending = (ImageView) findViewById(R.id.iv_anim_sending);
		ob_sending = iv_sending.getBackground();
		anim_sending = (AnimationDrawable) ob_sending;
		anim_sending.stop();//停止播放
		//播放动画
		anim_sound = (ImageView) findViewById(R.id.iv_anim_closetodevice);
		ob = anim_sound.getBackground();
		anim = (AnimationDrawable) ob;
		anim.stop();//停止播放
		anim.start();//开始播放
//		myplayer=new MyMediaPlayer(this, R.raw.close2device);
//		myplayer.start();
			
		
		timer_send=new Timer();
		
		bt_next.setVisibility(View.VISIBLE);
		bt_next.setText(TEXT_NEXT);
		
		tv_activity_fsksend_faild.setVisibility(View.GONE);
		iv_sending.setVisibility(View.GONE);
		TextViewTools.setTextUnderLine(tv_activity_fsksend_faild);//设置下划线
	}
	

	 Handler handler=new Handler(){

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch(msg.what){
			case MSG_BT_ENABLE:
//				myplayer.stop();
//				myplayer=new MyMediaPlayer(SendFSKActivity.this, R.raw.prepersend);
//				myplayer.start();
				tv_sendfsk1.setText(TEXT_WAITSENDING1);
				tv_sendfsk2.setText(TEXT_WAITSENDING2);
				TextViewTools.setTextColor(tv_sendfsk1, 2, 6, Color.RED);
				anim_sound.setVisibility(View.GONE);
				bt_sendfsk.setVisibility(View.VISIBLE);
			break;
			case R.id.tv_activity_fsksend_faild:
				//接收失败
				break;
			case MSG_BT_UNABLE:
				anim_sending.stop();//停止播放
				bt_sendfsk.setVisibility(View.VISIBLE);
				iv_sending.setVisibility(View.GONE);
				tv_sendfsk1.setText(TEXT_SEND_FINISH1);
				tv_sendfsk2.setText(TEXT_SEND_FINISH2);
				TextViewTools.setTextColor(tv_sendfsk2, 0, 4, Color.RED);
				
				tv_activity_fsksend_faild.setVisibility(View.VISIBLE);
				bt_next.setVisibility(View.VISIBLE);
				bt_next.setEnabled(true);
			break;
			}
		}
		
	};
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		LSFSKSet.getInstance(this).stopSend();
		timer_send.cancel();
		if(anim.isRunning()){
			anim.stop();
		}
		if(anim_sending.isRunning()){
			anim_sending.stop();
		}
	}
	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_sendfsk.setOnClickListener(this);
		tv_activity_fsksend_faild.setOnClickListener(this);
		bt_next.setOnClickListener(this);
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
		return R.layout.activity_fsksend;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//防暴力点击
				switch(v.getId()){
				case R.id.bt_activity_fsksend_send_clean:
					LSFSKSet.getInstance(this).stopSend();
					bt_sendfsk.setVisibility(View.GONE);
					iv_sending.setVisibility(View.VISIBLE);
					anim_sending.start();//开始播放
					
					tv_sendfsk1.setText(TEXT_SENDING1);
					tv_sendfsk2.setText(TEXT_SENDING2);
					//发送声波
					timer_send.schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Message msg=new Message();
							msg.what=MSG_BT_UNABLE;
							handler.sendMessage(msg);
						}
					}, LSFSKSet.getInstance(this).getPlayTime(MyWifiView.ssid , MyWifiView.password));
					try {
						boolean canSend=true;
						String alarm="为了保证手机能正常发声,";
//						RingerModelTools.SetRingAndVibrate(MyApplication.getInstance().getCur_Activity());
						final int half_voice=(int) ((MyApplication.getInstance().getAudioManager().
								getStreamMaxVolume(AudioManager.STREAM_MUSIC))*Tools.VOLUME_PERCENT);
						//Tools.CUR_VOL=Tools. mAudioManager.getStreamVolume(AudioManager.STREAM_DTMF);
						int curV=MyApplication.getInstance().getAudioManager().getStreamVolume(AudioManager.STREAM_MUSIC);
						int total=MyApplication.getInstance().getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC);
						if((curV*10)/total<4){
							MyApplication.getInstance().getAudioManager().setStreamVolume(AudioManager.STREAM_MUSIC, half_voice,0);//0代表不作任何处理，可使用AudioManager.FLAG_PLAY_...参数
						}
						if(RingerModelTools.isWiredHeadsetOn(MyApplication.getInstance().getCur_Activity())){
							canSend=false;
							alarm=alarm+"请将耳机拔出！";
						}
						if(canSend){
						
//							myplayer.stop();
							//final int max_voice=(int) ((MyApplication.getInstance().getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC))*0.6);
							
							//MyApplication.getInstance().getAudioManager().setStreamVolume(AudioManager.STREAM_MUSIC, max_voice,0);//0代表不作任何处理，可使用AudioManager.FLAG_PLAY_...参数
							LSFSKSet.getInstance(this).sendWifiSet(MyWifiView.ssid ,MyWifiView.password);
//							String send = MyWifiView.ssid + MyWifiView.password;
//							WifiSetRemindTools wrt=new WifiSetRemindTools(this);
//							wrt.sendWifiSet(this,send,MyWifiView.ispassword_remind,GetWave.FSK_ASR);
							
						}else{
							NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), alarm);
						}
					} catch (Exception e) {
						//打印出错信息
						e.printStackTrace();
						Log.d("WIFISETSENDERR","errmsg:"+ e.getMessage()+"*********");
					}
					
					break;
				case R.id.tv_activity_fsksend_faild:
					//没有听到提示
					ActivitySetting.startUnfinishedActivity(this, NoReciveMsgAlartActivity.class, null, false);
					break;
				case R.id.bt_activity_fsksend_success:
					if(bt_next.getText().toString().equals(TEXT_NEXT)){
						bt_next.setVisibility(View.GONE);
						bt_next.setText(TEXT_RECIEVE);
						Message msg=new Message();
						msg.what=MSG_BT_ENABLE;
						handler.sendMessage(msg);
					}else{
						//进入联网等待
						ActivitySetting.startUnfinishedActivity(this, ConnectWifiActiivity.class, null, false);
					}
					
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
