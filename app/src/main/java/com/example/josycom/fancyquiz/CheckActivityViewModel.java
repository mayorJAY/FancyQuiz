package com.example.josycom.fancyquiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CheckActivityViewModel extends AndroidViewModel {
    private QuizRepository mQuizRepository;
    private List<Question> mAllQuestions;


    public CheckActivityViewModel(@NonNull Application application) {
        super(application);
        mQuizRepository = new QuizRepository(application);
        mAllQuestions = mQuizRepository.getAllQuestions();
    }

    List<Question> getAllQuestions(){
        return mAllQuestions;
    }

    void put(Question... questions){
        mQuizRepository.put(questions);
    }
}
