package com.mydemoapps.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.mydemoapps.R;
import com.mydemoapps.model.Products;

/**
 * Created by Mayor kanodiya on 05-01-2016.
 */
public class MyOrderAdapter extends BaseAdapter {
    ArrayList<Products> slist = new ArrayList<Products>();
    Activity context;

    public MyOrderAdapter(ArrayList<Products> slist, Activity context) {
        this.slist = slist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return slist.size();
    }

    @Override
    public Object getItem(int position) {
        return slist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {

        public TextView prodect_price = null;
        public TextView prodect_name = null;
        public ImageView prodect_images= null;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        final ViewHolder view_holder;
        if (view == null) {

            view_holder = new ViewHolder();


            view = inflater.inflate(R.layout.list_row, null);

            view_holder.prodect_images = (ImageView) view.findViewById(R.id.profile_pic_Img);
            view_holder.prodect_name = (TextView) view.findViewById(R.id.user_name);
            view_holder.prodect_price = (TextView) view.findViewById(R.id.price);


            view.setTag(view_holder);

        } else {
            view_holder = (ViewHolder) view.getTag();
        }

        final Products trackBean = slist.get(position);
        view_holder.prodect_images.setImageResource(R.mipmap.ic_launcher);
        view_holder.prodect_name.setText(trackBean.getIteam_name());
        view_holder.prodect_price.setText(trackBean.getIteam_price());


        return view;
    }
}


