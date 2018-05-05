package com.tts168.autoset.activity.quickset;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.view.title.TitleView;

/**
 * FSK联网等待
 * 
 * @author 袁剑
 * 
 */
public class ConnectWifiActiivity extends MyBaseActivity implements
		OnClickListener {
	TitleView titleView;// 标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE = "连接设备";

	public static final String ActivityName = "ConnectWifiActiivity";
	private TextView tv_waittime;;
	
	private static final int WAITTIME=60;
	private int waiTimeIndex = WAITTIME;
	private Timer timer;
	private static final int MSG_WAITTIME = 0x01;// 显示等待时间
	private static final int MSG_NOTCONNECT = 0x02;// 等待超时

	private ImageView anim_sound;
	private AnimationDrawable anim;
	Object ob;

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

			refresh();
		}
	}

	/**
	 * 提供给外部的刷新接口
	 */
	public static void refresh() {

	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		titleView = new TitleView(this);
		titleView.setTitle(TITLE);
		// 播放动画
		anim_sound = (ImageView) findViewById(R.id.iv_anim_connectwifi);
		ob = anim_sound.getBackground();
		anim = (AnimationDrawable) ob;
		anim.start();// 开始播放
		tv_waittime = (TextView) findViewById(R.id.tv_activity_connectwifi_waittime);
		
		timer = new Timer();
		waiTimeIndex = WAITTIME;
		
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				waiTimeIndex--;
				Message msg = new Message();
				if (waiTimeIndex >= 0) {
					msg.what = MSG_WAITTIME;

				} else {
					msg.what = MSG_NOTCONNECT;
					if(anim.isRunning()){
						anim.stop();
					}
					timer.cancel();
				}
				handler.sendMessage(msg);
			}
		}, 1000,1000);

	}

	Handler handler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case MSG_WAITTIME:
				tv_waittime.setText("最多"+waiTimeIndex + "秒内完成连接");
				break;
			case MSG_NOTCONNECT:
				//NotifyDialog.notConnectAlertDialog(MyApplication.getInstance().getCur_Activity());
				ActivitySetting.startActivity(MyApplication.getInstance().getCur_Activity(), ConnectFailedActivity.class, null, true);
				break;
			
			}

		}
	};

	@Override
	protected void onDestroy() {
		if(anim.isRunning()){
			anim.stop();
		}
		
		timer.cancel();
		super.onDestroy();
	};

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
		return R.layout.activity_connectwifi;
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
		// 保存操作

	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub

	}

}
