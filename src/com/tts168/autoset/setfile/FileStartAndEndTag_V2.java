package com.tts168.autoset.setfile;
/**
 * �ڶ���
 * ��¼�ļ�ָ��λ�ñ�ǵ���ʼλ�� start, end
 * @author Yuanjian
 * @version
 * @see
 * start-end ֵ��0-8	���ݿ�汾 ��8-10	���ݰ���С��10-16���á�1��
16-48	WiFi�û�����48-112	WiFi����

112-120	������ѡ��8
120-122	���㱨ʱ����2
122-124	��㱨ʱ����2
124-128	�����㱨ʱ����ʼʱ��4
128-132	�����㱨ʱ������ʱ��4

132-152	��������������20
152-172	������˫������20
172-192	��������������20

192-224	Ц������������32
224-256	Ц����˫������32
256-288	Ц������������32

288-300	WiFi�Զ���ȡʱ����12
300-302	���պ�ף������2
302-304	��ʮ�Ľ�������2
304-306	������ǰ����2
//��48�ֽ�
306-310	��1�β���ʱ��4
310-314	��2�β���ʱ��4
314-318	��3�β���ʱ��4

346-352	���á�2��
//����
352-354	ÿ���������Ч����2
354-360	���á�3��
//ѭ��10��(366+280)
360-362	��+28��n��	�Ƿ���2
362-366	��+28��n��ʱ��4
366-367	��+28��n������1
367-368	��+28��n��5���Ӻ������ѱ��1
368-388	��+28��n������20


640-642	���յ����ѣ���Ч����2
642-644	���յ����ѣ���ǰ����2
644-648	���յ����ѣ���1�β���ʱ��4
648-652	���յ����ѣ���2�β���ʱ��4
652-656	���յ����ѣ���3�β���ʱ��4
656-664	���á�4��

//�ظ�30�Σ�ÿ�μ�112���ֽ�[4020]
664-666	��+112*n���Ƿ���2
666-668	��+112*n������������2
668-676	��+112*n������8
676-696	��+112*n���¼� 20
696-776	��+112*n��ף����80

4024-4094	���á�5��
4094-4096	Crc16 У����
;
 *               */
public class FileStartAndEndTag_V2 {
	/**
	 * start-end ֵ��0-8	���ݿ�汾 ��8-10	���ݰ���С��10-16����
	 */
	
	public static final int VERSION_START=0,VERSION_END=8;
	/**
	 * start-end ֵ��8-10	���ݰ���С��
	 */
	public static final int DATASIZE_START=8,DATASIZE_END=10;
	/**
	 * start-end ֵ��10-16����
	 */
	public static final int BACKUP1_START=10,BACKUP1_END=16;
	/**
	 * 16-48	WiFi�û�����
	 */
	public static final int SSID_START=16,SSID_END=48;
	/**
	 * 48-112	WiFi����
	 */
	public static final int PASSWORD_START=48,PASSWORD_END=112;
	/**
	 * 112-120	������ѡ��8
	 */
	public static final int ENUNCIATOR_START=112,ENUNCIATOR_END=120;
	/**
	 * 
		120-122	���㱨ʱ����2
		122-124	��㱨ʱ����2
		124-128	�����㱨ʱ����ʼʱ��4
		128-132	�����㱨ʱ������ʱ��4
	 */
	public static final int FULL_START=120,FULL_END=122;
	/**
	 * 
		122-124	��㱨ʱ����2
	 */
	public static final int HALF_START=122,HALF_END=124;
	/**
	 * 124-128	�����㱨ʱ����ʼʱ��4
	 */
	public static final int FULL_STARTTIME_START=124,FULL_STARTTIME__END=128;
	/**
	 * 128-132	�����㱨ʱ������ʱ��4
	 */
	public static final int HALF_STARTTIME_START=128,HALF_STARTTIME_END=132;
	/**
	 * 132-152	��������������20
		152-172	������˫������20
		172-192	��������������20
	 */
	public static final int SHORET_WEARTH_START=132,SHORET_WEARTH_END=152;
	public static final int DOUBLE_WEARTH_START=152,DOUBLE_WEARTH_END=172;
	public static final int LONG_WEARTH_START=172,LONG_WEARTH_END=192;
	
	/**
	 * 	192-224	Ц������������32
		224-256	Ц����˫������32
		256-288	Ц������������32
	 */
	public static final int SHORET_LAUGH_START=192,SHORET_LAUGH_END=224;
	public static final int DOUBLE_LAUGH_START=224,DOUBLE_LAUGH_END=256;
	public static final int LONG_LAUGH_START=256,LONG_LAUGH_END=288;
	
	/**
	 * 288-300	WiFi�Զ���ȡʱ����12
	 */
	public static final int AUTOWIFI_START=288,AUTOWIFI_END=300;
	/**
	 * 300-302	���պ�ף������2
	 */
	public static final int FESTIVAL_AND_BLESS_SWITCH_START=300,FESTIVAL_AND_BLESS_SWITCH_END=302;
	/**
	 * 302-304	��ʮ�Ľ�������2
	 */
	public static final int JIEQI_SWITCH_START=302,JIEQI_SWITCH_END=304;
	/**
	 * 304-306	������ǰ����2
	 */
	public static final int PLAY_AHEAD_DAYS_START=304,PLAY_AHEAD_DAYS_END=306;
	
	/**
	 * //��48�ֽ�
		306-310	��1�β���ʱ��4
		310-314	��2�β���ʱ��4
		314-318	��3�β���ʱ��4
		346-352	����(2)6
	 */
	public static final int PLAY_FIRST_TIME_START=306,PLAY_FIRST_TIME_END=310;
	public static final int PLAY_SECOND_TIME_START=310,PLAY_SECOND_TIME_END=314;
	public static final int PLAY_THIRD_TIME_START=314,PLAY_THIRD_TIME_END=318;
	public static final int BACKUP2_START=346,BACKUP2_END=352;
	
