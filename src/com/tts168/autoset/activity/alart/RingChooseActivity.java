package com.tts168.autoset.activity.alart;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.autoset.jni.play.PlayOptions;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.adapter.alart.ChooseRingAdapter;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartCommenSetTools;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.title.TitleView;
import com.tts168.autoset.watcher.ScroListener;

public class RingChooseActivity  extends MyBaseActivity implements OnClickListener{
	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE="����ѡ��";
	
	public static final String ActivityName="RingChooseActivity";
	ListView lv_choose ;
	ChooseRingAdapter sa;
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
			
			//MyApplication.asyncBitmapLoader.initExecutorService();[�õ��첽���صĵط���Ҫʹ��]
			
			refresh();
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		ArrayList<String>content=new ArrayList<String>();
		content.add(PlayOptions.setStateStop());
		TCPTools.sendTcp(content, Tools.Current_SocketIP,true);
		super.onDestroy();
	}
	/**
	 * �ṩ���ⲿ��ˢ�½ӿ�
	 */
		public static void refresh(){
		
		}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub	
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		
		lv_choose = (ListView) this.findViewById(R.id.lv_ringchoose);
		 final ArrayList<HashMap<String, Object>> al = AlartCommenSetTools.getSongInfo();
		sa = new ChooseRingAdapter(MyApplication.getInstance().getCur_Activity(), al,lv_choose);
		lv_choose.setAdapter(sa);
		lv_choose.setOnScrollListener(new ScroListener(sa));
		lv_choose.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				PreventViolence.preventClick(arg0, PreventViolence.LONG_TIME);//���������
				// TODO Auto-generated method stub
				String text = al.get(index).get(AlartCommenSetTools.KEY_SONGNAME).toString();
				String path=al.get(index).get(AlartCommenSetTools.KEY_SONGPATH).toString();
				AlartTools.getUpSetEntity.setRing_path(path);
				AwakeAlartCommentSetActivity.ringName=text;
				MyApplication.getInstance().getCur_Activity().onBackPressed();

			}

		});
		
		
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		
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
		return R.layout.activity_ringchoose;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//���������
				switch(v.getId()){
			
				}
		
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//�������
		ToastTools.short_Toast((Activity)MyApplication.getInstance().getCur_Activity(), "����ɹ�!!");
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
