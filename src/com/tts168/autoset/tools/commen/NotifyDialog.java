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
 * ��Ѥ��Ч����Dialog�������˱��˵�Jar��
 * 
 * @author Ԭ��
 * 
 */
public class NotifyDialog {

	public static NiftyDialogBuilder dialogBuilder = null;
	public static NiftyDialogBuilder dialogBuilder_updater = null;
	/**
	 * ���������ʾ�Ի���
	 * 
	 * @param context
	 * 
	 * @param content
	 *            ���µ�����
	 * @param updateURL
	 *            apk���ص�URL
	 * @param ver_name
	 *            apk�İ汾����
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
			// ����Dialog������Ϊȫ��ʹ��
			// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			Effectstype effect = Effectstype.Shake;
			dialogBuilder_updater
					.withTitle("������ʾ")
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
				dialogBuilder_updater.withButton1Text("��ʼ����") // def gone
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
				}).withButton2Text("�Ժ����").setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder_updater.dismiss();
						dialogBuilder_updater = null;
					}
				});
			}else{
				dialogBuilder_updater.withButton3Text("��ʼ����") // def gone
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
	 * ���ڸ�����ʾ
	 * @param context
	 *
	 */
	
	public static void updadatingDialog(final Context context) {
		if(NotifyDialog.dialogBuilder_updater !=null && NotifyDialog.dialogBuilder_updater.isShowing()) {
			NotifyDialog.dialogBuilder_updater.dismiss();
			NotifyDialog.dialogBuilder_updater=null;
		}
			dialogBuilder_updater = new NiftyDialogBuilder(context, R.style.dialog_untran);

			// ����Dialog������Ϊȫ��ʹ��
			// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			Effectstype effect = Effectstype.Shake;
			dialogBuilder_updater
					.withTitle("������ʾ")
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
					.withMessage("���ڸ��°汾�У����Ժ�...")
					;
	
			dialogBuilder_updater.isCancelable(false);
			dialogBuilder_updater.show();
		
		
	}
	/**
	 * ������Ƶ��ʾ�Ի���
	 * 
	 * @param context
	 */
	public static void SendDialog(Context context) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		// ����Dialog������Ϊȫ��ʹ��
		// dialogBuilder.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder.withTitle("��Ƶ������ܰ��ʾ")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage("WIFI�����У����Ժ�...")
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
	 * �˳�����Ի���
	 * 
	 * @param context
	 */
	public static void ExitAppDialog(Context context) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		// ����Dialog������Ϊȫ��ʹ��
		// dialogBuilder.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder
				.withTitle("��ܰ��ʾ")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage("ȷ���˳���")
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
				.withButton1Text("ȷ��").withButton2Text("ȡ��")
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
							// �رյ������
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
	 * ��ݳ���ѡ��Ի���
	 * 
	 * @param context
	 */
	public static void QuickChooseCityDialog(Context context, final TextView tv) {
		String title = "";
		title = "��ݳ�����ڣ�";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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
	 * ����ѡ��Ի���
	 * 
	 * @param context
	 */
	public static void ChooseCityDialog(Context context, final int tag,
			final Button bt_v, final TextView tv, Button bt_City, String sp_TAG) {
		String title = "";
		if (tag == CitySetTools.CITY_TAG) {
			title = "��ѡ����У�";
		} else {
			title = "��ѡ��ʡ�ݣ�";
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
		// ����Dialog������Ϊȫ��ʹ��
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
	 * Ц������ѡ��Ի���
	 * 
	 * @param context
	 */
	public static void ChooseLaughDialog(Context context, final TextView tv,
			String sp_TAG) {
		String title = "��ѡ��Ц������:";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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
	 * ����������ǰ����
	 * 
	 * @param context
	 */
	public static void ChooseAheadDays(Context context, final TextView tv_days,
			String sp_TAG, int type) {
		String title = "��ѡ����ǰ������:";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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
	 * �������ã�Ŀǰ��������������
	 * 
	 * @param context
	 * @param content
	 *            ��Ҫ�������������
	 * @param title1
	 *            ��Ҫ��ʾ�ı���
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
		// ����Dialog������Ϊȫ��ʹ��
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
	 * ʱ��ѡ��Ի���
	 * 
	 * @param context
	 * @param Timetype
	 *            ��Ҫ��WheelView����
	 *            Tools.TYPE_TIME(14ʱ18��)��Tools.TYPE_DATE��2014��12��4�գ�
	 * @param canChooseLunar
	 *            �Ƿ����ѡ������
	 * @param useMyData
	 *            �Ƿ����Լ�����������
	 * @param mydata
	 *            �Լ����������ݣ����봫���Դ�"",������ 2014��12��6��,ʱ��16:51
	 * @param isLunar
	 *            �Ƿ�ʹ������
	 * @param sp_TAG
	 * @param type
	 *            ��������������û������Խ��ս���������Tools.TYPE_SET_ALART,Tools.TYPE_SET_FESTIVE
	 */

	public static void ChooseTIMEDialog(Context context, final int Timetype,
			boolean canChooseLunar, boolean useMyData, String mydata,
			boolean isLunar, final String sp_TAG, final Button bt_time,
			final int type,int startHour,int endHour) {
		String title = "��ѡ��ʱ��:";
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		// dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
		// R.style.dialog_untran);
		// ;
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
				.withButton1Text("ȷ��").withButton2Text("ȡ��")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// �˴����ʺ���ʱ��16��30һ�࣬���ʺ�2014�ꡣ����������
							// String
							// spStr=Tools.time.split(Tools.FLAG_TIME_MAOHAO)[0]+Tools.time.split(Tools.FLAG_TIME_MAOHAO)[1];
							//
							// //���Ϊ��������
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
	 * TextView��ʾʱ��
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
		String title = "��ѡ��ʱ��:";
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
		// dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
		// R.style.dialog_untran);
		// ;
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
				.withButton1Text("ȷ��").withButton2Text("ȡ��")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (!PreventForceClick.isForceClick(500)) {
							// �˴����ʺ���ʱ��16��30һ�࣬���ʺ�2014�ꡣ����������
							// String
							// spStr=Tools.time.split(Tools.FLAG_TIME_MAOHAO)[0]+Tools.time.split(Tools.FLAG_TIME_MAOHAO)[1];
							//
							// //���Ϊ��������
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
	 * ˯��ʱ��ѡ��Ի���
	 * 
	 * @param context
	 */
	public static void ChooseSleepTimeDialog(Context context,
			final TextView tv, ArrayList<HashMap<String, String>> al,
			String sp_TAG) {
		String title = "��ѡ��ʱ��:";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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
	 * �ַ����б�ѡ��Ի���,��ť��ʾѡ�е�����
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
		// ����Dialog������Ϊȫ��ʹ��
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
	 * �豸ѡ��Ի���
	 * 
	 * @param context
	 */
	public static void ChooseDeviceDialog(Context context, final TextView tv,
			String sp_TAG) {
		String title = "��ѡ���л����豸:";
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
		}
		dialogBuilder = new NiftyDialogBuilder(context,
				R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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
	 * Wifi��SSID ѡ��Ի���
	 * 
	 * @param context
	 */
	public static void ChooseSSID(Context context, final Button bt) {
		String title = "��ѡ��SSID:";
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
		}
		dialogBuilderChoose = new NiftyDialogBuilderChoose(context,
				R.style.dialog_untran);
		;
		// ����Dialog������Ϊȫ��ʹ��
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
	 *�豸δ�ɹ�������ʾ�Ի���
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
		// ����Dialog������Ϊȫ��ʹ��
		// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder.withTitle("��ܰ��ʾ")
				// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage("�豸δ�ܳɹ����ӵ�WIFI������WIFI�����Ƿ����󣬲���������")
				// .withMessage(null) no Msg
				.withMessageColor("#99000000")
				// def
				.withIcon(context.getResources().getDrawable(R.drawable.logo1))
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(200) // def
				.withEffect(effect) // def Effectstype.Slidetop
				.withButton1Text("��������") // def gone
				.withButton2Text("�����豸�б�")
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
	 * ������ʾ�Ի���
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
		// ����Dialog������Ϊȫ��ʹ��
		// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder.withTitle("��ܰ��ʾ")
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
				.withButton3Text("ȷ��") // def gone

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
	 * NetWorkǿ�Ʋ�������ʾ�Ի���
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
		// ����Dialog������Ϊȫ��ʹ��
		// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		Effectstype effect = Effectstype.Newspager;
		dialogBuilder.withTitle("��ܰ��ʾ")
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
				.withButton3Text("����ɨ��") // def gone

				.setCustomView(R.layout.custom_view,
						context.getApplicationContext()) // .setCustomView(View
															// or ResId,context)
				.setButton3Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if(!Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
							SharedPreferences shared;
						
							shared = new SharedConfig(MyApplication.getInstance().getCur_Activity()).GetConfig(); // �õ������ļ�
							WifiAdmin wa;// Wifi������
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
	 * ǿ�Ʋ�������ʾ�Ի���
	 * 
	 * @param context
	 */
	public static void SimpleAlertDialog_CannotClose(Context context, String Notifyinfo,String bt_text) {
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			
		}else{
			dialogBuilder = new NiftyDialogBuilder(context, R.style.dialog_untran);
			;
			// ����Dialog������Ϊȫ��ʹ��
			// dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			Effectstype effect = Effectstype.Newspager;
			dialogBuilder.withTitle("��ܰ��ʾ")
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
							// �رյ������
							Network.destroy();
							MyApplication.getInstance().exitApp();

							dialogBuilder.dismiss();
							dialogBuilder = null;
							
						}
					}).show();
		}
		
		
		
	}
	
	
}
