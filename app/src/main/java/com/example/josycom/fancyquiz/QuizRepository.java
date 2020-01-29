package com.example.josycom.fancyquiz;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class QuizRepository {
    private QuizDao mQuizDao;
    private LiveData<List<Question>> mAllQuestions;


    QuizRepository(Application application){
        QuizDatabase db = QuizDatabase.getDatabase(application);
        mQuizDao = db.quizDao();
        mAllQuestions = mQuizDao.getAllQuestions();
    }

    LiveData<List<Question>> getAllQuestions(){
        return mAllQuestions;
    }

    void put(Question... questions){
        QuizDatabase.databaseWriteExecutor.execute(() ->
                mQuizDao.put(questions));
    }
}
