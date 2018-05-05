package com.tts168.autoset.view.player;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.asyloadImage.AsyncBitmapLoader;
import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.play.PlayItemEntity;
import com.autoset.jni.play.PlayOptions;
import com.autoset.jni.volume.VolumeOptions;
import com.baidu.voicerecognition.android.VoiceRecognitionClient;
import com.baidu.voicerecognition.android.VoiceRecognitionConfig;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;
import com.baiduVoiceCongnition.Config;
import com.baiduVoiceCongnition.Constants;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.localmusic.LocalMusicActivity;
import com.tts168.autoset.activity.player.PlayingActivity;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.MyBaseActivityView;

public class PlayingContentView extends MyBaseActivityView {
	 // 音频获取源  
    private int audioSource = MediaRecorder.AudioSource.MIC;  
    // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025  
    private static int sampleRateInHz = 44100;  
    // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道  
    private static int channelConfig = AudioFormat.CHANNEL_IN_STEREO;  
    // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。  
    private static int audioFormat = AudioFormat.ENCODING_PCM_16BIT;  
    // 缓冲区字节大小  
    private int bufferSizeInBytes = 0;  
    
    TextView tv_songName;
	ImageView iv_playing_song_pic;
	ImageView iv_like;
	ImageView iv_pre, iv_next,iv_stop;
	public ImageView iv_pause;
	
