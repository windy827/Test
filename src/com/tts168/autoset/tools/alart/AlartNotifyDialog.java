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
 * ��Ѥ��Ч����Dialog�������˱��˵�Jar��
 * 
 * @author Ԭ��
 * 
 */
public class AlartNotifyDialog {

	public static NiftyDialogBuilder dialogBuilder = null;

	/**
	 * ��������ʱ��
	 * 
	 * @param context
	 */
	public static void choosePlayTimeDialog(Context context, final TextView tv) {
		String title = "";
		title = "ѡ����������ʱ��:";
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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
//	 * ѡ������
//	 * 
//	 * @param context
//	 */
//	public static void chooseRingDialog(Context context, final TextView tv) {
//		String title = "";
//		title = "ѡ������:";
//		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
//			AlartNotifyDialog.dialogBuilder.dismiss();
//			AlartNotifyDialog.dialogBuilder=null;
//		}
//		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
//		;
//		// ����Dialog������Ϊȫ��ʹ��
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
	 * ѡ��С˯����
	 * 
	 * @param context
	 * @param tv
	 *            ��ʾС˯ʱ��
	 * @param mins
	 *            С˯�ķ�������0-60��
	 * @param times
	 *            С˯�Ĵ�����0-10��
	 */

	public static void chooseSleepAlartDialog(Context context,
			final TextView tv, int mins, int times) {
		String title = "";
		title = "С˯ʱ��:";
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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

				.withButton1Text("ȷ��").withButton2Text("ȡ��")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// �˴����ʺ���ʱ��16��30һ�࣬���ʺ�2014�ꡣ����������
							tv.setText("ÿ"
									+ ((int) AlartCommenSetTools.delay_time)
									+ "������һ�Σ���"
									+ ((int) AlartCommenSetTools.delay_num)
									+ "��");
							AlartTools.getUpSetEntity
									.setDelay_time(AlartCommenSetTools.delay_time);
							AlartTools.getUpSetEntity
									.setDelay_num(AlartCommenSetTools.delay_num);

							// ��ԭ��ʼ����ֵ����ֹӦ��û�˳�����Ϊ�ϴε��������
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
	 * ɾ������ ��ʾ�Ի���
	 * 
	 * @param context
	 * @param data
	 *            ������
	 * @param index
	 *            ���������
	 * @param jsonType
	 *            ��ǰ�������Json����
	 */

	public static void deleteAlartDialog(final Activity activity,
			Context context, final ArrayList<HashMap<String, Object>> data,
			final int index, final int jsonType) {
		String title = "";
		title = "��ܰ��ʾ";
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
//		dialogBuilder.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				.withMessage("ȷ��ɾ����")
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

				.withButton1Text("ȷ��").withButton2Text("ȡ��")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// �˴����ʺ���ʱ��16��30һ�࣬���ʺ�2014�ꡣ����������

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
											"ɾ��ʧ��","ɾ���ɹ�",
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
	 * �رջ������ ��ʾ�Ի���
	 * 
	 * @param context
	 * @param want_statu_isopen ϣ����״̬ 
 * @param al_info Ϊ���յ������������ArrayList������Alart��Remind�Լ�BirthDay��������
 * @param index ׼���޸ĵ�������Ϣ��id����
 *  @param faildMsg ����ʧ�ܵĽ����Ϣ
 *   @param successMsg ���ܳɹ��Ľ����Ϣ
 * @param optionType ��������  AlartTools.AlartType.OPTIONS_
	 */

	public static void openOrCloseAlartDialog(
			Context context,final boolean want_statu_isopen,final ArrayList<HashMap<String,Object>>al_info,
			final int []index,final int jsonType,final String faildMsg,final String successMsg,final int optionType) {
		String title = "";
		title = "��ܰ��ʾ";
		String msg="";
		if(want_statu_isopen){
			msg="ȷ������������";
		}else{
			msg="ȷ���ر�������";
		}
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		
		// ����Dialog������Ϊȫ��ʹ��
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

				.withButton1Text("ȷ��").withButton2Text("ȡ��")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// �˴����ʺ���ʱ��16��30һ�࣬���ʺ�2014�ꡣ����������
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
	 * ��������
	 * 
	 * @param context
	 * @param tv
	 *            ��ʾ����
	 * @param fre_model
	 *            ��һ�������ӻ���ѭ�����У�=0��ʾ��һ��������;=1��ÿ�죻=2���Զ���
	 * @param frequency
	 *            �������ڣ�
	 * 
	 */

	public static void chooseCycleDialog1(Context context, final TextView tv) {
		String title = "";
		title = "ѡ������:";
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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
				.withButton1Text("ȷ��").withButton2Text("ȡ��")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(1000)) {
							// �˴����ʺ���ʱ��16��30һ�࣬���ʺ�2014�ꡣ����������
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
