package com.tts168.autoset.view.popwindow;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity;
import com.tts168.autoset.activity.alart.AwakeAlartEditActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.SharedPrefenceSetTools;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.wheelview.WheelViewTools;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.PopupWindow.OnDismissListener;

/**
 * ��������popWindow
 * 
 * @author Ԭ��
 * 
 */
public class PopupWindowAlartCycle implements OnCheckedChangeListener,
		OnClickListener {

	private Context mContext;
	private static PopupWindow popupWindow;
	private View mPopupWindowView;

	private CheckBox cb_once;// һ��
	private CheckBox cb_everyday;// ÿ��
	private CheckBox cb_week1, cb_week2, cb_week3, cb_week4, cb_week5,
			cb_week6, cb_week7;
	private CheckBox[] cb_week;
	private TextView tv_cycle;
	private RelativeLayout rl_confirm, rl_cancle;
	int tempweek[] = new int[7];
	private RelativeLayout rl_date;//����
	private TextView tv_date;
	public PopupWindowAlartCycle(Context mContext, TextView baseView,RelativeLayout rl_date,TextView tv_date) {
		this.mContext = mContext;
		this.tv_cycle = baseView;
		this.rl_date=rl_date;
		this.tv_date=tv_date;
		initPopupWindow();
	}

	/**
	 * ��ʼ��popupwindow
	 */
	private void initPopupWindow() {
		initPopupWindowView();
		// ��ʼ��popupwindow������ʾview�����ø�view�Ŀ��/�߶�
		popupWindow = new PopupWindow(mPopupWindowView,
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		if ((int) AlartTools.alarmEntity.getFre_mode() == AlartTools.Cycle.Free_model.FRE_MODEL_ONCE) {
			cb_once.setChecked(true);
			for (int i = 0; i < AlartTools.Cycle.week_check.length; i++) {
				cb_week[i].setChecked(false);
			}
			cb_everyday.setChecked(false);

		} else if ((int) AlartTools.alarmEntity.getFre_mode() == AlartTools.Cycle.Free_model.FRE_MODEL_EVERYDAY) {
			cb_everyday.setChecked(true);
			for (int i = 0; i < AlartTools.Cycle.week_check.length; i++) {
				cb_week[i].setChecked(true);
			}
			cb_once.setChecked(false);
		} else {
			cb_once.setChecked(false);
			cb_everyday.setChecked(false);
			for (int i = 0; i < AlartTools.Cycle.week_check.length; i++) {
				if (AlartTools.Cycle.week_check[i] == 0) {
					cb_week[i].setChecked(false);
				} else {
					cb_week[i].setChecked(true);
				}
			}

		}

		for (int i = 0; i < cb_week.length; i++) {
			cb_week[i]
					.setOnCheckedChangeListener(new MyWeekOnCheckChangedListener(
							i));
		}
		cb_once.setOnCheckedChangeListener(this);
		cb_everyday.setOnCheckedChangeListener(this);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(false);
		// �����Ϊ�˵��������Back��Ҳ��ʹ����ʧ�����Ҳ�����Ӱ����ı�����ʹ�ø÷����������֮�⣬�ſɹرմ���
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// Background��������Ϊnull��dismiss��ʧЧ
		// popupWindow.setBackgroundDrawable(null);
		popupWindow.update();
		// popupWindow����dismissʱ������������setOutsideTouchable(true)�����view֮��/����back�ĵط�Ҳ�ᴥ��
		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				// showToast("�ر�popupwindow");
			}
		});

	}

	/**
	 * ��ʼ��popupwindowView,����view�е�textview����¼�
	 */
	private void initPopupWindowView() {

		// �ȳ�ʼ������
		AlartTools.Cycle.week_check = AlartTools.Cycle.getLastIntArrray(
				(int) AlartTools.alarmEntity.getFrequency(), 7);
		AlartTools.Cycle.cycleValue = AlartTools.Cycle
				.binarayInt2int(AlartTools.Cycle.week_check);
		AlartTools.Cycle.Fre_model = (int) AlartTools.alarmEntity.getFre_mode();
		AlartTools.Cycle.current_Fre_model = AlartTools.Cycle.Fre_model;
		mPopupWindowView = LayoutInflater.from(mContext).inflate(
				R.layout.popalartcycle, null);
		cb_once = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week_once);
		cb_everyday = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week_everyday);
		cb_week1 = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week1);
		cb_week2 = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week2);
		cb_week3 = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week3);
		cb_week4 = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week4);
		cb_week5 = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week5);
		cb_week6 = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week6);
		cb_week7 = (CheckBox) mPopupWindowView
				.findViewById(R.id.cb_notify_alart_cycle_week7);
		cb_week = new CheckBox[] { cb_week1, cb_week2, cb_week3, cb_week4,
				cb_week5, cb_week6, cb_week7 };
		rl_confirm = (RelativeLayout) mPopupWindowView
				.findViewById(R.id.rl_alartcycle_confirm);
		rl_cancle = (RelativeLayout) mPopupWindowView
				.findViewById(R.id.rl_alartcycle_cancle);
		rl_confirm.setOnClickListener(this);
		rl_cancle.setOnClickListener(this);
	}

	public void showPopwindow() {
		if (!popupWindow.isShowing()) {
			popupWindow.showAtLocation(this.tv_cycle, Gravity.BOTTOM, 0, 0);
		} else {

		}
	}

	public void dismissPopwindow() {
		if (popupWindow.isShowing()) {
			popupWindow.dismiss();
		}
	}

	/**
	 * ���������Ӧ���±��޸�AlartTools.week_check
	 * 
	 * @param index
	 */
	class MyWeekOnCheckChangedListener implements OnCheckedChangeListener {

		int index;

		/**
		 * �����Ӧ���±�
		 * 
		 * @param index
		 */
		public MyWeekOnCheckChangedListener(int index) {
			this.index = index;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub

			if (isChecked) {

				AlartTools.Cycle.week_check[index] = 1;
				
			
				int cycleValue = AlartTools.Cycle
						.binarayInt2int(AlartTools.Cycle.week_check);
				if (cycleValue == 127) {
					cb_everyday.setChecked(true);
					AlartTools.Cycle.current_Fre_model = AlartTools.Cycle.Free_model.FRE_MODEL_EVERYDAY;
				} else {
					AlartTools.Cycle.current_Fre_model = AlartTools.Cycle.Free_model.FRE_MODEL_DEFINED;
				}
				cb_once.setChecked(false);
			} else {
				AlartTools.Cycle.week_check[index] = 0;
				
				int cycleValue = AlartTools.Cycle
						.binarayInt2int(AlartTools.Cycle.week_check);
				if (cycleValue == 0) {
					cb_once.setChecked(true);
					AlartTools.Cycle.current_Fre_model = AlartTools.Cycle.Free_model.FRE_MODEL_ONCE;
				}else{
					AlartTools.Cycle.current_Fre_model = AlartTools.Cycle.Free_model.FRE_MODEL_DEFINED;
				}
				cb_everyday.setChecked(false);
			}

		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch(buttonView.getId()){
		case R.id.cb_notify_alart_cycle_week_once:
			if(isChecked){
				AlartTools.Cycle.current_Fre_model=AlartTools.Cycle.Free_model.FRE_MODEL_ONCE;
			
				
				for (int i = 0; i < AlartTools.Cycle.week_check.length; i++) {
					tempweek[i]=AlartTools.Cycle.week_check[i];
					cb_week[i].setChecked(false);
				}
				cb_everyday.setChecked(false);
			}else{
				if(AlartTools.Cycle.current_Fre_model==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
					int cycleValue = AlartTools.Cycle
							.binarayInt2int(tempweek);
					if(cycleValue==0){
						tempweek=AlartTools.Cycle.Init_week_check;
					}
					for (int i = 0; i < tempweek.length; i++) {
						if (tempweek[i] == 0) {
							cb_week[i].setChecked(false);
						} else {
							cb_week[i].setChecked(true);
						}
					}
				}
			}

			break;
		case R.id.cb_notify_alart_cycle_week_everyday:
			if(isChecked){
				AlartTools.Cycle.current_Fre_model=AlartTools.Cycle.Free_model.FRE_MODEL_EVERYDAY;
				for (int i = 0; i < AlartTools.Cycle.week_check.length; i++) {
					cb_week[i].setChecked(true);
				}
			}else{
		
				if (AlartTools.Cycle.current_Fre_model==AlartTools.Cycle.Free_model.FRE_MODEL_EVERYDAY) {
					cb_once.setChecked(true);
				}
				
			}
			break;
		}
	}
	/**
	 * ������ʾ���ڵĿؼ��ɼ�������������
	 * @param date
	 */
	public void setDateVisiable(){
		rl_date.setVisibility(View.VISIBLE);
		Tools.date=GetAndSetTime.getData();
		tv_date.setText(Tools.date);
	}
	/**
	 * ������ʾ���ڵĿؼ����ɼ�
	 */
	public void setDateGone(){
		rl_date.setVisibility(View.GONE);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);// ���������
		switch (v.getId()) {
		case R.id.rl_alartcycle_confirm:
			AlartTools.Cycle.Fre_model = AlartTools.Cycle.current_Fre_model;
			AlartTools.alarmEntity.setFre_mode(Double
					.parseDouble(AlartTools.Cycle.current_Fre_model + ""));
			AlartTools.Cycle.cycleValue = AlartTools.Cycle
					.binarayInt2int(AlartTools.Cycle.week_check);
			AlartTools.alarmEntity.setFrequency(Double
					.parseDouble(AlartTools.Cycle.cycleValue + ""));

			if ((int) AlartTools.alarmEntity.getFre_mode() == AlartTools.Cycle.Free_model.FRE_MODEL_ONCE) {
				String date = Tools.date;
				tv_cycle.setText("����һ��");
				setDateVisiable();
			} else {
				setDateGone();
				tv_cycle.setText(AlartTools.Cycle.getWeekCycleByIntArray(
						AlartTools.Cycle.current_Fre_model,
						AlartTools.Cycle.week_check));
			}
			dismissPopwindow();
			break;
	
		case R.id.rl_alartcycle_cancle:
			dismissPopwindow();
			break;
		}
	}
}
