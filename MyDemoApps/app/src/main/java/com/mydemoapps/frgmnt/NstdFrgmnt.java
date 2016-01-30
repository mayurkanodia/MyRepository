package com.mydemoapps.frgmnt;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mydemoapps.R;

/**
 * Created by android on 29/1/16.
 */
public class NstdFrgmnt extends Fragment {



    Button bk;


    public NstdFrgmnt() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.nstd_frmnt_lay, container, false);

        //////////////////////
        bk=(Button)rootView.findViewById(R.id.bk);

        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  getActivity().finish();
             /*   NstdFrgmnt fragment = new NstdFrgmnt();
                if (fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();
                }*/
            }
        });
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
