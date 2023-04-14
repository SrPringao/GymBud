package com.example.gymbud.Entidades;

public class ExerciseSet {
    private int id;

    private String name;

    private byte[] image;
    private int numSeries;
    private int numReps;
    private int muscleGroup;
    public ExerciseSet() {

    }
    public ExerciseSet(int id, String name, int numSeries, int numReps, int muscleGroup, byte[] image) {
        this.id = id;
        this.numSeries = numSeries;
        this.numReps = numReps;
        this.muscleGroup = muscleGroup;
        this.name = name;
        this.image = image;
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

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }
}