package com.tts168.autoset.activity.about;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.viewtools.TextViewTools;
import com.tts168.autoset.view.title.TitleView;

public class AboutUsActivity extends MyBaseActivity{
	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE="��������";
	
	public static final String ActivityName="AboutUsActivity";
	TextView tv_aboutus;//�������ǵ�����
	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}
	@Override
	public void rightToLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if(!Tools.CurrentActivityName.equals(getActivityName())){
			refresh();
		}
	}
	/**
	 * �ṩ���ⲿ��ˢ�½ӿ�
	 */
		public static void refresh(){
		
		}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub	
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		tv_aboutus=(TextView) findViewById(R.id.tv_activity_aboutus);
		TextViewTools.setTextColor(tv_aboutus, 14, 18, Color.RED);
		
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getContentViewID() {
		// TODO Auto-generated method stub
		return R.layout.activity_aboutus;
	}


	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
