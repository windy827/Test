package com.tts168.autoset.activity.alart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.remind.RemindEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.alart.addalart.AlartFragmentTabActivity;
import com.tts168.autoset.adapter.alart.AlartListViewAdapter;

import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.tcpAndudp.WifiWatchTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.title.TitleView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * �̳���MyBaseActivity��һ��ģ��
 * 
 * @author Ԭ��
 * 
 */
public class AlartActivity extends MyBaseActivity implements OnClickListener,
		OnItemClickListener {
	public static final String ActivityName = "AlartActivity";
	/**
	 * ����ˢ���߳�
	 */
	public static AtomicBoolean isUpdataThread = new AtomicBoolean(true);
	/**
	 * �Ƿ��Ѿ��õ����ݵı�־λ
	 */
	public static boolean hasData=false;
	/**
	 * ˢ������
	 */
	public static final int HANDLER_REFRESH_ADAPTER = 0x01;
	public static final int HANDLER_NOCOTENT = 0x02;// û���κ�����

	public static final String Alart_NetNoContent = "��ȡ��Ϣʧ�ܣ�ˢ��������~";
	public static final String Alart_NoContent = "��ǰû���κ�������Ϣ,�������Ӱ�~";
	TitleView titleView;// ���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE = "��������";

	private Button bt_save;// ���水ť
	private Button bt_add;// ������尴ť

	public  TextView tv_totalAlart;// �����б�ͳ��
	public  ListView lv_alart;// �����б�
	public  WaitView waitView;// �ȴ�����
	static AlartListViewAdapter adapter;// �����б��������
	RefreshThread th;

	@Override
	public void rightToLeft() {

	}

	@Override
	public void onBackPressed() {
		isUpdataThread.set(false);
		super.onBackPressed();

	}

	@Override
	public void leftToRight() {

	}

	/**
	 * �ṩ���ⲿ��ˢ�½ӿ�
	 */
	public  void refresh() {
		// ������˳���������
		waitView.setPBGone();
		if(AlartTools.alartAdapter_content.size()==0){
			tv_totalAlart.setText(Alart_NoContent);
		}else{
			tv_totalAlart.setText(MyApplication.getInstance().getCur_Activity().getResources().getString(
					R.string.alarmSet)
					+ "(" + AlartTools.alartAdapter_content.size() + ")");
		}
		AlartTools.sortAlartAdapter_content(AlartTools.alartAdapter_content);
		adapter = new AlartListViewAdapter(MyApplication.getInstance().getCur_Activity(),
				AlartTools.alartAdapter_content);
		
		lv_alart.setAdapter(adapter);
	}

	@Override
	protected void getDataRefresh() {
		if (!Tools.CurrentActivityName.equals(ActivityName)) {
			waitView.setPBVisable();
			hasData=false;
			if (isUpdataThread.compareAndSet( false ,  true )) {
				AlartTools.getAlartData();
				th = new RefreshThread();
			} else {
				AlartTools.getAlartData();
			}
			
			th.ressetData();//��֤�ܹ�����ˢ��
		}
	}

	@Override
	public void staticFindView() {
		// ����ʵ����
		Intent intent = getIntent();
		titleView = new TitleView(this);
		titleView.setSaveText(Tools.TITLE_REFRESH);
		titleView.setTitle(TITLE);

		waitView = new WaitView(this);
		waitView.setPBVisable();

		tv_totalAlart = (TextView) findViewById(R.id.tv_activity_alarttotal);
		lv_alart = (ListView) findViewById(R.id.lv_activity_alart);
		bt_add = (Button) findViewById(R.id.bt_activity_alart_add);

		AlartTools.alartAdapter_content = new ArrayList<HashMap<String, Object>>();
		AlartTools.getAlartData();
		//refresh();
		tv_totalAlart.setText(MyApplication.getInstance().getCur_Activity().getResources().getString(
				R.string.alarmSet)
				+ "(" + AlartTools.alartAdapter_content.size() + ")");
		isUpdataThread.set(true);
		th = new RefreshThread();
		// bt_save=(Button) findViewById(R.id.bt_model_save_save);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_REFRESH_ADAPTER:
				waitView.setPBGone();
				hasData=true;
				refresh();
				break;
			case HANDLER_NOCOTENT:
				// ��ʾû�л�ȡ���κ�����
				waitView.setPBGone();
				hasData=false;
				tv_totalAlart.setText(Alart_NetNoContent);
				break;
			}

		}
	};

	/**
	 * ˢ�µ��߳�[ÿ5�ٺ�����һ�Σ�������ݸı��ˢ��һ������] ɨ��ʮ��Ҳ����������Ȼ���Զ��ر������߳�
	 * 
	 * @author Administrator
	 * 
	 */
	public class RefreshThread extends Thread {
		public int leng = 0;
		public int search_time = 0;
		public static final int times = 50;// ����50��Ҳ����10����

		public RefreshThread() {
			ressetData();
			this.start();
		}

		// ��������
		public void ressetData() {
			leng = 0;
			search_time = 0;
			hasData=false;
		}

		@Override
		public void run() {
			
			super.run();
			Looper.prepare();
			while (isUpdataThread.get()) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				search_time++;
				if (leng != AlartTools.alartAdapter_content.size()) {
					Message msg = new Message();
					msg.what = HANDLER_REFRESH_ADAPTER;
					handler.sendMessage(msg);

					leng = AlartTools.alartAdapter_content.size();
					isUpdataThread.set(false);// ������ݼ�ֹͣ��ȡ
				}
				if (search_time > times&&!hasData) {
					
					isUpdataThread.set(false);
						Message msg = new Message();
						msg.what = HANDLER_NOCOTENT;
						handler.sendMessage(msg);
					
					
				}

			}
			Looper.loop();
			
		}
	}

	@Override
	public void staticListener() {
		lv_alart.setOnItemClickListener(this);
		bt_add.setOnClickListener(this);
	}

	@Override
	public void FindMyView() {

	}

	@Override
	public void FindMyListener() {

	}

	@Override
	public String getActivityName() {
		return ActivityName;
	}

	@Override
	public int getContentViewID() {
		return R.layout.activity_alart;
	}

	@Override
	public void onClick(View v) {
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//���������
			switch (v.getId()) {
			case R.id.model_title_iv_back1:
				onBackPressed();
				break;

			case R.id.bt_activity_alart_add:
				// �������
				isUpdataThread.set(false);// �˳������߳�
				ActivitySetting.startUnfinishedActivity(this,
						AlartFragmentTabActivity.class, null,false);
				break;
			
		}
	}

	@Override
	public void rightViewOption() {
		// ˢ�²��������»�ȡ���ݡ�
		PreventViolence.preventClick(titleView.tv_save, PreventViolence.LONGER_TIME);//���������
			waitView.setPBVisable();
			if (isUpdataThread.compareAndSet( false ,  true )) {
				AlartTools.getAlartData();
				th = new RefreshThread();
			} else {
				AlartTools.getAlartData();
			}
			th.ressetData();// ��֤�ܹ�����ˢ��
		

		// refresh();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		PreventViolence.preventClick(arg0, PreventViolence.SHORT_TIME);//���������
		HashMap<String, Object> content = AlartTools.alartAdapter_content
				.get(arg2);
		int type = (Integer) content
				.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
		isUpdataThread.set(false);// �˳������߳�
		int flagType = 0;// �ж������������е���һ������
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putInt(AlartTools.IntentKey.INTENT_INDEX, arg2);
		bundle.putBoolean(AlartTools.IntentKey.INTENT_ISADD, false);
		if (type == AlartTools.AlartType.JSON_ALART_TYPE_ALART) {
			// Json ����ΪAlarm

			AlarmEntity ae = (AlarmEntity) content
					.get(AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
			String title = ae.getTitle();
			bundle.putSerializable(AlartTools.IntentKey.INTENT_ENTITY_ALART, ae);
			if (title.equals(AlartTools.AlartTitle.ALART_TITLE_AWAKE)) {
				// ������

				ActivitySetting.startUnfinishedActivity(this,
						AwakeAlartEditActivity.class, bundle,false);
			} else if (title.equals(AlartTools.AlartTitle.ALART_TITLE_SLEEP)) {
				// ˯������
				flagType = AlartTools.AlartType.FLAG_ALART_TYPE_SLEEP;
				ActivitySetting.startUnfinishedActivity(this,
						SleepAlartEditActivity.class, bundle,false);
			} else {
				// �Զ�������

				flagType = AlartTools.AlartType.FLAG_ALART_TYPE_DEFINED;
				ActivitySetting.startUnfinishedActivity(this,
						DefinedAlartEditActivity.class, bundle,false);
			}
		} else if (type == AlartTools.AlartType.JSON_ALART_TYPE_REMIND) {
			// Json ����Ϊ����
			RemindEntity re = (RemindEntity) content
					.get(AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
			flagType = AlartTools.AlartType.FLAG_ALART_TYPE_REMIND;
			bundle.putSerializable(AlartTools.IntentKey.INTENT_ENTITY_REMIND,
					re);
			ActivitySetting.startUnfinishedActivity(this,
					RemindAlartActivity.class, bundle,false);
		} else if (type == AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY) {
			// Json ����Ϊ����
			flagType = AlartTools.AlartType.FLAG_ALART_TYPE_BIRTHDAY;
			BirthDayEntity be = (BirthDayEntity) content
					.get(AlartTools.AlartListViewAdapterTools.KEY_ENTITY);

			bundle.putSerializable(AlartTools.IntentKey.INTENT_ENTITY_BIRTHDAY,
					be);
			ActivitySetting.startUnfinishedActivity(this,
					BirthdayAlartActivity.class, bundle,false);

		}
	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
