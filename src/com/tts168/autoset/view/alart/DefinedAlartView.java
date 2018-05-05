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
import com.autoset.json.MyTools;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity;
import com.tts168.autoset.activity.alart.AwakeAlartEditActivity;
import com.tts168.autoset.activity.alart.DefinedAlartEditActivity;
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
import com.tts168.autoset.tools.commen.SharedPrefenceSetTools;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.wheelview.WheelViewTools;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.view.MyBaseView;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.alart.BirthdayAlartView.EditTimeLayoutView;
import com.tts168.autoset.view.popwindow.PopupWindowAlartCycle;

/**
 * Alarm的核心部分界面空间的设置
 * 
 * @author 袁剑
 * 
 */
public class DefinedAlartView extends MyBaseActivityView {

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
	private RelativeLayout rl_date;//日期
	private TextView tv_date;

	View view;
	AlartTitleView alartTitleView;// 标题界面
	Context context;
	public static WaitView waitView;// 等待数据
	public DefinedAlartView(Activity activity) {
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
	public void setIsAdd(boolean isAdd, AlarmEntity ae, int index) {
		isadd = isAdd;
		View timeView = null;
		if (isadd) {
			AlartCommenSetTools.ID = AlartTools.getAlarmGeneralID(
					AlartTools.alartAdapter_content,
					AlartTools.AlartType.JSON_ALART_TYPE_ALART);
			AlartCommenSetTools.is_lunar=0.0;
			AlartTools.Cycle.Fre_model = 1;
			AlartTools.Cycle.cycleValue = 127;
			view = activity.getLayoutInflater().inflate(
					R.layout.model_activity_awakealart, null, false);
			waitView = new WaitView(view);
			alartTitleView = new AlartTitleView(view,AlartTitleView.TEXTLEN_MAX20);
			alartTitleView.setEitTextHinit("请输入自定义的标签名");
			AlartTools.alarmEntity = new AlarmEntity(
					(int) AlartCommenSetTools.ID, alartTitleView.et_title
							.getText().toString(), GetAndSetTime.getData(),
					Tools.time + AlartTools.TimeTools.TIME_TAIL,
					AlarmEntity.ISLUNAR_NO, AlartCommenSetTools.isvalid,
					AlartTools.Cycle.Fre_model, AlartTools.Cycle.cycleValue);
			rl_cycle = (RelativeLayout) view.findViewById(R.id.rl_alart_cycle);
			tv_cycle = (TextView) view.findViewById(R.id.tv_alart_cycle);
			rl_date = (RelativeLayout) view.findViewById(R.id.rl_alart_date);
			tv_date = (TextView) view.findViewById(R.id.tv_alart_date);
			bt_save = (Button) view.findViewById(R.id.bt_model_save_save);
			timeView = view.findViewById(R.id.ll_timelayout);

			// 如果是编辑就显示标题栏，如果是添加就隐藏编辑栏
			rl_date.setOnClickListener(this);
			rl_cycle.setOnClickListener(this);
			EditTimeLayoutView.isLunar = false;// 默认为不选中
			EditTimeLayoutView.useMyDate = false;// 默认不使用
			if((int)AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
				tv_cycle.setText("响铃一次");
				setDateVisiable();
				
			}else{
				tv_cycle.setText(AlartTools.Cycle.getWeekCycleByInt(
						(int) AlartTools.alarmEntity.getFre_mode(), AlartTools.Cycle.cycleValue));
			}
		} else {
			AlartTools.alarmEntity = ae;
			alartTitleView = new AlartTitleView(activity,AlartTitleView.TEXTLEN_MAX20);
			waitView = new WaitView(activity);
			alartTitleView.setEitTextContent(AlartTools.alarmEntity.getTitle());
			AlartCommenSetTools.ID = ae.getId();
			rl_cycle = (RelativeLayout) activity
					.findViewById(R.id.rl_alart_cycle);
			tv_cycle = (TextView) activity.findViewById(R.id.tv_alart_cycle);
			rl_date = (RelativeLayout) activity.findViewById(R.id.rl_alart_date);
			tv_date = (TextView) activity.findViewById(R.id.tv_alart_date);
			bt_save = (Button) activity.findViewById(R.id.bt_model_save_save);
			timeView = activity.findViewById(R.id.ll_timelayout);

			EditTimeLayoutView.isLunar = false;// 默认为不选中
			EditTimeLayoutView.useMyDate = false;// 默认不使用

			// 如果是编辑就显示标题栏，如果是添加就隐藏编辑栏
			rl_date.setOnClickListener(this);
			rl_cycle.setOnClickListener(this);
			this.index = index;
			// AlartTools.Cycle.Fre_model=(int) ae.getFre_mode();
			// AlartTools.Cycle.cycleValue=(int) ae.getFrequency();
			EditTimeLayoutView.time = ae.getClock();
			EditTimeLayoutView.useMyDate = true;
			if (ae.getIs_lunar() == AlarmEntity.ISLUNAR_YES) {
				EditTimeLayoutView.isLunar = true;
			} else {
				EditTimeLayoutView.isLunar = false;
			}
			int cycle_Frequenc = (int) ae.getFrequency();
			alartTitleView.setEitTextContent(ae.getTitle());
			AlartTools.Cycle.current_cycleValue = cycle_Frequenc;
			if((int)AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
				tv_cycle.setText("响铃一次");
				setDateVisiable();
			}else{
				tv_cycle.setText(AlartTools.Cycle.getWeekCycleByInt(
						(int) ae.getFre_mode(), cycle_Frequenc));
			}

		}
		
		Tools.date=AlartTools.alarmEntity.getDate();
		bt_save.setOnClickListener(this);
		EditTimeLayoutView.flag = Tools.FLAG_TIME;
		WheelViewUse.getDateView(timeView, EditTimeLayoutView.flag, false,
				EditTimeLayoutView.useMyDate, EditTimeLayoutView.time,
				EditTimeLayoutView.isLunar);
	}
	/**
	 * 设置显示日期的控件可见，并设置日期
	 * @param date
	 */
	public void setDateVisiable(){
		rl_date.setVisibility(View.VISIBLE);
		Tools.date=GetAndSetTime.getData();
		tv_date.setText(Tools.date);
		
	}
	/**
	 * 设置显示日期的控件不可见
	 */
	public void setDateGone(){
		rl_date.setVisibility(View.GONE);
		
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

			case R.id.rl_alart_cycle:
				// 周期设置
				//AlartNotifyDialog.chooseCycleDialog(activity, tv_cycle);
				new PopupWindowAlartCycle(MyApplication.getInstance().getCur_Activity(), tv_cycle,rl_date,tv_date).showPopwindow();
				break;
			case R.id.rl_alart_date:
				// 日期设置
				NotifyDialog.ChooseTIMEDialog_TV(MyApplication.getInstance().getCur_Activity(), Tools.FLAG_DATE,false,true,tv_date.getText().toString(),false,
						SharedPrefenceSetTools.STARTTIME, tv_date,
						Tools.TYPE_SET_FESTIVE,WheelViewTools.COMMENT_HOUR_START,WheelViewTools.COMMENT_HOUR_end);
				
				break;
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
		
		if (alartTitleView.et_title.getText().length() > 0) {
			
			AlartTools.alarmEntity.setDate(Tools.date);
			AlartTools.alarmEntity.setClock(Tools.time
					+ AlartTools.TimeTools.TIME_TAIL);
			AlartTools.alarmEntity.setTitle(alartTitleView.et_title.getText()
					.toString());
			String date="";
	
			if((int) AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
				date=MyTools.getDoubQuot(AlartTools.BirthDay.convertToMydata(Tools.date)+",");
			}
			String longTime=tv_date.getText().toString().replace("-", "");
			if((int)AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE&&
					Long.parseLong(GetAndSetTime.getData().replace("-", ""))>Long.parseLong(longTime)){
				NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), "设置的时间已过期，请检查后再保存！");
				
			}else{
			if (isadd) {
				// 如果是添加
				((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
//				if ((int) AlartCommenSetTools.ID > AlartTools.MaxID_Alart.MAXID_ALARM) {
//					// 不能添加
//					ToastTools.short_Toast(activity, "添加的闹铃数已满!!");
//				} else {
					HashMap<String, Object> temp = new HashMap<String, Object>();
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE,
							AlartTools.AlartType.JSON_ALART_TYPE_ALART);
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
							AlartTools.alarmEntity);
					AlartTools.alartAdapter_content.add(temp);
					
					MyAlartJsonOptions
							.setAlartData(AlartTools.alartAdapter_content,
									new int[] { AlartTools.alartAdapter_content
											.size() - 1 },
									AlartTools.AlartType.JSON_ALART_TYPE_ALART,
									Tools.STATUS_UPDATE_FAILED, "添加成功,已为您设置了"
											+date+ MyTools.getDoubQuot(Tools.time)
											+ "的自定义闹铃",
									AlartTools.AlartType.OPTIONS_INSERT);
				//}

			} else {
				// 编辑
				// 如果是修改
				((DefinedAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
				HashMap<String, Object> temp = new HashMap<String, Object>();
				temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE,
						AlartTools.AlartType.JSON_ALART_TYPE_ALART);
				temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
						AlartTools.alarmEntity);
				AlartTools.alartAdapter_content.set(index, temp);
				MyAlartJsonOptions
						.setAlartData(AlartTools.alartAdapter_content,
								new int[] { index },
								AlartTools.AlartType.JSON_ALART_TYPE_ALART,
								Tools.STATUS_UPDATE_FAILED, "修改成功,已为您设置了"+date
										+ MyTools.getDoubQuot(Tools.time)
										+ "的自定义闹铃",
								AlartTools.AlartType.OPTIONS_UPDATE);
			}
			ToastTools.short_Toast(activity, Tools.OPTION_SAVING);
			}
		} else {
			NotifyDialog.SimpleAlertDialog(activity,
					"自定义闹铃的标题不能为空，请先输入自定义标题再保存！");
		}
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub

	}

	/**
	 * 编辑时间的Layout2
	 * 
	 * @author 袁剑
	 * 
	 */
	public static class EditTimeLayoutView {
		/**
		 * //WheelView标记的是时间还是日期
		 */
		static int flag = 0;
		/**
		 * //是否是阴历
		 */
		static boolean isLunar;
		/**
		 * //是否使用我定义的日期时间
		 */
		static boolean useMyDate;
		/**
		 * //传到WheelView的想要它显示时间日期，有一定的格式要求
		 */
		static String time;

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
