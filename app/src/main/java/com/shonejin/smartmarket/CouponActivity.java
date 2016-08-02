package com.shonejin.smartmarket;

/**
 * Created by oshinrawal on 8/2/16.
 */
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by oshinrawal on 7/28/16.
 */
public class CouponActivity extends AppCompatActivity {
    private Toolbar toolbar;
    Button addCouponBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        toolbar = (Toolbar) findViewById(R.id.toolbarCoupon);
        addCouponBtn = (Button) findViewById(R.id.addCouponButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
