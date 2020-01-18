package com.example.josycom.fancyquiz;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


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
        final TextView titleTextView = rootView.findViewById(R.id.title_tv);

        titleTextView.setText("Music");

        buttonTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(getResources().getColor(R.color.colorButton));
            }
        });

        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(getResources().getColor(R.color.colorButton));
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (buttonTen.isPressed() && buttonEasy.isPressed()){
//                    startQuiz();
//                }
                startQuiz();
            }
        });

        return rootView;
    }

    public void startQuiz(){
        //Launch Quiz Activity
        Intent questionActivityIntent  = new Intent(getContext(), QuestionActivity.class);
        startActivity(questionActivityIntent);
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }
}
