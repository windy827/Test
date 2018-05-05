package com.tts168.autoset.adapter.localmusic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.larkiv.larksmart7618.R;
import com.larkiv.larksmart7618.R.color;
import com.tools.sortlistview.SortModel;
import com.tts168.autoset.tools.localmusic.MusicSortModel;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class MusicSortAdapter extends BaseAdapter implements SectionIndexer{
	private List<MusicSortModel> al_info = null;
	private Context mContext;
	int index=-1;//初始化没有任何Item被选中
	ListView sortListView;
	public MusicSortAdapter(Context mContext, List<MusicSortModel> al_info,ListView sortListView) {
		this.mContext = mContext;
		this.al_info = al_info;
		this.sortListView=sortListView;
	}
	
	/**
	 * 当al_info View数据发生变化时,调用此方法来更新 View
	 * @param al_info
	 */
	public void updateListView(List<MusicSortModel> al_info){
		this.al_info = al_info;
		notifyDataSetChanged();
		if(index>=0){
			sortListView.setSelection(index);
		}
	}
	public void SetFocus(int index) {
		this.index = index;
		//this.notifyDataSetChanged();
		
		this.notifyDataSetInvalidated();// 刷新界面
		//sortListView.setSelection(index);
		}
	public int getCount() {
		return (this.al_info==null?0:this.al_info.size());
	}
	@Override
	public Object getItem(int position) {
		return al_info.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final MusicSortModel mContent = al_info.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.listitem_localmusic, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_musicname);
			//viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		//根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);
		
		//如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
//		if(position == getPositionForSection(section)){
//			viewHolder.tvLetter.setVisibility(View.VISIBLE);
//			viewHolder.tvLetter.setText(mContent.getSortLetters());
//		}else{
//			viewHolder.tvLetter.setVisibility(View.GONE);
//		}
		if(this.index==position){
			viewHolder.tvTitle.setTextColor(mContext.getResources().getColor(color.blue));
			
		}else{
			viewHolder.tvTitle.setTextColor(mContext.getResources().getColor(color.gray99_color));
		}
		viewHolder.tvTitle.setText(this.al_info.get(position).getName());
		
		return view;

	}
	


	final static class ViewHolder {
		//TextView tvLetter;
		TextView tvTitle;
	}


	/**
	 * 根据al_infoView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return al_info.get(position).getSortLetters().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = al_info.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}