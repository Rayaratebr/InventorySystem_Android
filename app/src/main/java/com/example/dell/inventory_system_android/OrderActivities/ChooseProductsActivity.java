package com.example.dell.inventory_system_android.OrderActivities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.Models.Order;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.Models.Product;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.connectionAsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ChooseProductsActivity extends AppCompatActivity {

    private List<String> stringList = new ArrayList<String>(Arrays.asList(new String[]{"asdaasdaw"}));
    private ArrayAdapter<String> adapter;
    private List<Parent> listing;
   // Class showActivity;
    private ListView lv;
    private int quantity;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private ArrayList<Product> productsList;
    private final Context context = this;

    private Button btnDone, btnCancel;

    private HashMap<Integer, Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_products);

        connectionAsyncTask asyncTask = new connectionAsyncTask(ChooseProductsActivity.this);
        asyncTask.execute(Helpers.PRODUCT);

        lv = (ListView) findViewById(R.id.listView);

        btnDone = (Button) findViewById(R.id.btnDoneProducts);
        btnCancel = (Button) findViewById(R.id.btnCclProducts);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringList);

        Log.w("%%%%%%%%%555", "before:");
        lv.setAdapter(adapter);


       builder = new AlertDialog.Builder(context);
        dialog = builder.create();
        builder.setTitle("Quantity");
        builder.setMessage("Enter the quantity wanted of this product");
        list= new HashMap<Integer, Integer>();



// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

       builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       quantity = Integer.parseInt((input.getText().toString()));

                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });
        builder.setCancelable(true);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //TODO add the selected product to the order
                builder.show();
                productsList=
                        new ArrayList<Product>();
                productsList.add(new Product((listing.get(i).getId()),quantity));
              // list.put(new Integer(listing.get(i).getId()),new Integer(quantity));
                Intent myIntent = getIntent();
                Order objOrder = (Order) myIntent.getSerializableExtra("sampleObject");
                objOrder.setProducts(productsList);

            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ADD THE PRODUCTS TO THE ORDER
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void fillList(List<Parent> myList) {
        Log.w("FRAGMENT", "FillingList");
        if (myList == null) {
            Toast.makeText(this, "ERROR: unable to fetch data", Toast.LENGTH_LONG).show();
            return;
        }

        this.listing = myList;
        stringList.clear();
        for (Parent item : myList) {
            stringList.add(item.listDisplay());
            Log.w("ListData",item.listDisplay());
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }
}
