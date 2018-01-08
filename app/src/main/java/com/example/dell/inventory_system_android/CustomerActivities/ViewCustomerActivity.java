package com.example.dell.inventory_system_android.CustomerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.ListingActivity;
import com.example.dell.inventory_system_android.MainActivity;
import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.OrderActivities.NewOrderActivity;
import com.example.dell.inventory_system_android.OrderActivities.ViewOrderActivity;
import com.example.dell.inventory_system_android.PaymentActivities.NewPaymentActivity;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;
import com.example.dell.inventory_system_android.connectionAsyncTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dell.inventory_system_android.Helpers.showActivity;

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

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        deleteBtn.setAnimation(shake);


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
                view.startAnimation(shake);
                Call<String> repos = Config.apiService.deleteCustomer(objectID);
                repos.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(ViewCustomerActivity.this, response.body(), Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(ViewCustomerActivity.this,
                                MainActivity.class);
                        ViewCustomerActivity.this.startActivity(myIntent);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(ViewCustomerActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                });
                Intent myIntent = new Intent(ViewCustomerActivity.this,
                        MainActivity.class);
                myIntent.putExtra("fromAnotherActivity",Helpers.ORDER);
                ViewCustomerActivity.this.startActivity(myIntent);
                finish();
            }

        });

        addPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewCustomerActivity.this,
                        NewPaymentActivity.class);
                myIntent.putExtra("customer",customer);
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
