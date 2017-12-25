package com.example.dell.inventory_system_android.PaymentActivities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dell.inventory_system_android.MainActivity;
import com.example.dell.inventory_system_android.Models.Order;
import com.example.dell.inventory_system_android.Models.Payment;
import com.example.dell.inventory_system_android.OrderActivities.NewOrderActivity;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ScheduleClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewPaymentActivity extends AppCompatActivity {

    Payment objPayment;
    EditText paymentDueDate,paymentAmount;
    Calendar myCalendar;
    public static int currentCustomerID;
    DatePickerDialog.OnDateSetListener paymentDueDateListener;
    private Button addPayment,cancelPayment, clearPayment, backPayment;
    AlertDialog.Builder dlgAlert;
    ScheduleClient scheduleClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_payment);

        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

        dlgAlert = new AlertDialog.Builder(this);

        myCalendar = Calendar.getInstance();
        paymentDueDate = (EditText) findViewById(R.id.paymentDate);

        paymentAmount = (EditText) findViewById(R.id.amountPayment);

        addPayment = (Button) findViewById(R.id.btnAddPayment);
        cancelPayment = (Button) findViewById(R.id.btnCclPayment);
        backPayment = (Button) findViewById(R.id.btnBackPayment);
        clearPayment = (Button) findViewById(R.id.btnClrPayment);


        objPayment = new Payment();
        objPayment.setCustomer_id(currentCustomerID);

        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String errorMessage = "";
                boolean valid = verifyFields(errorMessage);
                if (valid){
                    Payment payment = new Payment();
                   // payment.setPayment_date(getDateFromDatePicket(view));
                   // payment.setId();
                    Intent myIntent = getIntent();
                    Order objOrder = (Order) myIntent.getSerializableExtra("sampleObject");
                    objOrder.setPayment(payment);

                }
            }
        });

        paymentDueDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(paymentDueDate);
                //objPayment.setPayment_date(getDateFromDatePicket(view));

            }
        };

        paymentDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dpd = new DatePickerDialog(NewPaymentActivity.this, paymentDueDateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();

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
        scheduleClient.setAlarmForNotification(calendar,currentCustomerID);
        SimpleDateFormat tf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return tf.format(calendar.getTime());
    }

    boolean verifyFields(String errorMessage){
        //TODO CHECK IF DATE IS IN THE PAST
        if ((paymentDueDate.getText().toString()).isEmpty()){
            errorMessage += "You must select a valid payment due date!";
                    return false;
        }
        if ((paymentAmount.getText().toString()).isEmpty()){
            errorMessage += "Amount field can't be left blank!";
            return false;
        }

        if (!errorMessage.equals("")) {
            dlgAlert.setMessage(errorMessage);
            dlgAlert.setTitle("Error Message");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        return true;
    }

    @Override
    protected void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
        if(scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();
    }
}
