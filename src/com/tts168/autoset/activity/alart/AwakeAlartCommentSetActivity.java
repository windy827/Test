package com.tts168.autoset.activity.alart;

import java.util.ArrayList;

import com.autoset.jni.getupset.GetUpSetOption;
import com.autoset.jni.wakeup.WakeUpEntity;
import com.autoset.json.JsonParseOption;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.SendDataTools;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.WiperSwitch;
import com.tts168.autoset.view.WiperSwitch.OnChangedListener;
import com.tts168.autoset.view.alart.AlartChooseDialogView;
import com.tts168.autoset.view.title.TitleView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.AbsListView.OnScrollListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AbsListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ��ͨ������
 * @author Ԭ��
 *
 */
public class AwakeAlartCommentSetActivity extends MyBaseActivity implements OnClickListener, android.widget.RadioGroup.OnCheckedChangeListener {
	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE="��ͨ������";
	
	public static final String ActivityName="AwakeAlartCommentSetActivity";
	
	RelativeLayout rl_sleep;//С˯ʱ��
	TextView tv_sleep;
	RelativeLayout rl_ring;//����ѡ��
	TextView tv_ring;
	RelativeLayout rl_ringtime;//����ʱ��
	TextView tv_ringtime;
	
	Button bt_save;//����
	public  WaitView waitView;// �ȴ�����
	public static String ringName="";//��������
	/**
	 * ��Ϣ���ش򿪻�رն�Ӧ��ֵ
	 */
	int []msgOpen;
	/**
	 * ͳͳ����2
	 */
	CommentSet2 commentSet2;
	/**
	 * �������ͨ������	 
	 * @author Ԭ��
	 *
	 */
	public class CommentSet2{
		WiperSwitch ws1,ws2,ws3,ws4,ws5,ws6;
		/**
		 * ���ص��ܸ���
		 */
		public static final int SwitchTotal=6;
		WiperSwitch[]wss;
		RadioGroup rg;
		RadioButton cb_none,cb_once,cb_twice;
		RadioButton cbs[];
		/**
		 * �ϸ���ѭ������/����һ��/�������ε�˳��
		 */
		 double[]MSG_OPEN=new double[]{0,1,2};
	}
	
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
			tv_ring.setText(ringName);
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
//		Intent intent=getIntent();
//		DeviceEntity de=(DeviceEntity) intent.getSerializableExtra("deviceinfo");
		
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		bt_save=(Button) findViewById(R.id.bt_model_save_save);
		waitView = new WaitView(this);
		rl_sleep=(RelativeLayout) this.findViewById(R.id.rl_sleep_model_activity_awakealart_commentset);
		tv_sleep=(TextView) this.findViewById(R.id.tv_sleep_model_activity_awakealart_comment);
		rl_ring=(RelativeLayout) this.findViewById(R.id.rl_ring_model_activity_awakealart_commen);
		tv_ring=(TextView) this.findViewById(R.id.tv_ring_model_activity_awakealart_comment);
		rl_ringtime=(RelativeLayout) this.findViewById(R.id.rl_ringtime_model_activity_awakealart_comment);
		tv_ringtime=(TextView) this.findViewById(R.id.tv_ringtime_model_activity_awakealart_comment);
		
		
		commentSet2=new CommentSet2();
		commentSet2.ws1=(WiperSwitch) findViewById(R.id.switch_activity_awawke_comment1);
		commentSet2.ws2=(WiperSwitch) findViewById(R.id.switch_activity_awawke_comment2);
		commentSet2.ws3=(WiperSwitch) findViewById(R.id.switch_activity_awawke_comment3);
		commentSet2.ws4=(WiperSwitch) findViewById(R.id.switch_activity_awawke_comment4);
		commentSet2.ws5=(WiperSwitch) findViewById(R.id.switch_activity_awawke_comment5);
		commentSet2.ws6=(WiperSwitch) findViewById(R.id.switch_activity_awawke_comment6);
		commentSet2.wss=new WiperSwitch[]{commentSet2.ws6,commentSet2.ws5,commentSet2.ws4,commentSet2.ws3,commentSet2.ws2,commentSet2.ws1};
		commentSet2.rg=(RadioGroup) findViewById(R.id.rg_activity_awakeset_comment);
		commentSet2.cb_none=(RadioButton) findViewById(R.id.cb_activity_awakeset_comment1);
		commentSet2.cb_once=(RadioButton) findViewById(R.id.cb_activity_awakeset_comment2);
		commentSet2.cb_twice=(RadioButton) findViewById(R.id.cb_activity_awakeset_comment3);
		commentSet2.cbs=new RadioButton[]{commentSet2.cb_none,commentSet2.cb_once,commentSet2.cb_twice};
		msgOpen=AlartTools.Cycle.getLastIntArrray((int) AlartTools.getUpSetEntity.getMessage_open(),CommentSet2.SwitchTotal);
		double msgtimes=AlartTools.getUpSetEntity.getMessage_times();
		for(int i=0;i<commentSet2.MSG_OPEN.length;i++){
			if(commentSet2.MSG_OPEN[i]==msgtimes){
				commentSet2.cbs[i].setChecked(true);
			}else{
				commentSet2.cbs[i].setChecked(false);
			}
		}
		ringName=AlartCommenSetTools.getSongNameBySongPath(AlartTools.getUpSetEntity.getRing_path());
		tv_ring.setText(ringName);
		tv_sleep.setText("ÿ"+((int)AlartTools.getUpSetEntity.getDelay_time())+"������һ�Σ���"+((int)AlartTools.getUpSetEntity.getDelay_num())+"��");
		tv_ringtime.setText((int)AlartTools.getUpSetEntity.getPlay_time()+"��");
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		commentSet2.rg.setOnCheckedChangeListener(this);
		for(int i=0;i<commentSet2.wss.length;i++){
			if(msgOpen[i]==0){
				commentSet2.wss[i].setChecked(false);
			}else{
				commentSet2.wss[i].setChecked(true);
			}
			commentSet2.wss[i].setOnChangedListener(new MyOnChangedListener(i));
		}
		bt_save.setOnClickListener(this);
		rl_sleep.setOnClickListener(this);
		rl_ring.setOnClickListener(this);
		rl_ringtime.setOnClickListener(this);
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
		return R.layout.activity_awakealart_commenset;
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
				case R.id.rl_sleep_model_activity_awakealart_commentset:
					// С˯
					AlartNotifyDialog.chooseSleepAlartDialog(this, tv_sleep, (int)AlartTools.getUpSetEntity.getDelay_time(), (int)AlartTools.getUpSetEntity.getDelay_num());
					break;
				case R.id.rl_ring_model_activity_awakealart_commen:
					// ����
					//ѡ����Ŀǰ��û�жԽӺã��ݲ�ʵ��
					//AlartNotifyDialog.chooseRingDialog(this, tv_ring);
					ActivitySetting.startUnfinishedActivity(this, RingChooseActivity.class, null, false);
					break;
				case R.id.rl_ringtime_model_activity_awakealart_comment:
					//����ʱ��
					AlartNotifyDialog.choosePlayTimeDialog(this, tv_ringtime);
					break;
				case R.id.bt_model_save_save:
					waitView.setPBVisable();
					String content=GetUpSetOption.setGetUpSet(AlartTools.getUpSetEntity, JsonParseOption.SET_USERDATA, "�޸���ͨ������ʧ�ܣ�","�޸���ͨ�����óɹ���");
					
