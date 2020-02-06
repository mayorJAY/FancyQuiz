package com.example.josycom.fancyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    int score;
    private List<Question> mQuestions;
    private ResultActivityViewModel mResultActivityViewModel;
    private int mTotalQuestion;

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

        toolbar.setTitle("Result");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBarText));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mResultActivityViewModel = new ResultActivityViewModel(getApplication());
        mQuestions = mResultActivityViewModel.getAllQuestions();

        // Get the score saved in the Question Activity
        score = getIntent().getIntExtra("score", 0);
        mTotalQuestion = mQuestions.size();

        // Display the score in different formats (percentage, etc)
        totalQuestionTv.setText(String.valueOf(mTotalQuestion));
        float scoreToFloat = Float.valueOf(String.valueOf(score)) / Float.valueOf(String.valueOf(mTotalQuestion)) * 100F;
        scoreNumber.setText( scoreToFloat + "%");
        correctAnswer.setText(score + "/" + mTotalQuestion);
        incorrectAnswer.setText(mTotalQuestion - score + "/" + mTotalQuestion);

        buttonHome.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        buttonCheck.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CheckActivity.class)));
    }
}