	/**
	 * //���� 352-354	ÿ���������Ч����2
	 */
	
	public static final int DAILYALART_EFFECT_TOTAL_START=352,DAILYALART_EFFECT_TOTAL_END=354;
	/**
	 * 354-360	����(3)
	 */
	public static final int BACKUP3_START=354,BACKUP3_END=360;
	
	/**
	 * //ѭ��10��(366+280)
		360-362	��+28��DAILY_ALART_NUM��	�Ƿ���2
		362-366	��+28��DAILY_ALART_NUM��ʱ��4
		366-367	��+28��DAILY_ALART_NUM������1
		367-368	��+28��DAILY_ALART_NUM��5���Ӻ������ѱ��1
		368-388	��+28��DAILY_ALART_NUM������20
	 */
	/**
	 * ��¼�ǵڼ�����������Ч���������ж�  DAILYALART_EFFECT_TOTAL��ȡ+1������ʮ�򸲸ǵ�һ����
	 */
	public static int DAILY_ALART_NUM=0;
	/**
	 *360-362	��+28��DAILY_ALART_NUM��	�Ƿ���2�ֽ�
	 */
	public static int IS_DAILY_ALART_OPEN_START,IS_DAILY_ALART_OPEN_END;
	/**
	 *362-366	��+28��DAILY_ALART_NUM��ʱ��4�ֽ�
	 */
	public static int DAILY_ALART_TIME_START,DAILY_ALART_TIME_END;
	/**
	 * 366-367	��+28��DAILY_ALART_NUM������1�ֽ�
	 */
	public static int DAILY_ALART_CYCLE_START,DAILY_ALART_CYCLE_END;
	/**
	 * 5���Ӻ������ѱ��1�ֽ�
	 */
	public static int DAILY_ALART_REMIND_START,DAILY_ALART_REMIND_END;
	/**
	 * 368-388	��+28��DAILY_ALART_NUM������20�ֽ�
	 */
	public static int DAILY_ALART_TITLE_START,DAILY_ALART_TITLE_END;
	
	/**
	 * 640-642	���յ����ѣ���Ч����2
		642-644	���յ����ѣ���ǰ����2
		644-648	���յ����ѣ���1�β���ʱ��4
		648-652	���յ����ѣ���2�β���ʱ��4
		652-656	���յ����ѣ���3�β���ʱ��4
		656-664	����(4)8
	 */
	/**
	 * ���� 640-642	���յ����ѵ���Ч����2�ֽ�
	 */
	public static final int BIRTHDAY_EFFECT_TOTAL_START=640,BIRTHDAY_EFFECT_TOTAL_END=642;
	/**
	 * 642-644	���յ����ѣ���ǰ����2�ֽ�
	 */
	public static final int BIRTHDAY_AHEAD_DAYS_START=642,BIRTHDAY_AHEAD_DAYS_END=644;
	/**
	 * 644-648	���յ����ѣ���1�β���ʱ��4�ֽ�
	 */
	public static final int BIRTHDAY_FIRST_TIME_START=644,BIRTHDAY_FIRST_TIME_END=648;
	/**
	 * 648-652	���յ����ѣ���2�β���ʱ��4�ֽ�
	 */
	public static final int BIRTHDAY_SECOND_TIME_START=648,BIRTHDAY_SECOND_TIME_END=652;
	/**
	 * 652-656	���յ����ѣ���3�β���ʱ��4�ֽ�
	 */
	public static final int BIRTHDAY_THIRD_TIME_START=652,BIRTHDAY_THIRD_TIME_END=656;
	/**
	 * 656-664	����(4)8�ֽ�
	 */
	public static final int BACKUP4_START=656,BACKUP4_END=664;
	
	/**
	 * //�ظ�30�Σ�ÿ�μ�112���ֽ�[4020]
664-666	��+112*n���Ƿ���2
666-668	��+112*n������������2
668-676	��+112*n������8
676-696	��+112*n���¼� 20
696-776	��+112*n��ף����80

	 */
	/**
	 * ��¼�ǵڼ�����������Ч���������ж�  BIRTHDAY_EFFECT_TOTAL��ȡ+1������30�򸲸ǵ�һ����
	 */
	public static int BIRTHDAY_ALART_NUM=0;
	/**
	 *664-666	��+112*n���Ƿ���2�ֽ�
	 */
	public static int IS_BIRTHDAY_ALART_OPEN_START,IS_BIRTHDAY_ALART_OPEN_END;
	/**
	 *666-668	��+112*n������������2�ֽ�
	 */
	public static int BIRTHDAY_ALART_TIME_TYPE_START,BIRTHDAY_ALART_TIME_TYPE_END;
	/**
	 * 668-676	��+112*n������8�ֽ�
	 */
	public static int BIRTHDAY_ALART_DFATE_START,BIRTHDAY_ALART_DFATE_END;
	
	/**
	 * 676-696	��+112*n���¼� 20�ֽ�
	 */
	public static int BIRTHDAY_ALART_TITLE_START,BIRTHDAY_ALART_TITLE_END;
	/**
	 * 696-776	��+112*n��ף����80
	 */
	public static int BIRTHDAY_ALART_CONTENT_START,BIRTHDAY_ALART_CONTENT_END;
	
	/**
	 * 4024-4094	����
	 */
	public static final int BACKUP5_START=4024,BACKUP5_END=4094;
	/**
	 * 4094-4096	Crc16 У����
	 */
	public static final int Crc16_START=4094,Crc16_END=4096;
}
