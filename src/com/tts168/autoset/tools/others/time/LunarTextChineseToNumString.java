package com.tts168.autoset.tools.others.time;
import java.util.ArrayList;




/**
 * 将阴历日期转换成一串数字字符输出
 * 传入的阴历的年月日格式如下：二零壹肆年十二月初一 ——————————
 * 输出 20141201『闰月 在原来的月份的基础上的十位上加2 如09=29，11=31』
 * @author Administrator
 *
 */
public class LunarTextChineseToNumString {

	private static final String TAG_RUN="闰";
	private static final String[] monthName = {"正", "二", "三", "四", "五", "六","七", "八", "九", "十", "十一", "十二" };
	private static final int[]monthNum={1,2,3,4,5,6,7,8,9,10,11,12};
	public static final String dayName[]=new String[]{"初一", "初二", "初三", "初四", "初五", "初六", "初七","初八", "初九", "初十",
		"十一", "十二", "十三", "十四", "十五", "十六", "十七","十八", "十九", "二十",
		"廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七","廿八", "廿九", "三十","卅一"};
	public static final int []dayNum=new int[]{1,2,3,4,5,6,7,8,9,10,
												11,12,13,14,15,16,17,18,19,20,
												21,22,23,24,25,26,27,28,29,30,31};
	private static final String[] yearName = { "零", "一", "二", "三", "四", "五", "六","七", "八", "九" };
	private static final int[]yearNum={0,1,2,3,4,5,6,7,8,9};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(getMonth_list(Integer.parseInt("2013")));
	//	System.out.println(getDay_list(Integer.parseInt("2014"),Integer.parseInt("10")));
//		System.out.println(getYear("贰零壹肆年")); 
		System.out.println(getMonth("七月"));
	//	System.out.println(LunarTextChineseToNumString.getMonth("1915"));
		}
	/**
	 * 传入的阴历的年月日格式如下：二零壹肆年（闰）十二月初一
	 * @param textString
	 * @return
	 */
	public static String getNumString(String textString){
		String NumString="";
	
		String year=textString.split("年")[0];
		String monthAndday=textString.split("年")[1];
		String month=monthAndday.split("月")[0];
		String day=monthAndday.split("月")[1];
		//年的转换
		for(int i=0;i<year.length();i++){
			for(int j=0;j<yearName.length;j++){
				if(yearName[j].equals(year.charAt(i)+"")){
					NumString=NumString+yearNum[j];
					break;
				}
			}
		}
		//月的转换
		//如果为闰月，则在高位+2 如闰九月为29,闰十一月为31
			for(int j=0;j<monthName.length;j++){
				if(month.contains(TAG_RUN)){
					//如果包含闰月
					String mon=month.split(TAG_RUN)[1];
					if(mon.equals(monthName[j])){
						if(monthNum[j]<10){
							NumString=NumString+2;
							NumString=NumString+monthNum[j];
						}else{
							NumString=NumString+(20+monthNum[j]);
						}
						break;
					}
				}else{
					//如果不包含闰月
					if(month.equals(monthName[j])){
						if(monthNum[j]<10){
							NumString=NumString+0;	
						}
						NumString=NumString+monthNum[j];
					break;
					}
				}
				
				}
							
				
		//日的转换	
			for(int j=0;j<dayName.length;j++){
				if(dayName[j].equals(day)){
					if(dayNum[j]<10){
						NumString=NumString+"0";
					}
					NumString=NumString+dayNum[j];
					break;
				}
			}
		return NumString;
	}
	/**
	 * 根据农历年的【二零壹肆】转换成"2014"输出
	 * @param year 农历年
	 * @return
	 */
	public static String getYear(String year){
		String result="";
		
			//年的转换
				for(int i=0;i<year.length();i++){
					for(int j=0;j<yearName.length;j++){
						if(yearName[j].equals(year.charAt(i)+"")){
							result=result+yearNum[j];
							break;
						}
					}
				}
				return result;
	}
	/**
	 * 根据农历年的【二零壹肆】转换成"2014"输出
	 * //月的转换
		//如果为闰月，则在高位+2 如闰九月为29,闰十一月为31
	 * @param month 农历月
	 * @return
	 */
	public static String getMonth(String month){
		String result="";
		for(int j=0;j<monthName.length;j++){
			if(month.contains(TAG_RUN)){
				//如果包含闰月
				
				String mon=month.split(TAG_RUN)[1];
				if(mon.contains("月")){
					mon=mon.substring(0,mon.length()-1);
				}
				if(mon.equals(monthName[j])){
					if(monthNum[j]<10){
						result=result+2;
						result=result+monthNum[j];
					}else{
						result=result+(20+monthNum[j]);
					}
					break;
				}
			}else{
				//如果不包含闰月
				if(month.contains("月")){
					month=month.substring(0,month.length()-1);
				}
				if(month.equals(monthName[j])){
					if(monthNum[j]<10){
						result=result+0;	
					}
					result=result+monthNum[j];
				break;
				}
			}
			
			}
				return result;
	}
	
	/**
	 * 根据农历日的【初一】转换成"01"输出
	 * @param month 农历日
	 * @return
	 */
	public static String getDay(String day){
		String result="";
		//日的转换	
		for(int j=0;j<dayName.length;j++){
			if(dayName[j].equals(day)){
				if(dayNum[j]<10){
					result=result+"0";
				}
				result=result+dayNum[j];
				break;
			}
		}
		return result;
	}
	/**
	 * 根据传入的年份["2014"]得到该年份的农历月份ArrayList列表
	 * @param year 年份["2014"]
	 * @return ArrayList
	 */
	public static ArrayList<String> getMonth_list(int year){
		ArrayList<String> al_month=new ArrayList<String>();
		int year_num=year;
		int leapmonth=SolarToLunarTools.leapMonth(year_num);
		if(leapmonth==0){
			//如果没有闰月
			for(int i=0;i<monthNum.length;i++){
				al_month.add(monthName[i]);
			}
		}else{
			//如果有闰月
			for(int i=0;i<leapmonth;i++){
				al_month.add(monthName[i]);
			}
			al_month.add(TAG_RUN+monthName[leapmonth-1]);
			for(int i=leapmonth;i<monthNum.length;i++){
				al_month.add(monthName[i]);
			}
		}
		al_month.add("");//本项目最后一个不取
		return al_month;
	}
	
	/**
	 * 根据传入的年份["2014"]得到该年份的农历月份ArrayList列表
	 * @param year
	 * @param month
	 * @return ArrayList
	 */
	public static ArrayList<String> getDay_list(int year,int month){
		ArrayList<String> al_day=new ArrayList<String>();
		int totaldays=0;
		if(month>20){
			//证明为闰月
			totaldays=SolarToLunarTools.leapDays(year);
		}else{
			totaldays=SolarToLunarTools.monthDays(year, month);
		}
		for(int i=0;i<totaldays;i++){
			al_day.add(dayName[i]);
		}
		al_day.add("");//本项目最后一个不取
		return al_day;
	}
}
