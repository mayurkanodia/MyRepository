package com.mydemoapps.model;

import java.io.Serializable;

/**
 * Created by Mayor kanodiya on 05-01-2016.
 */
public class Products implements Serializable {

    public String iteam_name;

    public String getIteam_price() {
        return iteam_price;
    }

    public void setIteam_price(String iteam_price) {
        this.iteam_price = iteam_price;
    }

    public String getIteam_name() {
        return iteam_name;
    }

    public void setIteam_name(String iteam_name) {
        this.iteam_name = iteam_name;
    }

    public String iteam_price;
}
