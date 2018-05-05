package com.tts168.autoset.tools.commen;


import com.larkiv.larksmart7618.R;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
/**
 * ����汾��Ϣ�Ĺ�����
 * @author Ԭ��
 *
 */
public class VersionTools {
	
	  /**
	   * ��ȡ����汾��(������)
	   * @return ��ǰӦ�õĴ���汾��
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
	   * ��ȡ�汾�š�û�����appName,ע������mainifest.xml��Ӧ�����ݡ�
	   * @return ��ǰӦ�õİ汾����
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
	   * ���ݰ汾���Ƶõ�����Ҫ��ĸ�ʽ�İ汾 ��01.04���104
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
	   * ��ȡ�汾��
	   * @return ��ǰӦ�õİ汾����
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
