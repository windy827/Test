package com.autoset.jni.statusplay;

import java.io.Serializable;

/**
 * ״̬�ָ�������ʵ����
 * ���Ͷ�Ĭ������ΪSTATUS_DEFAULT_TYPE
 * @author Ԭ��
 *
 */
public class StatusPlayEntity implements Serializable{
	public static final String DOMAIN_STATUS_PLAY="optionPlay";//ͨ�ò�������
	
	
	public static final String  STATUS_PLAY_STATUS="status";//״̬��ʾ
	public static final String  STATUS_PLAYOBJECT="playobject";//
	public static final String  STATUS_TYPE	="type";//��������
	public static final String  STATUS_CONTENT="content";//��������
	/**
	 * �ɹ�
	 */
	public static final double STATUS_SUCCESS=1.0;
	/**
	 * ʧ��
	 */
	public static final double STATUS_FAILD=0.0;

	/**
	 * ������ʱ����Ϊ0
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
