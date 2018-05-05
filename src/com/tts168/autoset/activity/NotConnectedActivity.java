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
 * 未扫面到设备界面
 * 
 * @author 袁剑
 * 
 */
public class NotConnectedActivity extends MyBaseActivity implements
		OnClickListener {
	TitleView titleView;// 标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE = "连接助手";

	public static final String ActivityName = "NotConnectedActivity";

	Button bt_research;// 重新扫描
	Button bt_wifiset;// 进入WIFI设置

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
		 * 相当于按Home键
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
	 * 提供给外部的刷新接口
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
				// 进入重新扫面设备界面
				ActivitySetting.startActivity(this,
						SearchDevicesActivity.class, null,true);
				break;
			case R.id.bt_activity_notconnected_wifiset1:
				// 添加设备界面
				ActivitySetting.startUnfinishedActivity(this,
						CheckLightActivity.class, null,false);
				break;
			}
		}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		// 保存操作
		ToastTools.short_Toast((Activity)MyApplication.getInstance().getCur_Activity(), "保存成功!!");
	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
