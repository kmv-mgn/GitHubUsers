package com.example.kmv.githubusers.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.kmv.githubusers.CardAdapter;
import com.example.kmv.githubusers.Const;
import com.example.kmv.githubusers.Data;
import com.example.kmv.githubusers.GitHubService;
import com.example.kmv.githubusers.GitHubServiceUserInfo;
import com.example.kmv.githubusers.R;
import com.example.kmv.githubusers.RetrofitGit;
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

    //public RetrofitGit retrofitGit;
    private SectionsPagerAdapter mSectionsPagerAdapter;             //адаптер фрагмента для пейджера
    private ViewPager mViewPager;                                   //@ViewPager, в котором будет размещаться содержание раздела
    private static final String TAG = "MyLog";
    //public static List<User> usersList = new ArrayList<User>();                //для хранения полученного с сервера списка объектов типа User




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

       /* //устанавливаем RecyclerView и CardView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setAdapter(mCardAdapter);
*/
//---------------------------------------------------------------------------------------------------
        //создаем объект ретрофита Retrofit
        //retrofitGit = new RetrofitGit();
        RetrofitGit.initGitHub();           //создается объект ретрофита, выполнятеся запрос к серверу на список пользователей
                                            // и сохраняется в списке класса Data
        Log.d(Const.TAG,"Готов первоначальный список пользователей! Первый в списке:"+ Data.usersList.get(0).getLogin());

        for (int i=0; i<10; i++){
            RetrofitGit.getUserInfo(Data.usersList.get(i).getLogin());

        }

    /*    //1й вариант:
        usersList.clear();
        usersGitHub();      //получаем список польз-лей, а потом полную информацию о каждом по логину из списка
         for (User user : usersList){
            Log.d(TAG,"У пользователя "+user.getLogin()+" подписчиков = "+user.getFollowers());
        }
*/
 /*      //или 2й вариант:
        usersList.clear();
        getUserListGitHub();    //получаем список всех пользователей с api

        for (int i=0; i<10; i++){
            Log.d(TAG,"usersList: "+i+" человечек из списка: "+usersList.get(i).getLogin()+" = "+usersList.get(i).getFollowers());
            getUserInfoGitHub(usersList.get(i).getLogin());     //получаем полную информацию о пользователе по логину
        }


*/


        Toast.makeText(this, "Конец!", Toast.LENGTH_LONG).show();
    }

    //--------------------------------------------------------------------------------
    //  ------------              мои методы            ------------------------------
    //--------------------------------------------------------------------------------
/*//для варианта 2
    private Observable<ArrayList<User>> getUserListGitHub(){        //Получение списка пользователей от api
        //информацией по конкретному пользователю

        Observable<ArrayList<User>> userList = gitHubService.getUsers();  //Получает список пользователей с небольшим набором свойств пользователей
        Subscriber<ArrayList<User>> subscriber = new Subscriber<ArrayList<User>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"----Выполнение метода onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"-----Ошибка onError: "+e);
            }
            @Override
            public void onNext(ArrayList<User> users) {
                Log.d(TAG,"-----Проверка первого элемента: "+users.get(1).getLogin()+" = "+users.get(1).getFollowers());
                usersList = users;
            }
        };
        userList.subscribe(subscriber);
        return userList;
    }
//для варианта 2
    private Observable<User> getUserInfoGitHub(final String userLogin){        //Получение информации о пользователе
        //информацией по конкретному пользователю
        Observable<User> userInfo = gitHubServiceUserInfo.getUserInfo(userLogin);
        Subscriber<User> subscriber = new Subscriber<User>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"-+-+-Выполнение метода onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"-+-+-Ошибка onError: "+e);
            }
            @Override
            public void onNext(User user) {
                Log.d(TAG,"-+-+-Количество подписчиков у "+user.getLogin()+" = "+user.getFollowers());
                //mCardAdapter.addData(user); //usersList = users;
            }
        };
        userInfo.subscribe(subscriber);
        return userInfo;
    }
*/
//для варианта 1
/*  private Observable<ArrayList<User>> usersGitHub(){        //Получение информации о пользователях от api
        //информацией по конкретному пользователю
        Observable<ArrayList<User>> exampleUserInfo = gitHubService.getUsers();  //Получает список пользователей с небольшим набором свойств пользователей
        exampleUserInfo
                .flatMap(new Func1<List<User>, Observable<User>>() {
                    @Override
                    public Observable<User> call(List<User> users) {                    //преобразуем каждый "выпущенный" User в отдельный observable
                        return Observable.from(users);
                    }
                })
                .concatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(User user) {
                        return gitHubServiceUserInfo.getUserInfo(user.getLogin());  //получаем информацию о пользователе по его логину

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable> ()
                        {
                            @Override
                            public void call(Throwable throwable) {
                                Log.d(TAG,"Ошибка  "+ throwable);
                                onError();
                            }
                        })
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"-+-+-Выполнение метода onCompleted");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"-+-+-Ошибка onError: "+e);
                    }
                    @Override
                    public void onNext(User user) {
                        Log.d(TAG,"-+-+-Выполнение метода onNext");
                        usersList.add(user);
                    }
                });

        return exampleUserInfo;
    }
*/


}
