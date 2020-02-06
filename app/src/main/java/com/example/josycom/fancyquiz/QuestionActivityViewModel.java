package com.example.josycom.fancyquiz;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class QuestionActivityViewModel extends AndroidViewModel {
    private QuizRepository mQuizRepository;
    private List<Question> mAllQuestions;


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
}
