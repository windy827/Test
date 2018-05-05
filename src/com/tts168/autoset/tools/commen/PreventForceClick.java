package com.tts168.autoset.tools.commen;

import android.util.Log;

/**
 * 判断点击事件是否为暴力点击
 * @author 袁剑
 *@version 0.9.0.1
 */
public class PreventForceClick {
private static long lastClickTime;
public static boolean isShowToast=true;

public static int TIME_WAITLONGest=2000;
//public static int TIME_WAITLONG=1000;
public static int TIME_WAITSHORT=500;


/**
 * 需要用户传入的是以多少毫秒为界限判定是否为暴力点击
 * 是暴力点击返回true
 * @long howlong 以多少毫秒为界限
 * @return
 */
public static boolean isForceClick(long howlong){
	boolean isForce=false;
	long time=System.currentTimeMillis();
	long timeD=time-lastClickTime;
	Log.d("lastClickTime", lastClickTime+"ms");
	Log.d("time", time+"ms");
	Log.d("TD", timeD+"ms");
	if(timeD>0&&timeD<howlong){
		isForce= true;
	}else{
		isForce= false;
	}
	lastClickTime=time;
	return isForce;
}
}
