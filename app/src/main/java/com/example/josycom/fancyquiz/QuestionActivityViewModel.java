package com.example.josycom.fancyquiz;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionActivityViewModel extends AndroidViewModel {
    private QuizRepository mQuizRepository;
    private List<Question> mAllQuestions;
    private List<Question> mQuestions;


    public QuestionActivityViewModel(@NonNull Application application) {
        super(application);
        mQuizRepository = new QuizRepository(application);
        mAllQuestions = mQuizRepository.getAllQuestions();
    }

    List<Question> getAllQuestions() {
        return mAllQuestions;
    }

    void put(Question... questions){
        mQuizRepository.put(questions);
    }

    void setQuestions(List<Question> questions){
        mQuestions = questions;
    }

}
