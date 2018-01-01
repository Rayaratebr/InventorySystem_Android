package com.example.dell.inventory_system_android.OrderActivities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dell.inventory_system_android.CustomerActivities.SearchCustomerActivity;
import com.example.dell.inventory_system_android.DisplayFragment;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.searchAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.dell.inventory_system_android.Helpers.showActivity;

public class SearchOrderActivity extends AppCompatActivity {

    private Button searchBtn;
    private DisplayFragment displayFragment;
    private EditText orderDateFromTxt, orderDateToTxt, orderDueDateFromTxt, orderDueDateToTxt;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener orderDateFromListener, orderDueDateFromListener,orderDateToListener,orderDueDateToListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_order);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        orderDateFromTxt = (EditText) findViewById(R.id.orderDateFromTxt);
        orderDateToTxt = (EditText) findViewById(R.id.orderDateToTxt);
        orderDueDateFromTxt = (EditText) findViewById(R.id.orderDueDateFromTxt);
        orderDueDateToTxt = (EditText) findViewById(R.id.orderDueDateToTxt);
        displayFragment = (DisplayFragment) getFragmentManager().findFragmentById(R.id.displayFragment);
        myCalendar = Calendar.getInstance();

        orderDateFromListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(orderDateFromTxt);
            }
        };
        orderDueDateFromListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(orderDueDateFromTxt);
            }
        };
        orderDateToListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(orderDateToTxt);
            }
        };
        orderDueDateToListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(orderDueDateToTxt);
            }
        };


        //--------------------------------------------------------------------------------------------

        orderDateToTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dpd = new DatePickerDialog(SearchOrderActivity.this, orderDateToListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });
        orderDueDateToTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dpd = new DatePickerDialog(SearchOrderActivity.this, orderDueDateToListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });

        orderDateFromTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dpd = new DatePickerDialog(SearchOrderActivity.this, orderDateFromListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });
        orderDueDateFromTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dpd = new DatePickerDialog(SearchOrderActivity.this, orderDueDateFromListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });





        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int showType = Helpers.ORDER;
                String orderDateFrom = orderDateFromTxt.getText().toString();
                String orderDateTo = orderDateToTxt.getText().toString();
                String orderDueDateFrom = orderDueDateFromTxt.getText().toString();
                String orderDueDateTo = orderDueDateToTxt.getText().toString();
                searchAsyncTask asyncTask = new searchAsyncTask(SearchOrderActivity.this, displayFragment);
                asyncTask.execute(String.valueOf(showType), orderDateFrom, orderDateTo,orderDueDateFrom,orderDueDateTo);
                displayFragment.setShowActivity(showActivity.get(showType));
            }
        });
    }

    private void updateLabel(EditText editText) {
        String myFormat = "dd/MM/YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(myCalendar.getTime()));
    }

    private String getDateFromDatePicket(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat tf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return tf.format(calendar.getTime());
    }
}

