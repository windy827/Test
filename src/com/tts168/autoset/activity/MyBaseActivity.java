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
 * �����õ���Activity�Ļ���
 * 
 * @author Yuanjian 20141016
 * 
 */
public abstract class MyBaseActivity extends Activity {

	//public  MyBaseActivity instatance;
	public VelocityTracker velocityTracker;// ���ڵõ���������Ļ�ϵĻ����ٶ�
	public static final int VELOCITY = 600;
	public static final int XmodelY=5;//��X���ٶ���Y���ٶȵ�5��ʱ��Ĭ�����һ���
	
	
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
		Log.d("ACTIVITYTOTAL","onDestroyǰ "+MyActivityManager.getScreenManager().getActivityTotal());
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
		Log.d("ACTIVITYTOTAL","onDestroy�� "+MyActivityManager.getScreenManager().getActivityTotal());
		super.onDestroy();
		
	}
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	Log.d("MYBASEACTIVITYRECYCLE","onStop");
	super.onStop();
}
	/**
	 * ��ʼ������
	 */
	public void initView() {
		
		
		
		Tools.CurrentActivityName = getActivityName();
		/**
		 * setContentView�����ڳ�ʼ���ؼ���ǰ��
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
	 * ���һ���
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:

			if (velocityTracker == null) {

				velocityTracker = VelocityTracker.obtain();// ȡ����������Ļ�ϵĻ����ٶ�

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
				velocityTracker.computeCurrentVelocity(1000);// ����ÿ�뻬�����ٸ�����
				velocityX = (int) velocityTracker.getXVelocity();// ���������X�ٶ�
				velocityY=(int) velocityTracker.getYVelocity();
				Log.d("velocityTracker",velocityX+"******"+velocityY);
			}
			if (velocityX > VELOCITY&&Math.abs(velocityX)>XmodelY*Math.abs(velocityY)) {
				//�������һ���

				leftToRight();

			} else if (velocityX < -VELOCITY&&Math.abs(velocityX)>XmodelY*Math.abs(velocityY)) {
				// �������󻬶�
				rightToLeft();
			}

			if (velocityTracker != null) {
				velocityTracker.recycle();// ����
				velocityTracker = null;
			}
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	/**
	 * �������󻬶�
	 */
	public abstract void rightToLeft();

	/**
	 * �������һ���
	 */
	public abstract void leftToRight() ;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		MyApplication.getInstance().setCur_Activity(this);//��ܹؼ����ر��ǵ�����������������Tools.c��
		//instatance = this;
		// ��������onResume()
		getDataRefresh();//getDataRefresh();�������Tools.CurrentActivityName = getActivityName()֮ǰ����ˢ�µ��ж���Ч
		
		Tools.CurrentActivityName = getActivityName();		
		if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
			//�ն��б����ÿ5�뷢��һ��.
			UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_TERMINAL;
		}else if(Tools.CurrentActivityName.equals(WifiSetWaitingActivity.ActivityName)||
				Tools.CurrentActivityName.equals(NotConnectedActivity.ActivityName)||
				Tools.CurrentActivityName.equals(ConnectWifiActiivity.ActivityName)){
			//���ܽ���ɨ����� �Ľ���ÿ2�뷢��һ��
			UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_SEARCH;
		}
		else if(Tools.CurrentActivityName.equals(SearchDevicesActivity.ActivityName)){
			//�����Ʊ�����1�뷢��һ��
			UDPTools.Time_Send=UDPTools.TIME_SEND_SLEEP_SEARCH;
		}
		else{
			//��������5�뷢��һ��
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
		// ȡ����Ļ������onPause()
//		if(Tools.wakeLock!=null){
//			Tools.wakeLock.release();
//		}
		//instatance = this;
		MyApplication.getInstance().setCur_Activity(this);
		Tools.CurrentActivityName = getActivityName();
		super.onPause();
	}
	/**
	 * ListView�Ȼ����ؼ��Ļ�����������
	 */
	protected abstract void applyScrollListener();
	/**
	 * ���ݸ���
	 */
	protected abstract void getDataRefresh();

	/**
	 * �ҵ��̶��Ŀؼ���ID
	 */
	public abstract void staticFindView();

	/**
	 * ���ù̶��Ŀؼ��ļ���
	 */
	public abstract void staticListener();

	/**
	 * �����Լ����еĿؼ�ID
	 */
	public abstract void FindMyView();

	/**
	 * �����Լ����е�Listener������
	 */
	public abstract void FindMyListener();

	/**
	 * �õ�����������
	 * 
	 * @return
	 */
	public  abstract String getActivityName();

	/**
	 * �õ�������ContentView ID
	 * 
	 * @return
	 */
	public abstract int getContentViewID();

	
	
	/**
	 * ������������ұߵ�View�ؼ����õķ���
	 * 
	 * @return
	 */
	public abstract void rightViewOption();
}
