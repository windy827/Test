package com.autoset.jni.wifi;

import java.io.Serializable;

/**
 * Wifiʵ����SSID,���룬���ȼ�
 * @author Ԭ��
 *
 */
public class WifiEntity implements Serializable{
	public static final String DOMAIN_WIFI="wifi";//Wifi����
	public static final String WIFI_ID	="ID";//SSID����
	public static final String WIFI_SSID	="ssid";//SSID����
	public static final String WIFI_PASSWORD	="password";//���� 
	public static final String WIFI_PRIORITY	="priority";//���ȼ�
	
	/**
	 * Ψһ��ʶ�ţ�������ɾ�Ĳ�
	 */
	private int id;
	private String ssid;
	private String password;
	private int priority;
	/**
	 * KEYS��Ӧ��ֵ
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{WIFI_ID,WIFI_SSID,WIFI_PASSWORD,WIFI_PRIORITY};
	public WifiEntity(int id, String ssid, String password, int priority) {
		super();
		this.id = id;
		this.ssid = ssid;
		this.password = password;
		this.priority = priority;
		values=new Object[]{this.id,this.ssid,this.password,this.priority};
	}
	
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	
}
