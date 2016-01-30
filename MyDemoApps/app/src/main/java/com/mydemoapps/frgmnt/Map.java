package com.mydemoapps.frgmnt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mydemoapps.R;
import com.mydemoapps.cnstnt.Constant;
import com.mydemoapps.mapPack.GPSTracker;

/**
 * Created by Mayor kanodiya on 04-01-2016.
 */
public class Map extends Fragment {

    GPSTracker gps;
    private GoogleMap googleMap;
    private CameraPosition cameraPosition;
    private Marker marker_current_location;
    private Marker marker_;
    private Marker marker_destination;
    private Marker marker_search;

    Circle mapCircle=null;



    private SupportMapFragment fragment_map;


    public Map() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        //		Constant.login_way ="LOCATION";
        fragment_map = (SupportMapFragment) fm.findFragmentById(R.id.location_map);
        if (fragment_map == null)
        {
            fragment_map = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.location_map, fragment_map).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        gps = new GPSTracker(getActivity());
        if (gps.canGetLocation()) {

        }
        else {
            gps.showSettingsAlert();
        }
        Constant.Latitude = gps.getLatitude();
        Constant.Longitude = gps.getLongitude();
        System.out.print(Constant.Latitude+"  "+Constant.Longitude);
        initialize();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgmnt_map, container, false);






/*
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Geocoder geocoder = new Geocoder(getActivity());
                String addresse = "";

                try {
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addresses.size() > 0) {
                        StringBuilder str = new StringBuilder();
                        if (Geocoder.isPresent()) {
                            Address returnAddress = addresses.get(0);
                            //   String more = returnAddress.getFeatureName();
                            String more0 = returnAddress.getAddressLine(0);
                            String more1 = returnAddress.getAddressLine(1);
                            String more2 = returnAddress.getAddressLine(2);
                            String more3 = returnAddress.getAddressLine(3);

                            //   str.append(more0 + ", " + more1 + ", " + more2+", "+more3);
                            if (more3 == null || more3.equalsIgnoreCase("") || more3.length() == 0) {
                                str.append(more0 + ", " + more1 + ", " + more2);
                            } else {
                                str.append(more0 + ", " + more1 + ", " + more2 + ", " + more3);
                            }
                            addresse = str.toString().toUpperCase();

                        }
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                marker_ = googleMap.addMarker(new MarkerOptions()
                        .snippet(addresse)
                        .position(latLng));

            }
        });

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.equals(marker_current_location) || marker.equals(marker_destination)) {
                   *//* String url = getDirectionsUrl2(new LatLng(Constant.Latitude, Constant.Longitude), new LatLng(marker_destination.getPosition().latitude, marker_destination.getPosition().longitude));
                    DownloadTask downloadTask = new DownloadTask();
                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                    return true;*//*
                    return false;
                } else {

                    String url = getDirectionsUrl(new LatLng(Constant.Latitude, Constant.Longitude), new LatLng(marker.getPosition().latitude, marker.getPosition().longitude));
                    DownloadTask downloadTask = new DownloadTask();
                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                    //    Toast.makeText(MapActivity.this, marker.getSnippet(), 1).show();
                    return true;
                }


            }
        });*/


        return rootView;
    }
    @SuppressLint("NewApi") private void initialize() {
        // TODO Auto-generated method stub
        if (googleMap == null) {
            ////////////// inti map


          //  googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.real_time)).getMap();
            googleMap = fragment_map.getMap();

            System.out.print("map initiat");
            //  googleMap.clear();
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


            //Moving Camera to a Location with animation
            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(Constant.Latitude, Constant.Longitude)).zoom(12)
                    .build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));


            // create marker add marker
            marker_current_location = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Constant.Latitude, Constant.Longitude)));


                  addCircleToMap(2);
           /* Circle circle = googleMap.addCircle(new CircleOptions()
                    .center(new LatLng(-33.87365, 151.20689))
                    .radius(10000)
                    .strokeColor(Color.RED)
                    .fillColor(Color.BLUE));*/

            //Showing Current Location
            //  googleMap.setMyLocationEnabled(true);

            //Compass Functionality
            //   googleMap.getUiSettings().setCompassEnabled(true);

            //Zooming Buttons
            //  googleMap.getUiSettings().setZoomControlsEnabled(true);

            //Zooming Functionality
            //  googleMap.getUiSettings().setZoomGesturesEnabled(true);

            //My Location Button
            //  googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            //Map Rotate Gesture
            //  googleMap.getUiSettings().setRotateGesturesEnabled(true);

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }



    private void addCircleToMap(int miles) {

        // circle settings
        double radiusM = miles * 1609.34;// your radius in meters
        int radiusInt = (int) radiusM;
        LatLng _latLong = new LatLng(Constant.Latitude,Constant.Longitude);

        CircleOptions circleOptions = new CircleOptions().center(_latLong) // set center
                .radius(radiusInt) // set radius in meters
                .strokeColor(0x10000000).strokeWidth(5).
                        fillColor(0x55fe9f9f);
        // Fill color of the circle
        // 0x represents, this is an hexadecimal code
        // 55 represents percentage of transparency. For 100% transparency, specify 00.
        // For 0% transparency ( ie, opaque ) , specify ff
        // The remaining 6 characters(00ff00) specify the fill color

        if (mapCircle != null) {
            mapCircle.remove();
        }
        mapCircle = googleMap.addCircle(circleOptions);

      /*  googleMap.addGroundOverlay(new GroundOverlayOptions().
                image(bmD).
                position(_latLong, radiusInt * 2, radiusInt * 2).
                transparency(0.6f));*/
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
