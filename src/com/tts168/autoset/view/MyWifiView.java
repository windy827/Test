package com.tts168.autoset.view;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.autoset.jni.http.productinfo.ProductInfoEntity;
import com.larkiv.larksmart7618.R;


import com.tts168.autoset.activity.quickset.SendFSKActivity;
import com.tts168.autoset.activity.quickset.WifiSetActivity;
import com.tts168.autoset.activity.quickset.WifiSetWaitingActivity;
import com.tts168.autoset.database.LarkSmartDataBaseConnection;
import com.tts168.autoset.database.GetOrUpdateWifi;

import com.tts168.autoset.tools.Tools;

import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.AlertUtils;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.RingerModelTools;
import com.tts168.autoset.tools.highcset.cityset.ProviceAndCityResource;
import com.tts168.autoset.tools.highcset.presskey.LaughContent;
import com.tts168.autoset.tools.others.TimeTools;
import com.tts168.autoset.tools.others.converopt.StrBinaryTurn;
import com.tts168.autoset.tools.others.converopt.StringConvertTools;
import com.tts168.autoset.tools.others.fileopt.WriteData;
import com.tts168.autoset.tools.others.wifi.NetManager;
import com.tts168.autoset.tools.others.wifi.WifiAPTools;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.tools.quickset.WifiSetRemindTools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker.OnDateChangedListener;

/**
 * 1.在附近没有热点的情况下还没有测试 2.wifi 名用线程实时更新没有做出来，达不到效果！！！
 * 
 * @author Administrator
 * 
 */
public class MyWifiView implements OnCheckedChangeListener {
	
	public static final String TITLE="WIFI配置"; 
	public static final int WIFI_OPEN = 0x00;
	public static final int WIFI_CLOSE = 0x01;
	
	//wifi密码的最大长度
	public static final int PASSWORD_MAX_LENGTH=16;
	// ------------Wifi---------------------
	public static ArrayList<HashMap<String, String>> al_SSID;
	public static boolean isNotifyDialogExit = false;
	public static boolean isSearching = true;
	
	
	public static final int Device_HasConnected = 0x02;// 得到手机设备与云宝已连接的信息
	
	/**
	 * 是否继续等待connect_Thread的连接查询
	 */
	public static boolean isConnect_waitting = false;
	public  WifiAdmin wa;
//	public static boolean isPrimary;// 记录用户设置的wifi是打开还是关闭
	String sb = "";
	/**
	 * 用于检测发送的内容是否有更改,暂不做改变，可改变
	 */
	public static String sendWifiData, sendFunctionData;
	// ------------隐藏或显示来发送与否（时间日期以及笑话键）------------------------------

	public  ToggleButton tb_test;// 控制功能配置发送的内容和界面需要显示的内容
	public static String sendData;// 将要发送的有效数据
	public static int Send_Flag;// 标记是否发送时间日期以及笑话键
	public static final int SEND_ALL = 0x30;// 发送时间日期
	public static final int SEND_PART = 0x31;// 不发送时间日期

	// ----------------------------------------------------------------------------------
	// ----------------------------
	public  View a;
	public   EditText et_password;
	public CheckBox cb_remindpassword;//记住当前密码
	public CheckBox cb_password_visiable;//当前密码是否可见
	

	public  Button bt_SSID;
	public  Button bt_gotoSet;//进入功能配置按钮
	public  Activity aa;
	public  Button bt_set, bt_submit,bt_listen;
	public static boolean ispassword_remind=true;//是否记住密码
	
	public static   String ssid;//wifi的用户名和密码
	public static String password;

