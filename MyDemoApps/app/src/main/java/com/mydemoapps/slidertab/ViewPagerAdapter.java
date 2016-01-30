package com.mydemoapps.slidertab;

import android.graphics.Camera;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mydemoapps.frgmnt.CameraImageUpload;
import com.mydemoapps.frgmnt.Facebk;
import com.mydemoapps.frgmnt.ListData;
import com.mydemoapps.frgmnt.Map;

/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	CharSequence Titles[];
	int NumbOfTabs;
	public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[],
							int mNumbOfTabsumb) {
		super(fm);

		this.Titles = mTitles;
		this.NumbOfTabs = mNumbOfTabsumb;
	}

	// This method return the fragment for the every position in the View Pager
	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			CameraImageUpload cm = new CameraImageUpload();
			return cm;
		} else {
			ListData listData = new ListData();
			return listData;

		}
	}

	// This method return the titles for the Tabs in the Tab Strip

	@Override
	public CharSequence getPageTitle(int position) {
		return Titles[position];
	}

	// This method return the Number of tabs for the tabs Strip

	@Override
	public int getCount() {
		return NumbOfTabs;
	}
}