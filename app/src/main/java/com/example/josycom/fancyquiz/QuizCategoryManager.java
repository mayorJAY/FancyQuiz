package com.example.josycom.fancyquiz;

import androidx.annotation.DrawableRes;

class QuizCategoryManager {

    // Create the Quiz object
    public enum Quiz{
        GeneralKnowledge("General knowledge", R.drawable.ic_general_24dp),
        Books("Books", R.drawable.ic_book_24dp),
        Film("Film", R.drawable.ic_film_24dp),
        Music("Music", R.drawable.ic_music_note_24dp),
        MusicalAndTheatres("Musical & Theatres", R.drawable.ic_musical_24dp),
        Television("Television", R.drawable.ic_tv_24dp),
        VideoGames("Video Games", R.drawable.ic_videogame_24dp),
        BoardGames("Board Games", R.drawable.ic_boardgame_24dp),
        ScienceAndNature("Science & Nature", R.drawable.ic_science_24dp),
        Computer("Computer", R.drawable.ic_computer_24dp),
        Maths("Maths", R.drawable.ic_maths_24dp),
        Sports("Sports", R.drawable.ic_sport_24dp),
        Geography("Geography", R.drawable.ic_geography_24dp),
        History("History", R.drawable.ic_history_24dp),
        Politics("Politics", R.drawable.ic_politics_24dp),
        Art("Art", R.drawable.ic_art_24dp),
        Animals("Animals", R.drawable.ic_animal_24dp),
        Vehicles("Vehicles", R.drawable.ic_vehicle_24dp),
        Gadgets("Gadgets", R.drawable.ic_gadget_24dp),
        Business("Business", R.drawable.ic_business_24dp);

    private String name;
    private int image;
    Quiz(String name, @DrawableRes int image){
        this.name = name;
        this.image = image;
    }

    public @DrawableRes int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
    }

    private static Quiz[] quizzes = Quiz.values();

    static Quiz getQuizAt(int position){
        return quizzes[position];
    }

    static int getQuizCount(){
        return quizzes.length;
    }
}