	public  ProgressDialog mypDialog;
	
	
	//MediaPlayer mp;
	/**
	 * 控制Wifi
	 */
	public  Handler handler_wifi = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == WIFI_OPEN) {
				NotifyDialog.ChooseSSID(MyApplication.getInstance().getCur_Activity(), bt_SSID);

				// 如果用户原始设定的wifi 是关闭的，则关闭wifi
				// if(!isPrimary){
				// wa.CloseWifi();
				// }
			} else {
				
			}
		}

	};
	
	/**
	 * 设置密码
	 * @param text
	 */
	public void setPasswordContent(String text){
		if(text.length()>0){
			et_password.setText(text);
		}else{
			et_password.setHint("请输入密码，没有可不填");
		}
		
	}
	
	public  void getWifiView(final Activity a) {
		wa = new WifiAdmin(a);
		// 开启线程监听是否接收到IP
//		if(mp==null){
//			mp=MediaPlayer.create(a, R.raw.vol_try);
//			
//		}
		

		aa = a;
		mypDialog = new ProgressDialog(MyApplication.getInstance().getCur_Activity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// 设置进度条风格，风格为圆形，旋转的
		// mypDialog.setTitle("Google");
		// 设置ProgressDialog 标题
		mypDialog.setMessage("请稍候");
		
		// 设置ProgressDialog 的进度条是否不明确
		mypDialog.setCancelable(false);
		// 设置ProgressDialog 是否可以按退回按键取消

		bt_SSID = (Button) a.findViewById(R.id.bt_SSID1);
		
		et_password = (EditText) a.findViewById(R.id.editText_password);
		cb_remindpassword=(CheckBox) a.findViewById(R.id.cb_activity_wifiset_remindpassword);
		cb_password_visiable=(CheckBox) a.findViewById(R.id.cb_activity_wifiset_passwordvisiable);
		RefreshData();
		cb_remindpassword.setOnCheckedChangeListener(this);
		cb_password_visiable.setOnCheckedChangeListener(this);
		
		bt_submit = (Button) a.findViewById(R.id.button_login1);
		
		// 功能配置
		//bt_set = (Button) a.findViewById(R.id.button_set1);

		bt_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PreventViolence.preventClick(v, PreventViolence.LONG_TIME);
					boolean canSend=true;
					String alarm="为了保证手机能正常发声,";
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
					
					
//					if(RingerModelTools.getCurRingModel(MyApplication.getInstance().getCur_Activity())!=AudioManager.RINGER_MODE_NORMAL){
//						canSend=false;
//						alarm=alarm+"请将手机的情景模式设置为户外模式或标准模式;";
//					}
					if(RingerModelTools.isWiredHeadsetOn(MyApplication.getInstance().getCur_Activity())){
						canSend=false;
						alarm=alarm+"请将耳机拔出！";
					}
					if(canSend){
						try {
							ssid =StringConvertTools.getUTF8String(bt_SSID.getText().toString());
							password = StringConvertTools.getUTF8String(et_password.getText().toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// String gateway=et_gateway.getText().toString();
						if (ssid.equals("") ) {
							AlertUtils.SimpleAlert(MyApplication.getInstance().getCur_Activity(), "输入有误！",
									"SSID输入为空，请输入后再提交！");
						}

						else {
							
							new WifiSetRemindTools().sendWifiSet(MyApplication.getInstance().getCur_Activity(), cb_remindpassword.isChecked());
							ActivitySetting.startUnfinishedActivity(a, SendFSKActivity.class,null,false);
					
						}
					}else{
						NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), alarm);
					}
					
					

				
			}
		});
		
		bt_listen = (Button) a.findViewById(R.id.bt_activity_wifiview_listen);
		bt_listen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!PreventForceClick.isForceClick(500)) {

				}
			}
		});
		
	}

	

	

	/**
	 * 根据SSID得到对应的Wifi信息
	 */
	public static ArrayList<HashMap<String, Object>> getWifiSet_info(String SSID) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			list = GetOrUpdateWifi.getWifiSet_info(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase(), SSID);
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().endTransaction();
			if (LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase() != null) {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().close();
			}

		}
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().unlock();
		return list;
	}

	/**
	 * 刷新数据
	 */
	public  void RefreshData() {
		NetManager netManager = new NetManager(MyApplication.getInstance().getCur_Activity()); 				
		// 得到网络管理器
		boolean isenable=wa.mWifiManager.isWifiEnabled();
		
		if (netManager.isOpenNetwork()) {
			String ssid = WifiTool.getCurrentSSID(wa);
			/**
			 * 如果这个SSID在数据库里面有记录，则读取出来它的PassWord在MyWifiView.et_password上
			 */
			bt_SSID.setText(ssid);
			
			if (!ssid.equals("wifi")) {
				if(getWifiSet_info(ssid).size() > 0){
					et_password.setText((CharSequence) getWifiSet_info(
							ssid).get(0).get("PASSWORD"));
					/**
					 * 设置光标到字符串尾部
					 */
					et_password.setSelection(et_password.getText().length());
				}else{
					et_password.setText("");
					et_password.clearComposingText();
					et_password.setHint("请输入密码");
				}
				
			}else{
				//权限未开启提醒
				NotifyDialog.SimpleAlertDialog(aa, aa.getResources().getString(R.string.wifi_premission_alarm));
			}

		} else {
			if(WifiAPTools.isWifiApEnable(aa)){
				//如果手机是热点
				String sid=WifiAPTools.getWifiAPConfigration(aa).SSID;
				bt_SSID.setText(sid);
				String password="";
				if(getWifiSet_info(
						ssid).size()>0){
					password=(String) getWifiSet_info(
							ssid).get(0).get("PASSWORD");
				}
			
				et_password.setText((password) );
				/**
				 * 设置光标到字符串尾部
				 */
				et_password.setSelection(et_password.getText().length());
				
			}else{
				NotifyDialog.SimpleAlertDialog(aa, "您的Wifi开关已关闭，请打开Wifi后再刷新该界面！");
			}
			
		}

		bt_SSID.clearFocus();
		et_password.clearFocus();
		
//
//	
//		MyApplication.getInstance().getCur_Activity()UR_SEND_DATA = TimeTools.getNowStrDate();
//		MyApplication.getInstance().getCur_Activity()UR_SEND_TIME = TimeTools.getNowStrTime();
		
		
	}

	public  void setDataTime() {
		 Tools.Cur_DataY = Tools.OneChar2Double(data.getYear());
		 Tools.Cur_DataM = Tools.OneChar2Double(data.getMonth() + 1);
		 Tools.Cur_DataD = Tools.OneChar2Double(data.getDayOfMonth());
		 Tools.CUR_SEND_DATA = Tools.Cur_DataY + Tools.Cur_DataM
				+ Tools.Cur_DataD;

		textView.setText(TimeTools.getCurDate(Tools.CUR_SEND_DATA));

	}

	public  TextView textView;
	public  DatePicker data;

	

	public  class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			switch (v.getId()) {

		
			// ----------------------------------------------

			case R.id.rl_getinfoset:

				break;

			}

		}

	}

	
	
	
	
	/**
	 * 点击发送FSK按钮时弹出来的使用提示对话框
	 * 
	 * @param tv
	 */
	public static void getFSKRemindPopWin(View v) {
	
		final ImageView iv_anim;
		AnimationDrawable anim;
		LayoutInflater inflater = LayoutInflater.from(MyApplication.getInstance().getCur_Activity());
		// 引入窗口配置文件
		View view = inflater.inflate(R.layout.wifi_connection_dialog, null);
		// 创建PopupWindow对象
		final PopupWindow pop = new PopupWindow(view,
				Tools.getScreenWidth(MyApplication.getInstance().getCur_Activity()) * 3 / 4,
				Tools.getScreenHeight(MyApplication.getInstance().getCur_Activity()) / 3, true);
		iv_anim = (ImageView) view.findViewById(R.id.iv_wifi_connect);

		Object ob;
		//播放动画
		
		ob = iv_anim.getBackground();
		anim = (AnimationDrawable) ob;
		anim.stop();//停止播放
		anim.start();//开始播放


		// 需要设置一下此参数，点击外边可消失
		pop.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		pop.setOutsideTouchable(true);
		// 设置此参数获得焦点，否则无法点击
		pop.setFocusable(true);

		pop.showAtLocation(v, Gravity.CENTER, 0, 0);

	}





	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch(buttonView.getId()){
		case R.id.cb_activity_wifiset_remindpassword:
			//是否记住密码
			ispassword_remind=isChecked;
			break;
		case R.id.cb_activity_wifiset_passwordvisiable:
			//密码是否可见
			if(isChecked){
				et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
				/**
				 * 设置光标到字符串尾部
				 */
				et_password.setSelection(et_password.getText().length());
			}else{
				et_password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				/**
				 * 设置光标到字符串尾部
				 */
				et_password.setSelection(et_password.getText().length());
			}
			break;
		}
	}

}
