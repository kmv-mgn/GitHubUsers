package com.example.kmv.githubusers.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kmv.githubusers.data.Data;
import com.example.kmv.githubusers.R;
import com.example.kmv.githubusers.retrofit.RetrofitGit;
import com.example.kmv.githubusers.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

   SectionsPagerAdapter mSectionsPagerAdapter;             //адаптер фрагмента для пейджера
   ViewPager mViewPager;                                   //@ViewPager, в котором будет размещаться содержание раздела


 //--------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем адаптер, который будет возвращать фрагмент для каждой активной вкладки.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//---------------------------------------------------------------------------------------------------

        RetrofitGit.initGitHub();           //создается объект ретрофита, выполнятеся запрос к серверу на список пользователей
                                            // и сохраняется в списке класса Data

        //создается объект ретрофита, выполнятеся запрос к серверу на подробные сведения о конкретном пользователе из списка
        // и сохраняется в списке класса DetailsData
        for (int i=0; i<10; i++){
            RetrofitGit.getUserInfo(Data.usersList.get(i).getLogin());
        }
    }
}
