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
 * 1.�ڸ���û���ȵ������»�û�в��� 2.wifi �����߳�ʵʱ����û�����������ﲻ��Ч��������
 * 
 * @author Administrator
 * 
 */
public class MyWifiView implements OnCheckedChangeListener {
	
	public static final String TITLE="WIFI����"; 
	public static final int WIFI_OPEN = 0x00;
	public static final int WIFI_CLOSE = 0x01;
	
	//wifi�������󳤶�
	public static final int PASSWORD_MAX_LENGTH=16;
	// ------------Wifi---------------------
	public static ArrayList<HashMap<String, String>> al_SSID;
	public static boolean isNotifyDialogExit = false;
	public static boolean isSearching = true;
	
	
	public static final int Device_HasConnected = 0x02;// �õ��ֻ��豸���Ʊ������ӵ���Ϣ
	
	/**
	 * �Ƿ�����ȴ�connect_Thread�����Ӳ�ѯ
	 */
	public static boolean isConnect_waitting = false;
	public  WifiAdmin wa;
//	public static boolean isPrimary;// ��¼�û����õ�wifi�Ǵ򿪻��ǹر�
	String sb = "";
	/**
	 * ���ڼ�ⷢ�͵������Ƿ��и���,�ݲ����ı䣬�ɸı�
	 */
	public static String sendWifiData, sendFunctionData;
	// ------------���ػ���ʾ���������ʱ�������Լ�Ц������------------------------------

	public  ToggleButton tb_test;// ���ƹ������÷��͵����ݺͽ�����Ҫ��ʾ������
	public static String sendData;// ��Ҫ���͵���Ч����
	public static int Send_Flag;// ����Ƿ���ʱ�������Լ�Ц����
	public static final int SEND_ALL = 0x30;// ����ʱ������
	public static final int SEND_PART = 0x31;// ������ʱ������

	// ----------------------------------------------------------------------------------
	// ----------------------------
	public  View a;
	public   EditText et_password;
	public CheckBox cb_remindpassword;//��ס��ǰ����
	public CheckBox cb_password_visiable;//��ǰ�����Ƿ�ɼ�
	

	public  Button bt_SSID;
	public  Button bt_gotoSet;//���빦�����ð�ť
	public  Activity aa;
	public  Button bt_set, bt_submit,bt_listen;
	public static boolean ispassword_remind=true;//�Ƿ��ס����
	
	public static   String ssid;//wifi���û���������
	public static String password;

