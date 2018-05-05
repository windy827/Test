package com.tts168.autoset.tools.commen;

import android.util.Log;

/**
 * 打印工具 统一管理，方便以后去掉打印
 * 
 * @author 袁剑
 * 
 */
public class MyLogTools {

	public static final boolean needLog = true;

	public static void d(String tag, String msg) {
		if (needLog) {
			Log.d(tag, msg);
		}
		
	}

	public static void e(String tag, String msg) {
		if (needLog) {
			Log.e(tag, msg);
		}
		
	}

	public static void i(String tag, String msg) {
		if (needLog) {
			Log.i(tag, msg);
		}
		
	}

	public static void v(String tag, String msg) {
		if (needLog) {
			Log.v(tag, msg);
		}
		
	}
}
