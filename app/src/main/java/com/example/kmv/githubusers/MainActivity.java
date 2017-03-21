package com.example.kmv.githubusers;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    //адаптер фрагмента для пейджера
    private SectionsPagerAdapter mSectionsPagerAdapter;
    //@ViewPager, в котором будет размещаться содержание раздела
    private ViewPager mViewPager;
    private static final String TAG = "MyLog";
    private static final String TAG_Log = "TAG_Log";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем адаптер, который будет возвращать фрагмент для каждой активной вкладки.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        Log.d(TAG,"Прошли создание адаптера mSectionsPagerAdapter");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        //Log.d(TAG,"Нашли R.id.container в объект mViewPager");

        Log.d(TAG,"Установливаем для mViewPager адаптер mSectionsPagerAdapter");
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }
}
