package com.example.dell.inventory_system_android;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewCustomerActivity extends AppCompatActivity {

    String name,phone,address;
    int currentCustomerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        Button btnAddCust = (Button) findViewById(R.id.btnAddCust);
        Button btnCancelCust= (Button) findViewById(R.id.btnCclCust);

        TextView txtCustID= (TextView) findViewById(R.id.textViewCustID);

        final EditText txtName = (EditText) findViewById(R.id.txtCustName);
        final EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
        final EditText txtAddress = (EditText) findViewById(R.id.txtAddress);


        btnAddCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txtName.toString();
                phone = txtPhone.toString();
                address = txtAddress.toString();

                if (name.toUpperCase().matches("\\w+\\d+.*")){
                    dlgAlert.setMessage("Customer name can't contain numbers!");
                    dlgAlert.setTitle("Error Message");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                }
                (MainActivity.currentCustID)++;
            }
        });

        btnCancelCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hide the activity
                finish();
                //Start the New Customer Activity
                Intent myIntent=new Intent(NewCustomerActivity.this,
                        MainActivity.class);
                NewCustomerActivity.this.startActivity(myIntent);
            }
        });

        final int currentCustomerID = MainActivity.currentCustID;
        txtCustID.setText(currentCustomerID);



    }
}