	public  ProgressDialog mypDialog;
	
	
	//MediaPlayer mp;
	/**
	 * ����Wifi
	 */
	public  Handler handler_wifi = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == WIFI_OPEN) {
				NotifyDialog.ChooseSSID(MyApplication.getInstance().getCur_Activity(), bt_SSID);

				// ����û�ԭʼ�趨��wifi �ǹرյģ���ر�wifi
				// if(!isPrimary){
				// wa.CloseWifi();
				// }
			} else {
				
			}
		}

	};
	
	/**
	 * ��������
	 * @param text
	 */
	public void setPasswordContent(String text){
		if(text.length()>0){
			et_password.setText(text);
		}else{
			et_password.setHint("���������룬û�пɲ���");
		}
		
	}
	
	public  void getWifiView(final Activity a) {
		wa = new WifiAdmin(a);
		// �����̼߳����Ƿ���յ�IP
//		if(mp==null){
//			mp=MediaPlayer.create(a, R.raw.vol_try);
//			
//		}
		

		aa = a;
		mypDialog = new ProgressDialog(MyApplication.getInstance().getCur_Activity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// ���ý�������񣬷��ΪԲ�Σ���ת��
		// mypDialog.setTitle("Google");
		// ����ProgressDialog ����
		mypDialog.setMessage("���Ժ�");
		
		// ����ProgressDialog �Ľ������Ƿ���ȷ
		mypDialog.setCancelable(false);
		// ����ProgressDialog �Ƿ���԰��˻ذ���ȡ��

		bt_SSID = (Button) a.findViewById(R.id.bt_SSID1);
		
		et_password = (EditText) a.findViewById(R.id.editText_password);
		cb_remindpassword=(CheckBox) a.findViewById(R.id.cb_activity_wifiset_remindpassword);
		cb_password_visiable=(CheckBox) a.findViewById(R.id.cb_activity_wifiset_passwordvisiable);
		RefreshData();
		cb_remindpassword.setOnCheckedChangeListener(this);
		cb_password_visiable.setOnCheckedChangeListener(this);
		
		bt_submit = (Button) a.findViewById(R.id.button_login1);
		
		// ��������
		//bt_set = (Button) a.findViewById(R.id.button_set1);

		bt_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PreventViolence.preventClick(v, PreventViolence.LONG_TIME);
					boolean canSend=true;
					String alarm="Ϊ�˱�֤�ֻ�����������,";
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
					
					
//					if(RingerModelTools.getCurRingModel(MyApplication.getInstance().getCur_Activity())!=AudioManager.RINGER_MODE_NORMAL){
//						canSend=false;
//						alarm=alarm+"�뽫�ֻ����龰ģʽ����Ϊ����ģʽ���׼ģʽ;";
//					}
					if(RingerModelTools.isWiredHeadsetOn(MyApplication.getInstance().getCur_Activity())){
						canSend=false;
						alarm=alarm+"�뽫�����γ���";
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
							AlertUtils.SimpleAlert(MyApplication.getInstance().getCur_Activity(), "��������",
									"SSID����Ϊ�գ�����������ύ��");
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
	 * ����SSID�õ���Ӧ��Wifi��Ϣ
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
	 * ˢ������
	 */
	public  void RefreshData() {
		NetManager netManager = new NetManager(MyApplication.getInstance().getCur_Activity()); 				
		// �õ����������
		boolean isenable=wa.mWifiManager.isWifiEnabled();
		
		if (netManager.isOpenNetwork()) {
			String ssid = WifiTool.getCurrentSSID(wa);
			/**
			 * ������SSID�����ݿ������м�¼�����ȡ��������PassWord��MyWifiView.et_password��
			 */
			bt_SSID.setText(ssid);
			
			if (!ssid.equals("wifi")) {
				if(getWifiSet_info(ssid).size() > 0){
					et_password.setText((CharSequence) getWifiSet_info(
							ssid).get(0).get("PASSWORD"));
					/**
					 * ���ù�굽�ַ���β��
					 */
					et_password.setSelection(et_password.getText().length());
				}else{
					et_password.setText("");
					et_password.clearComposingText();
					et_password.setHint("����������");
				}
				
			}else{
				//Ȩ��δ��������
				NotifyDialog.SimpleAlertDialog(aa, aa.getResources().getString(R.string.wifi_premission_alarm));
			}

		} else {
			if(WifiAPTools.isWifiApEnable(aa)){
				//����ֻ����ȵ�
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
				 * ���ù�굽�ַ���β��
				 */
				et_password.setSelection(et_password.getText().length());
				
			}else{
				NotifyDialog.SimpleAlertDialog(aa, "����Wifi�����ѹرգ����Wifi����ˢ�¸ý��棡");
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
	 * �������FSK��ťʱ��������ʹ����ʾ�Ի���
	 * 
	 * @param tv
	 */
	public static void getFSKRemindPopWin(View v) {
	
		final ImageView iv_anim;
		AnimationDrawable anim;
		LayoutInflater inflater = LayoutInflater.from(MyApplication.getInstance().getCur_Activity());
		// ���봰�������ļ�
		View view = inflater.inflate(R.layout.wifi_connection_dialog, null);
		// ����PopupWindow����
		final PopupWindow pop = new PopupWindow(view,
				Tools.getScreenWidth(MyApplication.getInstance().getCur_Activity()) * 3 / 4,
				Tools.getScreenHeight(MyApplication.getInstance().getCur_Activity()) / 3, true);
		iv_anim = (ImageView) view.findViewById(R.id.iv_wifi_connect);

		Object ob;
		//���Ŷ���
		
		ob = iv_anim.getBackground();
		anim = (AnimationDrawable) ob;
		anim.stop();//ֹͣ����
		anim.start();//��ʼ����


		// ��Ҫ����һ�´˲����������߿���ʧ
		pop.setBackgroundDrawable(new BitmapDrawable());
		// ���õ��������ߴ�����ʧ
		pop.setOutsideTouchable(true);
		// ���ô˲�����ý��㣬�����޷����
		pop.setFocusable(true);

		pop.showAtLocation(v, Gravity.CENTER, 0, 0);

	}





	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch(buttonView.getId()){
		case R.id.cb_activity_wifiset_remindpassword:
			//�Ƿ��ס����
			ispassword_remind=isChecked;
			break;
		case R.id.cb_activity_wifiset_passwordvisiable:
			//�����Ƿ�ɼ�
			if(isChecked){
				et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
				/**
				 * ���ù�굽�ַ���β��
				 */
				et_password.setSelection(et_password.getText().length());
			}else{
				et_password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				/**
				 * ���ù�굽�ַ���β��
				 */
				et_password.setSelection(et_password.getText().length());
			}
			break;
		}
	}

}
