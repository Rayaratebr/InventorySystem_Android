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
import com.example.dell.inventory_system_android.CustomerActivities.*;
import com.example.dell.inventory_system_android.OrderActivities.NewOrderActivity;
import com.example.dell.inventory_system_android.ProductActivities.NewProductActivity;

import static com.example.dell.inventory_system_android.Helpers.showActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle aToggle;
    AlertDialog.Builder builder;
    int deletedID;
    Intent myIntent;
    DisplayFragment displayFragment;
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();




    public static int currentCustID = 0, currentOrderID = 0, currentProductID = 0;
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



        displayFragment = (DisplayFragment) getFragmentManager().findFragmentById(R.id.displayFragment);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        aToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.naviView);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
            mNavigationView.bringToFront();
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

        connectionAsyncTask asyncTask = new connectionAsyncTask(MainActivity.this,displayFragment);
        asyncTask.execute(Helpers.CUSTOMER);
        displayFragment.setShowActivity(showActivity.get(Helpers.CUSTOMER));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (aToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (isDisplayItem(id)) {
//            displayInFragment(id);
            int showType = Helpers.menuShowItemsType.get(id);
            connectionAsyncTask asyncTask = new connectionAsyncTask(MainActivity.this,displayFragment);
            asyncTask.execute(showType);
            displayFragment.setShowActivity(showActivity.get(showType));
        }
       /* if (isSearchItem(id)){
            int searchType = Helpers.menuShowItemsType.get(id);
          //  connectionAsyncTask asyncTask = new connectionAsyncTask(MainActivity.this,SearchActivity);
          //  asyncTask.execute(searchType);
        }*/

        if (id == R.id.nav_customer_add) {
            //hide the activity
            //finish();
            //Start the New Customer Activity
            Intent myIntent = new Intent(MainActivity.this,
                    NewCustomerActivity.class);
            MainActivity.this.startActivity(myIntent);
        }

        if(id == R.id.nav_customer_search){
            myIntent = new Intent(MainActivity.this,
                    SearchCustomerActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
   
        if (id == R.id.nav_customer_order) {
            //hide the activity
            //finish();
            //Start the New Customer Activity
            myIntent = new Intent(MainActivity.this,
                    NewOrderActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
        if (id == R.id.nav_order_add) {
            //hide the activity
            //finish();
            //Start the New Customer Activity
            myIntent = new Intent(MainActivity.this,
                    NewOrderActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
        if (id == R.id.nav_product_add) {
            myIntent = new Intent(MainActivity.this, NewProductActivity.class);
            MainActivity.this.startActivity(myIntent);
        }

        if (id == R.id.nav_close) {
            finish();
        }
        drawerLayout.closeDrawers();

        return true;
    }


//    private void displayInFragment(int menuItemId) {
//
//        if (displayFragment.isAdded()) {
//            List<Parent> displayList = null;
//
//            switch (menuItemId) {
//                case R.id.nav_customer_display:
//                    displayList = Helpers.getCustomersList(this);
//                    break;
//
//                case R.id.nav_order_display:
//                    displayList = Helpers.getOrdersList(this);
//                    break;
//
//                case R.id.nav_product_display:
//                    displayList = Helpers.getProductsList(this);
//
//                    break;
//
//            }
//
////            while (displayList == null) ;//TODO: async
//            displayFragment.fillList(displayList);
//
//        }
//
//    }

    private boolean isDisplayItem(int id) {
        return id == R.id.nav_customer_display || id == R.id.nav_order_display || id == R.id.nav_product_display;
    }

  /*  private boolean isSearchItem(int id){
        return id == R.id.nav_customer_search || id == R.id.nav_order_search || id == R.id.nav_product_search;
    }*/





}
