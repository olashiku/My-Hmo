package com.qucoon.myhmo.utils;

import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.qucoon.myhmo.R;

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

    public static void showErrorToast(View view, Context context, String message){
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
        snackbar.show();
    }

}
