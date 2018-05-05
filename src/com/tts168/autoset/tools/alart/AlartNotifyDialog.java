package com.tts168.autoset.tools.alart;

import java.util.ArrayList;
import java.util.HashMap;

import com.amo.demo.wheelview.DateWheelView;
import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.json.MyTools;
import com.larkiv.larksmart7618.R;

import com.niftydialogeffects.Effectstype;
import com.niftydialogeffects.NiftyDialogBuilder;
import com.niftydialogeffects.NiftyDialogBuilderChoose;
import com.tts168.autoset.activity.alart.AwakeAlartEditActivity;
import com.tts168.autoset.activity.alart.BirthdayAlartActivity;
import com.tts168.autoset.activity.alart.DefinedAlartEditActivity;
import com.tts168.autoset.activity.alart.RemindAlartActivity;
import com.tts168.autoset.activity.alart.SleepAlartEditActivity;
import com.tts168.autoset.model.wheelview.WheelViewSleepAlart;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.view.alart.AlartChooseDialogView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 有绚丽效果的Dialog，调用了别人的Jar包
 * 
 * @author 袁剑
 * 
 */
public class AlartNotifyDialog {

	public static NiftyDialogBuilder dialogBuilder = null;

	/**
	 * 设置响铃时长
	 * 
	 * @param context
	 */
	public static void choosePlayTimeDialog(Context context, final TextView tv) {
		String title = "";
		title = "选择铃声播放时长:";
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
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
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(300) // def
				.withEffect(effect)
				// def Effectstype.Slidetop
				// def gone
				.setCustomView(
						AlartChooseDialogView.alartPlayTimeChooseview(
								LayoutInflater.from(context), tv),
						context.getApplicationContext()) // .setCustomView(View
															// or ResId,context)

				.show();
	}

//	/**
//	 * 选择铃声
//	 * 
//	 * @param context
//	 */
//	public static void chooseRingDialog(Context context, final TextView tv) {
//		String title = "";
//		title = "选择铃声:";
//		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
//			AlartNotifyDialog.dialogBuilder.dismiss();
//			AlartNotifyDialog.dialogBuilder=null;
//		}
//		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
//		;
//		// 设置Dialog的类型为全局使用
////		dialogBuilder.getWindow().setType(
////				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//		Effectstype effect = Effectstype.Fadein;
//		dialogBuilder
//				.withTitle(title)
//				// .withTitle(null) no title
//				.withTitleColor("#FFFFFF")
//				// def
//				.withDividerColor("#11000000")
//				// def
//				// def
//				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
//				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
//				.withDuration(300) // def
//				.withEffect(effect)
//				// def Effectstype.Slidetop
//				// def gone
//				.setCustomView(
//						AlartChooseDialogView.ringChooseView(
//								LayoutInflater.from(context), tv),
//						context.getApplicationContext()) // .setCustomView(View
//															// or ResId,context)
//
//				.show();
//	}

	/**
	 * 选择小睡设置
	 * 
	 * @param context
	 * @param tv
	 *            显示小睡时间
	 * @param mins
	 *            小睡的分钟数【0-60】
	 * @param times
	 *            小睡的次数【0-10】
	 */

	public static void chooseSleepAlartDialog(Context context,
			final TextView tv, int mins, int times) {
		String title = "";
		title = "小睡时间:";
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
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
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false)
				// def | isCancelable(true)
				.withDuration(300)
				// def
				.withEffect(effect)
				// def Effectstype.Slidetop
				// def gone
				.setCustomView(
						WheelViewSleepAlart.getTimeView(context, mins, times),
						context.getApplicationContext())
				// .setCustomView(View
				// or ResId,context)

				.withButton1Text("确定").withButton2Text("取消")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// 此处仅适合于时间16：30一类，不适合2014年。。。的日期
							tv.setText("每"
									+ ((int) AlartCommenSetTools.delay_time)
									+ "分响铃一次，共"
									+ ((int) AlartCommenSetTools.delay_num)
									+ "次");
							AlartTools.getUpSetEntity
									.setDelay_time(AlartCommenSetTools.delay_time);
							AlartTools.getUpSetEntity
									.setDelay_num(AlartCommenSetTools.delay_num);

							// 还原初始化的值，防止应用没退出数据为上次的情况出现
							AlartCommenSetTools.delay_time = 5;
							AlartCommenSetTools.delay_num = 2;

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
	 * 删除闹铃 提示对话框
	 * 
	 * @param context
	 * @param data
	 *            总数据
	 * @param index
	 *            数组的索引
	 * @param jsonType
	 *            当前的闹铃的Json类型
	 */

