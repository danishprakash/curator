package com.example.android.curator;

/**
 * Created by Danish on 7/24/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class pageAdapter extends FragmentPagerAdapter {
    int numOfTabs;

    public pageAdapter(FragmentManager manager, int numOfTabs) {
        super(manager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                frag1 tab1 = new frag1();
                return tab1;

            case 1:
                frag2 tab2 = new frag2();
                return tab2;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return numOfTabs;
    }
}
