package com.mydemoapps.tabfragmentsviewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mydemoapps.frgmnt.CameraImageUpload;
import com.mydemoapps.frgmnt.Facebk;
import com.mydemoapps.frgmnt.ListData;
import com.mydemoapps.slidertab.TabFgmnt;

/*
FragmentStatePagerAdapter*/
public class TabActivityFragmentAdapter extends FragmentPagerAdapter{

	final int PAGE_COUNT = 4;
	Context context;
	CharSequence Titles[] = { "LIST", "FACEBOOK","CAMERA","NSTAD PAGER" };


	public TabActivityFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		switch(position){


			case 0:
				ListData  listData= new ListData();
				return  listData;

			case 1:
				Facebk facebk = new Facebk();
				return  facebk;

			case 2:
				CameraImageUpload cameraImageUpload=new CameraImageUpload();
				return cameraImageUpload;
			case 3:

				TabFgmnt tabFgmnt = new TabFgmnt();
				return  tabFgmnt;

		}

		return null;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return "Page " + position+" "+Titles[position];
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

}
