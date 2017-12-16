package com.example.dell.inventory_system_android.CustomerActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.ListingActivity;
import com.example.dell.inventory_system_android.MainActivity;
import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.OrderActivities.NewOrderActivity;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;

public class ViewCustomerActivity extends ViewActivity{

    int currentCustomerID = -1;
    Customer customer;
    TextView customerDetails;

    Button addOrderBtn,addPaymentBtn,viewOrdersBtn,viewPaymentsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        addOrderBtn = (Button) findViewById(R.id.addOrderBtn);
        addPaymentBtn = (Button) findViewById(R.id.addPaymentBtn);
        viewOrdersBtn = (Button) findViewById(R.id.viewOrdersBtn);
        viewPaymentsBtn = (Button) findViewById(R.id.viewPaymentsBtn);

        params = getIntent().getExtras();
        currentCustomerID = Helpers.getIDForActivity(params);
        customerDetails = (TextView) findViewById(R.id.textViewCustDtls);
        ObjectViewAsyncTask asyncTask = new ObjectViewAsyncTask(ViewCustomerActivity.this);
        asyncTask.execute(currentCustomerID,Helpers.CUSTOMER);

        addOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewCustomerActivity.this,
                        NewOrderActivity.class);
                Bundle b = new Bundle();
                b.putInt("ID",currentCustomerID); //Your id
                myIntent.putExtras(b); //Put your id to your next Intent
                ViewCustomerActivity.this.startActivity(myIntent);
            }
        });
        viewOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewCustomerActivity.this,
                        ListingActivity.class);
                Bundle b = new Bundle();
                b.putInt("ID",currentCustomerID); //Your id
                myIntent.putExtras(b); //Put your id to your next Intent
                ViewCustomerActivity.this.startActivity(myIntent);
            }
        });

    }


    public void setObject(Parent object){

        this.customer = (Customer)object;
        customerDetails.setText(customer.toString());//TODO: make it prettier
    }





}
