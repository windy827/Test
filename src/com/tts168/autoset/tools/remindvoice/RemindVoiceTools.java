package com.tts168.autoset.tools.remindvoice;
/**
 * 存放定义的声音ID，供需要播放声音的时候的MediaPlay的content调用
 * @author 袁剑
 *
 */
public class RemindVoiceTools {
/**
	ID	应用场景	提示音内容

	10101	唤醒参数-修改成功	唤醒参数修改成功;
	10102	唤醒参数-修改失败	唤醒参数修改失败;
	10112	提示音参数开启成功	唤醒参数开启成功;
	10113	提示音参数-关闭成功	唤醒参数关闭成功;
	
	10103	唤醒名：Hi云宝	Hi云宝;
	10104	唤醒名：Hi小播	Hi小播;
	10105	唤醒名：云宝	云宝;
	10106	唤醒名：小播	小播;
	10107	唤醒名：Hi，云宝	Hi，云宝;
	10108	唤醒名：Hi，小播	Hi，小播
	10109	唤醒名：百灵
	10110	唤醒名：Hi百灵
	10111	唤醒名：Hi，百灵
 	*/
	public static class AwakeRemindTools{
		public static final int SUCESS_UPDATE_WAKEUP=10101;
		public static final int FAILED_UPDATE_WAKEUP=10102;
		
		public static final int SUCESS_UPDATE_WAKEUP_promp_switch_on=10112;
		public static final int SUCESS_UPDATE_WAKEUP_promp_switch_off=10113;
		
		public static final int NAME1_WAKEUP=10103;
		public static final int NAME2_WAKEUP=10104;
		public static final int NAME3_WAKEUP=10105;
		
		public static final int NAME4_WAKEUP=10106;
		public static final int NAME5_WAKEUP=10107;
		public static final int NAME6_WAKEUP=10108;
		
		public static final int NAME7_WAKEUP=10109;
		public static final int NAME8_WAKEUP=10110;
		public static final int NAME9_WAKEUP=10111;
	}

	/**10201	发音人-设置失败	设置发音人失败;
	10202	发音人-设置成功	设置发音人成功;
	10203	发音人-晓玲	[m3]我是晓玲[m3];
	10204	发音人-尹小坚	[m51]我是尹小坚[m3];
	10205	发音人-易小强	[m52]我是易小强[m3];
	10206	发音人-田蓓蓓	[m53]我是田蓓蓓[m3];
	10207	发音人-唐老鸭	[m54]我是唐老鸭[m3];
	10208	发音人-小燕子	[m55]我是小燕子[m3];
	10301	终端列表查找设备发音的按钮	[m55]我在这[m3]*/
	public static class AnounciatorRemindTools{
		public static final int SUCESS_UPDATE_ANOUCIATOR=10201;
		public static final int FAILED_UPDATE_WAKEUP=10202;
		
		public static final int ANOUCIATOR_XIAOLING=10203;
		public static final int ANOUCIATOR2_YINXIAOJIAN=10204;
		public static final int ANOUCIATOR3_YIXIAOQIANG=10205;
		public static final int ANOUCIATOR4_TIANBEIBEI=10206;
		public static final int ANOUCIATOR5_TAOLAOYA=10207;
		public static final int ANOUCIATOR6_XIAOYANZI=10208;
		public static final int FINDDEVICE=10301;
	}

	/**
	10302	当手机App已连接上云宝设备时	已连接*/
	public static class OthersRemind{
	
		public static final int DEVICE_CONNECTED=10302;
	}
			
	/**11101	闹铃-起床---添加-成功	起床闹铃添加成功
	11102	闹铃-起床---修改-成功	起床闹铃修改成功
	11103	闹铃-起床---删除-成功	起床闹铃删除成功
	11104	闹铃-起床---关闭-成功	起床闹铃关闭成功
	
	11105	闹铃-起床---添加-失败	起床闹铃添加失败
	11106	闹铃-起床---修改-失败	起床闹铃修改失败
	11107	闹铃-起床---删除-失败	起床闹铃删除失败
	11108	闹铃-起床---关闭-失败	起床闹铃关闭失败*/
	public static class AwakeUpAlartRemindTools{
		public static final int SUCESS_ADD_AwakeUpAlart=11101;
		public static final int FAILED_ADD_AwakeUpAlart=11105;
		
		public static final int SUCESS_UPDATE_AwakeUpAlart=11102;
		public static final int FAILED_UPDATE_AwakeUpAlart=11106;
		
		public static final int SUCESS_DELETE_AwakeUpAlart=11103;
		public static final int FAILED_DELETE_AwakeUpAlart=11107;
		
		public static final int SUCESS_CLOSE_AwakeUpAlart=11104;
		public static final int FAILED_CLOSE_AwakeUpAlart=11108;
	}

	/**11201	闹铃-睡眠---添加-成功	睡眠闹铃添加成功
	11202	闹铃-睡眠---修改-成功	睡眠闹铃修改成功
	11203	闹铃-睡眠---删除-成功	睡眠闹铃删除成功
	11204	闹铃-睡眠---关闭-成功	睡眠闹铃关闭成功
	11205	闹铃-睡眠---添加-失败	睡眠闹铃添加失败
	11206	闹铃-睡眠---修改-失败	睡眠闹铃修改失败
	11207	闹铃-睡眠---删除-失败	睡眠闹铃删除失败
	11208	闹铃-睡眠---关闭-失败	睡眠闹铃关闭失败*/
	public static class SleepAlartRemindTools{
		public static final int SUCESS_ADD_SleepAlart=11201;
		public static final int FAILED_ADD_SleepAlart=11205;
		
