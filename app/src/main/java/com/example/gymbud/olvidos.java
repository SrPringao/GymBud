package com.example.gymbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class olvidos extends AppCompatActivity {
    EditText correo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidos);
        correo = findViewById(R.id.ETCorreoR);
    }


    protected void onStop(){
        super.onStop();
        Intent i = new Intent(olvidos.this, MainActivity.class);
        startActivity(i);
        finish();
    }


public void Query(String mail) {
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo.getText().toString()).matches()) {
        Toast.makeText(olvidos.this, "Ingresa un correo valido", Toast.LENGTH_SHORT).show();
    } else {
        String url = "http://francoaldrete.com/GymBud/correo.php?mail=";
        url += mail;
        JsonObjectRequest pet = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(olvidos.this, "Se envio el correo ", Toast.LENGTH_SHORT).show();
                String URL = "https://Francoaldrete.com/Gymbud/Index.php?mail=";
                URL += mail;
                Intent intentNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                startActivity(intentNavegador);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("yo", error.getMessage());
                Toast.makeText(olvidos.this, "El correo no existe", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue lanzarPeticion = Volley.newRequestQueue(this);
        lanzarPeticion.add(pet);
        lanzarPeticion.start();
    }
}


    public void LinkRegistro(View view){
        String mail = correo.getText().toString();
        Query(mail);
    }
    }
