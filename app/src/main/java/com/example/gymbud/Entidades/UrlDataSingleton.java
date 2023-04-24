package com.example.gymbud.Entidades;

public class UrlDataSingleton {
    private static UrlDataSingleton instance = null;
    private String id;

    private UrlDataSingleton() {
        // Constructor privado para evitar la creación de objetos desde fuera de la clase
    }

    public static UrlDataSingleton getInstance() {
        if (instance == null) {
            instance = new UrlDataSingleton();
        }
        return instance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
