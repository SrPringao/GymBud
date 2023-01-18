package com.example.gymbud.db.Entidades;


public class PersonInfo {
    private int UserId;
    private int Assists;
    private int DayRoutine;
    private double CurrentWeight;
    private double WeightGoal;
    private double Height;
    private int Gender;
    private int Age;
    private String Phrase;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getAssists() {
        return Assists;
    }

    public void setAssists(int assists) {
        Assists = assists;
    }

    public int getDayRoutine() {
        return DayRoutine;
    }

    public void setDayRoutine(int dayRoutine) {
        DayRoutine = dayRoutine;
    }

    public double getCurrentWeight() {
        return CurrentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        CurrentWeight = currentWeight;
    }

    public double getWeightGoal() {
        return WeightGoal;
    }

    public void setWeightGoal(double weightGoal) {
        WeightGoal = weightGoal;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPhrase() {
        return Phrase;
    }

    public void setPhrase(String phrase) {
        Phrase = phrase;
    }
}
