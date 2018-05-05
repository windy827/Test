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
 * ѡ��ʡ�ݳ��е�SortListDialog
 * @author Ԭ��
 *
 */
public class SortListDialog {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	
	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	/**
	 * ����ƴ��������ListView�����������
	 */
	private PinyinComparator pinyinComparator;
	public Activity instance;
	public LayoutInflater inflater;
	public static int type;//��������
	public static Button bt_v;//��ǰ�����Button
	public static TextView tv;//��ʾ��ǰ�ĳ��е�TextView
	public static Button bt_City_or_Pr;//ѡ����л�ʡ�ݺ���һ��Button��ʾ������
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
		//ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) view1.findViewById(R.id.sidrbar);
		dialog = (TextView) view1.findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		//�����Ҳഥ������
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {
				//����ĸ�״γ��ֵ�λ��
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
				//����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
				//Toast.makeText(instance.getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
			}
		});
		 //ʡ�ݵ���ͼ
		 if(type==CitySetTools.PRO_TAG){
			 SourceDateList = filledData(ProviceAndCityResource.Provice);
		}
		 //�е���ͼ
		else{
			SourceDateList = filledData(ProviceAndCityResource.getCityArrayByProviceName(bt_City_or_Pr.getText().toString()));
		}
		//SourceDateList = filledData(ProviceAndCityResource.Provice);
		
		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(instance, SourceDateList);
		sortListView.setAdapter(adapter);
		
		
		mClearEditText = (ClearEditText) view1.findViewById(R.id.filter_edit);
		
		//�������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
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
	 * ΪListView�������
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String [] date){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		for(int i=0; i<date.length; i++){
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			//����ת����ƴ��
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
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
	 * ����������е�ֵ���������ݲ�����ListView
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
		
		// ����a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
}
