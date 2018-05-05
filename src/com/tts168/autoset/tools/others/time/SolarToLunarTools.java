package com.tts168.autoset.tools.others.time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
/**
 * �õ�����ת�������ڵĹ�����
 * ��Ҫ���� getLunar(String year, String month,
			String day)
 * @author Ԭ��
 *
 */
public final class SolarToLunarTools {

	// private static Log log = LogFactory.getLog(Lauar.class);

	private static int monCyl, dayCyl, yearCyl;

	private static int year, month, day;

	private static boolean isLeap;

	private static int[] lunarInfo = { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5,

		0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,

		0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2,

		0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,

		0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,

		0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7,

		0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0,

		0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355,

		0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,

		0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,

		0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0,

		0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0,

		0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,

		0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50,

		0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954,

		0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0,

		0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,

		0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50,

		0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,

		0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,

		0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,

		0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0 };

		private static int[] solarMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,

		30, 31 };

		private static String[] Gan = { "��", "��", "��", "��", "��", "��", "��", "��",

		"��", "��" };

		private static String[] Zhi = { "��", "��", "��", "î", "��", "��", "��", "δ",

		"��", "��", "��", "��" };

		private static String[] Animals = { "��", "ţ", "��", "��", "��", "��", "��", "��",

		"��", "��", "��", "��" };

		private static int[] sTermInfo = { 0, 21208, 42467, 63836, 85337, 107014,

		128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989,

		308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224,

		483532, 504758 };

		private static String[] nStr1 = { "��", "һ", "��", "��", "��", "��", "��", "��",

		"��", "��", "ʮ" };

		private static String[] nStr2 = { "��", "ʮ", "إ", "ئ", "��" };

		private static String[] monthNong = { "��", "��", "��", "��", "��", "��", "��",

		"��", "��", "��", "ʮ", "ʮһ", "ʮ��" };

		private static String[] yearName = { "��", "һ", "��", "��", "��", "��", "��",

		"��", "��", "��" };


	public SolarToLunarTools() {

	}

	// ====================================== ����ũ�� y���������

	private static int lYearDays(int y) {

		int i;

		int sum = 348; // 29*12

		for (i = 0x8000; i > 0x8; i >>= 1) {

			// OurLog.debug("i="+i);

			sum += (lunarInfo[y - 1900] & i) == 0 ? 0 : 1; // ����+1��

		}

		return (sum + leapDays(y)); // +���µ�����

	}
