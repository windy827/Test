package com.tts168.autoset.activity.quickset.terminal;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import com.asyloadImage.AsyncBitmapLoader;
import com.autoset.jni.deviceInfo.DeviceInfoEntity;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.http.HttpEntity;
import com.autoset.jni.http.HttpOptions;
import com.autoset.jni.http.productinfo.ProductInfoEntity;
import com.autoset.jni.http.productinfo.ProductInfoOptions;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.activity.alart.AlartActivity.RefreshThread;
import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.activity.quickset.AddDeviceActivity;
import com.tts168.autoset.activity.quickset.CheckLightActivity;
import com.tts168.autoset.activity.quickset.WifiSetActivity;
import com.tts168.autoset.activity.webmusic.AnimateFirstDisplayListener;
import com.tts168.autoset.adapter.quickset.DeviceSelectAdapter;
import com.tts168.autoset.adapter.terminal.TerminalListAdapter;
import com.tts168.autoset.database.terminal.DB_Terminal_Option;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyActivityManager;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.SearchThread;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;
import com.tts168.autoset.tools.highcset.individuation.IndividuationTools;
import com.tts168.autoset.tools.highcset.terminal.TerminalNotifyDialog;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.quickset.DeviceSelectTools;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.title.TitleView;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 终端管理界面
 * 
 * @author 袁剑
 * 
 */
public class TerminalActivity extends MyBaseActivity implements OnClickListener {
	public static final String TITLE = "我的设备";
	public static final String NODEVICES = "未获取到设备信息，请检查设备是否掉线或者点击添加新的设备吧~";
	//public static final String FAILDGETDEVICES = "获取设备信息失败，刷新下试试~";
	/**
	 * 是否一直线程搜索设备
	 */
	public static AtomicBoolean isSearch = new AtomicBoolean(false);
	/**
	 * 是否已经得到数据的标志位
	 */
	public static boolean hasData=false;
	public static final int MSG_REFRESH = 0x01;
	public static final int MSG_NOCONTENT = 0x02;
	public static final int MSG_GETIMAGE = 0x03;
	TitleView titleView;// 标题栏
	private Button bt_adddevice;// 添加分组，添加设备

	public  TextView tv_total;// 当前可连接的设备数
	public static final String ToTALTEXT = "我的设备(";
	public  ListView lv;// 显示终端列表分组及组员
	public  SearchThread sth;// 查找设备的线程
	public static final String ActivityName = "TerminalActivity";

