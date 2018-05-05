package com.tts168.autoset.activity.welcome;

import java.util.ArrayList;
import java.util.HashMap;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.http.configAndupgrade.ConfigAndUpgradeJsonOptions;
import com.autoset.jni.http.configAndupgrade.ConfigEntity;
import com.autoset.jni.http.configAndupgrade.UpgradeEntity;
import com.autoset.jni.sleepset.SleepSetEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.commen.VersionTools;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.others.wifi.NetManager;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;

import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

//软件入口，闪屏界面。
public class MainActivity extends MyBaseActivity {
	public static final String ActivityName="MainActivity";
	private boolean first;	//判断是否第一次打开软件
	private View view;
	private Context context;
	private Animation animation;
	private NetManager netManager;
	private SharedPreferences shared;
	private static final int TIME = 1000; 										// 进入主程序的延迟时间
	WifiAdmin wa;//Wifi操作类
	

	
	@Override
	protected void onResume() {
//		MyApplication.getInstance().setCur_Activity(this);
//		context=this;
		into();
		
		super.onResume();
	}

//	public void onPause() {
//		MyApplication.getInstance().setCur_Activity(this);
//		context=this;
//		super.onPause();
//	}
	// 进入主程序的方法
	private void into() {
		if (netManager.isOpenNetwork()&&netManager.isOpenWifi()) {
			//如果网络可用则打开TCP服务端
//			TCPTools.tcpThread=new TCPThread(this);
//			TCPTools.tcpThread.doListen();
			if(MyApplication.hasSdcard()){
				 String mac=wa.GetMacAddress();
				 String info=wa.GetBSSID();
				 shared.edit().putString(SharedConfig.KEY_WIFIBSSID, info).commit();
				 shared.edit().putString(SharedConfig.KEY_WIFI_MAC, mac).commit();
				// 如果网络可用则判断是否第一次进入，如果是第一次则进入欢迎界面
				first = shared.getBoolean("First", true);
				// 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
				animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
				// 给view设置动画效果
				view.startAnimation(animation);
				animation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation arg0) {
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
					}

					// 这里监听动画结束的动作，在动画结束的时候开启一个线程，这个线程中绑定一个Handler,并
					// 在这个Handler中调用goHome方法，而通过postDelayed方法使这个方法延迟500毫秒执行，达到
					// 达到持续显示第一屏1000毫秒的效果
					@Override
					public void onAnimationEnd(Animation arg0) {
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent intent;
								//如果第一次，则进入引导页WelcomeActivity
//								HttpThread httpThread=new HttpThread(context);
//								httpThread.getHttpListen();
								if (first) {
								/**
								 * 做第一次进入的处理
								 */
								} else {
								/**
								 * 非第一次进入的处理
								 */
									
								}
								intent = new Intent(MainActivity.this,SearchDevicesActivity.class);
								startActivity(intent);
								// 设置Activity的切换效果
								overridePendingTransition(R.anim.in_from_right,
										R.anim.out_to_left);
								MainActivity.this.finish();
							}
						}, TIME);
					}
				});
			}else{
				ToastTools.long_Toast(this, "未检测到存储卡，请插入存储卡...");
				this.finish();
			}
			
		} else {
			// 如果网络不可用，则弹出对话框，对网络进行设置
			ActivitySetting.startActivity(this, NetUnableActivity.class, null,true);

		}
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
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		view = View.inflate(this, R.layout.activity_loading, null);
		setContentView(view);
		context = this;		
		MyApplication.getInstance().setCur_Activity(this);//得到上下文
		Tools.isAutoUpdateAlarm=true;
		TerminalTools.adapter_infoByIP=new ArrayList<TerminalAdapterEntity>();
		Tools.Current_SocketIP="";
		Tools.generalEntity=new GeneralEntity("", "", "", "", 1, 1, "", "", 0, "", "",false);
		int code=VersionTools.getVersionCodeByVersionName(MyApplication.getInstance().getCur_Activity());
		
		Tools.Current_DeviceName="";
		wa = new WifiAdmin(this);
		shared = new SharedConfig(context).GetConfig(); 	// 得到配置文件
		netManager = new NetManager(context); 				// 得到网络管理器
		MyApplication.initImageLoader(MyApplication.getInstance().getCur_Activity());
		Network.getInstance((Activity)MyApplication.getInstance().getCur_Activity());
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
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}

	@Override
	public int getContentViewID() {
		// TODO Auto-generated method stub
		return  R.layout.activity_loading;
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		
	}
}
