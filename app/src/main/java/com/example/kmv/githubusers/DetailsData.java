package com.example.kmv.githubusers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    //Функция, формирующая список пользователей с именами, попадающими в диапазон А-Н
    public static List<User> getDataAH (){
        List<User> dataAH = new ArrayList<User>();
        for (User user : usersDetailList){
            try{
                StringBuffer str = new StringBuffer(user.getName().substring(0,1).toUpperCase());
                //проверяем первую букву имени на диапазон A-H
                if (user.getName()!=null & str.codePointAt(0)<=Const.lastLetterList1.codePointAt(0)) {
                    Log.d(Const.TAG,"Первая буква имени "+str+" попадает в 1-й список ");
                    dataAH.add(user);           //добавляем в результирующий список
                }
            }
            catch (Exception e) {       //обработка возникающей ошибки
                Log.d(Const.TAG,"Ошибка в цикле по usersDetailList класса DetailsData: "+e);
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
    //Функция, формирующая список пользователей с именами, попадающими в диапазон I-P
    public static List<User> getDataIP (){
        List<User> dataIP = new ArrayList<User>();
        for (User user : usersDetailList){
            try{
                StringBuffer str = new StringBuffer(user.getName().substring(0,1).toUpperCase());
                //проверяем первую букву имени на диапазон A-H
                if (user.getName()!=null & str.codePointAt(0)<=Const.lastLetterList2.codePointAt(0)
                        & str.codePointAt(0)>Const.lastLetterList1.codePointAt(0)) {
                    Log.d(Const.TAG,"Первая буква имени "+str+" попадает во 2-й список ");
                    dataIP.add(user);           //добавляем в результирующий список
                }
            }
            catch (Exception e) {       //обработка возникающей ошибки
                Log.d(Const.TAG,"Ошибка в цикле по usersDetailList класса DetailsData: "+e);
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
    //Функция, формирующая список пользователей с именами, попадающими в диапазон I-P
    public static List<User> getDataQZ (){
        List<User> dataQZ = new ArrayList<User>();
        for (User user : usersDetailList){
            try{
                StringBuffer str = new StringBuffer(user.getName().substring(0,1).toUpperCase());
                //проверяем первую букву имени на диапазон A-H
                if (user.getName()!=null & str.codePointAt(0)>Const.lastLetterList2.codePointAt(0)) {
                    Log.d(Const.TAG,"Первая буква имени "+str+" попадает в 3-й список ");
                    dataQZ.add(user);           //добавляем в результирующий список
                }
            }
            catch (Exception e) {       //обработка возникающей ошибки
                Log.d(Const.TAG,"Ошибка в цикле по usersDetailList класса DetailsData: "+e);
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
}
