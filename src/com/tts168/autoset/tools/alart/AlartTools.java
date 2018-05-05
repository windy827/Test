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
 * �������õĹ�����
 * 
 * @author Ԭ��
 * 
 */
public class AlartTools {
	/**
	 * ȫ�־�̬������ͨ�����ý�����д��ô�
	 */
	public static AlarmEntity alarmEntity;// Alarm����ʵ����
	public static GetUpSetEntity getUpSetEntity;// ������ͨ������
	public static BirthDayEntity birthDayEntity;// birthday����ʵ����
	public static RemindEntity remindEntity;// remind����ʵ����
	/**
	 * ��ǰ��Acticity
	 */
	public static Activity fragActivity;

	public static class MaxID_Alart {
		/**
		 * �𴲣�˯�ߣ��Զ����ܹ�����ӵ�е������������
		 */
		public static final int MAXID_ALARM = 20;
		/**
		 * �����ܹ�����ӵ�е������������
		 */
		public static final int MAXID_BIRTH = 20;
		/**
		 * �����ܹ�����ӵ�е������������
		 */
		public static final int MAXID_REMIND = 20;
	}

	/**
	 * ���ͻ�ȡ������Ϣ������
	 * 
	 * @author Ԭ��
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
		// ��ӵ�˳�����Ҫ���������һ����ӵĻ������һ���ͽ��յģ�������һ�����յ�������Ϊһ���ж�������ʵ����ת
		domainNames.add(AlarmEntity.DOMAIN_ALARM);// �𴲣�˯ǰ���Զ���
		// TCPTools.sendTcpByDomain(domainNames, Tools.Current_Socket);
		// //////////////////////////////////////////////////////
		// domainNames = new ArrayList<String>();
		// ��ӵ�˳�����Ҫ���������һ����ӵĻ������һ���ͽ��յģ�������һ�����յ�������Ϊһ���ж�������ʵ����ת
		domainNames.add(RemindEntity.DOMAIN_REMIND);// ����
		// TCPTools.sendTcpByDomain(domainNames, Tools.Current_Socket);
		// //////////////////////////////////////////////////////
		// domainNames = new ArrayList<String>();
		// ��ӵ�˳�����Ҫ���������һ����ӵĻ������һ���ͽ��յģ�������һ�����յ�������Ϊһ���ж�������ʵ����ת
		domainNames.add(BirthDayEntity.DOMAIN_BIRTHDAY);// ����
		TCPTools.sendTcpByDomain(domainNames, Tools.Current_SocketIP);

	}

	public static class BirthDay {

		/**
		 * ������һ�ž�����ʮ����ʮ����ת����ת����1991-02-03
		 * 
		 * @param data
		 * @return
		 */
		public static String convertLunarDataToSolardata(String data) {
			String result = "";

			String date = LunarTextChineseToNumString.getNumString(data
					.split("��")[0]);
			HashMap<String, String> h = LunarToSolarTools.getSolar(date);
			int year = Integer.parseInt(h.get("year"));
			int month = Integer.parseInt(h.get("month"));
			int day = Integer.parseInt(h.get("day"));
			String content = year + "-" + month + "-" + day;
			result = content;
			return result;
		}

		/**
		 * ������һ�������³�����ת����ת����2015-5-9
		 * 
		 * @param data
		 * @return
		 */
		public static String convertLunarDataToMydata(String data) {
			String result = "";

			String year = LunarTextChineseToNumString
					.getYear(data.split("��")[0]);
			String month = LunarTextChineseToNumString
					.getMonth(data.split("��")[1].split("��")[0]);
			String day = LunarTextChineseToNumString.getDay(data.split("��")[1]
					.split("��")[1].split("��")[0]);
			result = year + "-" + month + "-" + day;
			return result;
		}

		/**
		 * ��2015��5��9��ת����ת����2015-5-9
		 * 
		 * @param data
		 * @return
		 */
		public static String convertSolarDataToMydata(String data) {
			String result = "";

			String year = data.split("��")[0];
			String month = data.split("��")[1].split("��")[0];
			String day = data.split("��")[1].split("��")[1].split("��")[0];
			result = year + "-" + month + "-" + day;
			return result;
		}

