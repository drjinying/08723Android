package com.shonejin.smartmarket;

/**
 * Created by oshinrawal on 8/2/16.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * Created by oshinrawal on 7/28/16.
 */
public class CouponActivity extends AppCompatActivity {
    private Toolbar toolbar;
    Button addCouponBtn;
    EditText couponName, couponDesc, expDate;
    HttpResponse response;
    String name, description, expiryDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        toolbar = (Toolbar) findViewById(R.id.toolbarCoupon);
        addCouponBtn = (Button) findViewById(R.id.addCouponButton);
        couponName = (EditText) findViewById(R.id.couponNameEditText);
        couponDesc = (EditText) findViewById(R.id.descEditText);
        expDate = (EditText) findViewById(R.id.dateEditText);
        addCouponBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(couponName.getText() != null && couponName.getText().length() != 0) {
                    if (couponDesc != null && couponDesc.length() != 0) {
                        if (expDate.getText() != null && expDate.getText().length() != 0) {
                            name = couponName.getText().toString();
                            description = couponDesc.getText().toString();
                            expiryDate = expDate.getText().toString();
                            new AddCoupon().execute();

                        } else {
                            getDialog("Error!", "Please provide expiry date.");
                        }
                    } else {
                        getDialog("Error!", "Please provide coupon description.");
                    }
                } else {
                    getDialog("Error!", "Please provide coupon name.");
                }


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getDialog(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CouponActivity.this);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AddCoupon extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(RestClient.BASE_URL+"/editCoupon");
            try {
                Log.e("Coupon Data", name + " " + description + " " + expiryDate);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("op","add"));
                nameValuePairs.add(new BasicNameValuePair("user_id",User.user_id));
                nameValuePairs.add(new BasicNameValuePair("coupon_id", name));
                nameValuePairs.add(new BasicNameValuePair("description", description));
                nameValuePairs.add(new BasicNameValuePair("expiry_id", expiryDate));
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
            getDialog("Coupon Added!", "Coupon " + name + " was added successfully");
            couponName.setText("");
            couponDesc.setText("");
            expDate.setText("");
        }
    }
}
