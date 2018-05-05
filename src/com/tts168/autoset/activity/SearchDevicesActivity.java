package com.tts168.autoset.activity;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.udp.SearchJsonOption;
import com.autoset.jni.udp.SearchSendEntity;
import com.autoset.jni.udp.UDPNetEntity;
import com.autoset.jni.udp.UDPNetOption;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;
import com.example.hellojni.DBOption;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.activity.quickset.WifiSetActivity;
import com.tts168.autoset.tools.FuncSwitch;
import com.tts168.autoset.tools.SendDataTools;
import com.tts168.autoset.tools.Tools;

import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.highcset.individuation.IndividuationTools;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.others.NetWorkUtils;
import com.tts168.autoset.tools.others.wifi.WifiAPTools;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.tcpAndudp.UDPSendContent;
import com.tts168.autoset.tools.tcpAndudp.UDPTools;
import com.tts168.autoset.tools.tcpAndudp.WifiStateWatchThread;
import com.tts168.autoset.tools.test.UdpAndTcpTest;
import com.tts168.autoset.view.MyWifiView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ���ؽ���
 * �����������Ҫ�����������ȷ���UDP�㲥��Ȼ��ȴ����գ�
 * ���ʮ���ò����ظ������������Ʊ��豸���Զ�����Wifi FSK���ý��棩��
 * ����ܹ������������TCP���ӽ������ݵĳ�ʼ��Ȼ���ٽ���ϵͳ���ý���
 * @author Ԭ��
 *
 */
public class SearchDevicesActivity extends MyBaseActivity implements OnClickListener{
	public WifiAdmin wa;
	public static boolean isSearching=true;
	public static String ActivityName = "SearchDevicesActivity";
	 
	//-----------------------��¼�Ƿ������ת ----------------------------------------------
	public static final int CanSkip=0x00;
	public static final int CanNotSkip=0x01;
	//--------------------------------------------------------------------------------------------
	
	/**
	 * �ȴ���ʱ������λΪ��
	 */
	public static  int WAITTING_TIME=9;
	public static int index=5;
	public TextView tv_timeCount;//��ʾ��Ҫ�ȴ���ʱ��
	
	public ImageView  iv_search;
	AnimationDrawable anim;
	Object ob;

	TextView tv_search_result;//�������
	Button bt_search_end;//��������
	static String deviceNames=" ";//��¼�����ϵ��豸������

	/**
	 * �����Ƿ����WifiSetActivity���Ѿ���Wifi���ߴ򲻿�wifi 5��� ���룩
	 */
	 public  Handler handler_wifi = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				//super.handleMessage(msg);
				switch(msg.what){
				case CanSkip:
					
					if(TerminalTools.adapter_infoByIP==null||TerminalTools.adapter_infoByIP.size()==0
							){
						//û���ݽ���FSK����
						
							
							ActivitySetting.startActivity(((MyBaseActivity)MyApplication.getInstance().getCur_Activity()), NotConnectedActivity.class, null,true);
						
						
					}
					else{
						//�����ݽ��빦������
							
							//��ǰ�ж���豸���ӡ����ܶ�����Ч��Socket����TerminalActivity�鿴���ص�Gernal��Ϣû�о���ʾ����ʧЧ��
							ActivitySetting.startActivity(((Activity)MyApplication.getInstance().getCur_Activity()), TerminalActivity.class, null,true);
				
							
						
						
						
					}
					break;
				case CanNotSkip:
					/**
					 * ������ʱ
					 */
					tv_timeCount.setText(msg.arg1+"");
					if(TerminalTools.adapter_infoByIP!=null&&TerminalTools.adapter_infoByIP.size()>0){
						deviceNames="";
						for(int i=0;i<TerminalTools.adapter_infoByIP.size();i++){
							String name=(String) TerminalTools.adapter_infoByIP.get(i).getGeneralEntity().getNickname();
							deviceNames=deviceNames+"\r\n"+name;
						}
						
					}
					tv_search_result.setText(deviceNames);
					break;
				}			
				

			}

		};
