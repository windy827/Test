package com.tts168.autoset.tools;
/**
 * 一些全局的功能模块的开关
 * @author 袁剑
 *
 */
public class FuncSwitch {
	public static final String TAG="FuncSwitch";
	/**
	 * 是否允许打印，在正式发布的版本中为了提高速度可以去除打印
	 */
	public static final boolean SWITCH_LOGCAT=true;

	/**
	 * UDP扫描，TCP设备刷新数据记录｛数据库记录｝
	 */
	public static final boolean SWITCH_TCPUDP_CONNECTTEST=false;
	/**
	 * 记录TCP断网｛数据库记录｝
	 */
	public static final boolean SWITCH_TCP_RECORD_DISCONNECT=false;
	/**
	 * TCP 心跳开关
	 */
	public static final boolean SWITCH_TCP_HEARTBEAT=true;
}
