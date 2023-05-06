package com.example.gymbud.Adaptadores;

import android.content.Context;

import com.example.gymbud.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonFeedBack {
    public static String Read(Context context) throws IOException {
        BufferedReader reader = null;
        reader= new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.feedback), "UTF-8"));
        String content = "";
        String line;
        while ((line = reader.readLine()) != null) {
            content += line;
        }
        return content;
    }
}
