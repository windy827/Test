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

        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
        String[] months_little = { "4", "6", "9", "11" };

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        dialog = new Dialog(context);
        dialog.setTitle("请选择日期与时间");
        // 找到dialog的布局文件
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.time_layout, null);

        // 年
        ArrayList<String> data = new ArrayList<String>();
        for(int i=1900;i<2100;i++){
        	 data.add(i+"");
        }
//        data.add("江西");
//        data.add("江苏");
//        data.add("南京");
        
        final StrArrayWheelView wv_year = (StrArrayWheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new StrArrayWheelAdapter(data));// 设置"年"的显示数据
        wv_year.setCyclic(true);// 可循环滚动
        wv_year.setLabel("年");// 添加文字
        wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

        // 月
        final StrArrayWheelView wv_month = (StrArrayWheelView) view.findViewById(R.id.month);
        wv_month.setAdapter(new DateNumericWheelAdapter(1, 12));
        wv_month.setCyclic(true);
        wv_month.setLabel("月");
        wv_month.setCurrentItem(month);

        // 日
        final StrArrayWheelView wv_day = (StrArrayWheelView) view.findViewById(R.id.day);
        wv_day.setCyclic(true);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new DateNumericWheelAdapter(1, 31));
        } else if (list_little.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new DateNumericWheelAdapter(1, 30));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                wv_day.setAdapter(new DateNumericWheelAdapter(1, 29));
            else
                wv_day.setAdapter(new DateNumericWheelAdapter(1, 28));
        }
        wv_day.setLabel("日");
        wv_day.setCurrentItem(day - 1);

        // 时
        final StrArrayWheelView wv_hours = (StrArrayWheelView) view.findViewById(R.id.hour);
        wv_hours.setAdapter(new DateNumericWheelAdapter(0, 23));
        wv_hours.setCyclic(true);
        wv_hours.setCurrentItem(hour);

        // 分
        final StrArrayWheelView wv_mins = (StrArrayWheelView) view.findViewById(R.id.mins);
        wv_mins.setAdapter(new DateNumericWheelAdapter(0, 59, "%02d"));
        wv_mins.setCyclic(true);
        wv_mins.setCurrentItem(minute);
        
        // 添加"年"监听
        StrArrayWheelView.OnWheelChangedListener wheelListener_year = new StrArrayWheelView.OnWheelChangedListener() {
            public void onChanged(StrArrayWheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + START_YEAR;
                // 判断大小月及是否闰年,用来确定"日"的数据
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
        // 添加"月"监听
        StrArrayWheelView.OnWheelChangedListener wheelListener_month = new StrArrayWheelView.OnWheelChangedListener() {
            public void onChanged(StrArrayWheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                // 判断大小月及是否闰年,用来确定"日"的数据
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

        // 根据屏幕密度来指定选择器字体的大小
        int textSize = 0;

        textSize = 12;

        wv_day.TEXT_SIZE = textSize;
        wv_hours.TEXT_SIZE = textSize;
        wv_mins.TEXT_SIZE = textSize;
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;

     
        // 设置dialog的布局,并显示
        dialog.setContentView(view);
        return dialog;
        
    }
}
