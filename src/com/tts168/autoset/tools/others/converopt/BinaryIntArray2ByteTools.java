package com.tts168.autoset.tools.others.converopt;


/**
 * 将1个字节的8位的零和1都赋值，然后返回一个byte的值
 * @author Administrator
 *
 */
public class BinaryIntArray2ByteTools {
public static final int[]array8=new int[]{128,64,32,16,8,4,2,1};
/**
 * 	得到这个byte对应的值
 * @param array 大小为8的只有0和1的数组
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
 * 将byte转换成二进制整型数组【只含0和1】
 * @param ss
 * @return
 */
public static int[] byte2BinaryInt(byte ss){
	int []result=StrBinaryTurn.byte2GBKBinary(ss);
	return result;
}
}
