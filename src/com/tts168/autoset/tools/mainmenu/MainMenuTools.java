package com.tts168.autoset.tools.mainmenu;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
/**
 * �߼����õĹ�����
 * @author Ԭ��
 *20150115 20��51
 */

public class MainMenuTools {
	/**
	 * ���GRIDVIEWʱ��������Ĳ�����������
	 */
	//����������������������������������������������������������������������������������������������������������������������������������������������
	public static final int MYDEVICE=0;//�ҵ��豸
	public static final int HIGH_SET=1;//�߼�����
//	public static final int TICKLER=2;//��������
//	public static final int AnswerHelper=19;//�ʴ�����
//	public static final int WEBMUSIC=3;//��������
	public static final int ANOUTUS=3;//����
	public static final int LOCALMUSIC=2;//��������
	
	
	public static final int BAIDUMUSIC=15;//�ٶ�����

	public static final int BAILINGBUY=17;//�����ֹ�
	public static final int HOMEDEVICE=18;//�ҵ����

	
	

//	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.tickler,R.drawable.answerhelper,
//		R.drawable.webmusic,R.drawable.baidumusic,R.drawable.localmusic,R.drawable.bailingbuy,R.drawable.homedevices,R.drawable.aboutus};
//	public static final String pic_names[]={"�ҵ��豸","�߼�����","��������","�ʴ�����","ϲ������","�ٶ�����","��������","�����ֹ�","�ҵ����","�����Ʊ�"};
	//������������������������������������������������������������������������1����������������������������������������������������������������������������������
	
//	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.tickler,R.drawable.answerhelper,
//		R.drawable.webmusic,R.drawable.aboutus};
//	public static final String pic_names[]={"�ҵ��豸","�߼�����","��������","�ʴ�����","ϲ������","�����Ʊ�"};
//	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.tickler,R.drawable.webmusic,R.drawable.answerhelper
//		};
	//public static final String pic_names[]={"�ҵ��豸","�߼�����","��������","ϲ������","�ʴ�����"};
//	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.tickler,R.drawable.webmusic,R.drawable.localmusic,R.drawable.aboutus};
//	public static final String pic_names[]={"�ҵ��豸","�߼�����","��������","ϲ������","��������","���ڰ���"};
	public static final int drawables[]={R.drawable.mydevice,R.drawable.highset,R.drawable.localmusic,R.drawable.aboutus};
	public static final String pic_names[]={"�ҵ��豸","�߼�����","��������","���ڰ���"};
	//������������������������������������������������������������������������������������������������������������������������������������������������������
	/**
	 * �õ�������GridView������ʾ�����ݣ�ͼƬ����ͼƬ��Ӧ�����ƣ�
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
