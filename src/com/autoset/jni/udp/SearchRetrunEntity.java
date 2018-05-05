package com.autoset.jni.udp;

import java.io.Serializable;

/**
 * UDP �ظ���ʵ����
 * 
 * @author Ԭ��
 * 
 */
public class SearchRetrunEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 232471L;

	public final static String DomainName = "lark";
	
	public final static String SearchRet_IP = "ip";
	public final static String SearchRet_PORT = "port";
	public final static String SearchRet_DEVICEID = "deviceid";
	public final static String SearchRet_VERSION = "version";
	public final static String SearchRet_PRODUCTID = "productid";
	public final static String SearchRet_CSendtime="CSendTime";//�ֻ�����UDP��ʱ��
	public final static String SearchRet_DRecivetime="DReciveTime";//�豸���ܵ�UDP��ʱ��
	public final static String SearchRet_DSendtime="DSendTime";//�豸�ط�UDP��ʱ��
	public final static String SearchRet_CRecievetime="CReciveTime";//�ֻ����յ�UDP��ʱ��
	
	String ip;
	int port;
	String deviceid;
	String version;
	String productid;
	String cSendTime;
	String dReciveTime;
	String dSendTime;
	String cellphoneReciveTime;
	
	public SearchRetrunEntity(String ip, int port, String deviceid,
			String version, String productid,String cSendTime,String dReciveTime,String dSendTime,String cellphoneReciveTime) {
		super();
		this.ip = ip;
		this.port = port;
		this.deviceid = deviceid;
		this.version = version;
		this.productid = productid;
		this.cSendTime=cSendTime;
		this.dReciveTime=dReciveTime;
		this.dSendTime=dSendTime;
		this.cellphoneReciveTime=cellphoneReciveTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	
	public String getcSendTime() {
		return cSendTime;
	}
	public void setcSendTime(String cSendTime) {
		this.cSendTime = cSendTime;
	}
	public String getdReciveTime() {
		return dReciveTime;
	}
	public void setdReciveTime(String dReciveTime) {
		this.dReciveTime = dReciveTime;
	}
	public String getdSendTime() {
		return dSendTime;
	}
	public void setdSendTime(String dSendTime) {
		this.dSendTime = dSendTime;
	}
	public String getCellphoneReciveTime() {
		return cellphoneReciveTime;
	}
	public void setCellphoneReciveTime(String cellphoneReciveTime) {
		this.cellphoneReciveTime = cellphoneReciveTime;
	}

}
