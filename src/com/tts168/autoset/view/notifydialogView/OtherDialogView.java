package com.tts168.autoset.view.notifydialogView;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.autoset.jni.http.configAndupgrade.UpgradeEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.highcset.cityset.ProviceAndCityResource;

public class OtherDialogView {
	/**
	 * 更新提示对话框的子View
	 * @param inflater
	 * @param content 更新提示的内容
	 * @return
	 */
		public static  View updateDialogView(LayoutInflater inflater,final UpgradeEntity entity) {
			
			String des=entity.getDescription();
			View view1=inflater.inflate(R.layout.dialog_update, null); 
			 TextView tv_content=(TextView) view1.findViewById(R.id.tv_dialog_update_content);
			 tv_content.setText(des);
			return view1;
			}
}
