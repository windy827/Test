package com.tts168.autoset.tools;
/**
 * 线程同步操作【线程锁】先lock()再unlock()
 * @author 袁剑
 *
 */
public class SyncLock {

	private boolean syncLock;
	public SyncLock(){
		syncLock=false;
	}
	
	
	public synchronized void lock(){
		while(syncLock==true){
			try{
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		syncLock=true;
	}
	public synchronized void unlock(){
		syncLock=false;
		notifyAll();
	}
}
