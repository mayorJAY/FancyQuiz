package com.example.josycom.fancyquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.CheckViewHolder> {

    private List<Question> questions;
    private int currentNumber = 0;
    private SharedPreferences mPreferences;

    CheckAdapter(Context context){
        QuizDatabase db = QuizDatabase.getDatabase(context);
        questions = db.quizDao().getAll();
        mPreferences =context.getSharedPreferences(QuestionActivity.sharedPrefFile, 0);
    }
    @NonNull
    @Override
    public CheckAdapter.CheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.check_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckAdapter.CheckViewHolder holder, int position) {
        if (currentNumber >= questions.size()){
            return;
        } else {
            Question currentQuestion = questions.get(currentNumber++);
            holder.question.setText(currentQuestion.Question);
        }
        Gson gson = new Gson();
        String json = mPreferences.getString("answers", null);
        Type type = new TypeToken<ArrayList<ChosenAnswer>>(){}.getType();
        ArrayList<ChosenAnswer> chosenAnswers = gson.fromJson(json, type);
        ChosenAnswer currentChosenAnswer = null;
        Question theCurrentQuestion = null;
        if (chosenAnswers != null) {
            currentChosenAnswer = chosenAnswers.get(position);
            theCurrentQuestion = questions.get(position);
        }
        if (currentChosenAnswer != null) {
            holder.answer1.setText(currentChosenAnswer.getAnswer());
        }
        if (theCurrentQuestion != null) {
            if (currentChosenAnswer != null) {
                if (!currentChosenAnswer.getAnswer().equals(theCurrentQuestion.answer)){
                    holder.answer1.setTextColor(Color.RED);
                    holder.answer2.setText("Answer: " + theCurrentQuestion.answer);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class CheckViewHolder extends RecyclerView.ViewHolder{
        TextView question, answer1, answer2;


        CheckViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question_tv);
            answer1 = itemView.findViewById(R.id.selected_answer_tv);
            answer2 = itemView.findViewById(R.id.correct_answer_tv);
        }
    }
}