		public static final int SUCESS_UPDATE_SleepAlart=11202;
		public static final int FAILED_UPDATE_SleepAlart=11206;
		
		public static final int SUCESS_DELETE_SleepAlart=11203;
		public static final int FAILED_DELETE_SleepAlart=11207;
		
		public static final int SUCESS_CLOSE_SleepAlart=11204;
		public static final int FAILED_CLOSE_SleepUpAlart=11208;
	}

	/**11301	闹铃-自定义-添加-成功	自定义闹铃添加成功
	11302	闹铃-自定义-修改-成功	自定义闹铃修改成功
	11303	闹铃-自定义-删除-成功	自定义闹铃删除成功
	11304	闹铃-自定义-关闭-成功	自定义闹铃关闭成功
	11305	闹铃-自定义-添加-失败	自定义闹铃添加失败
	11306	闹铃-自定义-修改-失败	自定义闹铃修改失败
	11307	闹铃-自定义-删除-失败	自定义闹铃删除失败
	11308	闹铃-自定义-关闭-失败	自定义闹铃关闭失败*/
	public static class DefinedAlartRemindTools{
		public static final int SUCESS_ADD_DefinedAlart=11301;
		public static final int FAILED_ADD_DefinedAlart=11305;
		
		public static final int SUCESS_UPDATE_DefinedAlart=11302;
		public static final int FAILED_UPDATE_DefinedAlart=11306;
		
		public static final int SUCESS_DELETE_DefinedAlart=11303;
		public static final int FAILED_DELETE_DefinedAlart=11307;
		
		public static final int SUCESS_CLOSE_DefinedAlart=11304;
		public static final int FAILED_CLOSE_DefinedAlart=11308;
	}
			
	/**11401	闹铃-备忘---添加-成功	明天备忘添加成功；
	11402	闹铃-备忘---修改-成功	明天备忘修改成功；
	11403	闹铃-备忘---删除-成功	明天备忘删除成功；
	11404	闹铃-备忘---关闭-成功	明天备忘关闭成功；
	11405	闹铃-备忘---添加-失败	明天备忘添加失败；
	11406	闹铃-备忘---修改-失败	明天备忘修改失败；
	11407	闹铃-备忘---删除-失败	明天备忘删除失败；
	11408	闹铃-备忘---关闭-失败	明天备忘关闭失败*/
	public static class RemindAlartRemindTools{
		public static final int SUCESS_ADD_RemindAlart=11401;
		public static final int FAILED_ADD_RemindAlart=11405;
		
		public static final int SUCESS_UPDATE_RemindAlart=11402;
		public static final int FAILED_UPDATE_RemindAlart=11406;
		
		public static final int SUCESS_DELETE_RemindAlart=11403;
		public static final int FAILED_DELETE_RemindAlart=11407;
		
		public static final int SUCESS_CLOSE_RemindAlart=11404;
		public static final int FAILED_CLOSE_RemindAlart=11408;
	}

	/** 11501	闹铃-生日---添加-成功	生日添加成功；
		11502	闹铃-生日---修改-成功	生日修改成功；
		11503	闹铃-生日---删除-成功	生日删除成功；
		11504	闹铃-生日---关闭-成功	生日关闭成功；
		11505	闹铃-生日---添加-失败	生日添加失败；
		11506	闹铃-生日---修改-失败	生日修改失败；
		11507	闹铃-生日---删除-失败	生日删除失败；
		11508	闹铃-生日---关闭-失败	生日关闭失败*/
	public static class BirthdayAlartRemindTools{
		public static final int SUCESS_ADD_BirthdayAlart=11501;
		public static final int FAILED_ADD_BirthdayAlart=11505;
		
		public static final int SUCESS_UPDATE_BirthdayAlart=11502;
		public static final int FAILED_UPDATE_BirthdayAlart=11506;
		
		public static final int SUCESS_DELETE_BirthdayAlart=11503;
		public static final int FAILED_DELETE_BirthdayAlart=11507;
		
		public static final int SUCESS_CLOSE_BirthdayAlart=11504;
		public static final int FAILED_CLOSE_BirthdayAlart=11508;
	}
	
	
	
	
	/**
	40101	通用：添加成功;
	40102	通用：修改成功;
	40103	通用：删除成功;
	40104	通用：关闭成功;
	40105	通用：设置成功;
	
	40106	通用：添加失败;
	40107	通用：修改失败;
	40108	通用：删除失败;
	40109	通用：关闭失败;
	40110	通用：设置失败*/
	public static class CommentRemindTools{
		public static final int SUCESS_ADD=40101;
		public static final int FAILED_ADD=40106;
		
		public static final int SUCESS_UPDATE=40102;
		public static final int FAILED_UPDATE=40107;
		
		public static final int SUCESS_DELETE=40103;
		public static final int FAILED_DELETE=40108;
		
		public static final int SUCESS_CLOSE=40104;
		public static final int FAILED_CLOSE=40109;
		
		public static final int SUCESS_SET=40105;
		public static final int FAILED_SET=40110;
	}

}
