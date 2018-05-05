package com.tts168.autoset.activity.alart;

import java.util.HashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amo.demo.wheelview.WheelViewUse;
import com.autoset.jni.alarm.AlarmEntity;
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
import com.tts168.autoset.tools.others.converopt.BinaryIntArray2ByteTools;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.alart.AlartBottomView;
import com.tts168.autoset.view.alart.AlartCloseAndDelView;
import com.tts168.autoset.view.alart.AlartTitleView;
import com.tts168.autoset.view.alart.AwakeAlartView;
import com.tts168.autoset.view.alart.SleepAlartView;
import com.tts168.autoset.view.title.TitleView;

public class SleepAlartEditActivity extends MyBaseActivity implements OnClickListener{
	TitleView titleView;//标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE="睡前闹铃";
	
	public static final String ActivityName="SleepAlartEditActivity";
	View view_title;//标题栏
	AlartCloseAndDelView alartCloseAndDelView;//关闭和删除界面
	
		
	SleepAlartView sleepAlartView;//睡眠闹铃的主要界面
	/**
	 * 当前界面是否为添加界面
	 */
	boolean isadd;
	/**
	 * 当前的实体类是AlartTools.alartAdapter_content里面的第几个索引，方便更新
	 */
	int index;
	
	public  WaitView waitView;// 等待数据
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
	 * 提供给外部的刷新接口
	 */
		public static void refresh(){
		
		}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		//接受实体类
		Intent intent=getIntent();
		isadd=intent.getExtras().getBoolean(AlartTools.IntentKey.INTENT_ISADD);
		index=intent.getExtras().getInt(AlartTools.IntentKey.INTENT_INDEX);
		AlartTools.alarmEntity=(AlarmEntity) intent.getExtras().getSerializable(AlartTools.IntentKey.INTENT_ENTITY_ALART);
		
		view_title=findViewById(R.id.indlude_activity_awakealart_title);
		titleView=new TitleView(this);		
		//titleView.setSaveText(Tools.TITLE_SAVE);
		titleView.setTitle(TITLE);
		waitView = new WaitView(this);
		alartCloseAndDelView=new AlartCloseAndDelView(this);	
		sleepAlartView=new SleepAlartView(this);
		sleepAlartView.setIsAdd(isadd, AlartTools.alarmEntity,index);
		view_title.setVisibility(View.VISIBLE);	
		if(AlartTools.alarmEntity.getIs_valid()==AlarmEntity.ISVALID_YES){
			alartCloseAndDelView.setOpenAlartTextAndDrawable(true);
		}else{
			alartCloseAndDelView.setOpenAlartTextAndDrawable(false);
		}
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
		return R.layout.activity_awakealart;
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
					if(AlartTools.alarmEntity.getIs_valid()==AlarmEntity.ISVALID_YES){
						//AlartTools.alarmEntity.setIs_valid(AlarmEntity.ISVALID_NO);
						isOpen=false; 
						closeOrOpenFaild="关闭闹铃失败";
						closeOrOpenSucess="关闭闹铃成功"+"已为您关闭了"+MyTools.getDoubQuot(AlartTools.alarmEntity.getClock().substring(0, 5))+"的睡前闹铃";
					}else{
						//AlartTools.alarmEntity.setIs_valid(AlarmEntity.ISVALID_YES);
						isOpen=true; 
						closeOrOpenFaild="开启闹铃失败";
						closeOrOpenSucess="开启闹铃成功"+"已为您开启了"+MyTools.getDoubQuot(AlartTools.alarmEntity.getClock().substring(0, 5))+"的睡前闹铃";
					}
					//alartCloseAndDelView.setOpenAlartTextAndDrawable(isOpen);
					HashMap<String,Object>temp=new HashMap<String, Object>();
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_TYPE, AlartTools.AlartType.JSON_ALART_TYPE_ALART);
					temp.put(AlartTools.AlartListViewAdapterTools.KEY_ENTITY, AlartTools.alarmEntity);
					AlartTools.alartAdapter_content.set(index, temp);
					AlartNotifyDialog.openOrCloseAlartDialog(this,isOpen,AlartTools.alartAdapter_content,new int[]{index}, AlartTools.AlartType.JSON_ALART_TYPE_ALART,closeOrOpenFaild,closeOrOpenSucess,AlartTools.AlartType.OPTIONS_UPDATE);
					/**
					 * 销毁当前Activity，进入AlartActivity
					 */
				//	this.finish();
					break;
				case R.id.iv_acticity_awakealart_del:
					//删除
					AlartNotifyDialog.deleteAlartDialog(this,this, AlartTools.alartAdapter_content, index, AlartTools.AlartType.JSON_ALART_TYPE_ALART);
					break;
					
					
				}
		}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//保存操作
		//保存按钮
		
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}
	


}
