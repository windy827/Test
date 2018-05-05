package com.tts168.autoset.tools.others.time;
/**
 * 日期操作工具类
 * @author 袁剑
 *
 */
public class DataTools {
/**
 * 使用前请确保你的输入为"2"等数字的字符串
 * @param data
 * @return
 */
	public static String getStrFormat2StrDate(String data){
		String result="";
		if(data.toCharArray().length>=2){
			result=data;
		}else{
			result=String.format("%02d",Integer.parseInt(data));
		}
		return result;
	}
	
	/**
	 * 使用前请确保你的输入为"2"等数字的字符串变成"000"格式
	 * @param data
	 * @return
	 */
	public static String getStrFormat3StrDate(String data){
		String result="";
		if(data.toCharArray().length>=3){
			result=data;
		}else{
			result=String.format("%03d",Integer.parseInt(data));
		}
		return result;
	}
	
	
	/**
	 * 使用前请确保你的输入为"2"等数字的字符串变成含有n个"0"格式
	 * @param data
	 * @return
	 */
	public static String getStrFormatStrDate(String data,int n){
		String result="";
		if(data.toCharArray().length>=n){
			result=data;
		}else{
			result=String.format("%0"+n+"d",Integer.parseInt(data));
		}
		return result;
	}
}
