package com.autoset.jni.alarm;

import java.io.Serializable;

/**
 * 每日闹铃的设置实体类
 * 
 * @author 袁剑
 * 
 */
public class AlarmEntity implements Serializable {

	public static final String DOMAIN_ALARM = "alarm";// 通用参数领域

	public static final String ALARM_ID = "ID";// 标题
	public static final String ALARM_TITLE = "title";// 标题
	public static final String ALARM_DATE = "date";// 日期
	public static final String ALARM_CLOCK = "clock";// 时间
	public static final String ALARM_IS_VALID = "is_valid";// 是否开启（使能）
	// public static final String ALARM_ENABLE="enable";//是否开启（使能）
	public static final String ALARM_IS_LUNAR = "is_lunar";// 是否是阴历 0阳历 1阴历

	public static final String ALARM_FRE_MODE = "fre_mode";// 周期模式，一次，每天，每周，每年，每月
	public static final String ALARM_FREQUENCY = "frequency";// 周期

	/**
	 * 本地音乐
	 */
	public static final double RING_TYPE_LOCAL = 0.0;
	/**
	 * 网络音乐
	 */
	public static final double RING_TYPE_NET = 1.0;
	/**
	 * 开启
	 */
	public static final double ENABLE_YES = 1.0;
	/**
	 * 关闭
	 */
	public static final double ENABLE_NO = 0.0;

	/**
	 * 生效
	 */
	public static final double ISVALID_YES = 1.0;
	/**
	 * 不生效
	 */
	public static final double ISVALID_NO = 0.0;

	/**
	 * 为阴历
	 */
	public static final double ISLUNAR_YES = 1.0;
	/**
	 * 为阳历
	 */
	public static final double ISLUNAR_NO = 0.0;
	/**
	 * 唯一标识号，用于增删改查
	 */
	private int id;
	private String title;
	private String date;
	private String clock;
	private double is_lunar;
	private double is_valid;

	private double fre_mode;
	private double frequency;

	private Object[] values;

	public static final String[] KEYS = new String[] { ALARM_ID, ALARM_TITLE,
			ALARM_DATE, ALARM_CLOCK, ALARM_IS_LUNAR, ALARM_IS_VALID,
			ALARM_FRE_MODE, ALARM_FREQUENCY };

	public AlarmEntity(int id, String title, String date, String clock,
			double is_lunar, double is_valid, double fre_mode, double frequency) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.clock = clock;

		this.is_lunar = is_lunar;
		this.is_valid = is_valid;
		this.fre_mode = fre_mode;
		this.frequency = frequency;

		values = new Object[] { this.id, this.title, this.date, this.clock,
				this.is_lunar, this.is_valid, this.fre_mode, this.frequency };
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public Object[] getValues() {
		return values;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public double getIs_lunar() {
		return is_lunar;
	}

	public void setIs_lunar(double is_lunar) {
		this.is_lunar = is_lunar;
	}

	public double getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(double is_valid) {
		this.is_valid = is_valid;
	}

	public double getFre_mode() {
		return fre_mode;
	}

	public void setFre_mode(double fre_mode) {
		this.fre_mode = fre_mode;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

}
