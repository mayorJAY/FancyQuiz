package com.example.josycom.fancyquiz;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

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
            //Populate the db
        }
    };
}

@Dao
interface QuizDao{

    @Query("SELECT * from Question WHERE Category LIKE :quizName")
    List<Question> getAll(String quizName);

    @Query("SELECT * from question")
    List<Question> getAll();

    @Query("DELETE FROM question")
    void deleteAll();

    @Insert
    void put(Question... questions);
}
