package com.autoset.jni.http;

import java.io.Serializable;

public class AudioEntity implements Serializable{

	public static final String METHOD="getAudioList";
	public static final String DOMAINNAME="audio";
	public static final double ID_AudioCategory=1000.0;
	
	
	public static final String DURATION="duration";
	public static final String ICON="icon";
	public static final String TITLE="title";
	public static final String URL="url";
	
	double duration;
	String icon;
	String title;
	String url;
	public AudioEntity(double duration, String icon, String title,
			String url) {
		super();
		this.duration = duration;
		this.icon = icon;
		this.title = title;
		this.url = url;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	
}
