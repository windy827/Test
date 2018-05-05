package com.tts168.autoset.view.player;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
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
public class PlayingTitleView extends MyBaseActivityView {
	TextView title;// 标题
	ImageView iv_left;// 左边的ImageView
	ImageView iv_right;// 右边的ImageView
	public static final String TITLE = "按键设置";

	public PlayingTitleView(Activity activity) {
		super(activity);

	}
	

	/**
	 * 设置标题
	 * @param title
	 */
public void setTitle(String title){
	this.title.setText(title);
}
	/**
	 * 设置返回按钮不可见，不设置默认可见
	 */
	public void setLeftImgViewInVisable(){
		this.iv_left.setVisibility(View.GONE);
	} 
	/**
	 * 设置返回按钮不可见，不设置默认可见
	 */
	public void setRightImgViewInVisable(){
		this.iv_right.setVisibility(View.GONE);
	} 

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_model_playing_title_left:
			activity.onBackPressed();
			break;
		case R.id.iv_model_playing_title_right:
			// 保存操作
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
		iv_right = (ImageView) activity.findViewById(R.id.iv_model_playing_title_right);// 写有保存的TextView
		title = (TextView) activity.findViewById(R.id.tv_model_playing_title);
		iv_left = (ImageView) activity.findViewById(R.id.iv_model_playing_title_left);
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		iv_right.setOnClickListener(this);
		iv_left.setOnClickListener(this);
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
