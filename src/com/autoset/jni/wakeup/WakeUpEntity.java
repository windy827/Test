package com.autoset.jni.wakeup;

import java.io.Serializable;
/**
 * ��������
 * @author Administrator
 *
 */
public class WakeUpEntity implements Serializable{

//	open_weakup	number	���ѹ��ܿ���	0���ر�1����
//	weak_name	string	����ʹ�õ����֣�ֻ�ܴ�ʾ��������ѡһ��
//		���Ʊ���  ��С����  ��Hi�Ʊ���   ��HiС����
//	open_prompt	number	������ʾ������	0���ر�1����
//	time_limit	number	����ʱ������ƿ���	0���ر�1����
//	
//	time_start	string	���ѿ�ʼʱ��	��06:00��
//	time_end	string	���ѽ���ʱ��	��24:00��
	
	public static final String DOMAIN_NAME="wakeup";

	/**
	 * ���ѹ��ܿ���	0���ر�1���� number
	 */
	public static final String KEY_open_weakup="open_wakeup";
	public static final double open_weakup_YES=1.0;
	public static final double open_weakup_NO=0.0;

	/**
	 * ����ʱ������ƿ���	0���ر�1���� number
	 */
	public static final String KEY_time_limit="time_limit";
	public static final double time_limit_YES=1.0;
	public static final double time_limit_NO=0.0;
	/**
	 * ����ʹ�õ����֣�ֻ�ܴ�ʾ��������ѡһ��
		���Ʊ���  ��С����  ��Hi�Ʊ���   ��HiС����  
	 */
	public static final String KEY_weak_name="wake_name";

	/**
	 * ���ѿ�ʼʱ��	��06:00��  string
	 */
	public static final String KEY_time_start="time_start";
	/**
	 * ���ѽ���ʱ��	��24:00��  string
	 */
	public static final String KEY_time_end="time_end";
	
	/**
	 * KEYS��Ӧ��ֵ
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{KEY_open_weakup,KEY_time_limit,KEY_weak_name,KEY_time_start,KEY_time_end};
	
	
	private double open_weakup;

	private double time_limit;
	private String weak_name;
	private String time_start;
	private String time_end;

	

	public WakeUpEntity(double open_weakup,
			 double time_limit, String weak_name,
			String time_start, String time_end) {
		super();
		
		this.open_weakup = open_weakup;
		
		this.time_limit = time_limit;
		this.weak_name = weak_name;
		this.time_start = time_start;
		this.time_end = time_end;
	
		values=new Object[]{this.open_weakup,this.time_limit,this.weak_name,this.time_start,this.time_end};
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	public double getOpen_weakup() {
		return open_weakup;
	}
	public void setOpen_weakup(double open_weakup) {
		this.open_weakup = open_weakup;
	}
	
	public double getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(double time_limit) {
		this.time_limit = time_limit;
	}
	public String getWeak_name() {
		return weak_name;
	}
	public void setWeak_name(String weak_name) {
		this.weak_name = weak_name;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	
	
	
	
	

	
	
	
}
