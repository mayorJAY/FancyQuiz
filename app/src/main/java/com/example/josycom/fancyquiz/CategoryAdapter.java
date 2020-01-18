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
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_category_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.quizImage.setImageResource(R.drawable.ic_music_note_24dp);
        holder.quizName.setText("Music");

    }

    @Override
    public int getItemCount() {
        return 20;
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
