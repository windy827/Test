package com.tts168.autoset.tools.commen;

import java.util.ArrayList;
import java.util.HashMap;

import com.amo.demo.wheelview.DateWheelView;
import com.autoset.jni.http.configAndupgrade.UpgradeEntity;
import com.larkiv.larksmart7618.R;
import com.niftydialogeffects.Effectstype;
import com.niftydialogeffects.NiftyDialogBuilder;
import com.niftydialogeffects.NiftyDialogBuilderChoose;
import com.tools.sortlistview.SortListDialog;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.activity.quickset.WifiSetActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.database.DB_Commeration_Option;
import com.tts168.autoset.database.DB_DailyAlart_Option;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.highcset.cityset.CitySetTools;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.notifydialog.ChooseListDialogView;
import com.tts168.autoset.tools.others.wifi.NetManager;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.tcpAndudp.WifiStateWatchThread;
import com.tts168.autoset.tools.update.DownloadService;
import com.tts168.autoset.tools.update.DownloadService.DownloadBinder;
import com.tts168.autoset.tools.update.UpdateServiceConnection;
import com.tts168.autoset.view.MyWifiView;
import com.tts168.autoset.view.notifydialogView.ChooseDialogView;
import com.tts168.autoset.view.notifydialogView.OtherDialogView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * 有绚丽效果的Dialog，调用了别人的Jar包
 * 
 * @author 袁剑
 * 
 */
public class NotifyDialog {

