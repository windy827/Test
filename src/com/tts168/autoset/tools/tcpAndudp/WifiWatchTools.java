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
 * WIFI监控工具类
 * @author 袁剑
 *
 */
public class WifiWatchTools {
	public static final String Alarm_STATE_WIFICLOSE="请求失败，当前没有可用的WIFI~";//WIFI不可用
	
	public static final String  Alarm__STATE_WIFICHANGE1="手机的网络已切换;";//WIFI不可用
	public static final String  Alarm__STATE_WIFICHANGE2="请确保手机和设备在同一wifi下！";//WIFI不可用
	/**
	 *  检测WIFI状态并提示【只有当当前Wifi名与配置文件的Wifi名一致时才会返回true】
	 * @param context
	 * @param needAlarm 是否需要提示
	 * @return
	 */
	 
	public static boolean checkWifiStateAndAlarm1(final Context context,boolean needAlarm){
		boolean isWifiStateNormal=false;
		SharedPreferences shared;
		NetManager netManager;
		final WifiAdmin wa;//Wifi操作类
		shared = new SharedConfig(context).GetConfig(); 	// 得到配置文件
		netManager = new NetManager(context); 				// 得到网络管理器
		wa = new WifiAdmin(context);
		 
		if(!netManager.isOpenWifi()){
			//WIFI不可用
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
				//WIFI已经切换
				
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
//									msg="当前Wifi SSID为"+ssid+",";
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
