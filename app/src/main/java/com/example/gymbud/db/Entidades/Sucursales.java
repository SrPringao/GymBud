package com.example.gymbud.db.Entidades;

import java.math.BigInteger;

public class Sucursales {
    private int Id,CurrentUsers,Rating;
    private String SubName,Location,ImageLink,Schedule;
    private BigInteger ContactNumber;

    public Sucursales(int id,int currentUsers,int rating,String subName,String location, String imageLink, String schedule, BigInteger contactNumber){
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
