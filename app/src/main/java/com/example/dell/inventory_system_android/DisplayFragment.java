package com.example.dell.inventory_system_android;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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


    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        List<String> customersString = new ArrayList<String>();
        for (int i = 0 ;i < Customer.getCustomersList().size(); i++) customersString.add(Customer.getCustomersList().get(i).toString());
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,customersString);
        // Inflate the layout for this fragment
        ListView lv = (ListView)rootView.findViewById(R.id.listViewFragment);
        lv.setAdapter(adapter);
        return rootView;
    }

}
