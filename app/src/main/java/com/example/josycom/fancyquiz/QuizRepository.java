package com.example.josycom.fancyquiz;

import android.app.Application;

import java.util.List;

class QuizRepository {
    private QuizDao mQuizDao;
    private List<Question> mAllQuestions;


    QuizRepository(Application application){
        QuizDatabase db = QuizDatabase.getDatabase(application);
        mQuizDao = db.quizDao();
        mAllQuestions = mQuizDao.getAllQuestions();
    }

    List<Question> getAllQuestions(){
        return mAllQuestions;
    }

    void put(Question... questions){
        QuizDatabase.databaseWriteExecutor.execute(() ->
                mQuizDao.put(questions));
    }
}
