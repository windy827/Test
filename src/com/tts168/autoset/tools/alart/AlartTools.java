package com.tts168.autoset.tools.alart;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.amo.demo.arrWheelview.StrArrayWheelAdapter;
import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.getupset.GetUpSetEntity;
import com.autoset.jni.remind.RemindEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.others.converopt.BinaryIntArray2ByteTools;
import com.tts168.autoset.tools.others.converopt.ConvertToByteArray;
import com.tts168.autoset.tools.others.converopt.StrBinaryTurn;
import com.tts168.autoset.tools.others.time.LunarTextChineseToNumString;
import com.tts168.autoset.tools.others.time.LunarToSolarTools;
import com.tts168.autoset.tools.others.time.SolarToLunarTools;
import com.tts168.autoset.tools.tcpAndudp.ConvertTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.alart.RemindAlartView;

/**
 * 闹铃设置的工具类
 * 
 * @author 袁剑
 * 
 */
public class AlartTools {
	/**
	 * 全局静态变量在通用设置界面会有大用处
	 */
	public static AlarmEntity alarmEntity;// Alarm闹铃实体类
	public static GetUpSetEntity getUpSetEntity;// 起床闹铃通用设置
	public static BirthDayEntity birthDayEntity;// birthday闹铃实体类
	public static RemindEntity remindEntity;// remind闹铃实体类
	/**
	 * 当前的Acticity
	 */
	public static Activity fragActivity;

	public static class MaxID_Alart {
		/**
		 * 起床，睡眠，自定义总共可以拥有的闹铃最大条数
		 */
		public static final int MAXID_ALARM = 20;
		/**
		 * 生日总共可以拥有的闹铃最大条数
		 */
		public static final int MAXID_BIRTH = 20;
		/**
		 * 备忘总共可以拥有的闹铃最大条数
		 */
		public static final int MAXID_REMIND = 20;
	}

	/**
	 * 发送获取闹铃信息的请求
	 * 
	 * @author 袁剑
	 * 
	 */
	public static void getAlartData() {
		if (AlartTools.alartAdapter_content == null) {
			AlartTools.alartAdapter_content = new ArrayList<HashMap<String, Object>>();
		} else {
			AlartTools.alartAdapter_content.clear();
		}
		// //////////////////////////////////////////////////////
		ArrayList<String> domainNames = new ArrayList<String>();
		// 添加的顺序很重要，这里最后一个添加的会是最后一个和接收的，如果最后一个接收到将或作为一个判断依据来实现跳转
		domainNames.add(AlarmEntity.DOMAIN_ALARM);// 起床，睡前，自定义
		// TCPTools.sendTcpByDomain(domainNames, Tools.Current_Socket);
		// //////////////////////////////////////////////////////
		// domainNames = new ArrayList<String>();
		// 添加的顺序很重要，这里最后一个添加的会是最后一个和接收的，如果最后一个接收到将或作为一个判断依据来实现跳转
		domainNames.add(RemindEntity.DOMAIN_REMIND);// 备忘
		// TCPTools.sendTcpByDomain(domainNames, Tools.Current_Socket);
		// //////////////////////////////////////////////////////
		// domainNames = new ArrayList<String>();
		// 添加的顺序很重要，这里最后一个添加的会是最后一个和接收的，如果最后一个接收到将或作为一个判断依据来实现跳转
		domainNames.add(BirthDayEntity.DOMAIN_BIRTHDAY);// 生日
		TCPTools.sendTcpByDomain(domainNames, Tools.Current_SocketIP);

	}

	public static class BirthDay {

		/**
		 * 将阴历一九九零年十二月十九日转换成转换成1991-02-03
		 * 
		 * @param data
		 * @return
		 */
		public static String convertLunarDataToSolardata(String data) {
			String result = "";

			String date = LunarTextChineseToNumString.getNumString(data
					.split("日")[0]);
			HashMap<String, String> h = LunarToSolarTools.getSolar(date);
			int year = Integer.parseInt(h.get("year"));
			int month = Integer.parseInt(h.get("month"));
			int day = Integer.parseInt(h.get("day"));
			String content = year + "-" + month + "-" + day;
			result = content;
			return result;
		}

