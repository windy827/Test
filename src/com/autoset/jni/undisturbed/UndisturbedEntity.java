package com.autoset.jni.undisturbed;

import java.io.Serializable;

/**
 * ���ſ���
 * @author Ԭ��
 *
 */
public class UndisturbedEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 66666L;
	public static final String DOMAIN_NAME="undisturbed";
	/**
	 * ������ʾ�����ܿ���	0���ر�1���� number
	 */
	public static final String KEY_open_prompt="undisturbed_open_prompt";
	public static final double open_prompt_YES=1.0;
	public static final double open_prompt_NO=0.0;
	/**
	 * ��ʶ��ʾ�����ܿ���	0���ر�1���� number
	 */
	public static final String KEY_open_prompt_a_o="undisturbed_ASRrefuse_prompt";
	public static final double open_prompt_a_o_YES=1.0;
	public static final double open_prompt_a_o_NO=0.0;
	/**
	 *ҹ��ģʽ����	0���ر�1����  number
	 */
	public static final String KEY_open_undisturbed="undisturbed_open";
	public static final double open_undisturbed_YES=1.0;
	public static final double open_undisturbed_NO=0.0;
	
	/**
	 *ҹ��ģʽ��ʼʱ��	��06:00��  string
	 */
	public static final String KEY_undisturbed_time_start="undisturbed_time_start";
	/**
	 * ҹ��ģʽ����ʱ�䡰24:00��  string
	 */
	public static final String KEY_undisturbed_time_end="undisturbed_time_end";
	/**
	 *ҹ��ģʽ�µ��ϵ��ʼ������ֵ��0-9��255��,255��ʾ���ð����������
	 */
	public static final String KEY_undisturbed_InitVolume="undisturbed_InitVolume";
	public static String[]volumeValuesString=new String[]{"ά�ֲ���","1������","2������","3������","4������","5������","6������"};
	public static int[] volumeValuesInteger=new int[]{255,0,1,3,5,7,9};
	
	/**
	 * KEYS��Ӧ��ֵ
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{KEY_open_undisturbed,KEY_open_prompt_a_o,KEY_undisturbed_time_start,KEY_undisturbed_time_end,KEY_undisturbed_InitVolume,KEY_open_prompt};
	
	
	private double open_undisturbed;
	private double open_undisturbed_a_o;
	private String undisturbed_time_start;
	private String undisturbed_time_end;
	private int undisturbed_initVolume;
	private double open_prompt;
	public UndisturbedEntity(double open_undisturbed,double open_undisturbed_a_o,
			String undisturbed_time_start, String undisturbed_time_end, int undisturbed_initVolume,double  open_prompt) {
		super();
		this.open_undisturbed = open_undisturbed;
		this.open_undisturbed_a_o=open_undisturbed_a_o;
		this.undisturbed_time_start = undisturbed_time_start;
		this.undisturbed_time_end = undisturbed_time_end;
		this.undisturbed_initVolume = undisturbed_initVolume;
		this.open_prompt=open_prompt;
		values=new Object[]{this.open_undisturbed,this.open_undisturbed_a_o,this.undisturbed_time_start,this.undisturbed_time_end,this.undisturbed_initVolume,this.open_prompt};
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	public double getOpen_undisturbed() {
		return open_undisturbed;
	}
	public void setOpen_undisturbed(double open_undisturbed) {
		this.open_undisturbed = open_undisturbed;
	}

	public String getUndisturbed_time_start() {
		return undisturbed_time_start;
	}
	public void setUndisturbed_time_start(String undisturbed_time_start) {
		this.undisturbed_time_start = undisturbed_time_start;
	}
	public String getUndisturbed_time_end() {
		return undisturbed_time_end;
	}
	public void setUndisturbed_time_end(String undisturbed_time_end) {
		this.undisturbed_time_end = undisturbed_time_end;
	}
	public int getUndisturbed_initVolume() {
		return undisturbed_initVolume;
	}
	public void setUndisturbed_initVolume(int undisturbed_initVolume) {
		this.undisturbed_initVolume = undisturbed_initVolume;
	} 
	public double getOpen_prompt() {
		return open_prompt;
	}
	public void setOpen_prompt(double open_prompt) {
		this.open_prompt = open_prompt;
	}
	public double getOpen_undisturbed_a_o() {
		return open_undisturbed_a_o;
	}
	public void setOpen_undisturbed_a_o(double open_undisturbed_a_o) {
		this.open_undisturbed_a_o = open_undisturbed_a_o;
	}
	
	
	
	
	

}
