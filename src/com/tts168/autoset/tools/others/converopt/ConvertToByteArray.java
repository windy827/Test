package com.tts168.autoset.tools.others.converopt;
/**
 * �����࣬���������͵�����ת����byte ����
 * @author Administrator
 *
 */
public class ConvertToByteArray {
	/**
	 * ��������ת����byte����
	 * �Ͱ�λ��ǰ���߰�λ�ں�
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
