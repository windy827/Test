package com.tts168.autoset.adapter.terminal;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.asyloadImage.AsyncBitmapLoader;
import com.asyloadImage.AsyncBitmapLoader.ImageCallBack;
import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.deviceInfo.DeviceInfoEntity;
import com.autoset.jni.general.GeneralEntity;

import com.larkiv.larksmart7618.R;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.activity.webmusic.AnimateFirstDisplayListener;

import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.tools.Tools;

import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;
import com.tts168.autoset.tools.highcset.individuation.IndividuationTools;
import com.tts168.autoset.tools.highcset.terminal.TerminalNotifyDialog;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;

import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 终端列表
 * 
 * @author 袁剑
 * 
 */
public class TerminalListAdapter extends BaseAdapter{
	public static String ClickDeviceName="";
	public static  String clickSocketIP="";
	private ArrayList<TerminalAdapterEntity> info;// 取得到的这次阅览的文字信息，包括设备名称
	private Context context;
	SparseArray<View> map = new SparseArray< View>();
	LayoutInflater lf;
	ListView listView;
	private boolean mBusy = false;
	/**
	 * 是否点击了设备详情
	 */
	public static boolean isDeviceInfo_Click=false;
	/**
	 * Wifi信号强度图片
	 */
	//Bitmap[]bms_wifissid=null;
	int []res_wifissid=new int[]{R.drawable.wifirssinfo0,R.drawable.wifirssinfo1,R.drawable.wifirssinfo2,R.drawable.wifirssinfo3,R.drawable.wifirssinfo4,R.drawable.wifirssinfo5};
	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
		
	}
	ArrayList<Bitmap>al_Bitmaps;//记录用于回收的图片
	public ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	
	public TerminalListAdapter(Context context,
			ArrayList<TerminalAdapterEntity> info,ListView listView) {
//		bms_wifissid=new Bitmap[6];
//		Resources res=context.getResources();
//
//		for(int i=0;i<res_wifissid.length;i++){
//			bms_wifissid[i]=BitmapFactory.decodeResource(res, res_wifissid[i]);
//		}
		
		//暂时添加，为了测试方便，到时候需要去掉
		this.info=new ArrayList<TerminalAdapterEntity>();
		for(int i=info.size()-1;i>=0;i--){
			this.info.add(info.get(i));
		}
		
		//暂时注释
		//this.info = info;
		this.context = context;
		this.listView=listView;
		lf = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		al_Bitmaps=new ArrayList<Bitmap>();

	}

	public class MyView {
		private ImageView iv_device;//设备图片
		private TextView tv_deviceName;// 设备名称
	
		private TextView tv_deviceBattery;// 设备电量
		private ImageView iv_wifissid;// 设备Wifi强度

		private RelativeLayout rl_connected;//连接设备
		private ImageView bt_editname;// 更改设备名称
		private ImageView bt_device_info;// 设备详情

		private ImageView iv_search_sound;// 查找设备
		private ImageView iv_expand;// 展开
		
		private LinearLayout ll_expand;//展开的内容
	}

	/**
	 * 在退出Activity的时候回收图片
	 */
	public void recycleBitmaps(){

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (this.info==null?0:this.info.size());
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return info.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyView mv;
		if (map.get(position) == null) {
			mv = new MyView();
			convertView = lf.inflate(R.layout.listitem_terminal, null);
			mv.iv_device=(ImageView) convertView.findViewById(R.id.iv_listitem_terminal_device);
			
			mv.tv_deviceBattery=(TextView) convertView
					.findViewById(R.id.tv_listitem_terminal_baterryinfo);
			mv.tv_deviceName = (TextView) convertView
					.findViewById(R.id.tv_listitem_terminal_name);
			mv.tv_deviceBattery = (TextView) convertView
					.findViewById(R.id.tv_listitem_terminal_baterryinfo);
			mv.rl_connected = (RelativeLayout) convertView
					.findViewById(R.id.rl_listitem_terminal_into);
			mv.bt_device_info = (ImageView) convertView
					.findViewById(R.id.bt_listitem_terminal_info);
			mv.bt_editname= (ImageView) convertView
					.findViewById(R.id.bt_listitem_terminal_editname);
			mv.iv_search_sound=(ImageView) convertView
					.findViewById(R.id.iv_listitem_terminal_sound);
			mv.iv_expand=(ImageView) convertView
					.findViewById(R.id.iv_listitem_terminal_expand);
					
			mv.ll_expand=(LinearLayout) convertView
					.findViewById(R.id.ll_listitem_terminal_expand);
			//mv.tv_deviceName.getPaint().setFakeBoldText(true);//加粗
			map.put(position, convertView);
			
			convertView.setTag(mv);
		} else {
			convertView = map.get(position);
			mv = (MyView) convertView.getTag();

		}
	
		TerminalAdapterEntity ta_entity=info.get(position);
		GeneralEntity ge=ta_entity.getGeneralEntity();
       
		mv.tv_deviceName.setText(ge.getNickname());
		mv.tv_deviceBattery.setText(ge.getDevice_power()+"%");
		
				
		mv.rl_connected.setOnClickListener(new TerminalItemBTClickListenre(ta_entity,mv.ll_expand,position));
		mv.bt_device_info.setOnClickListener(new TerminalItemBTClickListenre(ta_entity,mv.ll_expand,position));
		mv.iv_search_sound.setOnClickListener(new TerminalItemBTClickListenre(ta_entity,mv.ll_expand,position));
		mv.iv_expand.setOnClickListener(new TerminalItemBTClickListenre(ta_entity,mv.ll_expand,position));
		mv.bt_editname.setOnClickListener(new TerminalItemBTClickListenre(ta_entity,mv.ll_expand,position));
		return convertView;
	}
	
	public void updateView(int itemIndex){    
		  int visiblePosition = this.listView.getFirstVisiblePosition();    
		  View v = this.listView.getChildAt(itemIndex - visiblePosition);//Do something fancy with your listitem view    
		  ImageView iv_expand=(ImageView) v
					.findViewById(R.id.iv_listitem_terminal_expand);
					
		  LinearLayout ll_expand=(LinearLayout) v
					.findViewById(R.id.ll_listitem_terminal_expand);
		  if(ll_expand.getVisibility()==View.VISIBLE){
			  iv_expand.setImageResource(R.drawable.down_gray);
				ll_expand.setVisibility(View.GONE);
			}else{
				iv_expand.setImageResource(R.drawable.up_gray);
				ll_expand.setVisibility(View.VISIBLE);
			}
		}  
	/**
	 * 控制显示的LinerLayout里面的按钮的点击事件
	 * @author 袁剑
	 *
	 */
	
	public class TerminalItemBTClickListenre implements OnClickListener {
		
		
		TerminalAdapterEntity ter_entity;
		
		LinearLayout ll_expand;
		int index;
		
		boolean isMoveFlag;
		public TerminalItemBTClickListenre(TerminalAdapterEntity ter_entity,LinearLayout ll_expand,int index) {
			
			this.ll_expand=ll_expand;
			this.ter_entity=ter_entity;
			this.index=index;
			
			isMoveFlag=true;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			PreventViolence.preventClick(v, PreventViolence.SHORT_TIME);//防暴力点击
			switch(v.getId()){
			
			case R.id.bt_listitem_terminal_info:
				//设备详情
				/**
				 * 记录设备的名字
				 */
				
					isDeviceInfo_Click=true;
					((TerminalActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBVisable();
					TerminalListAdapter.ClickDeviceName=ter_entity.getGeneralEntity().getNickname();
					TerminalListAdapter.clickSocketIP=ter_entity.getSocketIp();	
					ArrayList<String>domainNames=new ArrayList<String>();
					domainNames.add(DeviceInfoEntity.DOMAIN_DEVICE_INFO);
					TCPTools.sendTcpByDomain(domainNames, ter_entity.getSocketIp());
				
				break;
			case R.id.rl_listitem_terminal_into:
				//切换设备
				
				Tools.Current_DeviceName=ter_entity.getGeneralEntity().getNickname();
				
				String ip=ter_entity.getSocketIp();
				if(!Tools.Current_SocketIP.equals(ip)){
					Tools.Current_SocketIP=ip;
					Tools.answerHelperEntity=new AnswerHelperEntity(1111, "暂无歌曲", "暂无歌曲", "暂无歌曲", new Date().toString(),AnswerHelperEntity.STATUS_STOP);
				}
				if(Tools.Current_SocketIP.length()>0){
					/**
					 * 设置网页网址内容
					 */
					
						Tools.generalEntity=ter_entity.getGeneralEntity();
						ActivitySetting.startActivity(((MyBaseActivity)MyApplication.getInstance().getCur_Activity()), MainMenuActivity.class, null,true);
						IndividuationTools.playSound(Tools.Current_SocketIP,RemindVoiceTools.OthersRemind.DEVICE_CONNECTED );
						ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "连接到"+Tools.Current_DeviceName+"成功");
					
					
				}else{
					ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "连接"+Tools.Current_DeviceName+"失败");
				}
				
				break;
			case R.id.iv_listitem_terminal_sound:
				//播放声音
				
					IndividuationTools.playSound(ter_entity.getSocketIp(), RemindVoiceTools.AnounciatorRemindTools.FINDDEVICE);
				break;	
			case R.id.bt_listitem_terminal_editname:
				//编辑昵称
				TerminalNotifyDialog.editNickNameDialog(context, ter_entity.getGeneralEntity().getNickname(),ter_entity.getGeneralEntity(),ter_entity.getSocketIp());
				break;
			case R.id.iv_listitem_terminal_expand:
				//展开或收缩图标
				updateView(this.index);
				break;
			}
		
		}


	}




}
