package com.tts168.autoset.tools.tcpAndudp;
/**
 * UDP的工具类
 * @author 袁剑
 *
 */
public class UDPTools {
    /**
      * 手机发送UDP的时间间隔,单位为秒
    */
	public static int Time_Send=1;
	/**
	 * UDP 发送睡眠的单位时间【毫秒】
	 */
	public static final int TIME_SEND_SLEEP_EACH=1000;
	/**
	 * 每1*TIME_SEND_SLEEP_EACH毫秒发送一次
	 */
	public static final int TIME_SEND_SLEEP_SEARCH=1;
	/**
	 * 每60*TIME_SEND_SLEEP_EACH毫秒发送一次
	 */
	public static final int TIME_SEND_SLEEP_OTHER=5;
	/**
	 * 每5*TIME_SEND_SLEEP_EACH毫秒发送一次
	 */
	public static final int TIME_SEND_SLEEP_TERMINAL=5;
	/**
	 * 每2*TIME_SEND_SLEEP_EACH毫秒发送一次
	 */
	public static final int TIME_SEND_SLEEP_WIFISENDWAIT=2;
	
}
