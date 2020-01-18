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
            //int position = viewHolder.getAdapterPosition();
            displayFragment();
        }
    };

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
