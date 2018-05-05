package com.tts168.autoset.tools.others.time;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 存放时间设置的设置项的内容
 * @return
 */
public class TimeContent {
	/**
	 * 存放时间设置的设置项的内容
	 * @return
	 */
	public static   ArrayList<HashMap<String, String>> getData() {
		String JokeDataSet[] = new String[]{
				"0天",  "1天",  "2天",  "3天",  "5天",  
				"7天",  "10天"};
		ArrayList<HashMap<String, String>> al=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<JokeDataSet.length;i++){
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put("name", JokeDataSet[i]);
			al.add(hm);
		}
		
		return al;
	}
}
