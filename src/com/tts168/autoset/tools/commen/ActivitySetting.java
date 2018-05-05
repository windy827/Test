package com.tts168.autoset.tools.commen;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.player.PlayingActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.update.UpdateTools;
import com.tts168.autoset.view.player.PlayingContentView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySetting {

	/**
	 * 去除标题栏
	 * 
	 * @param mActivity
	 */
	public static void requestNotTitleBar(final Activity mActivity) {
		mActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/**
	 * 全屏
	 * 
	 * @param mActivity
	 */
	public static void requestFullscreen(final Activity mActivity) {
		final Window window = mActivity.getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		window.requestFeature(Window.FEATURE_NO_TITLE);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static boolean HasreturnActivity = true;

	/**
	 * Toast 提醒
	 * 
	 * @param c
	 * @param info
	 *            提醒的内容
	 */
	public static void showToast(Context c, final String info) {
		Toast.makeText(c, info, Toast.LENGTH_SHORT).show();

	}

	// 开启一个新的activity
	public static void startActivity(Context ctx, Class<?> a, Bundle bundle,
			boolean isFinishPreActivitys) {
		if (UpdateTools.needUpdate()) {
			//需要弹出提示对话框的不给跳转
//			NotifyDialog.updadateRemindDialog(MyApplication.getInstance()
//					.getCur_Activity(), Tools.entity_Upgrade);
		} else {
			Intent intent = null;
			if (ctx == null || a == null) {
				return;
			} else {
				if (a != null)
					intent = new Intent(ctx, a);
				if (bundle != null && intent != null)
					intent.putExtras(bundle);
				if (intent != null) {
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					Log.d("ACTIVITYNULL", ctx + "***" + intent);
					try {
						if (isFinishPreActivitys) {
							MyActivityManager.getScreenManager()
									.popAllActivityExceptOne();
						}
						ctx.startActivity(intent);

						if (ctx instanceof Activity) {
							Activity oldactivity = (Activity) ctx;
							if (Tools.CurrentActivityName
									.equals(PlayingActivity.ActivityName)) {
								oldactivity.overridePendingTransition(
										R.anim.slide_in_from_bottom,
										R.anim.slide_out_to_bottom);
							} else {
								oldactivity.overridePendingTransition(
										R.anim.fade_in, R.anim.fade_out);
							}

							oldactivity.finish();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					// overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
					// 添加界面切换效果，注意只有Android的2.0(SdkVersion版本号为5)以后的版本才支持
					int version = Integer.valueOf(android.os.Build.VERSION.SDK);

				}

			}
		}

	}

	/**
	 * 开启一个新的Activity，原Activity不销毁
	 * 
	 * @param oldactivity
	 * @param a
	 * @param bundle
	 */
	public static void startUnfinishedActivity(Activity oldactivity,
			Class<?> a, Bundle bundle, boolean isFinishPreActivitys) {
		if (UpdateTools.needUpdate()) {
			//需要弹出提示对话框的不给跳转
//			NotifyDialog.updadateRemindDialog(MyApplication.getInstance()
//					.getCur_Activity(), Tools.entity_Upgrade);
		} else {
			Intent intent = null;
			if (a != null)
				intent = new Intent(oldactivity, a);
			if (bundle != null && intent != null)
				intent.putExtras(bundle);
			if (isFinishPreActivitys) {
				MyActivityManager.getScreenManager().popAllActivityExceptOne();
			}
			oldactivity.startActivity(intent);
			if (Tools.CurrentActivityName.equals(PlayingActivity.ActivityName)) {
				oldactivity
						.overridePendingTransition(R.anim.slide_in_from_bottom,
								R.anim.slide_out_to_bottom);
			} else {
				oldactivity.overridePendingTransition(R.anim.fade_in,
						R.anim.fade_out);
			}
			// 添加界面切换效果，注意只有Android的2.0(SdkVersion版本号为5)以后的版本才支持
			int version = Integer.valueOf(android.os.Build.VERSION.SDK);
			
		}

	}

	// 显示对话框
	public static void ShowDialog(final Activity activity, String title,
			String context) {

		Dialog alertDialog = new AlertDialog.Builder(activity).setIcon(null)
				.setTitle(title).setMessage(context)
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;

					}
				})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						return;

					}
				}).create();

		alertDialog.show();

	}

	// 显示对话框再跳转到另一个Activity
	public static void ShowDialogAndActivity(final Activity oldactivity,
			final Class<?> a, String title, String context) {

		new AlertDialog.Builder(oldactivity).setIcon(null).setTitle(title)
				.setMessage(context)
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						ActivitySetting.startActivity(oldactivity, a, null,
								false);
					}
				}).create().show();

	}

	// 退出程序
	public static void exitApp(final Activity activity) {
		Dialog alertDialog = new AlertDialog.Builder(activity)
				.setTitle("系统提示：").setMessage("您确定要退出吗？")
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						return;
					}
				})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent startMain = new Intent(Intent.ACTION_MAIN);
						startMain.addCategory(Intent.CATEGORY_HOME);
						startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						activity.startActivity(startMain);
						exitApp(activity);
						System.exit(0);// 退出程序

					}
				}).create();

		alertDialog.show();
	}

}
