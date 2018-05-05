package com.tts168.autoset.tools.others.converopt;
/**
 * ���κ�byte������໥ת��
 * @author Ԭ��
 *
 */
public class IntAndByteArrayConvert {
	//--------------------------------------------------------------------
	//----------------------intתbyte[]--------------------------------
	/** 
     * ��int��ֵת��Ϊռ�ĸ��ֽڵ�byte���飬������������(��λ��ǰ����λ�ں�)��˳�� ��bytesToInt��������ʹ��
     * @param value 
     *            Ҫת����intֵ
     * @return byte����
     */  
	public static byte[] intToBytes( int value ) 
	{ 
		byte[] src = new byte[4];
		src[3] =  (byte) ((value>>24) & 0xFF);
		src[2] =  (byte) ((value>>16) & 0xFF);
		src[1] =  (byte) ((value>>8) & 0xFF);  
		src[0] =  (byte) (value & 0xFF);				
		return src; 
	}
	 /** 
     * ��int��ֵת��Ϊռ�ĸ��ֽڵ�byte���飬������������(��λ��ǰ����λ�ں�)��˳��  ��bytesToInt2��������ʹ��
     */  
	public static byte[] intToBytes2(int value) 
	{ 
		byte[] src = new byte[4];
		src[0] = (byte) ((value>>24) & 0xFF);
		src[1] = (byte) ((value>>16)& 0xFF);
		src[2] = (byte) ((value>>8)&0xFF);  
		src[3] = (byte) (value & 0xFF);		
		return src;
	}
//--------------------------------------------------------------------
	//----------------------byte[]תint--------------------------------
	



	/** 
     * byte������ȡint��ֵ��������������(��λ��ǰ����λ�ں�)��˳�򣬺ͺ�intToBytes��������ʹ��
     *  
     * @param src 
     *            byte���� 
     * @param offset 
     *            ������ĵ�offsetλ��ʼ 
     * @return int��ֵ 
     */  
	public static int bytesToInt(byte[] src, int offset) {
		int value;	
		value = (int) ((src[offset] & 0xFF) 
				| ((src[offset+1] & 0xFF)<<8) 
				| ((src[offset+2] & 0xFF)<<16) 
				| ((src[offset+3] & 0xFF)<<24));
		return value;
	}
	
	 /** 
     * byte������ȡint��ֵ��������������(��λ�ں󣬸�λ��ǰ)��˳�򡣺�intToBytes2��������ʹ��
     */
	public static int bytesToInt2(byte[] src, int offset) {
		int value;	
		value = (int) ( ((src[offset] & 0xFF)<<24)
				|((src[offset+1] & 0xFF)<<16)
				|((src[offset+2] & 0xFF)<<8)
				|(src[offset+3] & 0xFF));
		return value;
	}

	
	

}
