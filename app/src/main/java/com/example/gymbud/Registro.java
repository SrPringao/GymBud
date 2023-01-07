package com.example.gymbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {
    EditText ETusuario,ETcontrasena,ETcontrasenaconf,ETcorreo;
    Button Bconfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ETusuario = findViewById(R.id.ETusuario);
        ETcontrasena = findViewById(R.id.ETcontrasena);
        ETcontrasenaconf = findViewById(R.id.ETcontrasenaconf);
        ETcorreo = findViewById(R.id.ETcorreo);
        Bconfirmar = findViewById(R.id.Bconfirmar);


    }

    public void confirmar (View view){
        Toast.makeText(Registro.this,ETcontrasena.getText().toString() + "    " + ETcontrasenaconf.getText().toString(),Toast.LENGTH_SHORT).show();
        if (ETcontrasena.getText().toString() != ETcontrasenaconf.getText().toString())
        {
            String url = "http://francoaldrete.com/GymBud/insert.php?usr=";
            url = url + ETusuario.getText().toString();
            url = url + "&mail=";
            url = url + ETcorreo.getText().toString();
            url = url + "&pass=";
            url = url + ETcontrasena.getText().toString();
            JsonObjectRequest pet = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getInt("") != -1) {
                            Intent i = new Intent(Registro.this, MainActivity.class);
                            startActivity(i);
                            finish();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_SHORT).show(); //Esta mamada muestra todos los datos del user
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
        else{
            Toast.makeText(Registro.this,"Las contraseñas no son iguales",Toast.LENGTH_SHORT).show();
        }
    }
}