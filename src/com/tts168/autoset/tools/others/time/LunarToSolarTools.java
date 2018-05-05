package com.tts168.autoset.tools.others.time;
import java.text.DateFormat;
import java.util.HashMap;
/**
 * 阴历转阳历的工具类
 * @author 袁剑
 *
 */
public class LunarToSolarTools {
	public static final int LEAP_MONTH_ADDNUM=20;//为闰月显示的月份在元月份的基础上添加的值
	
	
	public static void main(String[] args) {  
		// 调用农历日期转换阳历日期方法   
		//System.out.println((1-(-3)))
		
		System.out.println(getSolar("20142922"));
	}
	/**
	 * 传入阴历日期的字符串【如“20140901”，闰九月为“20142901”】，
	 * 返回一个携带年月日的HashMap，key值为year，month，day
	 * @param lunarNumData
	 * @return
	 */
	public static HashMap<String,String>getSolar(String lunarNumData){
		HashMap<String,String >temp=splitStringData(lunarNumData);
		int year=Integer.parseInt(temp.get("year"));
		int month=Integer.parseInt(temp.get("month"));
		//在我们的程序里面闰月是在原来月份的基础上加上20，二本功能程序对闰月的处理是
		if(month>LEAP_MONTH_ADDNUM){
			month=month-(LEAP_MONTH_ADDNUM-12);
		}
		int day=Integer.parseInt(temp.get("day"));
		HashMap<String,String >result=splitStringData(ChineseCalendar.sCalendarLundarToSolar(year,month, day));
		return result;
	}
	/**
	 * 针对20141102这种情况设定的分解字符串变成我们想要的年月日的HashMap
	 * @param data
	 * @return year，month,day  key值对应年月日
	 */
	public static HashMap<String,String> splitStringData(String data){
		String year=data.substring(0, 4);
		String month=data.substring(4, 6);
		String day=data.substring(6, 8);
		HashMap<String,String>result=new HashMap<String, String>();
		result.put("year", year);
		result.put("month", month);
		result.put("day", day);
		return result;
	}
	}
// 自定义日历类

