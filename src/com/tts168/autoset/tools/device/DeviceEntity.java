package com.tts168.autoset.tools.device;

import java.io.Serializable;

/**
 * �Ʊ��豸��ʵ����
 * @author Ԭ��
 *
 */
public class DeviceEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * �豸����
	 */
private String  deviceName;
/**
 * ����IP��ַ
 */
private String IP;
/**
 * �豸Mac��ַ
 */
private String MacIP;

/**
 * Ӳ���汾��
 */
private String version;
/**
 * ������汾��
 */
private String swversion;
/**
 * ��ƷID  000005001
 */
private String gid;
/**
 * �����ļ��汾
 */
private String config;

/**
 * ��Ʒ����
 */
private int power;
/**
 * wifi�ź�ǿ��
 */
private int rssi;
/**
 * �豸�˶�Ӧ�ķ�������ַ
 */
private String host;
public DeviceEntity(String deviceName, String iP, String macIP, String version,String swversion,
		String gid, int power,int rssi,String host,String config) {
	super();
	this.deviceName = deviceName;
	IP = iP;
	MacIP = macIP;
	this.version = version;
	this.swversion=swversion;
	this.gid = gid;
	this.power = power;
	this.rssi=rssi;
	this.host=host;
	this.config=config;
}
public int getPower() {
	return power;
}
public void setPower(int power) {
	this.power = power;
}
public String getDeviceName() {
	return deviceName;
}
public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}
public String getIP() {
	return IP;
}
public void setIP(String iP) {
	IP = iP;
}
public String getMacIP() {
	return MacIP;
}
public void setMacIP(String macIP) {
	MacIP = macIP;
}
public String getVersion() {
	return version;
}

public String getSwversion() {
	return swversion;
}
public void setSwversion(String swversion) {
	this.swversion = swversion;
}
public void setVersion(String version) {
	this.version = version;
}
public String getGid() {
	return gid;
}
public void setGid(String pid) {
	this.gid = pid;
}
public int getRssi() {
	return rssi;
}
public void setRssi(int rssi) {
	this.rssi = rssi;
}
public String getHost() {
	return host;
}
public void setHost(String host) {
	this.host = host;
}
public String getConfig() {
	return config;
}
public void setConfig(String config) {
	this.config = config;
}


}