		/**
		 * ��2015-02-03ת����2015��2��3��
		 * 
		 * @param data
		 * @return
		 */
		public static String convertToMydata(String data) {
			String result = "";
			String[] split = data.split("-");
			result = split[0] + "��" + split[1] + "��" + split[2] + "��";
			return result;
		}

		/**
		 * ��2015��2��3��ת����2015-02-03
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
	 * ʱ���������
	 * 
	 * @author Administrator
	 * 
	 */
	public static class TimeTools {
		public static String AlartCurrentTime = "";
		public static final String TIME_TAIL = ":00";
	}

	/**
	 * �㿪���ڶԻ����漰 ������
	 * 
	 * @author Ԭ��
	 * 
	 */
	public static class Cycle {

		/**
		 * ѭ��ģʽ ��һ�������ӻ���ѭ����=0��ʾ��һ��������;=1��ÿ�죻=2���Զ���
		 * 
		 * @author Administrator
		 * 
		 */
		public static class Free_model {

			/**
			 * ����һ��
			 */
			public static final int FRE_MODEL_ONCE = 0;
			/**
			 * ÿ������
			 */
			public static final int FRE_MODEL_EVERYDAY = 1;
			/**
			 * �Զ���
			 */
			public static final int FRE_MODEL_DEFINED = 2;

		}

		public static int[] week_check = new int[] { 1, 1, 1, 1, 1, 1, 1 };
		/**
		 * ��ʼ����ֵ
		 */
		public static int[] Init_week_check = new int[] { 1, 1, 1, 1, 1, 1, 1 };
		static String weekday[] = new String[] { "��һ ", "�ܶ� ", "���� ", "���� ",
				"���� ", "���� ", "���� ", };
		/**
		 * Ĭ��ÿ�춼����
		 */
		public static int current_Fre_model = 1;
		/**
		 * ��ǰ��������ڶ�Ӧ��ֵ
		 */
		public static int current_cycleValue = 127;

		/**
		 * ÿ�춼����
		 */
		public static int Fre_model = 1;
		/**
		 * ���ڶ�Ӧ��ֵ
		 */
		public static int cycleValue = 127;

		/**
		 * ����һ������ֵ�õ�һ����СΪlen�����飬ȡ��lenλ
		 * 
		 * @param len���ص�
		 *            ����ĳ���
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
		 * ͨ������ֵ��ȡ����
		 * 
		 * @return
		 */
		public static String getWeekCycleByInt(int fre_model, int values) {
			String result = "ÿ��";
			int[] array = StrBinaryTurn.byte2GBKBinary((byte) values);
			result = getWeekCycleByIntArray(fre_model, array);
			return result;
		}

		
		/**
		 * ����һ����СΪ7�����飬�������Ա����ֻ����0��1
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
				result = "һ��������";
				break;
			case AlartTools.Cycle.Free_model.FRE_MODEL_EVERYDAY:
				result = "ÿ��";
				break;
			case AlartTools.Cycle.Free_model.FRE_MODEL_DEFINED:
				if (week_values > 123) {
					if (week_values == 127) {
						result = "ÿ��";
					} else if (week_values == 126) {
						result = "��һ������";
					}else if (week_values == 125) {
						result = "��������";
					} 
					else if (week_values == 124) {
						result = "��һ������";
					}
				} else {
					if (week_values == 123) {
						result = "�������";
					} else if (week_values == 119) {
						result = "���ĳ���";
					} 
					else if (week_values == 111) {
						result = "��������";
					} 
					else if (week_values == 95) {
						result = "�ܶ�����";
					} 
					else if (week_values == 63) {
						result = "��һ����";
					} else{
						for (int i = 0; i < week.length; i++) {
							if (week[i] == 1) {
								result = result + weekday[i];
							}
						}
					}
					
				}

				if (result.length() == 0) {
					result = "������";
				}
				break;
			}
			return result;
		}

		// *************************************************************
		// *************************************************************
		// *************************************************************
		/**
		 * ���롾������ת���ɵġ��������鷵�ض�Ӧ������ֵ
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
		 * ����ĵڼ���
		 * 
		 * @param index
		 * @param length���鳤��
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
	// ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
	// �������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
	/**
	 * AlartListViewAdapter����������
	 */
	public static ArrayList<HashMap<String, Object>> alartAdapter_content = null;

