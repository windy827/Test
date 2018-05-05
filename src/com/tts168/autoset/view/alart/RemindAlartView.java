package com.tts168.autoset.view.alart;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amo.demo.wheelview.WheelViewUse;
import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.remind.RemindEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity;
import com.tts168.autoset.activity.alart.AwakeAlartEditActivity;
import com.tts168.autoset.activity.alart.RemindAlartActivity;
import com.tts168.autoset.activity.alart.addalart.AlartFragmentTabActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.alart.MyAlartJsonOptions;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.view.EditTextAndTextView;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.view.MyBaseView;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.alart.BirthdayAlartView.EditTimeLayoutView;

/**
 * Alarm的核心部分界面空间的设置
 * 
 * @author 袁剑
 * 
 */
public class RemindAlartView extends MyBaseActivityView {

	public static final int MAX_CONTENTTEXTTOTAL = 40;// 可以输入的建议的文字的个数
	EditTextAndTextView editTextAndTextView;// 有字数限制的编辑框
	// 最低端的按钮
	public Button bt_save;

	/**
	 * 当前界面是否为添加界面
	 */
	boolean isadd = true;
	/**
	 * 当前的实体类是AlartTools.alartAdapter_content里面的第几个索引，方便更新
	 */
	int index;
	private RelativeLayout rl_cycle;// 周期
	private TextView tv_cycle;

	View view;
	AlartTitleView alartTitleView;// 标题界面
	Context context;
	public  WaitView waitView;// 等待数据
	public RemindAlartView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置是否为添加
	 * 
	 * @param isAdd
	 * @param ae没有
	 *            【即为添加状态】可以填null
	 * @param index没有
	 *            【即为添加状态】可以填0
	 */
	public void setIsAdd(boolean isAdd, RemindEntity ae, int index) {
		isadd = isAdd;
		View timeView = null;
		if (isadd) {
			Tools.time=GetAndSetTime.getData();
			AlartCommenSetTools.ID = AlartTools.getAlarmGeneralID(
					AlartTools.alartAdapter_content,
					AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
		
			view = activity.getLayoutInflater().inflate(
					R.layout.model_activity_remindalart, null, false);
			waitView = new WaitView(view);
			timeView=view.findViewById(R.id.ll_timelayout);
			bt_save = (Button) view.findViewById(R.id.bt_model_save_save);
			editTextAndTextView = new EditTextAndTextView(view);
			editTextAndTextView.setMaxLength(MAX_CONTENTTEXTTOTAL);// 设置允许输入的最大字数
			editTextAndTextView.setEditTextHinit("在此处输入备忘的内容（最多"
					+ MAX_CONTENTTEXTTOTAL + "个字！）");
			EditTimeLayoutView.isLunar = false;// 默认为不选中
			EditTimeLayoutView.useMyDate = false;// 默认不使用
			// 如果是编辑就显示标题栏，如果是添加就隐藏编辑栏
			AlartTools.Cycle.Fre_model = AlartTools.Cycle.current_Fre_model;
			AlartTools.Cycle.cycleValue = AlartTools.Cycle.current_cycleValue;
		} else {
			AlartCommenSetTools.ID = ae.getId();
			editTextAndTextView = new EditTextAndTextView(activity);
			waitView = new WaitView(activity);
			bt_save = (Button) activity.findViewById(R.id.bt_model_save_save);
			editTextAndTextView.setMaxLength(MAX_CONTENTTEXTTOTAL);// 设置允许输入的最大字数
			editTextAndTextView.setEditTextHinit("在此处输入备忘的内容（最多"
					+ MAX_CONTENTTEXTTOTAL + "个字！）");
			timeView=activity.findViewById(R.id.ll_timelayout);
			editTextAndTextView.et_suggest_content.setText(ae.getContent());
			EditTimeLayoutView.isLunar = false;
			EditTimeLayoutView.time = AlartTools.BirthDay.convertToMydata(ae.getDate());
			EditTimeLayoutView.useMyDate = true;

		}

		bt_save.setOnClickListener(this);
		EditTimeLayoutView.flag = Tools.FLAG_DATE;
		WheelViewUse.getDateView(timeView, EditTimeLayoutView.flag, false,
				EditTimeLayoutView.useMyDate, EditTimeLayoutView.time,
				EditTimeLayoutView.isLunar);
	}

	/**
	 * 得到View，使用此方法前请先调用setIsAdd方法进行初始化
	 * 
	 * @return
	 */
	public View getView() {
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)) {
			if (PreventForceClick.isShowToast) {
				ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), Tools.CLICK_FAILD);
				PreventForceClick.isShowToast = false;
			}

		} else {
			PreventForceClick.isShowToast = true;
			switch (v.getId()) {

			case R.id.bt_model_save_save:

				titleSave();

				break;
			}
		}
	}

	public void titleSave() {
		// TODO Auto-generated method stub
		// 保存操作
		// 保存按钮
		String longTime=Tools.time.replace("-", "");
		if(Long.parseLong(GetAndSetTime.getData().replace("-", ""))>Long.parseLong(longTime)){
			NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), "设置的时间已过期，请检查后再保存！");
			
		}else{
			if (editTextAndTextView.et_suggest_content.getText().length() > 0) {
				
				if (isadd) {
					// 如果是添加
					((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
//					if ((int) AlartCommenSetTools.ID > AlartTools.MaxID_Alart.MAXID_REMIND) {
//						// 不能添加
//						ToastTools.short_Toast(activity, "添加的闹铃数已满!!");
//					} else {
						RemindEntity re = new RemindEntity(
								(int) AlartCommenSetTools.ID,
								AlarmEntity.ISVALID_YES,
								Tools.time,
								editTextAndTextView.et_suggest_content.getText()
										.toString());
						HashMap<String, Object> temp = new HashMap<String, Object>();
						temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE,
								AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
						temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
								re);
						AlartTools.alartAdapter_content.add(temp);
						MyAlartJsonOptions
								.setAlartData(
										AlartTools.alartAdapter_content,
										new int[] { AlartTools.alartAdapter_content
												.size() - 1 },
										AlartTools.AlartType.JSON_ALART_TYPE_REMIND,
										Tools.STATUS_UPDATE_FAILED, "添加备忘闹铃成功",
										AlartTools.AlartType.OPTIONS_INSERT);
					//}

				} else {
					// 编辑
					// 如果是修改
					((RemindAlartActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
					RemindEntity re = new RemindEntity(
							(int) AlartCommenSetTools.ID,
							AlartTools.remindEntity.getIs_valid(),
							Tools.time,
							editTextAndTextView.et_suggest_content.getText()
									.toString());
					HashMap<String, Object> temp = new HashMap<String, Object>();
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE,
							AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY, re);
					AlartTools.alartAdapter_content.set(index, temp);
					MyAlartJsonOptions.setAlartData(
							AlartTools.alartAdapter_content, new int[] { index },
							AlartTools.AlartType.JSON_ALART_TYPE_REMIND,
							Tools.STATUS_UPDATE_FAILED, "修改备忘闹铃成功",
							AlartTools.AlartType.OPTIONS_UPDATE);
				}
				ToastTools.short_Toast(activity, Tools.OPTION_SAVING);
			} else {
				NotifyDialog.SimpleAlertDialog(activity,
						"备忘闹铃的标题不能为空，请先输入自定义标题再保存！");
			}
			}
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub

	}

}
