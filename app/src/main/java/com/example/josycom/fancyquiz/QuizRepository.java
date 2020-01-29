package com.example.josycom.fancyquiz;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import static com.example.josycom.fancyquiz.QuizCategoryManager.Quiz.GeneralKnowledge;

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
        Question question = new Question(GeneralKnowledge.getName(),
                "Who formed the first political party in Nigeria?",
                "Herbert Macaulay", "Herbert Macaulay", "Nnamdi Azikwe", "Abubakar Tafawa Balewa", "Ahmadu Bello");
        Question question1 = new Question(
                GeneralKnowledge.getName(),"What was the first political party in Nigeria?",
                "Nigerian National Democratic Party (NNDP)","Advances People’s Democratic Alliance (APDA)",
                "Nigerian National Democratic Party (NNDP)",
                "Social Democratic Mega Party (SDMP)", "Labour Party");

        Question question2 = new Question(
                GeneralKnowledge.getName(),"What does the Eagle in the Nigerian coat of arm represent?",
                "Strength","Patience", "Dignity", "Strength", "Peace");

        Question question3 = new Question(
                GeneralKnowledge.getName(),"What do the two horses on the Nigerian coat of arm represent?",
                "Dignity","Strength", "Love", "Peace", "Dignity");

        Question question4 = new Question(
                GeneralKnowledge.getName(),"Nigeria is divided into how many geopolitical zones?",
                "Six", "Four", "Six", "Eight", "Twelve");

        Question question5 = new Question(
                GeneralKnowledge.getName(),"What was the black shield in the Nigerian coat of arm stand for?",
                "Nigeria’s Fertile Soil","Nigeria’s Unity", "Nigeria’s Freedom", "Nigeria’s Fertile Soil", "Nigeria's Dignity");

        Question question6 = new Question(GeneralKnowledge.getName(),
                "Who won the 2018 Russia world cup?",
                "France", "Spain","Belgium",
                "Croatia","France");

        Question question7 = new Question(
                GeneralKnowledge.getName(),"Who won the 2019 women world cup?",
                "Spain","USA",
                "Croatia","France","Spain");

        Question question8 = new Question(
                GeneralKnowledge.getName(),"Who won the 2006 world cup?",
                "Italy","Spain","Belgium",
                "Italy","France");

        Question question9 = new Question(
                GeneralKnowledge.getName(),"The Best player in football after Messi and Ronaldo is",
                "Mbappe Young","Mikel Obi","Neymar Junior",
                "Mesut Ozil","Mbappe Young");
        QuizDatabase.databaseWriteExecutor.execute(() ->{
            if (mQuizDao.getAnyQuestion().length < 1){
                mQuizDao.put(question, question1, question2, question3, question4, question5,
                        question6, question7, question8, question9);
            }
        });
    }
}
