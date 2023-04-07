package com.example.gymbud.Entidades;

public class Exercises {

    private int Id ,MuscularGroup, Tool, Category, Difficulty, stats,sets,reps;
    private String Name, Focus , ForeSeeing, Execution, Details;
    private byte[] Image;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMuscularGroup() {
        return MuscularGroup;
    }

    public void setMuscularGroup(int muscularGroup) {
        MuscularGroup = muscularGroup;
    }

    public String getFocus() {
        return Focus;
    }

    public void setFocus(String focus) {
        Focus = focus;
    }

    public String getForeSeeing() {
        return ForeSeeing;
    }

    public void setForeSeeing(String foreSeeing) {
        ForeSeeing = foreSeeing;
    }

    public String getExecution() {
        return Execution;
    }

    public void setExecution(String execution) {
        Execution = execution;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public int getTool() {
        return Tool;
    }

    public void setTool(int tool) {
        Tool = tool;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public int getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(int difficulty) {
        Difficulty = difficulty;
    }

    public int getStats() {
        return stats;
    }

    public void setStats(int stats) {
        this.stats = stats;
    }

    public void setSets(int sets){
        this.sets = sets;
    }

    public void setReps(int reps){
        this.reps = reps;
    }

    public int getSets(){
        return sets;
    }
    public int getReps(){
        return reps;
    }

}
