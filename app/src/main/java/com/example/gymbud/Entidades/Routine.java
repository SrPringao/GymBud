package com.example.gymbud.Entidades;

import java.util.List;

public class Routine {
    private String name;
    private List<Exercises> exerciseList;
    private int dayOfWeek;

    public Routine(String name, List<Exercises> exerciseList, int dayOfWeek) {
        this.name = name;
        this.exerciseList = exerciseList;
        this.dayOfWeek = dayOfWeek;
    }

    public String getName() {
        return name;
    }

    public List<Exercises> getExerciseList() {
        return exerciseList;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
