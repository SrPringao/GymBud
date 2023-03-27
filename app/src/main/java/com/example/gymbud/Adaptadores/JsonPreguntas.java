package com.example.gymbud.Adaptadores;

import android.content.Context;

import com.example.gymbud.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonPreguntas {
    public static String leerJson(Context context) throws IOException {
        BufferedReader reader = null;
        reader= new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.preguntas), "UTF-8"));
        String content = "";
        String line;
        while ((line = reader.readLine()) != null) {
            content += line;
        }
        return content;
    }
}
