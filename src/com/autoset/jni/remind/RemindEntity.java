package com.autoset.jni.remind;

import java.io.Serializable;

/**
 * 备忘设置
 * @author 袁剑
 *
 */
public class RemindEntity implements Serializable{
	public static final String DOMAIN_REMIND="remind";//通用参数领域
	
	public static final String REMIND_ID	="ID";
	/**
	 * //明日日期
	 */
	public static final String REMIND_DATE	="date";
	public static final String ALARM_IS_VALID="is_valid";//是否开启（使能）
	/**
	 * //备忘内容
	 */
	public static final String REMIND_CONTENT	="content";
	
	/**
	 * 唯一标识号，用于增删改查
	 */
	private int id;
	private double is_valid;
	private String date;
	private String content;
	/**
	 * KEYS对应的值
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
