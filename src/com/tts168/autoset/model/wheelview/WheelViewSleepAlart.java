package com.tts168.autoset.model.wheelview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.amo.demo.arrWheelview.StrArrayWheelAdapter;
import com.amo.demo.arrWheelview.StrArrayWheelView;
import com.amo.demo.wheelview.DateNumericWheelAdapter;
import com.amo.demo.wheelview.SolarNumToLunarChinese;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.DisplayUtil;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.others.TimeTools;
import com.tts168.autoset.tools.others.time.LunarTextChineseToNumString;
import com.tts168.autoset.tools.others.time.LunarToSolarTools;
import com.tts168.autoset.tools.others.time.SolarToLunarTools;
import com.tts168.autoset.view.wheelview.DataNameWheelView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
 * 
 * 
 * @author Ԭ��
 * 
 */
public class WheelViewSleepAlart {

	public static int textSize = 0;// �����С
	public static final int NUM_TEXTSIZE = DisplayUtil.sp2px(MyApplication.getInstance().getCur_Activity(), 20);// ���������С
	public static final int CHINESE_TEXTSIZE = DisplayUtil.sp2px(MyApplication.getInstance().getCur_Activity(), 18);// ���������С

	public static boolean is_cb_Checked;// ������¼CheckBox�չ���ʱ�Ƿ��Ѿ�ѡ���״̬

	/**
	 * 
	 * @param a
	 * @param mins AlartCommenSetTools.delay_time
	 *            ÿ������ļ��ʱ�䣬û�д���Ĭ��ֵ��0-60��
	 * @param times  AlartCommenSetTools.delay_num
	 *            ���������û�д���Ĭ��ֵ��0-10��
	 */

	public static View getTimeView(final Context a, int mins, int times) {

		LayoutInflater inflater = (LayoutInflater) a
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.time_layout, null);

		Calendar calendar = Calendar.getInstance();

		int wait_time = mins;
		int awake_times = times;

		final StrArrayWheelView wv_year = (StrArrayWheelView) view
				.findViewById(R.id.year);
		DataNameWheelView dataNameWheelView=new DataNameWheelView(view);
		
		dataNameWheelView.setMonthText("ÿ");
		
			dataNameWheelView.setHourText("����");
			dataNameWheelView.setMinText("��");
			
		wv_year.setCyclic(true);// ��ѭ������
		wv_year.setLabel("��");// �������

		// ��
		final StrArrayWheelView wv_month = (StrArrayWheelView) view
				.findViewById(R.id.month);
		wv_month.setCyclic(true);
		wv_month.setLabel("��");

		// ��
		final StrArrayWheelView wv_day = (StrArrayWheelView) view
				.findViewById(R.id.day);
		wv_day.setCyclic(true);

		// ʱ��
		final StrArrayWheelView wv_minus = (StrArrayWheelView) view
				.findViewById(R.id.hour);
		wv_minus.setAdapter(new DateNumericWheelAdapter(0, 5));
		wv_minus.setCyclic(true);
		//wv_minus.setLabel("����");
		wv_minus.setCurrentItem(wait_time);

		// ����
		final StrArrayWheelView wv_times = (StrArrayWheelView) view
				.findViewById(R.id.mins);
		wv_times.setAdapter(new DateNumericWheelAdapter(0, 2));
		wv_times.setCyclic(true);
		//wv_times.setLabel("��");
		wv_times.setCurrentItem(awake_times);

		final CheckBox cb = (CheckBox) view
				.findViewById(R.id.cb_islunar_time_layout);// �ж��Ƿ�Ϊ����

		cb.setVisibility(View.GONE);
		final TextView textView = (TextView) view
				.findViewById(R.id.tv_time_layout);
		// ����Ǹ���,����ʾΪ"02"����ʽ
		String parten = "00";
		DecimalFormat decimal = new DecimalFormat(parten);

		wv_year.setVisibility(View.GONE);
		wv_month.setVisibility(View.GONE);
		wv_day.setVisibility(View.GONE);

		wv_minus.setVisibility(View.VISIBLE);
		wv_times.setVisibility(View.VISIBLE);
		
		textView.setText("ÿ"+(wv_minus.getTextItem(wv_minus
				.getCurrentItem()))+"������һ�Σ���"+(wv_times.getTextItem(wv_times.getCurrentItem()))+"��");
		// ���ʱ�ļ���
		StrArrayWheelView.OnWheelChangedListener wheelListener_hour = new StrArrayWheelView.OnWheelChangedListener() {
			public void onChanged(StrArrayWheelView wheel, int oldValue,
					int newValue) {
			
				AlartCommenSetTools.delay_time=Double.parseDouble((wv_minus.getTextItem(wv_minus
						.getCurrentItem())+""));
				AlartTools.getUpSetEntity.setDelay_time(AlartCommenSetTools.delay_time);
					textView.setText("ÿ"+(wv_minus.getTextItem(wv_minus
						.getCurrentItem()))+"������һ�Σ���"+(wv_times.getTextItem(wv_times.getCurrentItem()))+"��");
					
			}

		};
		// ��ӷֵļ���
		StrArrayWheelView.OnWheelChangedListener wheelListener_min = new StrArrayWheelView.OnWheelChangedListener() {
			public void onChanged(StrArrayWheelView wheel, int oldValue,
					int newValue) {
				AlartCommenSetTools.delay_num=Double.parseDouble((wv_times.getTextItem(wv_times
						.getCurrentItem())+""));
				textView.setText("ÿ"+(wv_minus.getTextItem(wv_minus
						.getCurrentItem()))+"������һ�Σ���"+(wv_times.getTextItem(wv_times.getCurrentItem()))+"��");
				
			}

		};

		wv_minus.addChangingListener(wheelListener_hour);
		wv_times.addChangingListener(wheelListener_min);
		// ������Ļ�ܶ���ָ��ѡ��������Ĵ�С

		wv_minus.TEXT_SIZE = NUM_TEXTSIZE;
		wv_times.TEXT_SIZE = NUM_TEXTSIZE;
		return view;
	}
}
