package com.example.josycom.fancyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    QuizCategoryManager.Quiz quiz;
    QuizDatabase db;
    List<Question> questions;
    Question currentQuestion;
    int currentNumber = 1;
    int score = 0;
    private TextView questionTextView;
    private RadioGroup answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.question_toolbar);


        db = QuizDatabase.getDatabase(this);
        for (int i = 0; i < QuizCategoryManager.getQuizCount(); i++){
            quiz = QuizCategoryManager.getQuizAt(i);
        }

        toolbar.setTitle("Music");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBar));
        setSupportActionBar(toolbar);

        questions = db.quizDao().getAll();
        questionTextView = findViewById(R.id.quiz_question);
        answers = findViewById(R.id.answers);
        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == -1)
                    return;
                final RadioButton checkedRadioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                if (checkedRadioButton.getText().toString().matches(currentQuestion.answer)){
                    ++score;
                }
            }
        });

        final MaterialButton buttonNext = findViewById(R.id.next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonNext.getText().toString().matches("Submit")){
                    Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                    resultIntent.putExtra("score", score);
                    startActivity(resultIntent);
                }
                gotoNextQuestion();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        gotoNextQuestion();
    }

    private void gotoNextQuestion(){
        if (currentNumber >= questions.size()){
            MaterialButton buttonNext = findViewById(R.id.next);
            buttonNext.setText("Submit");
        } else {
            currentQuestion = questions.get(currentNumber++);
            questionTextView.setText(currentQuestion.Question);
            answers.clearCheck();
            for (int i = 0; i < currentQuestion.options.size(); i++){
                RadioButton radioButton = (RadioButton) answers.getChildAt(i);
                radioButton.setText(currentQuestion.options.get(i));
            }
        }
    }
}
