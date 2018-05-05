package com.autoset.jni.otherset;

import java.io.Serializable;
/**
 * 其他设置
 * @author 袁剑
 *
 */
 
 
public class ParameterEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6667L;
	
	public static final String DOMAIN_NAME="parameter1";
	
	/**
	 * 白天上电初始化后的初始音量（0-9，255）,255表示不初始化音量，沿用上个状态的音量
	 */
	public static String KEY_dayInitVolume="dayInitVolume";

	public static String[]volumeValuesString=new String[]{"维持不变","1级音量","2级音量","3级音量","4级音量","5级音量","6级音量"};
	public static int[] volumeValuesInteger=new int[]{255,0,1,3,5,7,9};
	/**
	 * MP3资源循环播放时长控制（分钟）
	 */
	public static String KEY_loopPlayTime="loopPlayTime";
	public static String[] playTimesString=new String[]{10+"分钟",20+"分钟",30+"分钟",40+"分钟",50+"分钟",60+"分钟"};
	public static int[] playTimesInt=new int[]{10,20,30,40,50,60};
	/**
	 * 正点报时开关
	 */
	public static String KEY_chime="chime";
	/**
	 * 半点报时开关
	 */
	public static String KEY_halfchime="halfchime";
	/**
	 * 报时起始时间(暂时不用，使用夜间模式的时间段)
	 */
	public static String KEY_chime_start="chime_start";
	/**
	 * 报时结束时间(暂时不用，使用夜间模式的时间段)
	 */
	public static String KEY_chime_end="chime_end";
	
	public static final int switch_YES=1;
	public static final int switch_NO=0;
	
	/**
	 * KEYS对应的值
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{KEY_dayInitVolume,KEY_loopPlayTime,KEY_chime,KEY_halfchime,KEY_chime_start,KEY_chime_end};
	
	
	private int  dayInitVolume;
	private int  loopPlayTime;
	private int  chime;
	private int  halfchime;
	private String chime_start;
	private String chime_end;
	public ParameterEntity(int dayInitVolume, int loopPlayTime, int chime,
			int halfchime, String chime_start, String chime_end) {
		super();
		this.dayInitVolume = dayInitVolume;
		this.loopPlayTime = loopPlayTime;
		this.chime = chime;
		this.halfchime = halfchime;
		this.chime_start = chime_start;
		this.chime_end = chime_end;
		values=new Object[]{this.dayInitVolume,this.loopPlayTime,this.chime,this.halfchime,this.chime_start,this.chime_end};
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	public int getDayInitVolume() {
		return dayInitVolume;
	}
	public void setDayInitVolume(int dayInitVolume) {
		this.dayInitVolume = dayInitVolume;
	}
	public int getLoopPlayTime() {
		return loopPlayTime;
	}
	public void setLoopPlayTime(int loopPlayTime) {
		this.loopPlayTime = loopPlayTime;
	}
	public int getChime() {
		return chime;
	}
	public void setChime(int chime) {
		this.chime = chime;
	}
	public int getHalfchime() {
		return halfchime;
	}
	public void setHalfchime(int halfchime) {
		this.halfchime = halfchime;
	}
	public String getChime_start() {
		return chime_start;
	}
	public void setChime_start(String chime_start) {
		this.chime_start = chime_start;
	}
	public String getChime_end() {
		return chime_end;
	}
	public void setChime_end(String chime_end) {
		this.chime_end = chime_end;
	}
	
	
}
