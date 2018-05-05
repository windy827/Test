package com.autoset.json;

import java.io.UnsupportedEncodingException;

import com.tts168.autoset.tools.Tools;

public class MyTools {
/**
 * Ϊbyte��������ټ���һ��0���ܹ�׼ȷ��������
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
	 * �����ֽڷ����ַ��������ֽ�������������0;
	 * @param deal �������ֽ�����
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
