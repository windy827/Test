package com.autoset.jni.remind;

import java.io.Serializable;

/**
 * ��������
 * @author Ԭ��
 *
 */
public class RemindEntity implements Serializable{
	public static final String DOMAIN_REMIND="remind";//ͨ�ò�������
	
	public static final String REMIND_ID	="ID";
	/**
	 * //��������
	 */
	public static final String REMIND_DATE	="date";
	public static final String ALARM_IS_VALID="is_valid";//�Ƿ�����ʹ�ܣ�
	/**
	 * //��������
	 */
	public static final String REMIND_CONTENT	="content";
	
	/**
	 * Ψһ��ʶ�ţ�������ɾ�Ĳ�
	 */
	private int id;
	private double is_valid;
	private String date;
	private String content;
	/**
	 * KEYS��Ӧ��ֵ
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{REMIND_ID,ALARM_IS_VALID,REMIND_DATE,REMIND_CONTENT};
	public RemindEntity(int id, double is_valid,String date, String content) {
		super();
		this.id = id;
		this.is_valid=is_valid;
		this.date = date;
		this.content = content;
		
		values=new Object[]{this.id,this.is_valid,this.date,this.content};
	}
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(double is_valid) {
		this.is_valid = is_valid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
