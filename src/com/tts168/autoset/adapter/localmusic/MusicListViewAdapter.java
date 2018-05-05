package com.tts168.autoset.adapter.localmusic;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;


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

/**�µ�
 * ��ʾ���������б�Ľ����Adapter
 * @author Ԭ��
 *
 */
public class MusicListViewAdapter extends BaseAdapter{
	
	ListView currentListView;//��ǰ������ʾ��ListView�ؼ�
	private ArrayList<HashMap<String,Object>>info;//ȡ�õ������������������Ϣ�������豸����

	LayoutInflater lf;
	private Context c;
	TextView tv_deviceName;
	
	SparseArray<View> map = new SparseArray<View>();
	 /**
	  * 
	  * @param a
	  * @param c
	  * @param info
	  * @param tv_deviceName ��ǰ�豸������ 
	  * @param lv
	  */
	public MusicListViewAdapter(Context c,ArrayList<HashMap<String,Object>>info){
		this.c=c;
		//this.currentListView=lv;
		
		this.info=info;
		lf=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	public class MyView{
		
		private TextView tv_name;//������
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (this.info==null?0:this.info.size());
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
		convertView=lf.inflate(R.layout.listitem_localmusic,null);
//		if(position%2==0){
//			convertView.setBackgroundColor(Color.argb(17, 0, 0, 0));
//		}
		
		mv.tv_name=(TextView) convertView.findViewById(R.id.tv_musicname);
		
		String filename=(String) info.get(position).get("musicName");
		
		mv.tv_name.setText(filename);

		
  		map.put(position,convertView );
		convertView.setTag(mv);
		}else{
			convertView=map.get(position);
			mv =  (MyView) convertView.getTag();
		
		}
		
		return convertView;
	}



}