					ArrayList<String>contents=new ArrayList<String>();
					contents.add(content);
					TCPTools.sendTcp(contents, Tools.Current_SocketIP,true);
					ToastTools.short_Toast(this, "������...");
					break;
				}
		}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//�������
		//ToastTools.short_Toast(instatance, "����ɹ�!!");
	}
	

	/**
	 * Я����Ϣ���ز������������͵Ŀ�������
	 * @author Ԭ��
	 *
	 */
	public class MyOnChangedListener implements OnChangedListener{

		int index;
		public MyOnChangedListener(int index){
			this.index=index;
		}
		@Override
		public void OnChanged(boolean CheckState) {
			// TODO Auto-generated method stub
			if (CheckState) {
				msgOpen[index]=1; 
				
			} else {
				msgOpen[index]=0;
			}
			//����Message_open��ֵ
			int messageopen=AlartTools.Cycle.binarayInt2int(msgOpen);
			AlartTools.getUpSetEntity.setMessage_open(Double.parseDouble(messageopen+""));
		}
		
	}
	/**
	 * Я����Ϣ���Ŵ���
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		
		switch(checkedId){
		case R.id.cb_activity_awakeset_comment1:
			AlartTools.getUpSetEntity.setMessage_times(commentSet2.MSG_OPEN[0]);
			break;
		case R.id.cb_activity_awakeset_comment2:
			AlartTools.getUpSetEntity.setMessage_times(commentSet2.MSG_OPEN[1]);
			break;
		case R.id.cb_activity_awakeset_comment3:
			AlartTools.getUpSetEntity.setMessage_times(commentSet2.MSG_OPEN[2]);
			break;
		}
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
