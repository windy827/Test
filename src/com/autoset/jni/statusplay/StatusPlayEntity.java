package com.autoset.jni.statusplay;

import java.io.Serializable;

/**
 * 状态恢复的设置实体类
 * 类型都默认设置为STATUS_DEFAULT_TYPE
 * @author 袁剑
 *
 */
public class StatusPlayEntity implements Serializable{
	public static final String DOMAIN_STATUS_PLAY="optionPlay";//通用参数领域
	
	
	public static final String  STATUS_PLAY_STATUS="status";//状态表示
	public static final String  STATUS_PLAYOBJECT="playobject";//
	public static final String  STATUS_TYPE	="type";//内容类型
	public static final String  STATUS_CONTENT="content";//内容主体
	/**
	 * 成功
	 */
	public static final double STATUS_SUCCESS=1.0;
	/**
	 * 失败
	 */
	public static final double STATUS_FAILD=0.0;

	/**
	 * 类型暂时设置为0
	 */
	public static final double STATUS_DEFAULT_TYPE=0.0;
	
	private double status;
	private double type;
	private String content;
	public StatusPlayEntity(double status, double type, String content) {
		super();
		this.status = status;
		this.type = type;
		this.content = content;
	}
	public double getStatus() {
		return status;
	}
	public void setStatus(double status) {
		this.status = status;
	}
	public double getType() {
		return type;
	}
	public void setType(double type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static void main(String[] args) {
		
	}
	
	
}
