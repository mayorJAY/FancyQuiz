package com.example.josycom.fancyquiz;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(indices = {@Index(unique = false, value = "Category")})
public class Question {

    public Question(){}

    public Question(int primaryKey, String category, String question, String answer, String... options){
        this.primaryKey = primaryKey;
        this.Question = question;
        this.Category = category;
        this.answer = answer;
        this.options.addAll(Arrays.asList(options));
    }

    @PrimaryKey(autoGenerate = true)
    private int primaryKey;
    private String Category;
    private String Question;
    private String answer;
    @TypeConverters(AnswerConverter.class)
    List<String> options = new ArrayList<>();
}

class AnswerConverter {

    @TypeConverter
    public String answersFromArray(List<String> answers) {
        String a = answers.get(0);


        for (int i = 1; i < answers.size(); i++) {
            a = a.concat(":").concat(answers.get(i));
        }

        return a;
    }

    @TypeConverter
    public List<String> answersToArray(String answers) {
        return Arrays.asList(answers.split(":"));
    }

}
