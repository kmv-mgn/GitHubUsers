package com.example.kmv.githubusers.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kmv.githubusers.fragment.PlaceholderFragment;

/**
 * Created by kmv on 21.03.2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_NUM_0 = 0;    //вкладка  A-H
    private static final int TAB_NUM_1 = 1;    //вкладка  I-P
    private static final int TAB_NUM_2 = 2;    //вкладка  Q-Z
    private static final String TAB_TITLE_0 = "A-H";    //вкладка  A-H
    private static final String TAB_TITLE_1 = "I-P";    //вкладка  I-P
    private static final String TAB_TITLE_2 = "Q-Z";    //вкладка  Q-Z


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_NUM_0: return TAB_TITLE_0;
            case TAB_NUM_1: return TAB_TITLE_1;
            case TAB_NUM_2: return TAB_TITLE_2;
        }
        return null;
    }
}
