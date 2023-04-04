package com.example.gymbud.db.Entidades;

import java.util.ArrayList;

public class IdList {
    private static ArrayList<Integer> idList = new ArrayList<>();

    public static ArrayList<Integer> getInstance() {
        return idList;
    }

    //method to eliminate an id from the list
    public static void removeId(int id) {
        for (int i = 0; i < idList.size(); i++) {
            if (idList.get(i) == id) {
                idList.remove(i);
            }
        }
    }
}
