package com.example.gymbud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {
    EditText ETusuario, ETcontrasena, ETcontrasenaconf, ETcorreo;
    Button Bconfirmar;
    boolean rancio ;

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

    public boolean verificar(String usuario){

        String urlv = "http://francoaldrete.com/GymBud/verificacion.php?usr=";
        urlv += usuario;

        StringRequest request = new StringRequest(Request.Method.GET, urlv, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("El usuario ya existe")) {
                    Toast.makeText(Registro.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    rancio = false;
                } else if (response.equalsIgnoreCase("El usuario no existe")){
                    Toast.makeText(Registro.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                    rancio = true;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue peti= Volley.newRequestQueue(Registro.this);
        peti.add(request);
        peti.start();

        return rancio;
    }

    public void confirmar(View view) {


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando pa");

            final String usuario = ETusuario.getText().toString().trim();
            final String correo = ETcorreo.getText().toString().trim();
            final String con1 = ETcontrasena.getText().toString().trim();
            final String con2 = ETcontrasenaconf.getText().toString().trim();

        //do {
            if (usuario.isEmpty()) {
                Toast.makeText(Registro.this, "Ingrese su nombre de usuario", Toast.LENGTH_SHORT).show();
                return;
            } else if (correo.isEmpty()) {
                Toast.makeText(Registro.this, "Ingrese su correo electronico", Toast.LENGTH_SHORT).show();
                return;
            } else if (con1.isEmpty()) {
                Toast.makeText(Registro.this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                return;
            } else if (con2.isEmpty()) {
                Toast.makeText(Registro.this, "Ingrese la confirmacion de su contraseña", Toast.LENGTH_SHORT).show();
                return;
            } else if (!TextUtils.equals(con1, con2)) {
                Toast.makeText(Registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(Registro.this, "Ingresa un correo valido", Toast.LENGTH_SHORT).show();
                return;
            }else if (!verificar(usuario)){
                Toast.makeText(Registro.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                rancio =  true;
                return;
            }





            progressDialog.show();

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


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("yo", error.getMessage());
            }
        });
        RequestQueue lanzarPeticion = Volley.newRequestQueue(this);
        lanzarPeticion.add(pet);
        lanzarPeticion.start();
        progressDialog.dismiss();
        Toast.makeText(Registro.this, "Te has registrado exitosamente", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Registro.this, MainActivity.class);
        startActivity(i);
        finish();


    }
}