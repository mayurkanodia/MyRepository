package com.mydemoapps.frgmnt;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import com.mydemoapps.R;
import com.mydemoapps.adapter.MyOrderAdapter;
import com.mydemoapps.model.Products;

/**
 * Created by Ravi on 29/07/15.
 */
public class ListData extends Fragment {


    ListView list;
    ArrayList<Products> productses = new ArrayList<>();
    public ListData() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flist, container, false);
        list =(ListView)rootView.findViewById(R.id.list);


        for(int l=0; l<=20; l++){

            Products products = new Products();
            products.setIteam_name("abc");
            products.setIteam_price("20/kg");

            productses.add(products);
        }

        MyOrderAdapter adapter = new MyOrderAdapter(productses,getActivity());
        list.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
