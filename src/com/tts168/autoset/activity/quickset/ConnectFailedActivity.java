package com.tts168.autoset.activity.quickset;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.view.title.TitleView;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * FSK����ʧ��
 * @author Ԭ��
 *
 */
public class ConnectFailedActivity extends MyBaseActivity implements OnClickListener{
	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE="����ʧ��";
	
	public static final String ActivityName="ConnectFailedActivity";
	Button bt_retry;//��������
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
			
			//MyApplication.asyncBitmapLoader.initExecutorService();[�õ��첽���صĵط���Ҫʹ��]
			
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
		
		bt_retry=(Button) findViewById(R.id.bt_activity_connectfailedretry);
		
		
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_retry.setOnClickListener(this);
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
		return R.layout.activity_connectfailed;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//���������
				switch(v.getId()){
				case R.id.bt_activity_connectfailedretry:
					//������������
					ActivitySetting.startActivity(this, WifiSetActivity.class, null, true);
					break;
				}
		
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//�������
		ToastTools.short_Toast((Activity)MyApplication.getInstance().getCur_Activity(), "����ɹ�!!");
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
