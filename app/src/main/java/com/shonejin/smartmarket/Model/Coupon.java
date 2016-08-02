package com.shonejin.smartmarket.Model;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by shone on 7/31/16.
 */
public class Coupon {
    // | coupon_id | user_id | description | expire_date |
    public String coupon_id;
    public String user_id;
    public String description;
    public Date expire_date;
}
