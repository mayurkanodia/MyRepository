package com.mydemoapps.slidertab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mydemoapps.R;
import com.mydemoapps.slidertab.SlidingTabLayout;
import com.mydemoapps.slidertab.ViewPagerAdapter;

/**
 * Created by Mayor kanodiya on 04-01-2016.
 */
public class TabFgmnt extends Fragment {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = { "Camera", "List" };
    int Numboftabs = 2;

    public TabFgmnt() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgmnt_nstd, container, false);
        adapter = new ViewPagerAdapter(getFragmentManager(), Titles, Numboftabs);
        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) rootView.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        // homeFragment = this;
        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) rootView.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.textColorPrimary);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


/*
        MainActivity.drawerFragment.setDrawerState(true);
        ImageView menu;
        menu = (ImageView) getActivity().findViewById(R.id.tgl_menu);
        menu.setVisibility(View.VISIBLE);
*/
        // Inflate the layout for this fragment
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

