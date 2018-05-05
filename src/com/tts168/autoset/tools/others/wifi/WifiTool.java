package com.tts168.autoset.tools.others.wifi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
/**
 * 操纵Wifi
 * @author Administrator
 *
 */
public class WifiTool {
	
	
	public static String getCurrentSSID(WifiAdmin wa){
	 
		WifiManager wifi = wa.mWifiManager;  
		WifiInfo info = wifi.getConnectionInfo();  
		
		String status = "";  
		if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED)  
		{  
		status = "WIFI_STATE_ENABLED";  
		}  
		String ssid = info.getSSID();  
	 
		if(ssid!=null&&ssid.length()>0){
			String start=ssid.toCharArray()[0]+"";
			String end=ssid.toCharArray()[ssid.length()-1]+"";
			//解决有的版本在得到的SSID时会在头尾加上引号的问题
			if(start.equals("\"")&&end.equals("\"")){
				ssid=(String) ssid.subSequence(1, ssid.length()-1);
			}
		}else{
			ssid ="";
		}
		
		return ssid;
		 
	}
	/**
	 * 返回当前连接上了的wifi的SSID，密码
	 */
	public static  ArrayList<HashMap<String,String>>getCurrentWifiInfo(WifiAdmin wa){
		ArrayList<HashMap<String,String>>current=new ArrayList<HashMap<String,String>>();
		List<WifiConfiguration> ll=wa.GetConfiguration();
		for(int i=0;i<ll.size();i++){
			int status=ll.get(i).status;
			String ssid=ll.get(i).SSID;
			//状态值为当前连上的
			if(status==WifiConfiguration.Status.CURRENT){
				HashMap<String,String> temp=new HashMap<String,String>();
				String SSID=ll.get(i).SSID.substring(1, ll.get(i).SSID.length()-1);
			
				temp.put("SSID", SSID);
			
				current.add(temp);
			}
		}
		return current;
	}
	/**
	 * 如果已经连接过wifi的，根据SSID得到Password	
	 * @return
	 */
		public static String getPassword(WifiAdmin wa,String SSID){
			String password="";
			List<WifiConfiguration> ll=wa.GetConfiguration();
			for(int i=0;i<ll.size();i++){
				String ssid= ll.get(i).SSID;
				int status=ll.get(i).status;
				Log.d("TEST",ssid+"::::"+status);
				//状态值为当前连上的
				if(status==WifiConfiguration.Status.CURRENT&&SSID.equals(ll.get(i).SSID)){
					password=ll.get(i).preSharedKey.substring(1, ll.get(i).preSharedKey.length()-2);;
				}
			}
			return password;
		}
	
/**
 * 得到一连串搜索到的SSID	
 * @return
 */
	public static ArrayList<HashMap<String,String>> getSSIDArray(WifiAdmin wa){
		ArrayList<HashMap<String,String>>all_SSID=new ArrayList<HashMap<String,String>>();
		List<ScanResult> mWifiList=wa.GetWifiList();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < mWifiList.size(); i++)
		{
			
			if((mWifiList.get(i)).SSID.length()>0){
				HashMap<String,String>temp=new HashMap<String,String>();
				String SSID=(mWifiList.get(i)).SSID;
				temp.put("SSID",SSID );
				all_SSID.add(temp);
				
			}
		}
		return all_SSID;
	}
}
