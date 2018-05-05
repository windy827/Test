package com.tts168.autoset.view.localmusic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.http.AudioEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.play.PlayItemEntity;
import com.autoset.jni.play.PlayOptions;
import com.larkiv.larksmart7618.R;
import com.tools.sortlistview.CharacterParser;
import com.tools.sortlistview.ClearEditText;
import com.tools.sortlistview.SideBar;
import com.tools.sortlistview.SideBar.OnTouchingLetterChangedListener;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.activity.localmusic.LocalMusicActivity;
import com.tts168.autoset.adapter.localmusic.MusicSortAdapter;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.highcset.cityset.ProviceAndCityResource;
import com.tts168.autoset.tools.localmusic.HttpServer;
import com.tts168.autoset.tools.localmusic.LocalMusicTools;
import com.tts168.autoset.tools.localmusic.MusicLoader.MusicInfo;
import com.tts168.autoset.tools.localmusic.MusicPinyinComparator;
import com.tts168.autoset.tools.localmusic.MusicSortModel;
import com.tts168.autoset.tools.localmusic.SearchFileThread;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.webmusic.WebMusicTools;
import com.tts168.autoset.view.player.PlayerView;

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
 * 手机音乐的SortListDialog
 * 
 * @author 袁剑
 * 
 */
public class MusicSortListDialog {
	public ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private TextView tv_nocontent;
	public  MusicSortAdapter adapter;
	private ClearEditText mClearEditText;

	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<MusicSortModel> SourceDateList;

	public static final String NOCONTENT="未搜索到相关歌曲，请确保手机上存有MP3格式的歌曲，如果存在请尝试重启本软件...";
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private MusicPinyinComparator pinyinComparator;
	public Activity instance;
	public LayoutInflater inflater;
	ArrayList<String> sortdate;
	ArrayList<MusicInfo> al_info;

	public MusicSortListDialog(Activity instance,
			ArrayList<MusicInfo> al_info) {
		this.instance = instance;
		this.al_info = al_info;
		this.sortdate = getSortDataList(al_info);

		inflater = LayoutInflater.from(instance);

	}

	public ArrayList<String> getSortDataList(
			ArrayList<MusicInfo> al_info) {
		ArrayList<String> sort = new ArrayList<String>();
		for (MusicInfo entity : al_info) {
			sort.add((String) entity.getTitle());
		}
		return sort;
	}

	/**
	 * 更新列表
	 * 
	 * @param sortdate
	 */
	public void updateSortlist(ArrayList<MusicInfo> updateal_info) {
		this.al_info = updateal_info;
		if(updateal_info.size()==0){
			tv_nocontent.setVisibility(View.VISIBLE);
		}else{
			tv_nocontent.setVisibility(View.INVISIBLE);
		}
		this.sortdate = getSortDataList(al_info);
		SourceDateList = filledData(this.sortdate, updateal_info);

		// SourceDateList = filledData(ProviceAndCityResource.Provice);

		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new MusicSortAdapter(instance, SourceDateList,sortListView);
		if((PlayerTools.PlayerViewTools.PLAYLIST_URLS!=null&&PlayerTools.PlayerViewTools.PLAYLIST_URLS.length>0&&
				PlayerTools.FLAG_MUSIC==LocalMusicActivity.FLAG_MUSIC_LOCAL)){
			if(PlayerTools.PlayerViewTools.PLAYLIST_URLS[0].equals(SourceDateList.get(0).getUrl())){
				PlayerTools.focusNeedChange=true;
			}
			else{
				PlayerTools.focusNeedChange=false;
			}
			
		}else{
			if(PlayerTools.PlayerViewTools.PLAYLIST_URLS==null){
				PlayerTools.focusNeedChange=true;
				PlayerTools.PlayerViewTools.current_index = -1;
			}else{
				PlayerTools.focusNeedChange=false;
			}
			
			
		}
		sortListView.setAdapter(adapter);
		adapter.updateListView(SourceDateList);
		
	}

