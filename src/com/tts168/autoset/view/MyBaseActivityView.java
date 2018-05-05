package com.tts168.autoset.view;


import com.tts168.autoset.activity.MyBaseActivity;

import android.app.Activity;
import android.view.View.OnClickListener;



/**
 * ������MyBaseActivity��View����
 * ���ǰ��ȷ��Layout�Ѿ���ӵ���ǰ��Activity
 * �Բ��ֳ���ģ����н���ؼ��ĳ�ʼ��
 * [FindView��SetListener��ֱ�ӵ���activity]
 * 
 * @author Ԭ��
 *
 */
public abstract class MyBaseActivityView implements OnClickListener{
	protected Activity activity;
	public MyBaseActivityView(Activity activity){
		this.activity=activity;
		initView();
	}

	
	/**
	 * ��ʼ������
	 */
	public void initView() {

		
		staticFindView();
		staticListener();
		FindMyView();
		FindMyListener();
	}
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
}
