package com.autoset.json;
/**
 * 关于Json传输中所有ID号的管理，防止出现重复ID而导致功能紊乱
 * @author 袁剑
 *
 */
public class ID_Manager {

	/**
	 * 删除操作对应的ID
	 */
	public static final int ID_DELETE_ALART=7000;
	
	/**
	 * 音乐播放的状态回复ID号
	 */
	public static final int ID_PLAYSTATE_RESUME=9900;
	public static final int ID_PLAYSTATE_PAUSE=9901;
	public static final int ID_PLAYSTATE_STOP=9902;
	
	/**
	 * alart
	 */
	public static final int ID_ALART_RINGCHOOSE=8000;
	
	public static final int ID_HeartBeat=9999;//心跳检测特有ID号，其他Json不能共用
}
