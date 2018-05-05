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
 * 查看设备是否在线，并刷新
 * @author 袁剑
 *
 */
public class SearchThread extends Thread{
		
	private Handler handler;
	public int leng=0;//在进行刷新的时候需要把这个值的大小置为0
	int search_time=0;//搜索的时长记录
	public static final int times=20;//搜索20次也就是10秒钟
	public SearchThread(Handler handler){
		ressetData();
		this.handler=handler;
	}
	//重置数据
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
