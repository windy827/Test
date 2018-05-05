package com.tts168.autoset.tools.player;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.others.TimeTools;
import com.tts168.autoset.tools.others.time.DataTools;

/**
 * 音乐播放的工具类
 * @author 袁剑
 *
 */
public class PlayerTools {

	/**
	 * 当前所在的音乐界面是那种音乐列表界面【喜马拉雅或是本地音乐，其他的界面参数值不用改变】
	 */
	public static int FLAG_MUSIC=-12;
	/**
	 * 当前界面焦点是否需要改变
	 */
	public static boolean focusNeedChange=false;
	/**
	 * 针对PlayerView的工具类
	 * @author 袁剑 
	 *
	 */
	public static class PlayerViewTools{
		/**
		 * 是否正在播放
		 */
		public static boolean isplay=true;
	
		/**
		 * 当前正在播放的音乐在列表中的位置
		 */
		public static int current_index=-1;
		/**
		 * 音乐列表的URL链接
		 */
		public static String PLAYLIST_URLS[]=null;
		/**
		 * 音乐列表的图片URL链接
		 */
		public static String PIC_URLS[]=null;
		/**
		 * 音乐列表的歌曲名称记录
		 */
		public static String PLAYLIST_SONGNAMES[]=null;
		
	}
	
	
	
	
	public static class PlayerKeys{
		/**
		 * 专辑名称
		 */
		public static final String KEY_ALBUM_NAME="name";
		/**
		 * 专辑创建时间
		 */
		public static final String KEY_ALBUM_CREATETIME="time";
	}
	//--------------------------------------------------------------------------------------------------------
		//××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
		//×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	/**
	 * 标记喜欢不喜欢
	 */
	public static class PlayLike{
		
		public static final int PLAYING_LIKE_DRAWABLES[]={R.drawable.unlike,R.drawable.like};
		public static final int FLAG_PLAYING_UNLIKE=0;
		public static final int FLAG_PLAYING_LIKE=1;
		
	} 
	
	
	//××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	//×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	/**
	 * 播放顺序
	 */
	public static class PlayingOrder{
		/**
		 * 播放顺序的索引【全局有效的静态变量】
		 */
		public static int Play_Order_Index=0; 
		/**
		 * 播放顺序
		 */
		public static final int PLAYING_ORDER_DRAWABLES[]={R.drawable.btn_player_cycle_select,R.drawable.btn_player_inorder_select,R.drawable.btn_player_random_select};
		public static final String[]PLAYING_ORDERS_INFO={"单曲循环","顺序播放","随机播放"};
		/**
		 * 单曲循环
		 */
		public static final int FLAG_PLAYING_ORDER_CYCLE=0;
		/**
		 * 顺序播放
		 */
		public static final int FLAG_PLAYING_ORDER_INORDER=1;
		/**
		 * 随机播放
		 */
		public static final int FLAG_PLAYING_ORDER_RANDOM=2;
	}
	
//--------------------------------------------------------------------------------------------------------
	//××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	//×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	/**
	 * 播放和暂停状态
	 */
	public static class PlayingState{
	/**
	 * 播放和暂停
	 */
	public static final int PLAYING_PLAY_DRAWABLES[]={R.drawable.btn_player_play_select,R.drawable.btn_player_pause_select};
	/**
	 * 点击播放
	 */
	public static final int FLAG_PLAYING_PLAY=0;
	/**
	 * 点击暂停
	 */
	public static final int FLAG_PLAYING_PAUSE=1;
}
	
	
	
	
	
	//------------------------------------------------------------------------------------------
	/**
	 * 将秒转换为对应的时长如03:45;
	 * @param durations 时长 单位为秒
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
