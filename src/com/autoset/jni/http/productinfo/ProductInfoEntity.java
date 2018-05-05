package com.autoset.jni.http.productinfo;

import java.io.Serializable;
/**
 * ≤˙∆∑–≈œ¢
 * @author ‘¨Ω£
 *
 */
public class ProductInfoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 123L;
	public static final String METHOD="getProductInfo";
	public static final String DOMAINNAME="product";
	
	public static final String ID_product_name="productid";
	public static final String Guideimage_name="guideimage";
	public static final String Title_product_name="title";
	public static final String Description_product_name="description";
	public static final String Guidetype_name="guidetype";
	public static final String ICON_product_name="icon";
	public static final String URL_product_name="url";
	public static final String LASTUPDATETIME_product_name="lastupdatetime";
	
	
	
	public static final double ID_AudioCategory=301.0;
	public static final int GuideType_Doubleclick=1;//À´ª˜≈‰÷√
	public static final double GuideType_Voice=2;//”Ô“Ù≈‰÷√
	
	String productid;
	String guideimage;
	int guidetype;
	String title;
	String description;
	String icon;
	String url;
	String lastupdatetime;
	public ProductInfoEntity(String productid,String guideimage,int guidetype, String title,
			String description, String icon, String url,String lastupdatetime) {
		super();
		this.productid = productid;
		this.guideimage=guideimage;
		this.guidetype=guidetype;
		this.title = title;
		this.description = description;
		this.icon = icon;
		this.url = url;
		this.lastupdatetime=lastupdatetime;
	}
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	
	public String getGuideimage() {
		return guideimage;
	}
	public void setGuideimage(String guideimage) {
		this.guideimage = guideimage;
	}
	
	public int getGuidetype() {
		return guidetype;
	}

	public void setGuidetype(int guidetype) {
		this.guidetype = guidetype;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	
	
	


}
