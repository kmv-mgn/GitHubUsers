package com.example.kmv.githubusers.retrofit;

import com.example.kmv.githubusers.model.User;

import java.util.ArrayList;
import rx.Observable;

import retrofit2.http.GET;

/**
 * Получение списка пользователей от апи гитхаба
 */

public interface GitHubService {
    @GET("users")
    Observable<ArrayList<User>> getUsers();
}
