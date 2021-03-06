package com.example.josycom.fancyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private List<Question> mAllQuestions;
    private Question currentQuestion;
    int currentNumber = 0;
    int score = 0;
    private TextView questionTextView;
    private RadioGroup answers;
    private MaterialButton questionNumber;
    private ArrayList<ChosenAnswer> mChosenAnswer;
    private SharedPreferences mPreferences;
    public static String sharedPrefFile = "com.example.josycom.fancyquiz.SHAREDPREF";
    private RadioButton mCheckedRadioButton;
    private QuestionActivityViewModel mQuestionActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.question_toolbar);
        toolbar.setTitle("Music");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBarText));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPreferences = getSharedPreferences(sharedPrefFile, 0);
        questionTextView = findViewById(R.id.quiz_question);
        answers = findViewById(R.id.answers);
        questionNumber = findViewById(R.id.question_number);
        mChosenAnswer = new ArrayList<>();

        mQuestionActivityViewModel = new QuestionActivityViewModel(getApplication());
        mAllQuestions = mQuestionActivityViewModel.getAllQuestions();

        answers.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == -1) return;
            mCheckedRadioButton = radioGroup.findViewById(i);
            // Add content of the RadioButton to the ArrayList
            if (!mChosenAnswer.contains(new ChosenAnswer(mCheckedRadioButton.getText().toString()))){
                mChosenAnswer.add(new ChosenAnswer(mCheckedRadioButton.getText().toString()));
                Toast toast = Toast.makeText(getApplicationContext(), mCheckedRadioButton.getText().toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 200);
                toast.show();
            }

            if (mCheckedRadioButton.getText().toString().matches(currentQuestion.answer)){
                // Track the score
                ++score;
            }
            answers.clearCheck();
        });

        final MaterialButton buttonNext = findViewById(R.id.next);
        buttonNext.setOnClickListener(v -> {
            if (buttonNext.getText().toString().matches("Submit")){

                // Start Result Activity passing it the Score Extra
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                resultIntent.putExtra("score", score);
                startActivity(resultIntent);
            }
            // Create a SharedPreference file and add the content of the ArrayList to it
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            Gson gson = new Gson();
            String json  = gson.toJson(mChosenAnswer);
            preferencesEditor.putString("answers", json);
            preferencesEditor.apply();

            gotoNextQuestion();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        gotoNextQuestion();
    }

    private void gotoNextQuestion(){
        if (currentNumber >= mAllQuestions.size()){
            MaterialButton buttonNext = findViewById(R.id.next);
            buttonNext.setText("Submit");
        } else {
            // Tie the Questions to the TextView
            currentQuestion = mAllQuestions.get(currentNumber++);
            questionTextView.setText(currentQuestion.Question);
            questionNumber.setText(String.valueOf(currentQuestion.primaryKey));
            // Tie the Options to the RadioButton
            for (int i = 0; i < currentQuestion.options.size(); i++){
                RadioButton radioButton = (RadioButton) answers.getChildAt(i);
                radioButton.setText(currentQuestion.options.get(i));
            }
        }
    }
}
