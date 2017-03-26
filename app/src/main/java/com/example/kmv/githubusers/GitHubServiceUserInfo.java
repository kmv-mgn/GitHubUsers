package com.example.kmv.githubusers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by work_android on 24.03.2017.
 */

public interface GitHubServiceUserInfo {
    @GET("users/{user}")
    Call <User> getUsers(@Part("user") String user);
}
