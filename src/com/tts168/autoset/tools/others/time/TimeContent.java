package com.tts168.autoset.tools.others.time;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * ���ʱ�����õ������������
 * @return
 */
public class TimeContent {
	/**
	 * ���ʱ�����õ������������
	 * @return
	 */
	public static   ArrayList<HashMap<String, String>> getData() {
		String JokeDataSet[] = new String[]{
				"0��",  "1��",  "2��",  "3��",  "5��",  
				"7��",  "10��"};
		ArrayList<HashMap<String, String>> al=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<JokeDataSet.length;i++){
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put("name", JokeDataSet[i]);
			al.add(hm);
		}
		
		return al;
	}
}
