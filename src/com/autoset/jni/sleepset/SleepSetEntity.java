package com.autoset.jni.sleepset;

import java.io.Serializable;
/**
 * 睡眠音乐设置实体类
 * @author 袁剑
 *
 */
public class SleepSetEntity implements Serializable{

    public static final String DOMAIN_NAME="album";
    
	public static final String CATEGORYID="categoryid";
	public static final String PLAYTIME="playtime";
	public static final String WILL_DO="will_do";
	private String categoryid;
	private int playtime;
	private int willdo;
	
	/**
	 * KEYS对应的值
	 */
	private Object[] values;
	public static final String[] KEYS = new String[] { CATEGORYID,
		PLAYTIME, WILL_DO };
	public SleepSetEntity(String categoryid, int playtime, int willdo) {
		super();
		this.categoryid = categoryid;
		this.playtime = playtime;
		this.willdo = willdo;
		values = new Object[] { this.categoryid, this.playtime, this.willdo };
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public int getPlaytime() {
		return playtime;
	}
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}
	public int getWilldo() {
		return willdo;
	}
	public void setWilldo(int willdo) {
		this.willdo = willdo;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	
	
}
