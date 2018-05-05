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
import com.autoset.jni.remind.RemindEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity;
import com.tts168.autoset.activity.alart.AwakeAlartEditActivity;
import com.tts168.autoset.activity.alart.RemindAlartActivity;
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
import com.tts168.autoset.view.EditTextAndTextView;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.view.MyBaseView;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.alart.BirthdayAlartView.EditTimeLayoutView;

/**
 * Alarm�ĺ��Ĳ��ֽ���ռ������
 * 
 * @author Ԭ��
 * 
 */
public class RemindAlartView extends MyBaseActivityView {

	public static final int MAX_CONTENTTEXTTOTAL = 40;// ��������Ľ�������ֵĸ���
	EditTextAndTextView editTextAndTextView;// ���������Ƶı༭��
	// ��Ͷ˵İ�ť
	public Button bt_save;

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

	View view;
	AlartTitleView alartTitleView;// �������
	Context context;
	public  WaitView waitView;// �ȴ�����
	public RemindAlartView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * �����Ƿ�Ϊ���
	 * 
	 * @param isAdd
	 * @param aeû��
	 *            ����Ϊ���״̬��������null
	 * @param indexû��
	 *            ����Ϊ���״̬��������0
	 */
	public void setIsAdd(boolean isAdd, RemindEntity ae, int index) {
		isadd = isAdd;
		View timeView = null;
		if (isadd) {
			Tools.time=GetAndSetTime.getData();
			AlartCommenSetTools.ID = AlartTools.getAlarmGeneralID(
					AlartTools.alartAdapter_content,
					AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
		
			view = activity.getLayoutInflater().inflate(
					R.layout.model_activity_remindalart, null, false);
			waitView = new WaitView(view);
			timeView=view.findViewById(R.id.ll_timelayout);
			bt_save = (Button) view.findViewById(R.id.bt_model_save_save);
			editTextAndTextView = new EditTextAndTextView(view);
			editTextAndTextView.setMaxLength(MAX_CONTENTTEXTTOTAL);// ��������������������
			editTextAndTextView.setEditTextHinit("�ڴ˴����뱸�������ݣ����"
					+ MAX_CONTENTTEXTTOTAL + "���֣���");
			EditTimeLayoutView.isLunar = false;// Ĭ��Ϊ��ѡ��
			EditTimeLayoutView.useMyDate = false;// Ĭ�ϲ�ʹ��
			// ����Ǳ༭����ʾ���������������Ӿ����ر༭��
			AlartTools.Cycle.Fre_model = AlartTools.Cycle.current_Fre_model;
			AlartTools.Cycle.cycleValue = AlartTools.Cycle.current_cycleValue;
		} else {
			AlartCommenSetTools.ID = ae.getId();
			editTextAndTextView = new EditTextAndTextView(activity);
			waitView = new WaitView(activity);
			bt_save = (Button) activity.findViewById(R.id.bt_model_save_save);
			editTextAndTextView.setMaxLength(MAX_CONTENTTEXTTOTAL);// ��������������������
			editTextAndTextView.setEditTextHinit("�ڴ˴����뱸�������ݣ����"
					+ MAX_CONTENTTEXTTOTAL + "���֣���");
			timeView=activity.findViewById(R.id.ll_timelayout);
			editTextAndTextView.et_suggest_content.setText(ae.getContent());
			EditTimeLayoutView.isLunar = false;
			EditTimeLayoutView.time = AlartTools.BirthDay.convertToMydata(ae.getDate());
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

			case R.id.bt_model_save_save:

				titleSave();

				break;
			}
		}
	}

	public void titleSave() {
		// TODO Auto-generated method stub
		// �������
		// ���水ť
		String longTime=Tools.time.replace("-", "");
		if(Long.parseLong(GetAndSetTime.getData().replace("-", ""))>Long.parseLong(longTime)){
			NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), "���õ�ʱ���ѹ��ڣ�������ٱ��棡");
			
		}else{
			if (editTextAndTextView.et_suggest_content.getText().length() > 0) {
				
				if (isadd) {
					// ��������
					((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
//					if ((int) AlartCommenSetTools.ID > AlartTools.MaxID_Alart.MAXID_REMIND) {
//						// �������
//						ToastTools.short_Toast(activity, "��ӵ�����������!!");
//					} else {
						RemindEntity re = new RemindEntity(
								(int) AlartCommenSetTools.ID,
								AlarmEntity.ISVALID_YES,
								Tools.time,
								editTextAndTextView.et_suggest_content.getText()
										.toString());
						HashMap<String, Object> temp = new HashMap<String, Object>();
						temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE,
								AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
						temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
								re);
						AlartTools.alartAdapter_content.add(temp);
						MyAlartJsonOptions
								.setAlartData(
										AlartTools.alartAdapter_content,
										new int[] { AlartTools.alartAdapter_content
												.size() - 1 },
										AlartTools.AlartType.JSON_ALART_TYPE_REMIND,
										Tools.STATUS_UPDATE_FAILED, "��ӱ�������ɹ�",
										AlartTools.AlartType.OPTIONS_INSERT);
					//}

				} else {
					// �༭
					// ������޸�
					((RemindAlartActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
					RemindEntity re = new RemindEntity(
							(int) AlartCommenSetTools.ID,
							AlartTools.remindEntity.getIs_valid(),
							Tools.time,
							editTextAndTextView.et_suggest_content.getText()
									.toString());
					HashMap<String, Object> temp = new HashMap<String, Object>();
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE,
							AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY, re);
					AlartTools.alartAdapter_content.set(index, temp);
					MyAlartJsonOptions.setAlartData(
							AlartTools.alartAdapter_content, new int[] { index },
							AlartTools.AlartType.JSON_ALART_TYPE_REMIND,
							Tools.STATUS_UPDATE_FAILED, "�޸ı�������ɹ�",
							AlartTools.AlartType.OPTIONS_UPDATE);
				}
				ToastTools.short_Toast(activity, Tools.OPTION_SAVING);
			} else {
				NotifyDialog.SimpleAlertDialog(activity,
						"��������ı��ⲻ��Ϊ�գ����������Զ�������ٱ��棡");
			}
			}
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub

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
