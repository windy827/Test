package com.tts168.autoset.activity.quickset;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.quickset.WifiSetRemindTools;
import com.tts168.autoset.view.title.TitleView;

/**
 * 配置等待界面
 * 
 * @author 袁剑
 * 
 */
public class WifiSetWaitingActivity extends MyBaseActivity implements
		OnClickListener {
	TitleView titleView;// 标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE = "网络配置结果";

	public static final String ActivityName = "WifiSetWaitingActivity";
	Button bt_wificonnected;// 联网成功

	Button bt_setfailed;// 配置失败

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
		super.onBackPressed();
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
		// TODO Auto-generated method stub
		titleView = new TitleView(this);
		titleView.setTitle(TITLE);

		bt_wificonnected = (Button) findViewById(R.id.bt_activity_wifiset_result_connected1);

		bt_setfailed = (Button) findViewById(R.id.bt_activity_wifiset_result_setfailed1);

	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_wificonnected.setOnClickListener(this);

		bt_setfailed.setOnClickListener(this);
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
		return R.layout.activity_wifiset_sending;
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
			case R.id.bt_activity_wifiset_result_connected1:
				// 联网成功【清空数组重新扫描】
				TerminalTools.adapter_infoByIP = new ArrayList<TerminalAdapterEntity>();
				ActivitySetting.startActivity((Activity)MyApplication.getInstance().getCur_Activity(),
						SearchDevicesActivity.class, null,true);
				break;
			case R.id.bt_activity_wifiset_result_setfailed1:
				// 设置失败或联网失败
				ActivitySetting.startActivity((Activity)MyApplication.getInstance().getCur_Activity(),
						WifiSetActivity.class, null,true);
				break;
			}
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
