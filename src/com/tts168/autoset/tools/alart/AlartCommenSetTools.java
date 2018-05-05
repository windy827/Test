package com.tts168.autoset.tools.alart;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity.CommentSet2;
import com.tts168.autoset.tools.Tools;

public class AlartCommenSetTools {
//_________________________________________________������ѡ��___________________________________________________________
	
	public static final String DEVICE_SONG_PATH_HEAD="Audio\\";
	public static final String KEY_SONGPATH="SONGID";
	public static final String KEY_SONGNAME="SONGNAME";
	public static final String KEY_SONGINFO="SONGINFO";
	public static final String []songId=new String[]{
		"ring001.mp3","ring002.mp3","ring003.mp3","ring004.mp3","ring005.mp3","ring006.mp3","ring007.mp3",
		"ring008.mp3","ring009.mp3","ring010.mp3","ring011.mp3","ring012.mp3","ring013.mp3","ring014.mp3"};
	public static final String []songName=new String[]{
		"��Ȼ����",
		"���õε�",
		"�ε�����",
		"��ܰ����",
		"����ͨ��",
		"̫������",
		"������",
		"������",
		"ͯ������",
		"������ũ",
		"�ž�֮��",
		"�¹�����",
		"���л���",
		"��������"};
	
	public static final String []songInfo=new String[]{
		"(����10s)","(����04s)","(����04s)","(����04s)","(����08s)",
		"(����04s)","(����04s)","(����08s)",
		"(����18s)","(����32s)","(����32s)",
		"(����32s)","(����32s)","(����32s)"};
	/**
	 * �õ�������������б�
	 * 
	 * AlartCommenSetTools.KEY_SONGPATH ��Ӧ����·��;
	 * AlartCommenSetTools.KEY_SONGNAME ��Ӧ��������
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
	 * ��������·���������������ʾ������
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
	 * �õ�һ��ArrayList<HashMap<String, String>>�����ò���ʱ��������
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
	 * ����һ������ֵ�õ�һ����СΪlen�����飬ȡ��lenλ
	 * 
	 * @param len���ص�
	 *            ����ĳ���
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
	 * Ĭ��ID
	 */
	public static  double ID=0;

	/**
	 * 0������Ч��1����Ч
	 */
	public static  double isvalid=1.0;
	/**
	 * Ĭ��Ϊ����
	 */
	public static  double is_lunar=0.0;;

	public static  double ring_type=0;
	
	public static  String ring_path=DEVICE_SONG_PATH_HEAD+songId[0];
	/**
	 * ÿ�����Ӳ���
	 */
	public static  double play_time=15;
	
	
	/**
	 * ��������
	 */
	public static  double delay_num=2;
	/**
	 * ̰˯���ʱ��
	 */
	public static  double delay_time=5;
	/**
	 * Ĭ�Ͻ�����Ϣ���Ŵ�����
	 */
	public static  double message_times=2;


	/**
	 * ������Ϣ����
	 */
	public static  double message_open=255;
	/**
	 * ������������Զ������
	 */
	public static  double will_do=0;

}
