package com.example.gymbud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.gymbud.Adaptadores.JsonPreguntas;
import com.example.gymbud.db.DbQuery;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Encuesta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Encuesta extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Encuesta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Encuesta.
     */
    // TODO: Rename and change types and number of parameters
    public static Encuesta newInstance(String param1, String param2) {
        Encuesta fragment = new Encuesta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_encuesta, container, false);
    }

    public int[] Resultado = new int[20];

    TextView Pregunta1;
    RadioButton  Respuesta1, Respuesta2, Respuesta3, Respuesta4;
    Button Confirmado;
    int i=1;
    int pos = 0;
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Resultado= new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0};
        super.onViewCreated(view, savedInstanceState);
        RutinaAuto();
        Pregunta1 = view.findViewById(R.id.Pregunta);
        Respuesta1 = view.findViewById(R.id.Respuesta1);
        Respuesta2 = view.findViewById(R.id.Respuesta2);
        Respuesta3 = view.findViewById(R.id.Respuesta3);
        Respuesta4 = view.findViewById(R.id.Respuesta4);
        Confirmado = view.findViewById(R.id.Acepto);
        datos(i);
        Respuesta1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Respuesta2.setChecked(false);
                    Respuesta3.setChecked(false);
                    Respuesta4.setChecked(false);
                }
            }
        });
        Respuesta2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Respuesta1.setChecked(false);
                    Respuesta3.setChecked(false);
                    Respuesta4.setChecked(false);
                }
            }
        });
        Respuesta3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Respuesta1.setChecked(false);
                    Respuesta2.setChecked(false);
                    Respuesta4.setChecked(false);
                }
            }
        });
        Respuesta4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Respuesta1.setChecked(false);
                    Respuesta2.setChecked(false);
                    Respuesta3.setChecked(false);
                }
            }
        });

        Confirmado.setOnClickListener(new View.OnClickListener() {
            int dias=0;
            int l=1;
            @Override
            public void onClick(View v) {
                switch (i) {

                    case 25:
                        pos=4;
                        break;

                        case 39:
                        pos=8;
                        break;

                    case 41:

                        if(Resultado[pos]==1){
                            dias=3;
                        }else if(Resultado[pos]==2) {
                            dias = 5;
                        }else{
                            dias=6;
                        }
                        break;

                    case 44:
                        int[] TiempoxDia = new int[dias];
                            Pregunta1.setText("¿Cuanto tiempo tienes para entrenar el dia " + l + "?");
                            if(Respuesta1.isChecked()){
                                TiempoxDia[l] = 1;
                            }else if(Respuesta2.isChecked()){
                                TiempoxDia[l] = 2;
                            }else if(Respuesta3.isChecked()){
                                TiempoxDia[l] = 3;
                            }
                            Log.d("Tiempo", "onClick: " + TiempoxDia[l]);
                        Log.d("Tiempo", "ele: " + l);
                            l++;
                        break;

                        case 56:
                        pos=14;
                        break;

                        case 69:
                        pos=17;
                        break;
                }
                if (l<dias && i==44) {
                    Log.d("Entre", "ESTOY DENTRO");
                }
                else {
                    if (Respuesta1.isChecked()) {
                        i += opcion1;
                        Resultado[pos] = 1;
                        pos += 1;
                    } else if (Respuesta2.isChecked()) {
                        i += opcion2;
                        Resultado[pos] = 2;
                        pos += 1;
                    } else if (Respuesta3.isChecked()) {
                        i += opcion3;
                        Resultado[pos] = 3;
                        pos += 1;
                    } else if (Respuesta4.isChecked()) {
                        i += opcion4;
                        Resultado[pos] = 4;
                        pos += 1;
                    }
                }


                Respuesta1.setChecked(false);
                Respuesta2.setChecked(false);
                Respuesta3.setChecked(false);
                Respuesta4.setChecked(false);
                datos(i);
                Log.d("I", ""+i);
                for(int i=0;i<Resultado.length;i++) {
                    Log.d("Resultado", "Seleccion "+i +" "+ Resultado[i]);
                }
                if(i==70){
                    Bundle bundle = new Bundle();
                    bundle.putIntArray("Resultado", Resultado);
                    Fragment firstFragment = new Rutinas();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    transaction.replace(R.id.navFragmentContainer, firstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }



            }

        });
    }
    private int opcion1, opcion2, opcion3, opcion4;
    public void datos(int Pregunta) {
        try {
            String contenidoJson = JsonPreguntas.leerJson(getContext());
            JsonParser parser = new JsonParser();
            JsonElement elemento = parser.parse(contenidoJson);
            JsonObject jsonObject = elemento.getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("JsonPreguntas");


            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = jsonArray.get(i).getAsJsonObject();
                int id = jsonObject.get("NumPregunta").getAsInt();
                if (Pregunta == id) {


                    String pregunta = jsonObject.get("pregunta").getAsString();
                    Pregunta1.setText(pregunta);

                    String respuesta1 = jsonObject.get("Respuesta1").getAsString();
                    Respuesta1.setText(respuesta1);
                     opcion1 = jsonObject.get("opcion1").getAsInt();


                    String respuesta2 = jsonObject.get("Respuesta2").getAsString();
                    Respuesta2.setText(respuesta2);
                     opcion2 = jsonObject.get("opcion2").getAsInt();


                    String respuesta3 = jsonObject.get("Respuesta3").getAsString();
                    if(respuesta3.equals("")){
                        Respuesta3.setVisibility(View.INVISIBLE);
                    }else{
                        Respuesta3.setVisibility(View.VISIBLE);
                    }
                    Respuesta3.setText(respuesta3);
                     opcion3 = jsonObject.get("opcion3").getAsInt();


                    String respuesta4 = jsonObject.get("Respuesta4").getAsString();
                    Respuesta4.setText(respuesta4);
                    if(respuesta4.equals("")){
                        Respuesta4.setVisibility(View.INVISIBLE);
                    }else{
                        Respuesta4.setVisibility(View.VISIBLE);
                    }
                     opcion4 = jsonObject.get("opcion4").getAsInt();

                    Log.d("Pregunta", pregunta);
                    Log.d("Respuesta1", respuesta1);
                    Log.d("opcion1", String.valueOf(opcion1));
                    Log.d("Respuesta2", respuesta2);
                    Log.d("opcion2", String.valueOf(opcion2));
                    Log.d("Respuesta3", respuesta3);
                    Log.d("opcion3", String.valueOf(opcion3));
                    Log.d("Respuesta4", respuesta4);
                    Log.d("opcion4", String.valueOf(opcion4));
                    break;
                }
            }
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            e.printStackTrace();
        }
    }

    public void RutinaAuto(){
        DbQuery dbQuery = new DbQuery(getContext());
        int[] ids = new int[40];
        for(int i=0;i<ids.length;i++){
            ids[i]=0;
        }
        ids = dbQuery.EjerciciosID(1,3,2,2);
        for(int i=0;i<ids.length;i++){
            Log.d("Ejercicios", "Ejercicio "+i+" "+ids[i]);
        }
    }
}