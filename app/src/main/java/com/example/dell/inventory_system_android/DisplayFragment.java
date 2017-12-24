package com.example.dell.inventory_system_android;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.Models.Parent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    List<String> stringList = new ArrayList<String>(Arrays.asList(new String[]{"asdaasdaw"}));
    ArrayAdapter<String> adapter;
    List<Parent> listing;
    Class showActivity;
    ListView lv;


    public DisplayFragment() {
        // Required empty public constructor
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        // Inflate the layout for this fragment
        lv = (ListView) rootView.findViewById(R.id.listViewFragment);
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, stringList);
        Log.w("%%%%%%%%%555", "before:");
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(),
                        showActivity);
                ViewActivity.objectID = listing.get(i).getId();
//                Bundle b = new Bundle();
//                b.putInt("ID", listing.get(i).getId()); //Your id
//                intent.putExtras(b); //Put your id to your next Intent

                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        return rootView;
    }


    public interface Communicator {
        public void respond(int position);
    }


    public void fillList(List<Parent> list) {
        Log.w("FRAGMENT", "FillingList");
        if (list == null) {
            Toast.makeText(this.getActivity(), "ERROR: unable to fetch data", Toast.LENGTH_LONG).show();
            return;
        }

        this.listing = list;
        stringList.clear();
        for (Parent item : list) {
            stringList.add(item.listDisplay());
            Log.w("ListData",item.listDisplay());
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

    public void setShowActivity(Class activity) {
        this.showActivity = activity;
    }

}
