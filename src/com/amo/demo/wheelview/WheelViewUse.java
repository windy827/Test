package com.amo.demo.wheelview;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.amo.demo.arrWheelview.StrArrayWheelAdapter;
import com.amo.demo.arrWheelview.StrArrayWheelView;
import com.autoset.jni.alarm.AlarmEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.DisplayUtil;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.others.TimeTools;
import com.tts168.autoset.tools.others.time.DataTools;
import com.tts168.autoset.tools.others.time.LunarTextChineseToNumString;
import com.tts168.autoset.tools.others.time.LunarToSolarTools;
import com.tts168.autoset.tools.others.time.SolarToLunarTools;
import com.tts168.autoset.view.wheelview.DataNameWheelView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * Tools.time������¼ʱ������
 * 
 * @author Ԭ��
 * 
 */
public class WheelViewUse {
	private static final int START_YEAR = 1900;
	private static final int END_YEAR = 2050;
	public static int textSize = 0;// �����С
	public static final int NUM_TEXTSIZE = DisplayUtil.sp2px(MyApplication.getInstance().getCur_Activity(), 18);// ���������С
	public static final int CHINESE_TEXTSIZE = DisplayUtil.sp2px(MyApplication.getInstance().getCur_Activity(), 18);// ���������С
	public static boolean is_cb_Checked;// ������¼CheckBox�չ���ʱ�Ƿ��Ѿ�ѡ���״̬

