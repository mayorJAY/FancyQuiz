package com.example.josycom.fancyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    QuizDatabase db;
    List<Question> questions;
    Question currentQuestion;
    int currentNumber = 0;
    int score = 0;
    private TextView questionTextView;
    private RadioGroup answers;
    MaterialButton questionNumber;
    ArrayList<ChosenAnswer> mChosenAnswer;

    private SharedPreferences mPreferences;
    public static String sharedPrefFile = "com.example.josycom.fancyquiz.SHAREDPREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.question_toolbar);
        toolbar.setTitle("Music");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBarText));
        setSupportActionBar(toolbar);

        db = QuizDatabase.getDatabase(this);
        mPreferences = getSharedPreferences(sharedPrefFile, 0);
        questions = db.quizDao().getAll();
        questionTextView = findViewById(R.id.quiz_question);
        answers = findViewById(R.id.answers);
        questionNumber = findViewById(R.id.question_number);
        mChosenAnswer = new ArrayList<>();

        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == -1)
                    return;
                final RadioButton checkedRadioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                // Add content of the RadioButton to the ArrayList
                mChosenAnswer.add(new ChosenAnswer(checkedRadioButton.getText().toString()));

                if (checkedRadioButton.getText().toString().matches(currentQuestion.answer)){
                    // Track the score
                    ++score;
                }
            }
        });

        final MaterialButton buttonNext = findViewById(R.id.next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonNext.getText().toString().matches("Submit")){

                    // Create a SharedPreference file and add the content of the ArrayList to it
                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                    Gson gson = new Gson();
                    String json  = gson.toJson(mChosenAnswer);
                    preferencesEditor.putString("answers", json);
                    preferencesEditor.apply();

                    // Start Result Activity passing it the Score Extra
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
            // Tie the Questions to the TextView
            currentQuestion = questions.get(currentNumber++);
            questionTextView.setText(currentQuestion.Question);
            questionNumber.setText(String.valueOf(currentQuestion.primaryKey));
            answers.clearCheck();
            // Tie the Options to the RadioButton
            for (int i = 0; i < currentQuestion.options.size(); i++){
                RadioButton radioButton = (RadioButton) answers.getChildAt(i);
                radioButton.setText(currentQuestion.options.get(i));
            }
        }
    }
}
