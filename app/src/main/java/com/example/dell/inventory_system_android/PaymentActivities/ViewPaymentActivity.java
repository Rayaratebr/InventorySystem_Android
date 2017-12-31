package com.example.dell.inventory_system_android.PaymentActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.Models.Payment;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;


public class ViewPaymentActivity extends ViewActivity {
    Payment payment;
    TextView txtPaymentDetails;
    Button viewCustomer, viewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);

        txtPaymentDetails = (TextView) findViewById(R.id.textViewPaymentDtls);
        viewCustomer = (Button) findViewById(R.id.viewCustomer);
        viewOrder = (Button) findViewById(R.id.viewOrder);

        ObjectViewAsyncTask asyncTask = new ObjectViewAsyncTask(ViewPaymentActivity.this);
        asyncTask.execute(objectID, Helpers.PAYMENT);

        viewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO CALL VIEW CUSTOMER ACTIVITY
            }
        });

        viewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO CALL VIEW ORDER ACTIVITY
            }
        });
    }

    @Override
    public void setObject(Parent object) {
        this.payment = (Payment)object;
        txtPaymentDetails.setText(payment.toString());//TODO: make it prettier
    }
}
