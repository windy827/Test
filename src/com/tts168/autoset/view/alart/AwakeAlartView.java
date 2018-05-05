package com.tts168.autoset.view.alart;

import java.util.ArrayList;
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
import com.autoset.jni.getupset.GetUpSetEntity;
import com.autoset.json.MyTools;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity;
import com.tts168.autoset.activity.alart.AwakeAlartEditActivity;
import com.tts168.autoset.activity.alart.addalart.AlartFragmentTabActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.alart.GeneralID;
import com.tts168.autoset.tools.alart.MyAlartJsonOptions;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.SharedPrefenceSetTools;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.wheelview.WheelViewTools;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.view.MyBaseView;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.popwindow.PopupWindowAlartCycle;

/**
 * Alarm�ĺ��Ĳ��ֽ���ռ������
 * 
 * @author Ԭ��
 * 
 */
public class AwakeAlartView extends MyBaseActivityView {

	// ��Ͷ˵İ�ť
	public Button bt_save;
	public RelativeLayout rl_comment;// ͨ������

	public AwakeAlartView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ǰ�����Ƿ�Ϊ��ӽ���
	 */
	boolean isadd = true;
	/**
	 * ��ǰ��ʵ������AlartTools.alartAdapter_content����ĵڼ����������������
	 */
	int index;
	private RelativeLayout rl_cycle;// ����
	private TextView tv_cycle;
	private RelativeLayout rl_date;//����
	private TextView tv_date;
	View view;
	Context context;

