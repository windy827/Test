package com.tts168.autoset.tools.tcpAndudp;

import java.util.ArrayList;
import java.util.HashMap;

import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.others.wifi.NetManager;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.view.MyWifiView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

/**
 * WIFI��ع�����
 * @author Ԭ��
 *
 */
public class WifiWatchTools {
	public static final String Alarm_STATE_WIFICLOSE="����ʧ�ܣ���ǰû�п��õ�WIFI~";//WIFI������
	
	public static final String  Alarm__STATE_WIFICHANGE1="�ֻ����������л�;";//WIFI������
	public static final String  Alarm__STATE_WIFICHANGE2="��ȷ���ֻ����豸��ͬһwifi�£�";//WIFI������
	/**
	 *  ���WIFI״̬����ʾ��ֻ�е���ǰWifi���������ļ���Wifi��һ��ʱ�Ż᷵��true��
	 * @param context
	 * @param needAlarm �Ƿ���Ҫ��ʾ
	 * @return
	 */
	 
	public static boolean checkWifiStateAndAlarm1(final Context context,boolean needAlarm){
		boolean isWifiStateNormal=false;
		SharedPreferences shared;
		NetManager netManager;
		final WifiAdmin wa;//Wifi������
		shared = new SharedConfig(context).GetConfig(); 	// �õ������ļ�
		netManager = new NetManager(context); 				// �õ����������
		wa = new WifiAdmin(context);
		 
		if(!netManager.isOpenWifi()){
			//WIFI������
			if(needAlarm){
				Handler handler = new Handler(context.getMainLooper());
				handler.post(new Runnable(){
					@Override
					public void run() {
						ToastTools.long_Toast(context, Alarm_STATE_WIFICLOSE);
					}
				});
				
			}
			
		}else{
		
			final String wifiinfo=wa.GetBSSID();
			
			 String sharedWIFIINFO=shared.getString(SharedConfig.KEY_WIFIBSSID, "");
			
			
			
			 if(wifiinfo.equals(sharedWIFIINFO)||Tools.Current_SocketIP.length()==0){
				 isWifiStateNormal=true;
			 }else{
				//WIFI�Ѿ��л�
				
				// 
				 if(needAlarm){
					 Handler handler = new Handler(context.getMainLooper());
						handler.post(new Runnable(){
							@Override
							public void run() {
								 //ToastTools.long_Toast(context, Alarm__STATE_WIFICHANGE);
								return;
//								if(Tools.Current_Socket==null){
//									return;
//								}
//								String ssid=WifiTool.getCurrentSSID(wa);
//								String msg="";
//								if(ssid.length()>0){
//									msg="��ǰWifi SSIDΪ"+ssid+",";
//								}
//								 NotifyDialog.SimpleAlertDialog(Tools.c, Alarm__STATE_WIFICHANGE1+Alarm__STATE_WIFICHANGE2);
								// ActivitySetting.startActivity((Activity)Tools.c, SearchDevicesActivity.class, null);
							}
						});
					
					}
				 
			 }
		}
		return isWifiStateNormal;
	}
}