class ChineseCalendar {
	// Array lIntLunarDay is stored in the monthly day information in every year from 1901 to 2100 of the lunar calendar,
	// The lunar calendar can only be 29 or 30 days every month, express with 12(or 13) pieces of binary bit in one year,
	// it is 30 days for 1 form in the corresponding location , otherwise it is 29 daysprivate
	static final int[] iLunarMonthDaysTable = { 0x4ae0, 0xa570, 0x5268, 0xd260, 0xd950, 0x6aa8, 0x56a0, 0x9ad0, 0x4ae8,0x4ae0, // 1910    
		0xa4d8, 0xa4d0, 0xd250, 0xd548, 0xb550, 0x56a0, 0x96d0, 0x95b0, 0x49b8, 0x49b0, // 1920  
		0xa4b0, 0xb258, 0x6a50, 0x6d40, 0xada8, 0x2b60, 0x9570, 0x4978, 0x4970, 0x64b0, // 1930  
		0xd4a0, 0xea50, 0x6d48, 0x5ad0, 0x2b60, 0x9370, 0x92e0, 0xc968, 0xc950, 0xd4a0, // 1940   
		0xda50, 0xb550, 0x56a0, 0xaad8, 0x25d0, 0x92d0, 0xc958, 0xa950, 0xb4a8, 0x6ca0, // 1950   
		0xb550, 0x55a8, 0x4da0, 0xa5b0, 0x52b8, 0x52b0, 0xa950, 0xe950, 0x6aa0, 0xad50, // 1960   
		0xab50, 0x4b60, 0xa570, 0xa570, 0x5260, 0xe930, 0xd950, 0x5aa8, 0x56a0, 0x96d0, // 1970   
		0x4ae8, 0x4ad0, 0xa4d0, 0xd268, 0xd250, 0xd528, 0xb540, 0xb6a0, 0x96d0, 0x95b0, // 1980   
		0x49b0, 0xa4b8, 0xa4b0, 0xb258, 0x6a50, 0x6d40, 0xada0, 0xab60, 0x9370, 0x4978, // 1990   
		0x4970, 0x64b0, 0x6a50, 0xea50, 0x6b28, 0x5ac0, 0xab60, 0x9368, 0x92e0, 0xc960, // 2000   
		0xd4a8, 0xd4a0, 0xda50, 0x5aa8, 0x56a0, 0xaad8, 0x25d0, 0x92d0, 0xc958, 0xa950, // 2010    
		0xb4a0, 0xb550, 0xb550, 0x55a8, 0x4ba0, 0xa5b0, 0x52b8, 0x52b0, 0xa930, 0x74a8, // 2020     
		0x6aa0, 0xad50, 0x4da8, 0x4b60, 0x9570, 0xa4e0, 0xd260, 0xe930, 0xd530, 0x5aa0, // 2030   
		0x6b50, 0x96d0, 0x4ae8, 0x4ad0, 0xa4d0, 0xd258, 0xd250, 0xd520, 0xdaa0, 0xb5a0, // 2040   
		0x56d0, 0x4ad8, 0x49b0, 0xa4b8, 0xa4b0, 0xaa50, 0xb528, 0x6d20, 0xada0, 0x55b0 // 2050
		};
		// Array iLunarLeapMonthTable preserves the lunar calendar leap month from 1901 to 2050,
		// if it is 0 express not to have , every byte was stored for two yearsprivate
		static final char[] iLunarLeapMonthTable = { 0x00, 0x50, 0x04, 0x00, 0x20, // 1910   
		0x60, 0x05, 0x00, 0x20, 0x70, // 1920  
		0x05, 0x00, 0x40, 0x02, 0x06, // 1930   
		0x00, 0x50, 0x03, 0x07, 0x00, // 1940     
		0x60, 0x04, 0x00, 0x20, 0x70, // 1950      
		0x05, 0x00, 0x30, 0x80, 0x06, // 1960     
		0x00, 0x40, 0x03, 0x07, 0x00, // 1970     
		0x50, 0x04, 0x08, 0x00, 0x60, // 1980     
		0x04, 0x0a, 0x00, 0x60, 0x05, // 1990     
		0x00, 0x30, 0x80, 0x05, 0x00, // 2000     
		0x40, 0x02, 0x07, 0x00, 0x50, // 2010     
		0x04, 0x09, 0x00, 0x60, 0x04, // 2020     
		0x00, 0x20, 0x60, 0x05, 0x00, // 2030     
		0x30, 0xb0, 0x06, 0x00, 0x50, // 2040     
		0x02, 0x07, 0x00, 0x50, 0x03 // 2050
		};
		// Array iSolarLunarTable stored the offset days
		// in New Year of solar calendar and lunar calendar from 1901 to 2050;
		private static final char[] iSolarLunarOffsetTable = { 
			49, 38, 28, 46, 34, 24, 43, 32, 21, 40, // 1910  
			29, 48, 36, 25, 44, 34, 22, 41, 31, 50, // 1920    
			38, 27, 46, 35, 23, 43, 32, 22, 40, 29, // 1930    
			47, 36, 25, 44, 34, 23, 41, 30, 49, 38, // 1940   
			26, 45, 35, 24, 43, 32, 21, 40, 28, 47, // 1950   
			36, 26, 44, 33, 23, 42, 30, 48, 38, 27, // 1960    
			45, 35, 24, 43, 32, 20, 39, 29, 47, 36, // 1970    
			26, 45, 33, 22, 41, 30, 48, 37, 27, 46, // 1980     
			35, 24, 43, 32, 50, 39, 28, 47, 36, 26, // 1990    
			45, 34, 22, 40, 30, 49, 37, 27, 46, 35, // 2000    
			23, 42, 31, 21, 39, 28, 48, 37, 25, 44, // 2010    
			33, 22, 40, 30, 49, 38, 27, 46, 35, 24, // 2020    
			42, 31, 21, 40, 28, 47, 36, 25, 43, 33, // 2030    
			22, 41, 30, 49, 38, 27, 45, 33, 23, 42, // 2040    
			31, 21, 40, 29, 47, 36, 25, 44, 32, 22, // 2050
			};
		static boolean bIsSolarLeapYear(int iYear) { 
			return ((iYear % 4 == 0) && (iYear % 100 != 0) || iYear % 400 == 0);
			}
		// The days in the month of solar calendar
		static int iGetSYearMonthDays(int iYear, int iMonth) {  
			if ((iMonth == 1) || (iMonth == 3) || (iMonth == 5) || (iMonth == 7) || 
					(iMonth == 8) || (iMonth == 10) || (iMonth == 12))   
				return 31;   
			else if ((iMonth == 4) || (iMonth == 6) || (iMonth == 9) || (iMonth == 11))  
				return 30;   
			else if (iMonth == 2) {    
				if (bIsSolarLeapYear(iYear))      
					return 29;    
				else      
					return 28;   
				} else     
					return 0;}
		// The offset days from New Year and the day when point out in solar calendar
		static int iGetSNewYearOffsetDays(int iYear, int iMonth, int iDay) {   
			int iOffsetDays = 0;  
			for (int i = 1; i < iMonth; i++) { 
				iOffsetDays += iGetSYearMonthDays(iYear, i);  
				}   
			iOffsetDays += iDay - 1;  
			return iOffsetDays;
			}
		static int iGetLLeapMonth(int iYear) { 
			char iMonth = iLunarLeapMonthTable[(iYear - 1901) / 2];  
			if (iYear % 2 == 0)   
				return (iMonth & 0x0f);  
			else    
				return (iMonth & 0xf0) >> 4;
			}
		static int iGetLMonthDays(int iYear, int iMonth) { 
			int iLeapMonth = iGetLLeapMonth(iYear);  
			if ((iMonth > 12) && (iMonth - 12 != iLeapMonth) || (iMonth < 0)) { 
				System.out.println("Wrong month, ^_^ , i think you are want a -1, go to death!"); 
				return -1;   
				}   
			if (iMonth - 12 == iLeapMonth) {  
				if ((iLunarMonthDaysTable[iYear - 1901] & (0x8000 >> iLeapMonth)) == 0)  
					return 29;  
				else   
					return 30;    
				}   
			if ((iLeapMonth > 0) && (iMonth > iLeapMonth))     
				iMonth++;    
			if ((iLunarMonthDaysTable[iYear - 1901] & (0x8000 >> (iMonth - 1))) == 0)   
				return 29;  
			else    
				return 30;}
		// Days in this year of lunar calendar
		static int iGetLYearDays(int iYear) {  
			int iYearDays = 0;  
			int iLeapMonth = iGetLLeapMonth(iYear); 
			for (int i = 1; i < 13; i++)  
				iYearDays += iGetLMonthDays(iYear, i); 
			if (iLeapMonth > 0)      
				iYearDays += iGetLMonthDays(iYear, iLeapMonth + 12);  
			return iYearDays;
			}
		static int iGetLNewYearOffsetDays(int iYear, int iMonth, int iDay) {  
			int iOffsetDays = 0;   
			int iLeapMonth = iGetLLeapMonth(iYear); 
			if ((iLeapMonth > 0) && (iLeapMonth == iMonth - 12)) {  
				iMonth = iLeapMonth;    
				iOffsetDays += iGetLMonthDays(iYear, iMonth);  
				}   
			for (int i = 1; i < iMonth; i++) { 
				iOffsetDays += iGetLMonthDays(iYear, i);  
				if (i == iLeapMonth)   
					iOffsetDays += iGetLMonthDays(iYear, iLeapMonth + 12);   
				}    
			iOffsetDays += iDay - 1;  
			return iOffsetDays;
			}
		// The solar calendar is turned into the lunar calendar
		static String sCalendarSolarToLundar(int iYear, int iMonth, int iDay) { 
			int iLDay, iLMonth, iLYear;  
			int iOffsetDays = iGetSNewYearOffsetDays(iYear, iMonth, iDay); 
			int iLeapMonth = iGetLLeapMonth(iYear);  
			if (iOffsetDays < iSolarLunarOffsetTable[iYear - 1901]) {  
				iLYear = iYear - 1;    
				iOffsetDays = iSolarLunarOffsetTable[iYear - 1901] - iOffsetDays; 
				iLDay = iOffsetDays;  
				for (iLMonth = 12;
						iOffsetDays > iGetLMonthDays(iLYear, iLMonth); iLMonth--) { 
					iLDay = iOffsetDays;   
					iOffsetDays -= iGetLMonthDays(iLYear, iLMonth);  
					}    
				if (0 == iLDay)    
					iLDay = 1;   
				else      
					iLDay = iGetLMonthDays(iLYear, iLMonth) - iOffsetDays + 1;  
				} else {   
					iLYear = iYear;   
					iOffsetDays -= iSolarLunarOffsetTable[iYear - 1901]; 
					iLDay = iOffsetDays + 1;   
					for (iLMonth = 1; iOffsetDays >= 0; iLMonth++) {  
						iLDay = iOffsetDays + 1;    
						iOffsetDays -= iGetLMonthDays(iLYear, iLMonth);  
						if ((iLeapMonth == iLMonth) && (iOffsetDays > 0)) {  
							iLDay = iOffsetDays;      
							iOffsetDays -= iGetLMonthDays(iLYear, iLMonth + 12); 
							if (iOffsetDays <= 0) {   
								iLMonth += 12 + 1;         
								break;         
								}     
							}      
						}      
					iLMonth--;  
					}   
			return "" + iLYear + (iLMonth > 9 ? "" + iLMonth : "0" + iLMonth) + (iLDay > 9 ? "" + iLDay : "0" + iLDay);}
		// The lunar calendar is turned into the Solar calendar
		/**
		 * 根据阴历的年日得出阳历日期，以字符串形式输出
		 * @param iYear
		 * @param iMonth【若为闰月 则在月份的基础上加上12，如闰九月=21】
		 * @param iDay
		 * @return
		 */
		static String sCalendarLundarToSolar(int iYear, int iMonth, int iDay) {  
			int iSYear, iSMonth, iSDay;  
			int iOffsetDays = iGetLNewYearOffsetDays(iYear, iMonth, iDay) + iSolarLunarOffsetTable[iYear - 1901]; 
			int iYearDays = bIsSolarLeapYear(iYear) ? 366 : 365;  
			if (iOffsetDays >= iYearDays) {   
				iSYear = iYear + 1;    
				iOffsetDays -= iYearDays;  
				} else {  
					iSYear = iYear;  
					}  
			iSDay = iOffsetDays + 1; 
			for (iSMonth = 1; iOffsetDays >= 0; iSMonth++) {    
				iSDay = iOffsetDays + 1;   
				iOffsetDays -= iGetSYearMonthDays(iSYear, iSMonth); 
				}   
			iSMonth--;   
			return "" + iSYear + (iSMonth > 9 ? iSMonth + "" : "0" + iSMonth) + (iSDay > 9 ? iSDay + "" : "0" + iSDay);
			}
		
		
	
	
}