	public static void deleteAlartDialog(final Activity activity,
			Context context, final ArrayList<HashMap<String, Object>> data,
			final int index, final int jsonType) {
		String title = "";
		title = "温馨提示";
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
//		dialogBuilder.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				.withMessage("确定删除吗？")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false)
				// def | isCancelable(true)
				.withDuration(300)
				// def
				.withEffect(effect)
				// def Effectstype.Slidetop
				// def gone
				// .setCustomView(View
				// or ResId,context)

				.withButton1Text("确定").withButton2Text("取消")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// 此处仅适合于时间16：30一类，不适合2014年。。。的日期

							if (Tools.CurrentActivityName
									.equals(AwakeAlartEditActivity.ActivityName)) {
								((AwakeAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
								
							} else if (Tools.CurrentActivityName
									.equals(SleepAlartEditActivity.ActivityName)) {
								((SleepAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
								
							} else if (Tools.CurrentActivityName
									.equals(DefinedAlartEditActivity.ActivityName)) {
								((DefinedAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
							} else if (Tools.CurrentActivityName
									.equals(BirthdayAlartActivity.ActivityName)) {
								((BirthdayAlartActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
								
							} else if (Tools.CurrentActivityName
									.equals(RemindAlartActivity.ActivityName)) {
								((RemindAlartActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
								
							}
							MyAlartJsonOptions
									.setAlartData(
											data,
											new int[] { index },
											jsonType,
											"删除失败","删除成功",
											AlartTools.AlartType.OPTIONS_DELETE);
							
							dialogBuilder.dismiss();
							dialogBuilder = null;
							// AlartTools.alartAdapter_content.remove(index);
						
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
	 * 关闭或打开闹铃 提示对话框
	 * 
	 * @param context
	 * @param want_statu_isopen 希望的状态 
 * @param al_info 为接收到的所有闹铃的ArrayList，包含Alart，Remind以及BirthDay三个域名
 * @param index 准备修改的闹铃信息的id数组
 *  @param faildMsg 接受失败的结果信息
 *   @param successMsg 接受成功的结果信息
 * @param optionType 操作类型  AlartTools.AlartType.OPTIONS_
	 */

	public static void openOrCloseAlartDialog(
			Context context,final boolean want_statu_isopen,final ArrayList<HashMap<String,Object>>al_info,
			final int []index,final int jsonType,final String faildMsg,final String successMsg,final int optionType) {
		String title = "";
		title = "温馨提示";
		String msg="";
		if(want_statu_isopen){
			msg="确定开启闹铃吗？";
		}else{
			msg="确定关闭闹铃吗？";
		}
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		
		// 设置Dialog的类型为全局使用
//		dialogBuilder.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				.withMessage(msg)
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false)
				// def | isCancelable(true)
				.withDuration(300)
				// def
				.withEffect(effect)
				// def Effectstype.Slidetop
				// def gone
				// .setCustomView(View
				// or ResId,context)

				.withButton1Text("确定").withButton2Text("取消")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// 此处仅适合于时间16：30一类，不适合2014年。。。的日期
							if(jsonType==AlartTools.AlartType.JSON_ALART_TYPE_ALART){
								if(want_statu_isopen){
									AlartTools.alarmEntity.setIs_valid(AlarmEntity.ISVALID_YES);
								}else{
									AlartTools.alarmEntity.setIs_valid(AlarmEntity.ISVALID_NO);
								}
							}else if(jsonType==AlartTools.AlartType.JSON_ALART_TYPE_REMIND){
								if(want_statu_isopen){
									AlartTools.remindEntity.setIs_valid(AlarmEntity.ISVALID_YES);
								}else{
									AlartTools.remindEntity.setIs_valid(AlarmEntity.ISVALID_NO);
								}
							}else if(jsonType==AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY){
								if(want_statu_isopen){
									
								}else{
									
								}
							}
							
							if (Tools.CurrentActivityName
									.equals(AwakeAlartEditActivity.ActivityName)) {
								((AwakeAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
								
							} else if (Tools.CurrentActivityName
									.equals(SleepAlartEditActivity.ActivityName)) {
								((SleepAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
								
							} else if (Tools.CurrentActivityName
									.equals(DefinedAlartEditActivity.ActivityName)) {
								((DefinedAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
							} else if (Tools.CurrentActivityName
									.equals(BirthdayAlartActivity.ActivityName)) {
								((BirthdayAlartActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
								
							} else if (Tools.CurrentActivityName
									.equals(RemindAlartActivity.ActivityName)) {
								((RemindAlartActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
								
							}
							MyAlartJsonOptions.setAlartData(al_info, index, jsonType, faildMsg, successMsg, optionType);
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
	 * 周期设置
	 * 
	 * @param context
	 * @param tv
	 *            显示周期
	 * @param fre_model
	 *            是一次性闹钟还是循环脑中，=0表示是一次性闹钟;=1是每天；=2是自定义
	 * @param frequency
	 *            闹钟周期，
	 * 
	 */

	public static void chooseCycleDialog1(Context context, final TextView tv) {
		String title = "";
		title = "选择周期:";
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
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
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(false)
				// def | isCancelable(true)
				.withDuration(300)
				// def
				.withEffect(effect)
				// def Effectstype.Slidetop
				// def gone
				.setCustomView(
						AlartChooseDialogView.cycleChooseView(
								LayoutInflater.from(context), tv),
						context.getApplicationContext())
				// .setCustomView(View
				.withButton1Text("确定").withButton2Text("取消")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(1000)) {
							// 此处仅适合于时间16：30一类，不适合2014年。。。的日期
							AlartTools.Cycle.Fre_model = AlartTools.Cycle.current_Fre_model;
							AlartTools.alarmEntity.setFre_mode(Double
									.parseDouble(AlartTools.Cycle.current_Fre_model
											+ ""));
							AlartTools.Cycle.cycleValue = AlartTools.Cycle
									.binarayInt2int(AlartTools.Cycle.week_check);
							AlartTools.alarmEntity.setFrequency(Double
									.parseDouble(AlartTools.Cycle.cycleValue
											+ ""));
							
							if((int) AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
								String date=Tools.date;
								tv.setText(date);
							}else{
								tv.setText(AlartTools.Cycle
										.getWeekCycleByIntArray(AlartTools.Cycle.current_Fre_model,AlartTools.Cycle.week_check));
							}
							
							dialogBuilder.dismiss();
							dialogBuilder = null;
						}
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
//						tv.setText(AlartTools.Cycle
//								.getWeekCycleByIntArray(AlartTools.Cycle.Fre_model,AlartTools.Cycle.getLastIntArrray((int) AlartTools.alarmEntity.getFrequency(),7)));
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).show();
	}
}