	/**
	 * ���������壬˯ǰ���壬�Զ������壬�������壬�������� ��˳���������
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
	 * �õ��������ArrayList����
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
				// Json ����ΪAlart
				AlarmEntity ae = (AlarmEntity) alartAdapter_content.get(i).get(
						AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
				String title = ae.getTitle();
				if (title.equals(AlartTools.AlartTitle.ALART_TITLE_AWAKE)) {
					// ������
					result.add(temp);
				}
			}
		}
		return result;
	}

	/**
	 * �õ�˯ǰ�����ArrayList����
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
				// Json ����ΪAlart
				AlarmEntity ae = (AlarmEntity) alartAdapter_content.get(i).get(
						AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
				String title = ae.getTitle();
				if (title.equals(AlartTools.AlartTitle.ALART_TITLE_SLEEP)) {
					// ˯ǰ����
					result.add(temp);
				}
			}
		}
		return result;
	}

	/**
	 * �õ��Զ��������ArrayList����
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
				// Json ����ΪAlart
				AlarmEntity ae = (AlarmEntity) alartAdapter_content.get(i).get(
						AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
				String title = ae.getTitle();
				if (!title.equals(AlartTools.AlartTitle.ALART_TITLE_AWAKE)
						&& !title
								.equals(AlartTools.AlartTitle.ALART_TITLE_SLEEP)) {
					// �Զ�������
					result.add(temp);

				}
			}
		}
		return result;
	}

	/**
	 * �õ����������ArrayList����
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
				// Json ����ΪREMIND
				result.add(temp);
			}
		}
		return result;
	}

	/**
	 * �õ����������ArrayList����
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
				// Json ����ΪREMIND
				result.add(temp);
			}
		}
		return result;
	}

	// ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
	// ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
	// ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������

	/**
	 * ��ת�������ʱ�򴫵�ʵ���������ֵ����ӻ�༭��־λ��
	 * 
	 * @author Ԭ��
	 * 
	 */
	public class IntentKey {
		// ------------------------------------------------------
		/**
		 * ��
		 */
		public static final String INTENT_ENTITY_ALART = "alart";

		// ------------------------------------------------------
		/**
		 * ����
		 */
		public static final String INTENT_ENTITY_REMIND = "remind";
		// ------------------------------------------------------
		/**
		 * ����
		 */
		public static final String INTENT_ENTITY_BIRTHDAY = "birthday";
		// ****************************************************
		/**
		 * �༭�������
		 */
		public static final String INTENT_ISADD = "addoredit";

		/**
		 * �༭��ʱ���ǵ�ǰ��������������ĵڼ�����ֻ���ڱ༭��ʱ�����õ���
		 */
		public static final String INTENT_INDEX = "index";
	}

	/**
	 * Json��ʽ�������Ϊ�����࣬Alart�����������壬˯ǰ���壬�Զ������塿�� BirthDay���������塿�� Remind���������ѡ�
	 */
	public static class AlartType {

