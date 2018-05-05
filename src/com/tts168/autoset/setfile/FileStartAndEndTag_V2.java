package com.tts168.autoset.setfile;
/**
 * 第二期
 * 记录文件指定位置标记的起始位置 start, end
 * @author Yuanjian
 * @version
 * @see
 * start-end 值：0-8	数据库版本 ；8-10	数据包大小；10-16备用【1】
16-48	WiFi用户名；48-112	WiFi密码

112-120	发音人选择8
120-122	整点报时开关2
122-124	半点报时开关2
124-128	整点半点报时：开始时间4
128-132	整点半点报时：结束时间4

132-152	天气键单击城市20
152-172	天气键双击城市20
172-192	天气键长按城市20

192-224	笑话键单击类型32
224-256	笑话键双击类型32
256-288	笑话键长按类型32

288-300	WiFi自动获取时间标记12
300-302	节日和祝福开关2
302-304	二十四节气开关2
304-306	播报提前天数2
//共48字节
306-310	第1次播放时间4
310-314	第2次播放时间4
314-318	第3次播放时间4

346-352	备用【2】
//闹铃
352-354	每天闹铃的有效条数2
354-360	备用【3】
//循环10次(366+280)
360-362	【+28×n】	是否开启2
362-366	【+28×n】时间4
366-367	【+28×n】周期1
367-368	【+28×n】5分钟后再提醒标记1
368-388	【+28×n】标题20


640-642	生日等提醒：有效条数2
642-644	生日等提醒：提前天数2
644-648	生日等提醒：第1次播报时间4
648-652	生日等提醒：第2次播报时间4
652-656	生日等提醒：第3次播报时间4
656-664	备用【4】

//重复30次，每次加112个字节[4020]
664-666	【+112*n】是否开启2
666-668	【+112*n】阴阳历类型2
668-676	【+112*n】日期8
676-696	【+112*n】事件 20
696-776	【+112*n】祝福语80

4024-4094	备用【5】
4094-4096	Crc16 校验码
;
 *               */
public class FileStartAndEndTag_V2 {
	/**
	 * start-end 值：0-8	数据库版本 ；8-10	数据包大小；10-16备用
	 */
	
	public static final int VERSION_START=0,VERSION_END=8;
	/**
	 * start-end 值：8-10	数据包大小；
	 */
	public static final int DATASIZE_START=8,DATASIZE_END=10;
	/**
	 * start-end 值：10-16备用
	 */
	public static final int BACKUP1_START=10,BACKUP1_END=16;
	/**
	 * 16-48	WiFi用户名；
	 */
	public static final int SSID_START=16,SSID_END=48;
	/**
	 * 48-112	WiFi密码
	 */
	public static final int PASSWORD_START=48,PASSWORD_END=112;
	/**
	 * 112-120	发音人选择8
	 */
	public static final int ENUNCIATOR_START=112,ENUNCIATOR_END=120;
	/**
	 * 
		120-122	整点报时开关2
		122-124	半点报时开关2
		124-128	整点半点报时：开始时间4
		128-132	整点半点报时：结束时间4
	 */
	public static final int FULL_START=120,FULL_END=122;
	/**
	 * 
		122-124	半点报时开关2
	 */
	public static final int HALF_START=122,HALF_END=124;
	/**
	 * 124-128	整点半点报时：开始时间4
	 */
	public static final int FULL_STARTTIME_START=124,FULL_STARTTIME__END=128;
	/**
	 * 128-132	整点半点报时：结束时间4
	 */
	public static final int HALF_STARTTIME_START=128,HALF_STARTTIME_END=132;
	/**
	 * 132-152	天气键单击城市20
		152-172	天气键双击城市20
		172-192	天气键长按城市20
	 */
	public static final int SHORET_WEARTH_START=132,SHORET_WEARTH_END=152;
	public static final int DOUBLE_WEARTH_START=152,DOUBLE_WEARTH_END=172;
	public static final int LONG_WEARTH_START=172,LONG_WEARTH_END=192;
	
	/**
	 * 	192-224	笑话键单击类型32
		224-256	笑话键双击类型32
		256-288	笑话键长按类型32
	 */
	public static final int SHORET_LAUGH_START=192,SHORET_LAUGH_END=224;
	public static final int DOUBLE_LAUGH_START=224,DOUBLE_LAUGH_END=256;
	public static final int LONG_LAUGH_START=256,LONG_LAUGH_END=288;
	
	/**
	 * 288-300	WiFi自动获取时间标记12
	 */
	public static final int AUTOWIFI_START=288,AUTOWIFI_END=300;
	/**
	 * 300-302	节日和祝福开关2
	 */
	public static final int FESTIVAL_AND_BLESS_SWITCH_START=300,FESTIVAL_AND_BLESS_SWITCH_END=302;
	/**
	 * 302-304	二十四节气开关2
	 */
	public static final int JIEQI_SWITCH_START=302,JIEQI_SWITCH_END=304;
	/**
	 * 304-306	播报提前天数2
	 */
	public static final int PLAY_AHEAD_DAYS_START=304,PLAY_AHEAD_DAYS_END=306;
	
	/**
	 * //共48字节
		306-310	第1次播放时间4
		310-314	第2次播放时间4
		314-318	第3次播放时间4
		346-352	备用(2)6
	 */
	public static final int PLAY_FIRST_TIME_START=306,PLAY_FIRST_TIME_END=310;
	public static final int PLAY_SECOND_TIME_START=310,PLAY_SECOND_TIME_END=314;
	public static final int PLAY_THIRD_TIME_START=314,PLAY_THIRD_TIME_END=318;
	public static final int BACKUP2_START=346,BACKUP2_END=352;
	
