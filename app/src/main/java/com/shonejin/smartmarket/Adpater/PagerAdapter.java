package com.shonejin.smartmarket.Adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shonejin.smartmarket.HomeFragment;
import com.shonejin.smartmarket.NavigationFragment;
import com.shonejin.smartmarket.ProfileFragment;
import com.shonejin.smartmarket.ShoppingListFragment;
import com.shonejin.smartmarket.CouponFragment;

/**
 * Created by oshinrawal on 7/27/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                NavigationFragment navigationFragment = new NavigationFragment();
                return navigationFragment;
            case 2:
                ShoppingListFragment shoppingListFragment = new ShoppingListFragment();
                return shoppingListFragment;
            case 3:
                CouponFragment couponFragment = new CouponFragment();
                return couponFragment;
            case 4:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
