package com.tts168.autoset.view.player;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.asyloadImage.AsyncBitmapLoader;
import com.asyloadImage.AsyncBitmapLoader.ImageCallBack;
import com.autoset.jni.answer.AnswerHelpJsonOption;
import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.http.productinfo.ProductInfoEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.play.PlayItemEntity;
import com.autoset.jni.play.PlayOptions;
import com.autoset.json.MyTools;
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
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.tools.quickset.DeviceSelectTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.tools.webmusic.WebMusicTools;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.view.definedview.RoundAngleImageView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 界面底端的小播放器模板
 * 需要将列表传过来，调用setSongState(int index,String []url,String[]songName)方法
 * @author 袁剑
 *
 */
public class PlayerView extends MyBaseActivityView{

	// 音频获取源  
    private int audioSource = MediaRecorder.AudioSource.MIC;  
    // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025  
    private static int sampleRateInHz = 44100;  
    // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道  
    private static int channelConfig = AudioFormat.CHANNEL_IN_MONO;  
    // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。  
    private static int audioFormat = AudioFormat.ENCODING_PCM_16BIT;  
    // 缓冲区字节大小  
    private int bufferSizeInBytes = 0;  

    RelativeLayout rl_model_player_expand;//展开播放操作界面
	RoundAngleImageView iv_voice_search;//声音识别搜索歌曲
	ImageView iv_stop;//停止键
	ImageView iv_play;//播放键
	ImageView iv_next;//下一首
	TextView tv_singer;//歌手名
	public TextView tv_music_name;//歌曲名称
	
	ProgressBar pb_wait,pb_play;
	RelativeLayout rl_model_player_song;//点击可打开播放界面
	private AsyncBitmapLoader asyncBitmapLoader; 
	
	public String baiduResult = "";

	private int mCurrentTheme = Config.DIALOG_THEME;

	// ----------------------------------------------------------

	private static final int RECOGNITION_DIALOG = 1;


	/** 锟斤拷锟斤拷识锟斤拷锟斤拷 */
	private boolean isRecognition = false;

	/** 锟斤拷锟斤拷锟斤拷锟铰硷拷锟�*/
	private static final int POWER_UPDATE_INTERVAL = 100;

	/** 锟斤拷锟竭筹拷Handler */
	private Handler mHandler;

	private ListFragment mCommandsFragment;

