package com.tts168.autoset.view;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.view.MyBaseView;

public class WaitView extends MyBaseView {

	public ProgressBar pb;
	public WaitView(View view) {
		super(view);
		// TODO Auto-generated constructor stub
	}
	public WaitView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	public void setPBGone(){
		pb.setVisibility(View.GONE);
	}
	public void setPBVisable(){
		pb.setVisibility(View.VISIBLE);
	}
	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void staticFindViewByView() {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void staticByViewListener() {
		// TODO Auto-generated method stub
		pb=(ProgressBar) view.findViewById(R.id.pg_waite);
	}

	
	@Override
	public void staticFindViewByActivity() {
		// TODO Auto-generated method stub
		pb=(ProgressBar) activity.findViewById(R.id.pg_waite);
		
	}

	@Override
	public void staticByActivityListener() {
		// TODO Auto-generated method stub

	}

}
