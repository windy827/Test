package com.tts168.autoset.adapter.alart;

import java.util.ArrayList;
import java.util.HashMap;

import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.remind.RemindEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.alart.AlartActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.ToastTools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**新的
 * 显示闹钟列表的界面的Adapter【以后如果涉及到想分类，可以添加筛选按钮】
 * @author 袁剑
 *
 */
public class AlartListViewAdapter extends BaseAdapter{
	
	ListView currentListView;//当前用来显示的ListView控件
	private ArrayList<HashMap<String,Object>>info;//取得到的这次阅览的文字信息，包括设备名称
	AlartListViewAdapter instatnce;
	LayoutInflater lf;
	private Context c;
	 TextView tv_deviceName;
	
	 SparseArray<View>map=new SparseArray< View>();
	 /**
	  * 
	  * @param a
	  * @param c
	  * @param info
	  * @param tv_deviceName 当前设备的名称 
	  * @param lv
	  */
	public AlartListViewAdapter(Context c,ArrayList<HashMap<String,Object>>info){
		this.c=c;
		//this.currentListView=lv;
		instatnce=this;
		
		this.tv_deviceName=tv_deviceName;
		this.info=info;
		lf=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	public class MyView{
		
		private TextView tv_time;//时间
		private TextView tv_incident;//事件
		private RelativeLayout rl_timeAndIncident;//时间和时间的父容器
		
		private TextView tv_cycle;//周期		
		private RelativeLayout rl_cycle;//周期的父容器
		
		private ImageView iv_alart;//闹铃图标
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return info==null?0:info.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return info.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyView mv;	
		if(map.get(position)==null){
		mv=new MyView();
		convertView=lf.inflate(R.layout.listitem_alart,null);
//		if(position%2==0){
//			convertView.setBackgroundColor(Color.argb(17, 0, 0, 0));
//		}
		
		mv.tv_time=(TextView) convertView.findViewById(R.id.tv_listitem_alart_time);
		mv.tv_incident=(TextView) convertView.findViewById(R.id.tv_listitem_alart_incident);
		mv.tv_cycle=(TextView) convertView.findViewById(R.id.tv_listitem_alart_cycle);
		mv.iv_alart=(ImageView) convertView.findViewById(R.id.iv_listitem_alart);
		mv.rl_timeAndIncident=(RelativeLayout) convertView.findViewById(R.id.rl_listitem_timeAndIncident);
		mv.rl_cycle=(RelativeLayout) convertView.findViewById(R.id.rl_listitem_cycle);
		//mv.tv_incident.getPaint().setFakeBoldText(true);//加粗
		int type=(Integer) this.info.get(position).get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
		String time="";
		String incident="";
		int cycle=0;
		int fre_model=0;
		int flagType=0;//判定是五类闹铃中的哪一类闹铃
		double is_valid=AlarmEntity.ISVALID_YES;
		String date="";
		if(type==AlartTools.AlartType.JSON_ALART_TYPE_ALART){
			//Json 域名为Alart
			AlarmEntity ae=(AlarmEntity) this.info.get(position).get(AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
			String title=ae.getTitle();
			time=ae.getClock().substring(0, 5);
			incident=ae.getTitle();
			date=ae.getDate();
			is_valid=ae.getIs_valid();
			cycle=(int) ae.getFrequency();
			fre_model=(int) ae.getFre_mode();
			if(title.equals(AlartTools.AlartTitle.ALART_TITLE_AWAKE)){
				//起床闹铃
				flagType=AlartTools.AlartType.FLAG_ALART_TYPE_AWAKE;
				
			}else if(title.equals(AlartTools.AlartTitle.ALART_TITLE_SLEEP)){
				//睡眠闹铃
				
				flagType=AlartTools.AlartType.FLAG_ALART_TYPE_SLEEP;
			}else{
				//自定义闹铃
			
				flagType=AlartTools.AlartType.FLAG_ALART_TYPE_DEFINED;
			}
			
			
		}else if(type==AlartTools.AlartType.JSON_ALART_TYPE_REMIND){
			//Json 域名为备忘
			fre_model=AlartTools.Cycle.Free_model.FRE_MODEL_ONCE;
			mv.tv_time.setVisibility(View.GONE);
			//mv.rl_cycle.setVisibility(View.GONE);
			RemindEntity re=(RemindEntity) this.info.get(position).get(AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
			is_valid=re.getIs_valid();
			incident=re.getContent();
			date=re.getDate();
			flagType=AlartTools.AlartType.FLAG_ALART_TYPE_REMIND;
		}else if(type==AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY){
			//Json 域名为生日
			mv.tv_time.setVisibility(View.GONE);
			//mv.rl_cycle.setVisibility(View.GONE);
			
			BirthDayEntity be=(BirthDayEntity) this.info.get(position).get(AlartTools.AlartListViewAdapterTools.KEY_ENTITY);
			//incident=be.getWho()+"的生日是"+be.getData_value();
			incident=be.getWho();
			date=be.getData_value().substring(5);
			flagType=AlartTools.AlartType.FLAG_ALART_TYPE_BIRTHDAY;
			
		}
		mv.tv_time.setText(time);
		mv.tv_incident.setText(incident);
		if(fre_model==AlartTools.Cycle.Free_model.FRE_MODEL_ONCE){
			mv.tv_cycle.setText(date);
		}else{
			mv.tv_cycle.setText(AlartTools.Cycle.getWeekCycleByInt(fre_model,cycle));
		}
		
		if(is_valid==AlarmEntity.ISVALID_YES){
			//闹铃开启
			mv.tv_time.setTextColor(Color.RED);
			mv.tv_incident.setTextColor(Color.argb(255, 0, 0, 0));
			mv.tv_cycle.setTextColor(Color.argb(102, 0, 0, 0));
			
			
		}else{
			//闹铃关闭
			mv.tv_time.setTextColor(Color.argb(40, 0, 0, 0));
			mv.tv_incident.setTextColor(Color.argb(40, 0, 0, 0));
			mv.tv_cycle.setTextColor(Color.argb(40, 0, 0, 0));
			convertView.setBackgroundColor(Color.argb(10, 0, 0, 0));
			mv.tv_incident.setText(mv.tv_incident.getText()+"(已关闭)");
		}

		int bg=AlartTools.getBackgroundResourceByFlagAlartType(c, flagType,is_valid);
		mv.iv_alart.setImageResource(bg);
		
		
		
//		mv.tv_incident.setText(incident);
//		mv.tv_cycle.setText(describe);
		
  		map.put(position,convertView );
		convertView.setTag(mv);
		}else{
			convertView=map.get(position);
			mv =  (MyView) convertView.getTag();
		
		}
		
		return convertView;
	}



}
