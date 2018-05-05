package com.tts168.autoset.adapter.answerrecord;

import java.util.ArrayList;
import java.util.HashMap;

import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.remind.RemindEntity;
import com.autoset.json.AutoSetParsorTools;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.alart.AlartActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.answer.AnswerTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 问答记录适配器
 * 
 * @author 袁剑
 * 
 */
public class AnswerRecordListViewAdapter extends BaseAdapter {

	private ArrayList<HashMap<String, Object>> info;// 取得到的这次阅览的文字信息，包括设备名称
	AnswerRecordListViewAdapter instatnce;
	LayoutInflater lf;
	private Context c;

	SparseArray< View> map = new SparseArray<View>();

	/**
	 * 
	 * @param a
	 * @param c
	 */
	public AnswerRecordListViewAdapter(Context c,
			ArrayList<HashMap<String, Object>> info) {
		this.c = c;
		// this.currentListView=lv;
		instatnce = this;
		this.info = info;
		lf = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public class MyView {
		private RelativeLayout rl_item;//
		private TextView tv_name;// 设备名称
		private TextView tv_date;// 日期
		private TextView tv_question;// 问题
		private TextView tv_reply;// 返回的内容
		private TextView tv_help_title;// 帮助标题
		private TextView tv_help;// 帮助
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (this.info==null?0:this.info.size());
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return info.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyView mv;
		if (map.get(position) == null) {
			mv = new MyView();
			convertView = lf.inflate(R.layout.listitem_answerrecord, null);
			// if(position%2==0){
			// convertView.setBackgroundColor(Color.argb(17, 0, 0, 0));
			// }
			mv.rl_item = (RelativeLayout) convertView
					.findViewById(R.id.rl_listitem_answerrecord);
			mv.tv_name = (TextView) convertView
					.findViewById(R.id.tv_listitem_answer_deviceName);
			mv.tv_date = (TextView) convertView
					.findViewById(R.id.tv_listitem_answerhelper_date);
			mv.tv_question = (TextView) convertView
					.findViewById(R.id.tv_listitem_answerhelper_question);
			mv.tv_reply = (TextView) convertView
					.findViewById(R.id.tv_listitem_answerhelper_reply);
			mv.tv_help = (TextView) convertView
					.findViewById(R.id.tv_listitem_answerhelper_help);
			mv.tv_help_title = (TextView) convertView
					.findViewById(R.id.tv_listitem_answerhelp_title_help);

			String name = (String) info.get(position).get(
					AnswerTools.KEY_NICKNAME);
			mv.tv_name.setText(name);
			AnswerHelperEntity entity = (AnswerHelperEntity) info.get(position)
					.get(AnswerTools.KEY_ENTITY);
			mv.tv_date.setText(entity.getDate());
			mv.tv_question.setText(entity.getQuestion());
			mv.tv_reply.setText(entity.getReply());
			mv.tv_help.setText(entity.getHelp());
			if (entity.getQuestion().equals(
					AutoSetParsorTools.RETURN_NULL_String_ERR)||entity.getQuestion().length()==0) {
				mv.tv_question.setText("未能识别");
				
			}
			if (entity.getHelp().equals(
					AutoSetParsorTools.RETURN_NULL_String_ERR)||entity.getHelp().length()==0) {
				mv.tv_help.setVisibility(View.GONE);
				mv.tv_help_title.setVisibility(View.GONE);
			}
			map.put(position, convertView);
			convertView.setTag(mv);
		} else {
			convertView = map.get(position);
			mv = (MyView) convertView.getTag();

		}
		// mv.rl_item.setOnLongClickListener(new
		// AnswerRecoredOnClickListener(info.get(position)));
		return convertView;
	}

	public class AnswerRecoredOnClickListener implements OnLongClickListener {
		HashMap<String, Object> iteminfo;

		public AnswerRecoredOnClickListener(HashMap<String, Object> iteminfo) {
			this.iteminfo = iteminfo;
		}

		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.rl_listitem_answerrecord:
				// 删除操作
				AnswerHelperEntity entity = (AnswerHelperEntity) iteminfo
						.get(AnswerTools.KEY_ENTITY);
				int id = entity.getId();
				AnswerTools.deleteAlartDialog(MyApplication.getInstance().getCur_Activity(), id);
				break;
			}
			return false;
		}

	}

}
