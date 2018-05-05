package com.tts168.autoset.tools;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import android.util.Log;


public class ReceiveTest{

	public static final int FRAME_HEAD_LENGTH=1;
	public static final int FRAME_SIZE_LENGTH=2;
	public final static int FRAME_COMMOND_LENGTH=1; 
	public static final int DEVICE_NAME_LENGTH=30;
	public static final int DEVICE_IP_LENGTH=20;
	public final static int DEVICE_PORT_LENGTH=6; 
	public static final int FRAME_CRC_LENGTH=2;

	
	/**
	 * UDP 的接收
	 * @param args
	 */
	public static String recive(DatagramSocket socket,DatagramPacket packet) {
		// TODO Auto-generated method stub
		String t="";
		String test="";
		byte[] m=null;
		try {
		
			
			Log.d("Listening", true+"");
			//String ip=socket.getLocalPort()+"@@"+socket.getLocalAddress()+"@@"+socket.getLocalSocketAddress();
			socket.receive(packet);//接收报文，程序停滞等待直到接收到报文
			
			Log.d("LISTENRE", "here");
			m = packet.getData();
			ArrayList<Byte> l=new ArrayList<Byte>();
			for(int i=8;i<m.length;i++){
				if(m[i]>0){
					l.add(m[i]);
				}
			}
			byte[]m1="".getBytes();
			byte[]my=new byte[l.size()];
			for(int i=0;i<my.length;i++){
				my[i]=l.get(i);
			}
			t=new String(my,"utf8");//=new String(m);
//			//byte[]
//			t=new String(packet.getData(),"utf8").trim();
//			//CRC校验
//			if(!Tools.IS_CRC_pass(m)){
//				m=null;
//			}
			Log.d("recUDPString", t);
//			socket.disconnect();//断开套接字
//			socket.close();//关闭套接字
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			Log.d("Sockethere","***************************");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("Sockethere11","***************************");
			e.printStackTrace();
		}
		return t;

	}

}
