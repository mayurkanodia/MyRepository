package com.example.bottomtabfragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VideoFragment extends Fragment implements OnClickListener{
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  // TODO Auto-generated method stub
  return inflater.inflate(R.layout.audio, container, false);
 }
 @Override
 public void onActivityCreated(Bundle savedInstanceState) {
  // TODO Auto-generated method stub
  super.onActivityCreated(savedInstanceState);
  TextView tv=(TextView)getActivity().findViewById(R.id.textset);
  tv.setText("Video");
  tv.setOnClickListener(this);
 }
 @Override
	public void onResume() {
		Toast.makeText(getActivity(),"Video fragment", 1).show();
		super.onResume();
	}
 @Override
 public void onClick(View arg0) {
  // TODO Auto-generated method stub
  ((BaseContainerFragment)getParentFragment()).replaceFragment(new VideoDescription(), true);
 }

}