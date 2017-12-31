package com.example.dell.inventory_system_android.CustomerActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.inventory_system_android.DisplayFragment;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.searchAsyncTask;

import static com.example.dell.inventory_system_android.Helpers.showActivity;

public class SearchCustomerActivity extends AppCompatActivity {

    DisplayFragment displayFragment;
    Button searchBtn;
    EditText nameTxt, phoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);

        searchBtn = (Button) findViewById(R.id.searchBtn);
        nameTxt = (EditText) findViewById(R.id.unitTxt);
        phoneTxt = (EditText) findViewById(R.id.priceTxt);
        displayFragment = (DisplayFragment) getFragmentManager().findFragmentById(R.id.displayFragment);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int showType = Helpers.CUSTOMER;
                String name = nameTxt.getText().toString();
                String phone = phoneTxt.getText().toString();
                searchAsyncTask asyncTask = new searchAsyncTask(SearchCustomerActivity.this, displayFragment);
                asyncTask.execute(String.valueOf(showType), name, phone);
                displayFragment.setShowActivity(showActivity.get(showType));
            }
        });
    }
}
