package com.tts168.autoset.tools.commen;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 *@author 袁剑
 *@version 0.9.0.1
 * @see SharedPrefence，Editor：
	 * 记录是否是第一次学习听写（“isFirst”）
	 * 记录上次登录成功的Wifi名和密码（SSID,PassWord）
 记录发送的日期（“SendDate”）
记录发送的时间（“SendTime”）
记录版本（“Version”）
记录短按城市(ShortPro)
记录长按城市(LongPro)
记录双击城市(DoublePro)
记录短按城市(ShortCity)
记录长按城市(LongCity)
记录双击城市(DoubleCity)

记录短按笑话(ShortLaugh)
记录长按笑话(LongLaugh)
记录双击笑话(DoubleLaugh)

记录整点报时（Full）
记录半点报时（Half）

记录无对话睡眠时间（Sleep）
记录Wifi自动获取时间（AutoWifi）
记录Crc16校验码（Crc16）
	 */
 

public class SharedPrefenceSetTools {

	public static final String SendDate="SendDate";
	public static final String SendTime="SendTime";
	
	public static final String ShortPro="ShortPro";
	public static final String LongPro="LongPro";
	public static final String DoublePro="DoublePro";
	
	public static final String ShortCity="ShortCity";
	public static final String LongCity="LongCity";
	public static final String DoubleCity="DoubleCity";
	
	public static final String ShortLaugh="ShortLaugh";
	public static final String LongLaugh="LongLaugh";
	public static final String DoubleLaugh="DoubleLaugh";
	public static final String SSID="SSID";
	public static final String PassWord="PassWord";
	public static final String Version="Version";
	public static final String Full="Full";
	public static final String Half="Half";
	public static final String Sleep="Sleep";//Default 0
	public static final String AutoWifi="AutoWifi";
	public static final String Crc16="Crc16";
	
	

	/**
	 * 记录生日提醒的提前天数
	 */
	public static final String AheadDAYS="AheadDAYS";
	public static final int DEFAULT_AheadDAYS=0;
	
	/**
	 * 记录节日提醒的提前天数
	 */
	public static final String AheadDAYS_Festive="AheadDAYS_Festive";
	public static final int DEFAULT_AheadDAYS_Festive=0;
	
	/**
	 * 记录节日提醒第一次播报的时间
	 */
	public static final String PLAYTIME1_Festive="PLAYTIME1_Festive";
	public static final String DEFAULT_PLAYTIME1_Festive="0700";
	/**
	 * 记录节日提醒第二次播报的时间
	 */
	public static final String PLAYTIME2_Festive="PLAYTIME2_Festive";
	public static final String DEFAULT_PLAYTIME2_Festive="1230";
	/**
	 * 记录节日提醒第三次播报的时间
	 */
	public static final String PLAYTIME3_Festive="PLAYTIME3_Festive";
	public static final String DEFAULT_PLAYTIME3_Festive="1900";
	
	
	/**
	 * 记录生日提醒第一次播报的时间
	 */
	public static final String PLAYTIME1="PLAYTIME1";
	public static final String DEFAULT_PLAYTIME1="0700";
	/**
	 * 记录生日提醒第二次播报的时间
	 */
	public static final String PLAYTIME2="PLAYTIME2";
	public static final String DEFAULT_PLAYTIME2="1230";
	/**
	 * 记录生日提醒第三次播报的时间
	 */
	public static final String PLAYTIME3="PLAYTIME3";
	public static final String DEFAULT_PLAYTIME3="1900";
	
	/**
	 * 记录播报开始的时间
	 */
	public static final String STARTTIME="STARTTIME";
	public static final String DEFAULT_STARTTIME="0700";
	
	/**
	 * 记录播报结束的时间
	 */
	public static final String ENDTIME="ENDTIME";
	public static final String DEFAULT_ENDTIME="2100";
	
	/**
	 * 发音人设置（enunciators）
	 */
	public static final String enunciators="enunciators";//发音人设置
	/**
	 * 默认发音人晓林（Default_enunciator）
	 */
	public static final String Default_enunciator="晓玲";//默认发音人晓林
	public static SharedPreferences sp;
	public static Editor ed;
	public static final String SP_Flag="SETTING";

}