/**
	 ����ũ�� y�����µ�����
*/
	public static int leapDays(int y) {

		if (leapMonth(y) != 0)

			return ((lunarInfo[y - 1900] & 0x10000) == 0 ? 29 : 30);

		else

			return (0);

	}
	/**
	 ����ũ�� y�����ĸ��� 1-12 , û�򴫻� 0
*/
	public static int leapMonth(int y) {

		return (lunarInfo[y - 1900] & 0xf);

	}

	/**
	 * ����ũ�� y��m�µ�������[������]
	 * @param y
	 * @param m
	 * @return
	 */

	public static int monthDays(int y, int m) {

		return ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0 ? 29 : 30);

	}

	// ====================================== ���ũ��, �����������, ����ũ���������

	// ����������� .year .month .day .isLeap .yearCyl .dayCyl .monCyl

	private static void Lunar1(Date objDate) {

		int i, leap = 0, temp = 0;

		// int monCyl,dayCyl,yearCyl;

		// int year,month,day;

		// boolean isLeap;

		Calendar cl = Calendar.getInstance();

		cl.set(1900, 0, 31); // 1900-01-31��ũ��1900�����³�һ

		Date baseDate = cl.getTime();

		// 1900-01-31��ũ��1900�����³�һ

		int offset = (int) ((objDate.getTime() - baseDate.getTime()) / 86400000); // ����(86400000=24*60*60*1000)

		// System.out.println(offset);

		dayCyl = offset + 40; // 1899-12-21��ũ��1899�����¼�����

		monCyl = 14; // 1898-10-01��ũ��������

		// �õ�����

		for (i = 1900; i < 2050 && offset > 0; i++) {

			temp = lYearDays(i); // ũ��ÿ������

			offset -= temp;

			monCyl += 12;

		}

		if (offset < 0) {

			offset += temp;

			i--;

			monCyl -= 12;

		}

		year = i; // ũ�����

		yearCyl = i - 1864; // 1864���Ǽ�����

		leap = leapMonth(i); // ���ĸ���

		isLeap = false;

		for (i = 1; i < 13 && offset > 0; i++) {

			// ����

			if (leap > 0 && i == (leap + 1) && isLeap == false) {

				--i;

				isLeap = true;

				temp = leapDays(year);

			} else {

				temp = monthDays(year, i);

			}

			// �������

			if (isLeap == true && i == (leap + 1))

				isLeap = false;

			offset -= temp;

			if (isLeap == false)

				monCyl++;

		}

		if (offset == 0 && leap > 0 && i == leap + 1)

			if (isLeap) {

				isLeap = false;

			} else {

				isLeap = true;

				--i;

				--monCyl;

			}

		if (offset < 0) {

			offset += temp;

			--i;

			--monCyl;

		}

		month = i; // ũ���·�

		day = offset + 1; // ũ�����

		// System.out.println(day);

	}

	private static int getYear() {

		return (year);

	}

	private static int getMonth() {

		return (month);

	}

	private static int getDay() {

		return (day);

	}

	private static int getMonCyl() {

		return (monCyl);

	}

	private static int getYearCyl() {

		return (yearCyl);

	}

	private static int getDayCyl() {

		return (dayCyl);

	}

	private static boolean getIsLeap() {

		return (isLeap);

	}

	/**
	 * //============================== ���� offset ���ظ�֧, 0=����
	 * 
	 * @param num
	 * @return
	 */
	private static String cyclical(int num) {

		return (Gan[num % 10] + Zhi[num % 12]);

	}

	/**
	 * //====================== ��������
	 * 
	 * @param d
	 * @return
	 */

	private static String cDay(int d) {

		String s;

		switch (d) {

		case 10:

			s = "��ʮ";

			break;

		case 20:

			s = "��ʮ";

			break;

		case 30:

			s = "��ʮ";

			break;

		default:

			s = nStr2[(int) (d / 10)];// ȡ��

			s += nStr1[d % 10];// ȡ��

		}

		return (s);

	}

	/**
	 * �õ�ũ����
	 * 
	 * @param y
	 * @return
	 */
	private static String cYear(int y) {
		String s = " ";
		int d;
		while (y > 0) {
			d = y % 10;
			y = (y - d) / 10;
			s = yearName[d] + s;
		}
		return (s);

	}

	/**
	 * ����������������("1991","02","3")�õ����������ݰ�����Ф h.put("lunar_animal", lunar_animal);
	 * h.put("lunar_year", lunar_year); h.put("lunar_month", lunar_month);
	 * h.put("lunar_month_bigor_small", lunar_month_bigor_small);//��С��
	 * h.put("lunar_day", lunar_day); h.put("lunar_traditionnary",
	 * lunar_traditionnary);//�������� ���������δ��������
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static HashMap<String, String> getLunar(String year, String month,
			String day) {

		Date sDObj;
		String s;
		int SY, SM, SD;
		int sy; // �����ж���Ч������
		SY = Integer.parseInt(year);
		SM = Integer.parseInt(month);
		SD = Integer.parseInt(day);

		Calendar cl = Calendar.getInstance();
		cl.set(SY, SM - 1, SD);
		sDObj = cl.getTime();

		// ����
		Lunar1(sDObj); // ũ��
		// ��Ч�ǰ�ũ����������
		sy = (getYear() - 4) % 12;
		String lunar_animal = Animals[sy];// ��Ф
		String lunar_year = cYear(getYear());// ���
		String lunar_month = (getIsLeap() ? "��" : "") + monthNong[getMonth()]
				+ "��"; // �·�
		String lunar_month_bigor_small = (monthDays(getYear(), getMonth()) == 29 ? "С"
				: "��");// ��С��
		String lunar_day = cDay(getDay());// ��
		String lunar_traditionnary = cyclical(getYearCyl()) + "��"
				+ cyclical(getMonCyl()) + "��" + cyclical(getDayCyl()) + "��";// ��������
		HashMap<String, String> h = new HashMap<String, String>();
		h.put("lunar_animal", lunar_animal);
		h.put("lunar_year", lunar_year);
		h.put("lunar_month", lunar_month);
		h.put("lunar_month_bigor_small", lunar_month_bigor_small);
		h.put("lunar_day", lunar_day);
		h.put("lunar_traditionnary", lunar_traditionnary);// �������� ���������δ��������
		return h;
	}

	public static void main(String[] args) {
		System.out.println(getLunar("2015", "06", "18"));

	}

}
