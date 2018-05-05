package com.tts168.autoset.tools.others.time;
import java.util.ArrayList;




/**
 * ����������ת����һ�������ַ����
 * ����������������ո�ʽ���£�����Ҽ����ʮ���³�һ ��������������������
 * ��� 20141201������ ��ԭ�����·ݵĻ����ϵ�ʮλ�ϼ�2 ��09=29��11=31��
 * @author Administrator
 *
 */
public class LunarTextChineseToNumString {

	private static final String TAG_RUN="��";
	private static final String[] monthName = {"��", "��", "��", "��", "��", "��","��", "��", "��", "ʮ", "ʮһ", "ʮ��" };
	private static final int[]monthNum={1,2,3,4,5,6,7,8,9,10,11,12};
	public static final String dayName[]=new String[]{"��һ", "����", "����", "����", "����", "����", "����","����", "����", "��ʮ",
		"ʮһ", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "ʮ��","ʮ��", "ʮ��", "��ʮ",
		"إһ", "إ��", "إ��", "إ��", "إ��", "إ��", "إ��","إ��", "إ��", "��ʮ","ئһ"};
	public static final int []dayNum=new int[]{1,2,3,4,5,6,7,8,9,10,
												11,12,13,14,15,16,17,18,19,20,
												21,22,23,24,25,26,27,28,29,30,31};
	private static final String[] yearName = { "��", "һ", "��", "��", "��", "��", "��","��", "��", "��" };
	private static final int[]yearNum={0,1,2,3,4,5,6,7,8,9};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(getMonth_list(Integer.parseInt("2013")));
	//	System.out.println(getDay_list(Integer.parseInt("2014"),Integer.parseInt("10")));
//		System.out.println(getYear("����Ҽ����")); 
		System.out.println(getMonth("����"));
	//	System.out.println(LunarTextChineseToNumString.getMonth("1915"));
		}
	/**
	 * ����������������ո�ʽ���£�����Ҽ���꣨��ʮ���³�һ
	 * @param textString
	 * @return
	 */
	public static String getNumString(String textString){
		String NumString="";
	
		String year=textString.split("��")[0];
		String monthAndday=textString.split("��")[1];
		String month=monthAndday.split("��")[0];
		String day=monthAndday.split("��")[1];
		//���ת��
		for(int i=0;i<year.length();i++){
			for(int j=0;j<yearName.length;j++){
				if(yearName[j].equals(year.charAt(i)+"")){
					NumString=NumString+yearNum[j];
					break;
				}
			}
		}
		//�µ�ת��
		//���Ϊ���£����ڸ�λ+2 �������Ϊ29,��ʮһ��Ϊ31
			for(int j=0;j<monthName.length;j++){
				if(month.contains(TAG_RUN)){
					//�����������
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
					//�������������
					if(month.equals(monthName[j])){
						if(monthNum[j]<10){
							NumString=NumString+0;	
						}
						NumString=NumString+monthNum[j];
					break;
					}
				}
				
				}
							
				
		//�յ�ת��	
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
	 * ����ũ����ġ�����Ҽ����ת����"2014"���
	 * @param year ũ����
	 * @return
	 */
	public static String getYear(String year){
		String result="";
		
			//���ת��
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
	 * ����ũ����ġ�����Ҽ����ת����"2014"���
	 * //�µ�ת��
		//���Ϊ���£����ڸ�λ+2 �������Ϊ29,��ʮһ��Ϊ31
	 * @param month ũ����
	 * @return
	 */
	public static String getMonth(String month){
		String result="";
		for(int j=0;j<monthName.length;j++){
			if(month.contains(TAG_RUN)){
				//�����������
				
				String mon=month.split(TAG_RUN)[1];
				if(mon.contains("��")){
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
				//�������������
				if(month.contains("��")){
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
	 * ����ũ���յġ���һ��ת����"01"���
	 * @param month ũ����
	 * @return
	 */
	public static String getDay(String day){
		String result="";
		//�յ�ת��	
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
	 * ���ݴ�������["2014"]�õ�����ݵ�ũ���·�ArrayList�б�
	 * @param year ���["2014"]
	 * @return ArrayList
	 */
	public static ArrayList<String> getMonth_list(int year){
		ArrayList<String> al_month=new ArrayList<String>();
		int year_num=year;
		int leapmonth=SolarToLunarTools.leapMonth(year_num);
		if(leapmonth==0){
			//���û������
			for(int i=0;i<monthNum.length;i++){
				al_month.add(monthName[i]);
			}
		}else{
			//���������
			for(int i=0;i<leapmonth;i++){
				al_month.add(monthName[i]);
			}
			al_month.add(TAG_RUN+monthName[leapmonth-1]);
			for(int i=leapmonth;i<monthNum.length;i++){
				al_month.add(monthName[i]);
			}
		}
		al_month.add("");//����Ŀ���һ����ȡ
		return al_month;
	}
	
	/**
	 * ���ݴ�������["2014"]�õ�����ݵ�ũ���·�ArrayList�б�
	 * @param year
	 * @param month
	 * @return ArrayList
	 */
	public static ArrayList<String> getDay_list(int year,int month){
		ArrayList<String> al_day=new ArrayList<String>();
		int totaldays=0;
		if(month>20){
			//֤��Ϊ����
			totaldays=SolarToLunarTools.leapDays(year);
		}else{
			totaldays=SolarToLunarTools.monthDays(year, month);
		}
		for(int i=0;i<totaldays;i++){
			al_day.add(dayName[i]);
		}
		al_day.add("");//����Ŀ���һ����ȡ
		return al_day;
	}
}
