package com.autoset.jni.udp;

import java.io.Serializable;
/**
 * UDP发送的内容
 * @author 袁剑
 *
 */
public class SearchSendEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9845347895L;
	
	public static final String Search_Method="search";
	
	public static final String Search_DomainName="caller";
	
	public static final String Search_Device_name="device";
	public static final String Search_Device_values="lark";
	
	public static final String Search_ip_name="ip";
	public static final String Search_deviceid_name="deviceid";
	public static final String Search_app_version_name="app_version";
	public static final String Search_CSendTime="CSendTime";
	public static final String Search_os_version_name="os_version";
	public static final String Search_os_version_values="android OS 2.3.3";
	
	public static final int Search_ID=9998;
	String ip;
	String deviceid;
	String app_version;
	String cSendTime;
	/**
	 * KEYS对应的值
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{Search_ip_name,Search_deviceid_name,Search_app_version_name,Search_os_version_name,Search_CSendTime};
	public SearchSendEntity(String ip, String deviceid, String app_version,String cSendTime) {
		super();
		this.ip = ip;
		this.deviceid = deviceid;
		this.app_version = app_version;
		this.cSendTime=cSendTime;
		values=new Object[]{this.ip,this.deviceid,this.app_version,Search_os_version_values,this.cSendTime};
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	public String getcSendTime() {
		return cSendTime;
	}
	public void setcSendTime(String cSendTime) {
		this.cSendTime = cSendTime;
	}
	

}
