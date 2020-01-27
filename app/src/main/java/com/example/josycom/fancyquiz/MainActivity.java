package com.example.josycom.fancyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView quizCategory = findViewById(R.id.quiz_category_rv);
        quizCategory.setHasFixedSize(true);
        quizCategory.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        CategoryAdapter categoryAdapter = new CategoryAdapter();
        quizCategory.setAdapter(categoryAdapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.grid_spacing_large);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.grid_spacing_small);
        quizCategory.addItemDecoration(new GridItemDecoration(largePadding, smallPadding));
        categoryAdapter.setOnItemClickListener(onItemClickListener);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            QuizCategoryManager.Quiz quiz = QuizCategoryManager.getQuizAt(position);

            // Saves the Quiz name to be passed to the Fragment
            Bundle bundle = new Bundle();
            String quizName = quiz.getName();
            bundle.putString("quizName", quizName);
            // Instantiate the fragment (MainFragment)
            MainFragment mainFragment = MainFragment.newInstance();
            mainFragment.setArguments(bundle);
            // Get the FragmentManager and start a transaction
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // Add the MainFragment
            fragmentTransaction.add(R.id.fragment_container, mainFragment).addToBackStack(null).commit();
        }
    };
}
