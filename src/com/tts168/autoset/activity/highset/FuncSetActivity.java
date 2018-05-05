package com.tts168.autoset.activity.highset;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.otherset.ParameterEntity;
import com.autoset.jni.presskey.PressKeyEntity;
import com.autoset.jni.sleepset.SleepSetEntity;
import com.autoset.jni.sleepset.SleepSetJsonOption;
import com.autoset.jni.undisturbed.UndisturbedEntity;
import com.autoset.jni.wakeup.WakeUpEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.highset.wakeset.WakeSetActivity;
import com.tts168.autoset.adapter.FuncSetGridViewAdapter;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.ForceTime;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.highcset.FuncSetTools;
import com.tts168.autoset.tools.mainmenu.MainMenuTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.player.PlayerView;
import com.tts168.autoset.view.title.TitleView;

/**
 * 高级设置的界面
 * 
 * @author 袁剑 2015-01-15 20：56
 */
public class FuncSetActivity extends MyBaseActivity implements OnClickListener,
		OnItemClickListener {
	public static final String TITLE = "高级设置";
	TitleView titleView;// 标题栏，如果想相应点击事件，需要在titleSave方法里面去实现

	public static boolean isCityClick = false;// 判断是否点击了城市设置

	GridView gv;
	public static final String ActivityName = "FuncSetActivity";
	public  WaitView waitView;// 等待数据


	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		// 标题栏
		titleView = new TitleView(this);
		titleView.setTitle(TITLE);

		gv = (GridView) findViewById(R.id.gv_funcset);

		ArrayList<HashMap<String, Object>> gv_content = FuncSetTools
				.getShowFuncSetInfo();
		FuncSetGridViewAdapter fa = new FuncSetGridViewAdapter(this, gv_content);
		gv.setAdapter(fa);
		gv.setOnItemClickListener(this);

		waitView = new WaitView(this);
		
	
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
		return R.layout.activity_funcset;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		}

	}

	@Override
	public void rightToLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub

	}

	/******************************************
	 * *******************************************
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		PreventViolence.preventClick(arg0, PreventViolence.LONG_TIME);// 防暴力点击
		switch (arg2) {
		case FuncSetTools.NIGHTCONTROL:
			// 免扰控制
			waitView.setPBVisable();
			ArrayList<String> domainNames = new ArrayList<String>();
			domainNames.add(UndisturbedEntity.DOMAIN_NAME);
			TCPTools.sendTcpByDomain(domainNames, Tools.Current_SocketIP);
			break;
//		case FuncSetTools.LOCALCITY:
//			// General请求得到城市
//			waitView.setPBVisable();
//			isCityClick = true;
//			ArrayList<String> domainNames1 = new ArrayList<String>();
//			domainNames1.add(GeneralEntity.DOMAIN_NAME);
//			TCPTools.sendTcpByDomain(domainNames1, Tools.Current_SocketIP);
//
//			break;
		case FuncSetTools.PERSONAL_AWAKESET:

			// 唤醒控制
			waitView.setPBVisable();
			ArrayList<String> domainNames2 = new ArrayList<String>();
			domainNames2.add(WakeUpEntity.DOMAIN_NAME);
			TCPTools.sendTcpByDomain(domainNames2, Tools.Current_SocketIP);

			break;
//		case FuncSetTools.SLEEPSET:
//			// 其它设置
//			waitView.setPBVisable();
//			ArrayList<String> domainNames4 = new ArrayList<String>();
//			domainNames4.add(SleepSetEntity.DOMAIN_NAME);
//			TCPTools.sendTcpByDomain(domainNames4, Tools.Current_SocketIP);
//			// ActivitySetting.startUnfinishedActivity(this,
//			// SaveErnegerActivity.class, null);
//			break;
//		case FuncSetTools.OTHERSET:
//			// 其它设置
//			waitView.setPBVisable();
//			ArrayList<String> domainNames3 = new ArrayList<String>();
//			domainNames3.add(ParameterEntity.DOMAIN_NAME);
//			TCPTools.sendTcpByDomain(domainNames3, Tools.Current_SocketIP);
//			// ActivitySetting.startUnfinishedActivity(this,
//			// SaveErnegerActivity.class, null);
//			break;

		// case FuncSetTools.SAVEERNERGER:
		// //节能控制
		// waitView.setPBVisable();
		// ActivitySetting.startUnfinishedActivity(this,
		// SaveErnegerActivity.class, null);
		// break;
		//
		//
		// case FuncSetTools.INDIVIDUATION:
		// //个性化设置
		// waitView.setPBVisable();
		// ActivitySetting.startUnfinishedActivity(this,
		// IndividuationActivity.class, null);
		// break;
		}

	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub

	}

}
