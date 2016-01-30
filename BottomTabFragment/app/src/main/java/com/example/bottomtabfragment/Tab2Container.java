package com.example.bottomtabfragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab2Container extends BaseContainerFragment {
 
 private boolean IsViewInited;
 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  Log.e("Ritesh", "Tab2");
  return inflater.inflate(R.layout.container_fragment, null);
 }
 
 @Override
 public void onActivityCreated(Bundle savedInstanceState) {
  super.onActivityCreated(savedInstanceState);
  if (!IsViewInited) {
   IsViewInited = true;
   initView();
     }
 }
 
 private void initView() {
  replaceFragment(new VideoFragment(), false);
  
 }
 
}