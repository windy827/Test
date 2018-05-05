package com.tts168.autoset.activity.alart.addalart;

import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.view.alart.AwakeAlartView;

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
 * @author yuanjian 
 * @desc alarms列表
 */
public class AwakeFragment extends Fragment {

	private Activity mActivity;
	AwakeAlartView awakeAlartView;//闹铃设置主要部分
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		System.out.println("AppsFragment :: onCreate");
		//Activity
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("AppsFragment :: onCreateView...");
		awakeAlartView=new AwakeAlartView(mActivity);
		awakeAlartView.setIsAdd(true, null,0);
		View view =awakeAlartView.getView();
		return view;
	}
}
