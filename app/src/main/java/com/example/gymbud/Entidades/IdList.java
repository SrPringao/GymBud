package com.example.gymbud.Entidades;

import java.util.ArrayList;
import java.util.Iterator;

public class IdList {
    private static ArrayList<ExerciseSet> idList = new ArrayList<>();

    public static ArrayList<ExerciseSet> getInstance() {
        return idList;
    }

    //method to eliminate an id from the list
    public static void removeId(int id) {
        Iterator<ExerciseSet> iterator = idList.iterator();
        while (iterator.hasNext()) {
            ExerciseSet exercise = iterator.next();
            if (exercise.getId() == id) {
                iterator.remove();
            }
        }
    }

    public static boolean containsExerciseWithId(ArrayList<ExerciseSet> list, int id) {
        for (ExerciseSet exercise : list) {
            if (exercise.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
