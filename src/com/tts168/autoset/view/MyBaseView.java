package com.tts168.autoset.view;


import com.tts168.autoset.activity.MyBaseActivity;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;



/**
 * ������View��View����
 * �Բ��ֳ���ģ����н���ؼ��ĳ�ʼ��
 * 
 * 
 * @author Ԭ��
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
	 * ���ص�ǰ��View
	 * @return
	 */
	public  View getView(){
		return view;
	}
	/**
	 * ���ݸ���
	 */
	protected abstract void getDataRefresh();

	/**
	 *  ͨ��View�ҵ��̶��Ŀؼ���ID
	 */
	public abstract void staticFindViewByView();

	/**
	 * ͨ��View���ÿؼ��ļ���
	 */
	public abstract void staticByViewListener();

	/**
	 *ͨ��Activity���õĿؼ�ID
	 */
	public abstract void staticFindViewByActivity();

	/**
	 * ͨ��Activity�����Լ���Listener������
	 */
	public abstract void staticByActivityListener();
}
