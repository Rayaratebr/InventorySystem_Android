package com.example.dell.inventory_system_android.OrderActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.inventory_system_android.Helpers;
import com.example.dell.inventory_system_android.ListingActivity;
import com.example.dell.inventory_system_android.Models.Order;
import com.example.dell.inventory_system_android.Models.Parent;
import com.example.dell.inventory_system_android.ObjectViewAsyncTask;
import com.example.dell.inventory_system_android.R;
import com.example.dell.inventory_system_android.ViewActivity;

import static com.example.dell.inventory_system_android.ViewActivity.objectID;

public class ViewOrderActivity extends ViewActivity {

    Order order;
    TextView orderDetailsLbl;

    Button addPaymentBtn,viewPaymentsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);


        addPaymentBtn = (Button) findViewById(R.id.addPaymentBtnOrder);
        viewPaymentsBtn = (Button) findViewById(R.id.viewPaymentsBtnOrder);



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

    }


    public void setObject(Parent object){

        this.order = (Order)object;
        orderDetailsLbl.setText(order.toString());//TODO: make it prettier
    }
}
