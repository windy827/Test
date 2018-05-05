package com.tts168.autoset.tools.others.wifi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
/**
 * 对本机作为Wifi热点的信息获取
 * @author Administrator
 *
 */
public class WifiAPTools {
	/**
	 * 得到热点的配置信息
	 */
	public static WifiConfiguration getWifiAPConfigration(Context c){
		WifiManager mWifiManager=(WifiManager) c.getSystemService(Context.WIFI_SERVICE);
		WifiConfiguration info=null;
		try {
			Method method=mWifiManager.getClass().getMethod("getWifiApConfiguration");
			info=(WifiConfiguration) method.invoke(mWifiManager);
			      
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
/**
 * 判断热点是否已经开启
 * @param c
 * @return
 */
	public static boolean isWifiApEnable(Context c){
		WifiManager mWifiManager=(WifiManager) c.getSystemService(Context.WIFI_SERVICE);
		boolean isEnable=false;
		try {
			Method method=mWifiManager.getClass().getMethod("isWifiApEnabled");
			
				isEnable=(Boolean) method.invoke(mWifiManager);
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isEnable;
	}
}
