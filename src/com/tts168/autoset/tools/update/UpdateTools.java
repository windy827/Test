package com.tts168.autoset.tools.update;

import android.os.Handler;

import com.autoset.jni.http.configAndupgrade.UpgradeEntity;
import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.commen.VersionTools;

/**
 * ���µĹ�����
 * 
 * @author Ԭ��
 * 
 */
public class UpdateTools {

	/**
	 * ����Ƿ񵯳���Ҫ���µ���ʾ�Ի���
	 */
	public static void checkUpdateDialog() {
		// ������
//		if (Tools.entity_Upgrade != null) {
//			int vcode = Integer.parseInt(Tools.entity_Upgrade.getVer_code());
//			int localVcode = VersionTools
//					.getVersionCodeByVersionName(MyApplication.getInstance()
//							.getCur_Activity());
//			if (vcode > localVcode) {
//				// ����������ʾ�Ի���
//
//				if (Tools.isAutoUpdateAlarm
//						|| Tools.entity_Upgrade.getForceupgrase() == UpgradeEntity.FORCEUPGRADE_YES) {
//					Tools.isAutoUpdateAlarm = false;
//
//					NotifyDialog.updadateRemindDialog(MyApplication
//							.getInstance().getCur_Activity(),
//							Tools.entity_Upgrade);
//				}
//
//			}
//		} else {
//
//			ToastTools.short_Toast(MyApplication
//							.getInstance().getCur_Activity(), "δ��ȡ���汾��Ϣ�����Ժ�����~");
//		}
	}
	
	/**
	 * �ж��Ƿ���Ҫ������ʾ�����Ϊ�ǣ�����治����ת
	 * @return
	 */
	public static boolean needUpdate(){
		boolean result=false;
//		if (Tools.entity_Upgrade != null) {
//			int vcode = Integer.parseInt(Tools.entity_Upgrade.getVer_code());
//			int localVcode = VersionTools
//					.getVersionCodeByVersionName(MyApplication.getInstance()
//							.getCur_Activity());
//			if (vcode > localVcode) {
//				// ����������ʾ�Ի���
//
//				if (Tools.isAutoUpdateAlarm
//						|| Tools.entity_Upgrade.getForceupgrase() == UpgradeEntity.FORCEUPGRADE_YES) {
//					Tools.isAutoUpdateAlarm = false;
//					result=true;
//				}
//
//			}
//		} else {
//
//			ToastTools.short_Toast(MyApplication
//							.getInstance().getCur_Activity(), "δ��ȡ���汾��Ϣ�����Ժ�����~");
//		}
		return result;
	}
}
