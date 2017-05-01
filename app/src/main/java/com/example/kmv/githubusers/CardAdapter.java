package com.example.kmv.githubusers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;

/**
 * Адаптер карточек для RecyclerView on 23.03.2017.
 */

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> mItems = DetailsData.usersDetailList;

    //добавление данных в процессе считывания данных с сервера
    public void addDataCard (User user) {
        Log.d(Const.TAG,"Зашли в метод addData и добавили "+ user.getLogin());
        //mItems.add(user);           //добавление данных о пользователе в список
        //notifyDataSetChanged();     //обновление RecyclerView
    }

    //Предоставляет ссылку на представления, используемые в RecyclerView
    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public TextView login;
        public TextView FollowUsers;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
            login = (TextView) v.findViewById(R.id.tv_login);
            FollowUsers = (TextView) v.findViewById(R.id.tv_FollowUsers);
        }

    }
    @Override       //Создание нового представления
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(cv);
    }

    @Override       //Заполнение заданного представления данными
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        User user = mItems.get(position);


        //viewHolder.login.setText(user.getLogin());


    }

    @Override       //Возвращает количество вариантов в наборе данных
    public int getItemCount() {
        return 10;
    }
}
