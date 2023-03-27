package com.example.gymbud;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymbud.Adaptadores.EjerciciosAdaptador;
import com.example.gymbud.Adaptadores.TienditaAdaptador;
import com.example.gymbud.db.DbQuery;

import java.util.ArrayList;

public class DetallesEjerciciosTiendita extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetallesEjerciciosTiendita() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DetallesEjerciciosTiendita newInstance(String param1, String param2) {
        DetallesEjerciciosTiendita fragment = new DetallesEjerciciosTiendita();
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
        return inflater.inflate(R.layout.fragment_detalles_ejercicios_tiendita, container, false);
    }

    ArrayList<Ejercicios> EjerciciosLista;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titulo = view.findViewById(R.id.DETtituloejerciciosGS);
        Bundle bundle = this.getArguments();
        String tituloEjercicio = bundle.getString("Ejercicio");
        titulo.setText(tituloEjercicio);


        int id = bundle.getInt("id");
        Log.d("id",Integer.toString(id));
        RecyclerView recyclerView = view.findViewById(R.id.DETRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        DbQuery dbQuery = new DbQuery (getContext());
        EjerciciosLista = new ArrayList<>();

        TienditaAdaptador adapter = new TienditaAdaptador(dbQuery.MostrarEjercicios(id));
        recyclerView.setAdapter(adapter);

    }
}