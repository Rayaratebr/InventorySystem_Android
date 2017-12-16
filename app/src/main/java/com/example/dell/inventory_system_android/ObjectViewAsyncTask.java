package com.example.dell.inventory_system_android;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.dell.inventory_system_android.Models.Parent;

import java.util.List;


public class ObjectViewAsyncTask extends AsyncTask<Integer,String ,Parent>
{

    private ViewActivity activity;//3shan amarri2 el activity la had el class 3shan a2dr ast3ml el motheds eli fih

    public ObjectViewAsyncTask(ViewActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Parent doInBackground(Integer... params) {

        Parent viewObject = null;
        int objectID = params[0];
        int objectType = params[1];

        switch (objectType){
            case Helpers.CUSTOMER:
                viewObject = Helpers.getCustomer(activity,objectID);
                break;
            case Helpers.PRODUCT:
                viewObject = Helpers.getProduct(activity,objectID);
                break;
            case Helpers.PAYMENT:
                viewObject = Helpers.getPayment(activity,objectID);
                break;
            case Helpers.ORDER:
                viewObject = Helpers.getOrder(activity,objectID);
                break;

        }

        return viewObject;
    }

    @Override
    protected void onPostExecute(Parent viewObject) {

        super.onPostExecute(viewObject);

        activity.setObject(viewObject);

    }
}
