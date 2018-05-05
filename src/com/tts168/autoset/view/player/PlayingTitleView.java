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
 * ������ģ�壬
 * setTitle(String title)���ñ���
 * ���ذ�ťĬ����ʾ���벻��ʾ�͵���setBackImgViewInVisable()��
 * �����TextView���Ըĳ��������ƣ�Ĭ�����TextView����ʾ������ð�ť��Ҫ��Ӧ�ķ���������ĿĬ����MyBaseActivity��titileSave������
 * 
 * @author Ԭ��
 * 
 */
public class PlayingTitleView extends MyBaseActivityView {
	TextView title;// ����
	ImageView iv_left;// ��ߵ�ImageView
	ImageView iv_right;// �ұߵ�ImageView
	public static final String TITLE = "��������";

	public PlayingTitleView(Activity activity) {
		super(activity);

	}
	

	/**
	 * ���ñ���
	 * @param title
	 */
public void setTitle(String title){
	this.title.setText(title);
}
	/**
	 * ���÷��ذ�ť���ɼ���������Ĭ�Ͽɼ�
	 */
	public void setLeftImgViewInVisable(){
		this.iv_left.setVisibility(View.GONE);
	} 
	/**
	 * ���÷��ذ�ť���ɼ���������Ĭ�Ͽɼ�
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
			// �������
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
		iv_right = (ImageView) activity.findViewById(R.id.iv_model_playing_title_right);// д�б����TextView
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
