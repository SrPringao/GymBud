package com.example.gymbud.db.Entidades;

import java.util.ArrayList;

public class IdList {
    private static ArrayList<Integer> idList = new ArrayList<>();

    public static ArrayList<Integer> getInstance() {
        return idList;
    }
}
