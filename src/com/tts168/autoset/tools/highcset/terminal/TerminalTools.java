package com.tts168.autoset.tools.highcset.terminal;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;

/**
 * 终端工具类
 * @author 袁剑
 *
 */
public class TerminalTools {
	/**
	 * 终端列表适配器上的内容
	 */
	public static ArrayList<TerminalAdapterEntity> adapter_infoByIP=new ArrayList<TerminalAdapterEntity>();
	public static final String TYPE_DEVICE_PID_BAILING1="000001";
	
	public static final String TYPE_DEVICE_PID_BAILING5="000005";
	/**
	 * 根据产品ID得到对应的图片
	 * @param pid
	 * @return
	 */
	public static int getDeviceDrawableRID(String pid){
		int drawable=R.drawable.bailing1;
		if(pid.equals(TYPE_DEVICE_PID_BAILING1)){
			drawable=R.drawable.bailing2;
		}else if(pid.equals(TYPE_DEVICE_PID_BAILING5)){
			drawable=R.drawable.bailing15;
		}
		
		else if(pid.equals("10001")){
			drawable=R.drawable.bailing4;
		}else if(pid.equals("10002")){
			drawable=R.drawable.bailing3;
		}
		else if(pid.equals("10003")){
			drawable=R.drawable.bailing6;
		}
		else if(pid.equals("10004")){
			drawable=R.drawable.bailing5;
		}
		else{
			drawable=R.drawable.bailing1;
		}
		return drawable;
	} 
}
