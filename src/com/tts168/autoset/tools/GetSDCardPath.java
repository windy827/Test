package com.tts168.autoset.tools;

import java.io.File;

import android.os.Environment;
/**
 * �õ�SD��·��
 * @author Ԭ��
 *
 */
public class GetSDCardPath {
	public static String getSDPath(){ 
	       File sdDir = null; 
	       String path="";
	       boolean sdCardExist = Environment.getExternalStorageState()   
	                           .equals(android.os.Environment.MEDIA_MOUNTED);   //�ж�sd���Ƿ���� 
	       if   (sdCardExist)   
	       {                               
	    	   path = Environment.getExternalStorageDirectory().getAbsolutePath();//��ȡ��Ŀ¼ 
	      }   
	       return path.toString(); 
	       
	}
}