	/**
	 * //闹铃 352-354	每天闹铃的有效条数2
	 */
	
	public static final int DAILYALART_EFFECT_TOTAL_START=352,DAILYALART_EFFECT_TOTAL_END=354;
	/**
	 * 354-360	备用(3)
	 */
	public static final int BACKUP3_START=354,BACKUP3_END=360;
	
	/**
	 * //循环10次(366+280)
		360-362	【+28×DAILY_ALART_NUM】	是否开启2
		362-366	【+28×DAILY_ALART_NUM】时间4
		366-367	【+28×DAILY_ALART_NUM】周期1
		367-368	【+28×DAILY_ALART_NUM】5分钟后再提醒标记1
		368-388	【+28×DAILY_ALART_NUM】标题20
	 */
	/**
	 * 记录是第几条（根据有效条数进行判断  DAILYALART_EFFECT_TOTAL获取+1，大于十则覆盖第一条）
	 */
	public static int DAILY_ALART_NUM=0;
	/**
	 *360-362	【+28×DAILY_ALART_NUM】	是否开启2字节
	 */
	public static int IS_DAILY_ALART_OPEN_START,IS_DAILY_ALART_OPEN_END;
	/**
	 *362-366	【+28×DAILY_ALART_NUM】时间4字节
	 */
	public static int DAILY_ALART_TIME_START,DAILY_ALART_TIME_END;
	/**
	 * 366-367	【+28×DAILY_ALART_NUM】周期1字节
	 */
	public static int DAILY_ALART_CYCLE_START,DAILY_ALART_CYCLE_END;
	/**
	 * 5分钟后再提醒标记1字节
	 */
	public static int DAILY_ALART_REMIND_START,DAILY_ALART_REMIND_END;
	/**
	 * 368-388	【+28×DAILY_ALART_NUM】标题20字节
	 */
	public static int DAILY_ALART_TITLE_START,DAILY_ALART_TITLE_END;
	
	/**
	 * 640-642	生日等提醒：有效条数2
		642-644	生日等提醒：提前天数2
		644-648	生日等提醒：第1次播报时间4
		648-652	生日等提醒：第2次播报时间4
		652-656	生日等提醒：第3次播报时间4
		656-664	备用(4)8
	 */
	/**
	 * 闹铃 640-642	生日等提醒的有效条数2字节
	 */
	public static final int BIRTHDAY_EFFECT_TOTAL_START=640,BIRTHDAY_EFFECT_TOTAL_END=642;
	/**
	 * 642-644	生日等提醒：提前天数2字节
	 */
	public static final int BIRTHDAY_AHEAD_DAYS_START=642,BIRTHDAY_AHEAD_DAYS_END=644;
	/**
	 * 644-648	生日等提醒：第1次播报时间4字节
	 */
	public static final int BIRTHDAY_FIRST_TIME_START=644,BIRTHDAY_FIRST_TIME_END=648;
	/**
	 * 648-652	生日等提醒：第2次播报时间4字节
	 */
	public static final int BIRTHDAY_SECOND_TIME_START=648,BIRTHDAY_SECOND_TIME_END=652;
	/**
	 * 652-656	生日等提醒：第3次播报时间4字节
	 */
	public static final int BIRTHDAY_THIRD_TIME_START=652,BIRTHDAY_THIRD_TIME_END=656;
	/**
	 * 656-664	备用(4)8字节
	 */
	public static final int BACKUP4_START=656,BACKUP4_END=664;
	
	/**
	 * //重复30次，每次加112个字节[4020]
664-666	【+112*n】是否开启2
666-668	【+112*n】阴阳历类型2
668-676	【+112*n】日期8
676-696	【+112*n】事件 20
696-776	【+112*n】祝福语80

	 */
	/**
	 * 记录是第几条（根据有效条数进行判断  BIRTHDAY_EFFECT_TOTAL获取+1，大于30则覆盖第一条）
	 */
	public static int BIRTHDAY_ALART_NUM=0;
	/**
	 *664-666	【+112*n】是否开启2字节
	 */
	public static int IS_BIRTHDAY_ALART_OPEN_START,IS_BIRTHDAY_ALART_OPEN_END;
	/**
	 *666-668	【+112*n】阴阳历类型2字节
	 */
	public static int BIRTHDAY_ALART_TIME_TYPE_START,BIRTHDAY_ALART_TIME_TYPE_END;
	/**
	 * 668-676	【+112*n】日期8字节
	 */
	public static int BIRTHDAY_ALART_DFATE_START,BIRTHDAY_ALART_DFATE_END;
	
	/**
	 * 676-696	【+112*n】事件 20字节
	 */
	public static int BIRTHDAY_ALART_TITLE_START,BIRTHDAY_ALART_TITLE_END;
	/**
	 * 696-776	【+112*n】祝福语80
	 */
	public static int BIRTHDAY_ALART_CONTENT_START,BIRTHDAY_ALART_CONTENT_END;
	
	/**
	 * 4024-4094	备用
	 */
	public static final int BACKUP5_START=4024,BACKUP5_END=4094;
	/**
	 * 4094-4096	Crc16 校验码
	 */
	public static final int Crc16_START=4094,Crc16_END=4096;
}
