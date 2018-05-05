package com.tts168.autoset.tools.highcset.terminal;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.amo.demo.wheelview.DateWheelView;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.general.GeneralJsonOption;
import com.autoset.json.JsonParseOption;
import com.larkiv.larksmart7618.R;

import com.niftydialogeffects.Effectstype;
import com.niftydialogeffects.NiftyDialogBuilder;
import com.niftydialogeffects.NiftyDialogBuilderChoose;
import com.tools.sortlistview.SortListDialog;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.adapter.terminal.TerminalListAdapter;

import com.tts168.autoset.database.DB_Commeration_Option;
import com.tts168.autoset.database.DB_DailyAlart_Option;
import com.tts168.autoset.model.wheelview.WheelViewSleepAlart;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.MyWifiView;
import com.tts168.autoset.view.notifydialogView.ChooseDialogView;
import com.tts168.autoset.view.terminal.TerminalChooseDialogView;


import android.app.Activity;
import android.content.Context;
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
public class TerminalNotifyDialog {

	public static NiftyDialogBuilder dialogBuilder = null;


	public static NiftyDialogBuilderChoose dialogBuilderChoose = null;

	/**
	 * 更改昵称
	 * 
	 * @param nickName 原昵称名
	 */
	public static void editNickNameDialog(Context context,final String nickName,final GeneralEntity ge,final String socketIP) {
		String title = "";
		title = "修改昵称:";
		if(TerminalNotifyDialog.dialogBuilder !=null && TerminalNotifyDialog.dialogBuilder.isShowing()) {
			TerminalNotifyDialog.dialogBuilder.dismiss();
			TerminalNotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		final TerminalChooseDialogView td=new TerminalChooseDialogView()
		;
		// 设置Dialog的类型为全局使用
//		dialogBuilder.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				// .withTitle(null) no title
				.withTitleColor("#99000000")
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
						td.editNameChooseview(
								LayoutInflater.from(context),nickName),
						context.getApplicationContext())
				// .setCustomView(View
				// or ResId,context)

				.withButton1Text("确定").withButton2Text("取消")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							String deviceName=td.et_title.getText().toString();
							ge.setNickname(deviceName);
							
							String content=GeneralJsonOption.setData_general(ge, JsonParseOption.SET_USERDATA,""+RemindVoiceTools.CommentRemindTools.FAILED_UPDATE,""+RemindVoiceTools.CommentRemindTools.SUCESS_UPDATE);
							
							ArrayList<String>contents=new ArrayList<String>();
							contents.add(content);
							TCPTools.sendTcp(contents, socketIP,true);
//							v.postDelayed(new Runnable() {
//								public void run() {
//									try {
//										Thread.sleep(1000);
//									} catch (InterruptedException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//									((TerminalActivity)MyApplication.getInstance().getCur_Activity()).searchDevice2Refresh();
//								}
//							}, 500);
							
							
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
	 * 更改分组
	 * 
	 * @param context
	 */
	public static void changeGroupDialog(Context context,final String macIp, final String childName) {
		String title = "";
		title = "更改分组:";
		if(TerminalNotifyDialog.dialogBuilderChoose !=null && TerminalNotifyDialog.dialogBuilderChoose.isShowing()) {
			TerminalNotifyDialog.dialogBuilderChoose.dismiss();
			TerminalNotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		final TerminalChooseDialogView td=new TerminalChooseDialogView()
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
						td.groupChooseview(
								LayoutInflater.from(context),macIp, childName),
						context.getApplicationContext()) // .setCustomView(View
															// or ResId,context)

				.show();
	}

	
}
