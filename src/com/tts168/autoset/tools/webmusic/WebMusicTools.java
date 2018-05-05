package com.tts168.autoset.tools.webmusic;

import java.util.ArrayList;
import java.util.HashMap;

import com.autoset.jni.http.AudioEntity;
import com.autoset.jni.http.CategoryEntity;
import com.autoset.json.JsonAllOption;
import com.autoset.json.MyTools;
import com.larkiv.larksmart7618.R;

/**
 * �������ֹ�����
 * @author Ԭ��
 *
 */
public class WebMusicTools {
	
	public static final String secondMusic_path="/mnt/sdcard/larksmart/webmusic/second/";
	public static final String thirdMusic_path="/mnt/sdcard/larksmart/webmusic/third/";
	public static final String playlistMusic_path="/mnt/sdcard/larksmart/webmusic/playlist/";
	public static ArrayList<AudioEntity> info=new ArrayList<AudioEntity>();
	
	/**
	 * ���ַּ�
	 */
	public static ArrayList<CategoryEntity> music_category_info_first=new ArrayList<CategoryEntity>();//һ���б�
	public static ArrayList<CategoryEntity> music_category_info_second=new ArrayList<CategoryEntity>();//�����б�
	public static ArrayList<CategoryEntity> music_category_info_third=new ArrayList<CategoryEntity>();//�����б�
	
	
	
//_______________________________________________һ��Ŀ¼__________________________________________________________________________________________________
	
	public static String []name_first=new String[]{"����","����","Ц��","��ͯ����","����СƷ","���ֽ�Ŀ","���ս�Ŀ","��н�Ŀ","��˵��Ŀ","��������"};
	public static int []drawables_first=new int[]{R.drawable.first_02,R.drawable.first_01,R.drawable.first_03,R.drawable.first_04,R.drawable.first_07,R.drawable.first_08,R.drawable.first_05,
		R.drawable.first_06,R.drawable.first_09,R.drawable.first_10};
	
	//_________________________________________________________________________________________________________________________________________________	
	
		/**
		 * ��ȡ���������϶�Ӧ��ͼƬID�Լ�����
		 * @param name
		 * @param drawables
		 * @return
		 */
		public static ArrayList<HashMap<String,Object>> getAdapterInfo(String[]name,int[]drawables){
			ArrayList<HashMap<String,Object>> info=new ArrayList<HashMap<String,Object>>();
			for(int i=0;i<name.length;i++){
				HashMap<String, Object>temp=new HashMap<String, Object>();
				temp.put(WebMusicTools.UseKey.AdapterInfoKey.WEBMUSIC_NAME, name[i]);
				temp.put(WebMusicTools.UseKey.AdapterInfoKey.WEBMUSIC_DRAWABLE, drawables[i]);
				info.add(temp);
			}
			return info;
		}
		
		//_________________________________________________________________________________________________________________________________________________	
	/**
	 * ���������õ��Ĺؼ���
	 * @author Ԭ��
	 *
	 */
	public static class UseKey{
		/**
		 * ������Ϣ
		 */
		public static final String KEY_TITLE="title";
		
		public static final String KEY_CATEGORYID="CATEGORYID";
		/**
		 * ����ListView���ݵ������ArrayList��HashMap��Ӧ��Keyֵ
		 * @author Ԭ��
		 *
		 */
		public static class AdapterInfoKey{
			public static final String WEBMUSIC_NAME="name";
			public static final String WEBMUSIC_DRAWABLE="drawable";
		}
		
	}
	
	
	public static final String SENDURL_JSON_HEAD="{"+MyTools.getDoubQuot(JsonAllOption.NameAndValues.JSON_PARAMS)+": {"+MyTools.getDoubQuot("playlist")+
	        ": [{"+MyTools.getDoubQuot("playitem")+ ": [{"+MyTools.getDoubQuot("content")+":";
	public static final String SENDURL_JSON_TAIL_WEB=","+  MyTools.getDoubQuot("type")+":2}]}]},"+ MyTools.getDoubQuot("id")+":1,"
	        + MyTools.getDoubQuot("method")+":"+MyTools.getDoubQuot("mediaPlay")+"}";
	public static final String SENDURL_JSON_TAIL_LOCAL=","+  MyTools.getDoubQuot("type")+":1}]}]},"+ MyTools.getDoubQuot("id")+":1,"
	        + MyTools.getDoubQuot("method")+":"+MyTools.getDoubQuot("mediaPlay")+"}";									
	
	public static String url[]=new String[]{"http://fdfs.xmcdn.com/group2/M01/03/09/wKgDsFFs5AfABrPHAAHGbPFVSkk094.mp3",
											"http://fdfs.xmcdn.com/group2/M00/03/0C/wKgDsFFs6vqArkMuAAe7_mfynPg173.mp3",
											"http://fdfs.xmcdn.com/group2/M02/03/10/wKgDsFFs9bHRvo1eABO_3tZ8gAw206.mp3",
											"http://fdfs.xmcdn.com/group2/M01/03/19/wKgDr1Fs_eXwtVhDAAfd_BguP3g379.mp3",
											"http://fdfs.xmcdn.com/group2/M02/03/1D/wKgDr1FtDkzyEaLiAAxIOY4xpWU839.mp3",
											
											"http://fdfs.xmcdn.com/group2/M01/03/18/wKgDsFFtFoGyluJqAA9q_WzfIc0388.mp3",
											"http://fdfs.xmcdn.com/group2/M01/03/1A/wKgDsFFtHrqwzrJ5AG7Ifq1ijiU439.mp3",
											"http://fdfs.xmcdn.com/group2/M02/03/1F/wKgDsFFtLx3w_FmTAA1Fuv2UVlI111.mp3",
											"http://fdfs.xmcdn.com/group2/M02/03/28/wKgDr1FtN1HACqXGAAiGMdRMHp8866.mp3",
											"http://fdfs.xmcdn.com/group2/M01/03/22/wKgDsFFtP4awvFRoABHlp0CxrpI476.mp3"};
	public static String getSendURLJson1(String url,boolean isWeb){
		String result="";
		if(isWeb){
			 result=SENDURL_JSON_HEAD+ MyTools.getDoubQuot(url)+SENDURL_JSON_TAIL_WEB;
		}else{
			 result=SENDURL_JSON_HEAD+ MyTools.getDoubQuot(url)+SENDURL_JSON_TAIL_LOCAL;
		}
		
		return result;
	}
}
