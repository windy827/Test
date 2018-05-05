package com.tts168.autoset.tools.others.time;
/**
 * ���ڲ���������
 * @author Ԭ��
 *
 */
public class DataTools {
/**
 * ʹ��ǰ��ȷ���������Ϊ"2"�����ֵ��ַ���
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
	 * ʹ��ǰ��ȷ���������Ϊ"2"�����ֵ��ַ������"000"��ʽ
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
	 * ʹ��ǰ��ȷ���������Ϊ"2"�����ֵ��ַ�����ɺ���n��"0"��ʽ
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
