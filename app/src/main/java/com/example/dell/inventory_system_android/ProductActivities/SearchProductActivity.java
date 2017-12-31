package com.example.dell.inventory_system_android.ProductActivities;

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

public class SearchProductActivity extends AppCompatActivity {

    private Button searchBtn;
    private EditText unitTxt;
    private EditText priceTxt;
    private DisplayFragment displayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        unitTxt = (EditText) findViewById(R.id.unitTxt);
        priceTxt = (EditText) findViewById(R.id.priceTxt);
        displayFragment = (DisplayFragment) getFragmentManager().findFragmentById(R.id.displayFragment);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int showType = Helpers.PRODUCT;
                String unit = unitTxt.getText().toString();
                String price = priceTxt.getText().toString();
                searchAsyncTask asyncTask = new searchAsyncTask(SearchProductActivity.this, displayFragment);
                asyncTask.execute(String.valueOf(showType), unit, price);
                displayFragment.setShowActivity(showActivity.get(showType));
            }
        });
    }

}
