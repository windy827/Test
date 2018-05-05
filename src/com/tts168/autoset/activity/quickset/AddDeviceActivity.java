package com.tts168.autoset.activity.quickset;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.asyloadImage.AsyncBitmapLoader;
import com.autoset.jni.http.CategoryEntity;
import com.autoset.jni.http.HttpEntity;
import com.autoset.jni.http.HttpOptions;
import com.autoset.jni.http.productinfo.ProductInfoEntity;
import com.autoset.jni.http.productinfo.ProductInfoOptions;
import com.geniusgithub.lazyloaddemo.cache.OldImageLoader;
import com.larkiv.larksmart7618.R;

import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.webmusic.AnimateFirstDisplayListener;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.adapter.quickset.DeviceSelectAdapter;
import com.tts168.autoset.adapter.webmusic.CategoryListLoaderAdapter;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.quickset.DeviceSelectTools;
import com.tts168.autoset.tools.quickset.WifiSetRemindTools;
import com.tts168.autoset.tools.webmusic.WebMusicTools;
import com.tts168.autoset.view.title.TitleView;

public class AddDeviceActivity extends MyBaseActivity implements OnClickListener, OnItemClickListener{
	TitleView titleView;//标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	public static final String TITLE="添加设备";
	
	public static final String ActivityName="AddDeviceActivity";
	 GridView gv;
	 ArrayList<ProductInfoEntity>info;
	 DeviceSelectAdapter adapter;
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
	protected void onDestroy() {
		//((AnimateFirstDisplayListener)adapter.animateFirstListener).displayedImages.clear();
		//MyApplication.asyncBitmapLoader.recyleBitmaps();
		super.onDestroy();
	}
	
	
	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if(!Tools.CurrentActivityName.equals(getActivityName())){
			refresh();
		}
	}
	/**
	 * 提供给外部的刷新接口
	 */
		public  void refresh(){
		
			//MyApplication.asyncBitmapLoader.initExecutorService();
			//如果本次进入获取到信息，则读上一次获取到的信息
			SharedConfig config=new SharedConfig(MyApplication.getInstance().getCur_Activity());
			String content=config.GetConfig().getString(SharedConfig.KEY_Productinfo, "");
			info=ProductInfoOptions.getProductInfoEntity(content);
			adapter = new DeviceSelectAdapter(MyApplication.getInstance().getCur_Activity(),info,DeviceSelectTools.devices_path);
			gv.setAdapter(adapter);
		}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub	
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		
		gv=(GridView) findViewById(R.id.gv_activity_deviceselect);
		
		
		
		refresh();	
		
		//th.start();
		
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		gv.setOnItemClickListener(this);
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
		return R.layout.activity_adddevice;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(PreventForceClick.isForceClick(PreventForceClick.TIME_WAITSHORT)){
			if(PreventForceClick.isShowToast){
				ToastTools.short_Toast((Activity)MyApplication.getInstance().getCur_Activity(), Tools.CLICK_FAILD);
				PreventForceClick.isShowToast=false;
			}
			
		}else{
			PreventForceClick.isShowToast=true;
				switch(v.getId()){
			
				}
		}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//保存操作
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		WifiSetRemindTools.guideimageURL=info.get(arg2).getGuideimage();
		WifiSetRemindTools.guideType= info.get(arg2).getGuidetype();
		
		ActivitySetting.startUnfinishedActivity((Activity)MyApplication.getInstance().getCur_Activity(), WifiSetActivity.class, null,false);
	}
	
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			String content=(String) msg.obj;
			info=ProductInfoOptions.getProductInfoEntity(content);
			adapter = new DeviceSelectAdapter(AddDeviceActivity.this,info,DeviceSelectTools.devices_path);
			gv.setAdapter(adapter);
			//tv.setText(content);
		};
	};
	
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		//gv.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling));
	}

}

