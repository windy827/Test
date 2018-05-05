package com.tts168.autoset.activity.mainmenu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.http.configAndupgrade.UpgradeEntity;
import com.autoset.jni.presskey.PressKeyEntity;
import com.autoset.jni.remind.RemindEntity;
import com.autoset.jni.wakeup.WakeUpEntity;
import com.larkiv.larksmart7618.R;
import com.larkiv.larksmart7618.R.color;
import com.slidingmenu.lib.SlidingMenu;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.about.AboutUsActivity;
import com.tts168.autoset.activity.alart.AlartActivity;
import com.tts168.autoset.activity.answerhelper.AnswerRecordActivity;
import com.tts168.autoset.activity.answerhelper.HelpOfAnswerHelperActivity;
import com.tts168.autoset.activity.highset.FuncSetActivity;
import com.tts168.autoset.activity.localmusic.LocalMusicActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalInfoActivity;
import com.tts168.autoset.activity.quickset.WifiSetActivity;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.adapter.FuncSetGridViewAdapter;
import com.tts168.autoset.adapter.MainMenuGridViewAdapter;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.highcset.individuation.IndividuationTools;
import com.tts168.autoset.tools.mainmenu.MainMenuTools;

import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.update.DownloadService.DownloadBinder;
import com.tts168.autoset.view.player.PlayerView;
import com.tts168.autoset.view.sidemenu.SideMenuView;
import com.tts168.autoset.view.title.TitleView;
/**
 * 主菜单界面
 * @author 袁剑
 *2015-01-15    20:59
 */
public class MainMenuActivity extends MyBaseActivity implements OnClickListener, OnItemClickListener{
	TitleView titleView;//标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE="主菜单"; 
	private SharedPreferences shared;
	public static SlidingMenu menu =null;
	private DownloadBinder binder;
	private boolean isBinded;
	public SideMenuView sideMenuView;
	GridView gv;
	/**
	 * //创建音乐播放列表界面
	 */
	public PlayerView pv;
	public static final String ActivityName="MainMenuActivity";
	public static final String ERR_SERVER="服务器异常,原因:";
	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}
	
	
	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if(!Tools.CurrentActivityName.equals(ActivityName)){
			pv.setPlayerContent(Tools.answerHelperEntity);
			//MyApplication.asyncBitmapLoader.initExecutorService();
			sideMenuView.setProductNameAndPic(Tools.Current_DeviceName, Tools.Current_DeviceBitmapURL);
		}
		
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		shared=new SharedConfig(this).GetConfig(); 	// 得到配置文件
		View view=LayoutInflater.from((Activity)MyApplication.getInstance().getCur_Activity()).inflate(R.layout.sidemenu_mainmenu, null);
		sideMenuView=new SideMenuView(view);
		//sideMenuView.setProductNameAndPic(Tools.Current_DeviceName, Tools.Current_DeviceBitmapURL);
		menu=new SlidingMenu(this);
		
		menu.setMode(SlidingMenu.LEFT_OF);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setAnimationCacheEnabled(true);
        menu.setAlwaysDrawnWithCacheEnabled(true);
        menu.setAboveOffsetRes(R.dimen.slidingmenu_offset);
        menu.setBehindWidthRes(R.dimen.slidingmenu_offset);
      //  menu.set
        //menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //menu.clearAnimation();
        menu.setFadeDegree(0.35f);
        menu.setBackgroundColor(color.gray33_color);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(view);
		// 标题栏
		titleView = new TitleView(this);
		titleView.setTitle(TITLE);
		
		titleView.setRightDrawable(R.drawable.finddevice_selector);
		titleView.setMenuImgViewVisable();
		titleView.setBackImgViewInVisable();
				
		gv=(GridView) findViewById(R.id.gv_funcset);
		
		ArrayList<HashMap<String,Object>>gv_content=MainMenuTools.getShowMainMenuInfo();
		MainMenuGridViewAdapter fa=new MainMenuGridViewAdapter(this, gv_content);
		gv.setAdapter(fa);
		gv.setOnItemClickListener(this);
		
		//创建音乐播放列表界面
		pv=new PlayerView(this);
		pv.setPlayerContent(Tools.answerHelperEntity);
		
		//MyApplication.asyncBitmapLoader.initExecutorService();
		sideMenuView.setProductNameAndPic(Tools.Current_DeviceName, Tools.Current_DeviceBitmapURL);
		
			
		
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
		return R.layout.activity_mainmenu;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
	}
	
	}


	public static void setMenu(){
		menu.toggle();
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(arg0, PreventViolence.LONG_TIME);//防暴力点击
		switch(arg2){
		case MainMenuTools.MYDEVICE:
			//我的设备
			
			ActivitySetting.startUnfinishedActivity(this, TerminalActivity.class, null,false);
			
			break;

//		case MainMenuTools.TICKLER:
//			//云宝闹铃
//			ActivitySetting.startUnfinishedActivity(this, AlartActivity.class, null,false);
//			break;
		case MainMenuTools.ANOUTUS:
			//进入关于我们
			ActivitySetting.startUnfinishedActivity(this, AboutUsActivity.class, null,false);
			break;


		case MainMenuTools.HIGH_SET:
			//高级设置【暂时】
			ActivitySetting.startUnfinishedActivity(this, FuncSetActivity.class, null,false);
			break;
//		case MainMenuTools.AnswerHelper:
//			//问答助手
//			boolean isvisable11=shared.getBoolean(SharedConfig.KEY_HELP_ANSWER_VISIABLE, SharedConfig.DEFAULT_HELP_ANSWER_VISIABLE);
//			if(isvisable11){
//				ActivitySetting.startUnfinishedActivity(this, HelpOfAnswerHelperActivity.class, null,false);
//			}else{
//				ActivitySetting.startUnfinishedActivity(this, AnswerRecordActivity.class, null,false);
//			}
//			break;
		case MainMenuTools.LOCALMUSIC:
			//本地音乐
			//ToastTools.short_Toast(this, "本地音乐开发中...");
			ActivitySetting.startUnfinishedActivity(this, LocalMusicActivity.class, null,false);
			break;
		}
		
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		IndividuationTools.playSound(Tools.Current_SocketIP, RemindVoiceTools.AnounciatorRemindTools.FINDDEVICE);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		//NotifyDialog.ExitAppDialog(instatance,false);
		
		
			/**
			 * 相当于按Home键
			 */
			Intent i= new Intent(Intent.ACTION_MAIN); 
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			i.addCategory(Intent.CATEGORY_HOME); 
			startActivity(i); 
		
			 
		
		//super.onBackPressed();
		
		
	}

	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	//MyApplication.asyncBitmapLoader.recyleBitmaps();
	}

}
