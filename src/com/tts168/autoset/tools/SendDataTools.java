package com.tts168.autoset.tools;
/** 
 * 1.数据库版本  DB_VERSION;
 * 2.数据包大小 PACKAGE_SIZE;
 * 3.WiFi用户名 WIFI_NAME;
 * 4.WiFi密码 WIFI_PASSWORD;
 * 5.发音人选择 ANOUNCER;
 * 6.整点报时开关 SWICH_FULL_READTIME;
 * 7.半点报时开关 SWICH_HALF_READTIME;
 * 8.整点半点报时：开始时间 FULL_READTIME_START；
 * 9.整点半点报时：结束时间 FULL_READTIME_END;
 * 10.天气键单击城市 CITY_CLICK;
 * 11.天气键双击城市 CITY_DOU_CLICK;
 * 12.天气键长按城市 CITY_LONG_PRESS;
 * 13.笑话键单击类型 LAUGH_CLICK;
 * 14.笑话键双击类型 LAUGH_DOU_CLICK;
 * 15.笑话键长按类型 LAUGH_LONG_PRESS;
 * 16.WiFi自动获取时间标记【12】 ISAutoGetWifiTime;
 * 17.节日和祝福开关 SWICH_FESTIVE;
 * 18.二十四节气开关 SWICH_JIEQI;
 * 19.播报提前天数 FESTIVE_AHEAD_DAYS;
 * 20.第1次播放时间 FESTIVE_PLAYTIME1;
 * 21.第2次播放时间 FESTIVE_PLAYTIME2;
 * 22.第3次播放时间 FESTIVE_PLAYTIME3;
 //-------闹钟部分【10条的空间重复的类型】
 * 23.是否开启 SWITCH_DAILY_ALART_ISOPEN;
 * 24.时间 DAILY_ALART_ISOPEN;
 * 25.周期 DAILY_ALART_CYCLE;
 * 26.5分钟后再提醒标记 DAILY_ALART_ISREREMIND;
 * 27.标题 DAILY_ALART_INCIDENT;
 //生日提醒
  * 28.生日等提醒：有效条数 COMMERATION_ALART_EFFCTIVE_TOTAL;
  * 29.生日等提醒：有效条数提前天数 COMMERATION_ALART_AHEAD_DAYS;
  * 30.生日等提醒：第1次播报时间 COMMERATION_ALART_PLAYTIME1;
  * 31.生日等提醒：第2次播报时间 COMMERATION_ALART_PLAYTIME2;
  * 32.生日等提醒：第3次播报时间 COMMERATION_ALART_PLAYTIME3;
     //-------生日闹铃内容部分【30条的空间重复的类型】
  * 33.是否开启 SWITCH_COMMERATION_ALART_ISOPEN;
  * 34.阴阳历类型 COMMERATION_ALART_TYPE;
  * 35.日期 COMMERATION_ALART_DATE;
  * 36.事件 COMMERATION_ALART_INCIDENT;
  * 37.祝福语 COMMERATION_ALART_DESCRIBE;
 */
public class SendDataTools {
	/**
	 * 1.数据库版本
	 */
	public static String DB_VERSION;
	/**
	 * 2.数据包大小
	 */
	public static int PACKAGE_SIZE;
	/**
	 * 3.WiFi用户名
	 */
	public static String WIFI_NAME;
	/**
	 * 4.WiFi密码
	 */
	public static String WIFI_PASSWORD;
	/**
	 *5.发音人选择[8]
	 */
	public static String ANOUNCER;
	
	/**
	 * 6.整点报时开关
	 */
	public static boolean SWICH_FULL_READTIME;
	/**
	 * 7.半点报时开关
	 */
	public static boolean SWICH_HALF_READTIME;
	/**
	 * 8.整点半点报时：开始时间
	 */
	public static String FULL_READTIME_START;
	/**
	 * 9.整点半点报时：结束时间
	 */
	public static String FULL_READTIME_END;
	/**
	 * 10.天气键单击城市
	 */
	public static String CITY_CLICK;
	/**
	 * 11.天气键双击城市
	 */
	public static String CITY_DOU_CLICK;
	/**
	 * 12.天气键长按城市
	 */
	public static String CITY_LONG_PRESS;
	/**
	 * 13.笑话键单击城市
	 */
	public static String LAUGH_CLICK;
	/**
	 * 14.笑话键双击城市
	 */
	public static String LAUGH_DOU_CLICK;
	/**
	 * 15.笑话键长按城市
	 */
	public static String LAUGH_LONG_PRESS;
	/**
	 * 16.WiFi自动获取时间标记【12】记录WiFi自动获取时间标记的数组，共十二个，标记了记为1，为标记记为0
	 */
	public static int []ISAutoGetWifiTime;
	/**
	 * 自动获取Wifi时间
	 */
	public static final int ISAutoGetWifiTime_YES=1;
	/**
	 * 不自动获取Wifi时间
	 */
	public static final int ISAutoGetWifiTime_NO=0;
	/**
	 * 17.节日和祝福开关
	 */
	public static int SWICH_FESTIVE;
	/**
	 * 18.二十四节气开关
	 */
	public static int SWICH_JIEQI;
	
