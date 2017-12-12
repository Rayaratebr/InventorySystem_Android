package com.example.dell.inventory_system_android;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewCustomerActivity extends AppCompatActivity {

    String name,phone,address;
    int currentCustomerID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        final AlertDialog.Builder dlgAlert2 = new AlertDialog.Builder(this);

        Button btnAddCust = (Button) findViewById(R.id.btnAddCust);
        Button btnCancelCust= (Button) findViewById(R.id.btnCclCust);
        Button btnClear = (Button) findViewById(R.id.btnClr);

        final TextView txtCustID= (TextView) findViewById(R.id.txtCustID);
        currentCustomerID = MainActivity.currentCustID;
        txtCustID.setText(Integer.toString(currentCustomerID));


        final EditText txtName = (EditText) findViewById(R.id.txtCustName);
        final EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
        final EditText txtAddress = (EditText) findViewById(R.id.txtAddress);


        btnAddCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txtName.getText().toString();
                phone = txtPhone.getText().toString();
                address = txtAddress.getText().toString();

                if (name.matches("\\w+\\d+.*")){
                    dlgAlert.setMessage("Customer name can't contain numbers!");
                    dlgAlert.setTitle("Error Message");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                }

                if(name.isEmpty()){
                    dlgAlert.setMessage("Name field can't be left blank!");
                    dlgAlert.setTitle("Error Message");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }

                if(txtPhone.toString().equals("")){
                    dlgAlert2.setMessage("Phone field can't be left blank!");
                    dlgAlert2.setTitle("Error Message");
                    dlgAlert2.setPositiveButton("OK", null);
                    dlgAlert2.setCancelable(true);
                    dlgAlert2.create().show();
                }
                if(address.isEmpty()){
                    dlgAlert.setMessage("Address field can't be left blank!");
                    dlgAlert.setTitle("Error Message");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }

                //TODO IF EVERYTHING IS FINE ADD THE CUSTOMER AND INCREMENT THE ID
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

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCustomerID = MainActivity.currentCustID;
                txtCustID.setText(Integer.toString(currentCustomerID));
                txtName.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
            }
        });




    }
}
