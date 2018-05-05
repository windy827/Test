package com.tts168.autoset.tools.others.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

/**
 * 得到和设置当前的日期/时间格式为yyyy-MM-dd/HH:mm:ss
 * @author 袁剑
 *
 */
public class GetAndSetTime {
/**
 * 得到当前日期
 */
public static String getData(){
    	//long now = android.os.SystemClock.uptimeMillis();
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	Date d1=new Date();
    	String t1=format.format(d1);
    	return t1;
    }

/**
 * 得到下一日的日期
 * @return
 */
public static String getNextDayDate(){
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

     Calendar lastDate = Calendar.getInstance();
    
     lastDate.add(Calendar.DAY_OF_MONTH, +1);//获取当前日期的下一天同理,-1可以获取前一天
     String str = sdf.format(lastDate.getTime());
     return str;
}
/**
 * 得到当前时间
 */
public static String setTime(){
	//long now = android.os.SystemClock.uptimeMillis();
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	Date d1=new Date();
	String t1=format.format(d1);
	return t1;
}
}
