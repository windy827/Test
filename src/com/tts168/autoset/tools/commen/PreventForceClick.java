package com.tts168.autoset.tools.commen;

import android.util.Log;

/**
 * �жϵ���¼��Ƿ�Ϊ�������
 * @author Ԭ��
 *@version 0.9.0.1
 */
public class PreventForceClick {
private static long lastClickTime;
public static boolean isShowToast=true;

public static int TIME_WAITLONGest=2000;
//public static int TIME_WAITLONG=1000;
public static int TIME_WAITSHORT=500;


/**
 * ��Ҫ�û���������Զ��ٺ���Ϊ�����ж��Ƿ�Ϊ�������
 * �Ǳ����������true
 * @long howlong �Զ��ٺ���Ϊ����
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
