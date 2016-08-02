package com.shonejin.smartmarket.Model;

import java.util.List;

/**
 * Created by shone on 7/31/16.
 */

public class ShoppingList {
    // | shoppinglist_id | user_id | ASIN | title | imgurl
    public String shoppinglist_id;
    public String user_id;
    public List<Product> productList;
}
