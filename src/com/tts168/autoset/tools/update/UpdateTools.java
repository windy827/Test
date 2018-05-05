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
 * 更新的工具类
 * 
 * @author 袁剑
 * 
 */
public class UpdateTools {

	/**
	 * 检查是否弹出需要更新的提示对话框
	 */
	public static void checkUpdateDialog() {
		// 检测更新
//		if (Tools.entity_Upgrade != null) {
//			int vcode = Integer.parseInt(Tools.entity_Upgrade.getVer_code());
//			int localVcode = VersionTools
//					.getVersionCodeByVersionName(MyApplication.getInstance()
//							.getCur_Activity());
//			if (vcode > localVcode) {
//				// 弹出更新提示对话框
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
//							.getInstance().getCur_Activity(), "未获取到版本信息，请稍候重试~");
//		}
	}
	
	/**
	 * 判断是否需要更新提示，如果为是，则界面不能跳转
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
//				// 弹出更新提示对话框
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
//							.getInstance().getCur_Activity(), "未获取到版本信息，请稍候重试~");
//		}
		return result;
	}
}
