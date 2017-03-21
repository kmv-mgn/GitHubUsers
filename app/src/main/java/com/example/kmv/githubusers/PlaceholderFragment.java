package com.example.kmv.githubusers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
        PlaceholderFragment fragment = new PlaceholderFragment();
        Log.d(TAG,"newInstance фрагмента вызвана с sectionNumber = "+ Integer.toString(sectionNumber));
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);    //не знаю пока как потом вытащить номер у фрагмента
        //numTab = sectionNumber;         //сделала пока так.
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

}
