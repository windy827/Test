package com.autoset.jni.play;

import java.io.Serializable;
/**
 * �������ֲ��ŵ�Json��ʽ
 * @author Administrator
 *
 */
public class PlayEntity implements Serializable{

	public static final String METHOD="mediaPlay"; 
	public static final String DOMAINNAME="playlist";
	public static final String PLAY_ITEM_NAME="playitem";
	public static final String PLAY_ITEM_CONTENT="content";
	public static final String PLAY_ITEM_TYPE="type";
	public static final String PLAY_ITEM_ID="id";
	
	/**
	 * ��������
	 */
	public static final int TYPE_PALYSOUND=3;
	/**
	 * ���ű�������
	 */
	public static final int TYPE_HTTP=2;
	/**
	 * ��������
	 */
	public static final int TYPE_TTS=0;
	private String content;
	private double type;
	public PlayEntity(String content, double type) {
		super();
		this.content = content;
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getType() {
		return type;
	}
	public void setType(double type) {
		this.type = type;
	}
	
}
