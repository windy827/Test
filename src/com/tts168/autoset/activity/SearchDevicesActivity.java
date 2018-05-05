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
 * 加载界面
 * 在这个界面需要做的事情是先发送UDP广播，然后等待接收，
 * 如果十秒后得不到回复（即连不上云宝设备则自动进入Wifi FSK配置界面）；
 * 如果能够连接上则进行TCP连接进行数据的初始化然后再进入系统配置界面
 * @author 袁剑
 *
 */
public class SearchDevicesActivity extends MyBaseActivity implements OnClickListener{
	public WifiAdmin wa;
	public static boolean isSearching=true;
	public static String ActivityName = "SearchDevicesActivity";
	 
	//-----------------------记录是否可以跳转 ----------------------------------------------
	public static final int CanSkip=0x00;
	public static final int CanNotSkip=0x01;
	//--------------------------------------------------------------------------------------------
	
	/**
	 * 等待的时长，单位为秒
	 */
	public static  int WAITTING_TIME=9;
	public static int index=5;
	public TextView tv_timeCount;//显示需要等待的时间
	
	public ImageView  iv_search;
	AnimationDrawable anim;
	Object ob;

	TextView tv_search_result;//搜索结果
	Button bt_search_end;//结束搜索
	static String deviceNames=" ";//记录连接上的设备的名称

	/**
	 * 控制是否进入WifiSetActivity（已经打开Wifi或者打不开wifi 5秒后 进入）
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
						//没内容进入FSK配置
						
							
							ActivitySetting.startActivity(((MyBaseActivity)MyApplication.getInstance().getCur_Activity()), NotConnectedActivity.class, null,true);
						
						
					}
					else{
						//有内容进入功能配置
							
							//当前有多个设备连接【可能都是无效的Socket，在TerminalActivity查看返回的Gernal信息没有就提示连接失效】
							ActivitySetting.startActivity(((Activity)MyApplication.getInstance().getCur_Activity()), TerminalActivity.class, null,true);
				
							
						
						
						
					}
					break;
				case CanNotSkip:
					/**
					 * 倒数计时
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
	 * 当FuncSwitch.SWITCH_TCPUDP_CONNECTTEST开关打开时才能允许测试
	 */
	if(UdpAndTcpTest.instance==null&&FuncSwitch.SWITCH_TCPUDP_CONNECTTEST){
		Log.d("TEST_SWITCH","TCPUDP测试已开启！！！");
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
	 * 加载播放声音的头和尾
	 */
	Tools.setHeadAndTail_Wave();
	wa=new WifiAdmin(this);
//	WifiStateWatchThread thread=new WifiStateWatchThread(this);//开启WIFI监听
//	thread.doListen();
	getUDPreciver();
	//开启终端列表连接的监听
	//Tools.al_Device_Info=new ArrayList<HashMap<String,Object>>();
	
	
	if(WifiAPTools.isWifiApEnable(this)){
		//如果手机是热点则不需要开启Wifi
		
	}else{
		//如果手机不是热点则需要开启Wifi
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
				 //超过5秒就不能连接上wifi了，也让进入配置界面
				 if(index<=0){
//					 Tools.isRec=false;
//					Tools.isSend=false;
					isSearching=false;
					 msg.what=CanSkip; 
				 }else{
					
					 if(wa.mWifiManager.isWifiEnabled()||WifiAPTools.isWifiApEnable((Activity)MyApplication.getInstance().getCur_Activity())){
						 //如果wifi能用或者手机是热点则可以不用继续等待wifi开启
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
		 * 相当于按Home键
		 */
		Intent i= new Intent(Intent.ACTION_MAIN); 
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		i.addCategory(Intent.CATEGORY_HOME); 
		startActivity(i);  
}
/**
 * 得到UDP的内容
 */

public static void getUDPreciver() {
	Network.getInstance((Activity)MyApplication.getInstance().getCur_Activity());
	/**
	 * 先对数据进行初始化，考虑到一次进入APP多次使用的情况
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
		//搜索结束
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
	//------从其他界面进入这个界面需要对已有的socket发送general请求进行轮询

	Network net = Network.getInstance(this);
	if (net != null) {
		net.refreshDevice();
	}
	
	//------------------------------------------------------------------------------------
	
	
	index=WAITTING_TIME;
	//设置UDP的发送时间间隔
	UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_SEARCH;
	if (net != null) {
		net.setUdpFrequency(UDPTools.Time_Send * 1000);
	}
	Tools.CurrentActivityName="SearchDevicesActivity";
	tv_timeCount=(TextView) findViewById(R.id.tv_activity_searchdevices);
	tv_timeCount.setText(index+"");
	//播放动画
	iv_search = (ImageView) findViewById(R.id.iv_search);
	ob = iv_search.getBackground();
	anim = (AnimationDrawable) ob;
	anim.stop();//停止播放
	anim.start();//开始播放

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
