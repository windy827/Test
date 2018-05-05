package com.autoset.jni.birthday;

import java.io.Serializable;

/**
 * ��������
 * @author Ԭ��
 *
 */
public class BirthDayEntity implements Serializable{
	public static final String DOMAIN_BIRTHDAY="birthday";//ͨ�ò�������
	public static final String BIRTHDAY_ID="ID";
	/**
	 * //����������
	 */
	public static final String BIRTHDAY_WHO="who";
	/**
	 * //���ڸ�ʽ ũ��������0=ũ����1=����
	 */
	public static final String BIRTHDAY_DATE_FORMATE="date_formate";
	/**
	 * ����
	 */
	public static final double DATE_FORMATE_Lunar=1;
	/**
	 * ����
	 */
	public static final double DATE_FORMATE_Solar=0;
	/**
	 * //����
	 */
	public static final String BIRTHDAY_DATE_VALUE="data_value";
	
	/**
	 * Ψһ��ʶ�ţ�������ɾ�Ĳ�
	 */
	private int id;
	private String who;
	private double date_formate;
	private String data_value;
	/**
	 * KEYS��Ӧ��ֵ
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{BIRTHDAY_ID,BIRTHDAY_WHO,BIRTHDAY_DATE_FORMATE,BIRTHDAY_DATE_VALUE};
	public BirthDayEntity(int id, String who, double date_formate,
			String data_value) {
		super();
		this.id = id;
		this.who = who;
		this.date_formate = date_formate;
		this.data_value = data_value;
		values=new Object[]{this.id,this.who,this.date_formate,this.data_value};
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
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public double getDate_formate() {
		return date_formate;
	}
	public void setDate_formate(double date_formate) {
		this.date_formate = date_formate;
	}
	public String getData_value() {
		return data_value;
	}
	public void setData_value(String data_value) {
		this.data_value = data_value;
	}
	
	
	
}
