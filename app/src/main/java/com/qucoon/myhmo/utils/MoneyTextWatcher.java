package com.qucoon.myhmo.utils;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import static com.example.neptune.utils.ExtentionsKt.formatDouble;


public class MoneyTextWatcher implements TextWatcher {
    private final WeakReference<EditText> editTextWeakReference;

    public MoneyTextWatcher(EditText editText) {
        editTextWeakReference = new WeakReference<EditText>(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        EditText editText = editTextWeakReference.get();
        if (editText == null) return;
        String s = editable.toString();
        if (s.isEmpty()) return;
        editText.removeTextChangedListener(this);
        String cleanString = s.replaceAll("[$,.£N₦]", "");
        if(!cleanString.isEmpty()){
            if(formatDouble(cleanString).getFirst()){
                double parsed = (Double)formatDouble(cleanString).getSecond();
                // String formatted = NumberFormat.getCurrencyInstance() .format((parsed/100));
                NumberFormat df = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                dfs.setCurrencySymbol("₦");
                dfs.setGroupingSeparator(',');
                dfs.setMonetaryDecimalSeparator('.');
                ((DecimalFormat) df).setDecimalFormatSymbols(dfs);
                String formatted = df.format((parsed/100));
                editText.setText(formatted);
                editText.setSelection(formatted.length());
            }else{
                editText.setText( (String) formatDouble(cleanString).getSecond());
                editText.setSelection(  ((String) formatDouble(cleanString).getSecond()).length());
            }
            editText.addTextChangedListener(this);


        }
//        try{
//            BigDecimal parsed = new BigDecimal(cleanString.trim()).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
//            NumberFormat df = NumberFormat.getCurrencyInstance();
//            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
//            dfs.setCurrencySymbol("₦");
//            dfs.setGroupingSeparator(',');
//            dfs.setMonetaryDecimalSeparator('.');
//            ((DecimalFormat) df).setDecimalFormatSymbols(dfs);
//            String formatted = df.format(parsed);
//            editText.setText(formatted);
//            editText.setSelection(formatted.length());
//            editText.addTextChangedListener(this);
//        }catch (Exception ex){
//            NumberFormat formatter = new DecimalFormat("#,###.00");
//            ((DecimalFormat) formatter).getDecimalFormatSymbols().setCurrencySymbol("₦");
//            String formatted=formatter.format(new BigDecimal(cleanString));
//            editText.setText(formatted);
//            editText.setSelection(formatted.length());
//            editText.addTextChangedListener(this);
//        }


    }
}