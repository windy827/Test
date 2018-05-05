package com.autoset.jni.heartbeat;

import java.io.Serializable;
/**
 * �������
 * @author Ԭ��
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
	int value;//��λ���롾�ȴ�value��ʱ�����û����Ӧ���������Զ��رյ���ǰ���ӡ�
	/**
	 * KEYS��Ӧ��ֵ
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
