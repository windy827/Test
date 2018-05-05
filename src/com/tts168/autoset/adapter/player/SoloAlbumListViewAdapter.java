package com.tts168.autoset.adapter.player;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.player.PlayerTools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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

/**
 * ��ʾ�豸�б�Ľ����Adapter
 * @author Ԭ��
 *
 */
public class SoloAlbumListViewAdapter extends BaseAdapter{
	
	ListView currentListView;//��ǰ������ʾ��ListView�ؼ�
	private ArrayList<HashMap<String,Object>>info;//ȡ�õ������������������Ϣ�������豸����
	SoloAlbumListViewAdapter instatnce;
	LayoutInflater lf;
	private Context c;
	 TextView tv_Name;
	 Activity currrentActivity;
	 SparseArray<View> map ;
	 /**
	  * 
	  * @param a
	  * @param c
	  * @param info
	  * @param tv_deviceName ��ǰ�豸������ 
	  * @param lv
	  */
	public SoloAlbumListViewAdapter(Activity a,ArrayList<HashMap<String,Object>>info,TextView tv_Name){
		this.c=a;
		instatnce=this;
		currrentActivity=a;
		this.tv_Name=tv_Name;
		this.info=info;
		map= new SparseArray<View>();
		lf=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	public class MyView{
		
		private TextView tv_albumname;//ר������
		
		private TextView tv_createTime;//ר������ʱ��
		
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyView mv;	
		if(map.get(position)==null){
		mv=new MyView();
		convertView=lf.inflate(R.layout.listitem_soloalbum,null);
//		if(position%2==0){
//			convertView.setBackgroundColor(Color.argb(17, 0, 0, 0));
//		}
//		
		mv.tv_albumname=(TextView) convertView.findViewById(R.id.tv_listitem_soloalbum_albumname);
		mv.tv_createTime=(TextView) convertView.findViewById(R.id.tv_listitem_soloalnum_date);

		
		map.put(position,convertView );
		convertView.setTag(mv);
		}else{
			convertView=map.get(position);
			mv =  (MyView) convertView.getTag();
		
		}
		String name=(String) info.get(position).get(PlayerTools.PlayerKeys.KEY_ALBUM_NAME);
		String createTime=(String) info.get(position).get(PlayerTools.PlayerKeys.KEY_ALBUM_CREATETIME);
		mv.tv_albumname.setText(name);
		mv.tv_createTime.setText(createTime);
		return convertView;
	}



}
