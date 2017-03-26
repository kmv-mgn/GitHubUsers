package com.example.kmv.githubusers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;

/**
 * Адаптер карточек для RecyclerView on 23.03.2017.
 */

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Предоставляет ссылку на представления, используемые в RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
    @Override       //Создание нового представления
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(cv);
    }

    @Override       //Заполнение заданного представления данными
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*
*/

    }

    @Override       //Возвращает количество вариантов в наборе данных
    public int getItemCount() {
        return 5;
    }
}
