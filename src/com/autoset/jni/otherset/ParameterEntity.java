package com.autoset.jni.otherset;

import java.io.Serializable;
/**
 * ��������
 * @author Ԭ��
 *
 */
 
 
public class ParameterEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6667L;
	
	public static final String DOMAIN_NAME="parameter1";
	
	/**
	 * �����ϵ��ʼ����ĳ�ʼ������0-9��255��,255��ʾ����ʼ�������������ϸ�״̬������
	 */
	public static String KEY_dayInitVolume="dayInitVolume";

	public static String[]volumeValuesString=new String[]{"ά�ֲ���","1������","2������","3������","4������","5������","6������"};
	public static int[] volumeValuesInteger=new int[]{255,0,1,3,5,7,9};
	/**
	 * MP3��Դѭ������ʱ�����ƣ����ӣ�
	 */
	public static String KEY_loopPlayTime="loopPlayTime";
	public static String[] playTimesString=new String[]{10+"����",20+"����",30+"����",40+"����",50+"����",60+"����"};
	public static int[] playTimesInt=new int[]{10,20,30,40,50,60};
	/**
	 * ���㱨ʱ����
	 */
	public static String KEY_chime="chime";
	/**
	 * ��㱨ʱ����
	 */
	public static String KEY_halfchime="halfchime";
	/**
	 * ��ʱ��ʼʱ��(��ʱ���ã�ʹ��ҹ��ģʽ��ʱ���)
	 */
	public static String KEY_chime_start="chime_start";
	/**
	 * ��ʱ����ʱ��(��ʱ���ã�ʹ��ҹ��ģʽ��ʱ���)
	 */
	public static String KEY_chime_end="chime_end";
	
	public static final int switch_YES=1;
	public static final int switch_NO=0;
	
	/**
	 * KEYS��Ӧ��ֵ
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
