package com.tts168.autoset.tools.others;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * �õ�����ʱ���ArrayList
 * @author Ԭ��
 *
 */
public class TimeSetContent {
	public static final String KEY_NAME="name";
	public static final String SPLIT_CHAR_MIN="����";
	public static final String SPLIT_CHAR="��";
	/**
	 * ���˯��ʱ�����õ������������
	 * @return
	 */
	public static   ArrayList<HashMap<String, String>> getSleepTimeData() {
		String JokeDataSet[] = new String[]{
				"30��",  "60��",  "120��",  "300��"};
		ArrayList<HashMap<String, String>> al=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<JokeDataSet.length;i++){
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put(KEY_NAME, JokeDataSet[i]);
			al.add(hm);
		}
		
		return al;
	}
}
