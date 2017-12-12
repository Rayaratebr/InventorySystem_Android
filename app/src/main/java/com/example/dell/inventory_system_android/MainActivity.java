package com.example.dell.inventory_system_android;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle aToggle;
    AlertDialog.Builder builder;
    int deletedID;
   /* DisplayFragment displayFragment;
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();*/

    static int currentCustID = 0;

   /* public static int getCurrentCustID() {
        return currentCustID;
    }

    public static void setCurrentCustID(int currentCustID2) {
        currentCustID = currentCustID2;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

     //   displayFragment = new DisplayFragment();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        aToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.naviView);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setTitle("Deleting an Item");
        builder.setMessage("Enter the ID of the item to be deleted");

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletedID = Integer.parseInt(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (aToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_customer_add){
            //hide the activity
            finish();
            //Start the New Customer Activity
            Intent myIntent=new Intent(MainActivity.this,
                    NewCustomerActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
        if (id == R.id.nav_customer_display){
          /*  fragmentTransaction.add(R.id.linearLayout,displayFragment,"FA");
            getFragmentManager().beginTransaction().add(R.id.drawer_layout,displayFragment,"FA").commit();
            fragmentTransaction.commit();
            if (displayFragment.isAdded()){
                builder.show();
                displayFragment.changeData(R.id.nav_customer_display);
            }*/

        }
        if (id == R.id.nav_customer_delete){
            builder.show();
            //TODO send the id to delete a customer
        }
        if (id == R.id.nav_order_delete){
            builder.show();
            //TODO send the id to delete an order
        }
        if (id == R.id.nav_product_delete){
            builder.show();
            //TODO send the id to delete a product
        }

        if (id == R.id.nav_customer_order){
            //hide the activity
            finish();
            //Start the New Customer Activity
            Intent myIntent=new Intent(MainActivity.this,
                    NewOrderActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
        if (id == R.id.nav_order_add){
            //hide the activity
            finish();
            //Start the New Customer Activity
            Intent myIntent=new Intent(MainActivity.this,
                    NewOrderActivity.class);
            MainActivity.this.startActivity(myIntent);
        }




        return false;
    }




}
