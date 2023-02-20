package com.example.gymbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Contacts;
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
import com.example.gymbud.db.DbQuery;
import com.example.gymbud.db.Entidades.PersonInfo;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
EditText ETusr,ETcontra;
Button btnIngreso, MACABRO;
TextView TVRegistro, TVRecuperar;
SharedPreferences archivo;
PersonInfo personInfo;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int shared;
        SharedPreferences sharedPrefs = getSharedPreferences("MainArchivo",context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        ETusr = findViewById(R.id.etUsuario);
        ETcontra = findViewById(R.id.etContraseña);
        btnIngreso = findViewById(R.id.botonIngresar);
        TVRegistro = findViewById(R.id.TVRegistro);
        TVRecuperar = findViewById(R.id.TVRecuperar);
      //  MACABRO = findViewById(R.id.botonMACABRO);

       /* MACABRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db!=null) {
                    Toast.makeText(MainActivity.this,"SE CREO LA BD",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"NO SE CREO UE",Toast.LENGTH_SHORT).show();
                }
            }
        });
        */


        shared = sharedPrefs.getInt("UID",0);
        if(shared != 0 ){ //este if evita que inicies sesion si anteriormente ya iniciaste sesion
            Intent i = new Intent(MainActivity.this, FragmentContainer.class);
            i.putExtra("UID",shared);
            startActivity(i);
            finish();
        }



        //Este setOnClickListener cuando presionas el boton te manda a la pantalla de registro
        TVRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Registro = new Intent(MainActivity.this, Registro.class);
                startActivity(Registro);
                finish();
            }
        });
        //Este setOnClickListener cuando presionas el boton te manda a la pantalla de olvidos
        TVRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent olvidos = new Intent(MainActivity.this, olvidos.class);
                startActivity(olvidos);
                finish();
            }
        });

        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

    }

    //La funcion ClickInicio lo que hace es que recibe los dos datos ingresados en los editText, para despues comprobar si alguno de estos esta vacio mandar error
    //, si esta completo realiza la query al servidor con el php designado, con la contraseña y usuario registrado, si si esta registrado acacede a la pantalla de
    //info usuario, si no existe manda un error y pide al usuario que reingrese sus credenciales
    public void clickInicio(View view) {
        SharedPreferences sharedPrefs = getSharedPreferences("MainArchivo",context.MODE_PRIVATE);

        final String usuario = ETusr.getText().toString().trim();
        final String correo = ETcontra.getText().toString().trim();

        if (usuario.isEmpty()) {
            Toast.makeText(MainActivity.this, "Ingrese un nombre de usuario para continuar", Toast.LENGTH_SHORT).show();
            return;
        } else if (correo.isEmpty()) {
            Toast.makeText(MainActivity.this, "Ingrese una contraseña para continuar", Toast.LENGTH_SHORT).show();
            return;
        }


        //Toast.makeText(this, "picon",Toast.LENGTH_SHORT).show();

        String url = "http://francoaldrete.com/GymBud/bd.php?usr=";
        url = url + Uri.encode(ETusr.getText().toString());
        url = url + "&pass=";
        url = url + Uri.encode(ETcontra.getText().toString());

        //este toast es pa verificar que el url se cree bien

        //Toast.makeText(this, url,Toast.LENGTH_SHORT).show();

        JsonObjectRequest pet = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(MainActivity.this,"Bienvenido "+ response.getString("User"),Toast.LENGTH_SHORT).show();
                    if (response.getInt("UID") != -1) {
                        int UID = response.getInt("UID");
                        DbQuery dbQuery = new DbQuery(MainActivity.this);
                        personInfo = dbQuery.verinfo(UID);
                        if (personInfo != null) {
                            Intent i = new Intent(MainActivity.this, FragmentContainer.class);
                            i.putExtra("UID",UID);


                            SharedPreferences.Editor editor = sharedPrefs.edit();
                            editor.putInt("UID", UID);
                            editor.commit();


                            startActivity(i);
                            finish();

                        } else {

                            long id = dbQuery.InsertarInfoPerson(UID, 0, 0, 0.00, 0.00, 0.00, 0, 0, "abubu");
                            SharedPreferences.Editor editor = sharedPrefs.edit();
                            editor.putInt("UID", UID);
                            editor.commit();

                            Intent i = new Intent(MainActivity.this, FragmentContainer.class);
                            i.putExtra("UID",UID);
                            startActivity(i);
                            finish();

                        }
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
    //Esta funcion es en caso de que presionen que se quieren registrar, los manda a la pantalla registro
    public void LinkRegistrarse(View view) {
        Intent Registro = new Intent(MainActivity.this, Registro.class);
        startActivity(Registro);
        finish();

    }
}