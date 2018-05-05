package com.autoset.jni.heartbeat;

import java.io.Serializable;
/**
 * 心跳检测
 * @author 袁剑
 *
 */
public class HeartBeatEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 242432453L;
	
	public static final String TIMER_name="timer";
	public static final String TIMER_values="app_heartbeat";
	public static final String VALUE_name="value";
	public static final int VALUE_values=15000;
	
	String timer;
	int value;//单位毫秒【等待value的时长如果没有响应服务器会自动关闭掉当前连接】
	/**
	 * KEYS对应的值
	 */
	private Object[] values;
	public static final String[] KEYS = new String[] { TIMER_name,VALUE_name };
	public HeartBeatEntity(String timer, int value) {
		super();
		this.timer = timer;
		this.value = value;
		this.values = new Object[]{this.timer,this.value};
	}
	public String getTimer() {
		return timer;
	}
	public void setTimer(String timer) {
		this.timer = timer;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	 

}
