package com.example.kmv.githubusers;

import java.util.ArrayList;
import rx.Observable;

import retrofit2.http.GET;

/**
 * Created by work_android on 24.03.2017.
 */

public interface GitHubService {
    @GET("users")
    Observable<ArrayList<User>> getUsers();
}
