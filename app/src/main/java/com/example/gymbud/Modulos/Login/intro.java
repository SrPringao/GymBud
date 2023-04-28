package com.example.gymbud.Modulos.Login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymbud.Entidades.UrlDataSingleton;
import com.example.gymbud.R;

public class intro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Uri data = getIntent().getData();
        if (data != null) {
            String id = data.getQueryParameter("id");
            // Usa el valor de id como desees
            Log.wtf("MiApp", "URI recibida: " + id);
            UrlDataSingleton.getInstance().setId(id);

        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        },2500);  //2500
    }
}