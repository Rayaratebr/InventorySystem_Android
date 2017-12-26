package com.example.dell.inventory_system_android.ProductActivities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.MainActivity;
import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.Models.Product;
import com.example.dell.inventory_system_android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewProductActivity extends AppCompatActivity {

    Product product;
    Button addButton, backButton, clearButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        addButton = (Button) findViewById(R.id.addProduct);
        backButton = (Button) findViewById(R.id.backProduct);
        clearButton = (Button) findViewById(R.id.clearProduct);
        cancelButton = (Button) findViewById(R.id.cancelProduct);

        final EditText productName = (EditText) findViewById(R.id.productName);
        final EditText productPrice = (EditText) findViewById(R.id.productPrice);
        final EditText productQuantity = (EditText) findViewById(R.id.productQuantity);

        final TextView productId = (TextView) findViewById(R.id.productID);

        //  productId.setText(Integer.toString(MainActivity.currentOrderID));

        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = productName.getText().toString();
                double price = Double.parseDouble(productPrice.getText().toString());
                final int quantity = Integer.parseInt(productQuantity.getText().toString());
                product = new Product(price, quantity, name);
                //TODO IF EVERYTHING IS FINE ADD THE CUSTOMER AND INCREMENT THE ID
                /*storing the new customer to database using port request*/
                Call<String> repos = Config.apiService.storeProduct(product);
                repos.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(NewProductActivity.this, response.body(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(NewProductActivity.this, "error", Toast.LENGTH_LONG).show();
                    }

                });
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent myIntent = new Intent(NewProductActivity.this,
                        MainActivity.class);
                NewProductActivity.this.startActivity(myIntent);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productName.setText("");
                productPrice.setText("");
                productQuantity.setText("");
                productId.setText(Integer.toString(MainActivity.currentProductID));
            }
        });
    }

}

