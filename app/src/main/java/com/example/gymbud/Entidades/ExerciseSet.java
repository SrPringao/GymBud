package com.example.gymbud.Entidades;

public class ExerciseSet {
    private int id;
    private String name;
    private String image;
    private int numSeries;
    private int numReps;
    private int muscleGroup;

    private int tiempo;
    public ExerciseSet() {

    }

    public ExerciseSet(int id, String name, int numSeries, int numReps, int muscleGroup, String image) {
        this.id = id;
        this.numSeries = numSeries;
        this.numReps = numReps;
        this.muscleGroup = muscleGroup;
        this.name = name;
        this.image = image;
    }

    public ExerciseSet(int id, String name, int muscleGroup, int tiempo) {
        this.id = id;
        this.numSeries = 0;
        this.numReps = 0;
        this.muscleGroup = muscleGroup;
        this.name = name;
        this.image = "";
        this.tiempo = tiempo;
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

    public String getImage() {
        return image;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}