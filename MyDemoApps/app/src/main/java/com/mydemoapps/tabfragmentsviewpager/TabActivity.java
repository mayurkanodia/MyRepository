package com.mydemoapps.tabfragmentsviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mydemoapps.R;

/**
 * Created by android on 29/1/16.
 */
public class TabActivity extends AppCompatActivity {
    private ImageView menu_btn;
    private TextView hdr_title;
    private ImageView hdr_cart;
    private ImageView hdr_fltr;

    public static ViewPager mPager;
    TabActivityFragmentAdapter fragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabactivity);




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
                Toast.makeText(TabActivity.this, "cart btn", Toast.LENGTH_SHORT).show();
            }
        });
        ///////
        hdr_fltr=(ImageView)findViewById(R.id.hdr_fltr);
        hdr_fltr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TabActivity.this, "filter btn", Toast.LENGTH_SHORT).show();
            }
        });
        hdr_title=(TextView)findViewById(R.id.hdr_title);

        ///////////

        /** Getting a reference to ViewPager from the layout */
        mPager = (ViewPager)findViewById(R.id.create_game_pager);
        fragmentPagerAdapter = new TabActivityFragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(fragmentPagerAdapter);
        hdr_title.setText(fragmentPagerAdapter.getPageTitle(0));
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                hdr_title.setText(fragmentPagerAdapter.getPageTitle(position));
                Toast.makeText(TabActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }
}
