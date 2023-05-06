package com.example.gymbud.Modulos.CreacionDeRutinas.RutinasAutomaticas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gymbud.Adaptadores.JsonPreguntas;
import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.Entidades.ExerciseSet;
import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.Routine;
import com.example.gymbud.Modulos.CreacionDeRutinas.Rutinas;
import com.example.gymbud.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
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
    boolean resp1=false, resp2=false, resp3=false, resp4=false;
    TextView Pregunta1;
    RadioButton  Respuesta1, Respuesta2, Respuesta3, Respuesta4;
    Button Confirmado;

    int i=1;
    int pos = 0;
    int[] TiempoxDia;
    int[] MusculoLesionado=new int[4];
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Resultadosinprocesar = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0};
        super.onViewCreated(view, savedInstanceState);
        Pregunta1 = view.findViewById(R.id.Pregunta);
        Respuesta1 = view.findViewById(R.id.Respuesta1);
        Respuesta2 = view.findViewById(R.id.Respuesta2);
        Respuesta3 = view.findViewById(R.id.Respuesta3);
        Respuesta4 = view.findViewById(R.id.Respuesta4);
        Confirmado = view.findViewById(R.id.Acepto);

        ImageView BotonBack = view.findViewById(R.id.EncuestaBack);

        BotonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstFragment = new Rutinas();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        datos(i);
        Respuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i!=27&&i!=28) {
                    Respuesta2.setChecked(false);
                    Respuesta3.setChecked(false);
                    Respuesta4.setChecked(false);
                    resp2=false;
                    resp3=false;
                    resp4=false;
                }
                if(resp1==true && Respuesta1.isChecked()){
                    Respuesta1.setChecked(false);
                    resp1=false;
                    return;
                }
                resp1=true;
            }
        });
//            Respuesta1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    Log.d("IsChecked", isChecked + "");
//                    if(i!=27&&i!=28){
//
//                        if (isChecked) {
//                            Respuesta2.setChecked(false);
//                            Respuesta3.setChecked(false);
//                            Respuesta4.setChecked(false);
//                        }
//                    }
//                    Log.d("IsChecked", isChecked + "");
//                }
//            });

        Respuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i!=27&&i!=28) {
                    Respuesta1.setChecked(false);
                    Respuesta3.setChecked(false);
                    Respuesta4.setChecked(false);
                    resp1=false;
                    resp3=false;
                    resp4=false;
                }
                if(resp2==true && Respuesta2.isChecked()){
                    Respuesta2.setChecked(false);
                    resp2=false;
                    return;
                }
                resp2=true;
            }
        });
//            Respuesta2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(i!=27&&i!=28) {
//                        if (isChecked) {
//                            Respuesta1.setChecked(false);
//                            Respuesta3.setChecked(false);
//                            Respuesta4.setChecked(false);
//                        }
//                    }
//                }
//            });
        Respuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i!=27&&i!=28) {
                    Respuesta2.setChecked(false);
                    Respuesta1.setChecked(false);
                    Respuesta4.setChecked(false);
                    resp1=false;
                    resp2=false;
                    resp4=false;
                }
                if(resp3==true && Respuesta3.isChecked()){
                    Respuesta3.setChecked(false);
                    resp3=false;
                    return;
                }
                resp3=true;
            }
        });
//            Respuesta3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(i!=27&&i!=28) {
//                        if (isChecked) {
//                            Respuesta1.setChecked(false);
//                            Respuesta2.setChecked(false);
//                            Respuesta4.setChecked(false);
//                        }
//                    }
//                }
//            });
        Respuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i!=27&&i!=28) {
                    Respuesta2.setChecked(false);
                    Respuesta3.setChecked(false);
                    Respuesta1.setChecked(false);
                    resp1=false;
                    resp2=false;
                    resp3=false;
                }
                if(resp4==true && Respuesta4.isChecked()){
                    Respuesta4.setChecked(false);
                    resp4=false;
                    return;
                }
                resp4=true;
            }
                                      });
