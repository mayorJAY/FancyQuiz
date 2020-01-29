package com.example.josycom.fancyquiz;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.josycom.fancyquiz.QuizCategoryManager.Quiz.GeneralKnowledge;

@Dao
interface QuizDao{

    @Query("SELECT * from Question WHERE Category LIKE :quizName")
    List<Question> getAllQuestionsByCategory(String quizName);

    @Query("SELECT * from question")
    LiveData<List<Question>> getAllQuestions();

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

    private static volatile QuizDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    static QuizDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (QuizDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "quiz_database")
                            //.fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            //.allowMainThreadQueries()
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
        }
    };
}
