package com.example.gymbud;

import android.os.Bundle;

import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ejercicios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ejercicios extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Ejercicios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ejercicios.
     */
    // TODO: Rename and change types and number of parameters
    public static Ejercicios newInstance(String param1, String param2) {
        Ejercicios fragment = new Ejercicios();
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
        //imagen.animate().translationX(-1400).setDuration(2700).setStartDelay(0);

        return inflater.inflate(R.layout.fragment_ejercicios, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imagen = view.findViewById(R.id.imgvuelta);
        ImageView modelo =  view.findViewById(R.id.imgmodelo);
        TextView titulo = view.findViewById(R.id.tituloejercicios);
        EditText hombro = view.findViewById(R.id.ethombro);


        modelo.setY(3000);
        titulo.setX(3000);

        imagen.animate().translationX(1100).setDuration(500).setStartDelay(0);
        modelo.animate().translationY(0).setDuration(500).setStartDelay(0);
        titulo.animate().translationX(0).setDuration(500).setStartDelay(0);

        final boolean[] ejercicios = {true};

        hombro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ejercicios[0]) {
                    //grupo1.setVisibility(View.VISIBLE);
                    modelo.setImageResource(R.drawable.otisparado);
                    titulo.setText("Ejercicios frontales");

                    ejercicios[0] = false;
                } else {
                    //grupo1.setVisibility(View.GONE);
                    modelo.setImageResource(R.drawable.otisparado2);
                    titulo.setText("Ejercicios traseros");
                    ejercicios[0] = true;
                }

            }
        });



    }
}