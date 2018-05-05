package com.tts168.autoset.tools.highcset.wakeset;

import com.autoset.jni.wakeup.WakeUpEntity;
import com.larkiv.larksmart7618.R;

/**
 * 唤醒控制工具类
 * @author 袁剑
 *
 */
public class WakeSetTools {

	public static final String []AWAKE_NAMES=new String[]{"Hi云宝","Hi小播","云宝","小播","Hi百灵","百灵"};
	
	/**
	 * 传递wakeup实体类的键名称
	 */
	public static final String KEYS_ENTITY="wakeupEntity";
	public static final int SELECTED_COLOR=R.color.gray99_color;
	public static final int NOTSELECTED_COLOR=R.color.gray66_color;
	/**
	 * 唤醒时间段限制开关0永远打开，1限时段打开；
	 */
	public static final double[]time_limit=new double[]{WakeUpEntity.time_limit_NO,WakeUpEntity.time_limit_YES};
}
