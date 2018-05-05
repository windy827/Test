package com.tts168.autoset.tools.player;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.others.TimeTools;
import com.tts168.autoset.tools.others.time.DataTools;

/**
 * ���ֲ��ŵĹ�����
 * @author Ԭ��
 *
 */
public class PlayerTools {

	/**
	 * ��ǰ���ڵ����ֽ��������������б���桾ϲ�����Ż��Ǳ������֣������Ľ������ֵ���øı䡿
	 */
	public static int FLAG_MUSIC=-12;
	/**
	 * ��ǰ���潹���Ƿ���Ҫ�ı�
	 */
	public static boolean focusNeedChange=false;
	/**
	 * ���PlayerView�Ĺ�����
	 * @author Ԭ�� 
	 *
	 */
	public static class PlayerViewTools{
		/**
		 * �Ƿ����ڲ���
		 */
		public static boolean isplay=true;
	
		/**
		 * ��ǰ���ڲ��ŵ��������б��е�λ��
		 */
		public static int current_index=-1;
		/**
		 * �����б��URL����
		 */
		public static String PLAYLIST_URLS[]=null;
		/**
		 * �����б��ͼƬURL����
		 */
		public static String PIC_URLS[]=null;
		/**
		 * �����б�ĸ������Ƽ�¼
		 */
		public static String PLAYLIST_SONGNAMES[]=null;
		
	}
	
	
	
	
	public static class PlayerKeys{
		/**
		 * ר������
		 */
		public static final String KEY_ALBUM_NAME="name";
		/**
		 * ר������ʱ��
		 */
		public static final String KEY_ALBUM_CREATETIME="time";
	}
	//--------------------------------------------------------------------------------------------------------
		//������������������������������������������������������������������������������������������������������������������������������������������������������������
		//����������������������������������������������������������������������������������������������������������������������������������
	/**
	 * ���ϲ����ϲ��
	 */
	public static class PlayLike{
		
		public static final int PLAYING_LIKE_DRAWABLES[]={R.drawable.unlike,R.drawable.like};
		public static final int FLAG_PLAYING_UNLIKE=0;
		public static final int FLAG_PLAYING_LIKE=1;
		
	} 
	
	
	//������������������������������������������������������������������������������������������������������������������������������������������������������������
	//����������������������������������������������������������������������������������������������������������������������������������
	/**
	 * ����˳��
	 */
	public static class PlayingOrder{
		/**
		 * ����˳���������ȫ����Ч�ľ�̬������
		 */
		public static int Play_Order_Index=0; 
		/**
		 * ����˳��
		 */
		public static final int PLAYING_ORDER_DRAWABLES[]={R.drawable.btn_player_cycle_select,R.drawable.btn_player_inorder_select,R.drawable.btn_player_random_select};
		public static final String[]PLAYING_ORDERS_INFO={"����ѭ��","˳�򲥷�","�������"};
		/**
		 * ����ѭ��
		 */
		public static final int FLAG_PLAYING_ORDER_CYCLE=0;
		/**
		 * ˳�򲥷�
		 */
		public static final int FLAG_PLAYING_ORDER_INORDER=1;
		/**
		 * �������
		 */
		public static final int FLAG_PLAYING_ORDER_RANDOM=2;
	}
	
//--------------------------------------------------------------------------------------------------------
	//������������������������������������������������������������������������������������������������������������������������������������������������������������
	//����������������������������������������������������������������������������������������������������������������������������������
	/**
	 * ���ź���ͣ״̬
	 */
	public static class PlayingState{
	/**
	 * ���ź���ͣ
	 */
	public static final int PLAYING_PLAY_DRAWABLES[]={R.drawable.btn_player_play_select,R.drawable.btn_player_pause_select};
	/**
	 * �������
	 */
	public static final int FLAG_PLAYING_PLAY=0;
	/**
	 * �����ͣ
	 */
	public static final int FLAG_PLAYING_PAUSE=1;
}
	
	
	
	
	
	//------------------------------------------------------------------------------------------
	/**
	 * ����ת��Ϊ��Ӧ��ʱ����03:45;
	 * @param durations ʱ�� ��λΪ��
	 * @return
	 */
	public static String getSongDurations(int durations){
		String time="00:00";
		int min=durations/TimeTools.S2Min;
		int s=durations%TimeTools.S2Min;
		time=DataTools.getStrFormat2StrDate(min+"")+":"+DataTools.getStrFormat2StrDate(s+"");
		return time;
	}
	
}