private void init() {
	// TODO Auto-generated method stub
	/**
	 * ��FuncSwitch.SWITCH_TCPUDP_CONNECTTEST���ش�ʱ�����������
	 */
	if(UdpAndTcpTest.instance==null&&FuncSwitch.SWITCH_TCPUDP_CONNECTTEST){
		Log.d("TEST_SWITCH","TCPUDP�����ѿ���������");
		UdpAndTcpTest.getInstance(this).startTimer();
	}
	if(TerminalTools.adapter_infoByIP!=null&&TerminalTools.adapter_infoByIP.size()>0){
		deviceNames="";
		for(int i=0;i<TerminalTools.adapter_infoByIP.size();i++){
			String name=(String) TerminalTools.adapter_infoByIP.get(i).getGeneralEntity().getNickname();
			deviceNames=deviceNames+"\r\n"+name;
		}
		
	}
	
	bt_search_end.setOnClickListener(this);
	/**
	 * ���ز���������ͷ��β
	 */
	Tools.setHeadAndTail_Wave();
	wa=new WifiAdmin(this);
//	WifiStateWatchThread thread=new WifiStateWatchThread(this);//����WIFI����
//	thread.doListen();
	getUDPreciver();
	//�����ն��б����ӵļ���
	//Tools.al_Device_Info=new ArrayList<HashMap<String,Object>>();
	
	
	if(WifiAPTools.isWifiApEnable(this)){
		//����ֻ����ȵ�����Ҫ����Wifi
		
	}else{
		//����ֻ������ȵ�����Ҫ����Wifi
		wa.OpenWifi();
	}

	new Thread(new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();
			isSearching=true;
			index=WAITTING_TIME;
			
			while (isSearching){
				Log.d("ISALIVE","isSearching ALIVEING>>>");
				List<ScanResult> wifilist=wa.GetWifiList();
				Message msg=new Message();
				 index--;
				 Log.d("TIMEINDEX","*&*&*&*&"+index);
				 //����5��Ͳ���������wifi�ˣ�Ҳ�ý������ý���
				 if(index<=0){
//					 Tools.isRec=false;
//					Tools.isSend=false;
					isSearching=false;
					 msg.what=CanSkip; 
				 }else{
					
					 if(wa.mWifiManager.isWifiEnabled()||WifiAPTools.isWifiApEnable((Activity)MyApplication.getInstance().getCur_Activity())){
						 //���wifi���û����ֻ����ȵ�����Բ��ü����ȴ�wifi����
		                   // msg.what=WIFI_OPEN;
		                    
		                    msg.what=CanNotSkip;
		                    try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              																																																																																																																																																																																																																																																																																																																																												e.printStackTrace();
							}
						}else{
							 msg.what=CanNotSkip;
//							 Tools.isRec=false;
//							Tools.isSend=false;
							 try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
				 }
				 
				msg.arg1=index;
				
				handler_wifi.sendMessage(msg);
				
			}
		} 
		
	} ).start();

}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
		/**
		 * �൱�ڰ�Home��
		 */
		Intent i= new Intent(Intent.ACTION_MAIN); 
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		i.addCategory(Intent.CATEGORY_HOME); 
		startActivity(i);  
}
/**
 * �õ�UDP������
 */

public static void getUDPreciver() {
	Network.getInstance((Activity)MyApplication.getInstance().getCur_Activity());
	/**
	 * �ȶ����ݽ��г�ʼ�������ǵ�һ�ν���APP���ʹ�õ����
	 */
	Tools.Rec_Device_name="";
	Tools.Rec_IP="";
	Tools.Rec_Port=0;
	
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if (PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)) {
		if (PreventForceClick.isShowToast) {
			PreventForceClick.isShowToast = false;
		}

	} else {
		PreventForceClick.isShowToast = true;

	switch(v.getId()){
	case R.id.bt_activity_search_devices_end:
		//��������
		index=0;
		break;
	}
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
protected void getDataRefresh() {
	// TODO Auto-generated method stub
	
}
@Override
public void staticFindView() {
	// TODO Auto-generated method stub
	//------����������������������Ҫ�����е�socket����general���������ѯ

	Network net = Network.getInstance(this);
	if (net != null) {
		net.refreshDevice();
	}
	
	//------------------------------------------------------------------------------------
	
	
	index=WAITTING_TIME;
	//����UDP�ķ���ʱ����
	UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_SEARCH;
	if (net != null) {
		net.setUdpFrequency(UDPTools.Time_Send * 1000);
	}
	Tools.CurrentActivityName="SearchDevicesActivity";
	tv_timeCount=(TextView) findViewById(R.id.tv_activity_searchdevices);
	tv_timeCount.setText(index+"");
	//���Ŷ���
	iv_search = (ImageView) findViewById(R.id.iv_search);
	ob = iv_search.getBackground();
	anim = (AnimationDrawable) ob;
	anim.stop();//ֹͣ����
	anim.start();//��ʼ����

	tv_search_result=(TextView) findViewById(R.id.tv_activity_searchdevices_result);
	bt_search_end=(Button) findViewById(R.id.bt_activity_search_devices_end);
	deviceNames="";
	tv_search_result.setText(deviceNames);
	init();
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
	return R.layout.activity_search_devices;
}
@Override
public void rightViewOption() {
	// TODO Auto-generated method stub
	
}
@Override
protected void applyScrollListener() {
	// TODO Auto-generated method stub
	
}

@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
}

}