	public void getView() {

		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new MusicPinyinComparator();

		sideBar = (SideBar) this.instance.findViewById(R.id.sidrbar);
		dialog = (TextView) this.instance.findViewById(R.id.dialog);
		tv_nocontent= (TextView) this.instance.findViewById(R.id.tv_nocontent);
		tv_nocontent.setText(NOCONTENT);
		sideBar.setTextView(dialog);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) this.instance
				.findViewById(R.id.country_lvcountry);
		
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TO DO将当前的路径转换成可播放的URL给设备进行播放
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				PreventViolence
				.preventClick(parent, PreventViolence.SHORT_TIME);// 防暴力点击
				PlayerTools.focusNeedChange=true;
				PlayerTools.FLAG_MUSIC=LocalMusicActivity.FLAG_MUSIC_LOCAL;
				PlayerTools.PlayerViewTools.PLAYLIST_URLS = new String[SourceDateList
						.size()];
				PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES = new String[SourceDateList
						.size()];
				PlayerTools.PlayerViewTools.PIC_URLS = new String[SourceDateList
						.size()];
				
			
				for (int i = 0; i < SourceDateList.size(); i++) {
					String url = SourceDateList.get(i).getUrl();
					PlayerTools.PlayerViewTools.PLAYLIST_URLS[i] = url;
					String name=SourceDateList.get(i).getName();
					PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[i] = name;
					PlayerTools.PlayerViewTools.PIC_URLS[i] = "";
				}
//				String url = SourceDateList.get(position).getUrl();
//				LocalMusicTools.playLocalMusic(Tools.Current_SocketIP, url);

				
				// 发送对应的URL信息
				adapter.SetFocus(position );// 设置焦点

				PlayerTools.PlayerViewTools.current_index = position;

//				PlayerTools.PlayerViewTools.isplay = false;
//				((LocalMusicActivity)MyApplication.getInstance().getCur_Activity()).pv.tv_music_name
//						.setText(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index]);
				ArrayList<String> content = new ArrayList<String>();
				ArrayList<PlayItemEntity> playItems = new ArrayList<PlayItemEntity>();
				PlayItemEntity playItemEntity = new PlayItemEntity(
						PlayItemEntity.TYPE_WEB,
						PlayerTools.PlayerViewTools.PLAYLIST_URLS[position]);
				playItems.add(playItemEntity);
				String urlsend = PlayOptions
						.setPlay(
								new String[] { PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[position ] },
								playItems, PlayEntity.METHOD, true);
				content.add(urlsend);
				TCPTools.sendTcp(content, Tools.Current_SocketIP, true);
				if(Tools.CurrentActivityName.equals(LocalMusicActivity.ActivityName)){
					Tools.answerHelperEntity=new AnswerHelperEntity(1111,  PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index], "暂无内容", "暂无内容", new Date().toString(),AnswerHelperEntity.STATUS_STOP);
					((LocalMusicActivity)MyApplication.getInstance().getCur_Activity()).pv.setPlayerContent(Tools.answerHelperEntity);
				}
			}
		});

		updateSortlist(this.al_info);

		mClearEditText = (ClearEditText) this.instance
				.findViewById(R.id.filter_edit);

		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
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

	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	private List<MusicSortModel> filledData(ArrayList<String> date,
			ArrayList<MusicInfo> music_info) {
		List<MusicSortModel> mSortList = new ArrayList<MusicSortModel>();

		for (int i = 0; i < date.size(); i++) {
			MusicSortModel sortModel = new MusicSortModel();
			sortModel.setName(date.get(i));
			String path = (String) music_info.get(i).getUrl();
			sortModel.setPath(path);
			sortModel
					.setUrl(HttpServer.getInstance(instance).generateURL(path));
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date.get(i));
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<MusicSortModel> filterDateList = new ArrayList<MusicSortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (MusicSortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
}
