package com.tts168.autoset.tools.others;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 得到设置时间的ArrayList
 * @author 袁剑
 *
 */
public class TimeSetContent {
	public static final String KEY_NAME="name";
	public static final String SPLIT_CHAR_MIN="分钟";
	public static final String SPLIT_CHAR="秒";
	/**
	 * 存放睡眠时长设置的设置项的内容
	 * @return
	 */
	public static   ArrayList<HashMap<String, String>> getSleepTimeData() {
		String JokeDataSet[] = new String[]{
				"30秒",  "60秒",  "120秒",  "300秒"};
		ArrayList<HashMap<String, String>> al=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<JokeDataSet.length;i++){
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put(KEY_NAME, JokeDataSet[i]);
			al.add(hm);
		}
		
		return al;
	}
}
