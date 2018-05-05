package com.tts168.autoset.view.alart;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
import com.niftydialogeffects.NiftyDialogBuilderChoose;
import com.tts168.autoset.adapter.alart.ChooseRingAdapter;
import com.tts168.autoset.database.terminal.DB_Terminal_Option;
import com.tts168.autoset.tools.SendDataTools;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.SharedPrefenceSetTools;
import com.tts168.autoset.tools.highcset.cityset.ProviceAndCityResource;
import com.tts168.autoset.tools.highcset.presskey.LaughContent;
import com.tts168.autoset.tools.highcset.terminal.TerminalNotifyDialog;
import com.tts168.autoset.tools.others.TimeSetContent;
import com.tts168.autoset.tools.others.time.TimeContent;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.tools.wheelview.WheelViewTools;
import com.tts168.autoset.watcher.ScroListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AlartChooseDialogView {


//	/**
//	 * 铃声选择列表
//	 * 
//	 * @param inflater
//	 * @param tv
//	 *            用于显示铃声设置的TextView
//	 * @return
//	 */
//	public static View ringChooseView(LayoutInflater inflater, final TextView tv) {
//
//		View view1 = inflater.inflate(R.layout.choose_custom_view, null);
//		ListView lv_choose = (ListView) view1.findViewById(R.id.lv_choose);
//		 final ArrayList<HashMap<String, Object>> al = AlartCommenSetTools.getSongInfo();
//		ChooseRingAdapter sa = new ChooseRingAdapter(MyApplication.getInstance().getCur_Activity(), al,tv,lv_choose);
//		lv_choose.setAdapter(sa);
//		lv_choose.setOnScrollListener(new ScroListener(sa));
//		lv_choose.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
//					long arg3) {
//				PreventViolence.preventClick(arg0, PreventViolence.LONG_TIME);//防暴力点击
//				// TODO Auto-generated method stub
//				String text = al.get(index).get(AlartCommenSetTools.KEY_SONGNAME).toString();
//				String path=al.get(index).get(AlartCommenSetTools.KEY_SONGPATH).toString();
//				AlartTools.getUpSetEntity.setRing_path(path);
//				tv.setText(text);
//				if (AlartNotifyDialog.dialogBuilder != null) {
//					AlartNotifyDialog.dialogBuilder.dismiss();
//					AlartNotifyDialog.dialogBuilder = null;
//				}
//
//			}
//
//		});
//
//		return view1;
//	}

	
	

	/**
	 * 周期选择列表
	 * 
	 * @param inflater
	 * @param tv
	 *            用于显示铃声设置的TextView
	 *   @param fre_model 闹铃模式
	 *   @param     frequency 周期对应的值     
	 * @return
	 */
	public static View cycleChooseView(LayoutInflater inflater, final TextView tv) {
		//先初始化变量
		AlartTools.Cycle.week_check=AlartTools.Cycle.getLastIntArrray((int) AlartTools.alarmEntity.getFrequency(),7);
		AlartTools.Cycle.cycleValue = AlartTools.Cycle
				.binarayInt2int(AlartTools.Cycle.week_check);
		AlartTools.Cycle.Fre_model = (int) AlartTools.alarmEntity.getFre_mode();
		AlartTools.Cycle.current_Fre_model=AlartTools.Cycle.Fre_model;
		View view1 = inflater.inflate(R.layout.choose_alart_cycle, null);
		final Button bt_choose_once_time;//选择一次性闹铃的日期
		final RadioGroup rg_cycle;//周期选择的RadioGroup
		final RadioButton cb_once;//一次
		final RadioButton cb_everyday;//每日
		final RadioButton cb_defined;//自定义
		
		final TextView tv_once,tv_everyday,tv_week;
		tv_once=(TextView) view1.findViewById(R.id.tv_alart_cycle_once);
		tv_everyday=(TextView) view1.findViewById(R.id.tv_alart_cycle_everyday);
		tv_week=(TextView) view1.findViewById(R.id.tv_alart_cycle_week);
		
		
		bt_choose_once_time= (Button) view1.findViewById(R.id.bt_choosealart_cycle_oncetime);
		rg_cycle=(RadioGroup) view1.findViewById(R.id.rg_notify_alart_cycle);
		cb_once=(RadioButton) view1.findViewById(R.id.cb_notify_alart_cycle_fremodel);
		cb_everyday=(RadioButton) view1.findViewById(R.id.cb_notify_alart_cycle_everyday);
		bt_choose_once_time.setText(Tools.date);
		
		CheckBox cb_week1,cb_week2,cb_week3,cb_week4,cb_week5,cb_week6,cb_week7;
		final CheckBox[]cb_week;
		cb_defined=(RadioButton) view1.findViewById(R.id.cb_notify_alart_cycle_defined);
		cb_week1=(CheckBox) view1.findViewById(R.id.cb_notify_alart_cycle_week1);
		cb_week2=(CheckBox) view1.findViewById(R.id.cb_notify_alart_cycle_week2);
		cb_week3=(CheckBox) view1.findViewById(R.id.cb_notify_alart_cycle_week3);
		cb_week4=(CheckBox) view1.findViewById(R.id.cb_notify_alart_cycle_week4);
		cb_week5=(CheckBox) view1.findViewById(R.id.cb_notify_alart_cycle_week5);
		cb_week6=(CheckBox) view1.findViewById(R.id.cb_notify_alart_cycle_week6);
		cb_week7=(CheckBox) view1.findViewById(R.id.cb_notify_alart_cycle_week7);
		cb_week=new CheckBox[]{cb_week1,cb_week2,cb_week3,cb_week4,cb_week5,cb_week6,cb_week7};
		
		if((int) AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
			cb_once.setChecked(true);
			bt_choose_once_time.setEnabled(true);
			tv_once.setEnabled(true);
			tv_everyday.setEnabled(false);
			tv_week.setEnabled(false);
		}else if((int) AlartTools.alarmEntity.getFre_mode()==AlartTools.Cycle.Free_model.FRE_MODEL_EVERYDAY){
			cb_everyday.setChecked(true);
			tv_everyday.setEnabled(true);
			
			cb_once.setChecked(false);
			bt_choose_once_time.setEnabled(false);
			tv_once.setEnabled(false);
			
			cb_defined.setChecked(false);
			tv_week.setEnabled(false);
		}else{
			cb_defined.setChecked(true);
			tv_week.setEnabled(true);
			
			cb_everyday.setChecked(false);
			tv_everyday.setEnabled(false);
			
			bt_choose_once_time.setEnabled(false);
			tv_once.setEnabled(false);
			tv_everyday.setEnabled(false);
			
		}
		
		for(int i=0;i<AlartTools.Cycle.week_check.length;i++){
			if(AlartTools.Cycle.week_check[i]==0){
				cb_week[i].setChecked(false);
			}else{
				cb_week[i].setChecked(true);
			}
			if(cb_defined.isChecked()){
				cb_week[i].setEnabled(true);
				
			}else{
				cb_week[i].setEnabled(false);
			}
		}
		
		bt_choose_once_time.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NotifyDialog.ChooseTIMEDialog(MyApplication.getInstance().getCur_Activity(), Tools.FLAG_DATE,false,true,Tools.date,false,
						SharedPrefenceSetTools.STARTTIME, (Button) v,
						Tools.TYPE_SET_FESTIVE,WheelViewTools.COMMENT_HOUR_START,WheelViewTools.COMMENT_HOUR_end);
			}
		});
		rg_cycle.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId){
				case R.id.cb_notify_alart_cycle_fremodel:
					
					if(cb_once.isChecked()){
						tv_once.setEnabled(true);
						bt_choose_once_time.setEnabled(true);
						AlartTools.Cycle.current_Fre_model=AlartTools.Cycle.Free_model.FRE_MODEL_ONCE;
					}else{
						tv_once.setEnabled(false);
						bt_choose_once_time.setEnabled(false);
					}
					break;
				case R.id.cb_notify_alart_cycle_everyday:
					if(cb_everyday.isChecked()){
						tv_everyday.setEnabled(true);
						AlartTools.Cycle.current_Fre_model=AlartTools.Cycle.Free_model.FRE_MODEL_EVERYDAY;
						bt_choose_once_time.setEnabled(false);
					}else{
						tv_everyday.setEnabled(false);
						
					}
					
					break;
				case R.id.cb_notify_alart_cycle_defined:
					if(cb_defined.isChecked()){
						tv_week.setEnabled(true);
						bt_choose_once_time.setEnabled(false);
						AlartTools.Cycle.current_Fre_model=AlartTools.Cycle.Free_model.FRE_MODEL_DEFINED;
					}	else{
						tv_week.setEnabled(false);
					}
					break;
				}
				
				for(int i=0;i<AlartTools.Cycle.week_check.length;i++){
					
					if(cb_defined.isChecked()){
						cb_week[i].setEnabled(true);
					}else{
						cb_week[i].setEnabled(false);
					}
				}
			}
		});
			
		
		for(int i=0;i<cb_week.length;i++){
			cb_week[i].setOnCheckedChangeListener(new MyWeekOnCheckChangedListener(i));
		}
		

		return view1;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------	
		//--------------------------------------------------------------------------------------------------------------------------------	
		//--------------------------------------------------------------------------------------------------------------------------------	
		//--------------------------------------------------------------------------------------------------------------------------------	
		
		
		/**
		 * 闹铃播放时长设置
		 * @param inflater
		 * @param tv 闹铃播放时长的TextView
		 * @return
		 */
				
				public static  View alartPlayTimeChooseview(LayoutInflater inflater,final TextView tv) {
					
					ArrayList<HashMap<String, String>> al_list=AlartCommenSetTools.getPlayTime(MyApplication.getInstance().getCur_Activity());
					
					 View view1=inflater.inflate(R.layout.choose_custom_view, null); 
					 ListView lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
					String []from=new String[]{Tools.SET_WHOLE_KEY};
					int []to=new int[]{R.id.tv_select};
					final ArrayList<HashMap<String, String>>al=al_list;
					SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al, R.layout.choose_dialogselect_item, from, to);
				lv_choose.setAdapter(sa);
				lv_choose.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						String text=al.get(arg2).get(Tools.SET_WHOLE_KEY);
						int time=Integer.parseInt(text.split(TimeSetContent.SPLIT_CHAR)[0]);
						//AlartCommenSetTools.play_time=time;
						AlartTools.getUpSetEntity.setPlay_time(time);
						tv.setText(text);
						AlartNotifyDialog.dialogBuilder.dismiss();
						AlartNotifyDialog.dialogBuilder=null;
					}
					
				});
			
					return view1;
			}
		

}

/**
 * 根据数组对应的下标修改AlartTools.week_check
 * @param index
 */
class MyWeekOnCheckChangedListener implements OnCheckedChangeListener{

	int index;
	/**
	 * 数组对应的下标
	 * @param index
	 */
	public MyWeekOnCheckChangedListener(int index){
		this.index=index;
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView,
			boolean isChecked) {
		// TODO Auto-generated method stub
	
			if(isChecked){
				AlartTools.Cycle.week_check[index]=1;
			}else{
				AlartTools.Cycle.week_check[index]=0;
			}
			
		
	}
	
	

	
}
