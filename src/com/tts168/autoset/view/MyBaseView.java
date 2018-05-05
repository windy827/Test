package com.tts168.autoset.view;


import com.tts168.autoset.activity.MyBaseActivity;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;



/**
 * 依赖于View的View基类
 * 对部分常用模板进行界面控件的初始化
 * 
 * 
 * @author 袁剑
 *
 */
public abstract class MyBaseView implements OnClickListener{
	protected View view;
	protected Activity activity;
	public MyBaseView(View view){
		
		this.view=view;
		staticFindViewByView();
		staticByViewListener();
	}
	public MyBaseView(Activity activity){
		this.activity=activity;
		staticFindViewByActivity();
		staticByActivityListener();
	}
	/**
	 * 返回当前的View
	 * @return
	 */
	public  View getView(){
		return view;
	}
	/**
	 * 数据更新
	 */
	protected abstract void getDataRefresh();

	/**
	 *  通过View找到固定的控件的ID
	 */
	public abstract void staticFindViewByView();

	/**
	 * 通过View设置控件的监听
	 */
	public abstract void staticByViewListener();

	/**
	 *通过Activity设置的控件ID
	 */
	public abstract void staticFindViewByActivity();

	/**
	 * 通过Activity设置自己的Listener监听器
	 */
	public abstract void staticByActivityListener();
}
