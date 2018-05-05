package com.tts168.autoset.activity.answerhelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.view.title.TitleView;
import com.tts168.autoset.view.webview.MyWebView;

public class HelpOfAnswerHelperActivity extends MyBaseActivity implements
		OnClickListener, OnCheckedChangeListener {
	TitleView titleView;// ���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	MyWebView myWebView;// �����
	CheckBox cb_remind;//�Ƿ��Զ�����
	public static final String TITLE = "����";
	private SharedPreferences shared;
	public static final String ActivityName = "HelpOfAnswerHelperActivity";
	Button bt_enter2helper;// ����ʴ�����

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
	 * �ṩ���ⲿ��ˢ�½ӿ�
	 */
	public static void refresh() {

	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		// ����ʵ����
		Intent intent = getIntent();
		DeviceEntity de = (DeviceEntity) intent
				.getSerializableExtra("deviceinfo");
		shared=new SharedConfig(this).GetConfig(); 	// �õ������ļ�
		titleView = new TitleView(this);
		titleView.setTitle(TITLE);

		myWebView = new MyWebView(this);
		myWebView.loadURL(new SharedConfig(this).GetConfig().getString(SharedConfig.KEY_URL_HELP, SharedConfig.DEFAULT_URL_HELP)+Tools.generalEntity.getProduct_ID()+Tools.URL_HTML_TAIL,this);
		cb_remind=(CheckBox) findViewById(R.id.cb_activity_answerhelp);
		boolean isvisable=shared.getBoolean(SharedConfig.KEY_HELP_ANSWER_VISIABLE, SharedConfig.DEFAULT_HELP_ANSWER_VISIABLE);
		if(isvisable){
			cb_remind.setChecked(false);
		}else{
			cb_remind.setChecked(true);
		}
		bt_enter2helper = (Button) findViewById(R.id.bt_activity_helpofanswerhelper);

	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_enter2helper.setOnClickListener(this);
		cb_remind.setOnCheckedChangeListener(this);
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
		return R.layout.activity_helpofanswerhelper;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)) {
			if (PreventForceClick.isShowToast) {
				ToastTools.short_Toast(this, Tools.CLICK_FAILD);
				PreventForceClick.isShowToast = false;
			}

		} else {
			PreventForceClick.isShowToast = true;
			switch (v.getId()) {
			case R.id.bt_activity_helpofanswerhelper:
				// ToastTools.short_Toast(instatance, "�����ʴ���");
				ActivitySetting.startActivity(this,
						AnswerRecordActivity.class, null,false);
				break;
			}
		}
	}

	// goBack()��ʾ����webView����һҳ��
	@Override
	public boolean onKeyDown(int keyCoder, KeyEvent event) {
		if (myWebView.wv.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK) {
			myWebView.wv.goBack();
			return true;
		} else {
			onBackPressed();
			return false;
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (myWebView.wv.canGoBack()) {
			myWebView.wv.goBack();

		} else {
			super.onBackPressed();

		}

	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			shared.edit().putBoolean(SharedConfig.KEY_HELP_ANSWER_VISIABLE, false).commit();
		}else{
			shared.edit().putBoolean(SharedConfig.KEY_HELP_ANSWER_VISIABLE, true).commit();
		}
	}
	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		// �������
		ToastTools.short_Toast(this, "����ɹ�!!");
	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
