package com.autoset.jni.getdomain;

import java.io.Serializable;
/**
 * �õ�������ʵ���ࡾ�����������Լ����������GETDATA�õ��Ļ���SETDATA�õ��ġ�
 * @author Ԭ��
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
