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
import android.widget.Switch;
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
        ImageView Back = view.findViewById(R.id.botonback4);
        String nombreMusculo = args.getString("nombre_musculo");//nombre del musculo seleccionado

       //Log.d("musculo",nombreMusculo );
        int id = args.getInt("ID");//id del musculo seleccionado
        Log.d("id",Integer.toString(id));
        recyclerView = view.findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        DbQuery dbQuery = new DbQuery(context);
        EjerciciosLista = new ArrayList<>();

    //creamos un objeto del adaptador para usarlo en el recycler
    //Despues realizamos la query con los ejercicios guardados en la bd con la funcion MostrarEjercicios, mandando el musculo seleccionado
        EjerciciosAdaptador adapter = new EjerciciosAdaptador(dbQuery.MostrarEjercicios(id));
        recyclerView.setAdapter(adapter);

        //Esta funcion te regresa al fragment anterior y manda los datos id y el nombre del musculo en un bundle
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Ejercicios();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        //Esta funcion lo  que hace es recibir la posicion de la tarjeta seleccionada del recycler, para despues,
        //sumar la cantidad previa de ejercicios en la base de datos y asi acceder al ejercicio correcto, llamando a la funcion
        //MusculoSuma, pasandole el nombre del musculo en la llamada.
        adapter.setOnClickListener(new EjerciciosAdaptador.EventOnItemClick() {
            @Override
            public void OnItemClick(int posicion) {
                Bundle args = new Bundle();
                int idplus;
                idplus = posicion;
                idplus += MusculoSuma(nombreMusculo);
                Log.d("idplus", ""+idplus);
                //Log.d("NOMBREMUSCULO", nombreMusculo);
                Fragment fragment = new fragment_ejercicio_seleccionado();
                args.putInt("id",idplus);
                args.putInt("ID",id);
                args.putString("Musculo",nombreMusculo);
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

//Esta funcion te regresa al fragment anterior y manda los datos id y el nombre del musculo en un bundle
        imagenatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment firstFragment = new Ejercicios();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                args.putInt("Id",id);
                args.putString("Musculo",nombreMusculo);
                firstFragment.setArguments(args);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


    }

    //Funcion para probar cuando esten los ejercicios ya en la bd
    //Esta funcion lo que hace es asegurarse que los id de cuando se seleccione en la aplicacion sean los mismos que estan en la bd,
    //ya que no encontre la manera de hacer que cuando se seleccione una tarjeta del recycler tenga un id personalizado y solo van de forma
    //ascendente, sumando a el id del recycler la cantidad de ejercicios previos para llegar al id correcto
    int MusculoSuma(String Musculo){
        int NumMagico=1;
        switch (Musculo){
            case "Hombro":
                NumMagico=1;
                break;
            case "Bicep":
                NumMagico=36;
                break;
            case "Pecho":
                NumMagico=20;
                break;
            case "Abs":
                NumMagico=85;
                break;
            case "Oblicuos":
                NumMagico=66;
                break;
            case "Antebrazo":
                NumMagico=97;
                break;
            case "Cuadriceps":
                NumMagico=116;
                break;
            case "Trapecios":
                NumMagico=141;
                break;
            case "Dorsal":
                NumMagico=161;
                break;
            case "Tricep":
                NumMagico=51;
                break;
            case "Espalda Media":
                NumMagico=105;
                break;

            case "Gluteo":
                NumMagico=134;
                break;
            case "Femoral":
                NumMagico=142;
                break;
            case "Pantorrilla":
                NumMagico=153;
                break;

        }
      //  Log.d("NUM MAGICO", ""+NumMagico);
        return NumMagico;
    }
}