package com.example.kmv.githubusers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by kmv on 21.03.2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "MyLog";

    //конструктор
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //отображает указанный фрагмент
    @Override
    public Fragment getItem(int position) {
        return null; //PlaceholderFragmen
    }

    //определяет количество листов пейджера
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: Log.d(TAG,"Создана надпись страницы пейджера 0");
                return "A-H";
            case 1: Log.d(TAG,"Создана надпись страницы пейджера 1");
                return "I-P";
            case 2: Log.d(TAG,"Создана надпись страницы пейджера 2");
                return "Q-Z";
        }
        return null;
    }
}
