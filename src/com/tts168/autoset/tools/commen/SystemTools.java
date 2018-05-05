package com.tts168.autoset.tools.commen;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;

/**
 * ����Androidϵͳ�Ĺ�����
 * @author Ԭ��
 *
 */
public class SystemTools {

	/**
	 * �õ���ǰ��ϵͳSDK�汾�ŵ�ֵ
	 * @return ����
	 */
	public static int VersionOfSDK(){
		int version = Integer.valueOf(android.os.Build.VERSION.SDK);
		return version;
	}
	
	
	/**
     * 
     *  @Description    : ��������ĳ����Ƿ�������
     *  @Method_Name    : isRunningApp
     *  @param context ������
     *  @param packageName �жϳ���İ���
     *  @return ������ص�Ȩ��
     *      <uses-permission android:name="android.permission.GET_TASKS"> 
     *  @return         : boolean
     *  @Creation Date  : 2014-10-31 ����1:14:15 
     *  @version        : v1.00
     *  @Author         : JiaBin
      
     *  @Update Date    : 
     *  @Update Author  : yj
     */
    public static boolean isRunningApp(Context context, String packageName) {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(100);
        for (RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
                isAppRunning = true;
                // find it, break
                break;
            }
        }
        return isAppRunning;
    }
}
