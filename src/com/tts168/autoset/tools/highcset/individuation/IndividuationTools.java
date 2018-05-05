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
	
	public static final String SET_FAILD="设置发音人失败！";
	public static final String SET_SUCCESS="设置发音人成功！";
	/**
	 * 晓玲
	 */
	public static final  String SOUND_XIAOLING="[m3]我是晓玲[m3]";
	/**
	 * 尹小坚
	 */
	public static final  String SOUND_YINXIAOJIAN="[m51]我是尹小坚[m3]";
	/**
	 * 易小强
	 */
	public static final  String SOUND_YIXIAOQIANG="[m52]我是易小强[m3]";
	/**
	 * 田蓓蓓
	 */
	public static final  String SOUND_TIANBEIBEI="[m53]我是田蓓蓓[m3]";
	/**
	 *唐老鸭
	 */
	public static final  String SOUND_TANGLAOYA="[m54]我是唐老鸭[m3]";
	/**
	 * 小燕子
	 */
	public static final  String SOUND_XIAOYANZI="[m55]我是小燕子[m3]";
	/**
	 * 我在呢
	 */
	public static final String  SOUND_HERE="[m55]我在呢[m3]";
	
public static void playSound(String socketIP, int sound){
	byte[]soundGBK=MyTools.getJNIUseByte(sound+"");
	String soundUTF=MyTools.byteArray2String(soundGBK);
	ArrayList<PlayItemEntity>entitys=new  ArrayList<PlayItemEntity>();
	PlayItemEntity pe=new PlayItemEntity(PlayEntity.TYPE_PALYSOUND, soundUTF);
	entitys.add(pe);
	
	String content=PlayOptions.setPlay(new String[]{"暂无歌曲"},entitys, PlayEntity.METHOD,false);
	byte[]sayData=MyTools.getJNIUseByte(PlayOptions.setPlay(new String[]{"暂无歌曲"},entitys, PlayEntity.METHOD,false));
	byte[]say_send=UDPSendContent.getSendDate(UDPSendContent.SEND_FLAG_JSON, sayData);
	ArrayList<byte[]>send=new ArrayList<byte[]>();
	send.add(say_send);
	
	TCPTools.tcpSendData(socketIP, send);
	
	
}
}
