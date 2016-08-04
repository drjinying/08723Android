package com.shonejin.smartmarket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shonejin.smartmarket.Model.Product;
import com.shonejin.smartmarket.Model.ShoppingList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by oshinrawal on 8/4/16.
 */
public class ShoppingListDetail extends AppCompatActivity {
    String urlDelete = RestClient.BASE_URL +"/editShoppingList";
    Button ok, delete;
    TextView txtView;
    HttpResponse response;
    Product item;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_coupon_detail);
        item = (Product) getIntent().getSerializableExtra("Product");
        txtView = (TextView) findViewById(R.id.textView11);
        txtView.setText(item.title + "\n" + item.description);
        ok = (Button) findViewById(R.id.okBtn);
        delete = (Button) findViewById(R.id.button4);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteItem().execute();
                onBackPressed();
            }
        });
    }
    private class DeleteItem extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlDelete);
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("op","RM_ITEM"));
                nameValuePairs.add(new BasicNameValuePair("user_id",User.user_id));
                nameValuePairs.add(new BasicNameValuePair("shoppinglist_id", ShoppingList.shoppinglist_id));
                nameValuePairs.add(new BasicNameValuePair("ASIN", item.ASIN));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response = httpClient.execute(httpPost);
                Log.e("Response", response.toString());
            } catch (ClientProtocolException e) {
                Log.e("Exception", e.getMessage());
            } catch(IOException e) {
                Log.e("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getDialog("Item Deleted!", "Item " + item.title + " was deleted successfully");
        }
    }
    private void getDialog(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShoppingListDetail.this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
