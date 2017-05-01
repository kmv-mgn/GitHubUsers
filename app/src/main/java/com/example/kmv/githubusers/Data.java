package com.example.kmv.githubusers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by work_android on 01.05.2017.
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
