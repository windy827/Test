package com.tts168.autoset.activity.alart.addalart;


import com.tts168.autoset.view.alart.AwakeAlartView;
import com.tts168.autoset.view.alart.DefinedAlartView;

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
public class DefinedFragment extends Fragment {

	private Activity mActivity;
	DefinedAlartView definedAlartView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		System.out.println("AppsFragment :: onCreate");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("AppsFragment :: onCreateView...");
		
		//View view = inflater.inflate(R.layout.model_activity_awakealart, container, false);
		definedAlartView=new DefinedAlartView(mActivity);
		definedAlartView.setIsAdd(true, null,0);
		View view =definedAlartView.getView();
		return view;
	}
}