	/**
	 * 
	 * @param a
	 * @param flag
	 *            ��Ҫ��WheelView���� Tools.TYPE_TIME��Tools.TYPE_DATE
	 * @param canChooseLunar
	 *            �Ƿ����ѡ������
	 * @param useMyData
	 *            �Ƿ����Լ�����������
	 * @param time
	 *            �Լ����������ݣ����봫���Դ�"",������ 2014��12��6��,ʱ��16:51
	 * @param isLunar
	 *            �Ƿ�ʹ������
	 * @return
	 */
	public static void getDateView(final View view, int flag,
			boolean canChooseLunar, boolean useMyData, String time,
			boolean isLunar) {
		int year = 0;
		int month = 0;
		int day = 0;
		int lunar_year = 0;
		int lunar_month = 0;
		int lunar_day = 0;
		int hour = 0;
		int minute = 0;

		textSize = CHINESE_TEXTSIZE;
		DataNameWheelView dataNameWheelView = new DataNameWheelView(view);
		if (flag == Tools.FLAG_DATE) {
			dataNameWheelView.setYearText("��");
			dataNameWheelView.setMonthText("��");
			dataNameWheelView.setDayText("��");
		} else {
			dataNameWheelView.setHourText("ʱ");
			dataNameWheelView.setMinText("��");
		}
		if (useMyData) {
			// ��ʾ�û������ʱ��
			if (flag == Tools.FLAG_DATE) {

				// ������
				if (isLunar) {
					// ��������ת���������������ת��������
					// HashMap<String,String>result=LunarToSolarTools.getSolar(LunarTextChineseToNumString.getNumString(time));
					// year=Integer.parseInt(result.get("year"));
					// month=Integer.parseInt(result.get("month"))-1;//����ü�ȥ1��Ϊ�˴��㷨��ͳһ��
					// day=Integer.parseInt(result.get("day"));
					// textSize=CHINESE_TEXTSIZE;
					String temp_year = time.split("��")[0];
					String tempmonthAndday = time.split("��")[1];
					String temp_month = tempmonthAndday.split("��")[0];
					String temp_day = tempmonthAndday.split("��")[1];
					year = Integer.parseInt(temp_year);
					month = Integer.parseInt(temp_month) - 1;// ����ü�ȥ1��Ϊ�˴��㷨��ͳһ��
					day = Integer.parseInt(temp_day.split("��")[0]);
				} else {
					String temp_year = time.split("��")[0];
					String tempmonthAndday = time.split("��")[1];
					String temp_month = tempmonthAndday.split("��")[0];
					String temp_day = tempmonthAndday.split("��")[1];
					year = Integer.parseInt(temp_year);
					month = Integer.parseInt(temp_month) - 1;// ����ü�ȥ1��Ϊ�˴��㷨��ͳһ��
					day = Integer.parseInt(temp_day.split("��")[0]);

				}

			} else {
				// ʱ��
				// ��ʾ��ǰ����ʱ��
				Calendar calendar = Calendar.getInstance();
				// ------����������
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH);
				day = calendar.get(Calendar.DATE);

				hour = Integer.parseInt(time.split(Tools.MAOHAO)[0]);
				minute = Integer.parseInt(time.split(Tools.MAOHAO)[1]);
			}
		} else {
			// ��ʾ��ǰ����ʱ��
			Calendar calendar = Calendar.getInstance();
			// ------����������
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DATE);
			Log.d("DAYMONTHYEAR", day + "****" + month + "****" + year);
			// ------����������

			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
		}

		// ��Ӵ�С���·ݲ�����ת��Ϊlist,����֮����ж�
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);
		// �ҵ�dialog�Ĳ����ļ�
		// LayoutInflater inflater = (LayoutInflater)
		// context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Context view = a;
		// -------------------data�����꣬data_year_lunar������,data_month_lunar������
		// ��
		final ArrayList<String> data = new ArrayList<String>();
		for (int i = START_YEAR; i < END_YEAR; i++) {
			data.add(i + "");
		}
		// �õ�����������б�
		final ArrayList<String> data_year_lunar = new ArrayList<String>();
		for (int i = START_YEAR; i < END_YEAR; i++) {
			data_year_lunar.add(SolarNumToLunarChinese.NumYearToChineseYear(i));
		}

		// �õ��������·��б�
		ArrayList<String> data_month_lunar = new ArrayList<String>();
		for (int i = 0; i < SolarNumToLunarChinese.dayNum.length; i++) {
			data_month_lunar.add(SolarNumToLunarChinese.dayName[i]);
		}

		final StrArrayWheelView wv_year = (StrArrayWheelView) (view)
				.findViewById(R.id.year);

		wv_year.setCyclic(true);// ��ѭ������
		// wv_year.setLabel("��");// �������

		// ��
		final StrArrayWheelView wv_month = (StrArrayWheelView) (view)
				.findViewById(R.id.month);
		wv_month.setCyclic(true);
		// wv_month.setLabel("��");

		// ��
		final StrArrayWheelView wv_day = (StrArrayWheelView) (view)
				.findViewById(R.id.day);
		wv_day.setCyclic(true);
		final CheckBox cb = (CheckBox) (view)
				.findViewById(R.id.cb_islunar_time_layout);// �ж��Ƿ�Ϊ����
		if (!canChooseLunar) {
			cb.setVisibility(View.GONE);
		} else {
			cb.setVisibility(View.VISIBLE);
		}
		cb.setChecked(isLunar);
		is_cb_Checked = cb.isChecked();
		final TextView textView = (TextView) (view)
				.findViewById(R.id.tv_time_layout);
		Tools.TYPE_DATE_Is_Lunar = is_cb_Checked;
		if (is_cb_Checked) {
			// ���������յ���ʾ
			// �õ���ǰ����������
			textSize = CHINESE_TEXTSIZE;
			HashMap<String, String> lunar_date = SolarToLunarTools.getLunar(
					year + "", month + 1 + "", day + "");
			wv_year.setAdapter(new StrArrayWheelAdapter(data_year_lunar));// ����"��"����ʾ����
			lunar_year = Integer.parseInt(LunarTextChineseToNumString
					.getYear(lunar_date.get("lunar_year")));
			wv_year.setCurrentItem(lunar_year - START_YEAR);// ��ʼ��ʱ��ʾ������
			lunar_month = Integer.parseInt(LunarTextChineseToNumString
					.getMonth(lunar_date.get("lunar_month")));
			lunar_day = Integer.parseInt(LunarTextChineseToNumString
					.getDay(lunar_date.get("lunar_day")));
			wv_month.setAdapter(new StrArrayWheelAdapter(
					LunarTextChineseToNumString.getMonth_list(lunar_year)));
			wv_day.setAdapter(new StrArrayWheelAdapter(
					LunarTextChineseToNumString.getDay_list(lunar_year,
							lunar_month)));
			if (lunar_month > 20) {
				lunar_month = lunar_month - 20 + 1;
			}

			wv_month.setCurrentItem(lunar_month);
			wv_day.setCurrentItem(lunar_day);
			String content = (wv_year.getTextItem(wv_year.getCurrentItem()))
					+ "��" + (wv_month.getTextItem(wv_month.getCurrentItem()))
					+ "��" + (wv_day.getTextItem(wv_day.getCurrentItem()) + "��");
			Tools.time = content;
			textView.setText(content);
		} else {
			// ���������յ���ʾ
			textSize = NUM_TEXTSIZE;
			wv_year.setAdapter(new StrArrayWheelAdapter(data));// ����"��"����ʾ����
			wv_year.setCurrentItem(year - START_YEAR);// ��ʼ��ʱ��ʾ������
			wv_month.setAdapter(new DateNumericWheelAdapter(1, 12));
			wv_month.setCurrentItem(month);
			// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
			if (list_big.contains(String.valueOf(month + 1))) {
				wv_day.setAdapter(new DateNumericWheelAdapter(1, 31));
			} else if (list_little.contains(String.valueOf(month + 1))) {
				wv_day.setAdapter(new DateNumericWheelAdapter(1, 30));
			} else {
				// ����
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
					wv_day.setAdapter(new DateNumericWheelAdapter(1, 29));
				else
					wv_day.setAdapter(new DateNumericWheelAdapter(1, 28));
			}
			wv_day.setCurrentItem(day - 1);
			// wv_day.setLabel("��");
			String content=(wv_year.getTextItem(wv_year.getCurrentItem()))
					+ "-"
					+DataTools.getStrFormat2StrDate(wv_month.getTextItem(wv_month.getCurrentItem()))
					+ "-" + DataTools.getStrFormat2StrDate((wv_day.getTextItem(wv_day.getCurrentItem())));
			Tools.time = content;
			textView.setText(content);
		}

		// ʱ
		final StrArrayWheelView wv_hours = (StrArrayWheelView) (view)
				.findViewById(R.id.hour);
		wv_hours.setAdapter(new DateNumericWheelAdapter(0, 23));
		wv_hours.setCyclic(true);
		// wv_hours.setLabel("ʱ");
		wv_hours.setCurrentItem(hour);

		// ��
		final StrArrayWheelView wv_mins = (StrArrayWheelView) (view)
				.findViewById(R.id.mins);
		wv_mins.setAdapter(new DateNumericWheelAdapter(0, 59, "%02d"));
		wv_mins.setCyclic(true);
		// wv_mins.setLabel("��");
		wv_mins.setCurrentItem(minute);

		// ����Ǹ���,����ʾΪ"02"����ʽ
		String parten = "00";
		DecimalFormat decimal = new DecimalFormat(parten);

		if (flag == Tools.FLAG_DATE) {
			wv_year.setVisibility(View.VISIBLE);
			wv_month.setVisibility(View.VISIBLE);
			wv_day.setVisibility(View.VISIBLE);
			textView.setVisibility(View.VISIBLE);
			if(canChooseLunar){
				cb.setVisibility(View.VISIBLE);
			}
			

			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
		} else {
			wv_year.setVisibility(View.GONE);
			wv_month.setVisibility(View.GONE);
			wv_day.setVisibility(View.GONE);
			textView.setVisibility(View.GONE);
			cb.setVisibility(View.GONE);

			wv_hours.setVisibility(View.VISIBLE);
			wv_mins.setVisibility(View.VISIBLE);

			Tools.time = TimeTools.time4Format((wv_hours.getTextItem(wv_hours
					.getCurrentItem()))
					+ Tools.FLAG_TIME_MAOHAO
					+ (wv_mins.getTextItem(wv_mins.getCurrentItem())));
		}
		// ���"��"����
		StrArrayWheelView.OnWheelChangedListener wheelListener_year = new StrArrayWheelView.OnWheelChangedListener() {
			public void onChanged(StrArrayWheelView wheel, int oldValue,
					int newValue) {
				if (!is_cb_Checked) {
					int year_num = newValue + START_YEAR;
					
					int curr=wv_day.getCurrentItem();
				
					
					// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
					if (list_big.contains(String.valueOf(wv_month
							.getCurrentItem() + 1))) {
						wv_day.setAdapter(new DateNumericWheelAdapter(1, 31));
						if(curr>30){
							wv_day.setCurrentItem(0);
						}
					} else if (list_little.contains(String.valueOf(wv_month
							.getCurrentItem() + 1))) {
						wv_day.setAdapter(new DateNumericWheelAdapter(1, 30));
						if(curr>29){
							wv_day.setCurrentItem(0);
						}
					} else {
						if ((year_num % 4 == 0 && year_num % 100 != 0)
								|| year_num % 400 == 0){
							wv_day.setAdapter(new DateNumericWheelAdapter(1, 29));
							if(curr>28){
								wv_day.setCurrentItem(0);
							}
						}
						
						else{
							wv_day.setAdapter(new DateNumericWheelAdapter(1, 28));
							if(curr>27){
								wv_day.setCurrentItem(0);
							}
						}
							
					}
					// ����Ǹ���,����ʾΪ"02"����ʽ
					String parten = "00";
					DecimalFormat decimal = new DecimalFormat(parten);
					String content=(wv_year.getTextItem(wv_year.getCurrentItem()))
							+ "-"
							+DataTools.getStrFormat2StrDate(wv_month.getTextItem(wv_month.getCurrentItem()))
							+ "-" + DataTools.getStrFormat2StrDate((wv_day.getTextItem(wv_day.getCurrentItem())));
					Tools.time = content;
					textView.setText(content);
				} else if (is_cb_Checked) {
					// Ϊ���������
					String year = (wv_year
							.getTextItem(wv_year.getCurrentItem()));
					int lunar_year = Integer
							.parseInt(LunarTextChineseToNumString.getYear(year));
					wv_month.setAdapter(new StrArrayWheelAdapter(
							LunarTextChineseToNumString
									.getMonth_list(lunar_year)));
					int lunar_month = Integer
							.parseInt(LunarTextChineseToNumString
									.getMonth(wv_month.getTextItem(wv_month
											.getCurrentItem())));
					wv_day.setAdapter(new StrArrayWheelAdapter(
							LunarTextChineseToNumString.getDay_list(lunar_year,
									lunar_month)));
					if (wv_month.getCurrentItem() > LunarTextChineseToNumString
							.getMonth_list(lunar_year).size()) {
						wv_month.setCurrentItem(1);
					}
					if (wv_day.getCurrentItem() > LunarTextChineseToNumString
							.getDay_list(lunar_year, lunar_month).size()) {
						wv_day.setCurrentItem(1);
					}
					Log.d("DAYLENGTH_CUREENT", LunarTextChineseToNumString
							.getDay_list(lunar_year, lunar_month).size()
							+ "&&&&&" + wv_day.getCurrentItem());
					// Log.d("YEAR_AND_MONTH",lunar_year+"&&&&&"+lunar_month);
					String content = (wv_year.getTextItem(wv_year
							.getCurrentItem()))
							+ "��"
							+ (wv_month.getTextItem(wv_month.getCurrentItem()))
							+ "��"
							+ (wv_day.getTextItem(wv_day.getCurrentItem()) + "��");
					Tools.time = content;

					textView.setText(content);
				}

			}
		};
		// ���"��"����
		StrArrayWheelView.OnWheelChangedListener wheelListener_month = new StrArrayWheelView.OnWheelChangedListener() {
			public void onChanged(StrArrayWheelView wheel, int oldValue,
					int newValue) {
				if (!is_cb_Checked) {
					// ���Ϊ����
					
					int month_num = newValue + 1;
					int curr=wv_day.getCurrentItem();
					
					// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
					if (list_big.contains(String.valueOf(month_num))) {
						wv_day.setAdapter(new DateNumericWheelAdapter(1, 31));
						if(curr>30){
							wv_day.setCurrentItem(0);
						}
					} else if (list_little.contains(String.valueOf(month_num))) {
						wv_day.setAdapter(new DateNumericWheelAdapter(1, 30));
						if(curr>29){
							wv_day.setCurrentItem(0);
						}
					} else {
						if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
								.getCurrentItem() + START_YEAR) % 100 != 0)
								|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0){
							wv_day.setAdapter(new DateNumericWheelAdapter(1, 29));
							if(curr>28){
								wv_day.setCurrentItem(0);
							}
						}
							
						else{
							wv_day.setAdapter(new DateNumericWheelAdapter(1, 28));
							if(curr>27){
								wv_day.setCurrentItem(0);
							}
						}
							
					}
					// ����Ǹ���,����ʾΪ"02"����ʽ
					String parten = "00";
					DecimalFormat decimal = new DecimalFormat(parten);

					String content = (wv_year.getTextItem(wv_year
							.getCurrentItem()))
							+ "-"
							+ DataTools.getStrFormat2StrDate(wv_month
									.getTextItem(wv_month.getCurrentItem()))
							+ "-"
							+ DataTools.getStrFormat2StrDate((wv_day
									.getTextItem(wv_day.getCurrentItem())));
					Tools.time = content;
					textView.setText(content);

				} else {
					// Ϊ���������
					int lunar_year = Integer
							.parseInt(LunarTextChineseToNumString
									.getYear((wv_year.getTextItem(wv_year
											.getCurrentItem()))));
					wv_month.setAdapter(new StrArrayWheelAdapter(
							LunarTextChineseToNumString
									.getMonth_list(lunar_year)));
					int lunar_month = Integer
							.parseInt(LunarTextChineseToNumString
									.getMonth(wv_month.getTextItem(wv_month
											.getCurrentItem())));
					wv_day.setAdapter(new StrArrayWheelAdapter(
							LunarTextChineseToNumString.getDay_list(lunar_year,
									lunar_month)));
					if (wv_month.getCurrentItem() > LunarTextChineseToNumString
							.getMonth_list(lunar_year).size()) {
						wv_month.setCurrentItem(1);
					}
					if (wv_day.getCurrentItem() > LunarTextChineseToNumString
							.getDay_list(lunar_year, lunar_month).size()) {
						wv_day.setCurrentItem(1);
					}
					String content = (wv_year.getTextItem(wv_year
							.getCurrentItem()))
							+ "��"
							+ (wv_month.getTextItem(wv_month.getCurrentItem()))
							+ "��"
							+ (wv_day.getTextItem(wv_day.getCurrentItem()) + "��");
					Tools.time = content;
					textView.setText(content);
				}

			}
		};

		// ���"��"����
		StrArrayWheelView.OnWheelChangedListener wheelListener_day = new StrArrayWheelView.OnWheelChangedListener() {
			public void onChanged(StrArrayWheelView wheel, int oldValue,
					int newValue) {
				if (!is_cb_Checked) {
					// ����Ǹ���,����ʾΪ"02"����ʽ
					String parten = "00";
					DecimalFormat decimal = new DecimalFormat(parten);
					String content = (wv_year.getTextItem(wv_year
							.getCurrentItem()))
							+ "-"
							+ DataTools.getStrFormat2StrDate(wv_month
									.getTextItem(wv_month.getCurrentItem()))
							+ "-"
							+ DataTools.getStrFormat2StrDate((wv_day
									.getTextItem(wv_day.getCurrentItem())));
					Tools.time = content;
					textView.setText(content);
				} else {
					// Ϊ���������
					String content = (wv_year.getTextItem(wv_year
							.getCurrentItem()))
							+ "��"
							+ (wv_month.getTextItem(wv_month.getCurrentItem()))
							+ "��"
							+ (wv_day.getTextItem(wv_day.getCurrentItem()) + "��");
					Tools.time = content;
					textView.setText(content);
				}

			}
		};
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				Tools.TYPE_DATE_Is_Lunar = isChecked;
				if (isChecked) {

					// ��ʾ��������
				//	if (!is_cb_Checked) {
						// �õ���ǰ����������
						AlartCommenSetTools.is_lunar = AlarmEntity.ISLUNAR_YES;
						textSize = CHINESE_TEXTSIZE;
						is_cb_Checked = isChecked;
//						String day11 = wv_day.getTextItem(wv_day
//								.getCurrentItem());
//						HashMap<String, String> lunar_date11 = SolarToLunarTools
//								.getLunar("1991", "02", "03");
						String time11 =Tools.time;
						String year=time11.split("-")[0];
						String month=time11.split("-")[1];
						String day=time11.split("-")[2];
						HashMap<String, String> lunar_date = SolarToLunarTools
								.getLunar(
										year,
										month,
										day);
//						HashMap<String, String> lunar_date = SolarToLunarTools
//								.getLunar(
//										(wv_year.getTextItem(wv_year
//												.getCurrentItem())) + "",
//										(wv_month.getTextItem(wv_month
//												.getCurrentItem())),
//										(wv_day.getTextItem(wv_day
//												.getCurrentItem())) + "");
						wv_year.setAdapter(new StrArrayWheelAdapter(
								data_year_lunar));// ����"��"����ʾ����
						int lunar_year = Integer
								.parseInt(LunarTextChineseToNumString
										.getYear(lunar_date.get("lunar_year")));
						wv_year.setCurrentItem(lunar_year - START_YEAR);// ��ʼ��ʱ��ʾ������
						int lunar_month = Integer.parseInt(LunarTextChineseToNumString
								.getMonth(lunar_date.get("lunar_month")));
						int lunar_day = Integer
								.parseInt(LunarTextChineseToNumString
										.getDay(lunar_date.get("lunar_day")));
						wv_month.setAdapter(new StrArrayWheelAdapter(
								LunarTextChineseToNumString
										.getMonth_list(lunar_year)));
						wv_day.setAdapter(new StrArrayWheelAdapter(
								LunarTextChineseToNumString.getDay_list(
										lunar_year, lunar_month)));
						if (lunar_month > 20) {
							lunar_month = lunar_month - 20+1;
						}

						//Log.d("WheelTest",lunar_day+""+);
						wv_month.setCurrentItem(lunar_month-1);
						wv_day.setCurrentItem(lunar_day-1 );

					//}
					String content = (wv_year.getTextItem(wv_year
							.getCurrentItem()))
							+ "��"
							+ (wv_month.getTextItem(wv_month.getCurrentItem()))
							+ "��"
							+ (wv_day.getTextItem(wv_day.getCurrentItem()) + "��");
					Tools.time = content;
					textView.setText(content);

				} else {
					// ��ʾ��������
					AlartCommenSetTools.is_lunar = AlarmEntity.ISLUNAR_NO;
					if (is_cb_Checked) {
						textSize = NUM_TEXTSIZE;
						is_cb_Checked = isChecked;
						String date = LunarTextChineseToNumString
								.getNumString((wv_year.getTextItem(wv_year
										.getCurrentItem()))
										+ "��"
										+ (wv_month.getTextItem(wv_month
												.getCurrentItem()))
										+ "��"
										+ (wv_day.getTextItem(wv_day
												.getCurrentItem())));
						HashMap<String, String> h = LunarToSolarTools
								.getSolar(date);
						int year = Integer.parseInt(h.get("year"));
						int month = Integer.parseInt(h.get("month"));
						int day = Integer.parseInt(h.get("day"));
						wv_year.setAdapter(new StrArrayWheelAdapter(data));// ����"��"����ʾ����
						wv_year.setCurrentItem(year - START_YEAR);// ��ʼ��ʱ��ʾ������
						wv_month.setAdapter(new DateNumericWheelAdapter(1, 12));
						wv_month.setCurrentItem(month - 1);
						// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
						if (list_big.contains(String.valueOf(month))) {
							wv_day.setAdapter(new DateNumericWheelAdapter(1, 31));
						} else if (list_little.contains(String.valueOf(month))) {
							wv_day.setAdapter(new DateNumericWheelAdapter(1, 30));
						} else {
							// ����
							if ((year % 4 == 0 && year % 100 != 0)
									|| year % 400 == 0)
								wv_day.setAdapter(new DateNumericWheelAdapter(
										1, 29));
							else
								wv_day.setAdapter(new DateNumericWheelAdapter(
										1, 28));
						}
						wv_day.setCurrentItem(day - 1);
						// wv_day.setLabel("��");

					}
					String content = (wv_year.getTextItem(wv_year
							.getCurrentItem()))
							+ "-"
							+ DataTools.getStrFormat2StrDate(wv_month
									.getTextItem(wv_month.getCurrentItem()))
							+ "-"
							+ DataTools.getStrFormat2StrDate((wv_day
									.getTextItem(wv_day.getCurrentItem())));
					Tools.time = content;
					textView.setText(content);
				}
				wv_day.TEXT_SIZE = textSize;
				wv_hours.TEXT_SIZE = textSize;
				wv_mins.TEXT_SIZE = textSize;
				wv_month.TEXT_SIZE = textSize;
				wv_year.TEXT_SIZE = textSize;
			}

		});

		// ���ʱ�ļ���
		StrArrayWheelView.OnWheelChangedListener wheelListener_hour = new StrArrayWheelView.OnWheelChangedListener() {
			public void onChanged(StrArrayWheelView wheel, int oldValue,
					int newValue) {
				Tools.time = TimeTools.time4Format((wv_hours
						.getTextItem(wv_hours.getCurrentItem()))
						+ Tools.FLAG_TIME_MAOHAO
						+ (wv_mins.getTextItem(wv_mins.getCurrentItem())));
			}

		};
		// ��ӷֵļ���
		StrArrayWheelView.OnWheelChangedListener wheelListener_min = new StrArrayWheelView.OnWheelChangedListener() {
			public void onChanged(StrArrayWheelView wheel, int oldValue,
					int newValue) {
				Tools.time = TimeTools.time4Format((wv_hours
						.getTextItem(wv_hours.getCurrentItem()))
						+ Tools.FLAG_TIME_MAOHAO
						+ (wv_mins.getTextItem(wv_mins.getCurrentItem())));
			}

		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);
		wv_day.addChangingListener(wheelListener_day);

		wv_hours.addChangingListener(wheelListener_hour);
		wv_mins.addChangingListener(wheelListener_min);
		// ������Ļ�ܶ���ָ��ѡ��������Ĵ�С

		wv_day.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;

	}
}
