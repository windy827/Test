package com.tools.sortlistview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.larkiv.larksmart7618.R;
import com.tools.sortlistview.SideBar.OnTouchingLetterChangedListener;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.highcset.cityset.CitySetTools;
import com.tts168.autoset.tools.highcset.cityset.ProviceAndCityResource;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 选择省份城市的SortListDialog
 * @author 袁剑
 *
 */
public class SortListDialog {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;
	public Activity instance;
	public LayoutInflater inflater;
	public static int type;//数据类型
	public static Button bt_v;//当前点击的Button
	public static TextView tv;//显示当前的城市的TextView
	public static Button bt_City_or_Pr;//选择城市或省份后另一个Button显示的内容
	public SortListDialog(Activity instance,final int type,Button bt_v,TextView tv,Button bt_City_or_Pro){
		this.instance=instance;
		this.type=type;
		this.bt_v=bt_v;
		this.tv=tv;
		this.bt_City_or_Pr=bt_City_or_Pro;
		inflater=LayoutInflater.from(instance);
		
	}
	public View getView() {
		View view1=inflater.inflate(R.layout.activity_test, null); 
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) view1.findViewById(R.id.sidrbar);
		dialog = (TextView) view1.findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		//设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
				
			}
		});
		
		sortListView = (ListView) view1.findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(type==CitySetTools.PRO_TAG){
					CitySetTools.PROVICE=((SortModel)adapter.getItem(position)).getName();
					CitySetTools.City_index=position;
					CitySetTools.CITY=ProviceAndCityResource.getCityArrayByProviceName(CitySetTools.PROVICE)[0];
					bt_City_or_Pr.setText(CitySetTools.CITY);
				}else{
					CitySetTools.CITY=((SortModel)adapter.getItem(position)).getName();
					bt_City_or_Pr.setText(ProviceAndCityResource.getProviceBaseOnCityName(CitySetTools.CITY));
				}
				  
		            	 if(type==CitySetTools.PRO_TAG){
		            	 bt_v.setText(CitySetTools.PROVICE);
		            	 if(tv!=null){
		            		 tv.setText(CitySetTools.CITY);
		            	 }
		            	
		            	 }
		            	 else if(type==CitySetTools.CITY_TAG){
			            	 bt_v.setText(CitySetTools.CITY);
			            	 if(tv!=null){
			            	 tv.setText(CitySetTools.CITY);
			            	 }
			            	 }		            		      
				NotifyDialog.dialogBuilderChoose.dismiss();
				NotifyDialog.dialogBuilderChoose=null;
				//这里要利用adapter.getItem(position)来获取当前position所对应的对象
				//Toast.makeText(instance.getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
			}
		});
		 //省份的视图
		 if(type==CitySetTools.PRO_TAG){
			 SourceDateList = filledData(ProviceAndCityResource.Provice);
		}
		 //市的视图
		else{
			SourceDateList = filledData(ProviceAndCityResource.getCityArrayByProviceName(bt_City_or_Pr.getText().toString()));
		}
		//SourceDateList = filledData(ProviceAndCityResource.Provice);
		
		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(instance, SourceDateList);
		sortListView.setAdapter(adapter);
		
		
		mClearEditText = (ClearEditText) view1.findViewById(R.id.filter_edit);
		
		//根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		return view1;
	}


	/**
	 * 为ListView填充数据
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String [] date){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		for(int i=0; i<date.length; i++){
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			//汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// 正则表达式，判断首字母是否是英文字母
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			
			mSortList.add(sortModel);
		}
		return mSortList;
		
	}
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
}
