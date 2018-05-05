package com.tts168.autoset.tools.commen;

import android.util.Log;

/**
 * ��ӡ���� ͳһ���������Ժ�ȥ����ӡ
 * 
 * @author Ԭ��
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
