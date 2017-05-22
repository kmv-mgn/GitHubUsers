package com.example.kmv.githubusers.retrofit;

import com.example.kmv.githubusers.model.User;

import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Получение информации от апи гитхаба о конкретном пользователе
 */

public interface GitHubServiceUserInfo {
    @GET("users/{user}")
    Observable<User> getUserInfo(@Path("user") String user);
}
