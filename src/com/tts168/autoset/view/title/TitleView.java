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
 * 标题栏模板，
 * setTitle(String title)设置标题
 * 返回按钮默认显示；想不显示就调用setBackImgViewInVisable()；
 * 保存的TextView可以改成其他名称，默认这个TextView不显示，点击该按钮需要相应的方法，本项目默认在MyBaseActivity的titileSave方法中
 * 
 * @author 袁剑
 * 
 */
public class TitleView extends MyBaseActivityView {
	public TextView title;// 标题
	ImageView iv_back;// 返回按钮
	ImageView iv_menu;// SidMenu的菜单按钮
	public TextView tv_save;// 保存
	ImageView iv_right;// 返回按钮

	public static final String TITLE = "按键设置";

	public TitleView(Activity activity) {
		
		super(activity);

	}
	
/**
 * 设置保存的TextView的内容，并将其显示出来，不设置默认不可见
 * @param text
 */
	public void setSaveText(String text){
		tv_save.setVisibility(View.VISIBLE);
		tv_save.setText(text);
	}
	/**
	 * 设置标题栏邮编的图片
	 * @param resid
	 */
	public void setRightDrawable(int resid){
		iv_right.setVisibility(View.VISIBLE);
		
		iv_right.setImageResource(resid);
		
	}
	
	/**
	 * 设置标题
	 * @param title
	 */
public void setTitle(String title){
	if(Tools.Current_DeviceName.equals("")){
		this.title.setText(title);
	}else{
		//this.title.setText(title+"【"+Tools.Current_DeviceName+"】");
		this.title.setText(title);
	}
	
}
	/**
	 * 设置返回按钮不可见，不设置默认可见
	 */
	public void setBackImgViewInVisable(){
		this.iv_back.setVisibility(View.INVISIBLE);
	} 
	/**
	 * 设置返回按钮不可见，设置默认可见
	 */
	public void setBackImgViewVisable(){
		this.iv_back.setVisibility(View.VISIBLE);
	} 
	
	/**
	 * 设置SideMenu 菜单按钮可见
	 */
	public void setMenuImgViewVisable(){
		this.iv_menu.setVisibility(View.VISIBLE);
	} 

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.SHORT_TIME);//防暴力点击
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
			// 保存操作
			((MyBaseActivity) activity).rightViewOption();
			break;
		case R.id.iv_model_title_save:
			// RightOptions操作
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
		
		tv_save = (TextView) activity.findViewById(R.id.tv_model_title_save);// 写有保存的TextView
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
