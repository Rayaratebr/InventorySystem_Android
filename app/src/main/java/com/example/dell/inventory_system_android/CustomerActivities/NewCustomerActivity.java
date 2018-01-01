package com.example.dell.inventory_system_android.CustomerActivities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.MainActivity;
import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCustomerActivity extends AppCompatActivity {

    String name, phone, address;
        int currentCustomerID = 0;
    Customer customer;
    Bundle params;
    AlertDialog.Builder dlgAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
        params = getIntent().getExtras();
        currentCustomerID = Helpers.getIDForActivity(params);
        Log.w("PARAMS:", Helpers.getIDForActivity(params) + "");

        dlgAlert = new AlertDialog.Builder(this);

        Button btnAddCust = (Button) findViewById(R.id.btnAddCust);
        Button btnCancelCust = (Button) findViewById(R.id.btnAddOrder);
        Button btnClear = (Button) findViewById(R.id.btnClr);



        final EditText txtName = (EditText) findViewById(R.id.txtCustName);
        final EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
        final EditText txtAddress = (EditText) findViewById(R.id.txtAddress);
        
        btnAddCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txtName.getText().toString();
                phone = txtPhone.getText().toString();
                address = txtAddress.getText().toString();
                String errorMessage = "";
                boolean valid =verifyFields(name,phone,address,errorMessage);

                if (valid){
                    customer = new Customer(name,address,phone);
                    //TODO IF EVERYTHING IS FINE ADD THE CUSTOMER AND INCREMENT THE ID
                /*storing the new customer to database using port request*/
                    Call<String> repos = Config.apiService.storeCustomer(customer);
                    repos.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(NewCustomerActivity.this, response.body(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(NewCustomerActivity.this, "error", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });


        btnCancelCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hide the activity
                finish();
                //Start the New Customer Activity
                Intent myIntent = new Intent(NewCustomerActivity.this,
                        MainActivity.class);
                NewCustomerActivity.this.startActivity(myIntent);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCustomerID = MainActivity.currentCustID;
                txtName.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
            }
        });


    }
    boolean verifyFields (String name, String phone, String address,String errorMessage){

        if (name.matches(".*\\d+.*")) {
            errorMessage += "Customer name can't contain numbers!\n";

        }

        if (name.isEmpty()) {
            errorMessage += "Name field can't be left blank!\n";
        }

        if (phone.equals("")) {
            errorMessage += "Phone field can't be left blank!\n";
        }
        if (address.isEmpty()) {
            errorMessage += "Address field can't be left blank!";

        }

        if (!errorMessage.equals("")) {
            dlgAlert.setMessage(errorMessage);
            dlgAlert.setTitle("Error Message");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            return false;
        }
        return true;
    }


}
