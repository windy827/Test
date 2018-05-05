package com.tts168.autoset.tools.localmusic;

import java.util.ArrayList;

import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.play.PlayItemEntity;
import com.autoset.jni.play.PlayOptions;
import com.autoset.json.MyTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.tcpAndudp.UDPSendContent;

public class LocalMusicTools {
	/**
	 * 本地音乐列表的焦点
	 */
	public static int Adapter_Focus_index=-1;
	/**
	 * 播放本地音乐
	 * @param socketIP
	 * @param url
	 */
	public static void playLocalMusic(String socketIP, String url){
		byte[]soundGBK=MyTools.getJNIUseByte(url);
		String soundUTF=MyTools.byteArray2String(soundGBK);
		ArrayList<PlayItemEntity>entitys=new  ArrayList<PlayItemEntity>();
		PlayItemEntity pe=new PlayItemEntity(PlayEntity.TYPE_HTTP, soundUTF);
		entitys.add(pe);
		
		String content=PlayOptions.setPlay(new String[]{"暂无歌曲"},entitys, PlayEntity.METHOD,false);
		byte[]sayData=MyTools.getJNIUseByte(PlayOptions.setPlay(new String[]{"暂无歌曲"},entitys, PlayEntity.METHOD,false));
		byte[]say_send=UDPSendContent.getSendDate(UDPSendContent.SEND_FLAG_JSON, sayData);
		ArrayList<byte[]>send=new ArrayList<byte[]>();
		send.add(say_send);
		
		TCPTools.tcpSendData(socketIP, send);
		
		
	}
}
