package com.example.gymbud;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gymbud.Adaptadores.JsonFeedBack;
import com.example.gymbud.Modulos.VerRutinas.VerRutinas;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EncuestaFeedback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EncuestaFeedback extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EncuestaFeedback() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EncuestaFeedback.
     */
    // TODO: Rename and change types and number of parameters
    public static EncuestaFeedback newInstance(String param1, String param2) {
        EncuestaFeedback fragment = new EncuestaFeedback();
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
    TextView Pregunta1;
    RadioButton Respuesta1, Respuesta2, Respuesta3, Respuesta4;
    Button Siguiente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_encuesta_feedback, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        int newDayOfWeek = getArguments().getInt("dia");

        Pregunta1 = view.findViewById(R.id.EFePregunta);
        Respuesta1 = view.findViewById(R.id.EFeRespuesta1);
        Respuesta2 = view.findViewById(R.id.EFeRespuesta2);
        Respuesta3 = view.findViewById(R.id.EFeRespuesta3);
        Respuesta4 = view.findViewById(R.id.EFeRespuesta4);
        Siguiente = view.findViewById(R.id.EFeAcepto);
        datos(0);


        ImageView botonBack = view.findViewById(R.id.EfeButtonBack);
        botonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstFragment = new VerRutinas();
                Bundle bundle = new Bundle();
                bundle.putInt("dia", newDayOfWeek);
                firstFragment.setArguments(bundle);
                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        Respuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Respuesta2.setChecked(false);
                Respuesta3.setChecked(false);
                Respuesta4.setChecked(false);
            }
        });
        Respuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Respuesta1.setChecked(false);
                Respuesta3.setChecked(false);
                Respuesta4.setChecked(false);
            }
        });
        Respuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Respuesta2.setChecked(false);
                Respuesta1.setChecked(false);
                Respuesta4.setChecked(false);
            }
        });
        Respuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Respuesta2.setChecked(false);
                Respuesta3.setChecked(false);
                Respuesta1.setChecked(false);
            }
        });

        Siguiente.setOnClickListener(new View.OnClickListener() {

            int i = 0;
            int[] respuestas = new int[3];
            @Override
            public void onClick(View view) {
                Log.d("NumItera"," "+i +"Respuesta: "+respuestas[i]);
                if(Respuesta1.isChecked()){
                    respuestas[i] = 1;
                }
                if(Respuesta2.isChecked()){
                    respuestas[i] = 2;
                }
                if(Respuesta3.isChecked()){
                    respuestas[i] = 3;
                }
                if(Respuesta4.isChecked()){
                    respuestas[i] = 4;
                }
                if(i==2){
                    Log.d("NumItera","Si entre");
                    Fragment firstFragment = new FeedbackEncuesta();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    Bundle bundle = new Bundle();
                    bundle.putIntArray("Datos", respuestas);
                    bundle.putInt("dia", newDayOfWeek);
                    firstFragment.setArguments(bundle);
                    transaction.replace(R.id.navFragmentContainer, firstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return;
                }
                i++;
                datos(i);

                Respuesta2.setChecked(false);
                Respuesta1.setChecked(false);
                Respuesta4.setChecked(false);
                Respuesta3.setChecked(false);
            }
        });
    }
    public void datos(int Pregunta) {
        try {
            String contenidoJson = JsonFeedBack.Read(getContext());
            JsonParser parser = new JsonParser();
            JsonElement elemento = parser.parse(contenidoJson);
            JsonObject jsonObject = elemento.getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("JsonPreguntasFeedback");
            Respuesta1.setVisibility(View.VISIBLE);
            Respuesta2.setVisibility(View.VISIBLE);
            Respuesta3.setVisibility(View.VISIBLE);
            Respuesta4.setVisibility(View.VISIBLE);
                jsonObject = jsonArray.get(Pregunta).getAsJsonObject();
                int id = jsonObject.get("NumPregunta").getAsInt();


                    String pregunta = jsonObject.get("pregunta").getAsString();
                        Pregunta1.setText(pregunta);


                    String respuesta1 = jsonObject.get("Respuesta1").getAsString();

                        Respuesta1.setText(respuesta1);



                    String respuesta2 = jsonObject.get("Respuesta2").getAsString();
                        Respuesta2.setText(respuesta2);

                    String respuesta3 = jsonObject.get("Respuesta3").getAsString();
                    Respuesta3.setText(respuesta3);
                    if(respuesta3.equals("")){
                        Respuesta3.setVisibility(View.INVISIBLE);
                    }else{
                        Respuesta3.setVisibility(View.VISIBLE);

                    }

                    String respuesta4 = jsonObject.get("Respuesta4").getAsString();
                    Respuesta4.setText(respuesta4);
                    if(respuesta4.equals("")){
                        Respuesta4.setVisibility(View.INVISIBLE);
                    }else{
                        Respuesta4.setVisibility(View.VISIBLE);
                    }

                }catch (IOException ex) {
                    ex.printStackTrace();
        }
            }

        }