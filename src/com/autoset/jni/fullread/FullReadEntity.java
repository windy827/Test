package com.autoset.jni.fullread;

import java.io.Serializable;

/**
 * ���㱨ʱʵ����
 * @author Ԭ��
 *
 */
public class FullReadEntity implements Serializable{
	public static final String DOMAIN_CLOCKCHIME="clockchime";//ͨ�ò�������
	
	public static final String CHIME_CHIME="chime";//���㱨ʱ����
	public static final String CHIME_HALF_CHIME	="halfchime";//��㱨ʱ����
	public static final String CHIME_START="start";//��ʱ��ʼʱ��
	public static final String CHIME_END="end";//��ʱ����ʱ��
	/**
	 * ���㿪��
	 */
	private int full;
	/**
	 * ��㿪��
	 */
	private int half;
	/**
	 * ��ʼʱ��
	 */
	private String starttime;
	/**
	 * ����ʱ��
	 */
	private String endtime;
	/**
	 * KEYS��Ӧ��ֵ
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{CHIME_CHIME,CHIME_HALF_CHIME,CHIME_START,CHIME_END};
	public FullReadEntity(int full, int half, String starttime, String endtime) {
		super();
		this.full = full;
		this.half = half;
		this.starttime = starttime;
		this.endtime = endtime;
		values=new Object[]{this.full,this.half,this.starttime,this.endtime};
	}
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
	}
	public int getFull() {
		return full;
	}
	public void setFull(int full) {
		this.full = full;
	}
	public int getHalf() {
		return half;
	}
	public void setHalf(int half) {
		this.half = half;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	

	
}
