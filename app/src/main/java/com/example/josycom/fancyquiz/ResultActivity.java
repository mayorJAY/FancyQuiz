package com.example.josycom.fancyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    int score;
    QuizDatabase db;
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.result_toolbar);
        final MaterialButton buttonHome = findViewById(R.id.goto_home);
        final MaterialButton buttonCheck = findViewById(R.id.check_answers);
        final TextView totalQuestionTv = findViewById(R.id.total_question_number);
        final TextView scoreNumber = findViewById(R.id.score_number);
        final TextView correctAnswer = findViewById(R.id.correct_answer_number);
        final TextView incorrectAnswer = findViewById(R.id.incorrect_answer_number);

        score = getIntent().getIntExtra("score", 0);
        db = QuizDatabase.getDatabase(this);
        questions = db.quizDao().getAll();
        int totalQuestion = questions.size();
        totalQuestionTv.setText(String.valueOf(totalQuestion));
        float scoreToFloat = Float.valueOf(String.valueOf(score)) / Float.valueOf(String.valueOf(totalQuestion)) * 100F;
        scoreNumber.setText( scoreToFloat + "%");
        correctAnswer.setText(score + "/" + totalQuestion);
        incorrectAnswer.setText(totalQuestion - score + "/" + totalQuestion);

        toolbar.setTitle("Result");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBar));
        setSupportActionBar(toolbar);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CheckActivity.class));
            }
        });
    }
}
