package com.tts168.autoset.tools.test;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.tts168.autoset.database.GetOrUpdateTCPDisConnect;
import com.tts168.autoset.database.GetOrUpdateUDPDisConnect;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.network.Network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * UDP和TCP的测试
 * @author 袁剑
 *
 */
public class UdpAndTcpTest {

	public static final String[]all_deviceNames=new String[]{"8","11","白灯","金灯"};
	public Context context;
	public static UdpAndTcpTest instance=null;
	Handler handler=null;
	Timer timer_udp;
	Timer timer_tcp;
	public static final int MSG_WHAT_UDP=0x01;
	public static final int MSG_WHAT_TCP=0x02;	
	public static final String UDP_MSG="UDP接收到的设备有:";
	public int count=0;
	public UdpAndTcpTest(Context context){
		this.context=context;
		if(instance==null){
			instance=this;
			handler=new Handler(context.getMainLooper()){
				@Override
				public void dispatchMessage(Message msg) {
					// TODO Auto-generated method stub
					super.dispatchMessage(msg);
					switch(msg.what){
					case MSG_WHAT_UDP:
						
						GetOrUpdateUDPDisConnect.recordDisConnect(getresult());
						//NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), msg.obj+"***"+getresult());
						break;
					case MSG_WHAT_TCP:
						String deviceNames1="";
						if(TerminalTools.adapter_infoByIP!=null&&TerminalTools.adapter_infoByIP.size()>0){
							for(int i=0;i<TerminalTools.adapter_infoByIP.size();i++){
								String name=(String) TerminalTools.adapter_infoByIP.get(i).getGeneralEntity().getNickname();
								deviceNames1=deviceNames1+"\r\n"+name;
							}
							
						}
						GetOrUpdateTCPDisConnect.recordDisConnect(getresult());
						//NotifyDialog.SimpleAlertDialog(MyApplication.getInstance().getCur_Activity(), msg.obj+"***"+getresult());
						break;
					}
				}
			};
		}
		
	}
	
	public static UdpAndTcpTestEntity getresult(){
		UdpAndTcpTestEntity entity=new UdpAndTcpTestEntity(UdpAndTcpTestEntity.FLAG_NO, UdpAndTcpTestEntity.FLAG_NO, UdpAndTcpTestEntity.FLAG_NO, UdpAndTcpTestEntity.FLAG_NO);
		ArrayList<String>devices=new ArrayList<String>();
		if(TerminalTools.adapter_infoByIP!=null&&TerminalTools.adapter_infoByIP.size()>0){
			for(int j=0;j<all_deviceNames.length;j++){
				String dev=all_deviceNames[j];
				boolean has=false;
				for(int i=0;i<TerminalTools.adapter_infoByIP.size();i++){
					String name=(String) TerminalTools.adapter_infoByIP.get(i).getGeneralEntity().getNickname();
					if(name.equals(dev)){
						has=true;
						break;
					}
				}
				if(has){
					if(j==0){
						entity.setState_8(UdpAndTcpTestEntity.FLAG_HAS);
					}else if(j==1){
						entity.setState_11(UdpAndTcpTestEntity.FLAG_HAS);
					}else if(j==2){
						entity.setState_white(UdpAndTcpTestEntity.FLAG_HAS);
					}else if(j==3){
						entity.setState_gold(UdpAndTcpTestEntity.FLAG_HAS);
					}
					
				}else{
					if(j==0){
						entity.setState_8(UdpAndTcpTestEntity.FLAG_NO);
					}else if(j==1){
						entity.setState_11(UdpAndTcpTestEntity.FLAG_NO);
					}else if(j==2){
						entity.setState_white(UdpAndTcpTestEntity.FLAG_NO);
					}else if(j==3){
						entity.setState_gold(UdpAndTcpTestEntity.FLAG_NO);
					}
				}
			}
		} 
		return entity;
	}
	public static UdpAndTcpTest getInstance(Context context){
		if(instance==null){
			instance=new UdpAndTcpTest(context);
		}
		return instance;
	}
	public void startTimer(){
		timer_udp=new Timer(true);
		timer_tcp=new Timer(true);
		timer_udp.schedule(task_udp, 15*1000, 10*1000);
		timer_tcp.schedule(task_tcp, 15*1000, 10*1000);
		
	}
	TimerTask task_udp = new TimerTask() {  
		   public void run() {  
		   //UDP 
			   if(count<2){
				   try {
					   //Network.getInstance(context).closeAllSocket();
					// 关闭掉服务端
					   Thread.sleep(2000);
					   Network.destroy();
						Network.getInstance(context).clearNodesrecored();
						 Log.d("UDPTCPSIZE","UDP NODESIZE清空后------"+Network.getInstance(context).getNodeSize()+"个设备");
					 
					   Network net = Network.getInstance(MyApplication.getInstance().getCur_Activity());
					   if(TerminalTools.adapter_infoByIP!=null){
						   TerminalTools.adapter_infoByIP.clear();
					   }else{
						   TerminalTools.adapter_infoByIP=new ArrayList<TerminalAdapterEntity>();
					   }
					   //Log.d("UDPTCPSIZE","UDP-----TerminalTools.adapter_infoByIP大小------"+TerminalTools.adapter_infoByIP.size()+"");
					  // Log.d("UDPTCPSIZE","UDP NODESIZE------"+Network.getInstance(context).getNodeSize()+"个设备");
						Thread.sleep(10*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					   Message msg=new Message();
					   msg.what=MSG_WHAT_UDP;
					  msg.obj="UDP共扫描到："+Network.getInstance(context).getNodeSize()+"个设备";
					  Log.d("UDPTCPSIZE","UDP------"+TerminalTools.adapter_infoByIP.size()+"个设备");
					  // Log.d("UDPNODESIZE",Network.getInstance(context).getNodeSize()+"个设备");
					   handler.sendMessage(msg);
					   count++;
					  
			   }
			  
		   }     
		};  
		TimerTask task_tcp = new TimerTask() {  
			   public void run() {  
			   //TCP 
				   if(count>=2&&count<4){
					   try {
						   Thread.sleep(2*1000);
							Network net = Network.getInstance(MyApplication.getInstance().getCur_Activity());
							if (net != null) {
								Log.d("NODESIZE",net.getNodeSize()+"........");
								net.refreshDevice();
							}
							 if(TerminalTools.adapter_infoByIP!=null){
								   TerminalTools.adapter_infoByIP.clear();
							   }else{
								   TerminalTools.adapter_infoByIP=new ArrayList<TerminalAdapterEntity>();
							   }
							Log.d("UDPTCPSIZE","TCP-----TerminalTools.adapter_infoByIP大小------"+TerminalTools.adapter_infoByIP.size()+"");
							Thread.sleep(10*1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						   Message msg=new Message();
						   msg.what=MSG_WHAT_TCP;
						  Log.d("UDPTCPSIZE","TCP------"+TerminalTools.adapter_infoByIP.size()+"个设备");
						   msg.obj="TCP共刷新到："+Network.getInstance(MyApplication.getInstance().getCur_Activity()).getNodeSize()+"个设备";
						   handler.sendMessage(msg);
						   count++;
					   } 
				   else{if(count>=4){
					   count=0;
				   
					 
				   }
				   }
			   }
			        
			};  
}
