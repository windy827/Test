package com.tts168.autoset.view.alart;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.view.MyBaseView;
/**
 * �Ͷ˵�������ť
 * @author Ԭ��
 *
 */
public class AlartBottomView extends MyBaseActivityView{
	

	public AlartBottomView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	//��Ͷ˵İ�ť
	public Button bt_save,bt_commen;
	
/**
 * ���ر��水ť
 */
	public void setSaveBtnGone(){
		bt_save.setVisibility(View.GONE);
	}
	
	/**
	 * �������ð�ť
	 */
		public void setCommentSetBtnGone(){
			bt_commen.setVisibility(View.GONE);
		}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		bt_save=(Button) activity.findViewById(R.id.bt_model_alart_bottom_save);
		bt_commen=(Button) activity.findViewById(R.id.bt_model_alart_bottom_commentset);
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
//		bt_save.setOnClickListener((OnClickListener) activity);
//		bt_commen.setOnClickListener((OnClickListener) activity);
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
