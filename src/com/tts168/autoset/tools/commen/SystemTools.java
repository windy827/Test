package com.tts168.autoset.tools.commen;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;

/**
 * 关于Android系统的工具类
 * @author 袁剑
 *
 */
public class SystemTools {

	/**
	 * 得到当前的系统SDK版本号的值
	 * @return 整型
	 */
	public static int VersionOfSDK(){
		int version = Integer.valueOf(android.os.Build.VERSION.SDK);
		return version;
	}
	
	
	/**
     * 
     *  @Description    : 这个包名的程序是否在运行
     *  @Method_Name    : isRunningApp
     *  @param context 上下文
     *  @param packageName 判断程序的包名
     *  @return 必须加载的权限
     *      <uses-permission android:name="android.permission.GET_TASKS"> 
     *  @return         : boolean
     *  @Creation Date  : 2014-10-31 下午1:14:15 
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
