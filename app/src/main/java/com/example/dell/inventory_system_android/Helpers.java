package com.example.dell.inventory_system_android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.Models.Order;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.Models.Payment;
import com.example.dell.inventory_system_android.Models.Product;
import com.example.dell.inventory_system_android.CustomerActivities.*;
import com.example.dell.inventory_system_android.OrderActivities.NewOrderActivity;
import com.example.dell.inventory_system_android.ProductActivities.NewProductActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class Helpers {

    public static final int CUSTOMER = 0;
    public static final int PRODUCT = 1;
    public static final int PAYMENT = 2;
    public static final int ORDER = 3;

    public static final int CUSTOMER_ORDERS = 4;

    public static final Map<Integer, Class> showActivity = new HashMap<Integer, Class>() {
        {
            put(CUSTOMER, ViewCustomerActivity.class);
            put(ORDER, NewOrderActivity.class);
            put(PRODUCT, NewProductActivity.class);

        }
    };
    public static final Map<Integer, Integer> menuShowItemsType = new HashMap<Integer, Integer>() {
        {
            put(R.id.nav_customer_display, CUSTOMER);
            put(R.id.nav_order_display,ORDER );
            put(R.id.nav_product_display,PRODUCT );
        }
    };


    public static List<Parent> getCustomersList(Activity activity, String name,String phone) {
        Log.w("SERVER", "GetCustomers");
        Call<List<Customer>> repos = Config.apiService.getAllCustomers(name,phone);
        try {
            return (List<Parent>) (Object) repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Customers", Toast.LENGTH_LONG).show();

            return null;
        }
    }

    public static List<Parent> getOrdersList(Activity activity) {
        Call<List<Order>> repos = Config.apiService.getAllOrders();
        try {
            return (List<Parent>) (Object) repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Orders", Toast.LENGTH_LONG).show();
            returnToMainActivity(activity);

            return null;
        }
    }

    public static List<Parent> getCustomerOrdersList(Activity activity, int customerID) {
        Call<List<Order>> repos = Config.apiService.getCustomerOrders(customerID);
        try {
            return (List<Parent>) (Object) repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Customer Orders", Toast.LENGTH_LONG).show();
            returnToMainActivity(activity);

            return null;
        }
    }

    public static List<Parent> getProductsList(Activity activity,String unit,String price) {
        Call<List<Product>> repos = Config.apiService.getAllProducts(unit,price);
        try {
            return (List<Parent>) (Object) repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Products", Toast.LENGTH_LONG).show();
            returnToMainActivity(activity);

            return null;
        }
    }

    public static List<Parent> getPaymentsList(Activity activity) {
        Call<List<Payment>> repos = Config.apiService.getAllPayments();
        try {
            return (List<Parent>) (Object) repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Payments", Toast.LENGTH_LONG).show();
            returnToMainActivity(activity);

            return null;
        }
    }

    public static Customer getCustomer(Activity activity, int customer_id) {
        Call<Customer> repos = Config.apiService.getCustomer(customer_id);
        try {
            return repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Customer", Toast.LENGTH_LONG).show();
            returnToMainActivity(activity);

            return null;
        }

    }

    public static Product getProduct(Activity activity, int product_id) {
        Call<Product> repos = Config.apiService.getProduct(product_id);
        try {
            return repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Product", Toast.LENGTH_LONG).show();
            returnToMainActivity(activity);

            return null;
        }

    }

    public static Payment getPayment(Activity activity, int payment_id) {
        Call<Payment> repos = Config.apiService.getPayment(payment_id);
        try {
            return repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Payment", Toast.LENGTH_LONG).show();
            returnToMainActivity(activity);

            return null;
        }

    }

    public static Order getOrder(Activity activity,int order_id) {
        Call<Order> repos = Config.apiService.getOrder(order_id);
        try {
            return repos.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ERROR: Can't fetch Order", Toast.LENGTH_LONG).show();
            returnToMainActivity(activity);

            return null;
        }

    }


    public static int getIDForActivity(Bundle b) {
        int value = -1; // or other values
        if (b != null) {
            value = b.getInt("ID");
        }

        return value;
    }

    public static void returnToMainActivity(Activity activity){
        Intent i=new Intent(activity, MainActivity.class);
        activity.startActivity(i);
    }
}
