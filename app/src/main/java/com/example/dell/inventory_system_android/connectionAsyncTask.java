package com.example.dell.inventory_system_android;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.dell.inventory_system_android.Models.Parent;
import java.util.ArrayList;
import java.util.List;


public class connectionAsyncTask extends AsyncTask<Integer,String ,List<Parent>>
{

    private Activity activity;//3shan amarri2 el activity la had el class 3shan a2dr ast3ml el motheds eli fih

    public connectionAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Parent> doInBackground(Integer... params) {

        List<Parent> displayList = null;
        int menuItemId = params[0];
        switch (menuItemId) {
            case R.id.nav_customer_display:
                displayList = Helpers.getCustomersList((MainActivity)activity);
                break;

            case R.id.nav_order_display:
                displayList = Helpers.getOrdersList(activity);
                break;

            case R.id.nav_product_display:
                displayList = Helpers.getProductsList(activity);

                break;

        }
        return displayList;
    }

    @Override
    protected void onPostExecute(List<Parent> displayList) {

        super.onPostExecute(displayList);
        DisplayFragment displayFragment = (DisplayFragment) ((MainActivity)activity).getFragmentManager().findFragmentByTag("FDisplay");
        displayFragment.fillList(displayList);
    }
}
