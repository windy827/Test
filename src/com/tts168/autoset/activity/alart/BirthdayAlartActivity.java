package com.tts168.autoset.activity.alart;

import java.util.HashMap;

import com.amo.demo.wheelview.WheelViewUse;
import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.alart.MyAlartJsonOptions;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.alart.AlartBottomView;
import com.tts168.autoset.view.alart.AlartCloseAndDelView;
import com.tts168.autoset.view.alart.AlartTitleView;
import com.tts168.autoset.view.alart.BirthdayAlartView;
import com.tts168.autoset.view.title.TitleView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;



	
	/**
	 * ˯������
	 * @author Ԭ��
	 *
	 */
public class BirthdayAlartActivity extends MyBaseActivity implements OnClickListener{
		TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
		public static final String TITLE="��������";
		
		public static final String ActivityName="BirthdayAlartActivity";
		View view_title;//������
		AlartCloseAndDelView alartCloseAndDelView;//�رպ�ɾ������
		BirthdayAlartView birthdayAlartView;//������Ҫ���ܱ༭����
		/**
		 * ��ǰ�����Ƿ�Ϊ��ӽ���
		 */
		boolean isadd;
		/**
		 * ��ǰ��ʵ������AlartTools.alartAdapter_content����ĵڼ����������������
		 */
		int index;
		public static WaitView waitView;// �ȴ�����
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
			AlartTools.birthDayEntity=(BirthDayEntity) intent.getExtras().getSerializable(AlartTools.IntentKey.INTENT_ENTITY_BIRTHDAY);
			
			view_title=findViewById(R.id.indlude_activity_awakealart_title);
			titleView=new TitleView(this);		
			//titleView.setSaveText(Tools.TITLE_SAVE);
			titleView.setTitle(TITLE);
			waitView = new WaitView(this);
			alartCloseAndDelView=new AlartCloseAndDelView(this);
			alartCloseAndDelView.setIsOpenViewGone();
			view_title.setVisibility(View.VISIBLE);	
			
			birthdayAlartView=new BirthdayAlartView(this);
			birthdayAlartView.setIsAdd(isadd, AlartTools.birthDayEntity,index);
			
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
			return R.layout.activity_birthdayalart;
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
					
					case R.id.bt_model_alart_bottom_save:
						rightViewOption();
						break;
					case R.id.iv_acticity_awakealart_del:
						//ɾ��
						AlartNotifyDialog.deleteAlartDialog(this,this, AlartTools.alartAdapter_content, index, AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY);
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
