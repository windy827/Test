package com.tts168.autoset.tools.others.converopt;





import java.io.UnsupportedEncodingException;

public class StrBinaryTurn {
	
	
	/**
	 * 字节转换成二进制字符串
	 * @param bs
	 * @return
	 */
	public static String byte2BinaryString(byte bs) {
		String total="";
		String ZERO="00000000";
		
			String   s   =   Integer.toBinaryString(bs);
			if   (s.length()   >
			8)   {
				s   =   s.substring(s.length()   -   8);
			}   else   if   (s.length()  
					<   8)   {
				s   =   ZERO.substring(s.length())   +   s;
			}
			total=total+s;
			
		
		return total;
	}
	
	/**
	 * 字节数组转换成二进制字符串
	 * @param bs
	 * @return
	 */
	public static String byte2BinaryString(byte[] bs) {
		String total="";
		String ZERO="00000000";
		for   (int i = 0;i<bs.length;i++)   {
			String   s   =   Integer.toBinaryString(bs[i]);
			if   (s.length()   >
			8)   {
				s   =   s.substring(s.length()   -   8);
			}   else   if   (s.length()  
					<   8)   {
				s   =   ZERO.substring(s.length())   +   s;
			}
			total=total+s;
			
		}
		return total;
	}
	
	/**
	 * 字符串转换成UTF8的编码的二进制整型数组
	 * @param str
	 * @return
	 */
	public static int[]StrTointlist(String str){
		int[]intlist=null;
		boolean []b=StrToBool(str);
		intlist=new int[b.length];
		for(int i=0;i<b.length;i++){
			if(b[i]){
				intlist[i]=1;
			}
			else{
				intlist[i]=0;
			}
			
		}
		return intlist;
	}
    //将Unicode字符串转换成bool型数组
    public static boolean[] StrToBool(String input){
        boolean[] output=Binstr16ToBool(BinstrToBinstr16(StrToBinstr(input)));
        return output;
    }
    //将bool型数组转换成Unicode字符串
    public  static String BoolToStr(boolean[] input){
        String output=BinstrToStr(Binstr16ToBinstr(BoolToBinstr16(input)));
        return output;
    }
    //将字符串转换成二进制字符串，以空格相隔
    private static  String StrToBinstr(String str) {
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i])+ " ";
        }
        return result;
    }
    //将二进制字符串转换成Unicode字符串
    private static  String BinstrToStr(String binStr) {
        String[] tempStr=StrToStrArray(binStr);
        char[] tempChar=new char[tempStr.length];
        for(int i=0;i<tempStr.length;i++) {
            tempChar[i]=BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }
    //将二进制字符串格式化成全16位带空格的Binstr
    private static  String BinstrToBinstr16(String input){
        StringBuffer output=new StringBuffer();
        String[] tempStr=StrToStrArray(input);
        for(int i=0;i<tempStr.length;i++){
            for(int j=16-tempStr[i].length();j>0;j--)
                output.append('0');
            output.append(tempStr[i]+" ");
        }
        return output.toString();
    }
    //将全16位带空格的Binstr转化成去0前缀的带空格Binstr
    private static  String Binstr16ToBinstr(String input){
        StringBuffer output=new StringBuffer();
        String[] tempStr=StrToStrArray(input);
        for(int i=0;i<tempStr.length;i++){
            for(int j=0;j<16;j++){
                if(tempStr[i].charAt(j)=='1'){
                    output.append(tempStr[i].substring(j)+" ");
                    break;
                }
                if(j==15&&tempStr[i].charAt(j)=='0')
                    output.append("0"+" ");
            }
        }
        return output.toString();
    }   
    //二进制字串转化为boolean型数组  输入16位有空格的Binstr
    private  static boolean[] Binstr16ToBool(String input){
        String[] tempStr=StrToStrArray(input);
        boolean[] output=new boolean[tempStr.length*16];
        for(int i=0,j=0;i<input.length();i++,j++)
            if(input.charAt(i)=='1')
                output[j]=true;
            else if(input.charAt(i)=='0')
                output[j]=false;
            else
                j--;
        return output;
    }
    //boolean型数组转化为二进制字串  返回带0前缀16位有空格的Binstr
    private  static String BoolToBinstr16(boolean[] input){ 
        StringBuffer output=new StringBuffer();
        for(int i=0;i<input.length;i++){
            if(input[i])
                output.append('1');
            else
                output.append('0');
            if((i+1)%16==0)
                output.append(' ');           
        }
        output.append(' ');
        return output.toString();
    }
    //将二进制字符串转换为char
    private  static char BinstrToChar(String binStr){
        int[] temp=BinstrToIntArray(binStr);
        int sum=0;   
        for(int i=0; i<temp.length;i++){
            sum +=temp[temp.length-1-i]<<i;
        }   
        return (char)sum;
    }
    //将初始二进制字符串转换成字符串数组，以空格相隔
    private  static String[] StrToStrArray(String str) {
        return str.split(" ");
    }
    //将二进制字符串转换成int数组
    private  static int[] BinstrToIntArray(String binStr) {       
        char[] temp=binStr.toCharArray();
        int[] result=new int[temp.length];   
        for(int i=0;i<temp.length;i++) {
            result[i]=temp[i]-48;
        }
        return result;
    }
   
    
    /**
     * 将byte型转成GBK编码的二进制整形数组
     * @param ss
     * @return int[]
     */
    	public static int[] byte2GBKBinary(byte ss) {
    		String toss="";
    		try {
    			toss = StrBinaryTurn.byte2BinaryString((byte)ss);
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		int []g=new int[toss.length()];
    		
    		for(int i=0;i<g.length;i++ ){
    			g[i]=Integer.parseInt(toss.charAt(i)+"");
    		
    		}
    		return g;
    	}
    
    
    
    
    /**
     * 将字符串转成GBK编码的二进制整形数组
     * @param ss
     * @return int[]
     */
    	public static byte[] String2GBKBinary(String ss) {
    		String toss="";
    		try {
    			toss = StrBinaryTurn.byte2BinaryString(ss.getBytes("gbk"));
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		byte []g=new byte[toss.length()];
    		
    		for(int i=0;i<g.length;i++ ){
    			g[i]=(byte) Integer.parseInt(toss.charAt(i)+"");
    		
    		}
    		return g;
    	}

   
}