	ImageView iv_search;
	ImageView iv_order;
	ProgressBar pb_progress;
	TextView tv_songname, tv_singer;
	PlayingTitleView ptv;
	ImageView iv_volup,iv_voldown;//音量加减 
	public static final String TITLE="播放控制";
	int isLikeIndex;//是否喜欢的索引，根据这个值来得到显示的图片
	int order;//播放顺序
	public PlayingContentView(MyBaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	public String baiduResult = "";

	private int mCurrentTheme = Config.DIALOG_THEME;

	// ----------------------------------------------------------

	private static final int RECOGNITION_DIALOG = 1;



	private boolean isRecognition = false;


	private static final int POWER_UPDATE_INTERVAL = 100;

	
	// ----------------------------------------------------------
	/**
	 * 设置播放器的内容
	 * @param entity
	 */
	public  void setPlayingContent(AnswerHelperEntity entity){
		if(entity.getStatus().equals(AnswerHelperEntity.STATUS_START)||entity.getStatus().equals(AnswerHelperEntity.STATUS_RESUME)){
			PlayerTools.PlayerViewTools.isplay=false;
		}else{
			PlayerTools.PlayerViewTools.isplay=true;
		}
		setPlaySateByIsPlayFlag(entity);
		setMusicName(entity.getQuestion());
	}
	/**
	 * 根据PlayerTools.PlayerViewTools.isPlay来进行判断
	 */
	public  void setPlaySateByIsPlayFlag(AnswerHelperEntity entity){
		if(PlayerTools.PlayerViewTools.isplay){
			setStatePause();
		}else{
			
			setStatePlay();
		}
		if(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length>0){
			iv_pause.setEnabled(true);
			iv_next.setEnabled(true);
			iv_pre.setEnabled(true);
		}else{
			if(!PlayerTools.PlayerViewTools.isplay){
				if(entity.getStatus().equals(AnswerHelperEntity.STATUS_PAUSE)){
					iv_pause.setEnabled(true);
				}else{
						iv_pause.setEnabled(false);
					
					
					
				}
				
			}
			
			iv_next.setEnabled(false);
			iv_pre.setEnabled(false);
		}
	}
	
	/**
	 * 设置为播放状态，更换相应的图片
	 */
	public  void setStatePlay(){
		PlayerTools.PlayerViewTools.isplay=true;
		iv_pause.setBackgroundResource(R.drawable.player_pause_selector);
		iv_pause.setEnabled(true);
	}
	/**
	 * 设置为暂停状态，更换相应的图片
	 */
	public  void setStatePause(){
		PlayerTools.PlayerViewTools.isplay=false;
		iv_pause.setBackgroundResource(R.drawable.player_play_selector);
		if(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length>0){
			iv_pause.setEnabled(true);
			iv_next.setEnabled(true);
			iv_pre.setEnabled(true);
		}else{
			iv_next.setEnabled(false);
			iv_pre.setEnabled(false);
			if(Tools.answerHelperEntity.getStatus().equals(AnswerHelperEntity.STATUS_PAUSE)){
				iv_pause.setEnabled(true);
			}else{
					iv_pause.setEnabled(false);	
				
			}
			
		}
	}
	public void setMusicName(String name){
		if(!name.equals(tv_songName.getText())){
			tv_songName.setText(name);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//防暴力点击
		switch (v.getId()) {
		case R.id.iv_model_playing_like:
			// 标记喜欢
			if(isLikeIndex==PlayerTools.PlayLike.FLAG_PLAYING_LIKE	){
				isLikeIndex=PlayerTools.PlayLike.FLAG_PLAYING_UNLIKE;	
				ToastTools.short_Toast(activity, "取消标记喜欢");
			}else{
				isLikeIndex=PlayerTools.PlayLike.FLAG_PLAYING_LIKE;
				ToastTools.short_Toast(activity, "标记喜欢成功");
			}
			iv_like.setBackgroundResource(PlayerTools.PlayLike.PLAYING_LIKE_DRAWABLES[isLikeIndex]);
			break;
		case R.id.iv_model_playing_pre:
			// 上一首
			if(PlayerTools.PlayerViewTools.PLAYLIST_URLS!=null&&PlayerTools.PlayerViewTools.PLAYLIST_URLS.length>0){
				//上一曲
				if(PlayerTools.PlayerViewTools.current_index>0){
					PlayerTools.PlayerViewTools.current_index--;
				}else{
					PlayerTools.PlayerViewTools.current_index=PlayerTools.PlayerViewTools.PLAYLIST_URLS.length-1;
				}
				//发送对应的URL信息
				ArrayList<String>content1=new ArrayList<String>();
				ArrayList<PlayItemEntity>playItems=new ArrayList<PlayItemEntity>();
				PlayItemEntity playItemEntity=new PlayItemEntity(PlayItemEntity.TYPE_WEB, PlayerTools.PlayerViewTools.PLAYLIST_URLS[PlayerTools.PlayerViewTools.current_index]);
				playItems.add(playItemEntity);
				String urlsend=PlayOptions.setPlay(new String[]{PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index]},playItems, PlayEntity.METHOD,true);
						
				content1.add(urlsend);
				
				TCPTools.sendTcp(content1, Tools.Current_SocketIP,true);
				if(Tools.CurrentActivityName.equals(PlayingActivity.ActivityName)){
					Tools.answerHelperEntity=new AnswerHelperEntity(1111,  PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index], "暂无内容", "暂无内容", new Date().toString(),AnswerHelperEntity.STATUS_STOP);
					((PlayingActivity)MyApplication.getInstance().getCur_Activity()).pcv.setPlayingContent(Tools.answerHelperEntity);
				}
				
				
				//PlayerTools.PlayerViewTools.isplay=false;
			}else{
				final View v_temp=v;
				new Handler(MyApplication.getInstance().getMainLooper()).postDelayed(new Runnable(){
					public void run() {
						v_temp.setEnabled(false);
				
					}
					}, 1005);
				ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "当前没有可播放的歌曲");
			}
			break;
		case R.id.iv_model_playing_next:
		
			if(PlayerTools.PlayerViewTools.PLAYLIST_URLS!=null&&PlayerTools.PlayerViewTools.PLAYLIST_URLS.length>0)
			{
				
				//下一曲
				if(PlayerTools.PlayerViewTools.current_index<PlayerTools.PlayerViewTools.PLAYLIST_URLS.length-1){
					PlayerTools.PlayerViewTools.current_index++;
				}else{
					PlayerTools.PlayerViewTools.current_index=0;
				}
				//发送对应的URL信息
				ArrayList<String>content1=new ArrayList<String>();
				ArrayList<PlayItemEntity>playItems=new ArrayList<PlayItemEntity>();
				PlayItemEntity playItemEntity=new PlayItemEntity(PlayItemEntity.TYPE_WEB, PlayerTools.PlayerViewTools.PLAYLIST_URLS[PlayerTools.PlayerViewTools.current_index]);
				playItems.add(playItemEntity);
				String urlsend=PlayOptions.setPlay(new String[]{PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index]},playItems, PlayEntity.METHOD,true);
						
				content1.add(urlsend);
				
				TCPTools.sendTcp(content1, Tools.Current_SocketIP,true);
				if(Tools.CurrentActivityName.equals(PlayingActivity.ActivityName)){
					Tools.answerHelperEntity=new AnswerHelperEntity(1111,  PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index], "暂无内容", "暂无内容", new Date().toString(),AnswerHelperEntity.STATUS_STOP);
					((PlayingActivity)MyApplication.getInstance().getCur_Activity()).pcv.setPlayingContent(Tools.answerHelperEntity);
				}
			}else{
				final View v_temp=v;
				new Handler(MyApplication.getInstance().getMainLooper()).postDelayed(new Runnable(){
					public void run() {
						v_temp.setEnabled(false);
				
					}
					}, 1005);
				ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "当前没有可播放的歌曲");
			}
			
			
			break;
		case R.id.iv_model_playing_stop12:
			ArrayList<String>content_stop=new ArrayList<String>();
			content_stop.add(PlayOptions.setStateStop());
			TCPTools.sendTcp(content_stop, Tools.Current_SocketIP,true);
			break;
		case R.id.iv_model_playing_play12:
			//播放或暂停
			if((PlayerTools.PlayerViewTools.PLAYLIST_URLS!=null&&PlayerTools.PlayerViewTools.PLAYLIST_URLS.length>0)||!this.tv_songname.getText().toString().equals("暂无歌曲")){
			ArrayList<String>content=new ArrayList<String>();
			if(PlayerTools.PlayerViewTools.isplay){
				content.add(PlayOptions.setStatePause());
				TCPTools.sendTcp(content, Tools.Current_SocketIP,true);
			}else{
				if(Tools.answerHelperEntity.getStatus().equals(AnswerHelperEntity.STATUS_STOP)){
					//停止状态
				
					//发送对应的URL信息
					if(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&
							PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length>0){
						if(PlayerTools.PlayerViewTools.current_index<0){
							PlayerTools.PlayerViewTools.current_index=0;
						}
						//发送对应的URL信息
						ArrayList<String>content1=new ArrayList<String>();
						ArrayList<PlayItemEntity>playItems=new ArrayList<PlayItemEntity>();
						PlayItemEntity playItemEntity=new PlayItemEntity(PlayItemEntity.TYPE_WEB, PlayerTools.PlayerViewTools.PLAYLIST_URLS[PlayerTools.PlayerViewTools.current_index]);
						playItems.add(playItemEntity);
						String urlsend=PlayOptions.setPlay(new String[]{PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index]},playItems, PlayEntity.METHOD,true);
								
						content1.add(urlsend);
						
						TCPTools.sendTcp(content1, Tools.Current_SocketIP,true);
						
					}else {
						final View v_temp=v;
						new Handler(MyApplication.getInstance().getMainLooper()).postDelayed(new Runnable(){
							public void run() {
								v_temp.setEnabled(false);
						
							}
							}, 1005);
						ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "当前没有可播放的歌曲");
					}
					
				}else{
				content.add(PlayOptions.setStateResume());
				TCPTools.sendTcp(content, Tools.Current_SocketIP,true);
				}
				
			}
		
			}else{
				final View v_temp=v;
				new Handler(MyApplication.getInstance().getMainLooper()).postDelayed(new Runnable(){
					public void run() {
						v_temp.setEnabled(false);
				
					}
					}, 1005);
				ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "当前没有可播放的歌曲");
			}
			
			break;
		
		case R.id.iv_model_playing_voice_search:
			SharedConfig config=new SharedConfig(MyApplication.getInstance().getCur_Activity());
			
			//调用语音识别接口
			break;
		case R.id.iv_model_playing_order:
			// 播放顺序
			if(order<PlayerTools.PlayingOrder.PLAYING_ORDER_DRAWABLES.length-1){
				order++;
				
			}else{
				order=PlayerTools.PlayingOrder.FLAG_PLAYING_ORDER_CYCLE;
			}
			iv_order.setBackgroundResource(PlayerTools.PlayingOrder.PLAYING_ORDER_DRAWABLES[order]);
			ToastTools.short_Toast(activity, PlayerTools.PlayingOrder.PLAYING_ORDERS_INFO[order]);
			break; 
		case R.id.iv_model_playing_volup:
			//音量加
			ArrayList<String>content_volup=new ArrayList<String>();
			content_volup.add(VolumeOptions.sendSetVolumeUp());
			TCPTools.sendTcp(content_volup, Tools.Current_SocketIP,true);
			break;
		case R.id.iv_model_playing_voldown:
			//音量减
			ArrayList<String>content_voldown=new ArrayList<String>();
			content_voldown.add(VolumeOptions.sendSetVolumeDown());
			TCPTools.sendTcp(content_voldown, Tools.Current_SocketIP,true);
			break;
		
		}
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		ptv=new PlayingTitleView((Activity)MyApplication.getInstance().getCur_Activity());
		ptv.setTitle(TITLE);
		//ptv.setLeftImgViewInVisable();
		ptv.setRightImgViewInVisable();//暂时隐藏右侧标题栏点击
		
		
		 tv_songName=(TextView) activity.findViewById(R.id.tv_model_playing_song);
		 
		 iv_playing_song_pic = (ImageView) activity
				.findViewById(R.id.iv_model_playing_pic);
		iv_like = (ImageView) activity.findViewById(R.id.iv_model_playing_like);
		iv_stop = (ImageView) activity.findViewById(R.id.iv_model_playing_stop12);
		iv_pre = (ImageView) activity.findViewById(R.id.iv_model_playing_pre);
		iv_next = (ImageView) activity.findViewById(R.id.iv_model_playing_next);
		iv_pause = (ImageView) activity
				.findViewById(R.id.iv_model_playing_play12);
		
		iv_search = (ImageView) activity
				.findViewById(R.id.iv_model_playing_voice_search);
		iv_order = (ImageView) activity
				.findViewById(R.id.iv_model_playing_order);

		tv_songname = (TextView) activity
				.findViewById(R.id.tv_model_playing_pb_left);
		tv_singer = (TextView) activity
				.findViewById(R.id.tv_model_playing_pb_right);
		pb_progress = (ProgressBar) activity
				.findViewById(R.id.pb_model_playing_play);
		
		iv_volup=(ImageView) activity.findViewById(R.id.iv_model_playing_volup);
		iv_voldown=(ImageView) activity.findViewById(R.id.iv_model_playing_voldown);
		if(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length>0){
			iv_pause.setEnabled(true);
			iv_next.setEnabled(true);
			iv_pre.setEnabled(true);
		}else{
			iv_next.setEnabled(false);
			iv_pre.setEnabled(false);
			if(Tools.answerHelperEntity.getStatus().equals(AnswerHelperEntity.STATUS_PAUSE)){
				iv_pause.setEnabled(true);
			}else{
				iv_pause.setEnabled(false);
			}
			
		}
		//得到当前歌曲是否已经标记为喜欢,然后赋值
		setIsLikeDrawable(false);
				
		//得到当前歌曲是否为随机播放,然后赋值
		setPlayOrder(0);
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		iv_like.setOnClickListener(this);
		iv_stop.setOnClickListener(this);
		iv_pre.setOnClickListener(this);
		iv_next.setOnClickListener(this);
		iv_pause.setOnClickListener(this);
		
		iv_search.setOnClickListener(this);
		iv_order.setOnClickListener(this);
		iv_volup.setOnClickListener(this);
		iv_voldown.setOnClickListener(this);
	
	    
	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 根据当前歌曲是否标记喜欢来确定图片的使用和参数的设置
	 * @param isLike
	 */
	public void setIsLikeDrawable(boolean isLike){
		if(isLike){
			isLikeIndex=PlayerTools.PlayLike.FLAG_PLAYING_LIKE;	
		}else{
			isLikeIndex=PlayerTools.PlayLike.FLAG_PLAYING_UNLIKE;	
		}
		
		iv_like.setBackgroundResource(PlayerTools.PlayLike.PLAYING_LIKE_DRAWABLES[isLikeIndex]);
	}
	
	
	/**
	 * 根据当前歌曲播放顺序的参数来确定图片的使用和参数的设置
	 * @param order 根据PlayerTools里面的	PlayerTools.PlayingOrder.FLAG_PLAYING...
	 */
	public void setPlayOrder(int order){
		this.order=order;
		iv_order.setBackgroundResource(PlayerTools.PlayingOrder.PLAYING_ORDER_DRAWABLES[order]);
	}

}
