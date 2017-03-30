package com.example.kmv.githubusers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kmv.githubusers.RetrofitGitHub;

import static com.example.kmv.githubusers.RetrofitGitHub.getModelsObservable;
import static com.example.kmv.githubusers.RetrofitGitHub.init;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceholderFragment extends Fragment {
    private static final String TAG = "MyLog";          //константа для логов
    private static final String ARG_SECTION_NUMBER = "section_number";  //константа, необходимая для сохранения в Bundle

    public PlaceholderFragment() {
        // Required empty public constructor
    }

    /* Возвращает новый экземпляр этого фрагмента по заданному номеру раздела.*/
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();   //создаем новый экземпляр фрагмента
        Log.d(TAG,"newInstance фрагмента вызвана с sectionNumber = "+ Integer.toString(sectionNumber));
        Bundle args = new Bundle(); //создаем новый объект бандл для сохранения текущей страницы
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int numTab; //переменная для хапоминания номера страницы пейджера
        numTab = getArguments().getInt(ARG_SECTION_NUMBER);
        Log.d(TAG,"... Создаем фрагмент, numTab = "+ Integer.toString(numTab));

        View rootView = inflater.inflate(R.layout.fragment_placeholder, container, false);

        TextView tv = (TextView) rootView.findViewById(R.id.tv_Fragment);
        String displayText = "Находимся на вкладке "+Integer.toString(numTab);
        tv.setText(displayText);

        CardAdapter adapter = new CardAdapter();
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        //установка макета для рециклер
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        Log.d(TAG,"Создали фрагмент и устанавливаем его");
        return rootView;

        //init();
        //getModelsObservable();

    }

}
