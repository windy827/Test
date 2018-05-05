package com.tts168.autoset.activity;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.quickset.AddDeviceActivity;
import com.tts168.autoset.activity.quickset.CheckLightActivity;
import com.tts168.autoset.activity.quickset.WifiSetActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.view.title.TitleView;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * δɨ�浽�豸����
 * 
 * @author Ԭ��
 * 
 */
public class NotConnectedActivity extends MyBaseActivity implements
		OnClickListener {
	TitleView titleView;// ���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE = "��������";

	public static final String ActivityName = "NotConnectedActivity";

	Button bt_research;// ����ɨ��
	Button bt_wifiset;// ����WIFI����

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
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		//NotifyDialog.ExitAppDialog(instatance);
		/**
		 * �൱�ڰ�Home��
		 */
		Intent i= new Intent(Intent.ACTION_MAIN); 
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		i.addCategory(Intent.CATEGORY_HOME); 
		startActivity(i);  
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

		titleView = new TitleView(this);
		titleView.setTitle(TITLE);

		bt_research = (Button) findViewById(R.id.bt_activity_notconnected_research1);
		bt_wifiset = (Button) findViewById(R.id.bt_activity_notconnected_wifiset1);
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_research.setOnClickListener(this);
		bt_wifiset.setOnClickListener(this);
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
		return R.layout.activity_notconnected;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)) {
			if (PreventForceClick.isShowToast) {
				ToastTools.short_Toast((Activity)MyApplication.getInstance().getCur_Activity(), Tools.CLICK_FAILD);
				PreventForceClick.isShowToast = false;
			}

		} else {
			PreventForceClick.isShowToast = true;

			switch (v.getId()) {
			case R.id.bt_activity_notconnected_research1:
				// ��������ɨ���豸����
				ActivitySetting.startActivity(this,
						SearchDevicesActivity.class, null,true);
				break;
			case R.id.bt_activity_notconnected_wifiset1:
				// ����豸����
				ActivitySetting.startUnfinishedActivity(this,
						CheckLightActivity.class, null,false);
				break;
			}
		}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		// �������
		ToastTools.short_Toast((Activity)MyApplication.getInstance().getCur_Activity(), "����ɹ�!!");
	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
