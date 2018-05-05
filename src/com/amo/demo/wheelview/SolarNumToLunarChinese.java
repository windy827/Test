package com.amo.demo.wheelview;
/**
 * �����࣬��������ʽ�����ڵ��꣬�£���ת������Ҫ��������ʽ
 * @author Ԭ��
 *
 */
public class SolarNumToLunarChinese {
	private static final String[] monthName = {"��", "��", "��", "��", "��", "��","��", "��", "��", "ʮ", "ʮһ", "ʮ��" };
	private static final int[]monthNum={1,2,3,4,5,6,7,8,9,10,11,12};
	public static final String dayName[]=new String[]{"��һ", "����", "����", "����", "����", "����", "����","����", "����", "��ʮ",
		"ʮһ", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "ʮ��", "ʮ��","ʮ��", "ʮ��", "��ʮ",
		"إһ", "إ��", "إ��", "إ��", "إ��", "إ��", "إ��","إ��", "إ��", "��ʮ","ئһ"};
	public static final int []dayNum=new int[]{1,2,3,4,5,6,7,8,9,10,
												11,12,13,14,15,16,17,18,19,20,
												21,22,23,24,25,26,27,28,29,30,31};
	private static final String[] yearName = { "��", "һ", "��", "��", "��", "��", "��","��", "��", "��" };
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
	 * ���������꡾1991�������ء�Ҽ����Ҽ��
	 * @param yearsNum 1991
	 * @return
	 */
	public static String NumYearToChineseYear(int yearsNum){
		String chineseYear="";
		String year=yearsNum+"";
		//���ת��
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
	 * ���������¡�1�������ء�����
	 * ���뷶ΧΪ1-12
	 * @param monthsNum 1
	 * @return
	 */
	public static String monthNumToChineseYear(int monthsNum){
		String NumString="";
		//�µ�ת��
		for(int j=0;j<monthNum.length;j++){
			if(monthsNum==monthNum[j]){
				
				NumString=NumString+monthName[j];
				break;
			}
		}
				return NumString;
	}
	
	/**
	 * ���������¡�27�������ء�إ�ߡ�
	 * ���뷶ΧΪ1-31
	 * @param daysNum 1
	 * @return
	 */
	public static String dayNumToChineseYear(int daysNum){
		String NumString="";
		//�µ�ת��
		for(int j=0;j<dayNum.length;j++){
			if(daysNum==dayNum[j]){
				
				NumString=NumString+dayName[j];
				break;
			}
		}
				return NumString;
	}
}