		/**
		 * Alart��ʽ��Json
		 */
		public static final int JSON_ALART_TYPE_ALART = 0x01;
		/**
		 * BIRTHDAY��ʽ��Json
		 */
		public static final int JSON_ALART_TYPE_BIRTHDAY = 0x02;
		/**
		 * BIRTHDAY��ʽ��Json
		 */
		public static final int JSON_ALART_TYPE_REMIND = 0x03;
		// --------------------------------------------------------
		/**
		 * ������FLAG
		 */
		public static final int FLAG_ALART_TYPE_AWAKE = 0x10;
		/**
		 * ˯������FLAG
		 */
		public static final int FLAG_ALART_TYPE_SLEEP = 0x11;
		/**
		 * �Զ�������FLAG
		 */
		public static final int FLAG_ALART_TYPE_DEFINED = 0x12;
		// --
		/**
		 * ��������FLAG
		 */
		public static final int FLAG_ALART_TYPE_REMIND = 0x13;
		// ----
		/**
		 * ��������FLAG
		 */
		public static final int FLAG_ALART_TYPE_BIRTHDAY = 0x14;

		// ______________________________________________________________________
		/**
		 * �������
		 */
		public static final int OPTIONS_INSERT = 0x20;
		/**
		 * �޸�����
		 */
		public static final int OPTIONS_UPDATE = 0x21;
		/**
		 * ɾ������
		 */
		public static final int OPTIONS_DELETE = 0x22;

	}

	/**
	 * �̶�������ı���
	 * 
	 * @author Ԭ��
	 * 
	 */
	public static class AlartTitle {
		/**
		 * ������ı���
		 */
		public static final String ALART_TITLE_AWAKE = "��";
		/**
		 * ˯������ı���
		 */
		public static final String ALART_TITLE_SLEEP = "˯��";
	}

	/**
	 * AlartListViewAdapter���������ݵĵ���HahMap��Ӧ��Keyֵ
	 * 
	 * @author Ԭ��
	 * 
	 */
	public static class AlartListViewAdapterTools {
		/**
		 * ���Ͷ�Ӧ��Keyֵ
		 */
		public static final String KEY_TYPE = "type";
		/**
		 * ʵ�����Ӧ��keyֵ
		 */
		public static final String KEY_ENTITY = "entity";

	}

	/**
	 * �����������������������ʲô����ͼƬ
	 * 
	 * @param c
	 * @param flagType
	 *            ��������
	 * @param is_valid
	 *            �ж��Ƿ���Ч��ֵ
	 * @return
	 */
	public static int getBackgroundResourceByFlagAlartType(Context c,
			int flagType, double is_valid) {
		int rid = R.drawable.default_small;
		switch (flagType) {
		// ----------Alart��Json��
		case AlartType.FLAG_ALART_TYPE_AWAKE:
			// ������ͼƬ
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.awakealart_pre;
			} else {
				rid = R.drawable.awakealart_nor;
			}

			break;
		case AlartType.FLAG_ALART_TYPE_SLEEP:
			// ˯������ͼƬ
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.sleep_pre;
			} else {
				rid = R.drawable.sleep_nor;
			}

			break;
		case AlartType.FLAG_ALART_TYPE_DEFINED:
			// �Զ�������ͼƬ
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.definedalart_pre;
			} else {
				rid = R.drawable.definedalart_nor;
			}
			break;
		// -----------Remind
		case AlartType.FLAG_ALART_TYPE_REMIND:
			// ��������ͼƬ
			if (is_valid == AlarmEntity.ISVALID_YES) {
				rid = R.drawable.remind_pre;
			} else {
				rid = R.drawable.remind_nor;
			}
			break;
		// -----Birthday
		case AlartType.FLAG_ALART_TYPE_BIRTHDAY:
			// ��������ͼƬ
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
	 * ����״̬��ʾ��Ϣ
	 */
	public static final String ALART_HAS_OPEN = "�����ѿ���";
	public static final String ALART_HAS_CLOSED = "�����ѹر�";
	// ------------------------KEY---------------------------------
	/**
	 * ��������
	 */
	public static final String KEY_ALART_TYPE = "type";
	/**
	 * ����ID��
	 */
	public static final String KEY_ALART_ID = "id";
	/**
	 * ���ڣ�����������߻��ж����죩
	 */
	public static final String KEY_ALART_DAY = "day";
	/**
	 * �����¼�
	 */
	public static final String KEY_ALART_INSIDENT = "insident";
	/**
	 * �������飨����ÿ����������ںͼ���������ľ������ڣ�
	 */
	public static final String KEY_ALART_DETAIL = "detail";
	/**
	 * �����Ƿ���
	 */
	public static final String KEY_ALART_ISCLOSE = "isclose";

