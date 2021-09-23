package com.qucoon.myhmo.utils;

import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtilz {




    public static Date getDateFromDatePickers(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }


    public static String getDate2(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(" dd-MMM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return sdf.format(calendar.getTime());
    }

}
