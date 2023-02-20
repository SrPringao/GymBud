package com.example.gymbud.db.Entidades;

public class Stats {
    int ID_Stats;

    public int getID_Stats() {
        return ID_Stats;
    }

    public void setID_Stats(int ID_Stats) {
        this.ID_Stats = ID_Stats;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getReps() {
        return Reps;
    }

    public void setReps(int reps) {
        Reps = reps;
    }

    public float getTime() {
        return Time;
    }

    public void setTime(float time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    int Weight;
    int Reps;

    public int getReps2() {
        return Reps2;
    }

    public void setReps2(int reps2) {
        Reps2 = reps2;
    }

    int Reps2;
    float Time;
    String Date;

    public int getIdEjercicio() {
        return IdEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        IdEjercicio = idEjercicio;
    }

    int IdEjercicio;
}
