package com.example.gymbud.Modulos.SeleccionEjercicios;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Adaptadores.EjercicioParecidoAdaptador;
import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.R;

import java.util.HashMap;

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
    RecyclerView recyclerView;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ImageView imagen = view.findViewById(R.id.botonback4);
        ImageView ImgMusculo = view.findViewById(R.id.Musculatura1);
        Button Stats;
        Context context = view.getContext();
        DbQuery dbQuery = new DbQuery(context);
        Bundle args = getArguments();
        int id = args.getInt("id");
        int ID = args.getInt("ID");
        String musculo = args.getString("Musculo");

        Log.d("idchikito", "" + id);
        Log.d("IDGRANDOTE", "" + ID);
        Log.d("Musculo recibido", musculo);

        HashMap<String, Integer> Tren = new HashMap<>();
        Tren.put("Hombro", 1);
        Tren.put("Bicep", 2);
        Tren.put("Pecho", 3);
        Tren.put("Abs", 4);
        Tren.put("Oblicuos", 5);
        Tren.put("Antebrazo", 6);
        Tren.put("Cuadriceps", 7);
        Tren.put("Trapecios", 8);
        Tren.put("Dorsal", 9);
        Tren.put("Triceps", 10);
        Tren.put("Espalda", 11);
        Tren.put("Gluteo", 13);
        Tren.put("Femoral", 14);
        Tren.put("Pantorrilla", 15);
        Tren.put("Cardio", 16);

        int Musculo = Tren.get(musculo);


        TypedArray imagenes = getResources().obtainTypedArray(R.array.imagenes);

        Drawable drawable = imagenes.getDrawable(ID - 1);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) ImgMusculo.getLayoutParams();
//        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        ImgMusculo.setLayoutParams(params);
        ImgMusculo.setImageDrawable(drawable);
        imagenes.recycle();

        Log.d("Musculo", "" + Musculo);

        recyclerView = view.findViewById(R.id.RecyclerViewParecido);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        EjercicioParecidoAdaptador ejercicioParecidoAdaptador = new EjercicioParecidoAdaptador(dbQuery.EjerciciosParecidos(Musculo, id));
        recyclerView.setAdapter(ejercicioParecidoAdaptador);

        exercises = dbQuery.EjerciciosVER(id);
        TextView Titulo, PreparacionD, EjecucionD, DetallesD;
        Titulo = view.findViewById(R.id.NombreEjercicio);
        PreparacionD = view.findViewById(R.id.PreparacionData);
        EjecucionD = view.findViewById(R.id.EjecucionData);
        DetallesD = view.findViewById(R.id.DetallesData);
        Stats = view.findViewById(R.id.Stats);
        VideoView ImagenEjercicio = view.findViewById(R.id.esGif);

        Titulo.setText(exercises.getName());
        PreparacionD.setText(exercises.getForeSeeing());
        EjecucionD.setText(exercises.getExecution());
        DetallesD.setText(exercises.getDetails());


        RelativeLayout tapon = view.findViewById(R.id.esTapon);;

        String url = "https://francoaldrete.com/GymBud/Ejercicios/" + id + ".mp4";
        ImagenEjercicio.setVideoPath(url);
        //mute video
        ImagenEjercicio.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                ImagenEjercicio.start();
                mp.setVolume(0f, 0f);
                tapon.setVisibility(View.GONE);
            }

        });

        ImagenEjercicio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // El video ha terminado de reproducirse, reiniciamos la reproducción
                ImagenEjercicio.start();
            }
        });



        Stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new stats();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putInt("id", id);
                args.putInt("ID", ID);
                args.putString("Musculo", musculo);
                fragment.setArguments(args);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ejercicioParecidoAdaptador.setOnClickListener(new EjercicioParecidoAdaptador.EventOnItemClick() {
            @Override
            public void OnItemClick(int id) {
                Fragment fragment = new fragment_ejercicio_seleccionado();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putInt("id", id);
                args.putInt("ID", ID);
                args.putString("Musculo", musculo);
                fragment.setArguments(args);
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new GrupoSeleccionado();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putInt("id", id);
                args.putInt("ID", ID);
                args.putString("nombre_musculo", musculo);
                fragment.setArguments(args);
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ejercicio_seleccionado, container, false);
    }
}