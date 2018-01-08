package com.example.dell.inventory_system_android.OrderActivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Config;
import com.example.dell.inventory_system_android.CustomerActivities.ViewCustomerActivity;
import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.ListingActivity;
import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.MainActivity;
import com.example.dell.inventory_system_android.Models.Order;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dell.inventory_system_android.ViewActivity.objectID;

public class ViewOrderActivity extends ViewActivity {

    Order order;
    TextView orderDetailsLbl;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    Button addPaymentBtn,viewPaymentsBtn, deleteOrder, backBtn, assignCustomer;
    Context context = this;
    Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        ObjectViewAsyncTask asyncTask = new ObjectViewAsyncTask(ViewOrderActivity.this);
        asyncTask.execute(objectID,Helpers.ORDER);

        addPaymentBtn = (Button) findViewById(R.id.addPaymentBtnOrder);
        viewPaymentsBtn = (Button) findViewById(R.id.viewPaymentsBtnOrder);
        deleteOrder = (Button) findViewById(R.id.buttonDeleterOrder);
        backBtn = (Button) findViewById(R.id.backButton);
        assignCustomer = (Button) findViewById(R.id.assignCustomer);
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        deleteOrder.setAnimation(shake);


        assignCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder = new AlertDialog.Builder(context);
                dialog = builder.create();
                builder.setTitle("Choose Customer")
                        .setMessage("Enter Customer ID")
                        .setView(input)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long newCustomerID = Long.parseLong((input.getText()).toString());
                                order.setCustomer_id(newCustomerID);
                                Call<String> repos = Config.apiService.updateOrder(objectID,order);
                                repos.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        Toast.makeText(ViewOrderActivity.this, response.body(), Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Toast.makeText(ViewOrderActivity.this, "error", Toast.LENGTH_LONG).show();

                                    }
                                });
                            }
                        });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                });
                builder.setCancelable(true);
                builder.show();
            }
        });

     /*   assignCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                List<Customer> customers = Helpers.getAllCustomersList(activity);//use function to get all customers in the DB
                final CharSequence[] items = {};
                for (int i = 0 ; i < customers.size() ; i++){
                    items[i] = customers.get(i).getName();
                }

// arraylist to keep the selected items
                final ArrayList seletedItems=new ArrayList();
                dialog = new AlertDialog.Builder(context);
                        dialog.setTitle("Select The Customer");
                        dialog.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
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
        });*/

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



//        params = getIntent().getExtras();
//        currentCustomerID = Helpers.getIDForActivity(params);


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
             view.startAnimation(shake);
             Call<String> repos = Config.apiService.deleteOrder(objectID);
             repos.enqueue(new Callback<String>() {
                 @Override
                 public void onResponse(Call<String> call, Response<String> response) {
                     Toast.makeText(ViewOrderActivity.this, response.body(), Toast.LENGTH_LONG).show();

                 }

                 @Override
                 public void onFailure(Call<String> call, Throwable t) {
                     Toast.makeText(ViewOrderActivity.this, "Error", Toast.LENGTH_LONG).show();

                 }
             });

             Intent myIntent = new Intent(ViewOrderActivity.this,
                     MainActivity.class);
             myIntent.putExtra("fromAnotherActivity",Helpers.ORDER);
             ViewOrderActivity.this.startActivity(myIntent);
             finish();


         }
     });
    }



    public void setObject(Parent object){

        this.order = (Order)object;
       orderDetailsLbl.setText(order.displayOrder());
    }
}
