package com.shonejin.smartmarket.Model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shone on 7/31/16.
 */
public class Coupon implements Serializable {
    // | coupon_id | user_id | description | expire_date |
    public String coupon_id;
    public String description;
    public String expire_date;
    public Coupon(String coupon_id, String description) {
        this.coupon_id = coupon_id;
        this.description = description;

    }
    public static Coupon fromJson(JSONObject jsn)
    {
        try
        {
            return new Coupon(
                    jsn.getString("coupon_id"),
                    jsn.getString("description")
            );
        }
        catch (Exception e)
        {
            Log.e("Coupon", "fromJson: parsing error");
        }
        return null;
    }
}
