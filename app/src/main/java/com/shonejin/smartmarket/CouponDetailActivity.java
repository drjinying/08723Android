package com.shonejin.smartmarket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shonejin.smartmarket.Model.Coupon;
import com.shonejin.smartmarket.Model.Product;

/**
 * Created by oshinrawal on 8/2/16.
 */
public class CouponDetailActivity extends AppCompatActivity {
    Button ok, delete;
    TextView txtView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_detail);
        Coupon item = (Coupon) getIntent().getSerializableExtra("Product");
        txtView = (TextView) findViewById(R.id.textView11);
        txtView.setText(item.coupon_id + "\n" + item.description + "\n" + item.expire_date);
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
                onBackPressed();
            }
        });
    }



}
