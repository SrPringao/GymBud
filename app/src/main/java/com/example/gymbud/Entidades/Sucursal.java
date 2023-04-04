package com.example.gymbud.Entidades;

import java.math.BigInteger;

public class Sucursal {
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

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
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

    private int Id,CurrentUsers,Rating;
    private String SubName,Location,ImageLink,Schedule;
    private int ContactNumber;

    public Sucursal(int id, int currentUsers, int rating, String subName, String location, String imageLink, String schedule, int contactNumber){
        this.Id = id;
        this.CurrentUsers = currentUsers;
        this.Rating = rating;
        this.SubName = subName;
        this.Location = location;
        this.ImageLink = imageLink;
        this.Schedule = schedule;
        this.ContactNumber = contactNumber;
    }


}
