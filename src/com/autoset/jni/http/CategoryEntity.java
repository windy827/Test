package com.autoset.jni.http;

import java.io.Serializable;

public class CategoryEntity implements Serializable{

	public static final String METHOD="getAudioCategory";
	public static final String DOMAINNAME="category";
	public static final String SUBDOMAINNAME="subCategory";
	
	public static final double ID_AudioCategory=1000.0;
	
	public static final String AudioCategoryID="categoryid";
	public static final String TITLE="title";
	public static final String HASSUB="hassub";
	
	public static final String PAGE="page";
	public static final String PERPAGE="perpage";
	public static final String[]paramsNames=new String[]{AudioCategoryID,PAGE,PERPAGE};
	
	String categoryid;
	String icon;
	boolean hasSub;
	String title;
	public CategoryEntity(String categoryid, String icon, boolean hasSub,
			String title) {
		super();
		this.categoryid = categoryid;
		this.icon = icon;
		this.hasSub = hasSub;
		this.title = title;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