		/**
		 * 将二零一五年五月初九日转换成转换成2015-5-9
		 * 
		 * @param data
		 * @return
		 */
		public static String convertLunarDataToMydata(String data) {
			String result = "";

			String year = LunarTextChineseToNumString
					.getYear(data.split("年")[0]);
			String month = LunarTextChineseToNumString
					.getMonth(data.split("年")[1].split("月")[0]);
			String day = LunarTextChineseToNumString.getDay(data.split("年")[1]
					.split("月")[1].split("日")[0]);
			result = year + "-" + month + "-" + day;
			return result;
		}

		/**
		 * 将2015年5月9日转换成转换成2015-5-9
		 * 
		 * @param data
		 * @return
		 */
		public static String convertSolarDataToMydata(String data) {
			String result = "";

			String year = data.split("年")[0];
			String month = data.split("年")[1].split("月")[0];
			String day = data.split("年")[1].split("月")[1].split("日")[0];
			result = year + "-" + month + "-" + day;
			return result;
		}

		/**
		 * 将2015-02-03转换成2015年2月3号
		 * 
		 * @param data
		 * @return
		 */
		public static String convertToMydata(String data) {
			String result = "";
			String[] split = data.split("-");
			result = split[0] + "年" + split[1] + "月" + split[2] + "日";
			return result;
		}

		/**
		 * 将2015年2月3号转换成2015-02-03
		 * 
		 * @param data
		 * @return
		 */
		public static String convertToYunbaoData(String data) {
			String result = "";
			String[] split = data.split("-");
			result = split[0] + "-" + split[1] + "-" + split[2];
			return result;
		}
	}

	/**
	 * 时间操作工具
	 * 
	 * @author Administrator
	 * 
	 */
	public static class TimeTools {
		public static String AlartCurrentTime = "";
		public static final String TIME_TAIL = ":00";
	}

	/**
	 * 点开周期对话框涉及 带参数
	 * 
	 * @author 袁剑
	 * 
	 */
	public static class Cycle {

		/**
		 * 循环模式 是一次性闹钟还是循环，=0表示是一次性闹钟;=1是每天；=2是自定义
		 * 
		 * @author Administrator
		 * 
		 */
		public static class Free_model {

			/**
			 * 响铃一次
			 */
			public static final int FRE_MODEL_ONCE = 0;
			/**
			 * 每天响铃
			 */
			public static final int FRE_MODEL_EVERYDAY = 1;
			/**
			 * 自定义
			 */
			public static final int FRE_MODEL_DEFINED = 2;

		}

		public static int[] week_check = new int[] { 1, 1, 1, 1, 1, 1, 1 };
		/**
		 * 初始化的值
		 */
		public static int[] Init_week_check = new int[] { 1, 1, 1, 1, 1, 1, 1 };
		static String weekday[] = new String[] { "周一 ", "周二 ", "周三 ", "周四 ",
				"周五 ", "周六 ", "周日 ", };
		/**
		 * 默认每天都响铃
		 */
		public static int current_Fre_model = 1;
		/**
		 * 当前界面的周期对应的值
		 */
		public static int current_cycleValue = 127;

		/**
		 * 每天都响铃
		 */
		public static int Fre_model = 1;
		/**
		 * 周期对应的值
		 */
		public static int cycleValue = 127;

		/**
		 * 输入一个整型值得到一个大小为len的数组，取后len位
		 * 
		 * @param len返回的
		 *            数组的长度
		 */
		public static int[] getLastIntArrray(int values, int len) {
			int[] result = new int[len];
			int[] array = BinaryIntArray2ByteTools
					.byte2BinaryInt((byte) values);
			for (int i = 0; i < result.length; i++) {
				result[i] = array[i + array.length - len];
			}
			return result;
		}

