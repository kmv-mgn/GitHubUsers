package com.example.kmv.githubusers;

import android.app.Application;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**  УДАЛИТЬ ПОТОМ ЗА НЕНАДОБНОСТЬЮ!
 * Created by work_android on 26.03.2017.
 */

public class GetDataGitHub {

    private static final String URL = "https://api.github.com/";    //базовый URL

    private ArrayList<String> userList = new ArrayList<String>();    //для хранения списка логинов пользователей

    private List<User> users = new ArrayList<User>();                //для хранения полученного с сервера списка объектов типа User

    private Gson gson = new GsonBuilder().create();                 //Инициализация объекта Gson
    //Инициализация объекта Retrofit
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)       //определение базововго url
            .addConverterFactory(GsonConverterFactory.create(gson)) //конвертирование в json-тип
            .build();

    private GitHubService service = retrofit.create(GitHubService.class);   //Инициализация интерфейса для получения списка всех пользователей
    //Инициализация интерфейса для получения информации о конкретном пользователе
    //private GitHubServiceUserInfo serviceUserInfo = retrofit.create(GitHubServiceUserInfo.class);

    public List<String> getUserList(){
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
        return userList;
    }
    ///////////////////////////////////////////////////////////////


    private void onError() {
        //Сервер вернул ошибку
    }

}
