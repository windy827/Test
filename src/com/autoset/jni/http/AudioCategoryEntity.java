package com.autoset.jni.http;

import java.io.Serializable;

public class AudioCategoryEntity implements Serializable{

	public static final String METHOD="getAudioCategory";
	
	public static final double ID_AudioCategory=1000.0;
	
	public static final String Name_AudioCategory="categoryid";
	
	
	public static final String DOMAINNAME="category";
	public static final String SUBDOMAINNAME="subCategory";
	public static final String TITLE="title";
	public static final String HASSUB="hassub";
	
	String categoryid;
	boolean hasSub;
	String title;
	
	
	public AudioCategoryEntity(String categoryid, boolean hasSub, String title) {
		super();
		this.categoryid = categoryid;
		this.hasSub = hasSub;
		this.title = title;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public boolean isHasSub() {
		return hasSub;
	}
	public void setHasSub(boolean hasSub) {
		this.hasSub = hasSub;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
