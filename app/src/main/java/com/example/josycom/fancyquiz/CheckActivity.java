package com.example.josycom.fancyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Toolbar toolbar = findViewById(R.id.check_toolbar);

        toolbar.setTitle("Check Answers");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAppBar));
        setSupportActionBar(toolbar);

        RecyclerView checkRecyclerView = findViewById(R.id.check_answers_rv);
        checkRecyclerView.setHasFixedSize(true);
        checkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CheckAdapter checkAdapter = new CheckAdapter(this);
        checkRecyclerView.setAdapter(checkAdapter);


        final MaterialButton doneButton = findViewById(R.id.done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
