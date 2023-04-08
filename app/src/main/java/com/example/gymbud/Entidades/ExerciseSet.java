package com.example.gymbud.Entidades;

public class ExerciseSet {
    private int id;
    private int numSeries;
    private int numReps;
    private int muscleGroup;

    public ExerciseSet(int id, int numSeries, int numReps, int muscleGroup) {
        this.id = id;
        this.numSeries = numSeries;
        this.numReps = numReps;
        this.muscleGroup = muscleGroup;
    }

    public ExerciseSet(int id) {
        this.id = id;
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

    public int getMuscleGroup() {
        return muscleGroup;
    }
}