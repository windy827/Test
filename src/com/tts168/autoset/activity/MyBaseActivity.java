package com.tts168.autoset.activity;



import com.larkiv.larksmart7618.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.slidingmenu.lib.SlidingMenu;
import com.tts168.autoset.activity.quickset.ConnectWifiActiivity;
import com.tts168.autoset.activity.quickset.WifiSetWaitingActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyActivityManager;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.tcpAndudp.UDPTools;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

/**
 * 所有用到的Activity的基类
 * 
 * @author Yuanjian 20141016
 * 
 */
public abstract class MyBaseActivity extends Activity {

	//public  MyBaseActivity instatance;
	public VelocityTracker velocityTracker;// 用于得到手势在屏幕上的滑动速度
	public static final int VELOCITY = 600;
	public static final int XmodelY=5;//当X的速度是Y的速度的5倍时方默认左右滑动
	
	
	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//instatance = this;
		MyApplication.getInstance().setCur_Activity(this);
		Log.d("ACTIVITYTOTAL","ONCREAT "+MyActivityManager.getScreenManager().getActivityTotal());
		initView();
		MyActivityManager.getScreenManager().pushActivity(this);
		
	}
	
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		ImageLoader.getInstance().clearMemoryCache();
		ActivitySetting.startActivity(MyApplication.getInstance().getCur_Activity(), SearchDevicesActivity.class, null, true);
		Log.d("MEMORY_APPLICATION","onLowMemory---Activity");
		super.onLowMemory();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		this.finish();
		Log.d("MYBASEACTIVITYRECYCLE","onBackPressed");
		super.onBackPressed();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.d("ACTIVITYTOTAL","onDestroy前 "+MyActivityManager.getScreenManager().getActivityTotal());
		MyActivityManager.getScreenManager().popActivity(this);
		if(AlartNotifyDialog.dialogBuilder !=null && AlartNotifyDialog.dialogBuilder.isShowing()) {
			AlartNotifyDialog.dialogBuilder.dismiss();
			AlartNotifyDialog.dialogBuilder=null;
			Log.d("DIALOG_DESTORY","AlartNotifyDialog.dialogBuilder");
		}
		if(NotifyDialog.dialogBuilder !=null && NotifyDialog.dialogBuilder.isShowing()) {
			NotifyDialog.dialogBuilder.dismiss();
			NotifyDialog.dialogBuilder=null;
			Log.d("DIALOG_DESTORY","NotifyDialog.dialogBuilder");
		}
		if(NotifyDialog.dialogBuilderChoose !=null && NotifyDialog.dialogBuilderChoose.isShowing()) {
			NotifyDialog.dialogBuilderChoose.dismiss();
			NotifyDialog.dialogBuilderChoose=null;
			Log.d("DIALOG_DESTORY","NotifyDialog.dialogBuilderChoose");
		}
		Log.d("ACTIVITYTOTAL","onDestroy后 "+MyActivityManager.getScreenManager().getActivityTotal());
		super.onDestroy();
		
	}
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	Log.d("MYBASEACTIVITYRECYCLE","onStop");
	super.onStop();
}
	/**
	 * 初始化界面
	 */
	public void initView() {
		
		
		
		Tools.CurrentActivityName = getActivityName();
		/**
		 * setContentView必须在初始化控件的前面
		 */
		if(getContentViewID()!=0){
			setContentView(getContentViewID());
		}
		
		staticFindView();
		staticListener();
		FindMyView();
		FindMyListener();
	}
	/**
	 * 左右滑动
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:

			if (velocityTracker == null) {

				velocityTracker = VelocityTracker.obtain();// 取得手势在屏幕上的滑动速度

				velocityTracker.addMovement(ev);

			}
			break;
		case MotionEvent.ACTION_MOVE:
			// int deltaX = (int) (lastMotionX - x);
			if (velocityTracker != null) {
				velocityTracker.addMovement(ev);
			}
			// lastMotionX = x;
			break;
		case MotionEvent.ACTION_UP:
			int velocityX = 0;
			int velocityY=0;
			if (velocityTracker != null) {
				velocityTracker.addMovement(ev);
				velocityTracker.computeCurrentVelocity(1000);// 计算每秒滑动多少个像素
				velocityX = (int) velocityTracker.getXVelocity();// 最后计算检索X速度
				velocityY=(int) velocityTracker.getYVelocity();
				Log.d("velocityTracker",velocityX+"******"+velocityY);
			}
			if (velocityX > VELOCITY&&Math.abs(velocityX)>XmodelY*Math.abs(velocityY)) {
				//从左往右滑动

				leftToRight();

			} else if (velocityX < -VELOCITY&&Math.abs(velocityX)>XmodelY*Math.abs(velocityY)) {
				// 从右往左滑动
				rightToLeft();
			}

			if (velocityTracker != null) {
				velocityTracker.recycle();// 回收
				velocityTracker = null;
			}
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	/**
	 * 从右往左滑动
	 */
	public abstract void rightToLeft();

	/**
	 * 从左往右滑动
	 */
	public abstract void leftToRight() ;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		MyApplication.getInstance().setCur_Activity(this);//这很关键，特别是当界面里面有依赖于Tools.c的
		//instatance = this;
		// 请求常亮，onResume()
		getDataRefresh();//getDataRefresh();必须放在Tools.CurrentActivityName = getActivityName()之前否则刷新的判断无效
		
		Tools.CurrentActivityName = getActivityName();		
		if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
			//终端列表界面每5秒发送一次.
			UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_TERMINAL;
		}else if(Tools.CurrentActivityName.equals(WifiSetWaitingActivity.ActivityName)||
				Tools.CurrentActivityName.equals(NotConnectedActivity.ActivityName)||
				Tools.CurrentActivityName.equals(ConnectWifiActiivity.ActivityName)){
			//可能进入扫描界面 的界面每2秒发送一次
			UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_SEARCH;
		}
		else if(Tools.CurrentActivityName.equals(SearchDevicesActivity.ActivityName)){
			//查找云宝界面1秒发送一次
			UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_SEARCH;
		}
		else{
			//其它界面5秒发送一次
			UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_OTHER;
		}
		
		Network net = Network.getInstance(null);
		if (net != null) {
			net.setUdpFrequency(UDPTools.Time_Send * 1000);
		}
		applyScrollListener();
		super.onResume();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// 取消屏幕常亮，onPause()
//		if(Tools.wakeLock!=null){
//			Tools.wakeLock.release();
//		}
		//instatance = this;
		MyApplication.getInstance().setCur_Activity(this);
		Tools.CurrentActivityName = getActivityName();
		super.onPause();
	}
	/**
	 * ListView等滑动控件的滑动监听操作
	 */
	protected abstract void applyScrollListener();
	/**
	 * 数据更新
	 */
	protected abstract void getDataRefresh();

	/**
	 * 找到固定的控件的ID
	 */
	public abstract void staticFindView();

	/**
	 * 设置固定的控件的监听
	 */
	public abstract void staticListener();

	/**
	 * 增加自己独有的控件ID
	 */
	public abstract void FindMyView();

	/**
	 * 增加自己独有的Listener监听器
	 */
	public abstract void FindMyListener();

	/**
	 * 得到这个类的名称
	 * 
	 * @return
	 */
	public  abstract String getActivityName();

	/**
	 * 得到这个类的ContentView ID
	 * 
	 * @return
	 */
	public abstract int getContentViewID();

	
	
	/**
	 * 标题栏里面的右边的View控件调用的方法
	 * 
	 * @return
	 */
	public abstract void rightViewOption();
}
