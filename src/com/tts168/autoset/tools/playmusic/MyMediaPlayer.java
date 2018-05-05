package com.tts168.autoset.tools.playmusic;

import java.io.IOException;

import com.larkiv.larksmart7618.R;

import android.content.Context;
import android.media.MediaPlayer;
/**
 * 可以播放raw下和手机制定路径下的mp3
 * @author 袁剑
 * @since 2015-12-02
 *
 */
public class MyMediaPlayer {
	MediaPlayer mp ;
	/**
	 * 播放R.raw下的mp3文件
	 * @param raw
	 */
	public MyMediaPlayer(Context context,int raw){
		mp = MediaPlayer.create(context, raw);
	}
	/**
	 * 播放手机指定路径下的 文件
	 * @param path
	 */
	
	public MyMediaPlayer(Context context,String path){
		 mp = new MediaPlayer();
	       String song = path;
	        try {
	            mp.setDataSource(song);
	          
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	public void start(){
		 /* 是否正在播放 */   
        if (mp.isPlaying())   
        {   
            //重置MediaPlayer到初始状态   
            mp.reset();   
        }   
		  try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          mp.start();
	}
	
	public boolean isLooping(){
		return mp.isLooping();
	}
	public boolean isPlaying(){
		return mp.isPlaying();
	}
	public void pause(){
		mp.pause();
	}
	public void stop(){
		mp.stop();
	}
	public void release(){
		if(mp!=null){
			if(mp.isPlaying()){
				mp.pause();
			}
			mp.release();
			mp=null;
		}
		
		
	}
}
