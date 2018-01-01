package com.example.dell.inventory_system_android;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;

import com.example.dell.inventory_system_android.Models.Parent;

import java.util.List;


public class searchAsyncTask extends AsyncTask<String, String, List<Parent>> {

    private Activity activity;//3shan amarri2 el activity la had el class 3shan a2dr ast3ml el motheds eli fih
    private Fragment fragment;

    public searchAsyncTask(Activity activity, Fragment fragment) {
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Parent> doInBackground(String... params) {

        List<Parent> displayList = null;
        int showTypeId = Integer.parseInt(params[0]);
        switch (showTypeId) {
            case Helpers.CUSTOMER:
                String name = null, phone = null;
                if (params.length > 1) {
                    if (!params[1].equals("")) {
                        name = params[1];
                    }
                    if (!params[2].equals("")) {
                        phone = params[2];
                    }
                }
                displayList = Helpers.getCustomersList(activity, name, phone);
                break;

            case Helpers.ORDER:
                String orderDateFrom = null, orderDateTo = null,orderDueDateFrom = null, orderDueDateTo = null;
                if (params.length > 1) {
                    if (!params[1].equals("")) {
                        orderDateFrom = params[1];
                    }
                    if (!params[2].equals("")) {
                        orderDateTo = params[2];
                    }
                    if (!params[3].equals("")) {
                        orderDueDateFrom = params[3];
                    }
                    if (!params[4].equals("")) {
                        orderDueDateTo = params[4];
                    }
                }
                displayList = Helpers.getOrdersList(activity,orderDateFrom,orderDateTo,orderDueDateFrom,orderDueDateTo);
                break;

            case Helpers.PRODUCT:
                String unit = null, price = null;
                if (params.length > 1) {
                    if (!params[1].equals("")) {
                        unit = params[1];
                    }
                    if (!params[2].equals("")) {
                        price = params[2];
                    }
                }
                displayList = Helpers.getProductsList(activity,unit,price);
                break;

        }
        return displayList;
    }

    @Override
    protected void onPostExecute(List<Parent> displayList) {

        super.onPostExecute(displayList);
        DisplayFragment displayFragment = (DisplayFragment) fragment;
        displayFragment.fillList(displayList);
    }
}
