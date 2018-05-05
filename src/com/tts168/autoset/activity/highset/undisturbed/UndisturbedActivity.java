package com.tts168.autoset.activity.highset.undisturbed;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.undisturbed.UndisturbedEntity;
import com.autoset.jni.undisturbed.UndisturbedOptions;
import com.autoset.jni.wakeup.WakeUpEntity;
import com.autoset.jni.wakeup.WakeUpOption;
import com.autoset.json.JsonParseOption;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.SendDataTools;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.SharedPrefenceSetTools;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.highcset.wakeset.WakeSetTools;
import com.tts168.autoset.tools.highcset.undisturbed.UndisturbedTools;
import com.tts168.autoset.tools.notifydialog.NotifyDialogTools;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.wheelview.WheelViewTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.WiperSwitch;
import com.tts168.autoset.view.title.TitleView;

/**
 * 免扰控制
 * 
 * @author 袁剑
 * 
 */
public class UndisturbedActivity extends MyBaseActivity implements
		OnClickListener {
	TitleView titleView;// 标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE = "免扰控制";

	public static final String ActivityName = "UndisturbedActivity";
	Button bt_save;// 保存
	 HighSet highset;
	 UndisturbedEntity undisturbedEntity;
	public   WaitView waitView;// 等待数据
	public static boolean isUndisturbedOpenSave=false; //是否是唤醒提示音保存

	/**
	 * 夜间设置
	 * 
	 * @author Administrator
	 * 
	 */
	public class HighSet {

		public LinearLayout ll_highsetcontent;
		public WiperSwitch sw_remindvoice;// 唤醒提示音开关
		public WiperSwitch sw_remindvoice_A_O;// 拒识提示音开关
		public RelativeLayout rl_A_O,rl_A_O_remind;//拒识提示模块
		// 夜间模式设置
		public WiperSwitch sw_Undisturbedmodel;// 夜间模式开关

		public TextView tv_start;
		public TextView tv_end;
		public Button bt_defined_starttime;// 自定义唤醒开始时间
		public Button bt_defined_endtime;// 自定义唤醒结束时间
		public Button bt_defined_vol;// 自定义夜间音量
	}

	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}

	@Override
	public void rightToLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if (!Tools.CurrentActivityName.equals(getActivityName())) {

			// MyApplication.asyncBitmapLoader.initExecutorService();[用到异步加载的地方需要使用]

			refresh();
		}
	}

	/**
	 * 提供给外部的刷新接口
	 */
	public static void refresh() {

		
	}

	@Override
	public void staticFindView() {
		
		isUndisturbedOpenSave=false; 
		// 接受实体类
		Intent intent = getIntent();
		undisturbedEntity = (UndisturbedEntity) intent
				.getSerializableExtra(UndisturbedTools.KEYS_ENTITY);
		waitView = new WaitView(this);
		highset = new HighSet();
		highset.sw_Undisturbedmodel = (WiperSwitch) this
				.findViewById(R.id.switch_activity_awakeset_undisturbedmodel);
		highset.ll_highsetcontent = (LinearLayout) findViewById(R.id.ll_acticity_wakeset_highsetcontent);
		highset.sw_remindvoice = (WiperSwitch) this
				.findViewById(R.id.switch_activity_undisturbed_prompt);
		highset.sw_remindvoice_A_O = (WiperSwitch) this
				.findViewById(R.id.switch_activity_undisturbed_prompt_a_o);
		highset.rl_A_O=(RelativeLayout) this
				.findViewById(R.id.rl_undistubed_a_o);
		highset.rl_A_O_remind=(RelativeLayout) this
				.findViewById(R.id.rl_undistubed_alarm_a_o);
		highset.bt_defined_starttime = (Button) this
				.findViewById(R.id.bt_starttime_set1_model2);
		highset.bt_defined_endtime = (Button) this
				.findViewById(R.id.bt_endtime_set1_model2);
		highset.bt_defined_vol = (Button) this
				.findViewById(R.id.bt_activity_undisturbedcontrol_vol);
		highset.tv_start = (TextView) findViewById(R.id.tv_acticity_wakeset_begin);
		highset.tv_end = (TextView) findViewById(R.id.tv_activity_wakeset_end);
	
		titleView = new TitleView(this);
		titleView.setTitle(TITLE);

		bt_save = (Button) findViewById(R.id.bt_model_save_save);

		if (undisturbedEntity != null) {
			double openprompt=undisturbedEntity.getOpen_prompt();
			if(openprompt==undisturbedEntity.open_prompt_YES){
				highset.sw_remindvoice.setChecked(true);
				highset.sw_remindvoice_A_O.setEnabled(true);
				highset.rl_A_O.setVisibility(View.VISIBLE);
				highset.rl_A_O_remind.setVisibility(View.VISIBLE);
			}else{
				highset.sw_remindvoice.setChecked(false);
				highset.sw_remindvoice_A_O.setEnabled(false);
				highset.rl_A_O.setVisibility(View.GONE);
				highset.rl_A_O_remind.setVisibility(View.GONE);
			}
			double openprompt_a_O=undisturbedEntity.getOpen_undisturbed_a_o();
			if(openprompt_a_O==undisturbedEntity.open_prompt_a_o_YES){
				
				highset.sw_remindvoice_A_O.setChecked(true);
			}else{
				highset.sw_remindvoice_A_O.setChecked(false);
			}
			// //夜间模式开关
			if (undisturbedEntity.getOpen_undisturbed() == undisturbedEntity.open_undisturbed_YES) {
				setViewEnable(true);
			} else {
				setViewEnable(false);
			}

			String starttime = undisturbedEntity.getUndisturbed_time_start();
			String endtime = undisturbedEntity.getUndisturbed_time_end();
			highset.bt_defined_starttime.setText(starttime.subSequence(0, 5));
			highset.bt_defined_endtime.setText(endtime.subSequence(0, 5));
		}
		String vol= Tools.getStringValuesByIntKey(undisturbedEntity.getUndisturbed_initVolume(), UndisturbedEntity.volumeValuesInteger, UndisturbedEntity.volumeValuesString);
		highset.bt_defined_vol.setText(vol);
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_save.setOnClickListener(this);

		highset.bt_defined_starttime.setOnClickListener(this);
		highset.bt_defined_endtime.setOnClickListener(this);
		highset.bt_defined_vol.setOnClickListener(this);
		// 唤醒提示音Switch按钮的监听
		highset.sw_remindvoice
						.setOnChangedListener(new com.tts168.autoset.view.WiperSwitch.OnChangedListener() {

							@Override
							public void OnChanged(boolean checkState) {
								// TODO Auto-generated method stub
								// 打开整点报时
								SendDataTools.SWICH_FULL_READTIME = checkState;
								if (checkState) {
									// wakeUpEntity.setOpen_prompt(WakeUpEntity.open_prompt_YES);
									undisturbedEntity
											.setOpen_prompt(UndisturbedEntity.open_prompt_YES);
									highset.sw_remindvoice_A_O.setEnabled(true);
									highset.rl_A_O.setVisibility(View.VISIBLE);
									highset.rl_A_O_remind.setVisibility(View.VISIBLE);
								} else {
									// wakeUpEntity.setOpen_prompt(WakeUpEntity.open_prompt_NO);
									undisturbedEntity
											.setOpen_prompt(UndisturbedEntity.open_prompt_NO);
									highset.sw_remindvoice_A_O.setEnabled(false);
									highset.rl_A_O.setVisibility(View.GONE);
									highset.rl_A_O_remind.setVisibility(View.GONE);
								}

							}
						});
		
		// 拒识提示音Switch按钮的监听
		highset.sw_remindvoice_A_O
						.setOnChangedListener(new com.tts168.autoset.view.WiperSwitch.OnChangedListener() {

							@Override
							public void OnChanged(boolean checkState) {
								// TODO Auto-generated method stub
								// 打开整点报时
								SendDataTools.SWICH_FULL_READTIME = checkState;
								if (checkState) {
									// wakeUpEntity.setOpen_prompt(WakeUpEntity.open_prompt_YES);
									undisturbedEntity
											.setOpen_undisturbed_a_o(UndisturbedEntity.open_prompt_a_o_YES);
								} else {
									// wakeUpEntity.setOpen_prompt(WakeUpEntity.open_prompt_NO);
									undisturbedEntity
									.setOpen_undisturbed_a_o(UndisturbedEntity.open_prompt_a_o_NO);
								}

							}
						});
		// 夜间模式Switch按钮的监听
		highset.sw_Undisturbedmodel
				.setOnChangedListener(new com.tts168.autoset.view.WiperSwitch.OnChangedListener() {

					@Override
					public void OnChanged(boolean checkState) {
						// TODO Auto-generated method stub
						SendDataTools.SWICH_HALF_READTIME = checkState;
						if (checkState) {
							undisturbedEntity
									.setOpen_undisturbed(undisturbedEntity.open_undisturbed_YES);
							setViewEnable(true);
							// Toast.makeText(Tools.c, "打开唤醒提示音", 500).show();
						} else {
							undisturbedEntity
									.setOpen_undisturbed(undisturbedEntity.open_undisturbed_NO);
							setViewEnable(false);
							// Toast.makeText(Tools.c, "关闭唤醒提示音", 500).show();
						}

					}
				});
	}

	
	public void setViewEnable(boolean canUse){
		highset.sw_Undisturbedmodel.setChecked(canUse);
		highset.bt_defined_starttime.setEnabled(canUse);
		highset.bt_defined_endtime.setEnabled(canUse);
		highset.bt_defined_vol.setEnabled(canUse);
	}
	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getContentViewID() {
		// TODO Auto-generated method stub
		return R.layout.activity_undisturbed;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);// 防暴力点击
		switch (v.getId()) {

		// 时间段设置
		case R.id.bt_starttime_set1_model2:
			NotifyDialog.ChooseTIMEDialog(MyApplication.getInstance().getCur_Activity(), Tools.FLAG_TIME, false,
					true, highset.bt_defined_starttime.getText().toString(),
					false, SharedPrefenceSetTools.STARTTIME, (Button) v,
					Tools.TYPE_SET_FESTIVE,WheelViewTools.NIGHTCONTROL_HOUR_START_start,WheelViewTools.NIGHTCONTROL_HOUR_START_end);
			break;
		case R.id.bt_endtime_set1_model2:
			NotifyDialog.ChooseTIMEDialog(MyApplication.getInstance().getCur_Activity(), Tools.FLAG_TIME, false,
					true, highset.bt_defined_endtime.getText().toString(),
					false, SharedPrefenceSetTools.ENDTIME, (Button) v,
					Tools.TYPE_SET_FESTIVE,WheelViewTools.NIGHTCONTROL_HOUR_END_START,WheelViewTools.NIGHTCONTROL_HOUR_END_end);
			break;
		case R.id.bt_activity_undisturbedcontrol_vol:
			// 选择音量
			NotifyDialog.ChooseListStringBTDialog(this, NotifyDialogTools.TITLE_CHOOSEVOL, (Button) v, UndisturbedEntity.volumeValuesString);
			break;
		case R.id.bt_model_save_save:
			// 保存操作
			isUndisturbedOpenSave=false;
			waitView.setPBVisable();
			String starttime = (String) highset.bt_defined_starttime.getText()
					+ AlartTools.TimeTools.TIME_TAIL;
			String endtime = (String) highset.bt_defined_endtime.getText()
					+ AlartTools.TimeTools.TIME_TAIL;
			undisturbedEntity.setUndisturbed_time_start(starttime);
			undisturbedEntity.setUndisturbed_time_end(endtime);
			int vol= Tools.getIntValuesByStringKey(highset.bt_defined_vol.getText().toString(), UndisturbedEntity.volumeValuesString, UndisturbedEntity.volumeValuesInteger);
			undisturbedEntity.setUndisturbed_initVolume(vol);
			
			String content =UndisturbedOptions
					.setUndisturbed(
							undisturbedEntity,
							JsonParseOption.SET_USERDATA,
							PlayEntity.TYPE_PALYSOUND,
							RemindVoiceTools.CommentRemindTools.FAILED_UPDATE
									+ "",
									RemindVoiceTools.CommentRemindTools.SUCESS_UPDATE
									+ "");

			ArrayList<String> contents = new ArrayList<String>();
			contents.add(content);
			TCPTools.sendTcp(contents, Tools.Current_SocketIP, true);
			ToastTools.short_Toast(this, "保存中...");
			// this.finish();
			break;
		}

	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		// 保存操作

	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub

	}

}