	BaiduASRDigitalDialog mDialog;
	RelativeLayout rl_player;


//	/**
//	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
//	 */
//	private Runnable mUpdateVolume = new Runnable() {
//		public void run() {
//			if (isRecognition) {
//				long vol = mASREngine.getCurrentDBLevelMeter();
//				// mControlPanel.volumeChange((int) vol);
//				mHandler.removeCallbacks(mUpdateVolume);
//				mHandler.postDelayed(mUpdateVolume, POWER_UPDATE_INTERVAL);
//			}
//		}
//	};
//	Handler handler = new Handler() {
//		public void handleMessage(android.os.Message msg) {
//			String content = (String) msg.obj;
//			if(content.length()>0){
//				ArrayList<String>contents=new ArrayList<String>();
//				contents.add(content);
//
//				TCPTools.sendTcp(contents, Tools.Current_SocketIP,true);
//				Log.d("BAIDUVOICE",content);
//			}else{
//				ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "服务器内部错误");
//			}
//			
//			//tv_music_name.setText(content);
//			// tv.setText(content);
//		};
//	};
	
	
	public PlayerView(MyBaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 设置设备正在播放的内容
	 * @param content
	 */
	public void setPlayerContent(String content){
		if(!content.equals(tv_music_name.getText())){
			tv_music_name.setText(content);
		}
		
	}
/**
 * 设置音乐列表以及当前播放的位置以及列表中每一项的歌名以及图片列表
 */
	//public void setSongState(int index){
//		this.cur_song_index=index;
//		this.url=PlayerTools.PlayerViewTools.PLAYLIST_URLS;
//		this.songPic=PlayerTools.PlayerViewTools.PIC_URLS;
//		this.songName=PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES;
//		if(this.url!=null&&this.url.length>0){
//			setPlaySateByIsPlayFlag();
//			tv_music_name.setText(songName[index]);
//			String imageURL=this.songPic[index];
//			 Bitmap bitmap=asyncBitmapLoader.loadBitmap(WebMusicTools.playlistMusic_path,iv_voice_search, imageURL, new ImageCallBack() {  
//	             
//	             @Override  
//	             public void imageLoad(ImageView imageView, Bitmap bitmap) {  
//	                 // TODO Auto-generated method stub  
//	                 imageView.setImageBitmap(bitmap);  
//	             }  
//	         });  
//	          if(bitmap == null)    
//	             {    
//	        	  iv_voice_search.setImageResource(R.drawable.playerdefault);
//	        	 
//	             }    
//	             else    
//	             {    
//	            	 iv_voice_search.setImageBitmap(bitmap);  
//	            	
//	             }    
//			
		//}
		
	//}
	
	/**
	 * 设置播放器的内容
	 * @param entity
	 */
	public  void setPlayerContent(AnswerHelperEntity entity){
		if(entity.getStatus().equals(AnswerHelperEntity.STATUS_START)||entity.getStatus().equals(AnswerHelperEntity.STATUS_RESUME)){
			PlayerTools.PlayerViewTools.isplay=false;
		}else{
			PlayerTools.PlayerViewTools.isplay=true;
		}
		setPlaySateByIsPlayFlag(entity);
		setPlayerContent(entity.getQuestion());
		
	}
	/**
	 * 根据PlayerTools.PlayerViewTools.isPlay来进行判断,以及判断暂停下一首按钮是否可点击
	 */
	public void setPlaySateByIsPlayFlag(AnswerHelperEntity entity){
		if(PlayerTools.PlayerViewTools.isplay){
			setStatePause(entity);
		}else{
			setStatePlay();
		}
		//PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&
		if(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length>0){
			iv_play.setEnabled(true);
			iv_next.setEnabled(true);
		}else{
			if(!PlayerTools.PlayerViewTools.isplay){
				iv_next.setEnabled(false);
				if(entity.getStatus().equals(AnswerHelperEntity.STATUS_PAUSE)){
					iv_play.setEnabled(true);
				}else{
					iv_play.setEnabled(false);
					
				}
				
			}
			
		}
	}
	/**
	 * 设置为播放状态，更换相应的图片
	 */
	public void setStatePlay(){
		PlayerTools.PlayerViewTools.isplay=true;
		iv_play.setBackgroundResource(R.drawable.player_pause_selector);
		
		iv_play.setEnabled(true);
	}
	/**
	 * 设置为暂停状态，更换相应的图片
	 */
	public void setStatePause(AnswerHelperEntity entity){
		PlayerTools.PlayerViewTools.isplay=false;
		iv_play.setBackgroundResource(R.drawable.player_play_selector);
		if(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length>0){
			iv_play.setEnabled(true);
			iv_next.setEnabled(true);
		}else{
			iv_next.setEnabled(false);
			if(entity.getStatus().equals(AnswerHelperEntity.STATUS_PAUSE)){
				iv_play.setEnabled(true);
				
			}else{
				iv_play.setEnabled(false);
				
			}
			
		}
	}
	
	/**
	 * 设置隐藏
	 */
	public void setGone(){
		rl_player.setVisibility(View.GONE);
	}
	/**
	 * 设置隐藏
	 */
	public void setVisiable(){
		rl_player.setVisibility(View.VISIBLE);
	}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		asyncBitmapLoader=new AsyncBitmapLoader(activity); 
		rl_model_player_expand=(RelativeLayout) activity.findViewById(R.id.rl_model_player_expand);
		iv_voice_search=(RoundAngleImageView) activity.findViewById(R.id.iv_model_player_voice_search);
		iv_stop=(ImageView) activity.findViewById(R.id.iv_model_player_stop);
		iv_play=(ImageView) activity.findViewById(R.id.iv_model_player_play);
		iv_next=(ImageView) activity.findViewById(R.id.iv_model_player_next);
		tv_music_name=(TextView) activity.findViewById(R.id.tv_model_player_music_name);
		tv_singer=(TextView) activity.findViewById(R.id.tv_model_player_singer);
		rl_model_player_song=(RelativeLayout) activity.findViewById(R.id.rl_model_player_song);
		
		pb_wait=(ProgressBar) activity.findViewById(R.id.pb_model_player_wait_play);
		pb_play=(ProgressBar) activity.findViewById(R.id.pb_model_player_play);
		rl_player=(RelativeLayout) activity.findViewById(R.id.rl_model_player);
		if(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length>0){
			iv_play.setEnabled(true);
			iv_next.setEnabled(true);
		}else{
			iv_next.setEnabled(false);
			if(Tools.answerHelperEntity.getStatus().equals(AnswerHelperEntity.STATUS_PAUSE)){
				iv_play.setEnabled(true);
			}else{
				iv_play.setEnabled(false);
			}
		}
		setPlaySateByIsPlayFlag(Tools.answerHelperEntity);
		//PlayingContentView
		
		 
//		mRecognitionListener = new DialogRecognitionListener() {
//
//			@Override
//			public void onResults(Bundle results) {
//				ArrayList<String> rs = results != null ? results
//						.getStringArrayList(RESULTS_RECOGNITION) : null;
//				if (rs != null && rs.size() > 0) {
//					JSONObject mainJson = new JSONObject();
//					JSONObject temp_json = null;
//					try {
//						temp_json = new JSONObject(rs.get(0));
//
//						mainJson.put("id", 1);
//						mainJson.put("method", "semanticAnalysis");
//						JSONObject parmasJson = new JSONObject();
//						JSONObject baiduJson = new JSONObject();
//
//						baiduJson.put("content", temp_json);
//						parmasJson.put("baidu", baiduJson);
//						mainJson.put("params", parmasJson);
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					baiduResult = mainJson.toString();
//					Log.d("BAIDUVOICE",baiduResult);
//					MyThread th=new MyThread(baiduResult);
//					th.start();
//
//				}
//
//			}
//		};
//		
	}
	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		rl_model_player_expand.setOnClickListener(this);
		iv_voice_search.setOnClickListener(this);
		iv_stop.setOnClickListener(this);
		iv_play.setOnClickListener(this);
		iv_next.setOnClickListener(this);
		rl_model_player_song.setOnClickListener(this);
	}
