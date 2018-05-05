
package com.tts168.autoset.tools;
/*
 * 
 * �������recom�����Ľ�֮��  Ƶ�ʺ�N���Ǳ�����
 * 
 * 
 * 
 * */

import java.util.Arrays;

public class Recompose
{
	private final static double pi=3.1415926;
	
	static int i,j;
	static int k=0;
	long l1= System.currentTimeMillis();
	public static byte[] Recompose(int[] inArray,int f0,int f1,int N,int simple)
	{
		     int fs=simple;   //����Ƶ��  ��λ��Hz
			 int  N1=fs/f1,N0=fs/f0;  //������
			 int k=0;
			 //byte[] byteOutArray={};
			 //float[] oneArray=new float[N];
			 //float[] zeroArray=new float[N];
			 float[] zeroArrayN=new float[N];
			 float[] oneArrayN=new float[N];
			// float[] tempArray = {};
			 
			 int mod1=N%N1;//������
			 int Int1=N/N1;//���ȡ��
			// int mod0=N%N0;
			 //int Int0=N/N0;
			// float[] mod0Array=new float[mod0];
			// float[] mod1Array=new float[mod1];
			 System.out.println(mod1);
			 System.out.println(Int1);
			 for(i=0;i<N;i++)
			  {
				zeroArrayN[i]=(float)(Math.sin(2*pi*i/N0));
			  }
		   
			 for(j=0;j<N;j++)
			  {
				oneArrayN[j]=(float)( Math.sin(2*pi*j/N1));
			  }
		  
		   int count=1;
		  /* for(i=0;i<Int0;i++)
		   {
			  tempArray=Unit(tempArray,zeroArray); 
		   }
		   for(i=0;i<mod0;i++)
		   {
			  mod0Array[i]=zeroArray[i]; 
		   }
		   zeroArrayN=Unit(tempArray,mod0Array);
		   
		 //�ϳɳ���ΪN������ ����ʾ��1��
		   float[]  tempArray1={};
		   for(i=0;i<Int1;i++)
		   {
			  tempArray1=Unit(tempArray1,oneArray); 
		   }
		   for(i=0;i<mod1;i++)
		   {
			  mod1Array[i]=oneArray[i]; 
		   }
		   oneArrayN=Unit(tempArray1,mod1Array);*/
		   
		   
		   //float []zeroArray2=Unit(zeroArray,zeroArray); 
		  // float[] oneArray2 = Arrays.copyOf(oneArray,16);
		   //int loc;  
		   float[]outArray= new float[N]; 
		   if(inArray[0]==0)                               
		   {
				   
			   outArray=Arrays.copyOf(zeroArrayN,N);      //�����0�Ļ�  ��zeroArray�����ǰ16λ��ӵ�outArray����
		   }
		   else if(inArray[0]==1)
		   {
			   outArray=Arrays.copyOf(oneArrayN, N);      //�����1�Ļ�  ��onearray�����ǰ16λ��ӵ�outArray����
		   }
			   
		   while(count<inArray.length)                     //����ѭ�����״̬
		   {
			      if(inArray[count]==1)
			      {
			    	  outArray=Unit(outArray,oneArrayN); 
			  
			      }
			      
			      else if(inArray[count]==0)
			      {
			    	  outArray=Unit(outArray,zeroArrayN);
					 
			      }
		          count++;

			}
		   System.out.println("�������ĳ��ȣ�"+outArray.length);
		   //����һ����������      int��  32λ
		   int[] IntOutArray=new int[outArray.length];
		 //����һ����������      short��  16λ
		   short[]ShortOutArray=new short[outArray.length];
		   byte[] byteOutArray=new byte[IntOutArray.length*2];

		   
		   for(i=0;i<outArray.length;i++)
		   {
			  IntOutArray[i]=(int) Math.rint(0.99*(256*128)*outArray[i]);
			  ShortOutArray[i]=(short) IntOutArray[i];
		
		   }
		    while(k<IntOutArray.length)
		    {
		     
		     byte[] temp2;
		     temp2=intToByteArray(ShortOutArray[k]);
		     //byteOutArray=UnitByte(byteOutArray,temp2);
		     byteOutArray[2*k]=temp2[0];
		     byteOutArray[2*k+1]=temp2[1];
		     
		     k++;
		     
		     }
		     return byteOutArray;  
      }
	
	   
	   
	   public static float[] Unit(float[] firstArray,float[] secondArray)
	   {
		  
		  
		   float[] unitedArray=new float[firstArray.length+secondArray.length];
		   for(i=0;i<firstArray.length;i++)
		   {
			   unitedArray[i]=firstArray[i];
		   }
		   for(j=0;j<secondArray.length;j++)
		   {  
			  
			  unitedArray[firstArray.length+j]=secondArray[j];
		   }
		   return unitedArray;          //���ص���������׵�ַ
	   }
	
	   public static byte[] intToByteArray(short i)
	   {
		   byte[] result=new byte[2];
		   result[1]=(byte) ((i>>8)&0xFF);     //
		   result[0]=(byte) ((i)&0xFF);
		   
		 //  result[2]=(byte) ((i>>8)&0xFF);
		   //result[3]=(byte) ((i)&0xFF);
		   return result;
		   
	   }
	
	   /*************************************************************
	    *Recompose ��������;
	    ************************************************************* */
	  
	 
        
	  
 public static void main(String args[])              //��֤  outArray�Ƿ����Ҫ��
	   {
		  int[]b={1,0};
		  byte[] by;
		  long l3= System.currentTimeMillis();
		  by= Recompose(b,2000,1000,64,16000);
		  long l2= System.currentTimeMillis();
		  System.out.println("��ʱ����"+(l2-l3));
		  for(i=0;i<by.length;i++)
		 {
		  System.out.println(by[i]);
		  }
		  System.out.println(by.length);
	   }
        
}

