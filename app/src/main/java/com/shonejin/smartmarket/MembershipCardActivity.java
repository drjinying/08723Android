package com.shonejin.smartmarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shonejin.smartmarket.R;

/**
 * Created by oshinrawal on 7/28/16.
 */
public class MembershipCardActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView cardImageView;
    private boolean buttonEnable = false;
    Button editButton;
    EditText membershipCardNumber;
    Button doneButton;
    String cardNumberStr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_card);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        cardImageView = (ImageView) findViewById(R.id.imageView);
        editButton = (Button) findViewById(R.id.editMembershipCardButton);
        membershipCardNumber = (EditText) findViewById(R.id.membershipCardNumber);
        doneButton = (Button) findViewById(R.id.doneButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        membershipCardNumber.setEnabled(false);
        doneButton.setVisibility(View.GONE);
        /*mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membershipCardNumber.setEnabled(true);
                doneButton.setVisibility(View.VISIBLE);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                membershipCardNumber.setEnabled(false);
                doneButton.setVisibility(View.GONE);
            }
        });
        getSupportActionBar().setTitle("SmartMarket");
        cardImageView.setImageResource(R.drawable.card);
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
