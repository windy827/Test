package com.tts168.autoset.view.wheelview;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.view.MyBaseView;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 
 * @author Ԭ��
 * @describe ����������ʱ�ֵĿؼ������Կ����ı�������set������Text(String content)����������Ӧ������Ĭ�ϸÿؼ�����ʾ��
 * 				����DataWheelView��һЩ���÷������������á��Ժ�Ҫ�������䡿
 *
 */
public class DataNameWheelView extends MyBaseView{

	public RelativeLayout rl_year,rl_month,rl_day,rl_hour,rl_min;
	
	TextView tv_year,tv_month,tv_day,tv_hour,tv_min;
	public DataNameWheelView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	public DataNameWheelView(View view) {
		super(view);
		// TODO Auto-generated constructor stub
	}
	/**
	 * �������DataView��Ӧ���ı�
	 * @param content
	 */
	public void setYearText(String content){
		rl_year.setVisibility(View.VISIBLE);
		tv_year.setText(content);
	}
	/**
	 * �����µ�DataView��Ӧ���ı�
	 * @param content
	 */
	public void setMonthText(String content){
		rl_month.setVisibility(View.VISIBLE);
		tv_month.setText(content);
	}
	/**
	 * �����յ�DataView��Ӧ���ı�
	 * @param content
	 */
	public void setDayText(String content){
		rl_day.setVisibility(View.VISIBLE);
		tv_day.setText(content);
	}
	/**
	 * ����ʱ��DataView��Ӧ���ı�
	 * @param content
	 */
	public void setHourText(String year){
		rl_hour.setVisibility(View.VISIBLE);
		tv_hour.setText(year);
	}
	/**
	 * ���÷ֵ�DataView��Ӧ���ı�
	 * @param content
	 */
	public void setMinText(String content){
		rl_min.setVisibility(View.VISIBLE);
		tv_min.setText(content);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticFindViewByView() {
		// TODO Auto-generated method stub
		rl_year=(RelativeLayout) view.findViewById(R.id.rl_year);
		rl_month=(RelativeLayout) view.findViewById(R.id.rl_month);
		rl_day=(RelativeLayout) view.findViewById(R.id.rl_day);
		rl_min=(RelativeLayout) view.findViewById(R.id.rl_min);
		rl_hour=(RelativeLayout) view.findViewById(R.id.rl_hour);
		
		tv_year=(TextView) view.findViewById(R.id.tv_year);
		tv_month=(TextView) view.findViewById(R.id.tv_month);
		tv_day=(TextView) view.findViewById(R.id.tv_day);
		tv_min=(TextView) view.findViewById(R.id.tv_min);
		tv_hour=(TextView) view.findViewById(R.id.tv_hour);
		
	}

	@Override
	public void staticByViewListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticFindViewByActivity() {
		// TODO Auto-generated method stub
		rl_year=(RelativeLayout) activity.findViewById(R.id.rl_year);
		rl_month=(RelativeLayout) activity.findViewById(R.id.rl_month);
		rl_day=(RelativeLayout) activity.findViewById(R.id.rl_day);
		rl_min=(RelativeLayout) activity.findViewById(R.id.rl_min);
		rl_hour=(RelativeLayout) activity.findViewById(R.id.rl_hour);
		
		tv_year=(TextView) activity.findViewById(R.id.tv_year);
		tv_month=(TextView) activity.findViewById(R.id.tv_month);
		tv_day=(TextView) activity.findViewById(R.id.tv_day);
		tv_min=(TextView) activity.findViewById(R.id.tv_min);
		tv_hour=(TextView) activity.findViewById(R.id.tv_hour);
	}

	@Override
	public void staticByActivityListener() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	

}
