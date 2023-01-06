package com.example.gymbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.net.Uri;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
EditText ETusr,ETcontra;
Button btnIngreso;
TextView TVRegistro, TVRecuperar;
SharedPreferences archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETusr = findViewById(R.id.etUsuario);
        ETcontra = findViewById(R.id.etContraseña);
        btnIngreso = findViewById(R.id.botonIngresar);
        TVRegistro = findViewById(R.id.TVRegistro);
        TVRecuperar = findViewById(R.id.TVRecuperar);


        TVRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Registro = new Intent(MainActivity.this, Registro.class);
                startActivity(Registro);
                finish();
            }
        });

        TVRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse("https://francoaldrete.com"));
                startActivity(intentNavegador);
            }
        });

        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

    }

    public void LinkRegistrarse(View view) {
        String url = "http://francoaldrete.com/GymBud/bd.php?usr=";
        url = url + ETusr.getText().toString();
        url = url + "&pass=";
        url = url + ETcontra.getText().toString();
        RequestQueue lanzarPeticion= Volley.newRequestQueue(this);
        JsonObjectRequest pet = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String UID,user,mail,password,presence;
                try {
                    UID = response.getString("UID");
                    user = response.getString("User");
                    mail = response.getString("Mail");
                    password = response.getString("Password");
                    presence = response.getString("Presence");
                        Intent i = new Intent(MainActivity.this, infopersonal.class);
                        startActivity(i);
                        finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("yo", error.getMessage());
            }
        });
        lanzarPeticion.add(pet);
    }

    public void clickInicio(View view) {

        Toast.makeText(this, "picon",Toast.LENGTH_SHORT).show();

        String url = "http://francoaldrete.com/GymBud/bd.php?usr=";
        url = url + ETusr.getText().toString();
        url = url + "&pass=";
        url = url + ETcontra.getText().toString();

        JsonObjectRequest pet = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("usr") != -1) {
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        SharedPreferences.Editor editor = archivo.edit();
                        editor.putInt("id_usuario", response.getInt("usr"));
                        editor.commit();
                        startActivity(i);
                        finish();
                    } else {
                        ETusr.setText("");
                        ETcontra.setText("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("yo", error.getMessage());
            }
        });
        RequestQueue lanzarPeticion= Volley.newRequestQueue(this);
        lanzarPeticion.add(pet);
        lanzarPeticion.start();
    }
}