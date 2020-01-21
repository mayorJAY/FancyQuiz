package com.example.josycom.fancyquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private View.OnClickListener mOnItemClickListener;
    QuizCategoryManager.Quiz quiz;
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        this.quiz = QuizCategoryManager.getQuizAt(position);
        holder.quizImage.setImageResource(quiz.getImage());
        holder.quizName.setText(quiz.getName());

    }

    @Override
    public int getItemCount() {
        return QuizCategoryManager.getQuizCount();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView quizImage;
        TextView quizName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            quizImage = itemView.findViewById(R.id.quiz_image);
            quizName = itemView.findViewById(R.id.quiz_name);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
