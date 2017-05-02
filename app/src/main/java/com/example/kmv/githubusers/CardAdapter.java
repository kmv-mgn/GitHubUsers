package com.example.kmv.githubusers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.bumptech.glide.Glide;

/**
 * Адаптер карточек для RecyclerView on 23.03.2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    //List<User> mItems = DetailsData.usersDetailList;
    //List<User> mItems = DetailsData.getDataAH();
    List<User> mItems;// = DetailsData.getDataIP();

    //добавление данных в процессе считывания данных с сервера
    public void addDataCard (User user) {
        Log.d(Const.TAG,"Зашли в метод addData и добавили "+ user.getLogin());
        //mItems.add(user);           //добавление данных о пользователе в список
        //notifyDataSetChanged();     //обновление RecyclerView
    }
    //указывает, какой именно список пользователей нужно загрузить в карточки
    public void setNumAdapter(int numTab){
        switch (numTab) {
            case 0: mItems = DetailsData.getDataAH();
                break;
            case 1: mItems = DetailsData.getDataIP();
                break;
            case 2: mItems = DetailsData.getDataQZ();
                break;
        }


        mItems=DetailsData.getDataIP();
    }
    //Предоставляет ссылку на представления, используемые в RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public TextView login;
        public TextView followUsers;
        public ImageView avatar;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
            login = (TextView) v.findViewById(R.id.tv_login);
            login.setText("ля-ля-ля");
            followUsers = (TextView) v.findViewById(R.id.tv_FollowUsers);
            avatar = (ImageView) v.findViewById(R.id.iv_avatar);
        }

    }
    @Override       //Создание нового представления
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(cv);
    }

                    //Заполнение заданного представления данными
    public void onBindViewHolder(ViewHolder holder, final int position) {
        User user = mItems.get(position);
        CardView cardView = holder.cardView;
        holder.login.setText(user.getName());                        //прописываем имя пользователя
        holder.followUsers.setText(user.getFollowers()+"/"+user.getFollowing()); //количество подписок и подписавшихся

        Glide.with(cardView.getContext())                   //загружаем аватарку
                .load(user.getAvatarUrl())
                .into(holder.avatar);


    }

    @Override       //Возвращает количество вариантов в наборе данных
    public int getItemCount() {
        return mItems.size();
    }
}
