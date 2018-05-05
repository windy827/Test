package com.tts168.autoset.tools.commen;



import android.content.Context;
import android.media.AudioManager;


/**
 * 情景模式工具类
 * @author 袁剑
 *
 */
public class RingerModelTools {
	public static int VibrateSetting_Ringer=0;
	public static int VibrateSetting_Notification=0;
	/**
	 * 得到当前的情景模式
	 * 
	 */
	
	public static int getCurRingModel(Context c)
    {
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
          //取得手机的初始音量，并初始化进度条
        //int volume=audio.getStreamVolume(AudioManager.STREAM_RING);  //取得初始音量
        //取得初始模式，并分别设置图标
        int mode=audio.getRingerMode();  //取得初始模式
        VibrateSetting_Ringer=audio.getVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER);
        VibrateSetting_Notification=audio.getVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION);
        return mode;
    }
	/**
	 * 根据得到的情景模式的Mode值设置情景模式
	 * 
	 */
	public static void setByRingMode(Context c,int ringMode) {
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
		 int mode=audio.getRingerMode();  //取得初始模式
        audio.setRingerMode(ringMode);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,
        		VibrateSetting_Ringer);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,
        		VibrateSetting_Notification);
       
    }
	/**
	 * 只有铃声没有震动
	 * 
	 */
	public static void setOnlyRing(Context c) {
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
        audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,
                AudioManager.VIBRATE_SETTING_OFF);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,
                AudioManager.VIBRATE_SETTING_OFF);
       
       
    }
	
	/**
	 * 
	 * 即有声音也有振动
	 */
	public static void SetRingAndVibrate(Context c) {
		 AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
	        audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,
	                AudioManager.VIBRATE_SETTING_ON);
	        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,
	                AudioManager.VIBRATE_SETTING_ON);
	    }
	 
	 /**
		 * 
		 * 只有振动
		 */
	public static void etOnlyVibrate(Context c) {
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
	        audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,
	                AudioManager.VIBRATE_SETTING_ON);
	        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,
	                AudioManager.VIBRATE_SETTING_ON);
	    }
	/**
	 * 无声无振动
	 * @param c
	 */
	public static void SetNoRingAndVibrate(Context c) {
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
        audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,
                AudioManager.VIBRATE_SETTING_OFF);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,
                AudioManager.VIBRATE_SETTING_OFF);
       
    }
	
	
	//--是否插入了耳机
	public static boolean isWiredHeadsetOn(Context c){
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
		return  audio.isWiredHeadsetOn();
	}
}
