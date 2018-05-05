package com.tts168.autoset.tools.commen;


import com.larkiv.larksmart7618.R;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
/**
 * 软件版本信息的工具类
 * @author 袁剑
 *
 */
public class VersionTools {
	
	  /**
	   * 获取代码版本号(更新用)
	   * @return 当前应用的代码版本号
	   */
	  public static int versionCode(Context c) {
	      try {
	          PackageManager manager = c.getPackageManager();
	          PackageInfo info = manager.getPackageInfo(c.getPackageName(), 0);
	          int version = info.versionCode;
	         return version;
	     } catch (Exception e) {
	         e.printStackTrace();
	         return 1;
	     }
	 }
	
	  /**
	   * 获取版本号【没有添加appName,注册里面mainifest.xml对应的内容】
	   * @return 当前应用的版本名称
	   */
	  public static String getVersionNameWithOutAppName(Context c) {
	      try {
	          PackageManager manager = c.getPackageManager();
	          PackageInfo info = manager.getPackageInfo(c.getPackageName(), 0);
	          String version = info.versionName;
	         return version;
	     } catch (Exception e) {
	         e.printStackTrace();
	         return c.getString(R.string.app_name);
	     }
	 }
	  
	  /**
	   * 根据版本名称得到我们要求的格式的版本 如01.04变成104
	   * @param c
	   * @return
	   */
	  public static int getVersionCodeByVersionName(Context c){
		  String vername=getVersionNameWithOutAppName(c);
		  String Vcode=vername.replace(".", "");
		  int code=Integer.parseInt(Vcode);
		  return code;
	  }
	 /**
	   * 获取版本号
	   * @return 当前应用的版本名称
	   */
	  public static String getVersionName(Context c) {
	      try {
	          PackageManager manager = c.getPackageManager();
	          PackageInfo info = manager.getPackageInfo(c.getPackageName(), 0);
	          String version = info.versionName;
	         return c.getString(R.string.app_name) + version;
	     } catch (Exception e) {
	         e.printStackTrace();
	         return c.getString(R.string.app_name);
	     }
	 }
	  
	
}
