package com.tts168.autoset.tools.playmusic;

import java.io.IOException;

import com.larkiv.larksmart7618.R;

import android.content.Context;
import android.media.MediaPlayer;
/**
 * ���Բ���raw�º��ֻ��ƶ�·���µ�mp3
 * @author Ԭ��
 * @since 2015-12-02
 *
 */
public class MyMediaPlayer {
	MediaPlayer mp ;
	/**
	 * ����R.raw�µ�mp3�ļ�
	 * @param raw
	 */
	public MyMediaPlayer(Context context,int raw){
		mp = MediaPlayer.create(context, raw);
	}
	/**
	 * �����ֻ�ָ��·���µ� �ļ�
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
		 /* �Ƿ����ڲ��� */   
        if (mp.isPlaying())   
        {   
            //����MediaPlayer����ʼ״̬   
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
