package com.example.kmv.githubusers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by work_android on 24.03.2017.
 */

public interface GitHubService {
    @GET("users")
    Call <List<User>> getUsers();
}
