package com.autoset.jni.play;

import java.io.Serializable;

public class PlayItemEntity implements Serializable{

	public static final String NAME="playitem";
	public static final String CONTENT="content";
	public static final String TYPE="type";
	
	public static final int TYPE_LOCAL=1;
	public static final int TYPE_WEB=2;
	
	
	
	//public static final int ID=10001;//表示是试听音乐声音
	
	private int type;
	private String content;
	/**
	 * KEYS对应的值
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{TYPE,CONTENT};
	public PlayItemEntity(int type, String content) {
		super();
		this.type = type;
		this.content = content;
		
		values=new Object[]{this.type,this.content};
	}
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