	/**
	 * �����Ƿ�Ϊ���
	 * 
	 * @param isAdd
	 * @param aeû��
	 *            ����Ϊ���״̬��������null
	 * @param indexû��
	 *            ����Ϊ���״̬��������0
	 */
	public void setIsAdd(boolean isAdd, AlarmEntity ae, int index) {
		isadd = isAdd;
		View timeView = null;
		if (isadd) {
			AlartCommenSetTools.ID = AlartTools.getAlarmGeneralID(
					AlartTools.alartAdapter_content,
					AlartTools.AlartType.JSON_ALART_TYPE_ALART);
			AlartCommenSetTools.is_lunar=0.0;
			AlartTools.Cycle.Fre_model = 1;
			AlartTools.Cycle.cycleValue = 127;
//			AlartTools.Cycle.Fre_model = AlartTools.Cycle.current_Fre_model;
//			AlartTools.Cycle.cycleValue = AlartTools.Cycle.current_cycleValue;
			/**
			 * ��ʼ��AlartTools.alarmEntity��ֵ
			 */
			AlartTools.alarmEntity = new AlarmEntity(
					(int) AlartCommenSetTools.ID, activity.getResources()
							.getString(R.string.alart_awake_title),
					GetAndSetTime.getData(), Tools.time
							+ AlartTools.TimeTools.TIME_TAIL,
					AlartCommenSetTools.is_lunar, AlartCommenSetTools.isvalid,
					AlartTools.Cycle.Fre_model, AlartTools.Cycle.cycleValue);
			view = activity.getLayoutInflater().inflate(
					R.layout.model_activity_awakealart, null, false);
			
			rl_cycle = (RelativeLayout) view.findViewById(R.id.rl_alart_cycle);
			tv_cycle = (TextView) view.findViewById(R.id.tv_alart_cycle);
			rl_date = (RelativeLayout) view.findViewById(R.id.rl_alart_date);
			tv_date = (TextView) view.findViewById(R.id.tv_alart_date);
			bt_save = (Button) view.findViewById(R.id.bt_model_save_save);
			rl_comment = (RelativeLayout) view
					.findViewById(R.id.rl_alart_comment);
			timeView = view.findViewById(R.id.ll_timelayout);
			EditTimeLayoutView.isLunar = false;// Ĭ��Ϊ��ѡ��
			EditTimeLayoutView.useMyDate = false;// Ĭ�ϲ�ʹ��

			rl_cycle.setOnClickListener(this);
			rl_date.setOnClickListener(this);
			bt_save.setOnClickListener(this);
			rl_comment.setOnClickListener(this);
			
			EditTimeLayoutView.useMyDate = false;
			if((int)AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
				tv_cycle.setText("����һ��");
				setDateVisiable();
				
			}else{
				tv_cycle.setText(AlartTools.Cycle.getWeekCycleByInt(
						(int) AlartTools.alarmEntity.getFre_mode(), AlartTools.Cycle.cycleValue));
			}
		} else {
			AlartTools.alarmEntity = ae;
			rl_cycle = (RelativeLayout) activity
					.findViewById(R.id.rl_alart_cycle);
			tv_cycle = (TextView) activity.findViewById(R.id.tv_alart_cycle);
			rl_date = (RelativeLayout) activity.findViewById(R.id.rl_alart_date);
			tv_date = (TextView) activity.findViewById(R.id.tv_alart_date);
			bt_save = (Button) activity.findViewById(R.id.bt_model_save_save);
			rl_comment = (RelativeLayout) activity
					.findViewById(R.id.rl_alart_comment);
			timeView = activity.findViewById(R.id.ll_timelayout);
			EditTimeLayoutView.isLunar = false;// Ĭ��Ϊ��ѡ��
			EditTimeLayoutView.useMyDate = false;// Ĭ�ϲ�ʹ��

			// ����Ǳ༭����ʾ���������������Ӿ����ر༭��

			rl_cycle.setOnClickListener(this);
			rl_date.setOnClickListener(this);
			bt_save.setOnClickListener(this);
			rl_comment.setOnClickListener(this);
			this.index = index;
			AlartTools.Cycle.Fre_model = (int) ae.getFre_mode();
			AlartTools.Cycle.cycleValue = (int) ae.getFrequency();
			EditTimeLayoutView.time = ae.getClock();
			EditTimeLayoutView.useMyDate = true;

			EditTimeLayoutView.isLunar = false;

			int cycle_Frequenc = (int) ae.getFrequency();
			AlartTools.Cycle.current_cycleValue = cycle_Frequenc;
			if((int)AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
				tv_cycle.setText("����һ��");
				setDateVisiable();
				
			}else{
				tv_cycle.setText(AlartTools.Cycle.getWeekCycleByInt(
						(int) ae.getFre_mode(), cycle_Frequenc));
			}
			

		}
		Tools.date=AlartTools.alarmEntity.getDate();
		
		rl_comment.setVisibility(View.VISIBLE);
		EditTimeLayoutView.flag = Tools.FLAG_TIME;
		WheelViewUse.getDateView(timeView, EditTimeLayoutView.flag, false,
				EditTimeLayoutView.useMyDate, EditTimeLayoutView.time,
				EditTimeLayoutView.isLunar);

	}

