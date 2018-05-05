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
	int index=-1;//��ʼ��û���κ�Item��ѡ��
	ListView sortListView;
	public MusicSortAdapter(Context mContext, List<MusicSortModel> al_info,ListView sortListView) {
		this.mContext = mContext;
		this.al_info = al_info;
		this.sortListView=sortListView;
	}
	
	/**
	 * ��al_info View���ݷ����仯ʱ,���ô˷��������� View
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
		
		this.notifyDataSetInvalidated();// ˢ�½���
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
		
		//����position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);
		
		//�����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
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
	 * ����al_infoView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return al_info.get(position).getSortLetters().charAt(0);
	}

	/**
	 * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
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
	 * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
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