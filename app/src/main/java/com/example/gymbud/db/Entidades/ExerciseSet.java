package com.example.gymbud.db.Entidades;

public class ExerciseSet {
    private int id;
    private int numSeries;
    private int numReps;

    public ExerciseSet(int id, int numSeries, int numReps) {
        this.id = id;
        this.numSeries = numSeries;
        this.numReps = numReps;
    }

    public int getId() {
        return id;
    }

    public int getNumSeries() {
        return numSeries;
    }

    public int getNumReps() {
        return numReps;
    }
}