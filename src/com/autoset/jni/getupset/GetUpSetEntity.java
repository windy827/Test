package com.autoset.jni.getupset;

import java.io.Serializable;

public class GetUpSetEntity  implements Serializable{

	public static final String DOMAIN_NAME="getupset";//领域
	

	public static final String 	ALARM_RING_TYPE	="ring_type";//音乐类型
	public static final String 	ALARM_RING_PATH	="ring_path";//音乐路径
	public static final String 	ALARM_RING_PLAY_TIME="play_time";//响铃时间
	
	public static final String 	ALARM_DELAY_NUM	="delay_num";//响铃次数  (贪睡次数)
	public static final String 	ALARM_DELAY_TIME="delay_time";//间隔响铃时间(贪睡时间)
	//public static final String 	ALARM_CONTENT	("content")//播音内容
	public static final String 	ALARM_MESSAGE_TIMES="message_times";//携带信息播放次数
	public static final String 	ALARM_MESSAGE_OPEN="message_open";//携带信息开关标志
	public static final String 	ALARM_WILL_DO="will_do";//闹铃起脑后 的自定义操作
	
	/**
	 * 本地音乐
	 */
	public static final double RING_TYPE_LOCAL=0.0;
	/**
	 * 网络音乐
	 */
	public static final double RING_TYPE_NET=1.0;
	/**
	 * 开启
	 */
	public static final double ENABLE_YES=1.0;
	/**
	 * 关闭
	 */
	public static final double ENABLE_NO=0.0;
	
	
	

	private double ring_type;
	private String ring_path;
	private double play_time;
	
	
	private double delay_num;
	private double delay_time;
	private double message_times;
	private double message_open;
	private double will_do;
	private Object[]values;
	
	public static final String []KEYS=new String[]{ALARM_RING_TYPE,ALARM_RING_PATH,
					ALARM_RING_PLAY_TIME,ALARM_DELAY_NUM,ALARM_DELAY_TIME,ALARM_MESSAGE_TIMES,ALARM_MESSAGE_OPEN,ALARM_WILL_DO};
	public GetUpSetEntity( double ring_type, String ring_path,
			double play_time, double delay_num, double delay_time,
			double message_times, double message_open, double will_do) {
		super();
		
		this.ring_type = ring_type;
		this.ring_path = ring_path;
		
		this.play_time = play_time;
		this.delay_num = delay_num;
		this.delay_time = delay_time;
		this.message_times = message_times;
		this.message_open = message_open;
		this.will_do = will_do;
		values=new Object[]{this.ring_type,this.ring_path,this.play_time,this.delay_num,this.delay_time,this.message_times,this.message_open,this.will_do};
	}
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
	}
	
	public double getRing_type() {
		return ring_type;
	}
	public void setRing_type(double ring_type) {
		this.ring_type = ring_type;
	}
	public String getRing_path() {
		return ring_path;
	}
	public void setRing_path(String ring_path) {
		this.ring_path = ring_path;
	}
	public double getPlay_time() {
		return play_time;
	}
	public void setPlay_time(double play_time) {
		this.play_time = play_time;
	}
	public double getDelay_num() {
		return delay_num;
	}
	public void setDelay_num(double delay_num) {
		this.delay_num = delay_num;
	}
	public double getDelay_time() {
		return delay_time;
	}
	public void setDelay_time(double delay_time) {
		this.delay_time = delay_time;
	}
	public double getMessage_times() {
		return message_times;
	}
	public void setMessage_times(double message_times) {
		this.message_times = message_times;
	}
	public double getMessage_open() {
		return message_open;
	}
	public void setMessage_open(double message_open) {
		this.message_open = message_open;
	}
	public double getWill_do() {
		return will_do;
	}
	public void setWill_do(double will_do) {
		this.will_do = will_do;
	}
	
	
	
}

