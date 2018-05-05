package com.tts168.autoset.tools.notifydialog;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.others.TimeSetContent;
/**
 * 生成下拉列表的View
 * @author 袁剑
 *
 */
public class ChooseListDialogView {
	Context context;
	LayoutInflater inflater;
	public ChooseListDialogView(Context context){
		this.context=context;
		this.inflater=LayoutInflater.from(context);
	}
	/**
	 * 得到一个ArrayList<HashMap<String, String>>的设置数组作为简单适配器上的内容
	 */
	public  ArrayList<HashMap<String, String>> getListForSimpleAdapter(Context context,String[]date) {
		ArrayList<HashMap<String, String>> adpter_content = new ArrayList<HashMap<String, String>>();
		
		int len = date.length;
		for (int i = 0; i < len; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put(Tools.SET_WHOLE_KEY, date[i]);
			adpter_content.add(temp);
		}
		return adpter_content;

	}
	/**
	 * 列表的内容
	 * @param inflater
	 * @param bt
	 * @param date
	 * @return
	 */
	public  View commentChooseview(final Button bt,String[]date) {

		ArrayList<HashMap<String, String>> al_list = getListForSimpleAdapter(this.context,date);

		View view1 = this.inflater.inflate(R.layout.choose_custom_view, null);
		ListView lv_choose = (ListView) view1.findViewById(R.id.lv_choose);
		String[] from = new String[] { Tools.SET_WHOLE_KEY };
		int[] to = new int[] { R.id.tv_select };
		final ArrayList<HashMap<String, String>> al = al_list;
		SimpleAdapter sa = new SimpleAdapter(this.context, al,
				R.layout.choose_dialogselect_item, from, to);
		lv_choose.setAdapter(sa);
		lv_choose.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String text = al.get(arg2).get(Tools.SET_WHOLE_KEY);
				bt.setText(text);
				if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
					NotifyDialog.dialogBuilder.dismiss();
					NotifyDialog.dialogBuilder=null;
				}
			}

		});

		return view1;
	}
}
