package com.example.josycom.fancyquiz;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

import static com.example.josycom.fancyquiz.QuizCategoryManager.Quiz.GeneralKnowledge;

@Dao
interface QuizDao{

    @Query("SELECT * from Question WHERE Category LIKE :quizName")
    List<Question> getAll(String quizName);

    @Query("SELECT * from question")
    List<Question> getAll();

    @Query("DELETE FROM question")
    void deleteAll();

    @Query("SELECT * FROM question LIMIT 1")
    Question[] getAnyQuestion();

    @Insert
    void put(Question... questions);
}


@Database(entities = Question.class, version = 1)
public abstract class QuizDatabase extends RoomDatabase {

    public abstract QuizDao quizDao();

    private static QuizDatabase INSTANCE;

    public static QuizDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (QuizDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "quiz_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final QuizDao quizDao;
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
                "Nigeria’s fertile soil","Nigeria’s Unity", "Nigeria’s Freedom", "Nigeria’s Fertile Soil", "Nigeria's Dignity");

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
                "Mbappe young","Mikel Obi","Neymar Junior",
                "Mesut Ozil","Mbappe Young");

        PopulateDbAsync(QuizDatabase db){
            quizDao = db.quizDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (quizDao.getAnyQuestion().length < 1){
                quizDao.put(question, question1, question2, question3, question4, question5, question6, question7, question8, question9);
            }
            return null;
        }
    }
}
