package com.example.kmv.githubusers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by work_android on 01.05.2017.
 */

public class RetrofitGit {

    public static Gson gson = new GsonBuilder().create();                 //Инициализация объекта Gson

    public static final String URL = "https://api.github.com/";    //базовый URL
    public static Retrofit retrofit = new Retrofit.Builder()              //Инициализация объекта Retrofit
            .baseUrl(URL)       //определение базововго url
            .addConverterFactory(GsonConverterFactory.create(gson)) //конвертирование в json-тип
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    public static GitHubService gitHubService = retrofit.create(GitHubService.class);   //Инициализация интерфейса для получения списка всех пользователей
    //Инициализация интерфейса для получения информации о конкретном пользователе
    public static GitHubServiceUserInfo gitHubServiceUserInfo = retrofit.create(GitHubServiceUserInfo.class);

    //----------------------------------------------------------------------------------------------
    //получение первоначального списка пользователей
    public static void initGitHub(){
        Data.clearData();       //очистили список пользователей Data
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
        //информацией по конкретному пользователю

        Observable<ArrayList<User>> userList = gitHubService.getUsers();  //Получает список пользователей с небольшим набором свойств пользователей
        Subscriber<ArrayList<User>> subscriber = new Subscriber<ArrayList<User>>() {
            @Override
            public void onCompleted() {
                Log.d(Const.TAG,"----Выполнение метода onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(Const.TAG,"-----Ошибка onError: "+e);
            }
            @Override
            public void onNext(ArrayList<User> users) {
                Log.d(Const.TAG,"-----Проверка первого элемента: "+users.get(1).getLogin()+" = "+users.get(1).getFollowers());
                for (User user : users)
                Data.addData(user);             //добавление пользователей в список класса Data
            }
        };
        userList.subscribe(subscriber);
        return userList;
    }

    //----------------------------------------------------------------------------------------------
    //получение полной информации от апи о пользователе
    private static Observable<User> getUserInfoGitHub(final String userLogin){        //Получение информации о пользователе
        //информацией по конкретному пользователю
        Observable<User> userInfo = gitHubServiceUserInfo.getUserInfo(userLogin);
        Subscriber<User> subscriber = new Subscriber<User>() {
            @Override
            public void onCompleted() {
                Log.d(Const.TAG,"-+-+-Выполнение метода onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.d(Const.TAG,"-+-+-Ошибка onError: "+e);
            }
            @Override
            public void onNext(User user) {
                Log.d(Const.TAG,"-+-+-Количество подписчиков у "+user.getLogin()+" = "+user.getFollowers());
                //adapter.addDataCard(user);
                DetailsData.addData(user);
            }
        };
        userInfo.subscribe(subscriber);
        return userInfo;
    }

    private void onError() {
        Log.d(Const.TAG,"Ууууупсс.. ошибочка вышла..");
    }
}
