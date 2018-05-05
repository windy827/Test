package com.tts168.autoset.activity.highset.wakeset;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.autoset.jni.general.GeneralJsonOption;
import com.autoset.jni.play.PlayEntity;
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
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.SharedPrefenceSetTools;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.highcset.individuation.IndividuationTools;
import com.tts168.autoset.tools.highcset.wakeset.WakeSetTools;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.tcpAndudp.WifiWatchTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.WiperSwitch;
import com.tts168.autoset.view.title.TitleView;

public class WakeSetActivity extends MyBaseActivity implements OnClickListener,
		OnCheckedChangeListener {
	TitleView titleView;// 标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE = "唤醒名称";

	public static final String ActivityName = "WakeSetActivity";

	WakeUpEntity wakeUpEntity;
	public  WaitView waitView;// 等待数据
	CommentSet commentSet;

	public static boolean isPrompt_save=false; //是否是唤醒提示音保存
	/**
	 * 通用设置
	 * 
	 * @author Administrator
	 * 
	 */
	public class CommentSet {
		public WiperSwitch sw_awakeset_prompt;// 唤醒提示音控制开关
		// 唤醒名设置
		public RadioGroup rg_name;
		public RadioButton rb_name1;
		public RadioButton rb_name2;
		public RadioButton rb_name3;
		public RadioButton rb_name4;
		public RadioButton rb_name5;
		public RadioButton rb_name6;
		public RadioButton[] rb_names;

		// 试听唤醒名
		public ImageView iv_sound1;
		public ImageView iv_sound2;
		public ImageView iv_sound3;
		public ImageView iv_sound4;
		public ImageView iv_sound5;
		public ImageView iv_sound6;
		public ImageView iv_sound[];
	}

	ScrollView svResult;// 滚动视图
	Button bt_save;// 保存按钮

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
		
		isPrompt_save=false;
		// 接受实体类
		Intent intent = getIntent();
		wakeUpEntity = (WakeUpEntity) intent
				.getSerializableExtra(WakeSetTools.KEYS_ENTITY);
		waitView = new WaitView(this);
		commentSet = new CommentSet();

		titleView = new TitleView(this);
		titleView.setTitle(TITLE);

		commentSet.sw_awakeset_prompt = (WiperSwitch) this
				.findViewById(R.id.switch_activity_undisturbed_prompt);
		commentSet.rg_name = (RadioGroup) findViewById(R.id.rg_activity_awake_awakename);
		commentSet.rb_name1 = (RadioButton) findViewById(R.id.rb_activity_wakeset_name1);
		commentSet.rb_name2 = (RadioButton) findViewById(R.id.rb_activity_wakeset_name2);
		commentSet.rb_name3 = (RadioButton) findViewById(R.id.rb_activity_wakeset_name3);
		commentSet.rb_name4 = (RadioButton) findViewById(R.id.rb_activity_wakeset_name4);
		commentSet.rb_name5 = (RadioButton) findViewById(R.id.rb_activity_wakeset_name5);
		commentSet.rb_name6 = (RadioButton) findViewById(R.id.rb_activity_wakeset_name6);
		commentSet.rb_names = new RadioButton[] { commentSet.rb_name1,
				commentSet.rb_name2, commentSet.rb_name3, commentSet.rb_name4,
				commentSet.rb_name5, commentSet.rb_name6 };

		commentSet.iv_sound1 = (ImageView) findViewById(R.id.iv_activity_wakeup_soundhiyunbao1);
		commentSet.iv_sound2 = (ImageView) findViewById(R.id.iv_activity_wakeup_soundhixiaobo1);
		commentSet.iv_sound3 = (ImageView) findViewById(R.id.iv_activity_wakeup_soundyunbao1);
		commentSet.iv_sound4 = (ImageView) findViewById(R.id.iv_activity_wakeup_soundxiaobo1);
		commentSet.iv_sound5 = (ImageView) findViewById(R.id.iv_activity_wakeup_soundhibailing);
		commentSet.iv_sound6 = (ImageView) findViewById(R.id.iv_activity_wakeup_soundbailing);
		commentSet.iv_sound = new ImageView[] { commentSet.iv_sound1,
				commentSet.iv_sound2, commentSet.iv_sound3,
				commentSet.iv_sound4, commentSet.iv_sound5,
				commentSet.iv_sound6 };

		bt_save = (Button) findViewById(R.id.bt_model_save_save);

		svResult = (ScrollView) findViewById(R.id.sv_activity_wakeset);

		if (wakeUpEntity != null) {
			String name = wakeUpEntity.getWeak_name();
			
			
			// 唤醒名称
			for (int i = 0; i < WakeSetTools.AWAKE_NAMES.length; i++) {
				if (WakeSetTools.AWAKE_NAMES[i].equals(name)) {
					commentSet.rb_names[i].setChecked(true);
				}
			}

		}

	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub

		commentSet.rg_name.setOnCheckedChangeListener(this);

		bt_save.setOnClickListener(this);

		for (int i = 0; i < commentSet.iv_sound.length; i++) {
			commentSet.iv_sound[i].setOnClickListener(this);
		}
		

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
		return R.layout.activity_wakeset;
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		// 保存操作
		ToastTools.short_Toast(this, Tools.OPTION_SAVING);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.SHORT_TIME);//防暴力点击
			switch (v.getId()) {
			// 试听唤醒名
			case R.id.iv_activity_wakeup_soundhiyunbao1:
				// hi,云宝
				IndividuationTools.playSound(Tools.Current_SocketIP,
							RemindVoiceTools.AwakeRemindTools.NAME1_WAKEUP);

				break;
			case R.id.iv_activity_wakeup_soundhixiaobo1:
				// hi,小播
					IndividuationTools.playSound(Tools.Current_SocketIP,
							RemindVoiceTools.AwakeRemindTools.NAME2_WAKEUP);

				break;
			case R.id.iv_activity_wakeup_soundyunbao1:
				// 云宝
					IndividuationTools.playSound(Tools.Current_SocketIP,
							RemindVoiceTools.AwakeRemindTools.NAME3_WAKEUP);

				break;
			case R.id.iv_activity_wakeup_soundxiaobo1:
				// hi,小播
					IndividuationTools.playSound(Tools.Current_SocketIP,
							RemindVoiceTools.AwakeRemindTools.NAME4_WAKEUP);

				break;

			case R.id.iv_activity_wakeup_soundhibailing:
				// hi,百灵
					IndividuationTools.playSound(Tools.Current_SocketIP,
							RemindVoiceTools.AwakeRemindTools.NAME8_WAKEUP);

				break;
			case R.id.iv_activity_wakeup_soundbailing:
				// 百灵
					IndividuationTools.playSound(Tools.Current_SocketIP,
							RemindVoiceTools.AwakeRemindTools.NAME7_WAKEUP);

				break;
			// _________________________________________________________________

			case R.id.bt_model_save_save:
				// 保存操作
				waitView.setPBVisable();
				isPrompt_save=false;
				String content = WakeUpOption.setWeakUp(wakeUpEntity,
						JsonParseOption.SET_USERDATA,
						PlayEntity.TYPE_PALYSOUND,
						RemindVoiceTools.AwakeRemindTools.FAILED_UPDATE_WAKEUP
								+ "",
						RemindVoiceTools.AwakeRemindTools.SUCESS_UPDATE_WAKEUP
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
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		int index = 0;
		PreventViolence.preventClick(group, PreventViolence.SHORT_TIME);//防暴力点击

			switch (group.getId()) {

			case R.id.rg_activity_awake_awakename:
				switch (checkedId) {
				case R.id.rb_activity_wakeset_name1:
					// hi云宝
					index = 0;
					break;
				case R.id.rb_activity_wakeset_name2:
					// Hi小播
					index = 1;
					break;
				case R.id.rb_activity_wakeset_name3:
					// 云宝
					index = 2;
					break;
				case R.id.rb_activity_wakeset_name4:
					// 小播
					index = 3;
					break;
				case R.id.rb_activity_wakeset_name5:
					// 云宝
					index = 4;
					break;
				case R.id.rb_activity_wakeset_name6:
					// 小播
					index = 5;
					break;
				}
				wakeUpEntity.setWeak_name(WakeSetTools.AWAKE_NAMES[index]);
				break;

			}
		
	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub

	}

}