		/**
		 * 通过整型值获取周期
		 * 
		 * @return
		 */
		public static String getWeekCycleByInt(int fre_model, int values) {
			String result = "每天";
			int[] array = StrBinaryTurn.byte2GBKBinary((byte) values);
			result = getWeekCycleByIntArray(fre_model, array);
			return result;
		}

		
		/**
		 * 输入一个大小为7的数组，且数组成员内容只能是0和1
		 * 
		 * @param week
		 * @return
		 */
		public static String getWeekCycleByIntArray(int fremodel, int[] weekvalues) {

			int week[]=new int[7];
			if(weekvalues.length==8){
				for(int i=0;i<7;i++){
					week[i]=weekvalues[i+1];
				}
			}else{
				week=weekvalues;
			}
			
			int week_values = binarayInt2int(week);
			String result = "";
			switch (fremodel) {
			case AlartTools.Cycle.Free_model.FRE_MODEL_ONCE:
				result = "一次性闹铃";
				break;
			case AlartTools.Cycle.Free_model.FRE_MODEL_EVERYDAY:
				result = "每天";
				break;
			case AlartTools.Cycle.Free_model.FRE_MODEL_DEFINED:
				if (week_values > 123) {
					if (week_values == 127) {
						result = "每天";
					} else if (week_values == 126) {
						result = "周一至周六";
					}else if (week_values == 125) {
						result = "周六除外";
					} 
					else if (week_values == 124) {
						result = "周一至周五";
					}
				} else {
					if (week_values == 123) {
						result = "周五除外";
					} else if (week_values == 119) {
						result = "周四除外";
					} 
					else if (week_values == 111) {
						result = "周三除外";
					} 
					else if (week_values == 95) {
						result = "周二除外";
					} 
					else if (week_values == 63) {
						result = "周一除外";
					} else{
						for (int i = 0; i < week.length; i++) {
							if (week[i] == 1) {
								result = result + weekday[i];
							}
						}
					}
					
				}

				if (result.length() == 0) {
					result = "无周期";
				}
				break;
			}
			return result;
		}

		// *************************************************************
		// *************************************************************
		// *************************************************************
		/**
		 * 输入【二进制转换成的】整型数组返回对应的整型值
		 * 
		 * @param array
		 * @return
		 */
		public static int binarayInt2int(int[] array) {
			int total = 0;
			for (int i = 0; i < array.length; i++) {
				if (array[i] == 1) {
					total = total + resultOfBinmaray(i, array.length);
				}

			}
			return total;
		}

		/**
		 * 数组的第几个
		 * 
		 * @param index
		 * @param length数组长度
		 * @return
		 */
		public static int resultOfBinmaray(int index, int length) {
			int times = length - 1 - index;
			int result = 1;
			for (int i = 0; i < times; i++) {
				result = result * 2;
			}
			return result;
		}
	}

	// *************************************************************
	// *************************************************************
	// *************************************************************
	// ×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	// ××××××××××××××××××××××××××××××××××××××××××闹铃适配器内容排序××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	/**
	 * AlartListViewAdapter的适配内容
	 */
	public static ArrayList<HashMap<String, Object>> alartAdapter_content = null;

