package com.example.josycom.fancyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;


public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Toolbar toolbar = findViewById(R.id.check_toolbar);

        toolbar.setTitle("Check Answers");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBarText));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView checkRecyclerView = findViewById(R.id.check_answers_rv);
        checkRecyclerView.setHasFixedSize(true);
        checkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CheckAdapter checkAdapter = new CheckAdapter(this);
        checkRecyclerView.setAdapter(checkAdapter);

        CheckActivityViewModel checkActivityViewModel = new ViewModelProvider(this).get(CheckActivityViewModel.class);
        checkAdapter.setQuestions(checkActivityViewModel.getAllQuestions());

        final MaterialButton doneButton = findViewById(R.id.done);
        doneButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }
}
