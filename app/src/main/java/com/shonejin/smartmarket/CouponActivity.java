package com.shonejin.smartmarket;

/**
 * Created by oshinrawal on 8/2/16.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    EditText couponName, couponDesc, expDate;

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
                Log.e("Add coupon ", "clicked");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CouponActivity.this);
                alertDialogBuilder.setTitle("Coupon Saved!");
                alertDialogBuilder
                        .setMessage("Coupon " + couponName.getText() + " is saved!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                couponName.setText("");
                                couponDesc.setText("");
                                expDate.setText("");
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
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
