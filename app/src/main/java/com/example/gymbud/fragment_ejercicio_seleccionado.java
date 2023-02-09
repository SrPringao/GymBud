package com.example.gymbud;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gymbud.db.DbQuery;
import com.example.gymbud.db.Entidades.Exercises;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_ejercicio_seleccionado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_ejercicio_seleccionado extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_ejercicio_seleccionado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_ejercicio_seleccionado.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_ejercicio_seleccionado newInstance(String param1, String param2) {
        fragment_ejercicio_seleccionado fragment = new fragment_ejercicio_seleccionado();
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
    Exercises exercises;
    @Override
public void onViewCreated(View view,Bundle savedInstanceState){
        Context context = view.getContext();
        DbQuery dbQuery = new DbQuery(context);
        Bundle args = getArguments();

        int id = args.getInt("id");

        Log.d("IDeee", ""+id);

        exercises = dbQuery.EjerciciosVER(id);
        TextView Titulo,PreparacionD,EjecucionD,DetallesD;
        Titulo = view.findViewById(R.id.NombreEjercicio);
        PreparacionD = view.findViewById(R.id.PreparacionData);
        EjecucionD = view.findViewById(R.id.EjecucionData);
        DetallesD = view.findViewById(R.id.DetallesData);

        Titulo.setText(exercises.getName());
        PreparacionD.setText(exercises.getForeSeeing());
        EjecucionD.setText(exercises.getExecution());
        DetallesD.setText(exercises.getDetails());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ejercicio_seleccionado, container, false);
    }
}