package com.tts168.autoset.activity.answerhelper;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.adapter.alart.AlartListViewAdapter;
import com.tts168.autoset.adapter.answerrecord.AnswerRecordListViewAdapter;
import com.tts168.autoset.database.answerhelper.AnswerHelperDBOptions;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.answer.AnswerTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.view.title.TitleView;
/**
 * �ʴ�����
 * @author Ԭ��
 *
 */
public class AnswerRecordActivity extends MyBaseActivity implements
		OnClickListener {
	TitleView titleView;// ���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE = "�ʴ��¼";

	public static final String ActivityName = "AnswerRecordActivity";
	public static final int HANDLER_REFRESH_ADAPTER = 0x01;// ˢ�²���
	public  ListView lv_info;
	public  AnswerRecordListViewAdapter adapter;

	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}

	@Override
	public void rightToLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if (!Tools.CurrentActivityName.equals(getActivityName())) {
			refresh();
		}
	}

	/**
	 * �ṩ���ⲿ��ˢ�½ӿ�
	 */
	public  synchronized void refresh() {
		AnswerTools.al_AnswerInfo = AnswerHelperDBOptions.getInfo();// ȡ����
		adapter = new AnswerRecordListViewAdapter(MyApplication.getInstance().getCur_Activity(),
				AnswerTools.al_AnswerInfo);
		lv_info.setAdapter(adapter);
		lv_info.setSelection(adapter.getCount() - 1);// �Զ���������Ͷ�
	}

	public  Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_REFRESH_ADAPTER:
				refresh();
				break;
			}

		};
	};

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		titleView = new TitleView(this);
		titleView.setSaveText(Tools.TITLE_HELP);
		titleView.setTitle(TITLE);
		lv_info = (ListView) findViewById(R.id.lv_activity_answerrecord);

		refresh();
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getContentViewID() {
		// TODO Auto-generated method stub
		return R.layout.activity_answerrecord;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)) {
			if (PreventForceClick.isShowToast) {
				ToastTools.short_Toast(this, Tools.CLICK_FAILD);
				PreventForceClick.isShowToast = false;
			}

		} else {
			PreventForceClick.isShowToast = true;
			switch (v.getId()) {

			}
		}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		// ��������
		ActivitySetting.startUnfinishedActivity(this,
				HelpOfAnswerHelperActivity.class, null,false);
	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub

	}

}
