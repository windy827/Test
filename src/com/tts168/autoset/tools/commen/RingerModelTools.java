package com.tts168.autoset.tools.commen;



import android.content.Context;
import android.media.AudioManager;


/**
 * �龰ģʽ������
 * @author Ԭ��
 *
 */
public class RingerModelTools {
	public static int VibrateSetting_Ringer=0;
	public static int VibrateSetting_Notification=0;
	/**
	 * �õ���ǰ���龰ģʽ
	 * 
	 */
	
	public static int getCurRingModel(Context c)
    {
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
          //ȡ���ֻ��ĳ�ʼ����������ʼ��������
        //int volume=audio.getStreamVolume(AudioManager.STREAM_RING);  //ȡ�ó�ʼ����
        //ȡ�ó�ʼģʽ�����ֱ�����ͼ��
        int mode=audio.getRingerMode();  //ȡ�ó�ʼģʽ
        VibrateSetting_Ringer=audio.getVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER);
        VibrateSetting_Notification=audio.getVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION);
        return mode;
    }
	/**
	 * ���ݵõ����龰ģʽ��Modeֵ�����龰ģʽ
	 * 
	 */
	public static void setByRingMode(Context c,int ringMode) {
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
		 int mode=audio.getRingerMode();  //ȡ�ó�ʼģʽ
        audio.setRingerMode(ringMode);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,
        		VibrateSetting_Ringer);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,
        		VibrateSetting_Notification);
       
    }
	/**
	 * ֻ������û����
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
	 * ��������Ҳ����
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
		 * ֻ����
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
	 * ��������
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
	
	
	//--�Ƿ�����˶���
	public static boolean isWiredHeadsetOn(Context c){
		AudioManager audio=(AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
		return  audio.isWiredHeadsetOn();
	}
}
