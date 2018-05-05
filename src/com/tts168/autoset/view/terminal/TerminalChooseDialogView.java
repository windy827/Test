package com.tts168.autoset.view.terminal;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
import com.niftydialogeffects.NiftyDialogBuilderChoose;
import com.tools.sortlistview.ClearEditText;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.database.terminal.DB_Terminal_Option;
import com.tts168.autoset.tools.SendDataTools;
import com.tts168.autoset.tools.Tools;

import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.highcset.cityset.ProviceAndCityResource;
import com.tts168.autoset.tools.highcset.presskey.LaughContent;
import com.tts168.autoset.tools.highcset.terminal.TerminalNotifyDialog;
import com.tts168.autoset.tools.others.TimeSetContent;
import com.tts168.autoset.tools.others.time.TimeContent;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.watcher.MaxLengthWatcher;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TerminalChooseDialogView {
	
public  View view1;
public  ListView lv_choose;
public static ArrayList<HashMap<String, String>> al;
public ClearEditText et_title;//编辑标题
TextView tv_watcher;//总字数显示




public  View editNameChooseview(LayoutInflater inflater,final String editName) {
	
	 view1=inflater.inflate(R.layout.notify_terminal_editname, null); 
	 et_title=(ClearEditText) view1.findViewById(R.id.et_view_model_edittext_content);
		tv_watcher= (TextView) view1.findViewById(R.id.tv_view_model_wordtotal);
		et_title.setText(editName);
		
			et_title.setHint("请输入昵称,不超过10个字");
		
		et_title.setOnEditorActionListener(new OnEditorActionListener() { 
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) { 
			return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
			}
			});
		et_title.addTextChangedListener(new MaxLengthWatcher(
				10, et_title, tv_watcher));
	 
	 /**
		 * 设置光标到字符串尾部
		 */
		et_title.setSelection(et_title.getText().length());

	return view1;
	}



/**
 *  分组列表
 * @param inflater
 * @param macIP 设备的Mac地址
 * @param childName 设备的昵称
 * @return
 */


		
		public   View groupChooseview(LayoutInflater inflater,final String macIP,final String childName) {
			
			 view1=inflater.inflate(R.layout.choose_custom_view, null); 
			 lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
			String []from=new String[]{"name"};
			int []to=new int[]{R.id.tv_select};
			  al=new ArrayList<HashMap<String,String>>();
			  al=DB_Terminal_Option.getGroupName(MyApplication.getInstance().getCur_Activity());			
			SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al, R.layout.choose_dialogselect_item, from, to);
		lv_choose.setAdapter(sa);
		lv_choose.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String groupName=al.get(arg2).get("name").toString();
					DB_Terminal_Option.changeGroupName(MyApplication.getInstance().getCur_Activity(), groupName,macIP,childName);
					((TerminalActivity)MyApplication.getInstance().getCur_Activity()).refresh();      	
				TerminalNotifyDialog.dialogBuilderChoose.dismiss();
				TerminalNotifyDialog.dialogBuilderChoose=null;
			}
			
		});
	
			return view1;
			}
		
		
	
}
