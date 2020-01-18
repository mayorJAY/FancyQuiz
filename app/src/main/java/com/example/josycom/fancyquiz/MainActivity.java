package com.example.josycom.fancyquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView quizCategory = findViewById(R.id.quiz_category);
        quizCategory.setHasFixedSize(true);
        quizCategory.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        quizCategory.setAdapter(new CategoryAdapter());
        int largePadding = getResources().getDimensionPixelSize(R.dimen.grid_spacing_large);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.grid_spacing_small);
        quizCategory.addItemDecoration(new GridItemDecoration(largePadding, smallPadding));
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{

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
    }

    private class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView quizImage;
        TextView quizName;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            quizImage = itemView.findViewById(R.id.quiz_image);
            quizName = itemView.findViewById(R.id.quiz_name);
        }
    }

    public void displayFragment(){
        // Instantiate the fragment (SimpleFragment)
        MainFragment mainFragment = MainFragment.newInstance();
        // Get the FragmentManager and start a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Add the SimpleFragment
        fragmentTransaction.add(R.id.fragment_container, mainFragment).addToBackStack(null).commit();
    }
}
