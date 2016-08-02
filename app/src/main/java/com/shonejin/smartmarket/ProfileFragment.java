package com.shonejin.smartmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.shonejin.smartmarket.R;

/**
 * Created by oshinrawal on 7/27/16.
 */
public class ProfileFragment extends Fragment {

    private String[] genderArray;
    private ImageView imageView;
    private Spinner genderSpinner;
    private Button membershipCardButton;
    private Button couponButton;
    ArrayAdapter<String> spinnerAdapter;

    public ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        genderArray = new String[]{"Female", "Male", "Other"};
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imageView = (ImageView) view.findViewById(R.id.profileImage);
        genderSpinner = (Spinner) view.findViewById(R.id.genderSpinner);
        membershipCardButton = (Button) view.findViewById(R.id.membershipCardButton);
        couponButton = (Button) view.findViewById(R.id.addCouponButton);
        imageView.setImageResource(R.drawable.profileimage);
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, genderArray);
        genderSpinner.setAdapter(spinnerAdapter);
        membershipCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MembershipCardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.e("Membership card button ", "Clicked");
                startActivity(intent);
            }
        });
        couponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CouponActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
