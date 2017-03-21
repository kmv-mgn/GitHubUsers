package com.example.kmv.githubusers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
        // Inflate the layout for this fragment
        int numTab; //переменная для хапоминания номера страницы пейджера
        numTab = getArguments().getInt(ARG_SECTION_NUMBER);
        Log.d(TAG,"... Создаем фрагмент, numTab = "+ Integer.toString(numTab));
        TextView tv = (TextView) container.findViewById(R.id.tvFragment);
        tv.setText("Находимся на вкладке "+numTab);

        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

}
