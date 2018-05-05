package com.tts168.autoset.tools.device;

import java.io.Serializable;

/**
 * 云宝设备的实体类
 * @author 袁剑
 *
 */
public class DeviceEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 设备名称
	 */
private String  deviceName;
/**
 * 网络IP地址
 */
private String IP;
/**
 * 设备Mac地址
 */
private String MacIP;

/**
 * 硬件版本号
 */
private String version;
/**
 * 软件件版本号
 */
private String swversion;
/**
 * 产品ID  000005001
 */
private String gid;
/**
 * 配置文件版本
 */
private String config;

/**
 * 产品电量
 */
private int power;
/**
 * wifi信号强度
 */
private int rssi;
/**
 * 设备端对应的服务器地址
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
