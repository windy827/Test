package com.tts168.autoset.activity.alart.addalart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.remind.RemindEntity;
import com.larkiv.larksmart7618.R;

import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyActivityManager;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.tcpAndudp.UDPTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.player.PlayerView;
import com.tts168.autoset.view.title.TitleView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;

/***
 * @author Ԭ��
 * @date 2015-1-4
 * @email 583129781@qq.com
 * @desc fragment Activity ���õ�˳����onAttach-->onCreate-->...-->onResume
 *       ���л�����һ��fragment��ʱ�򣬻����onPause-->onStop-->onDestroyView
 *       �л�����ʱ��onCreateView-->onActivityCreated-->onStart-->onResume
 *       Ҳ����˵onAttach ��onCreateֻ������һ�Ρ������ڽ������ݳ�ʼ����ʱ��Ӧ�ðѹ����ŵ������������н��С�
 */
public class AlartFragmentTabActivity extends FragmentActivity {

	/**
	 * ���ActivityName�ܹؼ������ж��Ƿ���Ҫˢ�µ���Ҫ����
	 */
	public static final String ActivityName="AlartFragmentTabActivity";
	public  AlartFragmentTabActivity instatance;
	
	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE="�������";
	
	private final static int TRANSLATE_ANIMATION_WIDTH = 150;
	private final static int ANIMATION_DURATION_FAST = 450;
	private final static int ANIMATION_DURATION_SLOW = 350;
	private final static int MOVE_DISTANCE = 50;

	private TabHost mTabHost;
	private TabManager mTabManager;
	private LinearLayout mSettingLinearLayout;
	private LinearLayout mMainLinearLayout;
	// ��Ļ���
	private int mWidth;
	private float mPositionX;
	// ����״̬
	private boolean mSlided = false;

	public  WaitView waitView;// �ȴ�����
	/**
	 * Tab����ͼ��
	 * @author Ԭ��
	 *
	 */
	public class IndexImageView{
		public ImageView iv_awake,iv_sleep,iv_defined,iv_remind,iv_birthday;
		public ImageView[]iv_index;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().setCur_Activity(this);
		instatance=this;
		MyActivityManager.getScreenManager().pushActivity(instatance);
		AlartTools.fragActivity=this;
		Tools.CurrentActivityName=ActivityName;
		ActivitySetting.requestNotTitleBar(this);

		setContentView(R.layout.activity_fragment_tabs);

		waitView = new WaitView(this);
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		
		mWidth = getResources().getDisplayMetrics().widthPixels;

		// �̳�tabactivity.getTabHost()����Ҫsetup()
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mTabManager = new TabManager(this, mTabHost, R.id.containertabcontent);

		RelativeLayout awakeAlart = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.fragement_awake_tab_layout, null);
		//IndexImageView.this.iv_awake=awakeAlart.findViewById(R.id.iv_)
		mTabManager.addTab(mTabHost.newTabSpec("awakeAlart").setIndicator(awakeAlart),
				AwakeFragment.class, null);

//		RelativeLayout sleepAlart = (RelativeLayout) getLayoutInflater().inflate(
//				R.layout.fragement_sleep_tab_layout, null);
//		mTabManager.addTab(mTabHost.newTabSpec("sleepAlart")
//				.setIndicator(sleepAlart), SleepFragment.class, null);

		RelativeLayout definedAlart = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.fragement_defined_tab_layout, null);
		mTabManager.addTab(mTabHost.newTabSpec("definedAlart")
				.setIndicator(definedAlart), DefinedFragment.class, null);
		
		RelativeLayout reminedAlart = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.fragement_remind_tab_layout, null);
		mTabManager.addTab(mTabHost.newTabSpec("reminedAlart")
				.setIndicator(reminedAlart), ReminedFragment.class, null);
		
		RelativeLayout birthdayAlart = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.fragement_birthday_tab_layout, null);
		mTabManager.addTab(mTabHost.newTabSpec("birthdayAlart")
				.setIndicator(birthdayAlart), BirthdayFragment.class, null);

		mMainLinearLayout = (LinearLayout) findViewById(R.id.main);
		mMainLinearLayout.setOnTouchListener(mOnTouchListener);
		


		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tag"));
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		MyApplication.getInstance().setCur_Activity(this);//��ܹؼ����ر��ǵ�����������������Tools.c��
		instatance = this;
		
		Tools.CurrentActivityName = ActivityName;		
	
			//��������5�뷢��һ��
		UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_OTHER;
		Network net = Network.getInstance(null);
		if (net != null) {
			net.setUdpFrequency(UDPTools.Time_Send * 1000);
		}
		
		super.onResume();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// ȡ����Ļ������onPause()
