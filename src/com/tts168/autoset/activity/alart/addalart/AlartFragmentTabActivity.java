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
 * @author 袁剑
 * @date 2015-1-4
 * @email 583129781@qq.com
 * @desc fragment Activity 调用的顺序是onAttach-->onCreate-->...-->onResume
 *       当切换到另一个fragment的时候，会调用onPause-->onStop-->onDestroyView
 *       切换回来时，onCreateView-->onActivityCreated-->onStart-->onResume
 *       也就是说onAttach 和onCreate只调用了一次。所以在进行数据初始化的时候应该把工作放到这两个方法中进行。
 */
public class AlartFragmentTabActivity extends FragmentActivity {

	/**
	 * 这个ActivityName很关键，是判断是否需要刷新的重要依据
	 */
	public static final String ActivityName="AlartFragmentTabActivity";
	public  AlartFragmentTabActivity instatance;
	
	TitleView titleView;//标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE="添加闹铃";
	
	private final static int TRANSLATE_ANIMATION_WIDTH = 150;
	private final static int ANIMATION_DURATION_FAST = 450;
	private final static int ANIMATION_DURATION_SLOW = 350;
	private final static int MOVE_DISTANCE = 50;

	private TabHost mTabHost;
	private TabManager mTabManager;
	private LinearLayout mSettingLinearLayout;
	private LinearLayout mMainLinearLayout;
	// 屏幕宽度
	private int mWidth;
	private float mPositionX;
	// 滑动状态
	private boolean mSlided = false;

	public  WaitView waitView;// 等待数据
	/**
	 * Tab索引图标
	 * @author 袁剑
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

		// 继承tabactivity.getTabHost()不需要setup()
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
		MyApplication.getInstance().setCur_Activity(this);//这很关键，特别是当界面里面有依赖于Tools.c的
		instatance = this;
		
		Tools.CurrentActivityName = ActivityName;		
	
			//其它界面5秒发送一次
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
		// 取消屏幕常亮，onPause()
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
	// 滑动
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
						// 向左边滑动
						
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
	 * 销毁之前
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tag", mTabHost.getCurrentTabTag());
	}

	public static class TabManager implements TabHost.OnTabChangeListener {
		private final AlartFragmentTabActivity mActivity;
		// 保存tab
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

		// 加入tab
		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
			tabSpec.setContent(new TabFactory(mActivity));
			String tag = tabSpec.getTag();

			TabInfo info = new TabInfo(tag, clss, args);
			final FragmentManager fm = mActivity.getSupportFragmentManager();
			info.fragment = fm.findFragmentByTag(tag);
			// isDetached分离状态
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
				// 脱离之前的tab
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
						// 激活
						fragmentTransaction.attach(newTab.fragment);
					}
				}
				mLastTab = newTab;
				fragmentTransaction.commit();
				// 会在进程的主线程中，用异步的方式来执行,如果想要立即执行这个等待中的操作，就要调用这个方法
				// 所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
				fragmentManager.executePendingTransactions();
			}
		}
	}
}