//            Respuesta4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(i!=27&&i!=28) {
//                        if (isChecked) {
//                            Respuesta1.setChecked(false);
//                            Respuesta2.setChecked(false);
//                            Respuesta3.setChecked(false);
//                        }
//                    }
//                }
//            });


        Confirmado.setOnClickListener(new View.OnClickListener() {
            int dias=0;
            int l=0;
            @Override
            public void onClick(View v) {
                switch (i) {

                    case 25:
                        pos = 4;
                        break;

                    case 27:
                        if (Respuesta1.isChecked()||Respuesta4.isChecked()) {
                            MusculoLesionado[0]=7;
                        }  if (Respuesta2.isChecked()) {
                        MusculoLesionado[1] = 15;
                    } if(Respuesta3.isChecked()) {
                        MusculoLesionado[2] = 16;
                    }
                        break;
                    case 28:
                        if (Respuesta1.isChecked()) {
                             MusculoLesionado[0]=1;

                        }  if (Respuesta2.isChecked()) {
                            MusculoLesionado[1] = 3;
                        } if(Respuesta3.isChecked()) {
                            MusculoLesionado[2] = 2;
                        }if(Respuesta4.isChecked()){
                            MusculoLesionado[3]=11;
                        }

                    case 39:
                        pos = 7;
                        break;

                    case 41:

                        if (Respuesta1.isChecked()) {
                            dias = 3;

                        } else if (Respuesta2.isChecked()) {
                            dias = 5;
                        } else {
                            dias = 6;
                        }
                        TiempoxDia = new int[dias + 1];
                        break;

                    case 44:
                    case 42:
                        int n=l+1;

                        Pregunta1.setText("¿Cuanto tiempo tienes para entrenar el dia " + n + "?");
                        if (Respuesta1.isChecked()) {
                            TiempoxDia[l] = 1;
                        } else if (Respuesta2.isChecked()) {
                            TiempoxDia[l] = 2;
                        } else if (Respuesta3.isChecked()) {
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
                if (l<=dias && i==44) {
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
                resp1=false;
                resp2=false;
                resp3=false;
                resp4=false;
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
                    if(Pregunta==44) {
                    }else {
                        Pregunta1.setText(pregunta);
                    }

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

    public void  RutinaAuto(int[] ResultadosSinProcesar,int[] TiempoxDia) {
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


        //Notas:
        //Buscar los ids de los ejercicios que se van a usar
        //Insertar los ids a la tabla de las rutinas

        //Crear la query concatenando strings dependiendo del usuario

        DbQuery dbQuery = new DbQuery(getContext());

        boolean Enfermedad = false; //Esta variable es para saber si el usuario tiene alguna enfermedad que le dificulte el entrenamiento.
        boolean EjerciciosCapaces=false; //Esta variable es para saber si se disminuyen los ejercicios que se le van a asignar o se quitar completamente.
        boolean Lesionado=false; //Esta variable es para saber si el usuario esta lesionado y asi no asignarle ejercicios que le puedan causar mas daño.
        int CantEjercicios=0;


        int Reps=0;
        int Series=0;
        boolean abs=false;

        HashMap<Integer,Integer> Tiempos = new HashMap<>();
        Tiempos.put(1,6);
        Tiempos.put(2,10);
        Tiempos.put(3,12);
        int Dia1=0,Dia2=0,Dia3=0,Dia4=0,Dia5=0,Dia6=0;
        //acceder a la posicion 9 del arreglo y agarrar los dias que en trena
        if(Resultadosinprocesar[10]==1) {

            CantEjercicios=Tiempos.get(ResultadosSinProcesar[11]);

        }else{

            for (int i = 1; i < TiempoxDia.length; i++) {
                switch (i) {
                    case 1:
                        Dia1 = Tiempos.get(TiempoxDia[i]);
                        Log.d("Dia1", String.valueOf(Dia1));
                        break;
                    case 2:
                        Dia2 = Tiempos.get(TiempoxDia[i]);
                        Log.d("Dia2", String.valueOf(Dia2));
                        break;
                    case 3:
                        Dia3 = Tiempos.get(TiempoxDia[i]);
                        Log.d("Dia3", String.valueOf(Dia3));
                        break;
                    case 4:
                        Dia4 = Tiempos.get(TiempoxDia[i]);
                        Log.d("Dia4", String.valueOf(Dia4));
                        break;
                    case 5:
                        Dia5 = Tiempos.get(TiempoxDia[i]);
                        Log.d("Dia5", String.valueOf(Dia5));
                        break;
                    case 6:
                        Dia6 = Tiempos.get(TiempoxDia[i]);
                        Log.d("Dia6", String.valueOf(Dia6));
                        break;
                }
            }
        }

        if(Resultadosinprocesar[1]==1) {
            Enfermedad=true;
            if (Resultadosinprocesar[2]==1){
                EjerciciosCapaces=true;
            }else{
                EjerciciosCapaces=false;
            }
            //Acceder a la posicion 3 y agarrar el tren que se dificulta
        }
        if(Resultadosinprocesar[4]==1) {
            Lesionado = true;
        }
        //acceder a la posicion 7 del arreglo y agarrar la experiencia del usuario
        //acceder a la posicion 8 del arreglo y agarrar la condicion fisica del usuario



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
        ArrayList<ExerciseSet> listaCompleta = new ArrayList<>();
        dbQuery.deleteRoutines();
        switch (ResultadosSinProcesar[9]) {

            case 1:
                if(EjerciciosCapaces==true && Resultadosinprocesar[3]==1){
                    listaCompleta.addAll(Dias(3,7,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,false,CantEjercicios,Dia1,2,Series,Reps));
                    listaCompleta.addAll(Dias(3,13,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,false,CantEjercicios,Dia1,2,Series,Reps));
                    AgregarRutina("Lunes",1,listaCompleta);
                    listaCompleta.clear();
                    //Dia 2
                    listaCompleta.addAll(Dias(3,14,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,false,CantEjercicios,Dia2,2,Series,Reps));
                    if(abs==true)
                    {
                        listaCompleta.addAll(Dias(3,4,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia2,2,Series,Reps));
                    }
                    AgregarRutina("Miercoles",3,listaCompleta);
                    listaCompleta.clear();
                    //Dia 3
                    listaCompleta.addAll(Dias(3,15,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,false,CantEjercicios,Dia3,2,Series,Reps));
                    listaCompleta.addAll(Dias(3,13,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,false,CantEjercicios,Dia3,2,Series,Reps));
                    AgregarRutina("Viernes",5,listaCompleta);
                    listaCompleta.clear();
                    break;
                }
                //Dia 1
                listaCompleta.addAll(Dias(3,3,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia1,1,Series,Reps));
                listaCompleta.addAll(Dias(3,1,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia1,1,Series,Reps));
                listaCompleta.addAll(Dias(3,10,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia1,1,Series,Reps));
                AgregarRutina("Lunes",1,listaCompleta);
                listaCompleta.clear();
                //Dia 2
                listaCompleta.addAll(Dias(2,2,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia2,1,Series,Reps));
                listaCompleta.addAll(Dias(2,11,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia2,1,Series,Reps));
                if(abs==true)
                {
                    listaCompleta.addAll(Dias(3,4,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia2,2,Series,Reps));
                }
                AgregarRutina("Miercoles",3,listaCompleta);
                listaCompleta.clear();
                //Dia 3
                listaCompleta.addAll(Dias(4,14,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia3,2,Series,Reps));
                listaCompleta.addAll(Dias(4,7,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia3,2,Series,Reps));
                listaCompleta.addAll(Dias(4,15,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia3,2,Series,Reps));
                listaCompleta.addAll(Dias(4,13,ResultadosSinProcesar,EjerciciosCapaces,Lesionado,MusculoLesionado,Enfermedad,CantEjercicios,Dia3,2,Series,Reps));
                AgregarRutina("Viernes",5,listaCompleta);
                listaCompleta.clear();
                break;

            case 2:
                if(EjerciciosCapaces==true && Resultadosinprocesar[3]==1){
                    listaCompleta.addAll(Dias(3, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia1, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia1, 2, Series, Reps));
                    AgregarRutina("Lunes", 1, listaCompleta);
                    listaCompleta.clear();
                    //Dia 2
                    listaCompleta.addAll(Dias(3, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia2, 2, Series, Reps));
                    if (abs == true) {
                        listaCompleta.addAll(Dias(3, 4, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia2, 2, 4, 12));
                    }
                    AgregarRutina("Martes", 2, listaCompleta);
                    listaCompleta.clear();
                    //Dia 3
                    listaCompleta.addAll(Dias(3, 15, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia3, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia3, 2, Series, Reps));
                    AgregarRutina("Miercoles", 3, listaCompleta);
                    listaCompleta.clear();
                    //Dia 4
                    listaCompleta.addAll(Dias(3, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia4, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia4, 2, Series, Reps));
                    AgregarRutina("Jueves", 4, listaCompleta);
                    listaCompleta.clear();
                    //Dia 5
                    listaCompleta.addAll(Dias(3, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia5, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 15, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia5, 2, Series, Reps));
                    if (abs == true) {
                        listaCompleta.addAll(Dias(3, 4, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia5, 2, 4, 12));
                    }
                    AgregarRutina("Viernes", 5, listaCompleta);
                    listaCompleta.clear();
                }else {
                    //Dia 1
                    listaCompleta.addAll(Dias(5, 3, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia1, 1, Series, Reps));
                    listaCompleta.addAll(Dias(5, 1, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia1, 1, Series, Reps));
                    listaCompleta.addAll(Dias(5, 10, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia1, 1, Series, Reps));
                    listaCompleta.addAll(Dias(5, 2, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia1, 1, Series, Reps));
                    listaCompleta.addAll(Dias(5, 11, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia1, 1, Series, Reps));
                    AgregarRutina("Lunes", 1, listaCompleta);
                    listaCompleta.clear();
                    //Dia 2
                    listaCompleta.addAll(Dias(4, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia2, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia2, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 15, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia2, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia2, 2, Series, Reps));
                    if (abs == true) {
                        listaCompleta.addAll(Dias(5, 4, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia2, 1, 4, 12));
                    }
                    AgregarRutina("Martes", 2, listaCompleta);
                    listaCompleta.clear();
                    //Dia 3
                    listaCompleta.addAll(Dias(3, 3, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia3, 1, Series, Reps));
                    listaCompleta.addAll(Dias(3, 1, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia3, 1, Series, Reps));
                    listaCompleta.addAll(Dias(3, 10, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia3, 1, Series, Reps));
                    AgregarRutina("Miercoles", 3, listaCompleta);
                    listaCompleta.clear();
                    //Dia 4
                    listaCompleta.addAll(Dias(2, 2, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia4, 1, Series, Reps));
                    listaCompleta.addAll(Dias(2, 11, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia4, 1, Series, Reps));
                    if (abs == true) {
                        listaCompleta.addAll(Dias(3, 4, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia4, 1, 4, 12));
                    }
                    AgregarRutina("Jueves", 4, listaCompleta);
                    listaCompleta.clear();
                    //Dia 5
                    listaCompleta.addAll(Dias(4, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia5, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia5, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 15, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia5, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia5, 2, Series, Reps));
                    AgregarRutina("Viernes", 5, listaCompleta);
                    listaCompleta.clear();
                }
                break;

            case 3:
                if(EjerciciosCapaces==true && Resultadosinprocesar[3]==1){
                    listaCompleta.addAll(Dias(3, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia1, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia1, 2, Series, Reps));
                    AgregarRutina("Lunes", 1, listaCompleta);
                    listaCompleta.clear();
                    //Dia 2
                    listaCompleta.addAll(Dias(3, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia2, 2, Series, Reps));
                    if (abs == true) {
                        listaCompleta.addAll(Dias(3, 4, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia2, 2, 4, 12));
                    }
                    AgregarRutina("Martes", 2, listaCompleta);
                    listaCompleta.clear();
                    //Dia 3
                    listaCompleta.addAll(Dias(3, 15, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia3, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia3, 2, Series, Reps));
                    AgregarRutina("Miercoles", 3, listaCompleta);
                    listaCompleta.clear();
                    //Dia 4
                    listaCompleta.addAll(Dias(3, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia4, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia4, 2, Series, Reps));
                    AgregarRutina("Jueves", 4, listaCompleta);
                    listaCompleta.clear();
                    //Dia 5
                    listaCompleta.addAll(Dias(3, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia5, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 15, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia5, 2, Series, Reps));
                    if (abs == true) {
                        listaCompleta.addAll(Dias(3, 4, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia5, 2, 4, 12));
                    }
                    AgregarRutina("Viernes", 5, listaCompleta);
                    listaCompleta.clear();
                    //Dia 6
                    listaCompleta.addAll(Dias(3, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia6, 2, Series, Reps));
                    listaCompleta.addAll(Dias(3, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, false, CantEjercicios, Dia6, 2, Series, Reps));
                    AgregarRutina("Sabado", 6, listaCompleta);
                    listaCompleta.clear();





                }else {
                    listaCompleta.addAll(Dias(3, 3, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia1, 1, Series, Reps));
                    listaCompleta.addAll(Dias(3, 1, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia1, 1, Series, Reps));
                    listaCompleta.addAll(Dias(3, 10, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia1, 1, Series, Reps));
                    AgregarRutina("Lunes", 1, listaCompleta);
                    listaCompleta.clear();
                    //Dia 2
                    listaCompleta.addAll(Dias(2, 2, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia2, 1, Series, Reps));
                    listaCompleta.addAll(Dias(2, 11, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia2, 1, Series, Reps));
                    if (abs == true) {
                        listaCompleta.addAll(Dias(3, 4, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia2, 1, 4, 12));
                    }
                    AgregarRutina("Martes", 2, listaCompleta);
                    listaCompleta.clear();
                    //Dia 3
                    listaCompleta.addAll(Dias(4, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia3, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia3, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 15, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia3, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia3, 2, Series, Reps));
                    AgregarRutina("Miercoles", 3, listaCompleta);
                    listaCompleta.clear();
                    //Dia 4
                    listaCompleta.addAll(Dias(3, 3, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia4, 1, Series, Reps));
                    listaCompleta.addAll(Dias(3, 1, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia4, 1, Series, Reps));
                    listaCompleta.addAll(Dias(3, 10, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia4, 1, Series, Reps));
                    AgregarRutina("Jueves", 4, listaCompleta);
                    listaCompleta.clear();
                    //Dia 5
                    listaCompleta.addAll(Dias(2, 2, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia5, 1, Series, Reps));
                    listaCompleta.addAll(Dias(2, 11, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia5, 1, Series, Reps));
                    if (abs == true) {
                        listaCompleta.addAll(Dias(3, 4, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia5, 1, 4, 12));
                    }
                    AgregarRutina("Viernes", 5, listaCompleta);
                    listaCompleta.clear();
                    //Dia 6
                    listaCompleta.addAll(Dias(4, 14, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia6, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 7, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia6, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 15, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia6, 2, Series, Reps));
                    listaCompleta.addAll(Dias(4, 13, ResultadosSinProcesar, EjerciciosCapaces, Lesionado, MusculoLesionado, Enfermedad, CantEjercicios, Dia6, 2, Series, Reps));
                    AgregarRutina("Sabado", 6, listaCompleta);
                    listaCompleta.clear();
                }
                break;
        }
        Regresamos();
    }

    private ArrayList<ExerciseSet> Dias(int cantMusculos,int musculo, int[] ResultadosSinProcesar,boolean EjerciciosCapaces,boolean Lesionado, int[] MusculoLesionado,boolean Enfermedad,double CantEjercicios,int Dia,int tren,int series,int reps) {
        DbQuery dbQuery = new DbQuery(getContext());
        HashMap<Integer, String> TrenSup = new HashMap<>();
        TrenSup.put(1, "Hombro");
        TrenSup.put(2, "Bicep");
        TrenSup.put(3, "Pecho");
        TrenSup.put(4, "Abs");
        TrenSup.put(5, "Oblicuos");
        TrenSup.put(6, "Antebrazo");
        TrenSup.put(8, "Trapecios");
        TrenSup.put(9, "Dorsal");
        TrenSup.put(10, "Triceps");
        TrenSup.put(11, "Espalda");

        HashMap<Integer, String> TrenInf = new HashMap<>();
        TrenInf.put(7, "Cuadriceps");
        TrenInf.put(13, "Gluteo");
        TrenInf.put(14, "Femoral");
        TrenInf.put(15,"Pantorrilla");
        TrenInf.put(16, "Cardio");

        ArrayList<ExerciseSet> Rutina = new ArrayList<>();
        ExerciseSet ejercicios = new ExerciseSet();
        double EjerciciosPorMusculo;
         String Query = " WHERE MuscularGroup = ";
                    Query += musculo;
                    if(ResultadosSinProcesar[15]!=4) {
                        Query += " AND Tool = " + ResultadosSinProcesar[15];
                    }
                    if (ResultadosSinProcesar[7] == 1) {
                        Query += " AND Difficulty = " + ResultadosSinProcesar[7];
                        Query +=" ORDER BY RANDOM() ";
                        //Query += " ORDER BY Difficulty ASC ";
                    }else{
                        Query +=" ORDER BY RANDOM() ";
                        //Query += " ORDER BY Difficulty Desc ";
                    }
                    if(Resultadosinprocesar[10]==1) {
                        EjerciciosPorMusculo = Math.floor(CantEjercicios/cantMusculos);
                    }else{
                        EjerciciosPorMusculo = Math.floor(Dia/cantMusculos);
                    }
                    if ((EjerciciosCapaces == true && Resultadosinprocesar[3]==tren) || ((MusculoLesionado[0]==musculo)||(MusculoLesionado[1]==musculo)||(MusculoLesionado[2]==musculo)||(MusculoLesionado[3]==musculo)) && (Lesionado==true)  ) {
                        Query += "LIMIT 0";
                    }else if((EjerciciosCapaces == false && Enfermedad==true && Resultadosinprocesar[3]==tren)){
                        Query+="LIMIT "+ Math.ceil(EjerciciosPorMusculo/2);
                    }else{
                        if(musculo==2||musculo==10||musculo==6||musculo==8||musculo==15||musculo==4){
                            Query+="LIMIT "+ 2;
                        }else {
                            Query += "LIMIT " + EjerciciosPorMusculo;
                        }
                        }
        Log.d("Ejercicios", Query);
                    ArrayList <Exercises> ejercicios1;
                    ejercicios1 = dbQuery.EjerciciosID(Query);
                    for(int i=0;i<ejercicios1.size();i++){
                        ejercicios = new ExerciseSet(ejercicios1.get(i).getId(),ejercicios1.get(i).getName(),series,reps,musculo,null);
                        Rutina.add(ejercicios);
                        Log.d("Ejercicios", "Ejercicio del musculo "+TrenSup.get(musculo)+" o "+TrenInf.get(musculo)+"  "+ejercicios1.get(i).getId());
                    }
                    return Rutina;
                }

    private void AgregarRutina(String day, int dayOfWeek, ArrayList<ExerciseSet> listaIds){

        DbQuery dbQuery = new DbQuery(getContext());
        Routine routine = new Routine("Rutina " + day, listaIds, dayOfWeek);
        Log.d("El dia de la semana " + day + "ya cuenta con una rutina", dbQuery.routineDayAlreadyFilled(dayOfWeek) + "");
                dbQuery.insertRoutine(routine);
                Toast.makeText(getContext(), "Rutina del dia " + day+ " actualizada correctamente", Toast.LENGTH_SHORT).show();
                listaIds.clear();
    }
    private void Regresamos(){
        Fragment firstFragment = new Rutinas();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
    }
            }


