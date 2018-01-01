package com.example.dell.inventory_system_android;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.dell.inventory_system_android.Helpers.showActivity;

public class ListingActivity extends AppCompatActivity {
    DisplayFragment displayFragment;
    public static int showType;
    public static int ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        displayFragment = (DisplayFragment) getFragmentManager().findFragmentById(R.id.fragment);
        connectionAsyncTask asyncTask = new connectionAsyncTask(ListingActivity.this,displayFragment);
        asyncTask.execute(showType,ID);
        displayFragment.setShowActivity(showActivity.get(showType));
    }
}
