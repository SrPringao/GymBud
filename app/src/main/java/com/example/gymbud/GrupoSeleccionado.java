package com.example.gymbud;

import android.content.Context;
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

import com.example.gymbud.Adaptadores.EjerciciosAdaptador;
import com.example.gymbud.db.DbQuery;
import com.example.gymbud.db.Entidades.Exercises;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GrupoSeleccionado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GrupoSeleccionado extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GrupoSeleccionado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GrupoSeleccionado.
     */
    // TODO: Rename and change types and number of parameters
    public static GrupoSeleccionado newInstance(String param1, String param2) {
        GrupoSeleccionado fragment = new GrupoSeleccionado();
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
        return inflater.inflate(R.layout.fragment_grupo_seleccionado, container, false);
    }

    RecyclerView recyclerView;
    ArrayList<Ejercicios> EjerciciosLista;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Context context = getContext();
        Bundle args = getArguments();
        String nombreMusculo = args.getString("nombre_musculo");
        int id = args.getInt("Id");
        Log.d("ID",Integer.toString(id));
        recyclerView = view.findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        DbQuery dbQuery = new DbQuery(context);
        EjerciciosLista = new ArrayList<>();

        EjerciciosAdaptador adapter = new EjerciciosAdaptador(dbQuery.MostrarEjercicios(id));
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new EjerciciosAdaptador.EventOnItemClick() {
            @Override
            public void OnItemClick(int posicion) {
                Bundle args = new Bundle();
                Fragment fragment = new fragment_ejercicio_seleccionado();
                args.putInt("id",posicion);
                fragment.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageView imagenatras = view.findViewById(R.id.botonback4);
        TextView textView = view.findViewById(R.id.tituloejerciciosGS);
        textView.setText(nombreMusculo);


        imagenatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment firstFragment = new Ejercicios();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


    }
}