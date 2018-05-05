package com.tts168.autoset.tools.alart;
import java.util.Random;

/**
 * 生成在一定范围内[MaxId]的随机数ID，生成的ID不能再已有数组中存在
 * @author 袁剑
 *
 */
public class GeneralID {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean  canAdd=canGenerID(new int[]{0,1,2,3},7);
		for(int i=0;i<20;i++){
			if(canAdd){
				System.out.println(generalID(new int[]{0,1,2,3},7));
			}
			
		}

		
	}
	
	/**
	 * 判断当前数组是否可以再生成ID
	 * @param hasID 已经存在的数组
	 * @param maxID 最大的数组值
	 * @return
	 */
	public static boolean canGenerID(int[]hasID,int maxID){
		boolean canGenerID=true;
		if(hasID.length>=maxID){
			//表示当前数组ID已经占满了，不可添加
			canGenerID=false;
		}
		return canGenerID;
	}
/**
 * 生成ID号
 * @param hasID 已经存在的ID号
 * @param maxID 最大ID号
 * @return 如果返回的值大于数组的最大值则说明没有ID可以生成
 */
	public static int generalID(int[]hasID,int maxID){
		int result=0;
		if(hasID.length>=maxID){
			//表示当前数组ID已经占满了，不可添加
			result=maxID+1;
		}else if(hasID.length==0){
			//当没有闹铃时，默认ID为0
			result=0;
		}else{
			boolean isExist=true;
			while(isExist){
				Random rom=new Random();
				int gernalID=rom.nextInt(maxID);
				for(int i=0;i<hasID.length;i++){
					if(gernalID==hasID[i]){
						isExist=true;
						break;
					}else{
						if(i==hasID.length-1){
							isExist=false;
							result=gernalID;
						}
					}
				}
			}
			
			
			
		}
		
		return result;
		
	}

}
