package com.shonejin.smartmarket.Model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shone on 7/31/16.
 */
public class Product implements Serializable {
    public double x;
    public double y;

    public String title;
    public String ASIN;
    public String price;


    public String image;
    public String description;
    public String customReview;

    public Product(double x, double y, String title, String ASIN, String price,
                    String image, String description, String customReview) {
        this.x = x;
        this.y = y;
        this.title = title;
        this.ASIN = ASIN;
        this.price = price;
        this.image = image;
        this.description = description;
        this.customReview = customReview;
    }

    public static Product fromJson(JSONObject jsn)
    {
        try
        {
            return new Product(
                    jsn.getDouble("x"),
                    jsn.getDouble("y"),
                    jsn.getString("title"),
                    jsn.getString("ASIN"),
                    jsn.getString("price"),
                    jsn.getString("image"),
                    jsn.getString("description"),
                    jsn.getString("customReview")
            );
        }
        catch (Exception e)
        {
            Log.e("Product", "fromJson: parsing error");
        }
        return null;
    }
}