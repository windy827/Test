package com.tts168.autoset.tools.commen;

import java.util.Stack;

import android.app.Activity;

/**
 * Activity 的管理栈
 * 
 * @author 袁剑
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

	// 退出栈顶
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

	// 退出当前的Activity
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

	// 获得当前栈顶
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

	// 所有Activity的总数目
	public int getActivityTotal() {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		return activityStack.size();
	}

	// 当前Activity推入栈中
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		activityStack.add(activity);
	}

	// 退出所有Activity除了当前的Class
	public void popAllActivityExceptOne() {
		if (activityStack == null) {
			activityStack = new Stack();
		}
		for (int i = 0; i < activityStack.size() - 1; i++) {
			Activity activity = (Activity) activityStack.get(i);
			popActivity(activity);
		}
	}

	// 退出所有Activity
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