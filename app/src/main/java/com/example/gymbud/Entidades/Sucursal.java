package com.example.gymbud.Entidades;

public class Sucursal {

    public Sucursal(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCurrentUsers() {
        return CurrentUsers;
    }

    public void setCurrentUsers(int currentUsers) {
        CurrentUsers = currentUsers;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public String getSubName() {
        return SubName;
    }

    public void setSubName(String subName) {
        SubName = subName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getSchedule() {
        return Schedule;
    }

    public void setSchedule(String schedule) {
        Schedule = schedule;
    }

    public int getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(int contactNumber) {
        ContactNumber = contactNumber;
    }

    private int Id,CurrentUsers;
    private float Rating;
    private String SubName,Location,ImageLink,Schedule;
    private int ContactNumber;
    private String Latitud;
    private String Longitud;



    public Sucursal(int id, int currentUsers, double rating, String subName, String location, String imageLink, String schedule, int contactNumber, String Latitud,String Longitud){
        this.Id = id;
        this.CurrentUsers = currentUsers;
        this.Rating = (float) rating;
        this.SubName = subName;
        this.Location = location;
        this.ImageLink = imageLink;
        this.Schedule = schedule;
        this.ContactNumber = contactNumber;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
    }


    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }
}
