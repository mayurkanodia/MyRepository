package com.mydemoapps.frgmnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mydemoapps.FragmentTabHost.TabHostFragments;
import com.mydemoapps.R;
import com.mydemoapps.slidertab.TabFgmnt;
import com.mydemoapps.model.FragmentDrawer;
import com.mydemoapps.tabfragmentsviewpager.TabActivity;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    public static FragmentDrawer drawerFragment;

    private ImageView menu_btn;
    private TextView hdr_title;
    private ImageView hdr_cart;
    private ImageView hdr_fltr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

 /////
        menu_btn=(ImageView)findViewById(R.id.tgl_menu);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerFragment.openDrawer();
            }
        });
      //////
        hdr_cart=(ImageView)findViewById(R.id.hdr_cart);
        hdr_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Toast.makeText(MainActivity.this,"cart btn",Toast.LENGTH_SHORT).show();
            }
        });
        ///////
        hdr_fltr=(ImageView)findViewById(R.id.hdr_fltr);
        hdr_fltr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"filter btn",Toast.LENGTH_SHORT).show();
            }
        });
        hdr_title=(TextView)findViewById(R.id.hdr_title);

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
   //     drawerFragment.setDrawerState(false);

        // display the first navigation drawer view on app launch
        displayView(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       Intent intent;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent = new Intent(MainActivity.this, TabHostFragments.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Setting action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
           return true;
        }

        if(id == R.id.my_action){
            Toast.makeText(getApplicationContext(), "My action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.my_action2){
            Toast.makeText(getApplicationContext(), "My action2 is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new Map();
                title = getString(R.string.map);
                hdr_title.setText(R.string.map);
                break;
            case 1:
                fragment = new CameraImageUpload();
                title = getString(R.string.camera);
                hdr_title.setText(R.string.camera);

                break;
            case 2:
                fragment = new ListData();
                title = getString(R.string.list);
                hdr_title.setText(R.string.list);

                break;
            case 3:
                fragment = new Facebk();
                title = getString(R.string.facebook);
                hdr_title.setText(R.string.facebook);

                break;
            case 4:
                fragment = new TabFgmnt();
                title = getString(R.string.tab);
                hdr_title.setText(R.string.tab);

                break;
            case 5:

                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                startActivity(intent);

                break;


            default:
                break;
        }

        if (fragment != null && position !=5) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}