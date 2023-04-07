package com.example.gymbud.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    private String name;
    private List<ExerciseSet> exerciseList;
    private int dayOfWeek;

    public Routine(String name, ArrayList<ExerciseSet> exerciseList, int dayOfWeek) {
        this.name = name;
        this.exerciseList = exerciseList;
        this.dayOfWeek = dayOfWeek;
    }

    public String getName() {
        return name;
    }

    public List<ExerciseSet> getExerciseList() {
        return exerciseList;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
