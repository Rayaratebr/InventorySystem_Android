package com.example.dell.inventory_system_android.PaymentActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.MainActivity;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.Models.Payment;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.OrderActivities.ViewOrderActivity;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewPaymentActivity extends ViewActivity {
    Payment payment;
    TextView txtPaymentDetails;
    Button viewCustomer, viewOrder, deletePayment, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);

        txtPaymentDetails = (TextView) findViewById(R.id.textViewPaymentDtls);
        viewCustomer = (Button) findViewById(R.id.viewCustomer);
        viewOrder = (Button) findViewById(R.id.viewOrder);
        deletePayment = (Button) findViewById(R.id.buttonDeleterPayment);
        backBtn = (Button) findViewById(R.id.backButton);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        deletePayment.setAnimation(shake);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        deletePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(shake);
                Call<String> repos = Config.apiService.deletePayment(objectID);
                repos.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(ViewPaymentActivity.this, response.body(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(ViewPaymentActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                });
                Intent myIntent = new Intent(ViewPaymentActivity.this,
                        MainActivity.class);
                myIntent.putExtra("fromAnotherActivity",Helpers.ORDER);
                ViewPaymentActivity.this.startActivity(myIntent);
                finish();
            }
        });
    }

    @Override
    public void setObject(Parent object) {
        this.payment = (Payment)object;
       //txtPaymentDetails.setText(payment.toString());//TODO: make it prettier
    }
}