	public  WaitView waitView;// 等待数据
	public static int count=1;
	 public TerminalListAdapter ta;
	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}

	public  Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == MSG_REFRESH) {
				hasData=true;
				tv_total.setVisibility(View.GONE);
				waitView.setPBGone();
				refresh();
			} else if (msg.what == MSG_NOCONTENT) {
				hasData=false;
				waitView.setPBGone();
				tv_total.setVisibility(View.VISIBLE);
				tv_total.setText(NODEVICES);
				
				//refresh();
				
			}
		}
	};
	protected void onDestroy() {
		super.onDestroy();
		TerminalActivity.isSearch.set(false);
		sth = null;
	}
	@Override
	public void rightToLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub

	}

	/**
	 * 对外提供的刷新接口
	 */
	public  void refresh() {
		if(waitView==null){
			return;
		}
		if(TerminalTools.adapter_infoByIP.size()>0){
			waitView.setPBGone();
			tv_total.setVisibility(View.GONE);
			tv_total.setText(ToTALTEXT +TerminalTools.adapter_infoByIP.size()+ ")");
		}
		else{
			tv_total.setVisibility(View.VISIBLE);
			tv_total.setText(NODEVICES);
		}
		
		ArrayList<TerminalAdapterEntity>al_list=new ArrayList<TerminalAdapterEntity>();
		for(int i=TerminalTools.adapter_infoByIP.size()-1;i>=0;i--){
			al_list.add(TerminalTools.adapter_infoByIP.get(i));
		}
		Log.d("CHECKRIGHT","TerminalTools.adapter_infoByIP"+TerminalTools.adapter_infoByIP.size()+"al_list"+al_list.size());
		ta = new TerminalListAdapter(MyApplication.getInstance().getCur_Activity(),
				al_list, lv);
		lv.setAdapter(ta);

	}

	/**
	 * 用来开启线程更新设备列表【点击刷新或OnResum时调用】
	 */
	public  void searchDevice2Refresh() {

		if(waitView==null){
			waitView = new WaitView((Activity)MyApplication.getInstance().getCur_Activity());
		}
		waitView.setPBVisable();
		// 发送设备信息请求

		Network net = Network.getInstance(null);
		if (net != null) {
			Log.d("NODESIZE",net.getNodeSize()+"........");
			net.refreshDevice();
		}
		count++;
		/**
		 * 每次刷新的时候重置是否已经得到数据的标志位为false
		 */
		hasData=false;
		if(TerminalTools.adapter_infoByIP==null){
			TerminalTools.adapter_infoByIP=new ArrayList<TerminalAdapterEntity>();
		}else{
			TerminalTools.adapter_infoByIP.clear();
		}
		
		// Tools.al_Device_Info=new ArrayList<HashMap<String,Object>>();
		if (TerminalActivity.isSearch.compareAndSet( false ,  true )) {
			
			sth = new SearchThread(handler);
			sth.start();
		}

		sth.ressetData();// 在进行刷新的时候需要把这个值的大小置为0
		refresh();

	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		Log.d("getDataRefresh",
				Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)
						+ "");
		if (!Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)) {
			searchDevice2Refresh();
			if(	Tools.Current_DeviceName.length() != 0
					&&MyActivityManager.getScreenManager().getActivityTotal()>1){
				titleView.setBackImgViewVisable();
			}else{
				titleView.setBackImgViewInVisable();
			}
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// 关闭设备搜索线程
		if (Tools.Current_DeviceName.length() == 0
				|| MyActivityManager.getScreenManager().getActivityTotal() <= 1) {
			// 即当前界面为选择终端界面，还没有进入主界面
			/**
			 * 相当于按Home键
			 */
			Intent i= new Intent(Intent.ACTION_MAIN); 
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			i.addCategory(Intent.CATEGORY_HOME); 
			startActivity(i);  

		} else {
			super.onBackPressed();
		}
		
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		// thread.start();
		//sth = new SearchThread(handler);
		titleView = new TitleView(this);
		titleView.setSaveText(Tools.TITLE_REFRESH);
		titleView.setTitle(TITLE);
		if(	Tools.Current_DeviceName.length() != 0
				&&MyActivityManager.getScreenManager().getActivityTotal()>=1){
			titleView.setBackImgViewVisable();
		}else{
			titleView.setBackImgViewInVisable();
		}
		
		waitView = new WaitView((Activity)MyApplication.getInstance().getCur_Activity());

		tv_total = (TextView) findViewById(R.id.tv_activity_terminal_total);
		bt_adddevice = (Button) findViewById(R.id.bt_terminal_adddevice);
		lv = (ListView) findViewById(R.id.lv_activity_terminalinfo);
//------------------------数据优化-------------------------------------------
		if ( MyApplication.getInstance().getPreActivityName().equals(SearchDevicesActivity.ActivityName)) {
			//如果是从搜索设备界面过来的就不用走清空数据重新发送的流程了
			if (TerminalActivity.isSearch.compareAndSet( false ,  true )) {
				
				sth = new SearchThread(handler);
				sth.start();
			}

			sth.ressetData();// 在进行刷新的时候需要把这个值的大小置为0
			tv_total.setText(ToTALTEXT +TerminalTools.adapter_infoByIP.size()+ ")");
			refresh();
		}else{
			tv_total.setText(ToTALTEXT + "0)");
			searchDevice2Refresh();
		}
		//-----------------------------------------------------------------------------		
		

	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_adddevice.setOnClickListener(this);
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
		return R.layout.activity_terminal;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.LONG_TIME);//防暴力点击
		switch (v.getId()) {

		case R.id.bt_terminal_adddevice:
			// 添加设备
			ActivitySetting.startUnfinishedActivity(this,
					CheckLightActivity.class, null,false);
			break;
		}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		// 刷新操作
		PreventViolence.preventClick(titleView.tv_save, PreventViolence.LONG_TIME);//防暴力点击
			searchDevice2Refresh();
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		//lv.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling));
	}
	
	

}
