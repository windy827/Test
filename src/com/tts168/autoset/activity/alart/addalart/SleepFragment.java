package com.tts168.autoset.activity.alart.addalart;

import com.tts168.autoset.view.alart.AwakeAlartView;
import com.tts168.autoset.view.alart.SleepAlartView;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/***
 * @author huangsm
 * @date 2013-1-4
 * @email huangsanm@gmail.com
 * @desc apps¡–±Ì
 */
public class SleepFragment extends Fragment {

	private Activity mActivity;
	SleepAlartView sleepAlartView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		System.out.println("AppsFragment :: onCreate");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("AppsFragment :: onCreateView...");
		
		sleepAlartView=new SleepAlartView(mActivity);
		sleepAlartView.setIsAdd(true, null,0);
		View view =sleepAlartView.getView();
	
		return view;
	}
}
