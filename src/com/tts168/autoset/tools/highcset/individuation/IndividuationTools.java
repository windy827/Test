package com.tts168.autoset.tools.highcset.individuation;

import java.net.Socket;
import java.util.ArrayList;

import com.autoset.jni.deviceInfo.DeviceSayOptions;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.play.PlayItemEntity;
import com.autoset.jni.play.PlayOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.tcpAndudp.UDPSendContent;
import com.tts168.autoset.tools.tcpAndudp.WifiWatchTools;

public class IndividuationTools {
	
	public static final String SET_FAILD="���÷�����ʧ�ܣ�";
	public static final String SET_SUCCESS="���÷����˳ɹ���";
	/**
	 * ����
	 */
	public static final  String SOUND_XIAOLING="[m3]��������[m3]";
	/**
	 * ��С��
	 */
	public static final  String SOUND_YINXIAOJIAN="[m51]������С��[m3]";
	/**
	 * ��Сǿ
	 */
	public static final  String SOUND_YIXIAOQIANG="[m52]������Сǿ[m3]";
	/**
	 * ������
	 */
	public static final  String SOUND_TIANBEIBEI="[m53]����������[m3]";
	/**
	 *����Ѽ
	 */
	public static final  String SOUND_TANGLAOYA="[m54]��������Ѽ[m3]";
	/**
	 * С����
	 */
	public static final  String SOUND_XIAOYANZI="[m55]����С����[m3]";
	/**
	 * ������
	 */
	public static final String  SOUND_HERE="[m55]������[m3]";
	
public static void playSound(String socketIP, int sound){
	byte[]soundGBK=MyTools.getJNIUseByte(sound+"");
	String soundUTF=MyTools.byteArray2String(soundGBK);
	ArrayList<PlayItemEntity>entitys=new  ArrayList<PlayItemEntity>();
	PlayItemEntity pe=new PlayItemEntity(PlayEntity.TYPE_PALYSOUND, soundUTF);
	entitys.add(pe);
	
	String content=PlayOptions.setPlay(new String[]{"���޸���"},entitys, PlayEntity.METHOD,false);
	byte[]sayData=MyTools.getJNIUseByte(PlayOptions.setPlay(new String[]{"���޸���"},entitys, PlayEntity.METHOD,false));
	byte[]say_send=UDPSendContent.getSendDate(UDPSendContent.SEND_FLAG_JSON, sayData);
	ArrayList<byte[]>send=new ArrayList<byte[]>();
	send.add(say_send);
	
	TCPTools.tcpSendData(socketIP, send);
	
	
}
}
