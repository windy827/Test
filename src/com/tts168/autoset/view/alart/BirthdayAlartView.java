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
 * Alarm的核心部分界面空间的设置
 * @author 袁剑
 *
 */
public class BirthdayAlartView extends MyBaseActivityView{

	//最低端的按钮
	public Button bt_save;
	
	

	/**
	 * 当前界面是否为添加界面
	 */
	boolean isadd=true;
	/**
	 * 当前的实体类是AlartTools.alartAdapter_content里面的第几个索引，方便更新
	 */
	int index;
	private RelativeLayout rl_cycle;//周期
	private TextView tv_cycle;
	
	View view;
	AlartTitleView alartTitleView;//标题界面
	Context context;
	
	public BirthdayAlartView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
/**
 * 设置是否为添加
 * @param isAdd
 * @param ae没有【即为添加状态】可以填null
 * @param index没有【即为添加状态】可以填0
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
			alartTitleView.setEitTextHinit("请输入寿星的名字或称呼");
			timeView=view.findViewById(R.id.ll_timelayout);
			EditTimeLayoutView.isLunar = false;// 默认为不选中
			EditTimeLayoutView.useMyDate = false;// 默认不使用
			
			// 如果是编辑就显示标题栏，如果是添加就隐藏编辑栏
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
	 * 得到View，使用此方法前请先调用setIsAdd方法进行初始化
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
		//保存操作
		if(alartTitleView.et_title.getText().length()>0){
			/**
			 * 得到当前时间
			 */
			
			if(Tools.time.contains("年")){
				if(AlartCommenSetTools.is_lunar==BirthDayEntity.DATE_FORMATE_Lunar){
					Tools.time=AlartTools.BirthDay.convertLunarDataToSolardata(Tools.time);
				}else{
					Tools.time=AlartTools.BirthDay.convertSolarDataToMydata(Tools.time);
				}
				
			}
			
			//保存按钮
			if(isadd){
				//如果是添加
				((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
//				if((int)AlartCommenSetTools.ID>AlartTools.MaxID_Alart.MAXID_BIRTH){
//					//不能添加
//					ToastTools.short_Toast(activity, "添加的闹铃数已满!!");
//				}else{
					BirthDayEntity bde=new BirthDayEntity((int)AlartCommenSetTools.ID,alartTitleView.et_title.getText().toString(), AlartCommenSetTools.is_lunar,Tools.time );
					HashMap<String,Object>temp=new HashMap<String, Object>();
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE, AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY);
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY, bde);
					AlartTools.alartAdapter_content.add(temp);
					MyAlartJsonOptions.setAlartData(AlartTools.alartAdapter_content,  new int[]{AlartTools.alartAdapter_content.size()-1},AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY,Tools.STATUS_UPDATE_FAILED,"添加成功,已为您设置了"+ MyTools.getDoubQuot(alartTitleView.et_title.getText().toString())+"的生日闹铃",AlartTools.AlartType.OPTIONS_INSERT);
			//	}
				
			}else{
				//编辑
				//如果是修改
				BirthdayAlartActivity.waitView.setPBVisable();
				BirthDayEntity bde=new BirthDayEntity((int)AlartCommenSetTools.ID,alartTitleView.et_title.getText().toString(), AlartCommenSetTools.is_lunar, Tools.time);
				HashMap<String,Object>temp=new HashMap<String, Object>();
				temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE, AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY);
				temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY, bde);
				AlartTools.alartAdapter_content.set(index, temp);
				MyAlartJsonOptions.setAlartData(AlartTools.alartAdapter_content, new int[]{index},AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY,Tools.STATUS_UPDATE_FAILED,"修改成功,已为您设置了"+ MyTools.getDoubQuot(alartTitleView.et_title.getText().toString())+"的生日闹铃",AlartTools.AlartType.OPTIONS_UPDATE);
			}
			ToastTools.short_Toast(activity, Tools.OPTION_SAVING);
		}
		else {
			NotifyDialog.SimpleAlertDialog(activity,
					"寿星的称呼不能为空，请先输入自定义标题再保存！");
		}
		
			
	}
	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 编辑时间的Layout2
	 * 
	 * @author 袁剑
	 * 
	 */
	public static class EditTimeLayoutView {
		/**
		 * //WheelView标记的是时间还是日期
		 */
		static int flag = 0;
		/**
		 * //是否是阴历
		 */
		static boolean isLunar;
		/**
		 * //是否使用我定义的日期时间
		 */
		static boolean useMyDate;
		/**
		 * //传到WheelView的想要它显示时间日期，有一定的格式要求
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
