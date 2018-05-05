
package com.tts168.autoset.tools;
/*
 * 
 * 函数相比recom函数改进之处  频率和N都是变量。
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
		     int fs=simple;   //采样频率  单位：Hz
			 int  N1=fs/f1,N0=fs/f0;  //采样点
			 int k=0;
			 //byte[] byteOutArray={};
			 //float[] oneArray=new float[N];
			 //float[] zeroArray=new float[N];
			 float[] zeroArrayN=new float[N];
			 float[] oneArrayN=new float[N];
			// float[] tempArray = {};
			 
			 int mod1=N%N1;//求余数
			 int Int1=N/N1;//相除取整
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
		   
		 //合成长度为N的数组 来表示“1”
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
				   
			   outArray=Arrays.copyOf(zeroArrayN,N);      //如果是0的话  将zeroArray数组的前16位添加到outArray数组
		   }
		   else if(inArray[0]==1)
		   {
			   outArray=Arrays.copyOf(oneArrayN, N);      //如果是1的话  将onearray数组的前16位添加到outArray数组
		   }
			   
		   while(count<inArray.length)                     //进入循环添加状态
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
		   System.out.println("输出数组的长度："+outArray.length);
		   //定义一个整型数组      int型  32位
		   int[] IntOutArray=new int[outArray.length];
		 //定义一个整型数组      short型  16位
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
		   return unitedArray;          //返回的是数组的首地址
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
	    *Recompose 函数描述;
	    ************************************************************* */
	  
	 
        
	  
 public static void main(String args[])              //验证  outArray是否符合要求
	   {
		  int[]b={1,0};
		  byte[] by;
		  long l3= System.currentTimeMillis();
		  by= Recompose(b,2000,1000,64,16000);
		  long l2= System.currentTimeMillis();
		  System.out.println("耗时测试"+(l2-l3));
		  for(i=0;i<by.length;i++)
		 {
		  System.out.println(by[i]);
		  }
		  System.out.println(by.length);
	   }
        
}

