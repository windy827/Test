package com.tts168.autoset.activity.welcome;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 获得软件的全文配置文件
 * @author 袁剑
 *
 */
public class SharedConfig {
	Context context;
	SharedPreferences shared;
	public static final String KEY_URL_BUY="buyurl";
	public static final String DEFAULT_HELP="http://www.larkiv.com/alarkhelper/product/larkshop.html";
	
	public static final String KEY_URL_HELP="helpurl";
	public static final String DEFAULT_URL_HELP="http://www.larkiv.com/alarkhelper/help/";
	
	public static final String KEY_URL_ABOUT="abouturl";
	public static final String DEFAULT_URL_ABOUT="http://www.larkiv.com/alarkhelper/product/";
	
	public static final String KEY_URL_XMLY_HELP="xmly_help";
	public static final String DEFAULT_URL_XMLY_HELP="http://www.larkiv.com/alarkhelper/help/xmly.html";
	
	public static final String KEY_URL_WEIXIN="weixin_url";
	public static final String DEFAULT_URL_WEIXIN="http://www.larkiv.com/alarkhelper/weixin/gongzhonghao.html";
	
	public static final String KEY_Productinfo="Productinfo";
	
	/**
	 * 存放OpenId
	 */
	public static final String KEY_Openid="Openid";
	
	/**
	 * 喜马拉雅音乐帮助是否可见
	 */
	public static final String KEY_HELP_WEBMUSIC_VISIABLE="webmusicVisiable";
	public static final boolean DEFAULT_HELP_WEBMUSIC_VISIABLE=true;
	/**
	 * 问答帮助是否可见
	 */
	public static final String KEY_HELP_ANSWER_VISIABLE="ANSWERVisiable";
	public static final boolean DEFAULT_HELP_ANSWER_VISIABLE=true;
	/**
	 * 存放当前的Wifi信息
	 */
	public static final String KEY_WIFI_MAC="wifimac";
	
	public static final String KEY_WIFIBSSID="WIFIBSSID";
	
	
	/**
	 * 存放当前音量强制为最大音量的百分比
	 */
	public static final String KEY_AUDIO_VOL_PERCENT="vol_percent";
	public static final float DEFAUT_AUDIO_VOL_PERCENT=0.8f;
	public SharedConfig(Context context){
		this.context=context;
		shared=context.getSharedPreferences("config", Context.MODE_PRIVATE);
	}

	public SharedPreferences GetConfig(){
		return shared;
	}
	public void ClearConfig(){
		shared.edit().clear().commit();
	}
}
