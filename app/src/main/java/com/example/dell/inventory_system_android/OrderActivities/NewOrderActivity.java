package com.example.dell.inventory_system_android.OrderActivities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.MainActivity;
import com.example.dell.inventory_system_android.Models.Order;
import com.example.dell.inventory_system_android.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOrderActivity extends AppCompatActivity {
    EditText orderDateTxt, orderDueDateTxt, customerIDTxt;
    Button addOrderBtn, closeButton, backButton, clearButton;
    Calendar myCalendar;
    Order objOrder;
    int currentCustomerID;
    Bundle params;
    DatePickerDialog.OnDateSetListener orderDateListener, orderDueDateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);


        myCalendar = Calendar.getInstance();
        orderDateTxt = (EditText) findViewById(R.id.orderDateTxt);
        orderDueDateTxt = (EditText) findViewById(R.id.orderDueDateTxt);
        final TextView orderId = (TextView) findViewById(R.id.orderID);
        customerIDTxt = (EditText) findViewById(R.id.customerIDTxt);


        objOrder = new Order();
        params = getIntent().getExtras();
        currentCustomerID = Helpers.getIDForActivity(params);
        objOrder.setCustomer_id(currentCustomerID);

        //If this activity is opened from customer view
        if (currentCustomerID > 0) {
            customerIDTxt.setText(currentCustomerID + "");
            customerIDTxt.setEnabled(false);
        }
        //TODO:add products

        addOrderBtn = (Button) findViewById(R.id.addOrderBtn);
        closeButton = (Button) findViewById(R.id.btnCclOrd);
        backButton = (Button) findViewById(R.id.btnBckOrd);
        clearButton = (Button) findViewById(R.id.buttonClearOrder);

        orderId.setText(Integer.toString(MainActivity.currentOrderID));

        orderDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(orderDateTxt);
                objOrder.setOrderDate(getDateFromDatePicket(view));

            }
        };
        orderDueDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(orderDueDateTxt);
                objOrder.setOrderDueDate(getDateFromDatePicket(view));

            }
        };
        orderDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dpd = new DatePickerDialog(NewOrderActivity.this, orderDateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();
            }
        });


        orderDueDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dpd = new DatePickerDialog(NewOrderActivity.this, orderDueDateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();

            }
        });

        addOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: check empty dates
                //TODO SEND CUSTOMER ID AND ORDER DETAILS TO BE STORED
                /*storing the new order to database using port request*/
                Call<String> repos = Config.apiService.storeCustomerOrder(1, objOrder);
                repos.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        //TODO: we can return also the new added order[to get its ID] to use it in the next view to add products.
                        Toast.makeText(NewOrderActivity.this, response.body(), Toast.LENGTH_LONG).show();
                        //TODO: go to adding order products
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(NewOrderActivity.this, "error", Toast.LENGTH_LONG).show();
                    }
                });
                (MainActivity.currentOrderID)++;
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent myIntent = new Intent(NewOrderActivity.this,
                        MainActivity.class);
                NewOrderActivity.this.startActivity(myIntent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderId.setText(Integer.toString(MainActivity.currentOrderID));
                if (!customerIDTxt.isEnabled()) {
                    customerIDTxt.setText("");
                }
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