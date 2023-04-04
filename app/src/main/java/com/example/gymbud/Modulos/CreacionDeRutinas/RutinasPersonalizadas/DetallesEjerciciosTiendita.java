package com.example.gymbud.Modulos.CreacionDeRutinas.RutinasPersonalizadas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymbud.Adaptadores.TienditaAdaptador;
import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.Modulos.SeleccionEjercicios.Ejercicios;
import com.example.gymbud.R;

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
        ImageView imagenatras = view.findViewById(R.id.DETBotonBack);

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
//        Log.d("Ejercicios en pantalla 1 para ver como los regresa", dbQuery.MostrarEjercicios(id).toString());
        recyclerView.setAdapter(adapter);

        imagenatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstFragment = new CreacionDeRutinas();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }


}