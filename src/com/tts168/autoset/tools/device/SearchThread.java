package com.tts168.autoset.tools.device;

import java.net.Socket;

import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * �鿴�豸�Ƿ����ߣ���ˢ��
 * @author Ԭ��
 *
 */
public class SearchThread extends Thread{
		
	private Handler handler;
	public int leng=0;//�ڽ���ˢ�µ�ʱ����Ҫ�����ֵ�Ĵ�С��Ϊ0
	int search_time=0;//������ʱ����¼
	public static final int times=20;//����20��Ҳ����10����
	public SearchThread(Handler handler){
		ressetData();
		this.handler=handler;
	}
	//��������
	public void ressetData(){
		leng=0;
		search_time=0;
		TerminalActivity.hasData=false;
	}
		@Override
		public void run() {
			while(TerminalActivity.isSearch.get()){
				search_time++;
				if(search_time>times&&!TerminalActivity.hasData){
					TerminalActivity.isSearch.set(false);
					if(TerminalTools.adapter_infoByIP.size()==0){
						Message msg=new Message();
						msg.what=TerminalActivity.MSG_NOCONTENT;
						handler.sendMessage(msg);
					}
					
				}else{
					if(leng!=TerminalTools.adapter_infoByIP.size()){
						Message msg=new Message();
						msg.what=TerminalActivity.MSG_REFRESH;
						handler.sendMessage(msg);
						leng=TerminalTools.adapter_infoByIP.size();
					}
					
				}
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}			
		}
	}
