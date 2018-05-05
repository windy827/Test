package com.tts168.autoset.tools.others.wifi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
/**
 * �Ա�����ΪWifi�ȵ����Ϣ��ȡ
 * @author Administrator
 *
 */
public class WifiAPTools {
	/**
	 * �õ��ȵ��������Ϣ
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
 * �ж��ȵ��Ƿ��Ѿ�����
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
