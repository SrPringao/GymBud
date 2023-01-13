package com.example.gymbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class olvidos extends AppCompatActivity {
    EditText correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidos);
        correo = findViewById(R.id.ETCorreoR);
    }

    public void LinkRegistro(View view){

        String link = correo.getText().toString();
        String URL = "https://Francoaldrete.com/Gymbud/Index.php?mail=";
        URL += link;
        Intent intentNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(intentNavegador);
    }
}