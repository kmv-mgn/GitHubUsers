package com.example.kmv.githubusers.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kmv.githubusers.GitHubService;
import com.example.kmv.githubusers.GitHubServiceUserInfo;
import com.example.kmv.githubusers.R;
import com.example.kmv.githubusers.SectionsPagerAdapter;
import com.example.kmv.githubusers.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;             //адаптер фрагмента для пейджера
    private ViewPager mViewPager;                                   //@ViewPager, в котором будет размещаться содержание раздела
    private static final String TAG = "MyLog";
    private static final String TAG_Log = "TAG_Log";
    private static final String URL = "https://api.github.com/";    //базовый URL

    private ArrayList<String> userList = new ArrayList<String>();    //для хранения списка логинов пользователей
    //private List<User> users = new ArrayList<User>();                //для хранения полученного с сервера списка объектов типа User

        private Gson gson = new GsonBuilder().create();                 //Инициализация объекта Gson


        private Retrofit retrofit = new Retrofit.Builder()              //Инициализация объекта Retrofit
                .baseUrl(URL)       //определение базововго url
                .addConverterFactory(GsonConverterFactory.create(gson)) //конвертирование в json-тип
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        private GitHubService gitHubService = retrofit.create(GitHubService.class);   //Инициализация интерфейса для получения списка всех пользователей
        //Инициализация интерфейса для получения информации о конкретном пользователе
        private GitHubServiceUserInfo gitHubServiceUserInfo = retrofit.create(GitHubServiceUserInfo.class);

 //--------------------------------------------------------------------------------------------------
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

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        Log.d(TAG,"Связываем tabLayout и mViewPager");
        tabLayout.setupWithViewPager(mViewPager);

//---------------------------------------------------------------------------------------------------
       /*Observable<ArrayList<User>> usersGitHub = gitHubService.getUsers();      //получили список пользователей с апи
        usersGitHub.subscribeOn(Schedulers.newThread())
                    .subscribe();*/
       // usersGitHub();
      //  userInfo("kmv-mgn");
      //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       exampleUserInfo();




    }

    //--------------------------------------------------------------------------------
    //  ------------              мои методы            ------------------------------
    //--------------------------------------------------------------------------------
/*
    private Observable<ArrayList<User>> usersGitHub(){          //получаем объект Observable от апи со списком
                                                                //всех открытых в апи пользователей
        Observable<ArrayList<User>> usersGitHub = gitHubService.getUsers();

        usersGitHub.subscribeOn(Schedulers.newThread())        //подписываем все действия в отдельный поток
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<User>>() {
                    @Override
                    public void call(ArrayList<User> users) {
                        for (User user: users) {
                            Log.d(TAG, "Логин, который получили с апи" + user.getLogin());
                        }
                    }
                });
        return usersGitHub;
    }

    private Observable<User> userInfo(String userLogin){        //получаем объект Observable от апи с
                                                                //информацией по конкретному пользователю
        Observable<User> userInfo = gitHubServiceUserInfo.getUserInfo(userLogin);
       userInfo.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Log.d(TAG,"Количество подписчиков"+user.getFollowers());
                    }
                });
        return userInfo;
    }
*/
    private Observable<ArrayList<User>> exampleUserInfo(){        //Общий Observable от апи с
        //информацией по конкретному пользователю
        Observable<ArrayList<User>> exampleUserInfo = gitHubService.getUsers();  //Получает список пользователей с данными
        exampleUserInfo
                .flatMap(new Func1<List<User>, Observable<User>>() {
                    @Override
                    public Observable<User> call(List<User> users) {
                        return Observable.from(users);
                    }
                })
                .flatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(User user) {
                        return gitHubServiceUserInfo.getUserInfo(user.getLogin());            //userInfo(user.getLogin());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable> ()
                        {
                            @Override
                            public void call(Throwable throwable) {
                                onError();
                            }
                        })
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Log.d(TAG,"Количество подписчиков у "+user.getLogin()+" = "+user.getFollowers());
                    }
                    });

        return exampleUserInfo;
    }

    private void onError() {
        Log.d(TAG,"Ууууупсс.. ошибочка вышла..");
    }


}