	/**
	 * ѭ�����ڡ�ÿ�����塿
	 */
	public static final String KEY_ALART_CYCLE = "cycle";
	/**
	 * �ظ����ѡ�ÿ�����塿
	 */
	public static final String KEY_ALART_REMIND = "remind";
	/**
	 * �������͡������ա�
	 */
	public static final String KEY_ALART_DATATYPE = "datatype";
	// -------------------------VALUE-------------------------------------------
	/**
	 * ÿ������
	 */
	public static final int VALUE_ALART_TYPE_DAILY = 0x01;
	/**
	 * ����������
	 */
	public static final int VALUE_ALART_TYPE_COMMENORATION = 0x02;
	/**
	 * ÿ�������Լ�����������
	 */
	public static final int VALUE_ALART_TYPE_DAILY_AND_COMMENORATION = 0x03;

	// -------------------------------------------------------------------------

	// ������ת
	public static final String FLAG_ALART_ADD_OR_UPDATE_KEY = "FLAG_ALART_ADD_OR_UPDATE_KEY";

	/**
	 * ��¼��ǰ��״̬�����
	 */
	public static final int ALART_FLAG_ADD = 0x20;
	/**
	 * ��¼��ǰ��״̬�Ǹ���
	 */
	public static final int ALART_FLAG_UPDATE = 0x21;
	// ----------------------------------------------------------------------------------------------
	// --------------------------С˯ʱ�����,����ʱ�� --------------
	/**
	 * С˯ʱ����
	 */
	public static int ALART_SLEEP_MINS = 5;
	/**
	 * С˯���Ѵ���
	 */
	public static int ALART_SLEEP_TIMES = 3;

	/**
	 * ����ʱ��
	 */
	public static int ALART_SLEEP_PLAYTIME = 8;

	// ����������������������������������������������������������������������������������������������������������
	// ����������������������������������������������������������������������������������������
	/**
	 * �����б��Keyֵ
	 */
	public static final String KEY_RING = "ring";

	/**
	 * �õ�һ��ArrayList<HashMap<String, String>>����������������
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

	// ����������������������������������������������������������������������������������������������������������
	// ����������������������������������������������������������������������������������������
	// ------------------------------------------------------------------------------------
	/**
	 * ����Alarm����ӵ�ID;ȡֵ��ΧΪ[0,maxID-1]
	 * 
	 * @param entitys
	 *            �𴲣�˯�ߣ��Զ����ʵ���ࣻ
	 * @param alartJsonType
	 *            �����Json����
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
			// Json ����ΪAlarm
			maxID = AlartTools.MaxID_Alart.MAXID_ALARM;
		} else if (alartJsonType == AlartTools.AlartType.JSON_ALART_TYPE_REMIND) {
			// Json ����Ϊ����
			maxID = AlartTools.MaxID_Alart.MAXID_REMIND;
		} else if (alartJsonType == AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY) {
			// Json ����Ϊ����
			maxID = AlartTools.MaxID_Alart.MAXID_BIRTH;
		}
		int id = GeneralID.generalID(ids, maxID);
		return id;

	}

	/**
	 * ����AlartActivity�����Adapter��ArrayList�ó�����alartJsonType���͵�ID����
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
					// Json ����ΪAlarm
					AlarmEntity ae = (AlarmEntity) al_alart.get(i).get(
							AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
					al_ids.add(ae.getId());

				} else if (type == AlartTools.AlartType.JSON_ALART_TYPE_REMIND) {
					// Json ����Ϊ����
					RemindEntity re = (RemindEntity) al_alart.get(i).get(
							AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
					al_ids.add(re.getId());

				} else if (type == AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY) {
					// Json ����Ϊ����
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
