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
import rx.functions.Action1;
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
       Observable<User> userInfo = gitHubServiceUserInfo.getUserInfo("kmv-mgn");
        userInfo.subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        System.out.println(user.getLogin());
                    }
                });







/*    //---------------------------------------------------------------------------------------------------
        //Получаем данные с гитхаба
        Call<List<User>> call = service.getUsers();

        //асинхронное получение данных с сервера
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                // Запрос выполнился успешно
                if (response.isSuccessful()) {
                    //отображение данных каждого пользователя в общем списке
                    for (User user : response.body()){
                        userList.add(user.getLogin());  //наполняем массив логинов для последующего считывания подробной информации
                        //System.out.println("Login="+user.getLogin()+" Name="+user.getName());

                    }

                } else {
                    onError();                          // Сервер вернул ошибку
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                onError();                              // Сервер вернул ошибку
            }

        });


        //сразу сортируем полученный список пользователей
        Collections.sort(userList, new Comparator <String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        //ВЫВОД ВСЕГО СПИСКА пользователей
        System.out.println("ВЫВОД ВСЕГО СПИСКА пользователей");
        for (int i=0; i<userList.size(); i++){
            System.out.println("Login="+userList.get(i));
        }

        //отображение данных каждого пользователя в общем списке (отсортированном)
        System.out.println("Отсортированный вывод:");
        for (User user : users){
            System.out.println("Login="+user.getLogin()+" Name="+user.getName());
        }
//-------------------------------------------------------------------------------------------------------
*/
/*        //Получаем данные с гитхаба о конкретном пользователе
        for (String user : userList){                   //цикл по списку пользователей
            Call <User> callUserInfo = serviceUserInfo.getUsers(user);

            //асинхронное получение данных с сервера
            callUserInfo.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    // Запрос выполнился успешно
                    if (response.isSuccessful()) {
                        users.add(response.body());     //добавляем полученную информацию о польз-ле в общий список
                    } else {
                        onError();                      // Сервер вернул ошибку
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    onError();                          // Сервер вернул ошибку
                }
            });

        }
*/

    }

    private void onError() {
        //Сервер вернул ошибку
    }
}
