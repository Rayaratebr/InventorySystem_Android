package com.example.dell.inventory_system_android.ProductActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.Models.Product;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProductActivity extends ViewActivity {

    Product product;
    TextView txtProductDetails;
    Button viewCustomer, deleteProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        viewCustomer = (Button) findViewById(R.id.viewCustomerProduct);
        deleteProduct = (Button) findViewById(R.id.buttonDeleteProduct);

        txtProductDetails = (TextView) findViewById(R.id.textViewProductDtls);


        ObjectViewAsyncTask asyncTask = new ObjectViewAsyncTask(ViewProductActivity.this);
        asyncTask.execute(objectID, Helpers.PRODUCT);

        viewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO CALL VIEW CUSTOMER ACTIVITY
            }
        });



        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Product> repos = Config.apiService.deleteProduct(objectID);
                repos.enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Toast.makeText(ViewProductActivity.this, "Success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(ViewProductActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }

    @Override
    public void setObject(Parent object) {
        this.product = (Product)object;
        txtProductDetails.setText(product.toString());//TODO: make it prettier
    }
}

