package com.tts168.autoset.activity.quickset;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.view.title.TitleView;
/**
 * WIFI灯未闪烁
 * @author 袁剑
 *
 */
public class NoLightFlashActivity  extends MyBaseActivity implements OnClickListener{
	TitleView titleView;//标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE="WIFI灯未闪烁？";
	
	public static final String ActivityName="NoLightFlashActivity";
	
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
		if(!Tools.CurrentActivityName.equals(getActivityName())){
			
			//MyApplication.asyncBitmapLoader.initExecutorService();[用到异步加载的地方需要使用]
			
			refresh();
		}
	}
	/**
	 * 提供给外部的刷新接口
	 */
		public static void refresh(){
		
		}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub	
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);		
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
		return R.layout.activity_nolightflash;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//防暴力点击
				switch(v.getId()){
			
				}
		
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//保存操作
		ToastTools.short_Toast((Activity)MyApplication.getInstance().getCur_Activity(), "保存成功!!");
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}