	/**
	 * 19.播报提前天数
	*/
	public static int FESTIVE_AHEAD_DAYS;
	/**
	 * 20.第1次播放时间
	*/
	public static String FESTIVE_PLAYTIME1;
	/**
	 * 21.第2次播放时间
	*/
	public static String FESTIVE_PLAYTIME2;
	/**
	 * 22.第3次播放时间
	*/
	public static String FESTIVE_PLAYTIME3;
	
	
	 //-------闹钟部分【10条的空间重复的类型】
	/*
	*23.是否开启
	 * 24.时间
	 * 25.周期
	 * 26.5分钟后再提醒标记
	 * 27.标题
	 */
	/**
	 * 23.是否开启
	 */
	public static int SWITCH_DAILY_ALART_ISOPEN;
	/**
	 * 限取的每日闹铃的条数
	 */
	public static final int DAILY_ALART_LIMIT_TOTAL=10; 
	/**
	 * 24.时间
	 */
	public static String DAILY_ALART_ISOPEN;
	/**
	 * 25.周期[1]
	 */
	public static int DAILY_ALART_CYCLE;
	/**
	 * 26.5分钟后再提醒标记[1]
	 */
	public static int DAILY_ALART_ISREREMIND;
	/**
	 * 27.标题
	 */
	public static String DAILY_ALART_INCIDENT;
	
	
	//生日提醒
	  /* 28.生日等提醒：有效条数
	  * 29.生日等提醒：有效条数提前天数
	  * 30.生日等提醒：第1次播报时间
	  * 31.生日等提醒：第2次播报时间
	  * 32.生日等提醒：第3次播报时间
	  * */
	/**
	 * 28.生日等提醒：有效条数[2]
	 */
	public static int COMMERATION_ALART_EFFCTIVE_TOTAL;
	/**
	 * 29.生日等提醒：有效条数提前天数
	 */
	public static int COMMERATION_ALART_AHEAD_DAYS;
	/**
	 * 30.生日等提醒：第1次播报时间
	*/
	public static String COMMERATION_ALART_PLAYTIME1;
	/**
	 * 31.生日等提醒：第2次播报时间
	*/
	public static String COMMERATION_ALART_PLAYTIME2;
	/**
	 * 32.生日等提醒：第3次播报时间
	*/
	public static String COMMERATION_ALART_PLAYTIME3;
	     //-------生日闹铃内容部分【30条的空间重复的类型】
	  /* 33.是否开启
	  * 34.阴阳历类型
	  * 35.日期
	  * 36.事件
	  * 37.祝福语
	  */
	/**
	 * 限取的每日闹铃的条数
	 */
	public static final int COMMERATION_ALART_LIMIT_TOTAL=30; 
	/**
	 * 33.是否开启
	 */
	public static int SWITCH_COMMERATION_ALART_ISOPEN;
	/**
	 * 34.阴阳历类型
	 */
	public static int COMMERATION_ALART_TYPE;
	/**
	 * 35.日期
	 */
	public static String COMMERATION_ALART_DATE;
	/**
	 * 36.事件
	 */
	public static int COMMERATION_ALART_INCIDENT;
	/**
	 * 37.祝福语
	 */
	public static int COMMERATION_ALART_DESCRIBE;
	
	/**
	 * 将07:30转换成0730
	 * @param time
	 * @return
	 */
	public static String getFormatTime(String time){
		String formatTime="";
		formatTime=time.split(Tools.FLAG_TIME_MAOHAO)[0]+time.split(Tools.FLAG_TIME_MAOHAO)[1];
		return formatTime;
	}
	
	/**
	 * 根据true或false 转换成大小为2 的byte 数组//1存放在前面的字节上
	 * @param isTrue
	 * @return
	 */
		public static byte[]boolean2ByteArray2(boolean isTrue){
			byte[]result=new byte[2];
			if(isTrue){
				result[0]=1;
			}
			return result;
		}
		/**
		 * 根据大小为2的byte数组转换成true或false//1存放在前面的字节上
		 * @param isTrue
		 * @return
		 */
		public static boolean byteArray2Boolean(byte[]isTrue){
			boolean result=false;
			if(isTrue[0]==1){
				result=true;
			}
			return result;
		}
	
	
	
}
