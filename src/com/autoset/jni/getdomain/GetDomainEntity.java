package com.autoset.jni.getdomain;

import java.io.Serializable;
/**
 * 得到域名的实体类【包含域名称以及这个域名是GETDATA得到的还是SETDATA得到的】
 * @author 袁剑
 *
 */
public class GetDomainEntity implements Serializable{

	private String domainName="";
	private boolean isFromGetdata=true;
	public GetDomainEntity(String domainName, boolean isFromGetdata) {
		super();
		this.domainName = domainName;
		this.isFromGetdata = isFromGetdata;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public boolean isFromGetdata() {
		return isFromGetdata;
	}
	public void setFromGetdata(boolean isFromGetdata) {
		this.isFromGetdata = isFromGetdata;
	}
	
}
