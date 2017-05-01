package com.example.kmv.githubusers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by work_android on 02.05.2017.
 */

public class DetailsData {
    public static List<User> usersDetailList = new ArrayList<User>(){};

    public static void addData (User user) {
        usersDetailList.add(user);
    }

    public static void clearData (){
        usersDetailList.clear();
    }
}
