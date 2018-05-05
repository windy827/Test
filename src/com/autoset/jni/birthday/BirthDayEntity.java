package com.autoset.jni.birthday;

import java.io.Serializable;

/**
 * 生日设置
 * @author 袁剑
 *
 */
public class BirthDayEntity implements Serializable{
	public static final String DOMAIN_BIRTHDAY="birthday";//通用参数领域
	public static final String BIRTHDAY_ID="ID";
	/**
	 * //生日人名字
	 */
	public static final String BIRTHDAY_WHO="who";
	/**
	 * //日期格式 农历或阳历0=农历，1=阳历
	 */
	public static final String BIRTHDAY_DATE_FORMATE="date_formate";
	/**
	 * 阴历
	 */
	public static final double DATE_FORMATE_Lunar=1;
	/**
	 * 阳历
	 */
	public static final double DATE_FORMATE_Solar=0;
	/**
	 * //日期
	 */
	public static final String BIRTHDAY_DATE_VALUE="data_value";
	
	/**
	 * 唯一标识号，用于增删改查
	 */
	private int id;
	private String who;
	private double date_formate;
	private String data_value;
	/**
	 * KEYS对应的值
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
