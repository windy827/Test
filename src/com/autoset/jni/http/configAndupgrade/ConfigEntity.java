package com.autoset.jni.http.configAndupgrade;

import java.io.Serializable;
/**
 * ������Ϣ������Ҫ���ʵ�URL��ַ��
 * 
 * @author Ԭ��
 *
 */
public class ConfigEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME_CONFIG="config";
	//public static final String NAME_UPGRADE="config";[���ݲ�д���µ�����]
	public static final String URL_BUY="buy_url";
	public static final String URL_HELP="help_url";
	public static final String URL_PRODUCT="product_url";
	public static final String URL_WEIXIN="weixin_url";
	public static final String URL_WEBMUSIC_XMLY_HELP="xmly_help_url";
	
	/**
	 * �����ֹ���ַ
	 */
	private String urlbuy;
	/**
	 * �ʴ������ַ
	 */
	private String urlhelp;
	/**
	 * ���ڲ�Ʒ��ַ
	 */
	private String urlproduct;
	/**
	 * ΢�Ź��ں�
	 */
	private String urlweixin;
	/**
	 * ϲ�����Ű�����ַ
	 */
	private String xmly_help;
	public ConfigEntity(String urlbuy, String urlhelp,
			String urlproduct,String xmly_help,String urlweixin) {
		super();
		this.urlbuy = urlbuy;
		this.urlhelp = urlhelp;
		this.urlproduct = urlproduct;
		this.xmly_help=xmly_help;
		this.urlweixin=urlweixin;
	}
	public String getUrlbuy() {
		return urlbuy;
	}
	public void setUrlbuy(String urlbuy) {
		this.urlbuy = urlbuy;
	}
	public String getUrlhelp() {
		return urlhelp;
	}
	public void setUrlhelp(String urlhelp) {
		this.urlhelp = urlhelp;
	}
	public String getUrlproduct() {
		return urlproduct;
	}
	public void setUrlproduct(String urlproduct) {
		this.urlproduct = urlproduct;
	}
	public String getXmly_help() {
		return xmly_help;
	}
	public void setXmly_help(String xmly_help) {
		this.xmly_help = xmly_help;
	}
	public String getUrlweixin() {
		return urlweixin;
	}
	public void setUrlweixin(String urlweixin) {
		this.urlweixin = urlweixin;
	}
	
	
	
}
