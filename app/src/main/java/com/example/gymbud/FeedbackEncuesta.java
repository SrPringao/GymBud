package com.example.gymbud;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gymbud.Modulos.VerRutinas.VerRutinas;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackEncuesta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackEncuesta extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedbackEncuesta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackEncuesta.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackEncuesta newInstance(String param1, String param2) {
        FeedbackEncuesta fragment = new FeedbackEncuesta();
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
        return inflater.inflate(R.layout.fragment_feedback_encuesta, container, false);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageView img = view.findViewById(R.id.FBotonback);
        int[] Datos ;
        Datos = getArguments().getIntArray("Datos");
        int Dia = getArguments().getInt("dia");

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstFragment = new VerRutinas();
                Bundle bundle = new Bundle();
                bundle.putInt("dia", Dia);
                firstFragment.setArguments(bundle);
                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageView imagen1 = (ImageView) getView().findViewById(R.id.Feed1);
        if(Datos[0]==1 || Datos[0]==2){//Peso bien
            imagen1.setImageDrawable(getResources().getDrawable(R.drawable.ic_pesobien));
        }else{//Peso subir
            imagen1.setImageDrawable(getResources().getDrawable(R.drawable.ic_pesobien));
        }
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mensajin = "";
                switch (Datos[0]){
                    case 1:
                        Mensajin = "Te recomendamos usar una carga que al terminar estes al  borde del fallo MUSCULAR o al fallo total";
                        break;
                        case 2:
                            Mensajin = "El peso que usaste es el adecuado procura acercarte al fallo muscular";
                            break;
                    case 3:
                        Mensajin = "Te recomendamos subir las cargas hasta terminar al borde del fallo MUSCULAR o al fallo total";
                        break;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("RECOMENDACION");
                builder.setMessage(Mensajin);
                builder.setPositiveButton("Ok", (dialog, which) -> {
                     dialog.dismiss();

                });

                builder.show();
            }
        });

        ImageView imagen2 = (ImageView) getView().findViewById(R.id.Feed2);
        if(Datos[1]==1)
        {
            imagen2.setVisibility(View.GONE);
        }else if(Datos[1]==2) {//dormir mejor
            imagen2.setImageDrawable(getResources().getDrawable(R.drawable.ic_dormirmas));
        }else{//Dormir mejor plus plus
            imagen2.setImageDrawable(getResources().getDrawable(R.drawable.ic_dormirmasmas));
        }
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Mensajin = "";
                switch (Datos[1]){
                    //Preguntas sobre como fue tu descanso el dia de ayer
                    case 2:
                    case 3:
                        Mensajin = "Te recomendamos que duermas 8 horas diarias para que tu cuerpo se recupere de manera optima";
                        break;

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("DESCANSO");
                builder.setMessage(Mensajin);
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();

                });

                builder.show();
            }
        });

        ImageView imagen3 = (ImageView) getView().findViewById(R.id.Feed3);
        if(Datos[2]==2)
        {
            imagen3.setVisibility(View.GONE);
        } else if (Datos[2]==1){//Comiste tempra
            imagen3.setImageDrawable(getResources().getDrawable(R.drawable.ic_comertempra));
        }else if(Datos[2]==3){//Colacion
            imagen3.setImageDrawable(getResources().getDrawable(R.drawable.ic_colacion));
        }else{//Come algo destripon
            imagen3.setImageDrawable(getResources().getDrawable(R.drawable.ic_destripon));
        }
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                String Mensajin = "";
                switch (Datos[1]){
                    //Preguntas sobre tiempos desde tu ultima comida hasta el entrenamiento
                    case 1:
                        //30 minutos
                        Mensajin = "Es recomendable que esperes almenos hora y media antes de entrenar para evitar malestares estomacales";
                        break;
                    case 3:
                        //6 horas
                        Mensajin = "Es recomendable que consumas alguna colacion antes de entrenar para evitar setirte debil";
                        break;
                    case 4:
                        //7+ horas
                        Mensajin = "Es recomendable que consumas tus alimentos en las horas correctas para evitar sentirte debil y tener mejores resultados";
                        break;

                }
                builder.setTitle("ALIMENTACION");
                builder.setMessage(Mensajin);
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();

                });

                builder.show();
            }
        });


    }
}