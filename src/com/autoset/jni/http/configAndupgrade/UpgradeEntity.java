package com.autoset.jni.http.configAndupgrade;

import java.io.Serializable;
/**
 * 更新实体类，升级信息包含版本号以及升级下载最新APP的网址
 * @author 袁剑
 *
 */
public class UpgradeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9090L;

	public static final String NAME_Upgrade="upgrade";
	public static final String NAME_ConfigId="configid";
	public static final String NAME_Download="download";
	public static final String NAME_FName="fname";
	public static final String NAME_Url="url";
	
	public static final String NAME_Reboot="reboot";
	public static final String NAME_Description="description";
	public static final String NAME_Vercode="ver_code";
	public static final String NAME_Vername="ver_name";
	public static final String NAME_FORCEUPGRADE="forceupgrase";
	/**
	 * 强制更新
	 */
	public static final int FORCEUPGRADE_YES=1;
	/**
	 * 不强制更新
	 */
	public static final int FORCEUPGRADE_NO=0;
	
	String configid;
	String description;
	String fname;
	String url;
	int reboot;
	String ver_code;
	String ver_name;
	int forceupgrase;
	public UpgradeEntity(String configid, String description,String fname, String url, int reboot,
			String ver_code, String ver_name,int forceupgrase) {
		super();
		this.configid = configid;
		this.description=description;
		this.fname = fname;
		this.url = url;
		this.reboot = reboot;
		this.ver_code = ver_code;
		this.ver_name = ver_name;
		this.forceupgrase=forceupgrase;
	}
	public String getConfigid() {
		return configid;
	}
	public void setConfigid(String configid) {
		this.configid = configid;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getReboot() {
		return reboot;
	}
	public void setReboot(int reboot) {
		this.reboot = reboot;
	}
	public String getVer_code() {
		return ver_code;
	}
	public void setVer_code(String ver_code) {
		this.ver_code = ver_code;
	}
	public String getVer_name() {
		return ver_name;
	}
	public void setVer_name(String ver_name) {
		this.ver_name = ver_name;
	}
	public int getForceupgrase() {
		return forceupgrase;
	}
	public void setForceupgrase(int forceupgrase) {
		this.forceupgrase = forceupgrase;
	}
	
	
	
}
