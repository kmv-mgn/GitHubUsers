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
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by kmv on 30.03.2017.
 */

public class RetrofitGitHub {

    private static final String URL = "https://api.github.com/";    //базовый URL
    private static final String TAG = "MyLog";

    private static Observable<ArrayList<User>> observableRetrofit;
    private static BehaviorSubject<ArrayList<User>> observableModelsList;
    private static Subscription subscription;

    public RetrofitGitHub() {                       //конструктор
    }


    public static void init() {                     //инициализация объектов ретрофита для запроса к api
        Log.d(TAG, "init");

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Gson gson = new GsonBuilder().create();                //Инициализация объекта Gson

        Retrofit retrofit = new Retrofit.Builder()              //Инициализация объекта Retrofit
                .baseUrl(URL)                                   //определение базововго url
                .addConverterFactory(GsonConverterFactory.create(gson)) //конвертирование в json-тип
                .addCallAdapterFactory(rxAdapter)
                .build();

        GitHubService service = retrofit.create(GitHubService.class);   //Инициализация интерфейса для получения списка всех пользователей
        //Инициализация интерфейса для получения информации о конкретном пользователе
        //private GitHubServiceUserInfo serviceUserInfo = retrofit.create(GitHubServiceUserInfo.class);

        observableRetrofit = service.getUsers();
    }

    //
    public static void resetModelsObservable() {
        observableModelsList = BehaviorSubject.create();

        if (subscription != null && !subscription.isUnsubscribed()) {   //отписываемся от
            subscription.unsubscribe();
        }
        subscription = observableRetrofit.subscribe(new Subscriber<ArrayList<User>>() {
            @Override
            public void onCompleted() {
                //do nothing
            }

            @Override
            public void onError(Throwable e) {
                observableModelsList.onError(e);
            }

            @Override
            public void onNext(ArrayList<User> models) {
                observableModelsList.onNext(models);
            }
        });
    }

    public static Observable<ArrayList<User>> getModelsObservable() {
        if (observableModelsList == null) {
            resetModelsObservable();
        }
        return observableModelsList;
    }



}
