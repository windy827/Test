package com.autoset.jni.fullread;

import java.io.Serializable;

/**
 * 整点报时实体类
 * @author 袁剑
 *
 */
public class FullReadEntity implements Serializable{
	public static final String DOMAIN_CLOCKCHIME="clockchime";//通用参数领域
	
	public static final String CHIME_CHIME="chime";//正点报时开关
	public static final String CHIME_HALF_CHIME	="halfchime";//半点报时开关
	public static final String CHIME_START="start";//报时起始时间
	public static final String CHIME_END="end";//报时结束时间
	/**
	 * 整点开关
	 */
	private int full;
	/**
	 * 半点开关
	 */
	private int half;
	/**
	 * 开始时间
	 */
	private String starttime;
	/**
	 * 结束时间
	 */
	private String endtime;
	/**
	 * KEYS对应的值
	 */
	private Object[]values;
	public static final String []KEYS=new String[]{CHIME_CHIME,CHIME_HALF_CHIME,CHIME_START,CHIME_END};
	public FullReadEntity(int full, int half, String starttime, String endtime) {
		super();
		this.full = full;
		this.half = half;
		this.starttime = starttime;
		this.endtime = endtime;
		values=new Object[]{this.full,this.half,this.starttime,this.endtime};
	}
	public void setValues(Object[] values){
		this.values=values;
	}
	public Object[] getValues(){
		return values;
	}
	public int getFull() {
		return full;
	}
	public void setFull(int full) {
		this.full = full;
	}
	public int getHalf() {
		return half;
	}
	public void setHalf(int half) {
		this.half = half;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	

	
}
