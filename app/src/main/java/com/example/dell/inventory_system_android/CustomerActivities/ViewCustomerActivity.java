package com.example.dell.inventory_system_android.CustomerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.ListingActivity;
import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.OrderActivities.NewOrderActivity;
import com.example.dell.inventory_system_android.PaymentActivities.NewPaymentActivity;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCustomerActivity extends ViewActivity{

//    int currentCustomerID = -1;
    Customer customer;
    TextView customerDetailsLbl;

    Button addOrderBtn,addPaymentBtn,viewOrdersBtn,viewPaymentsBtn,deleteBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        addOrderBtn = (Button) findViewById(R.id.btnAddOrder);
        addPaymentBtn = (Button) findViewById(R.id.addPaymentBtn);
        viewOrdersBtn = (Button) findViewById(R.id.viewOrdersBtn);
        viewPaymentsBtn = (Button) findViewById(R.id.viewPaymentsBtn);
        customerDetailsLbl = (TextView) findViewById(R.id.textViewCustDtls);
        deleteBtn = (Button) findViewById(R.id.buttonDelete);
        backBtn = (Button) findViewById(R.id.backButton);


//        params = getIntent().getExtras();
//        currentCustomerID = Helpers.getIDForActivity(params);

        ObjectViewAsyncTask asyncTask = new ObjectViewAsyncTask(ViewCustomerActivity.this);
        asyncTask.execute(objectID,Helpers.CUSTOMER);

        addOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewCustomerActivity.this,
                        NewOrderActivity.class);
                NewOrderActivity.currentCustomerID = objectID;

                ViewCustomerActivity.this.startActivity(myIntent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewCustomerActivity.this,
                        ListingActivity.class);
//                Bundle b = new Bundle();
//                b.putInt("ID",objectID); //Your id
//                myIntent.putExtras(b); //Put your id to your next Intent
                ListingActivity.showType = Helpers.CUSTOMER_ORDERS;
                ListingActivity.ID = objectID;
                ViewCustomerActivity.this.startActivity(myIntent);
            }
        });

        viewPaymentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewCustomerActivity.this,
                        ListingActivity.class);
//                Bundle b = new Bundle();
//                b.putInt("ID",objectID); //Your id
//                myIntent.putExtras(b); //Put your id to your next Intent
                ListingActivity.showType = Helpers.CUSTOMER_PAYMENTS;
                ListingActivity.ID = objectID;
                ViewCustomerActivity.this.startActivity(myIntent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Customer> repos = Config.apiService.deleteCustomer(objectID);
                repos.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        Toast.makeText(ViewCustomerActivity.this, "Success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Toast.makeText(ViewCustomerActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                });
            }

        });

        addPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewCustomerActivity.this,
                        NewPaymentActivity.class);
                myIntent.putExtra("customerName",customer.getName());
                NewPaymentActivity.currentCustomerID = objectID;
                ViewCustomerActivity.this.startActivity(myIntent);
            }
        });


    }


    public void setObject(Parent object){

        this.customer = (Customer)object;
        customerDetailsLbl.setText(customer.displayCustomer());//TODO: make it prettier
        //customerDetailsLbl.setFontFeatureSettings();
    }





}
