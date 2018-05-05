package com.tts168.autoset.tools.tcpAndudp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.os.Message;
import android.util.Log;

import com.autoset.jni.deviceInfo.DeviceInfoEntity;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.heartbeat.HeartBeatEntity;
import com.autoset.jni.heartbeat.HeartBeatJsonOptions;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.tcpAndudp.tcpsever.TCPThread;

/**
 * TCP�Ĺ�����
 * 
 * @author Ԭ��
 * 
 */
public class TCPTools {
	/**
	 * TCP���ӵĶ˿ں�
	 */
	public static final int port = 8090;

	public static final int VERSION_APK = 12;
	public static final int VERSION_ANDROID = 2;

	/**
	 * TCP���ӵķ��صı��ĵ��������޹ص�[ͷ��Length��Flag����-1]�������������ַ���
	 */
	public static final int PACKAGE_HEADLENGTH = 7;

	/**
	 * TCP�߳�
	 */
	public static TCPThread tcpThread = null;
	
	public static void tcpSendData(String socket, ArrayList<byte[]> data) {
		Network net = Network.getInstance(null);
		for (byte[] req : data) {
			if (null == net || !net.sendToHost(socket, req)) {
				Log.d("TERMINAL", "cannot send request, network offline");
				break;
			}
		}
	}

	/**
	 * ������������Ҫ�õ���������Ϣ���޹��ء�
	 * 
	 * @param domainName
	 *            ��������
	 * @param socket
	 */
	public static  void sendTcpByDomain(
			ArrayList<String> domainName, String socket) {
		ArrayList<byte[]> final_send = new ArrayList<byte[]>();
		if (domainName != null) {

			String[] domainNames = new String[domainName.size()];
			for (int i = 0; i < domainName.size(); i++) {
				domainNames[i] = domainName.get(i);

			}
			byte[] sendData = MyTools.getJNIUseByte(new AutoSetJsonTools()
					.setGetDomainJsonObject(JsonParseOption.GET_USERDATA,
							domainNames));

			byte[] send = UDPSendContent.getSendDate(
					UDPSendContent.SEND_FLAG_JSON, sendData);
			String sendString = "";
			try {
				sendString = new String(send, Tools.TEXT_FORMAT);
				Log.d("sendString", sendString);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			final_send.add(send);

		}
		
			tcpSendData(socket, final_send);
		
		
	}

	/**
	 * ������Ҫ���͵����ݡ������ڱ������
	 * 
	 * @param content
	 *            ��Ҫ���͵����ݣ����ô˷���
	 * @param socket
	 */
	public static void sendTcp(ArrayList<String> content,String socket,boolean needcheckSend) {
		ArrayList<byte[]> final_send = new ArrayList<byte[]>();
		if (content != null) {
			for (int i = 0; i < content.size(); i++) {
				Log.d("HEARTLOG","TCPTOOLS:");
				byte[] sendData = MyTools.getJNIUseByte(content.get(i));
				byte[] send = UDPSendContent.getSendDate(
						UDPSendContent.SEND_FLAG_JSON, sendData);
				final_send.add(send);
			}

		}
		tcpSendData(socket, final_send);
//		if(needcheckSend){
//			if(WifiWatchTools.checkWifiStateAndAlarm(Tools.c,true)){
//				Log.d("HEARTLOG","WifiWatchTools:");
//				tcpSendData(socket, final_send);
//			}
//		}else{
//			tcpSendData(socket, final_send);
//		}
		
	}

	/**
	 * ����Send��Get��Method
	 * 
	 * @param sendcontent
	 *            û�д���null
	 * @param getcontent
	 *            û�д���null
	 * @param socket
	 */
	public static void sendTcpMethodOfSendAndGet(ArrayList<String> sendcontent,
			ArrayList<String> getcontent, String socket) {
		ArrayList<byte[]> final_send = new ArrayList<byte[]>();
		if (sendcontent != null) {
			for (int i = 0; i < sendcontent.size(); i++) {
				byte[] sendData = MyTools.getJNIUseByte(sendcontent.get(i));
				byte[] send = UDPSendContent.getSendDate(
						UDPSendContent.SEND_FLAG_JSON, sendData);
				final_send.add(send);
			}
		}
		if (getcontent != null) {
			String[] domainNames = new String[getcontent.size()];
			for (int i = 0; i < getcontent.size(); i++) {
				domainNames[i] = getcontent.get(i);

			}
			byte[] sendData = MyTools.getJNIUseByte(new AutoSetJsonTools()
					.setGetDomainJsonObject(JsonParseOption.GET_USERDATA,
							domainNames));

			byte[] send = UDPSendContent.getSendDate(
					UDPSendContent.SEND_FLAG_JSON, sendData);
			String sendString = "";
			try {
				sendString = new String(send, Tools.TEXT_FORMAT);
				Log.d("sendString", sendString);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			final_send.add(send);
		}
		
		tcpSendData(socket, final_send);
		
		

	}

	/**
	 * ������ⷢ�͵�����
	 * 
	 * @param socket
	 */
	public static void sendHeartBeatDection(String socket) {
		String content = HeartBeatJsonOptions
				.setData_HeartBeat(new HeartBeatEntity(
						HeartBeatEntity.TIMER_values,
						HeartBeatEntity.VALUE_values));

		Log.d("HEARTLOG","������STRING����Ϊ:"+content.length());
		ArrayList<String> contents = new ArrayList<String>();
		contents.add(content);


		TCPTools.sendTcp(contents, socket, false);

	}
}
