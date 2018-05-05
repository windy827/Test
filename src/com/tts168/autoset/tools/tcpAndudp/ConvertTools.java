package com.tts168.autoset.tools.tcpAndudp;
/**
 * ��������ת������
 * @author Ԭ��
 *
 */
public class ConvertTools {

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		byte[] result ={5,0,0,0};
//		System.out.println(byteToInt(result));
////		for(int i=0;i<result.length;i++){
////			System.out.println(result[i]);
////		}
//
//	}
	//long����ת��byte���� 

	  public static byte[] longToByte(long number) { 

	        long temp = number; 

	        byte[] b = new byte[8]; 

	        for (int i = 0; i < b.length; i++) { 

	            b[i] = new Long(temp & 0xff).byteValue();// �����λ���������λ 

	            temp = temp >> 8; // ������8λ 

	        } 

	        return b; 

	    } 

	    

	    //byte����ת��long 

	    public static long byteToLong(byte[] b) { 

	        long s = 0; 

	        long s0 = b[0] & 0xff;// ���λ 

	        long s1 = b[1] & 0xff; 

	        long s2 = b[2] & 0xff; 

	        long s3 = b[3] & 0xff; 

	        long s4 = b[4] & 0xff;// ���λ 

	        long s5 = b[5] & 0xff; 

	        long s6 = b[6] & 0xff; 

	        long s7 = b[7] & 0xff; 

	 

	        // s0���� 

	        s1 <<= 8; 

	        s2 <<= 16; 

	        s3 <<= 24; 

	        s4 <<= 8 * 4; 

	        s5 <<= 8 * 5; 

	        s6 <<= 8 * 6; 

	        s7 <<= 8 * 7; 

	        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7; 

	        return s; 

	    } 

	 
	 

	    public static byte[] intToByte(int number) { 

	        int temp = number; 

	        byte[] b = new byte[4]; 

	        for (int i = 3; i >0; i--) { 

	            b[i] = new Integer(temp & 0xff).byteValue();// �����λ���������λ 

	            temp = temp >> 8; // ������8λ 

	        } 

	        return b; 

	    } 

	 

	     

	    public static int byteToInt(byte[] b) { 

	        int s = 0; 

	        int s0 = b[0] & 0xff;// ���λ 

	        int s1 = b[1] & 0xff; 

	        int s2 = b[2] & 0xff; 

	        int s3 = b[3] & 0xff; 

	        s3 <<= 24; 

	        s2 <<= 16; 

	        s1 <<= 8; 

	        s = s0 | s1 | s2 | s3; 

	        return s; 

	    } 

	 

	     

	    public static byte[] shortToByte(short number) { 

	        int temp = number; 

	        byte[] b = new byte[2]; 

	        for (int i = 0; i < b.length; i++) { 

	            b[i] = new Integer(temp & 0xff).byteValue();// �����λ���������λ 

	            temp = temp >> 8; // ������8λ 

	        } 

	        return b; 

	    } 

	 

	     

	    public static short byteToShort(byte[] b) { 

	        short s = 0; 

	        short s0 = (short) (b[0] & 0xff);// ���λ 

	        short s1 = (short) (b[1] & 0xff); 

	        s1 <<= 8; 

	        s = (short) (s0 | s1); 

	        return s; 

	    }
}
