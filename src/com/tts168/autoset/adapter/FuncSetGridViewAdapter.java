package com.tts168.autoset.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 高级设置的GridView的Adapter
 * @author 袁剑
 * 20150115 20：51
 *
 */
public class FuncSetGridViewAdapter extends BaseAdapter {
	Context context;
	ArrayList<HashMap<String, Object>> al_info;
	LayoutInflater lf;
	SparseArray<View> map = new SparseArray< View>();
	public FuncSetGridViewAdapter(Context context,ArrayList<HashMap<String, Object>> al_info) {
		// TODO Auto-generated constructor stub
		this.al_info=al_info;
		this.context=context;
		lf=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.al_info==null?0:this.al_info.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.al_info.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public class MyView{
		private TextView tv_setname;
		private ImageView iv_set;
		
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyView mv;
		String newmPinyin=" ";
		if(map.get(position)==null){
			/**
			 * Adapter通知更新它不会给以回应
			 */
					mv=new MyView();
		convertView=lf.inflate(R.layout.gv_frame1_item,null);
		mv.tv_setname=(TextView) convertView.findViewById(R.id.textView_gv_frame1_item1);
  		mv.iv_set=(ImageView) convertView.findViewById(R.id.iv_frame);
	    map.put(position,convertView );
		convertView.setTag(mv);
		}else{
			convertView=map.get(position);
			mv =  (MyView) convertView.getTag();
		
		}
		mv.iv_set.setImageResource(  (Integer) this.al_info.get(position).get("drawable"));
		mv.tv_setname.setText((CharSequence) this.al_info.get(position).get("name"));
		return convertView;
	}

}
