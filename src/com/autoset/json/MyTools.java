package com.autoset.json;

import java.io.UnsupportedEncodingException;

import com.tts168.autoset.tools.Tools;

public class MyTools {
/**
 * 为byte数组最后再加上一个0，能够准确处理数据
 * @param primaryData
 * @return
 */
	public static byte[]getJNIUseByte(String primaryData){
		byte[]result=null;
		try {
			result=primaryData.getBytes(Tools.TEXT_FORMAT);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte []newresult=new byte[result.length+2];
		for(int i=0;i<result.length;i++){
			newresult[i]=result[i];
		}
		
		return newresult;
	}
	
	/**
	 * 根据字节返回字符串，在字节数组后面有添加0;
	 * @param deal 待处理字节数组
	 * @return
	 */
	public static String byteArray2String(byte[]deal){
		String result="";
		byte[]re=new byte[deal.length+1];
			for(int i=0;i<deal.length;i++){
				re[i]=deal[i];
			}	
		try {
			result=new String(re,Tools.TEXT_FORMAT).trim();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static String getDoubQuot(String str){
		String result="\""+str+"\"";
		return result;
	}
}
