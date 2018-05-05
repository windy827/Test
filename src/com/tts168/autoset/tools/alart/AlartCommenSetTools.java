package com.tts168.autoset.tools.alart;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity.CommentSet2;
import com.tts168.autoset.tools.Tools;

public class AlartCommenSetTools {
//_________________________________________________起床音乐选择___________________________________________________________
	
	public static final String DEVICE_SONG_PATH_HEAD="Audio\\";
	public static final String KEY_SONGPATH="SONGID";
	public static final String KEY_SONGNAME="SONGNAME";
	public static final String KEY_SONGINFO="SONGINFO";
	public static final String []songId=new String[]{
		"ring001.mp3","ring002.mp3","ring003.mp3","ring004.mp3","ring005.mp3","ring006.mp3","ring007.mp3",
		"ring008.mp3","ring009.mp3","ring010.mp3","ring011.mp3","ring012.mp3","ring013.mp3","ring014.mp3"};
	public static final String []songName=new String[]{
		"自然晨曦",
		"常用滴滴",
		"滴滴铃声",
		"温馨提醒",
		"智商通用",
		"太阳公公",
		"懒猪起床",
		"懒猪起床",
		"童声翻唱",
		"鸟鸣卡农",
		"寂静之声",
		"月光音乐",
		"梦中婚礼",
		"世界无限"};
	
	public static final String []songInfo=new String[]{
		"(铃声10s)","(铃声04s)","(铃声04s)","(铃声04s)","(人声08s)",
		"(人声04s)","(人声04s)","(人声08s)",
		"(人声18s)","(音乐32s)","(音乐32s)",
		"(音乐32s)","(音乐32s)","(音乐32s)"};
	/**
	 * 得到起床闹铃的铃声列表
	 * 
	 * AlartCommenSetTools.KEY_SONGPATH 对应音乐路径;
	 * AlartCommenSetTools.KEY_SONGNAME 对应音乐名称
	 * @return
	 */
	public static ArrayList<HashMap<String,Object>> getSongInfo(){
		ArrayList<HashMap<String, Object>> song = new ArrayList<HashMap<String, Object>>();
		for(int i=0;i<songId.length;i++){
			HashMap<String, Object>temp=new HashMap<String, Object>();
			temp.put( AlartCommenSetTools.KEY_SONGPATH , DEVICE_SONG_PATH_HEAD+songId[i]);
			temp.put(AlartCommenSetTools.KEY_SONGNAME, songName[i]+songInfo[i]);
			temp.put(AlartCommenSetTools.KEY_SONGINFO, songInfo[i]);
			song.add(temp);
		}
		return song;
		}
	
	/**
	 * 根据音乐路径获得音乐用于显示的名称
	 * @param songPath
	 * @return
	 */
	public static String getSongNameBySongPath(String songPath){
		String name=songName[0];
		for(int i=0;i<songId.length;i++){
			String path=DEVICE_SONG_PATH_HEAD+songId[i];
			if(songPath.equals(path)){
				name=songName[i]+songInfo[i];
				break;
			}
		}
		return name;
	}
	//____________________________________________________________________________________________________________________
	
	/**
	 * 得到一个ArrayList<HashMap<String, String>>的设置播放时长的数组
	 */
	public static ArrayList<HashMap<String, String>> getPlayTime(
			Context context) {
		ArrayList<HashMap<String, String>> time = new ArrayList<HashMap<String, String>>();
		String[] times = context.getResources().getStringArray(
				R.array.playtime);
		int len = times.length;
		for (int i = 0; i < len; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put(Tools.SET_WHOLE_KEY, times[i]);
			time.add(temp);
		}
		return time;

	}
	
	/**
	 * 输入一个整型值得到一个大小为len的数组，取后len位
	 * 
	 * @param len返回的
	 *            数组的长度
	 */
	public static int[]getSwitchInfo(int values, int len){
		int []temp=AlartTools.Cycle.getLastIntArrray(values,len);
		int []result=new int[len];
		for(int i=0;i<len;i++){
			result[i]=temp[len-1-i];
		}
		return result;
	}
	/**
	 * 默认ID
	 */
	public static  double ID=0;

	/**
	 * 0：不生效，1：生效
	 */
	public static  double isvalid=1.0;
	/**
	 * 默认为阳历
	 */
	public static  double is_lunar=0.0;;

	public static  double ring_type=0;
	
	public static  String ring_path=DEVICE_SONG_PATH_HEAD+songId[0];
	/**
	 * 每几分钟播报
	 */
	public static  double play_time=15;
	
	
	/**
	 * 播报几次
	 */
	public static  double delay_num=2;
	/**
	 * 贪睡间隔时间
	 */
	public static  double delay_time=5;
	/**
	 * 默认今天信息播放次数。
	 */
	public static  double message_times=2;


	/**
	 * 今天信息开关
	 */
	public static  double message_open=255;
	/**
	 * 闹铃启动后的自定义操作
	 */
	public static  double will_do=0;

}
