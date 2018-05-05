package com.autoset.jni.http;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 发送Http请求的Json实体类
 * @author 袁剑
 *
 */
public class HttpEntity implements Serializable{

	public static final String METHOD_getAudioCategory="getAudioCategory";
	public static final String METHOD_getAudioList="getAudioList";
	public static final String paramsID_NEWS_getAudioList="001001";
	public static final String paramsID_MUSIC_getAudioMain="002";
	public static final String paramsID_MUSIC_getAudioList="002001001";
	String method;
	int id;
	String paramsidValues;
	ArrayList<String[]> al_paramsidValues;
	String []paramsidsValues;
	
	public HttpEntity(String method, int id, String paramsidValues) {
		super();
		this.method = method;
		this.id = id;
		this.paramsidValues = paramsidValues;
	}
	public HttpEntity(String method, int id, ArrayList<String[]> al_paramsidValues) {
		super();
		this.method = method;
		this.id = id;
		this.al_paramsidValues = al_paramsidValues;
	}
	public HttpEntity(String method, int id, String []paramsidsValues) {
		super();
		this.method = method;
		this.id = id;
		this.paramsidsValues = paramsidsValues;
	}
	
	
	public String[] getParamsidsValues() {
		return paramsidsValues;
	}
	public void setParamsids(String[] paramsidsValues) {
		this.paramsidsValues = paramsidsValues;
	}
	
	
	public ArrayList<String[]> getAl_paramsidValues() {
		return al_paramsidValues;
	}
	public void setAl_paramsidValues(ArrayList<String[]> al_paramsidValues) {
		this.al_paramsidValues = al_paramsidValues;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setParamsidsValues(String[] paramsidsValues) {
		this.paramsidsValues = paramsidsValues;
	}
	public String getParamsidValues() {
		return paramsidValues;
	}
	public void setParamsidValues(String paramsidValues) {
		this.paramsidValues = paramsidValues;
	}
	
	
	
}
