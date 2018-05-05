package com.tts168.autoset.tools.highcset;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
/**
 * 高级设置的工具类
 * @author 袁剑
 *20150115 20：51
 */

public class FuncSetTools {
	
	/**
	 * 点击GRIDVIEW时根据下面的参数进行索引
	 */

	/**
	 *唤醒名称
	 */
	public static final int PERSONAL_AWAKESET=0;
//	/**
//	 * 睡眠音乐设置
//	 */
//	public static final int SLEEPSET=1;
//	/**
//	 * 本地城市设置
//	 */
//	public static final int LOCALCITY=2;
	/**
	 * 免扰控制
	 */
	public static final int NIGHTCONTROL=1;
//	/**
//	 *其它设置
//	 */
//	public static final int OTHERSET=4;

//	public static final int drawables[]={R.drawable.wakeupset,R.drawable.sleepmusic,R.drawable.individuation,R.drawable.presskey,
//		R.drawable.fullread,R.drawable.saveernergy_control};
//	public static final String pic_names[]={"唤醒控制","睡前音乐设置","个性化设置","按键设置","整点报时","节能控制"};
//	public static final int drawables[]={R.drawable.wakeupset,R.drawable.sleepmusic,R.drawable.localcity,R.drawable.undisturbed,R.drawable.otherset};
//	public static final String pic_names[]={"唤醒名称","睡前音乐设置","本地城市","免扰控制","其它设置"};
	public static final int drawables[]={R.drawable.wakeupset,R.drawable.undisturbed};
	public static final String pic_names[]={"唤醒名称","免扰控制"};
	/**
	 * 得到用来在GridView里面显示的内容（图片，和图片对应的名称）
	 * @return
	 */
	public static ArrayList<HashMap<String,Object>> getShowFuncSetInfo(){
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
