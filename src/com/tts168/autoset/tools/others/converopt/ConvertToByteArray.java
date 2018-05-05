package com.tts168.autoset.tools.others.converopt;
/**
 * 工具类，将各种类型的数据转换成byte 数组
 * @author Administrator
 *
 */
public class ConvertToByteArray {
	/**
	 * 将短整型转换成byte数组
	 * 低八位在前，高八位在后
	 * @param i
	 * @return
	 */
	 public static byte[] shortToByteArray(short i)
	   {
		   byte[] result=new byte[2];
		   result[1]=(byte) ((i>>8)&0xFF);     //
		   result[0]=(byte) ((i)&0xFF);
		   
		 //  result[2]=(byte) ((i>>8)&0xFF);
		   //result[3]=(byte) ((i)&0xFF);
		   return result;
		   
	   }
}
