package com.example.dell.inventory_system_android;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.OrderActivities.ChooseProductsActivity;
import com.example.dell.inventory_system_android.OrderActivities.NewOrderActivity;

import java.util.ArrayList;
import java.util.List;


public class connectionAsyncTask extends AsyncTask<Integer, String, List<Parent>> {

    private Activity activity;//3shan amarri2 el activity la had el class 3shan a2dr ast3ml el motheds eli fih
    private Fragment fragment;
    private ChooseProductsActivity myProductActivity;

    public connectionAsyncTask(Activity activity, Fragment fragment) {
        this.activity = activity;
        this.fragment = fragment;
    }

   public connectionAsyncTask(ChooseProductsActivity activity){
        this.myProductActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Parent> doInBackground(Integer... params) {

        List<Parent> displayList = null;
        int showTypeId = params[0];
        int ID = -1;
        if (params.length > 1) {
            ID = params[1];
        }

        switch (showTypeId) {
            case Helpers.CUSTOMER:
                displayList = Helpers.getCustomersList(activity, null, null);
                break;

            case Helpers.ORDER:
                displayList = Helpers.getOrdersList(activity);
                break;

            case Helpers.PRODUCT:
                displayList = Helpers.getProductsList(activity,null,null);
                break;

            case Helpers.CUSTOMER_ORDERS:
                displayList = Helpers.getCustomerOrdersList(activity, ID);
                break;

        }
        return displayList;
    }

    @Override
    protected void onPostExecute(List<Parent> displayList) {

        super.onPostExecute(displayList);
        DisplayFragment displayFragment = (DisplayFragment) fragment;


        try{
            displayFragment.fillList(displayList);
        }
        catch (NullPointerException e){
            myProductActivity.fillList(displayList);
        }

    }
}
