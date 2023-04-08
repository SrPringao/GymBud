package com.example.gymbud.Funciones;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Funciones {
    public static List<Integer> obtenerNumerosMasRepetidos(List<Integer> numeros) {
        Map<Integer, Integer> frecuencia = new HashMap<>();
        for (Integer numero : numeros) {
            frecuencia.put(numero, frecuencia.getOrDefault(numero, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> entradas = new ArrayList<>(frecuencia.entrySet());
        entradas.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Integer> numerosMasRepetidos = new ArrayList<>();
        for (int i = 0; i < entradas.size() && numerosMasRepetidos.size() < 2; i++) {
            numerosMasRepetidos.add(entradas.get(i).getKey());
        }
        return numerosMasRepetidos;
    }

}
