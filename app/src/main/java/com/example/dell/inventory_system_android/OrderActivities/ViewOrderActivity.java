package com.example.dell.inventory_system_android.OrderActivities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.ListingActivity;
import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.Models.Order;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dell.inventory_system_android.ViewActivity.objectID;

public class ViewOrderActivity extends ViewActivity {

    Order order;
    TextView orderDetailsLbl;

    Button addPaymentBtn,viewPaymentsBtn, deleteOrder, backBtn, assignCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);


        addPaymentBtn = (Button) findViewById(R.id.addPaymentBtnOrder);
        viewPaymentsBtn = (Button) findViewById(R.id.viewPaymentsBtnOrder);
        deleteOrder = (Button) findViewById(R.id.buttonDeleterOrder);
        backBtn = (Button) findViewById(R.id.backButton);
        assignCustomer = (Button) findViewById(R.id.assignCustomer);

        assignCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ArrayList<Customer> customers = new ArrayList<Customer>();//use function to get all customers in the DB
                final CharSequence[] items = {};
                for (int i = 0 ; i < customers.size() ; i++){
                    items[i] = customers.get(i).getName();
                }

// arraylist to keep the selected items
                final ArrayList seletedItems=new ArrayList();
                AlertDialog dialog = new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Select The Customer")
                        .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    seletedItems.add(indexSelected);
                                } else if (seletedItems.contains(indexSelected)) {
                                    // Else, if the item is already in the array, remove it
                                    seletedItems.remove(Integer.valueOf(indexSelected));
                                }
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //  Your code when user clicked on OK
                                //  You can write the code  to save the selected item here
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //  Your code when user clicked on Cancel
                            }
                        }).create();
                dialog.show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



//        params = getIntent().getExtras();
//        currentCustomerID = Helpers.getIDForActivity(params);

        ObjectViewAsyncTask asyncTask = new ObjectViewAsyncTask(ViewOrderActivity.this);
        asyncTask.execute(objectID, Helpers.ORDER);

     /*   addPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewOrderActivity.this,
                        NewPaymentActivity.class);
                NewPaymentActivity.currentCustomerID = objectID;

                ViewOrderActivity.this.startActivity(myIntent);
            }
        });
        viewPaymentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewOrderActivity.this,
                        ListingActivity.class);
//                Bundle b = new Bundle();
//                b.putInt("ID",objectID); //Your id
//                myIntent.putExtras(b); //Put your id to your next Intent
                ListingActivity.showType = Helpers.CUSTOMER_PAYMENTS;
                ListingActivity.ID = objectID;
                ViewOrderActivity.this.startActivity(myIntent);
            }
        });*/

     deleteOrder.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Call<Order> repos = Config.apiService.deleteOrder(objectID);
             repos.enqueue(new Callback<Order>() {
                 @Override
                 public void onResponse(Call<Order> call, Response<Order> response) {
                     Toast.makeText(ViewOrderActivity.this, "Success", Toast.LENGTH_LONG).show();
                 }

                 @Override
                 public void onFailure(Call<Order> call, Throwable t) {
                     Toast.makeText(ViewOrderActivity.this, "Error", Toast.LENGTH_LONG).show();

                 }
             });
         }
     });
    }



    public void setObject(Parent object){

        this.order = (Order)object;
        orderDetailsLbl.setText(order.displayOrder());//TODO: make it prettier
    }
}
