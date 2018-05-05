package com.tts168.autoset.tools.mainmenu;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
/**
 * 高级设置的工具类
 * @author 袁剑
 *20150115 20：51
 */

public class MainMenuTools {
	/**
	 * 点击GRIDVIEW时根据下面的参数进行索引
	 */
	//×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	public static final int MYDEVICE=0;//我的设备
	public static final int HIGH_SET=1;//高级设置
//	public static final int TICKLER=2;//备忘记事
//	public static final int AnswerHelper=19;//问答助手
//	public static final int WEBMUSIC=3;//网络音乐
	public static final int ANOUTUS=3;//帮助
	public static final int LOCALMUSIC=2;//本地音乐
	
	
	public static final int BAIDUMUSIC=15;//百度音乐

	public static final int BAILINGBUY=17;//百灵乐购
	public static final int HOMEDEVICE=18;//家电控制

	
	

//	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.tickler,R.drawable.answerhelper,
//		R.drawable.webmusic,R.drawable.baidumusic,R.drawable.localmusic,R.drawable.bailingbuy,R.drawable.homedevices,R.drawable.aboutus};
//	public static final String pic_names[]={"我的设备","高级设置","百灵闹钟","问答助手","喜马拉雅","百度音乐","本地音乐","百灵乐购","家电控制","关于云宝"};
	//××××××××××××××××××××××××××××××××××测试1×××××××××××××××××××××××××××××××××××××××××
	
//	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.tickler,R.drawable.answerhelper,
//		R.drawable.webmusic,R.drawable.aboutus};
//	public static final String pic_names[]={"我的设备","高级设置","百灵闹钟","问答助手","喜马拉雅","关于云宝"};
//	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.tickler,R.drawable.webmusic,R.drawable.answerhelper
//		};
	//public static final String pic_names[]={"我的设备","高级设置","百灵闹钟","喜马拉雅","问答助手"};
//	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.tickler,R.drawable.webmusic,R.drawable.localmusic,R.drawable.aboutus};
//	public static final String pic_names[]={"我的设备","高级设置","百灵闹钟","喜马拉雅","本地音乐","关于百灵"};
	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.localmusic,R.drawable.aboutus};
	public static final String pic_names[]={"我的设备","高级设置","本地音乐","关于百灵"};
	//×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	/**
	 * 得到用来在GridView里面显示的内容（图片，和图片对应的名称）
	 * @return
	 */
	public static ArrayList<HashMap<String,Object>> getShowMainMenuInfo(){
		ArrayList<HashMap<String,Object>> info=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<drawables.length;i++){
			HashMap<String,Object>temp=new HashMap<String, Object>();
			temp.put("drawable", drawables[i]);
			temp.put("name", pic_names[i]);
			info.add(temp);
		}
		return info;
	}
}
