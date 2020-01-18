package com.example.josycom.fancyquiz;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Button buttonTen = rootView.findViewById(R.id.ten);
        final Button buttonEasy = rootView.findViewById(R.id.easy);
        final Button buttonStart = rootView.findViewById(R.id.start_quiz);

        buttonTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.BLUE);
            }
        });

        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.BLUE);
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonTen.isPressed() && buttonEasy.isPressed()){
                    startQuiz();
                }
            }
        });

        return rootView;
    }

    public void startQuiz(){
        //Launch Quiz Activity
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }
}
