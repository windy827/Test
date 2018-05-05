package com.tts168.autoset.view.webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.view.MyBaseView;

public class MyWebView extends MyBaseView {

	public WebView wv;
	public ProgressBar pb;
	public RelativeLayout rl_refresh;
	public Button bt_refresh;
	public MyWebView(View view) {
		super(view);
		// TODO Auto-generated constructor stub
	}
	public MyWebView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch(v.getId()){
		case R.id.bt_model_webview:
			wv.reload();//重新加载
			pb.setVisibility(View.VISIBLE);
			rl_refresh.setVisibility(View.GONE);
		}
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

	}

	public void loadURL(String urlss,Context context){
		wv.getSettings().setJavaScriptEnabled(true);
		//wv.getSettings().setsu
		wv.setScrollBarStyle(0);
		WebSettings webSettings = wv.getSettings();
		webSettings.setAllowFileAccess(true);
		webSettings.setBuiltInZoomControls(true);
		
		pb.setVisibility(View.VISIBLE);
		rl_refresh.setVisibility(View.GONE);
		wv.loadUrl(urlss);
		// 加载数据
		wv.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					// MainActivity.this.setTitle("加载完成");
					pb.setVisibility(View.INVISIBLE);
					wv.setVisibility(View.VISIBLE);
				} else {
					// MainActivity.this.setTitle("加载中.......");
					pb.setVisibility(View.VISIBLE);					
				}
			}
		});
		// 这个是当网页上的连接被点击的时候
		wv.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(final WebView view,
					final String url) {
				view.loadUrl(url);
				pb.setVisibility(View.INVISIBLE);
				return true;
			}
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				
				pb.setVisibility(View.INVISIBLE);
				rl_refresh.setVisibility(View.VISIBLE);
				wv.stopLoading();
				wv.clearView();
				wv.setVisibility(View.INVISIBLE);
				super.onReceivedError(view, errorCode, description, failingUrl);//super的放在后面很重要
			}
		});
		
		
	}
	/**
	 * 停止加载
	 */
	public void stopLoading(){
		pb.setVisibility(View.INVISIBLE);
		rl_refresh.setVisibility(View.GONE);
		wv.stopLoading();
	}
	@Override
	public void staticFindViewByActivity() {
		// TODO Auto-generated method stub
		wv=(WebView) activity.findViewById(R.id.wv_model_webview);
		pb=(ProgressBar) activity.findViewById(R.id.pg_waite);
		rl_refresh=(RelativeLayout) activity.findViewById(R.id.rl_model_webview);
		bt_refresh=(Button) activity.findViewById(R.id.bt_model_webview);
		rl_refresh.setVisibility(View.GONE);
		wv.setVisibility(View.INVISIBLE);
	}

	@Override
	public void staticByActivityListener() {
		// TODO Auto-generated method stub
		bt_refresh.setOnClickListener(this);
	}

}
