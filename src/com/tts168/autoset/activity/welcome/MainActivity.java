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

//�����ڣ��������档
public class MainActivity extends MyBaseActivity {
	public static final String ActivityName="MainActivity";
	private boolean first;	//�ж��Ƿ��һ�δ����
	private View view;
	private Context context;
	private Animation animation;
	private NetManager netManager;
	private SharedPreferences shared;
	private static final int TIME = 1000; 										// ������������ӳ�ʱ��
	WifiAdmin wa;//Wifi������
	

	
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
	// ����������ķ���
	private void into() {
		if (netManager.isOpenNetwork()&&netManager.isOpenWifi()) {
			//�������������TCP�����
//			TCPTools.tcpThread=new TCPThread(this);
//			TCPTools.tcpThread.doListen();
			if(MyApplication.hasSdcard()){
				 String mac=wa.GetMacAddress();
				 String info=wa.GetBSSID();
				 shared.edit().putString(SharedConfig.KEY_WIFIBSSID, info).commit();
				 shared.edit().putString(SharedConfig.KEY_WIFI_MAC, mac).commit();
				// �������������ж��Ƿ��һ�ν��룬����ǵ�һ������뻶ӭ����
				first = shared.getBoolean("First", true);
				// ���ö���Ч����alpha����animĿ¼�µ�alpha.xml�ļ��ж��嶯��Ч��
				animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
				// ��view���ö���Ч��
				view.startAnimation(animation);
				animation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation arg0) {
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
					}

					// ����������������Ķ������ڶ���������ʱ����һ���̣߳�����߳��а�һ��Handler,��
					// �����Handler�е���goHome��������ͨ��postDelayed����ʹ��������ӳ�500����ִ�У��ﵽ
					// �ﵽ������ʾ��һ��1000�����Ч��
					@Override
					public void onAnimationEnd(Animation arg0) {
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent intent;
								//�����һ�Σ����������ҳWelcomeActivity
//								HttpThread httpThread=new HttpThread(context);
//								httpThread.getHttpListen();
								if (first) {
								/**
								 * ����һ�ν���Ĵ���
								 */
								} else {
								/**
								 * �ǵ�һ�ν���Ĵ���
								 */
									
								}
								intent = new Intent(MainActivity.this,SearchDevicesActivity.class);
								startActivity(intent);
								// ����Activity���л�Ч��
								overridePendingTransition(R.anim.in_from_right,
										R.anim.out_to_left);
								MainActivity.this.finish();
							}
						}, TIME);
					}
				});
			}else{
				ToastTools.long_Toast(this, "δ��⵽�洢���������洢��...");
				this.finish();
			}
			
		} else {
			// ������粻���ã��򵯳��Ի��򣬶������������
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
		MyApplication.getInstance().setCur_Activity(this);//�õ�������
		Tools.isAutoUpdateAlarm=true;
		TerminalTools.adapter_infoByIP=new ArrayList<TerminalAdapterEntity>();
		Tools.Current_SocketIP="";
		Tools.generalEntity=new GeneralEntity("", "", "", "", 1, 1, "", "", 0, "", "",false);
		int code=VersionTools.getVersionCodeByVersionName(MyApplication.getInstance().getCur_Activity());
		
		Tools.Current_DeviceName="";
		wa = new WifiAdmin(this);
		shared = new SharedConfig(context).GetConfig(); 	// �õ������ļ�
		netManager = new NetManager(context); 				// �õ����������
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
