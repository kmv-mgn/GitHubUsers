package com.example.kmv.githubusers.data;

import com.example.kmv.githubusers.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Список пользователей GitHub
 */

public class Data {

    public static List<User> usersList = new ArrayList<User>(){};

    public static void addData (User user) {
        usersList.add(user);
    }

    public static void clearData (){
        usersList.clear();
    }
}
