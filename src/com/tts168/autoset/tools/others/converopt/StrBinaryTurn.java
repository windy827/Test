package com.tts168.autoset.tools.others.converopt;





import java.io.UnsupportedEncodingException;

public class StrBinaryTurn {
	
	
	/**
	 * �ֽ�ת���ɶ������ַ���
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
	 * �ֽ�����ת���ɶ������ַ���
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
	 * �ַ���ת����UTF8�ı���Ķ�������������
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
    //��Unicode�ַ���ת����bool������
    public static boolean[] StrToBool(String input){
        boolean[] output=Binstr16ToBool(BinstrToBinstr16(StrToBinstr(input)));
        return output;
    }
    //��bool������ת����Unicode�ַ���
    public  static String BoolToStr(boolean[] input){
        String output=BinstrToStr(Binstr16ToBinstr(BoolToBinstr16(input)));
        return output;
    }
    //���ַ���ת���ɶ������ַ������Կո����
    private static  String StrToBinstr(String str) {
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i])+ " ";
        }
        return result;
    }
    //���������ַ���ת����Unicode�ַ���
    private static  String BinstrToStr(String binStr) {
        String[] tempStr=StrToStrArray(binStr);
        char[] tempChar=new char[tempStr.length];
        for(int i=0;i<tempStr.length;i++) {
            tempChar[i]=BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }
    //���������ַ�����ʽ����ȫ16λ���ո��Binstr
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
    //��ȫ16λ���ո��Binstrת����ȥ0ǰ׺�Ĵ��ո�Binstr
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
    //�������ִ�ת��Ϊboolean������  ����16λ�пո��Binstr
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
    //boolean������ת��Ϊ�������ִ�  ���ش�0ǰ׺16λ�пո��Binstr
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
    //���������ַ���ת��Ϊchar
    private  static char BinstrToChar(String binStr){
        int[] temp=BinstrToIntArray(binStr);
        int sum=0;   
        for(int i=0; i<temp.length;i++){
            sum +=temp[temp.length-1-i]<<i;
        }   
        return (char)sum;
    }
    //����ʼ�������ַ���ת�����ַ������飬�Կո����
    private  static String[] StrToStrArray(String str) {
        return str.split(" ");
    }
    //���������ַ���ת����int����
    private  static int[] BinstrToIntArray(String binStr) {       
        char[] temp=binStr.toCharArray();
        int[] result=new int[temp.length];   
        for(int i=0;i<temp.length;i++) {
            result[i]=temp[i]-48;
        }
        return result;
    }
   
    
    /**
     * ��byte��ת��GBK����Ķ�������������
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
     * ���ַ���ת��GBK����Ķ�������������
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