	/**
	 * 按照起床闹铃，睡前闹铃，自定义闹铃，备忘闹铃，生日闹铃 的顺序进行排序
	 * 
	 * @param alartAdapter_content
	 */
	public static void sortAlartAdapter_content(
			ArrayList<HashMap<String, Object>> alartAdapter_content) {
		ArrayList<HashMap<String, Object>> result1 = getAwakeAlartAdapter_content(alartAdapter_content);
		ArrayList<HashMap<String, Object>> result2 = getSleepAlartAdapter_content(alartAdapter_content);
		ArrayList<HashMap<String, Object>> result3 = getDefinedAlartAdapter_content(alartAdapter_content);
		ArrayList<HashMap<String, Object>> result4 = getRemindAlartAdapter_content(alartAdapter_content);
		ArrayList<HashMap<String, Object>> result5 = getBirthdayAlartAdapter_content(alartAdapter_content);

		AlartTools.alartAdapter_content = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < result1.size(); i++) {
			AlartTools.alartAdapter_content.add(result1.get(i));
		}
		for (int i = 0; i < result2.size(); i++) {
			AlartTools.alartAdapter_content.add(result2.get(i));
		}
		for (int i = 0; i < result3.size(); i++) {
			AlartTools.alartAdapter_content.add(result3.get(i));
		}
		for (int i = 0; i < result4.size(); i++) {
			AlartTools.alartAdapter_content.add(result4.get(i));
		}
		for (int i = 0; i < result5.size(); i++) {
			AlartTools.alartAdapter_content.add(result5.get(i));
		}

	}

	/**
	 * 得到起床闹铃的ArrayList内容
	 * 
	 * @param alartAdapter_content
	 */
	public static ArrayList<HashMap<String, Object>> getAwakeAlartAdapter_content(
			ArrayList<HashMap<String, Object>> alartAdapter_content) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < alartAdapter_content.size(); i++) {
			HashMap<String, Object> temp = alartAdapter_content.get(i);
			int type = (Integer) temp
					.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
			if (type == AlartTools.AlartType.JSON_ALART_TYPE_ALART) {
				// Json 域名为Alart
				AlarmEntity ae = (AlarmEntity) alartAdapter_content.get(i).get(
						AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
				String title = ae.getTitle();
				if (title.equals(AlartTools.AlartTitle.ALART_TITLE_AWAKE)) {
					// 起床闹铃
					result.add(temp);
				}
			}
		}
		return result;
	}

	/**
	 * 得到睡前闹铃的ArrayList内容
	 * 
	 * @param alartAdapter_content
	 */
	public static ArrayList<HashMap<String, Object>> getSleepAlartAdapter_content(
			ArrayList<HashMap<String, Object>> alartAdapter_content) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < alartAdapter_content.size(); i++) {
			HashMap<String, Object> temp = alartAdapter_content.get(i);
			int type = (Integer) temp
					.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
			if (type == AlartTools.AlartType.JSON_ALART_TYPE_ALART) {
				// Json 域名为Alart
				AlarmEntity ae = (AlarmEntity) alartAdapter_content.get(i).get(
						AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
				String title = ae.getTitle();
				if (title.equals(AlartTools.AlartTitle.ALART_TITLE_SLEEP)) {
					// 睡前闹铃
					result.add(temp);
				}
			}
		}
		return result;
	}

	/**
	 * 得到自定义闹铃的ArrayList内容
	 * 
	 * @param alartAdapter_content
	 */
	public static ArrayList<HashMap<String, Object>> getDefinedAlartAdapter_content(
			ArrayList<HashMap<String, Object>> alartAdapter_content) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < alartAdapter_content.size(); i++) {
			HashMap<String, Object> temp = alartAdapter_content.get(i);
			int type = (Integer) temp
					.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
			if (type == AlartTools.AlartType.JSON_ALART_TYPE_ALART) {
				// Json 域名为Alart
				AlarmEntity ae = (AlarmEntity) alartAdapter_content.get(i).get(
						AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
				String title = ae.getTitle();
				if (!title.equals(AlartTools.AlartTitle.ALART_TITLE_AWAKE)
						&& !title
								.equals(AlartTools.AlartTitle.ALART_TITLE_SLEEP)) {
					// 自定义闹铃
					result.add(temp);

				}
			}
		}
		return result;
	}

	/**
	 * 得到备忘闹铃的ArrayList内容
	 * 
	 * @param alartAdapter_content
	 */
	public static ArrayList<HashMap<String, Object>> getRemindAlartAdapter_content(
			ArrayList<HashMap<String, Object>> alartAdapter_content) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < alartAdapter_content.size(); i++) {
			HashMap<String, Object> temp = alartAdapter_content.get(i);
			int type = (Integer) temp
					.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
			double is_valid = AlarmEntity.ISVALID_YES;
			if (type == AlartTools.AlartType.JSON_ALART_TYPE_REMIND) {
				// Json 域名为REMIND
				result.add(temp);
			}
		}
		return result;
	}

	/**
	 * 得到生日闹铃的ArrayList内容
	 * 
	 * @param alartAdapter_content
	 */
	public static ArrayList<HashMap<String, Object>> getBirthdayAlartAdapter_content(
			ArrayList<HashMap<String, Object>> alartAdapter_content) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < alartAdapter_content.size(); i++) {
			HashMap<String, Object> temp = alartAdapter_content.get(i);
			int type = (Integer) temp
					.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
			double is_valid = AlarmEntity.ISVALID_YES;
			if (type == AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY) {
				// Json 域名为REMIND
				result.add(temp);
			}
		}
		return result;
	}

	// ×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	// ×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
	// ×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××

	/**
	 * 跳转到界面的时候传递实体类或类型值【添加或编辑标志位】
	 * 
	 * @author 袁剑
	 * 
	 */
	public class IntentKey {
		// ------------------------------------------------------
		/**
		 * 起床
		 */
		public static final String INTENT_ENTITY_ALART = "alart";

		// ------------------------------------------------------
		/**
		 * 备忘
		 */
		public static final String INTENT_ENTITY_REMIND = "remind";
		// ------------------------------------------------------
		/**
		 * 生日
		 */
		public static final String INTENT_ENTITY_BIRTHDAY = "birthday";
		// ****************************************************
		/**
		 * 编辑还是添加
		 */
		public static final String INTENT_ISADD = "addoredit";

		/**
		 * 编辑的时候标记当前内容是数组里面的第几个【只有在编辑的时候有用到】
		 */
		public static final String INTENT_INDEX = "index";
	}

	/**
	 * Json格式面闹铃分为三大类，Alart【包含起床闹铃，睡前闹铃，自定义闹铃】， BirthDay【生日闹铃】， Remind【备忘提醒】
	 */
	public static class AlartType {

		/**
		 * Alart格式的Json
		 */
		public static final int JSON_ALART_TYPE_ALART = 0x01;
		/**
		 * BIRTHDAY格式的Json
		 */
		public static final int JSON_ALART_TYPE_BIRTHDAY = 0x02;
		/**
		 * BIRTHDAY格式的Json
		 */
		public static final int JSON_ALART_TYPE_REMIND = 0x03;
		// --------------------------------------------------------
		/**
		 * 起床闹铃FLAG
		 */
		public static final int FLAG_ALART_TYPE_AWAKE = 0x10;
		/**
		 * 睡觉闹铃FLAG
		 */
		public static final int FLAG_ALART_TYPE_SLEEP = 0x11;
		/**
		 * 自定义闹铃FLAG
		 */
		public static final int FLAG_ALART_TYPE_DEFINED = 0x12;
		// --
		/**
		 * 备忘闹铃FLAG
		 */
		public static final int FLAG_ALART_TYPE_REMIND = 0x13;
		// ----
		/**
		 * 生日闹铃FLAG
		 */
		public static final int FLAG_ALART_TYPE_BIRTHDAY = 0x14;

		// ______________________________________________________________________
		/**
		 * 添加闹铃
		 */
		public static final int OPTIONS_INSERT = 0x20;
		/**
		 * 修改闹铃
		 */
		public static final int OPTIONS_UPDATE = 0x21;
		/**
		 * 删除闹铃
		 */
		public static final int OPTIONS_DELETE = 0x22;

	}

	/**
	 * 固定的闹铃的标题
	 * 
	 * @author 袁剑
	 * 
	 */
	public static class AlartTitle {
		/**
		 * 起床闹铃的标题
		 */
		public static final String ALART_TITLE_AWAKE = "起床";
		/**
		 * 睡眠闹铃的标题
		 */
		public static final String ALART_TITLE_SLEEP = "睡眠";
	}

	/**
	 * AlartListViewAdapter的适配数据的单个HahMap对应的Key值
	 * 
	 * @author 袁剑
	 * 
	 */
	public static class AlartListViewAdapterTools {
		/**
		 * 类型对应的Key值
		 */
		public static final String KEY_TYPE = "type";
		/**
		 * 实体类对应的key值
		 */
		public static final String KEY_ENTITY = "entity";

	}

	/**
	 * 根据闹铃的类型来决定放置什么背景图片
	 * 
	 * @param c
	 * @param flagType
	 *            闹铃类型
	 * @param is_valid
	 *            判断是否生效的值
	 * @return
	 */
	public static int getBackgroundResourceByFlagAlartType(Context c,
			int flagType, double is_valid) {
		int rid = R.drawable.default_small;
		switch (flagType) {
		// ----------Alart【Json】
		case AlartType.FLAG_ALART_TYPE_AWAKE:
			// 起床闹铃图片
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.awakealart_pre;
			} else {
				rid = R.drawable.awakealart_nor;
			}

			break;
		case AlartType.FLAG_ALART_TYPE_SLEEP:
			// 睡眠闹铃图片
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.sleep_pre;
			} else {
				rid = R.drawable.sleep_nor;
			}

			break;
		case AlartType.FLAG_ALART_TYPE_DEFINED:
			// 自定义闹铃图片
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.definedalart_pre;
			} else {
				rid = R.drawable.definedalart_nor;
			}
			break;
		// -----------Remind
		case AlartType.FLAG_ALART_TYPE_REMIND:
			// 备忘闹铃图片
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.remind_pre;
			} else {
				rid = R.drawable.remind_nor;
			}
			break;
		// -----Birthday
		case AlartType.FLAG_ALART_TYPE_BIRTHDAY:
			// 生日闹铃图片
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.birthdayalart_pre;
			} else {
				rid = R.drawable.birthdayalart_nor;
			}
			break;

		}
		return rid;
	}

	// -------------------------------------------------------------------

	/**
	 * 闹铃状态提示信息
	 */
	public static final String ALART_HAS_OPEN = "闹铃已开启";
	public static final String ALART_HAS_CLOSED = "闹铃已关闭";
	// ------------------------KEY---------------------------------
	/**
	 * 闹铃类型
	 */
	public static final String KEY_ALART_TYPE = "type";
	/**
	 * 闹铃ID号
	 */
	public static final String KEY_ALART_ID = "id";
	/**
	 * 日期（今天明天或者还有多少天）
	 */
	public static final String KEY_ALART_DAY = "day";
	/**
	 * 闹铃事件
	 */
	public static final String KEY_ALART_INSIDENT = "insident";
	/**
	 * 闹铃详情（包括每日闹铃的周期和纪念日闹铃的具体日期）
	 */
	public static final String KEY_ALART_DETAIL = "detail";
	/**
	 * 闹铃是否开启
	 */
	public static final String KEY_ALART_ISCLOSE = "isclose";

	/**
	 * 循环日期【每日闹铃】
	 */
	public static final String KEY_ALART_CYCLE = "cycle";
	/**
	 * 重复提醒【每日闹铃】
	 */
	public static final String KEY_ALART_REMIND = "remind";
	/**
	 * 日期类型【纪念日】
	 */
	public static final String KEY_ALART_DATATYPE = "datatype";
	// -------------------------VALUE-------------------------------------------
	/**
	 * 每日闹铃
	 */
	public static final int VALUE_ALART_TYPE_DAILY = 0x01;
	/**
	 * 纪念日闹铃
	 */
	public static final int VALUE_ALART_TYPE_COMMENORATION = 0x02;
	/**
	 * 每日闹铃以及纪念日闹铃
	 */
	public static final int VALUE_ALART_TYPE_DAILY_AND_COMMENORATION = 0x03;

	// -------------------------------------------------------------------------

	// 界面跳转
	public static final String FLAG_ALART_ADD_OR_UPDATE_KEY = "FLAG_ALART_ADD_OR_UPDATE_KEY";

	/**
	 * 记录当前的状态是添加
	 */
	public static final int ALART_FLAG_ADD = 0x20;
	/**
	 * 记录当前的状态是更新
	 */
	public static final int ALART_FLAG_UPDATE = 0x21;
	// ----------------------------------------------------------------------------------------------
	// --------------------------小睡时间次数,响铃时长 --------------
	/**
	 * 小睡时间间隔
	 */
	public static int ALART_SLEEP_MINS = 5;
	/**
	 * 小睡提醒次数
	 */
	public static int ALART_SLEEP_TIMES = 3;

	/**
	 * 响铃时长
	 */
	public static int ALART_SLEEP_PLAYTIME = 8;

	// ×××××××××××××××××××××××××××××××××××××××××××××××××××××
	// ××××××××××××××××××××××××××××××××××××××××××××
	/**
	 * 铃声列表的Key值
	 */
	public static final String KEY_RING = "ring";

	/**
	 * 得到一个ArrayList<HashMap<String, String>>的设置铃声的数组
	 */
	public static ArrayList<HashMap<String, Object>> getRings(Context context) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		String[] rings = context.getResources().getStringArray(R.array.rings);
		int len = rings.length;
		for (int i = 0; i < len; i++) {
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put(KEY_RING, rings[i]);
			result.add(temp);
		}
		return result;

	}

	// ×××××××××××××××××××××××××××××××××××××××××××××××××××××
	// ××××××××××××××××××××××××××××××××××××××××××××
	// ------------------------------------------------------------------------------------
	/**
	 * 生成Alarm的添加的ID;取值范围为[0,maxID-1]
	 * 
	 * @param entitys
	 *            起床，睡眠，自定义的实体类；
	 * @param alartJsonType
	 *            闹铃的Json类型
	 *            AlartTools.AlartType.JSON_ALART_TYPE_ALART/AlartTools
	 *            .AlartType.JSON_ALART_TYPE_REMIND/AlartTools.AlartType.
	 *            JSON_ALART_TYPE_BIRTHDAY
	 * @return
	 */
	public static int getAlarmGeneralID(
			ArrayList<HashMap<String, Object>> al_alart, int alartJsonType) {

		int maxID = 0;
		int[] ids = getIDsInAdapterArrayList(al_alart, alartJsonType);
		if (alartJsonType == AlartTools.AlartType.JSON_ALART_TYPE_ALART) {
			// Json 域名为Alarm
			maxID = AlartTools.MaxID_Alart.MAXID_ALARM;
		} else if (alartJsonType == AlartTools.AlartType.JSON_ALART_TYPE_REMIND) {
			// Json 域名为备忘
			maxID = AlartTools.MaxID_Alart.MAXID_REMIND;
		} else if (alartJsonType == AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY) {
			// Json 域名为生日
			maxID = AlartTools.MaxID_Alart.MAXID_BIRTH;
		}
		int id = GeneralID.generalID(ids, maxID);
		return id;

	}

	/**
	 * 传入AlartActivity里面的Adapter的ArrayList得出里面alartJsonType类型的ID数组
	 * 
	 * @param al_alart
	 * @param alartJsonType
	 * @return
	 */
	public static int[] getIDsInAdapterArrayList(
			ArrayList<HashMap<String, Object>> al_alart, int alartJsonType) {
		ArrayList<Integer> al_ids = new ArrayList<Integer>();
		for (int i = 0; i < al_alart.size(); i++) {
			int type = (Integer) al_alart.get(i).get(
					AlartTools.AlartListViewAdapterTools.KEY_TYPE);
			if (type == alartJsonType) {
				if (type == AlartTools.AlartType.JSON_ALART_TYPE_ALART) {
					// Json 域名为Alarm
					AlarmEntity ae = (AlarmEntity) al_alart.get(i).get(
							AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
					al_ids.add(ae.getId());

				} else if (type == AlartTools.AlartType.JSON_ALART_TYPE_REMIND) {
					// Json 域名为备忘
					RemindEntity re = (RemindEntity) al_alart.get(i).get(
							AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
					al_ids.add(re.getId());

				} else if (type == AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY) {
					// Json 域名为生日
					BirthDayEntity be = (BirthDayEntity) al_alart.get(i).get(
							AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
					al_ids.add(be.getId());
				}
			}

		}

		int[] ids = new int[al_ids.size()];
		for (int j = 0; j < ids.length; j++) {
			ids[j] = al_ids.get(j);
		}
		return ids;
	}
}
