package com.tts168.autoset.tools.test;
/**
 * UDP扫描和TCP设备刷新的实体类
 * 以8，11，white,gold的顺序进行
 * @author 袁剑
 *
 */
public class UdpAndTcpTestEntity {

	public static final int FLAG_HAS=1;
	public static final int FLAG_NO=0;
	private int state_8=0;
	private int state_11=0;
	private int state_white=0;
	private int state_gold=0;
	
	public UdpAndTcpTestEntity(int state_8, int state_11, int state_white,
			int state_gold) {
		super();
		this.state_8 = state_8;
		this.state_11 = state_11;
		this.state_white = state_white;
		this.state_gold = state_gold;
	}
	public int getState_8() {
		return state_8;
	}
	public void setState_8(int state_8) {
		this.state_8 = state_8;
	}
	public int getState_11() {
		return state_11;
	}
	public void setState_11(int state_11) {
		this.state_11 = state_11;
	}
	public int getState_white() {
		return state_white;
	}
	public void setState_white(int state_white) {
		this.state_white = state_white;
	}
	public int getState_gold() {
		return state_gold;
	}
	public void setState_gold(int state_gold) {
		this.state_gold = state_gold;
	}
	
	
}
