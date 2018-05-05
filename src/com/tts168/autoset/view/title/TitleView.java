package com.tts168.autoset.view.title;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.larkiv.larksmart7618.R;
import com.slidingmenu.lib.SlidingMenu;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.view.MyBaseActivityView;

/**
 * ������ģ�壬
 * setTitle(String title)���ñ���
 * ���ذ�ťĬ����ʾ���벻��ʾ�͵���setBackImgViewInVisable()��
 * �����TextView���Ըĳ��������ƣ�Ĭ�����TextView����ʾ������ð�ť��Ҫ��Ӧ�ķ���������ĿĬ����MyBaseActivity��titileSave������
 * 
 * @author Ԭ��
 * 
 */
public class TitleView extends MyBaseActivityView {
	public TextView title;// ����
	ImageView iv_back;// ���ذ�ť
	ImageView iv_menu;// SidMenu�Ĳ˵���ť
	public TextView tv_save;// ����
	ImageView iv_right;// ���ذ�ť

	public static final String TITLE = "��������";

	public TitleView(Activity activity) {
		
		super(activity);

	}
	
/**
 * ���ñ����TextView�����ݣ���������ʾ������������Ĭ�ϲ��ɼ�
 * @param text
 */
	public void setSaveText(String text){
		tv_save.setVisibility(View.VISIBLE);
		tv_save.setText(text);
	}
	/**
	 * ���ñ������ʱ��ͼƬ
	 * @param resid
	 */
	public void setRightDrawable(int resid){
		iv_right.setVisibility(View.VISIBLE);
		
		iv_right.setImageResource(resid);
		
	}
	
	/**
	 * ���ñ���
	 * @param title
	 */
public void setTitle(String title){
	if(Tools.Current_DeviceName.equals("")){
		this.title.setText(title);
	}else{
		//this.title.setText(title+"��"+Tools.Current_DeviceName+"��");
		this.title.setText(title);
	}
	
}
	/**
	 * ���÷��ذ�ť���ɼ���������Ĭ�Ͽɼ�
	 */
	public void setBackImgViewInVisable(){
		this.iv_back.setVisibility(View.INVISIBLE);
	} 
	/**
	 * ���÷��ذ�ť���ɼ�������Ĭ�Ͽɼ�
	 */
	public void setBackImgViewVisable(){
		this.iv_back.setVisibility(View.VISIBLE);
	} 
	
	/**
	 * ����SideMenu �˵���ť�ɼ�
	 */
	public void setMenuImgViewVisable(){
		this.iv_menu.setVisibility(View.VISIBLE);
	} 

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.SHORT_TIME);//���������
		switch (v.getId()) {
		case R.id.model_title_iv_back1:
			activity.onBackPressed();
			break;
		case R.id.model_title_tv1:
			if(!Tools.CurrentActivityName.equals(MainMenuActivity.ActivityName)){
				activity.onBackPressed();
			}
			
			break;
		case R.id.model_title_iv_menu:
			MainMenuActivity.setMenu();
			break;
		case R.id.tv_model_title_save:
			// �������
			((MyBaseActivity) activity).rightViewOption();
			break;
		case R.id.iv_model_title_save:
			// RightOptions����
			((MyBaseActivity) activity).rightViewOption();
			break;
		}

	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		
		tv_save = (TextView) activity.findViewById(R.id.tv_model_title_save);// д�б����TextView
		title = (TextView) activity.findViewById(R.id.model_title_tv1);
		iv_back = (ImageView) activity.findViewById(R.id.model_title_iv_back1);
		iv_menu = (ImageView) activity.findViewById(R.id.model_title_iv_menu);
		iv_right= (ImageView) activity.findViewById(R.id.iv_model_title_save);
		
		 
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		tv_save.setOnClickListener(this);
		title.setOnClickListener(this);
		iv_back.setOnClickListener(this);
		iv_menu.setOnClickListener(this);
		iv_right.setOnClickListener(this);
	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub

	}

}
