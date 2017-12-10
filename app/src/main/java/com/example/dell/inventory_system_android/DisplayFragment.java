package com.example.dell.inventory_system_android;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {
    List<String> customersString;
    List<String> ordersString;
    List<String> productsString;
    ArrayAdapter<String> adapter;



    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        customersString = new ArrayList<String>();
        ordersString = new ArrayList<String>();
        productsString = new ArrayList<String>();
        for (int i = 0 ;i < Customer.getCustomersList().size(); i++) customersString.add(Customer.getCustomersList().get(i).toString());
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,customersString);
        // Inflate the layout for this fragment
        ListView lv = (ListView)rootView.findViewById(R.id.listViewFragment);
        lv.setAdapter(adapter);
        return rootView;
    }

    public interface Communicator{
        public void respond(int position);
    }

    public void changeData(int position){
        if (position == R.id.nav_customer_display){
            customersString.add("Customers");
            customersString.add("are being");
            customersString.add("displayed");
            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,customersString);
            adapter.notifyDataSetChanged();
        }
        /*else if(position == R.id.nav_order_display){
            ordersString.add("Orders");
            ordersString.add("Displayed");
            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ordersString);
            adapter.notifyDataSetChanged();
        }
        else if (position == R.id.nav_product_display){
            productsString.add("Products");
            productsString.add("Displayed");
            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,productsString);
            adapter.notifyDataSetChanged();

        }*/
    }

}
