package com.tts168.autoset.tools.highcset;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
/**
 * �߼����õĹ�����
 * @author Ԭ��
 *20150115 20��51
 */

public class FuncSetTools {
	
	/**
	 * ���GRIDVIEWʱ��������Ĳ�����������
	 */

	/**
	 *��������
	 */
	public static final int PERSONAL_AWAKESET=0;
//	/**
//	 * ˯����������
//	 */
//	public static final int SLEEPSET=1;
//	/**
//	 * ���س�������
//	 */
//	public static final int LOCALCITY=2;
	/**
	 * ���ſ���
	 */
	public static final int NIGHTCONTROL=1;
//	/**
//	 *��������
//	 */
//	public static final int OTHERSET=4;

//	public static final int drawables[]={R.drawable.wakeupset,R.drawable.sleepmusic,R.drawable.individuation,R.drawable.presskey,
//		R.drawable.fullread,R.drawable.saveernergy_control};
//	public static final String pic_names[]={"���ѿ���","˯ǰ��������","���Ի�����","��������","���㱨ʱ","���ܿ���"};
//	public static final int drawables[]={R.drawable.wakeupset,R.drawable.sleepmusic,R.drawable.localcity,R.drawable.undisturbed,R.drawable.otherset};
//	public static final String pic_names[]={"��������","˯ǰ��������","���س���","���ſ���","��������"};
	public static final int drawables[]={R.drawable.wakeupset,R.drawable.undisturbed};
	public static final String pic_names[]={"��������","���ſ���"};
	/**
	 * �õ�������GridView������ʾ�����ݣ�ͼƬ����ͼƬ��Ӧ�����ƣ�
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
