package com.tts168.autoset.tools.others.converopt;

import java.io.UnsupportedEncodingException;

import com.tts168.autoset.tools.Tools;

public class StringConvertTools {
	public static final String FORMAT_GBK="gbk";
	public static final String FORMAT_UTF8="utf8";
	/**
	 * ���ַ���ǿ��ת����UTF8d��ʽ���ַ���
	 * @param primaryData
	 * @return
	 */
	public static String getUTF8String(String primaryData){
		String result="";
		byte[]temps=getUTF8Bytes(primaryData);
		result=byteArray2String(temps);
		return result;
	} 
	/**
	 * ����UTF8���ַ�����Ӧ���ֽ�����
	 * @param primaryData
	 * @return
	 */
		public static byte[]getUTF8Bytes(String primaryData){
			byte[]result=null;
			try {
				result=primaryData.getBytes(FORMAT_UTF8);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
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
				result=new String(re,FORMAT_UTF8).trim();
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
