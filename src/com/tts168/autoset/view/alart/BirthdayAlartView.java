package com.tts168.autoset.view.alart;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amo.demo.wheelview.WheelViewUse;
import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.json.MyTools;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity;
import com.tts168.autoset.activity.alart.BirthdayAlartActivity;
import com.tts168.autoset.activity.alart.addalart.AlartFragmentTabActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.alart.MyAlartJsonOptions;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.view.MyBaseView;
import com.tts168.autoset.view.WaitView;
/**
 * Alarm�ĺ��Ĳ��ֽ���ռ������
 * @author Ԭ��
 *
 */
public class BirthdayAlartView extends MyBaseActivityView{

	//��Ͷ˵İ�ť
	public Button bt_save;
	
	

	/**
	 * ��ǰ�����Ƿ�Ϊ��ӽ���
	 */
	boolean isadd=true;
	/**
	 * ��ǰ��ʵ������AlartTools.alartAdapter_content����ĵڼ����������������
	 */
	int index;
	private RelativeLayout rl_cycle;//����
	private TextView tv_cycle;
	
	View view;
	AlartTitleView alartTitleView;//�������
	Context context;
	
	public BirthdayAlartView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
/**
 * �����Ƿ�Ϊ���
 * @param isAdd
 * @param aeû�С���Ϊ���״̬��������null
 * @param indexû�С���Ϊ���״̬��������0
 */
	public void setIsAdd(boolean isAdd,BirthDayEntity  ae,int index){
		isadd=isAdd;
		View timeView=null;
		
		
		if (isadd) {
			Tools.time=GetAndSetTime.getData();
			AlartCommenSetTools.is_lunar=0.0;
			AlartCommenSetTools.ID=AlartTools.getAlarmGeneralID(AlartTools.alartAdapter_content, AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY);
			view = activity.getLayoutInflater().inflate(R.layout.model_activity_birthdayalart, null, false);
			
			bt_save=(Button) view.findViewById(R.id.bt_model_save_save);
			alartTitleView =new AlartTitleView(view,AlartTitleView.TEXTLEN_MAX10);
			alartTitleView.setEitTextHinit("���������ǵ����ֻ�ƺ�");
			timeView=view.findViewById(R.id.ll_timelayout);
			EditTimeLayoutView.isLunar = false;// Ĭ��Ϊ��ѡ��
			EditTimeLayoutView.useMyDate = false;// Ĭ�ϲ�ʹ��
			
			// ����Ǳ༭����ʾ���������������Ӿ����ر༭��
			AlartTools.Cycle.Fre_model=AlartTools.Cycle.current_Fre_model;
			AlartTools.Cycle.cycleValue=AlartTools.Cycle.current_cycleValue;
			EditTimeLayoutView.useMyDate = false;
		} else {
		
			alartTitleView =new AlartTitleView(activity,AlartTitleView.TEXTLEN_MAX10);
			
			AlartCommenSetTools.ID=ae.getId();
			AlartCommenSetTools.is_lunar=ae.getDate_formate();
			Tools.time=ae.getData_value();
			bt_save=(Button) activity.findViewById(R.id.bt_model_save_save);
			timeView=activity.findViewById(R.id.ll_timelayout);
			alartTitleView.setEitTextContent(ae.getWho());
			
			AlartCommenSetTools.is_lunar=ae.getDate_formate();
			if (ae.getDate_formate() ==BirthDayEntity.DATE_FORMATE_Lunar) {
				EditTimeLayoutView.isLunar = true;
				//EditTimeLayoutView.time = ae.getData_value();
			} else {
				EditTimeLayoutView.isLunar = false;
				
			}	
			EditTimeLayoutView.time = AlartTools.BirthDay.convertToMydata(ae.getData_value());
			EditTimeLayoutView.useMyDate = true;
			
		}
		
		bt_save.setOnClickListener(this);
		
		EditTimeLayoutView.flag = Tools.FLAG_DATE;
		WheelViewUse.getDateView(timeView, EditTimeLayoutView.flag, false,
				EditTimeLayoutView.useMyDate, EditTimeLayoutView.time,
				EditTimeLayoutView.isLunar);
	}
	/**
	 * �õ�View��ʹ�ô˷���ǰ���ȵ���setIsAdd�������г�ʼ��
	 * @return
	 */
	public View getView(){
		return view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)){
			if(PreventForceClick.isShowToast){
				ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), Tools.CLICK_FAILD);
				PreventForceClick.isShowToast=false;
			}
			
		}else{
			PreventForceClick.isShowToast=true;
		switch(v.getId()){
		
			
		case R.id.bt_model_save_save:
			
				titleSave();
			
			
			break;
		}
			}
	}
	public void titleSave() {
		// TODO Auto-generated method stub
		//�������
		if(alartTitleView.et_title.getText().length()>0){
			/**
			 * �õ���ǰʱ��
			 */
			
			if(Tools.time.contains("��")){
				if(AlartCommenSetTools.is_lunar==BirthDayEntity.DATE_FORMATE_Lunar){
					Tools.time=AlartTools.BirthDay.convertLunarDataToSolardata(Tools.time);
				}else{
					Tools.time=AlartTools.BirthDay.convertSolarDataToMydata(Tools.time);
				}
				
			}
			
			//���水ť
			if(isadd){
				//��������
				((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
//				if((int)AlartCommenSetTools.ID>AlartTools.MaxID_Alart.MAXID_BIRTH){
//					//�������
//					ToastTools.short_Toast(activity, "��ӵ�����������!!");
//				}else{
					BirthDayEntity bde=new BirthDayEntity((int)AlartCommenSetTools.ID,alartTitleView.et_title.getText().toString(), AlartCommenSetTools.is_lunar,Tools.time );
					HashMap<String,Object>temp=new HashMap<String, Object>();
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE, AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY);
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY, bde);
					AlartTools.alartAdapter_content.add(temp);
					MyAlartJsonOptions.setAlartData(AlartTools.alartAdapter_content,  new int[]{AlartTools.alartAdapter_content.size()-1},AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY,Tools.STATUS_UPDATE_FAILED,"��ӳɹ�,��Ϊ��������"+ MyTools.getDoubQuot(alartTitleView.et_title.getText().toString())+"����������",AlartTools.AlartType.OPTIONS_INSERT);
			//	}
				
			}else{
				//�༭
				//������޸�
				BirthdayAlartActivity.waitView.setPBVisable();
				BirthDayEntity bde=new BirthDayEntity((int)AlartCommenSetTools.ID,alartTitleView.et_title.getText().toString(), AlartCommenSetTools.is_lunar, Tools.time);
				HashMap<String,Object>temp=new HashMap<String, Object>();
				temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE, AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY);
				temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY, bde);
				AlartTools.alartAdapter_content.set(index, temp);
				MyAlartJsonOptions.setAlartData(AlartTools.alartAdapter_content, new int[]{index},AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY,Tools.STATUS_UPDATE_FAILED,"�޸ĳɹ�,��Ϊ��������"+ MyTools.getDoubQuot(alartTitleView.et_title.getText().toString())+"����������",AlartTools.AlartType.OPTIONS_UPDATE);
			}
			ToastTools.short_Toast(activity, Tools.OPTION_SAVING);
		}
		else {
			NotifyDialog.SimpleAlertDialog(activity,
					"���ǵĳƺ�����Ϊ�գ����������Զ�������ٱ��棡");
		}
		
			
	}
	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * �༭ʱ���Layout2
	 * 
	 * @author Ԭ��
	 * 
	 */
	public static class EditTimeLayoutView {
		/**
		 * //WheelView��ǵ���ʱ�仹������
		 */
		static int flag = 0;
		/**
		 * //�Ƿ�������
		 */
		static boolean isLunar;
		/**
		 * //�Ƿ�ʹ���Ҷ��������ʱ��
		 */
		static boolean useMyDate;
		/**
		 * //����WheelView����Ҫ����ʾʱ�����ڣ���һ���ĸ�ʽҪ��
		 */
		static String time;

	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		
	
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

}
