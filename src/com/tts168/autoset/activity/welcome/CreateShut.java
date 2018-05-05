package com.tts168.autoset.activity.welcome;


import com.larkiv.larksmart7618.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

//���������ݼ�
public class CreateShut {
	public CreateShut(Activity activity) {
		// intent进行隐式跳转,到桌面创建快捷方�?
		Intent addIntent = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		//不允许重�?
		addIntent.putExtra("duplicate", false);
		// 得到应用的名�?
		String title = activity.getResources().getString(R.string.app_name);
		// 将应用的图标设置为Parceable类型
		Parcelable icon = Intent.ShortcutIconResource.fromContext(
				activity, R.drawable.ic_launcher);
		// 点击图标之后的意图操�?
		Intent myIntent = new Intent(activity, MainActivity.class);
		// 设置快捷方式的名�?
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		// 设置快捷方式的图�?
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		// 设置快捷方式的意�?
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);
		// 发�?广播
		activity.sendBroadcast(addIntent);
	}
}
