package com.tts168.autoset.tools.commen;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.answerhelper.AnswerRecordActivity;
import com.tts168.autoset.activity.localmusic.LocalMusicActivity;
import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.activity.player.PlayingActivity;
import com.tts168.autoset.tools.Tools;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

/**
 * ���������������
 * @author Ԭ��
 *
 */
public class PreventViolence {
	public static final int SHORT_TIME=500;
	public static final int LONG_TIME=1000;
	public static final int LONGER_TIME=2000;
	/**
	 * 
	 * @param v  �������Ŀؼ�
	 * @param timeMs ���ٺ����Ժ�����ظ����
	 */
	
	public static void preventClick(final View v,int timeMs){
		Log.d("view is able= ",v.isEnabled()+"");
		v.setEnabled(false);
		v.setClickable(false);
		Log.d("view is able= ",v.isEnabled()+"");

		// ���ε���¼���0.5���ָ�
		new Handler(MyApplication.getInstance().getMainLooper()).postDelayed(new Runnable(){
		public void run() {
		//��Բ�����״̬�������⴦����������״̬���ı�
		if((v.getId()==R.id.iv_model_player_play||v.getId()==R.id.iv_model_playing_play12)&&Tools.answerHelperEntity.getStatus().equals(AnswerHelperEntity.STATUS_STOP)){
			//�����ǰ�������ʴ��ؽ��棬֪ͨ���½���
			v.setClickable(true);
			if(Tools.CurrentActivityName.equals(AnswerRecordActivity.ActivityName)){
				//֪ͨ����
				Message msg_answer = new Message();			
				msg_answer.what = AnswerRecordActivity.HANDLER_REFRESH_ADAPTER;;	
				((AnswerRecordActivity)MyApplication.getInstance().getCur_Activity()).handler.sendMessage(msg_answer);
			}else if(Tools.CurrentActivityName.equals(MainMenuActivity.ActivityName)){
				//֪ͨ����
				((MainMenuActivity)MyApplication.getInstance().getCur_Activity()).pv.setPlayerContent(Tools.answerHelperEntity);
			}else if(Tools.CurrentActivityName.equals(LocalMusicActivity.ActivityName)){
				//֪ͨ����
				((LocalMusicActivity)MyApplication.getInstance().getCur_Activity()).pv.setPlayerContent(Tools.answerHelperEntity);
			}
			else if(Tools.CurrentActivityName.equals(PlayingActivity.ActivityName)){
				//֪ͨ����
				((PlayingActivity)MyApplication.getInstance().getCur_Activity()).setPlayingContent(Tools.answerHelperEntity);
			}
		}else{
			v.setEnabled(true);
			v.setClickable(true);
		}
		Log.d("view is able= ","LEt Enable"+v.isEnabled()+"");
		}
		}, timeMs);
	}
	
}
