package com.tts168.autoset.activity.alart;

import java.util.HashMap;

import com.amo.demo.wheelview.WheelViewUse;
import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.remind.RemindEntity;
import com.autoset.json.MyTools;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.alart.MyAlartJsonOptions;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.alart.AlartBottomView;
import com.tts168.autoset.view.alart.AlartCloseAndDelView;
import com.tts168.autoset.view.alart.AlartTitleView;
import com.tts168.autoset.view.alart.RemindAlartView;
import com.tts168.autoset.view.title.TitleView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RemindAlartActivity extends MyBaseActivity implements OnClickListener{

	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE="��������";
	
	public static final String ActivityName="RemindAlartActivity";
	View view_title;//������
	AlartCloseAndDelView alartCloseAndDelView;//�رպ�ɾ������
	RemindAlartView  remindAlartView;//����������Ҫ����
	/**
	 * ��ǰ�����Ƿ�Ϊ��ӽ���
	 */
	boolean isadd;
	/**
	 * ��ǰ��ʵ������AlartTools.alartAdapter_content����ĵڼ����������������
	 */
	int index;
	public  WaitView waitView;// �ȴ�����

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
		//����ʵ����
		Intent intent=getIntent();
		isadd=intent.getExtras().getBoolean(AlartTools.IntentKey.INTENT_ISADD);
		index=intent.getExtras().getInt(AlartTools.IntentKey.INTENT_INDEX);
		AlartTools.remindEntity=(RemindEntity) intent.getExtras().getSerializable(AlartTools.IntentKey.INTENT_ENTITY_REMIND);
		waitView = new WaitView(this);
		alartCloseAndDelView=new AlartCloseAndDelView(this);
		view_title=findViewById(R.id.indlude_activity_remind_title);
		titleView=new TitleView(this);		
		if(AlartTools.remindEntity.getIs_valid()==AlarmEntity.ISVALID_YES){
			alartCloseAndDelView.setOpenAlartTextAndDrawable(true);
		}else{
			alartCloseAndDelView.setOpenAlartTextAndDrawable(false);
		}
		//titleView.setSaveText(Tools.TITLE_SAVE);
		titleView.setTitle(TITLE);
		view_title.setVisibility(View.VISIBLE);
					
		remindAlartView=new RemindAlartView(this);			
		remindAlartView.setIsAdd(isadd, AlartTools.remindEntity,index);		
				
		
	}
	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		//iv_del.setonc
	
		
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
		return R.layout.activity_remindalart;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)){
			if(PreventForceClick.isShowToast){
				ToastTools.short_Toast(this, Tools.CLICK_FAILD);
				PreventForceClick.isShowToast=false;
			}
			
		}else{
			PreventForceClick.isShowToast=true;
				switch(v.getId()){
				
				case R.id.iv_acticity_awakealart_isopen:
					String closeOrOpenSucess;
					String closeOrOpenFaild;
					boolean isOpen=true; 
					//String closeOrOpen;
					if(AlartTools.remindEntity.getIs_valid()==AlarmEntity.ISVALID_YES){
						//AlartTools.remindEntity.setIs_valid(AlarmEntity.ISVALID_NO);
						isOpen=false; 
						closeOrOpenFaild="�رձ�������ʧ��";
						closeOrOpenSucess="�رձ�������ɹ�";
					}else{
						//AlartTools.remindEntity.setIs_valid(AlarmEntity.ISVALID_YES);
						isOpen=true; 
						closeOrOpenFaild="������������ʧ��";
						closeOrOpenSucess="������������ɹ�";
						//closeOrOpen=
					}
					//AlartTools.remindEntity.setDate(GetAndSetTime.getNextDayDate());
					//alartCloseAndDelView.setOpenAlartTextAndDrawable(isOpen);
					HashMap<String,Object>temp=new HashMap<String, Object>();
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE, AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY, AlartTools.remindEntity);
					AlartTools.alartAdapter_content.set(index, temp);
					AlartNotifyDialog.openOrCloseAlartDialog(this,isOpen,AlartTools.alartAdapter_content,new int[]{index}, AlartTools.AlartType.JSON_ALART_TYPE_REMIND,closeOrOpenFaild,closeOrOpenSucess,AlartTools.AlartType.OPTIONS_UPDATE);
					break;
				case R.id.bt_model_alart_bottom_save:
					rightViewOption();
					break;
				case R.id.iv_acticity_awakealart_del:
					//ɾ��
					AlartNotifyDialog.deleteAlartDialog(this,this, AlartTools.alartAdapter_content, index, AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
					break;
					
					
				}
		}
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
