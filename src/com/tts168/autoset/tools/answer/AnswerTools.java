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
 * �ʴ𹤾���
 * @author Ԭ��
 *
 */
public class AnswerTools {
	//__________________________________________________________________
	
	/**
	 * ����ʴ����ݵ������Ϣ�������ǳ��Լ�
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
		title = "��ܰ��ʾ";
		if(AnswerTools.dialogBuilder !=null && AnswerTools.dialogBuilder.isShowing()) {
			AnswerTools.dialogBuilder.dismiss();
			AnswerTools.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
//		dialogBuilder.getWindow().setType(
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Fadein;
		dialogBuilder
				.withTitle(title)
				.withMessage("��ѡ����Ҫ���еĲ���")
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

				.withButton1Text("����ɾ��").withButton2Text("����").withButton3Text("ȫ��ɾ��")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// �˴����ʺ���ʱ��16��30һ�࣬���ʺ�2014�ꡣ����������

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
							// �˴����ʺ���ʱ��16��30һ�࣬���ʺ�2014�ꡣ����������

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
