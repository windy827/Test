package com.tts168.autoset.activity.player;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.play.PlayOptions;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.player.PlayingContentView;
import com.tts168.autoset.view.player.PlayingTitleView;


/**
 * 展开的正在播放的音乐界面
 * @author 袁剑
 *
 */
public class PlayingActivity extends MyBaseActivity implements OnClickListener{

	PlayingTitleView ptv;
	public PlayingContentView pcv;
	public static final String TITLE="播放控制";
	public static final String ActivityName="PlayingActivity";
	
	
	//RoundAngleImageView iv_voice_search;//声音识别搜索歌曲
	TextView tv_songName;
	//ImageView iv_stop;//停止键
//	static ImageView iv_play;//播放键
//	ImageView iv_next;//下一首
	



	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}
	@Override
	public void rightToLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if(!Tools.CurrentActivityName.equals(getActivityName())){
			setPlayingContent(Tools.answerHelperEntity);
		}
	}
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
		pcv.setPlaySateByIsPlayFlag(entity);
		 tv_songName.setText(entity.getQuestion());
	}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		ptv=new PlayingTitleView((Activity)MyApplication.getInstance().getCur_Activity());
		pcv=new PlayingContentView((MyBaseActivity)MyApplication.getInstance().getCur_Activity());
		ptv.setTitle(TITLE);
	
		
		//iv_voice_search=(RoundAngleImageView) findViewById(R.id.iv_model_playing_pic);
		tv_songName=(TextView) findViewById(R.id.tv_model_playing_song);
		 //iv_stop=(ImageView) findViewById(R.id.iv_model_playing_stop12);//停止键
		
		
		 
		 String songName=this.getIntent().getStringExtra("songName");
		 setPlayingContent(Tools.answerHelperEntity);
		 tv_songName.setText(songName);
		//得到当前歌曲是否已经标记为喜欢,然后赋值
		pcv.setIsLikeDrawable(false);
		
		//得到当前歌曲是否为随机播放,然后赋值
		pcv.setPlayOrder(0);
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
//		iv_voice_search.setOnClickListener(this);
//		iv_stop.setOnClickListener(this);
		
	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getContentViewID() {
		// TODO Auto-generated method stub
		return R.layout.activity_playing;
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//此处为打开播放列表。点击标题栏右侧图标触发
		ActivitySetting.startUnfinishedActivity((Activity)MyApplication.getInstance().getCur_Activity(), SoloAlbumActivity.class, null,false);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){	
	case R.id.iv_model_player_stop:
		ArrayList<String>content2=new ArrayList<String>();
		content2.add(PlayOptions.setStateStop());
		TCPTools.sendTcp(content2, Tools.Current_SocketIP,true);
		break;
		}
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
