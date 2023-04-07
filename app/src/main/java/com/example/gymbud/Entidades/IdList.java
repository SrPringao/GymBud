package com.example.gymbud.Entidades;

import java.util.ArrayList;

public class IdList {
    private static ArrayList<ExerciseSet> idList = new ArrayList<>();

    public static ArrayList<ExerciseSet> getInstance() {
        return idList;
    }

    //method to eliminate an id from the list
    public static void removeId(int id) {
        ExerciseSet idEjercicio = new ExerciseSet(id);

        for (int i = 0; i < idList.size(); i++) {
            if (idList.get(i).getId() == idEjercicio.getId()) {
                idList.remove(i);
            }
        }
    }
}
