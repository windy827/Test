package com.amo.demo.wheelview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.amo.demo.arrWheelview.StrArrayWheelAdapter;
import com.amo.demo.arrWheelview.StrArrayWheelView;
import com.larkiv.larksmart7618.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PowerHallStrArrayDialog
{
    private static int START_YEAR = 1900, END_YEAR = 2100;
    private static Dialog dialog;
    
    public static Dialog getDateDialog(Context context, final TextView textView) {
        
        
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // ��Ӵ�С���·ݲ�����ת��Ϊlist,����֮����ж�
        String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
        String[] months_little = { "4", "6", "9", "11" };

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        dialog = new Dialog(context);
        dialog.setTitle("��ѡ��������ʱ��");
        // �ҵ�dialog�Ĳ����ļ�
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.time_layout, null);

        // ��
        ArrayList<String> data = new ArrayList<String>();
        for(int i=1900;i<2100;i++){
        	 data.add(i+"");
        }
//        data.add("����");
//        data.add("����");
//        data.add("�Ͼ�");
        
        final StrArrayWheelView wv_year = (StrArrayWheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new StrArrayWheelAdapter(data));// ����"��"����ʾ����
        wv_year.setCyclic(true);// ��ѭ������
        wv_year.setLabel("��");// �������
        wv_year.setCurrentItem(year - START_YEAR);// ��ʼ��ʱ��ʾ������

        // ��
        final StrArrayWheelView wv_month = (StrArrayWheelView) view.findViewById(R.id.month);
        wv_month.setAdapter(new DateNumericWheelAdapter(1, 12));
        wv_month.setCyclic(true);
        wv_month.setLabel("��");
        wv_month.setCurrentItem(month);

        // ��
        final StrArrayWheelView wv_day = (StrArrayWheelView) view.findViewById(R.id.day);
        wv_day.setCyclic(true);
        // �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
        if (list_big.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new DateNumericWheelAdapter(1, 31));
        } else if (list_little.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new DateNumericWheelAdapter(1, 30));
        } else {
            // ����
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                wv_day.setAdapter(new DateNumericWheelAdapter(1, 29));
            else
                wv_day.setAdapter(new DateNumericWheelAdapter(1, 28));
        }
        wv_day.setLabel("��");
        wv_day.setCurrentItem(day - 1);

        // ʱ
        final StrArrayWheelView wv_hours = (StrArrayWheelView) view.findViewById(R.id.hour);
        wv_hours.setAdapter(new DateNumericWheelAdapter(0, 23));
        wv_hours.setCyclic(true);
        wv_hours.setCurrentItem(hour);

        // ��
        final StrArrayWheelView wv_mins = (StrArrayWheelView) view.findViewById(R.id.mins);
        wv_mins.setAdapter(new DateNumericWheelAdapter(0, 59, "%02d"));
        wv_mins.setCyclic(true);
        wv_mins.setCurrentItem(minute);
        
        // ���"��"����
        StrArrayWheelView.OnWheelChangedListener wheelListener_year = new StrArrayWheelView.OnWheelChangedListener() {
            public void onChanged(StrArrayWheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + START_YEAR;
                // �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
                if (list_big.contains(String
                        .valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new DateNumericWheelAdapter(1, 31));
                } else if (list_little.contains(String.valueOf(wv_month
                        .getCurrentItem() + 1))) {
                    wv_day.setAdapter(new DateNumericWheelAdapter(1, 30));
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                            || year_num % 400 == 0)
                        wv_day.setAdapter(new DateNumericWheelAdapter(1, 29));
                    else
                        wv_day.setAdapter(new DateNumericWheelAdapter(1, 28));
                }
            }
        };
        // ���"��"����
        StrArrayWheelView.OnWheelChangedListener wheelListener_month = new StrArrayWheelView.OnWheelChangedListener() {
            public void onChanged(StrArrayWheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                // �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
                if (list_big.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new DateNumericWheelAdapter(1, 31));
                } else if (list_little.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new DateNumericWheelAdapter(1, 30));
                } else {
                    if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
                            .getCurrentItem() + START_YEAR) % 100 != 0)
                            || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
                        wv_day.setAdapter(new DateNumericWheelAdapter(1, 29));
                    else
                        wv_day.setAdapter(new DateNumericWheelAdapter(1, 28));
                }
                
            }
        };
        wv_year.addChangingListener(wheelListener_year);
        wv_month.addChangingListener(wheelListener_month);

        // ������Ļ�ܶ���ָ��ѡ��������Ĵ�С
        int textSize = 0;

        textSize = 12;

        wv_day.TEXT_SIZE = textSize;
        wv_hours.TEXT_SIZE = textSize;
        wv_mins.TEXT_SIZE = textSize;
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;

     
        // ����dialog�Ĳ���,����ʾ
        dialog.setContentView(view);
        return dialog;
        
    }
}
