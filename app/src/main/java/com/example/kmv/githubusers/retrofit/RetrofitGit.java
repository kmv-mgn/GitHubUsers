package com.example.kmv.githubusers.retrofit;

import android.util.Log;

import com.example.kmv.githubusers.Const;
import com.example.kmv.githubusers.data.Data;
import com.example.kmv.githubusers.data.DetailsData;
import com.example.kmv.githubusers.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Получение и формирование данных о пользователях
 */

public class RetrofitGit {

    public static Gson gson = new GsonBuilder().create();

    public static final String URL = "https://api.github.com/";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    public static GitHubService gitHubService = retrofit.create(GitHubService.class);
    public static GitHubServiceUserInfo gitHubServiceUserInfo = retrofit.create(GitHubServiceUserInfo.class);

    //----------------------------------------------------------------------------------------------
    //получение первоначального списка пользователей
    public static void initGitHub(){
        Data.clearData();           //очистили список пользователей Data
        DetailsData.clearData(); //очистили список с подробной информацией о пользователе DetailsData
        getUserListGitHub();    //получаем список всех пользователей с api
    }

    //получение полной информации от апи о пользователе
    public static void getUserInfo(String login) {
        getUserInfoGitHub(login);
    }

    //----------------------------------------------------------------------------------------------
    //Получение списка пользователей от api
    public static Observable<ArrayList<User>> getUserListGitHub(){

        Observable<ArrayList<User>> userList = gitHubService.getUsers();  //Получает список пользователей с небольшим набором свойств пользователей
        Subscriber<ArrayList<User>> subscriber = new Subscriber<ArrayList<User>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                excepOnError(e);
            }
            @Override
            public void onNext(ArrayList<User> users) {
                for (User user : users)
                Data.addData(user);             //добавление пользователей в список класса Data
            }
        };
        userList.subscribe(subscriber);
        return userList;
    }

    //----------------------------------------------------------------------------------------------
    //получение полной информации от апи о пользователе
    public static Observable<User> getUserInfoGitHub(final String userLogin){
        //информацией по конкретному пользователю
        Observable<User> userInfo = gitHubServiceUserInfo.getUserInfo(userLogin);
        Subscriber<User> subscriber = new Subscriber<User>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                excepOnError(e);
            }
            @Override
            public void onNext(User user) {
                DetailsData.addData(user);
            }
        };
        userInfo.subscribe(subscriber);
        return userInfo;
    }

    private static void excepOnError(Throwable e) {
        Log.d(Const.TAG,"Ууууупсс.. ошибочка вышла: " + e);
    }
}
