package com.tts168.autoset.view;


import com.tts168.autoset.activity.MyBaseActivity;

import android.app.Activity;
import android.view.View.OnClickListener;



/**
 * 依赖于MyBaseActivity的View基类
 * 添加前请确认Layout已经添加到当前的Activity
 * 对部分常用模板进行界面控件的初始化
 * [FindView或SetListener可直接调用activity]
 * 
 * @author 袁剑
 *
 */
public abstract class MyBaseActivityView implements OnClickListener{
	protected Activity activity;
	public MyBaseActivityView(Activity activity){
		this.activity=activity;
		initView();
	}

	
	/**
	 * 初始化界面
	 */
	public void initView() {

		
		staticFindView();
		staticListener();
		FindMyView();
		FindMyListener();
	}
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
}
