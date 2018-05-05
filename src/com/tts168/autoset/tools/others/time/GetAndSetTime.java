package com.tts168.autoset.tools.others.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

/**
 * �õ������õ�ǰ������/ʱ���ʽΪyyyy-MM-dd/HH:mm:ss
 * @author Ԭ��
 *
 */
public class GetAndSetTime {
/**
 * �õ���ǰ����
 */
public static String getData(){
    	//long now = android.os.SystemClock.uptimeMillis();
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	Date d1=new Date();
    	String t1=format.format(d1);
    	return t1;
    }

/**
 * �õ���һ�յ�����
 * @return
 */
public static String getNextDayDate(){
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

     Calendar lastDate = Calendar.getInstance();
    
     lastDate.add(Calendar.DAY_OF_MONTH, +1);//��ȡ��ǰ���ڵ���һ��ͬ��,-1���Ի�ȡǰһ��
     String str = sdf.format(lastDate.getTime());
     return str;
}
/**
 * �õ���ǰʱ��
 */
public static String setTime(){
	//long now = android.os.SystemClock.uptimeMillis();
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	Date d1=new Date();
	String t1=format.format(d1);
	return t1;
}
}
