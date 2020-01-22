package com.example.josycom.fancyquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.CheckViewHolder> {

    QuizDatabase db;
    List<Question> questions;
    Question currentQuestion;
    int currentNumber = 0;

    CheckAdapter(Context context){
        db = QuizDatabase.getDatabase(context);
        questions = db.quizDao().getAll();
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
            currentQuestion = questions.get(currentNumber++);
            holder.question.setText(currentQuestion.Question);
        }

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class CheckViewHolder extends RecyclerView.ViewHolder{
        TextView question, answer1, answer2;


        public CheckViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question_tv);
            answer1 = itemView.findViewById(R.id.selected_answer_tv);
            answer2 = itemView.findViewById(R.id.correct_answer);
        }
    }
}
