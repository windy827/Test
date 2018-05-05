package com.amo.demo.wheelview;
/**
 * 工具类，将数字形式的日期的年，月，日转换成需要的文字形式
 * @author 袁剑
 *
 */
public class SolarNumToLunarChinese {
	private static final String[] monthName = {"正", "二", "三", "四", "五", "六","七", "八", "九", "十", "十一", "十二" };
	private static final int[]monthNum={1,2,3,4,5,6,7,8,9,10,11,12};
	public static final String dayName[]=new String[]{"初一", "初二", "初三", "初四", "初五", "初六", "初七","初八", "初九", "初十",
		"十一", "十二", "十三", "十四", "十五", "十六", "十七","十八", "十九", "二十",
		"廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七","廿八", "廿九", "三十","卅一"};
	public static final int []dayNum=new int[]{1,2,3,4,5,6,7,8,9,10,
												11,12,13,14,15,16,17,18,19,20,
												21,22,23,24,25,26,27,28,29,30,31};
	private static final String[] yearName = { "零", "一", "二", "三", "四", "五", "六","七", "八", "九" };
	private static final int[]yearNum=new int[]{0,1,2,3,4,5,6,7,8,9};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(NumYearToChineseYear(1991));
System.out.println(monthNumToChineseYear(1));
System.out.println(dayNumToChineseYear(27));
	}
	/**
	 * 输入日期年【1991】，返回【壹玖玖壹】
	 * @param yearsNum 1991
	 * @return
	 */
	public static String NumYearToChineseYear(int yearsNum){
		String chineseYear="";
		String year=yearsNum+"";
		//年的转换
				for(int i=0;i<year.length();i++){
					for(int j=0;j<yearNum.length;j++){
						if((yearNum[j]+"").equals(year.charAt(i)+"")){
							chineseYear=chineseYear+yearName[j];
							break;
						}
					}
				}
				return chineseYear;
	}
	
	/**
	 * 输入日期月【1】，返回【正】
	 * 输入范围为1-12
	 * @param monthsNum 1
	 * @return
	 */
	public static String monthNumToChineseYear(int monthsNum){
		String NumString="";
		//月的转换
		for(int j=0;j<monthNum.length;j++){
			if(monthsNum==monthNum[j]){
				
				NumString=NumString+monthName[j];
				break;
			}
		}
				return NumString;
	}
	
	/**
	 * 输入日期月【27】，返回【廿七】
	 * 输入范围为1-31
	 * @param daysNum 1
	 * @return
	 */
	public static String dayNumToChineseYear(int daysNum){
		String NumString="";
		//月的转换
		for(int j=0;j<dayNum.length;j++){
			if(daysNum==dayNum[j]){
				
				NumString=NumString+dayName[j];
				break;
			}
		}
				return NumString;
	}
}
