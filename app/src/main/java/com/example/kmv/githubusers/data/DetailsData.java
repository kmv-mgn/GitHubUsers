package com.example.kmv.githubusers.data;

import android.util.Log;

import com.example.kmv.githubusers.Const;
import com.example.kmv.githubusers.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Получение различных списков пользователей (в зависимости от первой буквы имени)
 */

public class DetailsData {

    public static List<User> usersDetailList = new ArrayList<User>(){};

    public static void addData (User user) {
        usersDetailList.add(user);
    }

    public static void clearData (){
        usersDetailList.clear();
    }

    public static List<User> getDataAH (){      //список пользователей с именами, попадающими в диапазон А-Н
        List<User> dataAH = new ArrayList<User>();
        for (User user : usersDetailList){
            try{
                StringBuffer str = new StringBuffer(user.getName().substring(0,1).toUpperCase());
                                                            //проверяем первую букву имени на диапазон A-H
                if (user.getName()!=null & str.codePointAt(0)<= Const.lastLetterList1.codePointAt(0)) {
                    dataAH.add(user);
                }
            }
            catch (Exception e) {
                onError(e);
            }
        }
                                                                    //сортируем список по первой букве
        Collections.sort(dataAH, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.getName().substring(0,1).compareTo(o2.getName().substring(0,1));
            }
        });
        return dataAH;
    }

    public static List<User> getDataIP (){              //список пользователей с именами, попадающими в диапазон I-P
        List<User> dataIP = new ArrayList<User>();
        for (User user : usersDetailList){
            try{
                StringBuffer str = new StringBuffer(user.getName().substring(0,1).toUpperCase());
                                                            //проверяем первую букву имени на диапазон A-H
                if (user.getName()!=null & str.codePointAt(0)<=Const.lastLetterList2.codePointAt(0)
                        & str.codePointAt(0)>Const.lastLetterList1.codePointAt(0)) {
                    dataIP.add(user);
                }
            }
            catch (Exception e) {
                onError(e);
            }
        }
                                                                    //сортируем список по первой букве
        Collections.sort(dataIP, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.getName().substring(0,1).compareTo(o2.getName().substring(0,1));
            }
        });
        return dataIP;
    }

    public static List<User> getDataQZ (){          //список пользователей с именами, попадающими в диапазон I-P
        List<User> dataQZ = new ArrayList<User>();
        for (User user : usersDetailList){
            try{
                StringBuffer str = new StringBuffer(user.getName().substring(0,1).toUpperCase());
                                                        //проверяем первую букву имени на диапазон A-H
                if (user.getName()!=null & str.codePointAt(0)>Const.lastLetterList2.codePointAt(0)) {
                    Log.d(Const.TAG,"Первая буква имени "+str+" попадает в 3-й список ");
                    dataQZ.add(user);
                }
            }
            catch (Exception e) {
                onError(e);
            }
        }
                                                                    //сортируем список по первой букве
        Collections.sort(dataQZ, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.getName().substring(0,1).compareTo(o2.getName().substring(0,1));
            }
        });
        return dataQZ;
    }

    private static void onError (Exception e){
        Log.d(Const.TAG,"Ошибка в DetailsData: "+e);
    }
}