	public static NiftyDialogBuilder dialogBuilder = null;
	public static NiftyDialogBuilder dialogBuilder_updater = null;
	/**
	 * 软件更新提示对话框
	 * 
	 * @param context
	 * 
	 * @param content
	 *            更新的内容
	 * @param updateURL
	 *            apk下载的URL
	 * @param ver_name
	 *            apk的版本名称
	 */
	public static void updadateRemindDialog(final Context context,
			final UpgradeEntity entity) {
		if(NotifyDialog.dialogBuilder_updater !=null && NotifyDialog.dialogBuilder_updater.isShowing()) {
//			NotifyDialog.dialogBuilder_updater.dismiss();
//			NotifyDialog.dialogBuilder_updater=null;
		}
		else{
			dialogBuilder_updater = new NiftyDialogBuilder(context, R.style.dialog_untran);
			;
			// 设置Dialog的类型为全局使用
			// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			Effectstype effect = Effectstype.Shake;
			dialogBuilder_updater
					.withTitle("更新提示")
					// .withTitle(null) no title
					.withTitleColor("#FFFFFF")
					// def
					.withDividerColor("#11000000")
					// def //.withMessage(null) no Msg
					// def
					.withIcon(context.getResources().getDrawable(R.drawable.logo1))
					.isCancelableOnTouchOutside(false) // def | isCancelable(true)
					.withDuration(200) // def
					.withEffect(effect) // def Effectstype.Slidetop
					
					 // def gone
					.setCustomView(
							OtherDialogView.updateDialogView(
									LayoutInflater.from(context), entity),
							context.getApplicationContext()) // .setCustomView(View
																// or ResId,context)
					;
			if(entity.getForceupgrase()==UpgradeEntity.FORCEUPGRADE_NO){
				dialogBuilder_updater.withButton1Text("开始更新") // def gone
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						MyApplication app = MyApplication.getInstance();
						if(!app.isDownload()){
							app.setDownload(true);
							DownloadService.apkUrl = entity.getUrl();
							Intent it = new Intent(context.getApplicationContext(),
									DownloadService.class);
							context.startService(it);
						}
					
						// context.bindService(it, new
						// UpdateServiceConnection(), Context.BIND_AUTO_CREATE);
						dialogBuilder_updater.dismiss();
						dialogBuilder_updater = null;
					}
				}).withButton2Text("稍后更新").setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder_updater.dismiss();
						dialogBuilder_updater = null;
					}
				});
			}else{
				dialogBuilder_updater.withButton3Text("开始更新") // def gone
				.setButton3Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						MyApplication app = MyApplication.getInstance();
						if(!app.isDownload()){
							app.setDownload(true);
							DownloadService.apkUrl = entity.getUrl();
							Intent it = new Intent(context.getApplicationContext(),
									DownloadService.class);
							context.startService(it);
						}
					
						// context.bindService(it, new
						// UpdateServiceConnection(), Context.BIND_AUTO_CREATE);
//						dialogBuilder_updater.dismiss();
//						dialogBuilder_updater = null;
						updadatingDialog(MyApplication.getInstance().getCur_Activity());
					}
				});
				dialogBuilder_updater.isCancelable(false);
			}
			dialogBuilder_updater.show();
		}
		
	}

	
	/**
	 * 正在更新提示
	 * @param context
	 *
	 */
	
	public static void updadatingDialog(final Context context) {
		if(NotifyDialog.dialogBuilder_updater !=null && NotifyDialog.dialogBuilder_updater.isShowing()) {
			NotifyDialog.dialogBuilder_updater.dismiss();
			NotifyDialog.dialogBuilder_updater=null;
		}
			dialogBuilder_updater = new NiftyDialogBuilder(context, R.style.dialog_untran);

			// 设置Dialog的类型为全局使用
			// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			Effectstype effect = Effectstype.Shake;
			dialogBuilder_updater
					.withTitle("更新提示")
					// .withTitle(null) no title
					.withTitleColor("#FFFFFF")
					// def
					.withDividerColor("#11000000")
					// def //.withMessage(null) no Msg
					// def
					.withIcon(context.getResources().getDrawable(R.drawable.logo1))
					.isCancelableOnTouchOutside(false) // def | isCancelable(true)
					.withDuration(200) // def
					.withEffect(effect) // def Effectstype.Slidetop
					
					 // def gone
					.withMessage("正在更新版本中，请稍候...")
					;
	
			dialogBuilder_updater.isCancelable(false);
			dialogBuilder_updater.show();
		
		
	}
	/**
	 * 发送音频提示对话框
	 * 
	 * @param context
	 */
	public static void SendDialog(Context context) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		// 设置Dialog的类型为全局使用
		// dialogBuilder.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder.withTitle("音频配置温馨提示")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage("WIFI配置中，请稍候...")
				// .withMessage(null) no Msg
				.withMessageColor("#FFFFFF")
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false) // def | isCancelable(true)
				.withDuration(500) // def
				.withEffect(effect)
				// def Effectstype.Slidetop

				.setCustomView(
						ChooseDialogView.FSKAlartView(LayoutInflater
								.from(context)),
						context.getApplicationContext()) // .setCustomView(View
															// or ResId,context)
				.show();
	}

	public static NiftyDialogBuilderChoose dialogBuilderChoose = null;

	/**
	 * 退出程序对话框
	 * 
	 * @param context
	 */
	public static void ExitAppDialog(Context context) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		// 设置Dialog的类型为全局使用
		// dialogBuilder.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder
				.withTitle("温馨提示")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage("确定退出吗？")
				// .withMessage(null) no Msg
				.withMessageColor("#99000000")
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false)
				// def | isCancelable(true)
				.withDuration(500)
				// def
				.withEffect(effect)
				// def Effectstype.Slidetop
				.withButton1Text("确定").withButton2Text("取消")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {

							Tools.isRec = false;
							Tools.isSend = false;
							WifiStateWatchThread.isWatchWifiState = false;
							TerminalTools.adapter_infoByIP.clear();
							Tools.Current_SocketIP = "";
							Tools.generalEntity = null;
							// 关闭掉服务端
							Network.destroy();
							MyApplication.getInstance().exitApp();

							dialogBuilder.dismiss();
							dialogBuilder = null;

						}
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).show();

	}

	/**
	 * 快捷城市选择对话框
	 * 
	 * @param context
	 */
	public static void QuickChooseCityDialog(Context context, final TextView tv) {
		String title = "";
		title = "快捷城市入口：";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
//		dialogBuilderChoose.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilderChoose
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect)
				// def Effectstype.Slidetop
				// def gone
				.setCustomView(
						ChooseDialogView.QuickProAndCityChooseview(
								LayoutInflater.from(context), tv),
						context.getApplicationContext()) // .setCustomView(View
															// or ResId,context)

				.show();
	}

	/**
	 * 城市选择对话框
	 * 
	 * @param context
	 */
	public static void ChooseCityDialog(Context context, final int tag,
			final Button bt_v, final TextView tv, Button bt_City, String sp_TAG) {
		String title = "";
		if (tag == CitySetTools.CITY_TAG) {
			title = "请选择城市：";
		} else {
			title = "请选择省份：";
		}
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		SortListDialog sd = new SortListDialog((Activity) context, tag, bt_v,
				tv, bt_City);
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
//		dialogBuilderChoose.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilderChoose
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect)
				// def Effectstype.Slidetop
				// def gone
				.setCustomView(sd.getView(), context.getApplicationContext()) // .setCustomView(View
				// or ResId,context)

				.show();
	}

	/**
	 * 笑话类型选择对话框
	 * 
	 * @param context
	 */
	public static void ChooseLaughDialog(Context context, final TextView tv,
			String sp_TAG) {
		String title = "请选择笑话类型:";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilderChoose.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilderChoose
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						ChooseDialogView.Laughview(
								LayoutInflater.from(MyApplication.getInstance().getCur_Activity()), tv, sp_TAG),
						context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect) // def Effectstype.Slidet
				.show();
	}

	/**
	 * 生日闹铃提前天数
	 * 
	 * @param context
	 */
	public static void ChooseAheadDays(Context context, final TextView tv_days,
			String sp_TAG, int type) {
		String title = "请选择提前的天数:";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilderChoose.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilderChoose
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						ChooseDialogView.AheadDaysView(
								LayoutInflater.from(MyApplication.getInstance().getCur_Activity()), tv_days, sp_TAG,
								type), context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect) // def Effectstype.Slidet
				.show();
	}

	/**
	 * 整体设置，目前包含发音人设置
	 * 
	 * @param context
	 * @param content
	 *            需要进行适配的内容
	 * @param title1
	 *            需要显示的标题
	 */
	public static void ChooseSET_WHOLE(Context context, final Button bt_days,
			String sp_TAG, ArrayList<HashMap<String, String>> content,
			String title1) {
		String title = title1;
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilderChoose.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilderChoose
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						ChooseDialogView.WholeSetView(
								LayoutInflater.from(MyApplication.getInstance().getCur_Activity()), bt_days, sp_TAG,
								content), context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect) // def Effectstype.Slidet
				.show();
	}

	/**
	 * 
	 * 时间选择对话框
	 * 
	 * @param context
	 * @param Timetype
	 *            需要的WheelView类型
	 *            Tools.TYPE_TIME(14时18分)或Tools.TYPE_DATE（2014年12月4日）
	 * @param canChooseLunar
	 *            是否可以选择阴历
	 * @param useMyData
	 *            是否用自己的日期数据
	 * @param mydata
	 *            自己的日期数据，不想传可以传"",年月日 2014年12月6日,时分16:51
	 * @param isLunar
	 *            是否使用阴历
	 * @param sp_TAG
	 * @param type
	 *            是来自闹铃的设置还是来自节日节气的设置Tools.TYPE_SET_ALART,Tools.TYPE_SET_FESTIVE
	 */

	public static void ChooseTIMEDialog(Context context, final int Timetype,
			boolean canChooseLunar, boolean useMyData, String mydata,
			boolean isLunar, final String sp_TAG, final Button bt_time,
			final int type,int startHour,int endHour) {
		String title = "请选择时间:";
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		// dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
		// R.style.dialog_untran);
		// ;
		// 设置Dialog的类型为全局使用
//		dialogBuilder.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						DateWheelView.getDateView(context, Timetype,
								canChooseLunar, useMyData, mydata, isLunar, startHour, endHour),
						context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false)
				// def | isCancelable(true)
				.withDuration(300)
				// def
				.withEffect(effect)
				// def Effectstype.Slidet
				.withButton1Text("确定").withButton2Text("取消")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// 此处仅适合于时间16：30一类，不适合2014年。。。的日期
							// String
							// spStr=Tools.time.split(Tools.FLAG_TIME_MAOHAO)[0]+Tools.time.split(Tools.FLAG_TIME_MAOHAO)[1];
							//
							// //如果为闹铃设置
							// SharedPrefenceSetTools.ed.putString(sp_TAG,spStr);
							// SharedPrefenceSetTools.ed.commit();

							if (Timetype == Tools.FLAG_TIME) {
								bt_time.setText(Tools.time);
							} else if (Timetype == Tools.FLAG_DATE) {
								bt_time.setText(Tools.date);
							}

							dialogBuilder.dismiss();
							dialogBuilder = null;
						}
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).show();
	}
	/**
	 * TextView显示时间
	 * @param context
	 * @param Timetype
	 * @param canChooseLunar
	 * @param useMyData
	 * @param mydata
	 * @param isLunar
	 * @param sp_TAG
	 * @param bt_time
	 * @param type
	 * @param startHour
	 * @param endHour
	 */
	public static void ChooseTIMEDialog_TV(Context context, final int Timetype,
			boolean canChooseLunar, boolean useMyData, String mydata,
			boolean isLunar, final String sp_TAG, final TextView bt_time,
			final int type,int startHour,int endHour) {
		String title = "请选择时间:";
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		// dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
		// R.style.dialog_untran);
		// ;
		// 设置Dialog的类型为全局使用
//		dialogBuilder.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						DateWheelView.getDateView(context, Timetype,
								canChooseLunar, useMyData, mydata, isLunar, startHour, endHour),
						context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false)
				// def | isCancelable(true)
				.withDuration(300)
				// def
				.withEffect(effect)
				// def Effectstype.Slidet
				.withButton1Text("确定").withButton2Text("取消")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// 此处仅适合于时间16：30一类，不适合2014年。。。的日期
							// String
							// spStr=Tools.time.split(Tools.FLAG_TIME_MAOHAO)[0]+Tools.time.split(Tools.FLAG_TIME_MAOHAO)[1];
							//
							// //如果为闹铃设置
							// SharedPrefenceSetTools.ed.putString(sp_TAG,spStr);
							// SharedPrefenceSetTools.ed.commit();

							if (Timetype == Tools.FLAG_TIME) {
								bt_time.setText(Tools.time);
							} else if (Timetype == Tools.FLAG_DATE) {
								bt_time.setText(Tools.date);
							}

							dialogBuilder.dismiss();
							dialogBuilder = null;
						}
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).show();
	}

	/**
	 * 睡眠时长选择对话框
	 * 
	 * @param context
	 */
	public static void ChooseSleepTimeDialog(Context context,
			final TextView tv, ArrayList<HashMap<String, String>> al,
			String sp_TAG) {
		String title = "请选择时长:";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilderChoose.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilderChoose
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						ChooseDialogView.SleepTimeChooseview(
								LayoutInflater.from(MyApplication.getInstance().getCur_Activity()), tv, al),
						context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect) // def Effectstype.Slidet
				.show();
	}

	
	/**
	 * 字符串列表选择对话框,按钮显示选中的内容
	 * 
	 * @param context
	 */
	public static void ChooseListStringBTDialog(Context context,String title,
			final Button bt, String []info) {
		
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context,
				R.style.dialog_untran);
		// 设置Dialog的类型为全局使用
		// dialogBuilderChoose.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						new ChooseListDialogView(context).commentChooseview(bt, info),
						context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect) // def Effectstype.Slidet
				.show();
	}

	
	/**
	 * 设备选择对话框
	 * 
	 * @param context
	 */
	public static void ChooseDeviceDialog(Context context, final TextView tv,
			String sp_TAG) {
		String title = "请选择切换的设备:";
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context,
				R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilderChoose.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						ChooseDialogView.DeviceChooseview(
								LayoutInflater.from(MyApplication.getInstance().getCur_Activity()), tv,
								TerminalTools.adapter_infoByIP),
						context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect) // def Effectstype.Slidet
				.show();
	}

	/**
	 * Wifi的SSID 选择对话框
	 * 
	 * @param context
	 */
	public static void ChooseSSID(Context context, final Button bt) {
		String title = "请选择SSID:";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilderChoose.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilderChoose
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.setCustomView(
						ChooseDialogView.SSIDview(LayoutInflater.from(MyApplication.getInstance().getCur_Activity()),
								bt), context.getApplicationContext())
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect) // def Effectstype.Slidet
				.show();
	}
	/**
	 *设备未成功连接提示对话框
	 * 
	 * @param context
	 */
	public static void notConnectAlertDialog(Context context) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder.withTitle("温馨提示")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage("设备未能成功连接到WIFI，请检查WIFI密码是否有误，并重新配置")
				// .withMessage(null) no Msg
				.withMessageColor("#99000000")
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(200) // def
				.withEffect(effect) // def Effectstype.Slidetop
				.withButton1Text("重新配置") // def gone
				.withButton2Text("进入设备列表")
				.setCustomView(R.layout.custom_view,
						context.getApplicationContext()) // .setCustomView(View
															// or ResId,context)
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						ActivitySetting.startActivity(MyApplication.getInstance().getCur_Activity(), WifiSetActivity.class, null, true);
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						ActivitySetting.startActivity(MyApplication.getInstance().getCur_Activity(), TerminalActivity.class, null, true);
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).show();
	}
	/**
	 * 网络提示对话框
	 * 
	 * @param context
	 */
	public static void SimpleAlertDialog(Context context, String Notifyinfo) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder.withTitle("温馨提示")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage(Notifyinfo)
				// .withMessage(null) no Msg
				.withMessageColor("#99000000")
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(200) // def
				.withEffect(effect) // def Effectstype.Slidetop
				.withButton3Text("确定") // def gone

				.setCustomView(R.layout.custom_view,
						context.getApplicationContext()) // .setCustomView(View
															// or ResId,context)
				.setButton3Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).show();
	}
	/**
	 * NetWork强制操作的提示对话框
	 * 
	 * @param context
	 */
	public static void SimpleAlertDialog_NetAlart(Context context, String Notifyinfo) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
		// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder.withTitle("温馨提示")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage(Notifyinfo)
				// .withMessage(null) no Msg
				.withMessageColor("#99000000")
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false) // def | isCancelable(true)
				.isCancelable(false)
				.withDuration(200) // def
				.withEffect(effect) // def Effectstype.Slidetop
				.withButton3Text("重新扫描") // def gone

				.setCustomView(R.layout.custom_view,
						context.getApplicationContext()) // .setCustomView(View
															// or ResId,context)
				.setButton3Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if(!Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
							SharedPreferences shared;
						
							shared = new SharedConfig(MyApplication.getInstance().getCur_Activity()).GetConfig(); // 得到配置文件
							WifiAdmin wa;// Wifi操作类
							wa = new WifiAdmin(MyApplication.getInstance().getCur_Activity());
							String wifiinfo = wa.GetBSSID();
							shared.edit()
									.putString(SharedConfig.KEY_WIFIBSSID, wifiinfo)
									.commit();
						
							TerminalTools.adapter_infoByIP.clear();
							
							ActivitySetting.startActivity((Activity) MyApplication.getInstance().getCur_Activity(),
									SearchDevicesActivity.class, null,true);
						}else{
							Tools.Current_DeviceName="";
						}
						
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).show();
		
		
	}
	/**
	 * 强制操作的提示对话框
	 * 
	 * @param context
	 */
	public static void SimpleAlertDialog_CannotClose(Context context, String Notifyinfo,String bt_text) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			
		}else{
			dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
			;
			// 设置Dialog的类型为全局使用
			// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			Effectstype effect = Effectstype.Newspager;
			dialogBuilder.withTitle("温馨提示")
					// .withTitle(null) no title
					.withTitleColor("#FFFFFF")
					// def
					.withDividerColor("#11000000")
					// def
					.withMessage(Notifyinfo)
					// .withMessage(null) no Msg
					.withMessageColor("#99000000")
					// def
					.withIcon(context.getResources().getDrawable(R.drawable.logo1))
					.isCancelableOnTouchOutside(false) // def | isCancelable(true)
					.isCancelable(false)
					.withDuration(200) // def
					.withEffect(effect) // def Effectstype.Slidetop
					.withButton3Text(bt_text) // def gone

					.setCustomView(R.layout.custom_view,
							context.getApplicationContext()) // .setCustomView(View
																// or ResId,context)
					.setButton3Click(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							Tools.isRec = false;
							Tools.isSend = false;
							WifiStateWatchThread.isWatchWifiState = false;
							TerminalTools.adapter_infoByIP.clear();
							Tools.Current_SocketIP = "";
							Tools.generalEntity = null;
							// 关闭掉服务端
							Network.destroy();
							MyApplication.getInstance().exitApp();

							dialogBuilder.dismiss();
							dialogBuilder = null;
							
						}
					}).show();
		}
		
		
		
	}
	
	
}
