package com.qucoon.myhmo.popups.utilitypupups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.qucoon.myhmo.R;
import com.qucoon.myhmo.utils.CommonUtilz;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

public class DatePickerBottomSheetFragment extends BottomSheetDialogFragment {

  //  @BindView(R.id.tvTitletext)
    TextView tvTitletext;
  //  @BindView(R.id.btnComplete)
    DatePicker datePicker;

   // @BindView(R.id.btnComplete)
    Button btnComplete;

    OnDateSelected onDateSelected;
    Date date;
    String dat,title,buttonTitle;

    public enum  DateType {
        START_DATE,END_DATE
    }

  //  private Lazy<DataPasserLiveData> datapasser = inject(DataPasserLiveData.class);


    public static DatePickerBottomSheetFragment newInstance(String title, String buttonTitle){
        DatePickerBottomSheetFragment f = new DatePickerBottomSheetFragment();
        Bundle b = new Bundle();
        b.putString("title",title);
        b.putString("buttontitle",buttonTitle);
        f.setArguments(b);
        return f;
    }

    public interface OnDateSelected{
        void onDatePicked(String date);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_date_picker_bottom_sheet, container, false);
     //   ButterKnife.bind(this,view);

        btnComplete = view.findViewById(R.id.btnComplete);
        tvTitletext = view.findViewById(R.id.tvTitletext);
        datePicker = view.findViewById(R.id.datePicker);

        try{
            onDateSelected = (OnDateSelected) getParentFragment();
        }catch (Exception e){
            System.out.println(e);
        }
        if(getArguments() != null){

            title = getArguments().getString("title");
            buttonTitle = getArguments().getString("buttontitle");


            tvTitletext.setText(title);
            btnComplete.setText(buttonTitle);
        }

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                date = CommonUtilz.getDateFromDatePickers(datePicker);
                System.out.println("thisismydate" +new SimpleDateFormat("mm/dd/yy").format(date));
             //   formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                dat= String.format("%d", datePicker.getYear()) + "/" + String.format("%d", datePicker.getMonth()+1) + "/" + String.format("%d", datePicker.getDayOfMonth());



                if(title.equals("Select Start Date")){
                     dismiss();

//                    datapasser.getValue().getExpectedStartDate().postValue(new SimpleDateFormat("yyyy-MM-dd").format(date));
//                    datapasser.getValue().getExpectedStartDate1().postValue(new SimpleDateFormat("dd-MMM-yyyy").format(date));


                } else if(title.equals("Select end Date")){
                    dismiss();
//                    datapasser.getValue().getExpectedEndDate().postValue(new SimpleDateFormat("yyyy-MM-dd").format(date));
//                    datapasser.getValue().getExpectedEndDate1().postValue(new SimpleDateFormat("dd-MMM-yyyy").format(date));

                } else {

                    dismiss();
                    onDateSelected.onDatePicked(new SimpleDateFormat("yyyy-MM-dd").format(date));
//                    datapasser.getValue().getExpectedEndDate().postValue(new SimpleDateFormat("yyyy-MM-dd").format(date));
//                    datapasser.getValue().getExpectedEndDate1().postValue(new SimpleDateFormat("dd-MMM-yyyy").format(date));


                }



            }
        });


        return  view;
    }

}