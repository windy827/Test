package com.autoset.jni.deviceInfo;

import java.io.Serializable;

/**
 * �豸��Ϣ
 * @author Ԭ��
 *
 */
public class DeviceInfoEntity implements Serializable{

	public static final String DOMAIN_DEVICE_INFO="device_info";//�Ʊ�ʵʱ����
	public static final String DOMAIN_DEVICE_FLUSH="device_flush";//�Ʊ�����ʵʱˢ��
	
	public static final String DEVICE_INFO_IP="ip";//�豸ip
	public static final String DEVICE_INFO_MAC="mac";//�豸mac
	public static final String DEVICE_INFO_POWER="device_power";//�豸����
	public static final String DEVICE_INFO_HWVER="hw_ver";//�豸�汾��
	public static final String DEVICE_INFO_SWVER="sw_ver";//�豸�汾��
	public static final String DEVICE_INFO_PID="productid";//�豸ID
	public static final String DEVICE_INFO_GID="goodsid";//�豸ID
	public static final String DEVICE_INFO_RSSI="RSSI";//Wifi�ź�ǿ��	0-5,0:δ����,5:���
	public static final String DEVICE_INFO_HOST="host";//�豸��Ӧ�ķ�������ַ
	public static final String DEVICE_INFO_CONFIG="config";//�豸��Ӧ�������ļ��汾
	public static final String DEVICE_INFO_OPENID="openid";//�豸��OpenID
	
	
	private int device_power;
	private String device_ip;
	private String device_mac;
	private String device_version;
	private String sw_version;
	private String device_product_ID;
	private String goodsid;
	private String config;
	private int rssi;
	private String host;
	private String openid;
	/**
	 * KEYS��Ӧ��ֵ
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{DEVICE_INFO_POWER,DEVICE_INFO_IP,DEVICE_INFO_MAC,DEVICE_INFO_HWVER,DEVICE_INFO_SWVER,DEVICE_INFO_PID,DEVICE_INFO_GID,DEVICE_INFO_RSSI,DEVICE_INFO_HOST,DEVICE_INFO_CONFIG};
	
	public DeviceInfoEntity(int device_power, String device_ip,
			String device_mac, String device_version,String sw_version, String device_product_ID,String goodsid,int rssi,String host,String config,String openid) {
		super();
		this.device_power = device_power;
		this.device_ip = device_ip;
		this.device_mac = device_mac;
		this.device_version = device_version;
		this.sw_version=sw_version;
		this.device_product_ID = device_product_ID;
		this.goodsid=goodsid;
		this.rssi=rssi;
		this.host=host;
		this.config=config;
		this.openid=openid;
		values=new Object[]{this.device_power,this.device_ip,this.device_mac,this.device_version,this.sw_version,this.device_product_ID,this.goodsid,this.rssi,this.host,this.config};
	}
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
	}
	
	public String getSw_version() {
		return sw_version;
	}
	public void setSw_version(String sw_version) {
		this.sw_version = sw_version;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public int getDevice_power() {
		return device_power;
	}
	public void setDevice_power(int device_power) {
		this.device_power = device_power;
	}
	public String getDevice_ip() {
		return device_ip;
	}
	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}
	public String getDevice_mac() {
		return device_mac;
	}
	public void setDevice_mac(String device_mac) {
		this.device_mac = device_mac;
	}
	public String getDevice_version() {
		return device_version;
	}
	public void setDevice_version(String device_version) {
		this.device_version = device_version;
	}
	public String getDevice_product_ID() {
		return device_product_ID;
	}
	public void setDevice_product_ID(String device_product_ID) {
		this.device_product_ID = device_product_ID;
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
	
}
