package com.tts168.autoset.tools.highcset.presskey;

import java.util.ArrayList;
import java.util.HashMap;

public class LaughContent {
	/**
	 * ���Ц�����õ������������
	 * @return
	 */
	public static   ArrayList<HashMap<String, String>> getData() {
		String JokeDataSet[] = new String[]{
				"ѧ��Ц��",  "�к�Ц��",  "Ů��Ц��",  "����Ц��",  "Ů��Ц��",  
				"����Ц��",  "��ĬЦ��",  "��ЦЦ��",  "����Ц��",  "����Ц��",  
				"��Ц��",  "��Ц��",    "����Ц��",  "����Ц��",  "��ɫЦ��"};
		ArrayList<HashMap<String, String>> al=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<JokeDataSet.length;i++){
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put("name", JokeDataSet[i]);
			al.add(hm);
		}
		
		return al;
	}
}
