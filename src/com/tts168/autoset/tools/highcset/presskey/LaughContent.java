package com.tts168.autoset.tools.highcset.presskey;

import java.util.ArrayList;
import java.util.HashMap;

public class LaughContent {
	/**
	 * 存放笑话设置的设置项的内容
	 * @return
	 */
	public static   ArrayList<HashMap<String, String>> getData() {
		String JokeDataSet[] = new String[]{
				"学生笑话",  "男孩笑话",  "女孩笑话",  "老人笑话",  "女人笑话",  
				"男人笑话",  "幽默笑话",  "爆笑笑话",  "爱情笑话",  "恋爱笑话",  
				"求爱笑话",  "冷笑话",    "成人笑话",  "夫妻笑话",  "黄色笑话"};
		ArrayList<HashMap<String, String>> al=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<JokeDataSet.length;i++){
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put("name", JokeDataSet[i]);
			al.add(hm);
		}
		
		return al;
	}
}
