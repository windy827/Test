package com.tts168.autoset.tools.commen;

import java.util.Stack;

import android.app.Activity;

/**
 * Activity �Ĺ���ջ
 * 
 * @author Ԭ��
 * 
 */
public class MyActivityManager {

	private static Stack activityStack;
	private static MyActivityManager instance;

	private MyActivityManager() {

	}

	public static MyActivityManager getScreenManager() {
		if (instance == null) {
			instance = new MyActivityManager();
		}
		return instance;
	}

	// �˳�ջ��
	public void popActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	// �˳���ǰ��Activity
	public void popActivity() {
		Activity activity = (Activity) activityStack.lastElement();
		if (activityStack == null) {
			activityStack = new Stack();
		}
		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	// ��õ�ǰջ��
	public Activity currentActivity() {
		Activity activity = null;
		if (activityStack == null) {
			activityStack = new Stack();
		}
		if (!activityStack.empty()) {
			activity = (Activity) activityStack.lastElement();
		}
		return activity;
	}

	// ����Activity������Ŀ
	public int getActivityTotal() {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		return activityStack.size();
	}

	// ��ǰActivity����ջ��
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		activityStack.add(activity);
	}

	// �˳�����Activity���˵�ǰ��Class
	public void popAllActivityExceptOne() {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		for (int i = 0; i < activityStack.size() - 1; i++) {
			Activity activity = (Activity) activityStack.get(i);
			popActivity(activity);
		}
	}

	// �˳�����Activity
	public void popAllActivity() {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		for (int i = 0; i < activityStack.size(); i++) {
			Activity activity = (Activity) activityStack.get(i);
			popActivity(activity);
		}
	}
}