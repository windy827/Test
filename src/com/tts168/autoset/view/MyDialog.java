package com.tts168.autoset.view;

import com.larkiv.larksmart7618.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyDialog extends Dialog {
	Context context;

	public MyDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.presscheckdialog);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		ListView lv=(ListView) findViewById(R.id.lv_press);
		
	}

}