//	class AudioRecordThread implements Runnable {  
//		AudioRecord audioRecord;
//		public AudioRecordThread(AudioRecord audioRecord){
//			this.audioRecord=audioRecord;
//		}
//        @Override  
//        public void run() {  
//            
//            
//        }  
//    }  
//	/** 
//     * 这里将数据写入文件，但是并不能播放，因为AudioRecord获得的音频是原始的裸音频， 
//     * 如果需要播放就必须加入一些格式或者编码的头信息。但是这样的好处就是你可以对音频的 裸数据进行处理，比如你要做一个爱说话的TOM 
//     * 猫在这里就进行音频的处理，然后重新封装 所以说这样得到的音频比较容易做一些音频的处理。 
//     */  
//    private void writeDateTOFile() {  
//        // new一个byte数组用来存一些字节数据，大小为缓冲区大小  
//        byte[] audiodata = new byte[bufferSizeInBytes];  
//        FileOutputStream fos = null;  
//        int readsize = 0;  
//        try {  
//            File file = new File(AudioName);  
//            if (file.exists()) {  
//                file.delete();  
//            }  
//            fos = new FileOutputStream(file);// 建立一个可存取字节的文件  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        while (isRecord == true) {  
//            readsize = audioRecord.read(audiodata, 0, bufferSizeInBytes);  
//            if (AudioRecord.ERROR_INVALID_OPERATION != readsize) {  
//                try {  
//                    fos.write(audiodata);  
//                } catch (IOException e) {  
//                    e.printStackTrace();  
//                }  
//            }  
//        }  
//        try {  
//            fos.close();// 关闭写入流  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//    }  	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.isEnabled()){
			PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//防暴力点击
			switch(v.getId()){
			case R.id.iv_model_player_voice_search:
				//弹出声音识别界面
			    // 获得缓冲区字节大小  
				SharedConfig config=new SharedConfig(MyApplication.getInstance().getCur_Activity());
				
				break;
			case R.id.iv_model_player_play:
				//播放或暂停
			
				if((PlayerTools.PlayerViewTools.PLAYLIST_URLS!=null&&PlayerTools.PlayerViewTools.PLAYLIST_URLS.length>0)||!this.tv_music_name.getText().toString().equals("暂无歌曲"))
				{
				ArrayList<String>content=new ArrayList<String>();
				if(PlayerTools.PlayerViewTools.isplay){
					
					content.add(PlayOptions.setStatePause());
					TCPTools.sendTcp(content, Tools.Current_SocketIP,true);
				}else{
					
					if(Tools.answerHelperEntity.getStatus().equals(AnswerHelperEntity.STATUS_STOP)){
						//停止状态
						//发送对应的URL信息
						if(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES!=null&&PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length>0){
							if(PlayerTools.PlayerViewTools.current_index<0){
								PlayerTools.PlayerViewTools.current_index=0;
							}
							ArrayList<String>content1=new ArrayList<String>();
							ArrayList<PlayItemEntity>playItems=new ArrayList<PlayItemEntity>();
							PlayItemEntity playItemEntity=new PlayItemEntity(PlayItemEntity.TYPE_WEB, PlayerTools.PlayerViewTools.PLAYLIST_URLS[PlayerTools.PlayerViewTools.current_index]);
							playItems.add(playItemEntity);
							String urlsend=PlayOptions.setPlay(new String[]{PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index]},playItems, PlayEntity.METHOD,true);
									
							content1.add(urlsend);
							TCPTools.sendTcp(content1, Tools.Current_SocketIP,true);
							tv_music_name.setText(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index]);
							 if(Tools.CurrentActivityName.equals(LocalMusicActivity.ActivityName)){
								//如果在播放界面点击切换到下一首则对应的Adapter的焦点也要改变
								((LocalMusicActivity)MyApplication.getInstance().getCur_Activity()).msDialog.adapter.SetFocus(PlayerTools.PlayerViewTools.current_index);//设置焦点
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
						
					}else{
						//暂停状态
						content.add(PlayOptions.setStateResume());
						TCPTools.sendTcp(content, Tools.Current_SocketIP,true);
					}
					
				}
				
				}
				else
				{
					final View v_temp=v;
					new Handler(MyApplication.getInstance().getMainLooper()).postDelayed(new Runnable(){
						public void run() {
							v_temp.setEnabled(false);
					
						}
						}, 1005);
					ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "当前没有可播放的歌曲");
				}
				break;
			case R.id.iv_model_player_stop:
				ArrayList<String>content=new ArrayList<String>();
				content.add(PlayOptions.setStateStop());
				TCPTools.sendTcp(content, Tools.Current_SocketIP,true);
				break;
			case R.id.iv_model_player_next:

				if(PlayerTools.PlayerViewTools.PLAYLIST_URLS!=null&&PlayerTools.PlayerViewTools.PLAYLIST_URLS.length>0){

					//下一曲
				
					if(PlayerTools.PlayerViewTools.current_index<PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length-1){
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
					Tools.answerHelperEntity=new AnswerHelperEntity(1111,  PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index], "暂无内容", "暂无内容", new Date().toString(),AnswerHelperEntity.STATUS_STOP);
					setPlayerContent(Tools.answerHelperEntity);
					//tv_music_name.setText(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index]);
//					PlayerTools.PlayerViewTools.current_index++;
//					PlayerTools.PlayerViewTools.isplay=false;
//					setPlaySateByIsPlayFlag();
//					setSongState(this.cur_song_index);
					if(Tools.CurrentActivityName.equals(LocalMusicActivity.ActivityName)&&PlayerTools.focusNeedChange&&PlayerTools.FLAG_MUSIC==LocalMusicActivity.FLAG_MUSIC_LOCAL){
						//如果在播放界面点击切换到下一首则对应的Adapter的焦点也要改变
						((LocalMusicActivity)MyApplication.getInstance().getCur_Activity()).msDialog.adapter.SetFocus(PlayerTools.PlayerViewTools.current_index);//设置焦点
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
			case R.id.rl_model_player_song:
				//打开当前播放歌曲的界面

				Bundle bundle=new Bundle();
				bundle.putString("songName", this.tv_music_name.getText().toString());
				ActivitySetting.startUnfinishedActivity(activity, PlayingActivity.class, bundle,false);
				break;
			case R.id.rl_model_player_expand:
				Bundle bundle1=new Bundle();
				bundle1.putString("songName", this.tv_music_name.getText().toString());
				ActivitySetting.startUnfinishedActivity(activity, PlayingActivity.class, bundle1,false);
				break;
			}
		}
		
	}
		

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}

	
	

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub
		
	}

	

}
