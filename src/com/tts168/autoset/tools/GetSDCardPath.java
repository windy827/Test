package com.tts168.autoset.tools;

import java.io.File;

import android.os.Environment;
/**
 * 得到SD卡路径
 * @author 袁剑
 *
 */
public class GetSDCardPath {
	public static String getSDPath(){ 
	       File sdDir = null; 
	       String path="";
	       boolean sdCardExist = Environment.getExternalStorageState()   
	                           .equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在 
	       if   (sdCardExist)   
	       {                               
	    	   path = Environment.getExternalStorageDirectory().getAbsolutePath();//获取跟目录 
	      }   
	       return path.toString(); 
	       
	}
}
