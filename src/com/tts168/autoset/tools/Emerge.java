package com.tts168.autoset.tools;

import android.util.Log;

public class Emerge
{
	private final static double pi=3.1415926;
	private final static int INDEX=192;
	static int count=0;
	static byte[] by=new byte[2];
	static int i,j;
	public static byte[] Recompose(int[] inArray,int inArrayLen,int f0,int f1,int simple)
	{
			 int  N1=simple/f1,N0=simple/f0;
			 int Len=inArrayLen*INDEX;
			 short[] sh=new short[Len];
			 float[] zeroArrayN=new float[INDEX];
			 float[] oneArrayN=new float[INDEX];
			 float[] Emerge=new float[Len];
			 byte[] output=new byte[2*Len];
			 for(i=0;i<INDEX;i++)
			  {
				zeroArrayN[i]=(float)(Math.sin(2*pi*i/N0));
				oneArrayN[i]=(float)( Math.sin(2*pi*i/N1));
			  }
		   for(i=0;i<inArrayLen;i++)
		   {
			   if(inArray[i]==0)
			   {
				   for(j=0;j<INDEX;j++)
				   {
					   Emerge[j+count]=zeroArrayN[j];
				   }
			   }
			   if(inArray[i]==1)
			   {
				   for(j=0;j<INDEX;j++)
				   {
					   Emerge[j+count]=oneArrayN[j];
				   }
			   }
			   count+=INDEX;
			   Log.d("TTSCOUNT", count+"__________");
		   }
		   //---------------BUGGER---------------
		   count=0;
		   for(i=0;i<Len;i++)
		   {
			   sh[i]=(short)(0.99*(256*128)*Emerge[i]);
			   by=intToByteArray(sh[i]);
			   output[2*i]=by[0];
			   output[2*i+1]=by[1];
		   }
		   //---------------------------
		   Log.d("TTSCOUNT1212", count+"__________");
		   return output;
	}
	public static byte[] intToByteArray(short i)
	{
	   byte[] result=new byte[2];
	   result[1]=(byte) ((i>>8)&0xFF);     
	   result[0]=(byte) ((i)&0xFF);
	   return result;
		   
	}
	
 /*public static void main(String args[])
	   {
		  int[]b={0,1};
		  byte[] by;
		  by= Recompose(b,2,2000,1000,16000);
		  System.out.println("The Emerge Array is:\n");
		  for(i=0;i<by.length;i++)
		 {
		  System.out.println(by[i]);
		 }
		  System.out.println(by.length);
	   }*/
}