//		if(Tools.wakeLock!=null){
//			Tools.wakeLock.release();
//		}
		instatance = this;
		MyApplication.getInstance().setCur_Activity(this);
		Tools.CurrentActivityName = ActivityName;
		UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_OTHER;
		Network net = Network.getInstance(null);
		if (net != null) {
			net.setUdpFrequency(UDPTools.Time_Send * 1000);
		}
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyActivityManager.getScreenManager().popActivity();
		waitView=null;
		super.onDestroy();
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	super.onBackPressed();
	
}
	// ����
	private OnTouchListener mOnTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v.getId() == R.id.main) {
				int action = event.getAction();
				switch (action) {
					case MotionEvent.ACTION_DOWN :
						mPositionX = event.getX();
						break;
					case MotionEvent.ACTION_MOVE :
						final float currentX = event.getX();
						// ����߻���
						
						break;
				}
				return true;
			} 
			return false;
		}
	};

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * ����֮ǰ
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tag", mTabHost.getCurrentTabTag());
	}

	public static class TabManager implements TabHost.OnTabChangeListener {
		private final AlartFragmentTabActivity mActivity;
		// ����tab
		private final Map<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
		private final TabHost mTabHost;
		private final int mContainerID;
		private TabInfo mLastTab;

		/**
		 * @param activity context
		 * @param tabHost tab
		 * @param containerID fragment's parent note
		 */
		public TabManager(AlartFragmentTabActivity activity, TabHost tabHost,
				int containerID) {
			mActivity = activity;
			mTabHost = tabHost;
			mContainerID = containerID;
			mTabHost.setOnTabChangedListener(this);
		}

		static final class TabInfo {
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;
			private Fragment fragment;

			TabInfo(String _tag, Class<?> _clss, Bundle _args) {
				tag = _tag;
				clss = _clss;
				args = _args;
			}
		}

		static class TabFactory implements TabHost.TabContentFactory {
			private Context mContext;
			TabFactory(Context context) {
				mContext = context;
			}

			@Override
			public View createTabContent(String tag) {
				View v = new View(mContext);
				v.setMinimumHeight(0);
				v.setMinimumWidth(0);
				return v;
			}
		}

		// ����tab
		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
			tabSpec.setContent(new TabFactory(mActivity));
			String tag = tabSpec.getTag();

			TabInfo info = new TabInfo(tag, clss, args);
			final FragmentManager fm = mActivity.getSupportFragmentManager();
			info.fragment = fm.findFragmentByTag(tag);
			// isDetached����״̬
			if (info.fragment != null && !info.fragment.isDetached()) {
				FragmentTransaction ft = fm.beginTransaction();
				ft.detach(info.fragment);
				ft.commit();
			}
			mTabs.put(tag, info);
			mTabHost.addTab(tabSpec);
		}

		@Override
		public void onTabChanged(String tabId) {
			TabInfo newTab = mTabs.get(tabId);
			if (mLastTab != newTab) {
				FragmentManager fragmentManager = mActivity
						.getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				// ����֮ǰ��tab
				if (mLastTab != null && mLastTab.fragment != null) {
					fragmentTransaction.detach(mLastTab.fragment);
				}
				if (newTab != null) {
					if (newTab.fragment == null) {
						newTab.fragment = Fragment.instantiate(mActivity,
								newTab.clss.getName(), newTab.args);
						fragmentTransaction.add(mContainerID, newTab.fragment,
								newTab.tag);
					} else {
						// ����
						fragmentTransaction.attach(newTab.fragment);
					}
				}
				mLastTab = newTab;
				fragmentTransaction.commit();
				// ���ڽ��̵����߳��У����첽�ķ�ʽ��ִ��,�����Ҫ����ִ������ȴ��еĲ�������Ҫ�����������
				// ���еĻص�����ص���Ϊ��������������б�ִ����ɣ����Ҫ��ϸȷ����������ĵ���λ�á�
				fragmentManager.executePendingTransactions();
			}
		}
	}
}