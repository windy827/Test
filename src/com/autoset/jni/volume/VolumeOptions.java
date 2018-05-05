package com.autoset.jni.volume;

import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;

/**
 * ������Json����
 * @author Ԭ��
 *
 */
public class VolumeOptions {
	public  static final String SetMethod_Name="setVolume";
	public  static final String setVolumeUpMethod_Name="setVolumeUp";
	public  static final String setVolumeDownMethod_Name="setVolumeDown";
	public  static final String GetMethod_Name="getVolume";
	
	public static int SETID=2222;
	public static int GETID=2223;
	//-------------------------����---------------------------
	/**
	 * ����set��������
	 */
	public static String  sendSetVolume(int volumeParams){
		AutoSetJsonTools autoSetJsonTools=new AutoSetJsonTools();
		String result=autoSetJsonTools.setVolumeJsonObject(SETID, SetMethod_Name, volumeParams);
		return result;
	}
	/**
	 * ����get��������
	 */
	public static String sendGetVolume(){
		AutoSetJsonTools autoSetJsonTools=new AutoSetJsonTools();
		String result=autoSetJsonTools.setVolumeJsonObject(SETID, SetMethod_Name,  null);
		return result;
	}
	
	
	/**
	 * ���͵�����������
	 */
	public static String sendSetVolumeUp(){
		AutoSetJsonTools autoSetJsonTools=new AutoSetJsonTools();
		String result=autoSetJsonTools.setVolumeJsonObject(SETID, setVolumeUpMethod_Name, null);
		return result;
	}
	
	/**
	 * ���ͼ�С��������
	 */
	public static String sendSetVolumeDown(){
		AutoSetJsonTools autoSetJsonTools=new AutoSetJsonTools();
		String result=autoSetJsonTools.setVolumeJsonObject(SETID, setVolumeDownMethod_Name,  null);
		return result;
	}
	//----------------------����-------------------------------
	/**
	 * �õ���ǰ����
	 * @param data
	 * @return
	 */
	public static int getCurrentVol(String data){
		int curVol=1;
		try {
			JSONObject main=new JSONObject(data);
			boolean hasResult=main.has("result");
			if(hasResult){
				JSONObject result=new JSONObject(data);
				boolean hasCurVol=result.has("curVol");
				if(hasCurVol){
					AutoSetParsorTools autoJsonTools=new AutoSetParsorTools(result);
					curVol=autoJsonTools.getInt("curVol");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curVol;
		
	} 
}
