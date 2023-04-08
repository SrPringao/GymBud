package com.example.gymbud.Modulos.CreacionDeRutinas.RutinasAutomaticas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.gymbud.Adaptadores.JsonPreguntas;
import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.R;
import com.example.gymbud.Modulos.CreacionDeRutinas.Rutinas;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Encuesta#newInstance} factory method to
 * create an instance of this fragment.
 */

import java.util.HashMap;

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

    public int[] Resultadosinprocesar = new int[20];

    TextView Pregunta1;
    RadioButton  Respuesta1, Respuesta2, Respuesta3, Respuesta4;
    Button Confirmado;
    int i=1;
    int pos = 0;
    int[] TiempoxDia;
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Resultadosinprocesar = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        super.onViewCreated(view, savedInstanceState);
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
                        pos=7;
                        break;

                    case 41:

                        if(Resultadosinprocesar[pos]==1){
                            dias=3;
                        }else if(Resultadosinprocesar[pos]==2) {
                            dias = 5;
                        }else{
                            dias=6;
                        }
                        break;

                    case 44:
                        TiempoxDia = new int[dias];
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
                        pos=12;
                        break;

                    case 69:
                        pos=15;
                        break;
                }
                if (l<dias && i==44) {
                    Log.d("Entre", "ESTOY DENTRO");
                }
                else {
                    if (Respuesta1.isChecked()) {
                        i += opcion1;
                        Resultadosinprocesar[pos] = 1;
                        pos += 1;
                    } else if (Respuesta2.isChecked()) {
                        i += opcion2;
                        Resultadosinprocesar[pos] = 2;
                        pos += 1;
                    } else if (Respuesta3.isChecked()) {
                        i += opcion3;
                        Resultadosinprocesar[pos] = 3;
                        pos += 1;
                    } else if (Respuesta4.isChecked()) {
                        i += opcion4;
                        Resultadosinprocesar[pos] = 4;
                        pos += 1;
                    }
                }


                Respuesta1.setChecked(false);
                Respuesta2.setChecked(false);
                Respuesta3.setChecked(false);
                Respuesta4.setChecked(false);
                datos(i);
                Log.d("I", ""+i);
                for(int i = 0; i< Resultadosinprocesar.length; i++) {
                    int n = i+1;
                    Log.d("Resultado", "Seleccion "+ n +" "+ Resultadosinprocesar[i]);
                }
                if(i==70){
                    RutinaAuto(Resultadosinprocesar,TiempoxDia);
                    Bundle bundle = new Bundle();
                    bundle.putIntArray("Resultado", Resultadosinprocesar);
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

    public void RutinaAuto(int[] ResultadosSinProcesar,int[] TiempoxDia) {
        //Primer resultado es el sexo del usuario(1 es hombre, 2 es mujer)
        //el segundo es si padece de alguna enfermedad(1 es si, 2 es no)
        // el tercero es si la enfermedad le dificulta el entrenamiento(1 si 2 no)
        //El cuarto es en que tren le dificulta la enfermedad: 1 tren superior, 2 tren inferior, 3 ejercicios cardiovasculares,4 todos.
        //La cinco es si tiene una lesion(1 si 2 no)
        //el sexto es en que tren se encuentra lesionado: 1 tren superior, 2 tren inferior.
        //la siete depende si escogio 1:(Es tren superior por lo que las opciones son: 1: Hombro, 2:Pecho, 3:Brazo, 4:espalda)
        //Si escogio 2:(Es tren inferior por lo que las opciones son: 1: Rodilla, 2:Tobillo, 3:Isquios, 4:Cuadriceps)
        //la octava es la experiencia en el gimnasio: 1:Novato, 2:Intermedio, 3:Avanzado
        //La novena es su condicion de fisica actual: 1:Bajo, 2:Medio/Alto
        //La decima es cuantos dias entrena: 1:3 2:5 3:6
        //La once pregunta si dispone del mismo tiempo para entrenar todos los dias: 1:Si 2:No
        //La doce pregunta Cuanto tiempo dispone para hacer ejercicio: 1:Menos de 60 mins, 2:Entre 60 y 90 mins, 3:Mas de 90 mins
        //O en caso de que haya seleccionado 2 en la doce pregunta: 1:Entre 30 y 60 mins, 2:Entre 60 y 90 mins, 3:Mas de 90 mins y se repite la pregunta la cantidad de veces que se selecciono en la pregunta 11
        //La trece pregunta su objetivo: 1:Ganar masa muscular, 2:Ganar Fuerza, 3:Perder peso, 4:Mantener la forma física actual
        //La catorce pregunta en que musculos desea enfocarse: 1:Tren superior, 2:Tren inferior, 3:Cuerpo completo.
        //La quince pregunta si desea agregar abdomen a la rutina: 1:Si 2:No.
        //La dieciseis pregunta que equipo preferiria usar a la hora de entrenar: 1:Mancuernas, 2:Poleas, 3:Barras, 4:Maquinas




        //Crear la query concatenando strings dependiendo del usuario
        DbQuery dbQuery = new DbQuery(getContext());
        int[] ids;
        boolean EjerciciosCapaces=false; //Esta variable es para saber si se disminuyen los ejercicios que se le van a asignar o se quitar completamente.
        boolean Lesionado=false; //Esta variable es para saber si el usuario esta lesionado y asi no asignarle ejercicios que le puedan causar mas daño.
        boolean MismoTiempo=false; //Esta variable es para saber si el usuario dispone del mismo tiempo para entrenar todos los dias.
        int CantEjercicios=0;
        String MusculoLesionado="";
        if(Resultadosinprocesar[1]==1) {
            if (Resultadosinprocesar[2]==1){
                EjerciciosCapaces=true;
            }else{
                EjerciciosCapaces=false;
            }
            //Acceder a la posicion 3 y agarrar el tren que se dificulta
        }
        if(Resultadosinprocesar[4]==1) {
            Lesionado = true;
            if(Resultadosinprocesar[5]==1) {
                switch (Resultadosinprocesar[6]) {
                    case 1:
                        MusculoLesionado= "Hombro";
                        break;
                    case 2:
                        MusculoLesionado= "Pecho";
                        break;
                    case 3:
                        MusculoLesionado= "Brazo";
                        break;
                    case 4:
                        MusculoLesionado= "Espalda";
                        break;
                }
            }else {
                switch (Resultadosinprocesar[6]) {
                    case 1:
                        MusculoLesionado = "Rodilla";
                        break;
                    case 2:
                        MusculoLesionado = "Tobillo";
                        break;
                    case 3:
                        MusculoLesionado = "Isquios";
                        break;
                    case 4:
                        MusculoLesionado = "Cuadriceps";
                        break;
                }
            }
        }
        //acceder a la posicion 7 del arreglo y agarrar la experiencia del usuario
//acceder a la posicion 8 del arreglo y agarrar la condicion fisica del usuario
//acceder a la posicion 9 del arreglo y agarrar los dias que entrena
        HashMap<Integer,Integer> Tiempos = new HashMap<>();
        Tiempos.put(1,6);
        Tiempos.put(2,8);
        Tiempos.put(3,12);
        if(Resultadosinprocesar[10]==1) {

            CantEjercicios=Tiempos.get(ResultadosSinProcesar[12]);

        }else{

            int Lunes,Martes,Miercoles,Jueves,Viernes,Sabado;
            for(int i=0;i<TiempoxDia[11];i++){
                switch (i){
                    case 0:
                        Lunes=Tiempos.get(TiempoxDia[i]);
                        break;
                    case 1:
                        Martes=Tiempos.get(TiempoxDia[i]);
                        break;
                    case 2:
                        Miercoles=Tiempos.get(TiempoxDia[i]);
                        break;
                    case 3:
                        Jueves=Tiempos.get(TiempoxDia[i]);
                        break;
                    case 4:
                        Viernes=Tiempos.get(TiempoxDia[i]);
                        break;
                    case 5:
                        Sabado=Tiempos.get(TiempoxDia[i]);
                        break;
                }
            }
        }
        int Reps=0;
        int Series=0;
        boolean abs=false;
        if(Resultadosinprocesar[12]==1||Resultadosinprocesar[12]==3||Resultadosinprocesar[12]==4) {
            Series=4;
            Reps=12;
        }else{
            Series=4;
            Reps=8;
        }
        if(Resultadosinprocesar[14]==1)
        {
            abs=true;
        }

        ids = dbQuery.EjerciciosID(1,3,2,2);
        for(int i=0;i<ids.length;i++){
            Log.d("Ejercicios", "Ejercicio "+i+" "+ids[i]);
        }
    }
}