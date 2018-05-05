package com.tts168.autoset.tools.answer;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.larkiv.larksmart7618.R;
import com.niftydialogeffects.Effectstype;
import com.niftydialogeffects.NiftyDialogBuilder;
import com.tts168.autoset.activity.answerhelper.AnswerRecordActivity;
import com.tts168.autoset.database.answerhelper.AnswerHelperDBOptions;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.alart.MyAlartJsonOptions;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventForceClick;

/**
 * 问答工具类
 * @author 袁剑
 *
 */
public class AnswerTools {
	//__________________________________________________________________
	
	/**
	 * 存放问答内容的相关信息，包括昵称以及
	 */
	public static ArrayList<HashMap<String,Object>> al_AnswerInfo=new ArrayList<HashMap<String,Object>>();
	public static final String KEY_NICKNAME="nickname";
	public static final String KEY_ENTITY="enity";
	public static final String KEY_SOCKET="socket";
	
	//__________________________________________________________________
	public static NiftyDialogBuilder dialogBuilder = null;
	public static void deleteAlartDialog(
			Context context, final int id) {
		String title = "";
		title = "温馨提示";
		if(AnswerTools.dialogBuilder !=null && AnswerTools.dialogBuilder.isShowing()) {
			AnswerTools.dialogBuilder.dismiss();
			AnswerTools.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// 设置Dialog的类型为全局使用
//		dialogBuilder.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				.withMessage("请选择您要进行的操作")
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

				.withButton1Text("单个删除").withButton2Text("返回").withButton3Text("全部删除")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// 此处仅适合于时间16：30一类，不适合2014年。。。的日期

							AnswerHelperDBOptions.delInfo(id);
						
							((AnswerRecordActivity)MyApplication.getInstance().getCur_Activity()).refresh();
							dialogBuilder.dismiss();
							dialogBuilder = null;
							
						}
					}
				}).setButton3Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// 此处仅适合于时间16：30一类，不适合2014年。。。的日期

							AnswerHelperDBOptions.delAllInfo();
						
							((AnswerRecordActivity)MyApplication.getInstance().getCur_Activity()).refresh();
							dialogBuilder.dismiss();
							dialogBuilder = null;
							
						}
					}
				})
				.setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
						dialogBuilder = null;
					}
				}).show();
	}
}
