package com.tts168.autoset.view.volcontrol;

import android.app.Activity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.view.MyBaseView;

/**
 * FSK配置音量强制调节
 * @author 袁剑
 *
 */
public class VolControlView extends MyBaseView implements OnSeekBarChangeListener{

	SeekBar sb;
	TextView tv_vol_pro;
	public static final String CUR_VOL="当前音量："; 
	float vol_cur_percent=0.8f;
	public static float MIN_VOL=0.4f;
	public VolControlView(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticFindViewByView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticByViewListener() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 得到当前的音量百分比
	 * @return
	 */
	public float getCurVolPercent(){
		return vol_cur_percent;
	}
/**
 * 设置进度条和TextView的显示内容
 * @param vol_percent
 */
	public void setProgress(float vol_percent){
		vol_cur_percent=vol_percent;
		sb.setProgress((int)((vol_percent-MIN_VOL)*10)/2);
		tv_vol_pro.setText(CUR_VOL+(int)(vol_percent*100)+"%");
	}
	@Override
	public void staticFindViewByActivity() {
		// TODO Auto-generated method stub
		sb=(SeekBar) activity.findViewById(R.id.sb_vol);
		tv_vol_pro=(TextView) activity.findViewById(R.id.tv_cur_percent);
	}

	@Override
	public void staticByActivityListener() {
		// TODO Auto-generated method stub
		sb.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		float percent=((float)progress*2)/10.0f;
		vol_cur_percent=MIN_VOL+percent;
		tv_vol_pro.setText(CUR_VOL+(int)(vol_cur_percent*100)+"%");
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	
}
