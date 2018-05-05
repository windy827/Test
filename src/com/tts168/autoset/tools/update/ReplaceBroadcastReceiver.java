package com.tts168.autoset.tools.update;

import java.io.File;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
/**
 * 删除旧 的相同的APK文件
 * @author Administrator
 *
 */
public class ReplaceBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "ApkDelete";

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		File downLoadApk = new File(Environment.getExternalStorageDirectory(),
				DownloadService.apk_name);
		if (downLoadApk.exists()) {
			downLoadApk.delete();
		}
		Log.i(TAG, "downLoadApkFile was deleted!");
	}

}
