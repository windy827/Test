package com.autoset.jni.presskey;

import java.io.Serializable;

/**
 * 按键设置实体类
 * @author 袁剑
 *
 */
public class PressKeyEntity implements Serializable{
	public static final String DOMAIN_KEYBOARD="keyboard";//Wifi领域
	
	public static final String KEYBOARD_KEY_ID="key_id";//按键id
	public static final String KEYBOARD_NAME="name";//按键名称
	public static final String KEYBOARD_TYPE="type";//按键类型
	public static final String KEYBOARD_SCLICK="press";//短按动作
	public static final String KEYBOARD_DCLICK="dclick";//双击动作
	public static final String KEYBOARD_LCLICK="lpress";//长按动作
	/**
	 * 天气键
	 */
	public static final double KEY_ID_WEATHER=0.0;
	public static final String NAME_WEATHER="天气";
	/**
	 * 新闻键
	 */
	public static final double KEY_ID_NEWS=0.0;
	public static final String NAME_NEWS="新闻";
	/**
	 * 日历键
	 */
	public static final double KEY_ID_DATE=0.0; 
	public static final String NAME_DATE="日历";
	/**
	 * 笑话键
	 */
	public static final double KEY_ID_LAUGH=0.0;
	public static final String NAME_LAUGH="笑话";
	/**
	 * 功能1键【增大音量】
	 */
	public static final double KEY_ID_FUN1=0.0;
	public static final String NAME_FUN1="功能";
	/**
	 * 功能2键【减小音量】
	 */
	public static final double KEY_ID_FUN2=0.0;
	public static final String NAME_FUN2="功能";
	
	
	private double key_id;
	private String name;
	private String type;
	
	private String sclick;
	private String dclick;
	private String lclick;
	/**
	 * KEYS对应的值
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{KEYBOARD_KEY_ID,KEYBOARD_NAME,KEYBOARD_TYPE,KEYBOARD_SCLICK,KEYBOARD_DCLICK,KEYBOARD_LCLICK};
	public PressKeyEntity(double key_id, String name, String type,
			String sclick, String dclick, String lclick) {
		super();
		this.key_id = key_id;
		this.name = name;
		this.type = type;
		this.sclick = sclick;
		this.dclick = dclick;
		this.lclick = lclick;
		values=new Object[]{this.key_id,this.name,this.type,this.sclick,this.dclick,this.lclick};
	}
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
	}
	public double getKey_id() {
		return key_id;
	}
	public void setKey_id(double key_id) {
		this.key_id = key_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSclick() {
		return sclick;
	}
	public void setSclick(String sclick) {
		this.sclick = sclick;
	}
	public String getDclick() {
		return dclick;
	}
	public void setDclick(String dclick) {
		this.dclick = dclick;
	}
	public String getLclick() {
		return lclick;
	}
	public void setLclick(String lclick) {
		this.lclick = lclick;
	}
	
	
}