	/**
	 * ������ʾ���ڵĿؼ��ɼ�������������
	 * @param date
	 */
	public void setDateVisiable(){
		rl_date.setVisibility(View.VISIBLE);
		Tools.date=GetAndSetTime.getData();
		tv_date.setText(Tools.date);
	}
	/**
	 * ������ʾ���ڵĿؼ����ɼ�
	 */
	public void setDateGone(){
		rl_date.setVisibility(View.GONE);
		
	}
	/**
	 * �õ�View��ʹ�ô˷���ǰ���ȵ���setIsAdd�������г�ʼ��
	 * 
	 * @return
	 */
	public View getView() {
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)) {
			if (PreventForceClick.isShowToast) {
				ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), Tools.CLICK_FAILD);
				PreventForceClick.isShowToast = false;
			}

		} else {
			PreventForceClick.isShowToast = true;
			switch (v.getId()) {

			case R.id.rl_alart_cycle:
				// ��������
				//AlartNotifyDialog.chooseCycleDialog(activity, tv_cycle);
				new PopupWindowAlartCycle(MyApplication.getInstance().getCur_Activity(), tv_cycle,rl_date,tv_date).showPopwindow();
				break;
			case R.id.rl_alart_date:
				// ��������
				NotifyDialog.ChooseTIMEDialog_TV(MyApplication.getInstance().getCur_Activity(), Tools.FLAG_DATE,false,true,tv_date.getText().toString(),false,
						SharedPrefenceSetTools.STARTTIME, tv_date,
						Tools.TYPE_SET_FESTIVE,WheelViewTools.COMMENT_HOUR_START,WheelViewTools.COMMENT_HOUR_end);
				
				break;
			case R.id.bt_model_save_save:
				// ˢ�²��������»�ȡ���ݡ�

				titleSave();

				break;
			case R.id.rl_alart_comment:
				// ͨ�����ð�ť
				if (isadd) {
					// ��������
					
					if(activity.equals(MyApplication.getInstance().getCur_Activity())){
						((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
					}
					
				}
					
				else{
					((AwakeAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
				}
				ArrayList<String> domainNames = new ArrayList<String>();
				// ��ӵ�˳�����Ҫ���������һ����ӵĻ������һ���ͽ��յ�
				domainNames.add(GetUpSetEntity.DOMAIN_NAME);
				TCPTools.sendTcpByDomain(domainNames, Tools.Current_SocketIP);

				// ActivitySetting.startUnfinishedActivity(activity,
				// AwakeAlartCommentSetActivity.class, null);
				break;
			}
		}
	}

	public void titleSave() {
		// TODO Auto-generated method stub
		// �������
		// ���水ť
		
		AlartTools.alarmEntity.setClock(Tools.time
				+ AlartTools.TimeTools.TIME_TAIL);
		AlartTools.alarmEntity.setDate(Tools.date);
		String date="";
		
		if((int) AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
			date=MyTools.getDoubQuot(AlartTools.BirthDay.convertToMydata(Tools.date)+",");
		}
		String longTime=tv_date.getText().toString().replace("-", "");
		if((int)AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE&&
				Long.parseLong(GetAndSetTime.getData().replace("-", ""))>Long.parseLong(longTime)){
			NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), "���õ�ʱ���ѹ��ڣ�������ٱ��棡");
			
		}else{
			
		
		if (isadd) {
			// ��������
			((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
//			if ((int) AlartCommenSetTools.ID > AlartTools.MaxID_Alart.MAXID_ALARM) {
//				// �������
//				ToastTools.short_Toast(activity, "��ӵ�����������!!");
//			} else {
	

				
				HashMap<String, Object> temp = new HashMap<String, Object>();
				temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE,
						AlartTools.AlartType.JSON_ALART_TYPE_ALART);
				temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
						AlartTools.alarmEntity);
				AlartTools.alartAdapter_content.add(temp);
				
				MyAlartJsonOptions
						.setAlartData(
								AlartTools.alartAdapter_content,
								new int[] { AlartTools.alartAdapter_content
										.size() - 1 },
								AlartTools.AlartType.JSON_ALART_TYPE_ALART,
								MyTools.getDoubQuot(Tools.STATUS_UPDATE_FAILED),
								"��ӳɹ�,��Ϊ��������" +date+ MyTools.getDoubQuot(Tools.time)
										+ "��������",
								AlartTools.AlartType.OPTIONS_INSERT);
			//}

		} else {
			// �༭
			// ������޸�
			((AwakeAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE,
					AlartTools.AlartType.JSON_ALART_TYPE_ALART);
			temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
					AlartTools.alarmEntity);
			AlartTools.alartAdapter_content.set(index, temp);
			MyAlartJsonOptions.setAlartData(AlartTools.alartAdapter_content,
					new int[] { index },
					AlartTools.AlartType.JSON_ALART_TYPE_ALART,
					MyTools.getDoubQuot(Tools.STATUS_UPDATE_FAILED),
					"�޸ĳɹ�,��Ϊ��������" +date+ MyTools.getDoubQuot(Tools.time) + "��������",
					AlartTools.AlartType.OPTIONS_UPDATE);
		}
		ToastTools.short_Toast(activity, Tools.OPTION_SAVING);
		}
		/**
		 * ���ٵ�ǰActivity������AlartActivity
		 */
		// activity.finish();
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
