package com.tts168.autoset.tools.highcset.wakeset;

import com.autoset.jni.wakeup.WakeUpEntity;
import com.larkiv.larksmart7618.R;

/**
 * ���ѿ��ƹ�����
 * @author Ԭ��
 *
 */
public class WakeSetTools {

	public static final String []AWAKE_NAMES=new String[]{"Hi�Ʊ�","HiС��","�Ʊ�","С��","Hi����","����"};
	
	/**
	 * ����wakeupʵ����ļ�����
	 */
	public static final String KEYS_ENTITY="wakeupEntity";
	public static final int SELECTED_COLOR=R.color.gray99_color;
	public static final int NOTSELECTED_COLOR=R.color.gray66_color;
	/**
	 * ����ʱ������ƿ���0��Զ�򿪣�1��ʱ�δ򿪣�
	 */
	public static final double[]time_limit=new double[]{WakeUpEntity.time_limit_NO,WakeUpEntity.time_limit_YES};
}
