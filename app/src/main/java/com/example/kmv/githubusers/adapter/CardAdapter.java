package com.example.kmv.githubusers.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.bumptech.glide.Glide;
import com.example.kmv.githubusers.data.DetailsData;
import com.example.kmv.githubusers.R;
import com.example.kmv.githubusers.model.User;

/**
 * Адаптер карточек для RecyclerView on 23.03.2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private static final int TAB_NUM_0 = 0;    //вкладка  A-H
    private static final int TAB_NUM_1 = 1;    //вкладка  I-P
    private static final int TAB_NUM_2 = 2;    //вкладка  Q-Z

    private List<User> mItems;

    //определяем, какой именно список пользователей нужно загрузить в карточки
    public void setNumAdapter(int numTab){
        switch (numTab) {
            case TAB_NUM_0: mItems = DetailsData.getDataAH();
                break;
            case TAB_NUM_1: mItems = DetailsData.getDataIP();
                break;
            case TAB_NUM_2: mItems = DetailsData.getDataQZ();
                break;
        }
    }

     public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public TextView login;
        public TextView followUsers;
        public ImageView avatar;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
            login = (TextView) v.findViewById(R.id.tv_login);
            followUsers = (TextView) v.findViewById(R.id.tv_FollowUsers);
            avatar = (ImageView) v.findViewById(R.id.iv_avatar);
        }

    }
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(cv);
    }


    public void onBindViewHolder(ViewHolder holder, final int position) {

        User user = mItems.get(position);
        CardView cardView = holder.cardView;
        holder.login.setText(user.getName());                        //прописываем имя пользователя
        holder.followUsers.setText(user.getFollowers()+"/"+user.getFollowing()); //количество подписок и подписавшихся

        Glide.with(cardView.getContext())                   //загружаем аватарку
                .load(user.getAvatarUrl())
                .into(holder.avatar);


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
