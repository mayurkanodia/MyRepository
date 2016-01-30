package com.mydemoapps.FragmentTabHost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mydemoapps.R;
import com.mydemoapps.frgmnt.CameraImageUpload;
import com.mydemoapps.frgmnt.Facebk;
import com.mydemoapps.frgmnt.ListData;
import com.mydemoapps.frgmnt.Map;
import com.mydemoapps.slidertab.TabFgmnt;

/**
 * Created by android on 29/1/16.
 */
public class TabHostFragments extends AppCompatActivity {
    private ImageView menu_btn;
    private TextView hdr_title;
    private ImageView hdr_cart;
    private ImageView hdr_fltr;


    private Button fb,map,list,cam,tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhostfrgment);
        /////
        menu_btn=(ImageView)findViewById(R.id.tgl_menu);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //////
        hdr_cart=(ImageView)findViewById(R.id.hdr_cart);
        hdr_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TabHostFragments.this, "cart btn", Toast.LENGTH_SHORT).show();
            }
        });
        ///////
        hdr_fltr=(ImageView)findViewById(R.id.hdr_fltr);
        hdr_fltr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TabHostFragments.this, "filter btn", Toast.LENGTH_SHORT).show();
            }
        });
        hdr_title=(TextView)findViewById(R.id.hdr_title);
        hdr_title.setText("TAB HOST FRAGMENTS");
        ///////////

        map=(Button)findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(0);
                Toast.makeText(TabHostFragments.this, "map btn", Toast.LENGTH_SHORT).show();
            }
        });
        cam=(Button)findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(1);
                Toast.makeText(TabHostFragments.this, "cam, btn", Toast.LENGTH_SHORT).show();
            }
        });
        list=(Button)findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(2);
                Toast.makeText(TabHostFragments.this, "list btn", Toast.LENGTH_SHORT).show();
            }
        });

        fb=(Button)findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(3);
                Toast.makeText(TabHostFragments.this, "fb btn", Toast.LENGTH_SHORT).show();
            }
        });
        tab=(Button)findViewById(R.id.tab);
        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(4);
                Toast.makeText(TabHostFragments.this, "fb btn", Toast.LENGTH_SHORT).show();
            }
        });
        displayView(0);
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
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
