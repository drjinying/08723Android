package com.shonejin.smartmarket.Model;

import com.shonejin.smartmarket.User;

import java.util.List;

/**
 * Created by shone on 7/31/16.
 */

public class ShoppingList {
    // | shoppinglist_id | user_id | ASIN | title | imgurl
    public static String shoppinglist_id = "1";
    public String user_id = User.user_id;
    public List<Product> productList;
}
