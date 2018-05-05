package com.autoset.jni.udp;

import java.io.Serializable;

/**
 * ����UDP���յ���Name[�Ʊ�����],IP ��ַ���˿ں�
 * @author Ԭ��
 *
 */
public class UDPNetEntity implements Serializable{
	public static final String DOMAIN_NET="server";//ͨ�ò�������
	
	public static final String NET_IP	="ip";//ip��ַ��Ϣ
	public static final String NET_PORT	="port";//�˿ں�
	public static final String NET_APK_VERSION	="apk_version";//app�汾��
	public static final String NET_ANDROID_VERSION="android_version";//android�汾��
	
	private String ip;
	private int port;
	private int apk_version;
	private int android_version;
	/**
	 * KEYS��Ӧ��ֵ
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{NET_IP,NET_PORT,NET_APK_VERSION,NET_ANDROID_VERSION};
	public UDPNetEntity(String ip, int port, int apk_version,
			int android_version) {
		super();
		this.ip = ip;
		this.port = port;
		this.apk_version = apk_version;
		this.android_version = android_version;
		values=new Object[]{this.ip,this.port,this.apk_version,this.android_version};
	}
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
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
	public int getApk_version() {
		return apk_version;
	}
	public void setApk_version(int apk_version) {
		this.apk_version = apk_version;
	}
	public int getAndroid_version() {
		return android_version;
	}
	public void setAndroid_version(int android_version) {
		this.android_version = android_version;
	}
	
}
