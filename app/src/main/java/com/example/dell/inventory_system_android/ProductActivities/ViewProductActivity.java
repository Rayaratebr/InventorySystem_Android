package com.example.dell.inventory_system_android.ProductActivities;

import android.content.Intent;
import android.os.Handler;
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
import com.example.dell.inventory_system_android.Models.Product;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.OrderActivities.SearchOrderActivity;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProductActivity extends ViewActivity {

    Product product;
    TextView txtProductDetails;
    Button viewOrders, deleteProduct,backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        viewOrders = (Button) findViewById(R.id.relatedOrders);
        deleteProduct = (Button) findViewById(R.id.buttonDeleteProduct);
        backBtn = (Button) findViewById(R.id.backButton);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtProductDetails = (TextView) findViewById(R.id.textViewProductDtls);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        deleteProduct.setAnimation(shake);

        ObjectViewAsyncTask asyncTask = new ObjectViewAsyncTask(ViewProductActivity.this);
        asyncTask.execute(objectID, Helpers.PRODUCT);

        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO VIEW ORDERS THAT HAVE THIS PRODUCT
            }
        });



        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(shake);
                Call<String> repos = Config.apiService.deleteProduct(objectID);
                repos.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(ViewProductActivity.this, response.body(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
//                        Toast.makeText(ViewProductActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent myIntent = new Intent(ViewProductActivity.this,
                                MainActivity.class);
                        ViewProductActivity.this.startActivity(myIntent);
                        finish();
                    }
                }, 2000);

            }
        });
    }

    @Override
    public void setObject(Parent object) {
        this.product = (Product)object;
        txtProductDetails.setText(product.displayProduct());//TODO: make it prettier
    }
}

