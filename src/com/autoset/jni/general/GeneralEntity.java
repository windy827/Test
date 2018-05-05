package com.autoset.jni.general;

import java.io.Serializable;

/**
 * 通用参数领域General的实体类【个性化设置里面有用到】
 * 
 * @author 袁剑
 * 
 */
public class GeneralEntity implements Serializable {

	public static final String DOMAIN_DEVICE_FLUSH = "device_flush";// 云宝数据实时刷新
	public static final String DOMAIN_NAME = "general";// 通用参数领域
	public static final String GENERAL_NICKNAME = "nickname";// 产品名称
	public static final String GENERAL_VOICEMAN = "voiceman";// 个性发音人
	public static final String GENERAL_CITY = "city";// 所在城市
	public static final String GENERAL_LOCAL_ADAPTER = "localAdapter";// 保留信息
	public static final String GENERAL_LOG_FILTER = "logfilter";// 日志过滤器
	public static final String GENERAL_POWER = "device_power";// 设备电量
	public static final String GENERAL_PID="productid";//产品ID
	public static final String GENERAL_DEVICEID = "deviceid";// 设备ID
	public static final String GENERAL_userSet = "userSet";// 设备ID
	public static final String GENERAL_province = "province";// 设备ID
	public static final String GENERAL_Openid = "openid";//设备的openid
	public static final String GENERAL_FSK = "fsk";//设备是否刚进行过FSK配置，根据是否包含这个字段来判断

	public static final int USERSET_AUTO=2;//自动获取
	public static final int USERSET_DEFINED=1;//自定义
	
	private String nickname;
	private String voiceman;
	private String city;
	private Object localAdapter;
	private double logfilter;
	private int device_power;
	private String product_ID;
	private String deviceid;
	private int userSet;//0代表自动获取，1代表自定义
	private String province;
	private String openid;//设备的openid
	private boolean isFSKSet;
	/**
	 * KEYS对应的值
	 */
	private Object[] values;
	public static final String[] KEYS = new String[] { GENERAL_NICKNAME,
			GENERAL_VOICEMAN, GENERAL_CITY, GENERAL_LOCAL_ADAPTER,
			GENERAL_LOG_FILTER, GENERAL_POWER,GENERAL_PID,GENERAL_DEVICEID,GENERAL_userSet,GENERAL_province,GENERAL_Openid };
/**
 * 
 * @param nickname
 * @param voiceman
 * @param city
 * @param localAdapter
 * @param logfilter
 * @param device_power
 * @param product_ID
 * @param deviceid
 * @param userSet
 * @param province
 * @param openid
 * @param isFSKSet 初始化用false
 */
	public GeneralEntity(String nickname, String voiceman, String city,
			Object localAdapter, double logfilter, int device_power,String product_ID,
			String deviceid,int userSet,String province,String openid,boolean isFSKSet) {
		super();
		this.nickname = nickname;
		this.voiceman = voiceman;
		this.city = city;
		this.localAdapter = localAdapter;
		this.logfilter = logfilter;
		this.device_power=device_power;
		this.product_ID=product_ID;
		this.deviceid=deviceid;
		this.userSet=userSet;
		this.province=province;
		this.openid=openid;
		this.isFSKSet=isFSKSet;
		values = new Object[] { this.nickname, this.voiceman, this.city,
				this.localAdapter, this.logfilter, this.device_power,this.product_ID ,this.deviceid,this.userSet,this.province,this.openid};
	}

	public int getUserSet() {
		return userSet;
	}

	public void setUserSet(int userSet) {
		this.userSet = userSet;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public Object[] getValues() {
		return values;
	}
	public String getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(String product_ID) {
		this.product_ID = product_ID;
	}
	public int getDevice_power() {
		return device_power;
	}

	public void setDevice_power(int device_power) {
		this.device_power = device_power;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getVoiceman() {
		return voiceman;
	}

	public void setVoiceman(String voiceman) {
		this.voiceman = voiceman;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Object getLocalAdapter() {
		return localAdapter;
	}

	public void setLocalAdapter(Object localAdapter) {
		this.localAdapter = localAdapter;
	}

	public double getLogfilter() {
		return logfilter;
	}

	public void setLogfilter(double logfilter) {
		this.logfilter = logfilter;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public boolean isFSKSet() {
		return isFSKSet;
	}

	public void setFSKSet(boolean isFSKSet) {
		this.isFSKSet = isFSKSet;
	}

}
