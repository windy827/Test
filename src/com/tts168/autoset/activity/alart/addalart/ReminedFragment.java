package com.tts168.autoset.activity.alart.addalart;

import com.tts168.autoset.view.alart.DefinedAlartView;
import com.tts168.autoset.view.alart.RemindAlartView;

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
public class ReminedFragment extends Fragment {

	private Activity mActivity;
	RemindAlartView remindAlartView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		System.out.println("AppsFragment :: onCreate");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("AppsFragment :: onCreateView...");
		
			
		remindAlartView=new RemindAlartView(mActivity);
		remindAlartView.setIsAdd(true, null,0);
		View view =remindAlartView.getView();
		return view;
	}
}
