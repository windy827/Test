package com.tts168.autoset.tools.others.converopt;


/**
 * ��1���ֽڵ�8λ�����1����ֵ��Ȼ�󷵻�һ��byte��ֵ
 * @author Administrator
 *
 */
public class BinaryIntArray2ByteTools {
public static final int[]array8=new int[]{128,64,32,16,8,4,2,1};
/**
 * 	�õ����byte��Ӧ��ֵ
 * @param array ��СΪ8��ֻ��0��1������
 * @return
 */
public static byte getByte(int[]array){
		byte result=0;
		for(int i=0;i<array.length;i++){
			result=(byte) (result+array8[i]*array[i]);
		}
		return result;
	}
/**
 * ��byteת���ɶ������������顾ֻ��0��1��
 * @param ss
 * @return
 */
public static int[] byte2BinaryInt(byte ss){
	int []result=StrBinaryTurn.byte2GBKBinary(ss);
	return result;
}
}
