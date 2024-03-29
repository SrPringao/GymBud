package com.example.gymbud.Modulos.SeleccionEjercicios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gymbud.R;

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


        return inflater.inflate(R.layout.fragment_ejercicios, container, false);
    }


    View.OnClickListener ejercicioseleccionado =new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Fragment fragment = new GrupoSeleccionado();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Bundle args = new Bundle();


            //Este switch case se usa para diferenciar cual fue el musculo que se selecciono y asi poder hacer la query despues, con su respectivo
            //nombre y el id del musculo en la base de datos
            switch (view.getId()) {

                case R.id.ethombro:
                    Log.d("Tag", "onClick: hombro");

                    args.putString("nombre_musculo", "Hombro");
                    args.putInt("ID",1);
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                case R.id.etbiceps:
                    Log.d("Tag", "onClick: bicep");
                    args.putInt("ID",2);
                    args.putString("nombre_musculo", "Bicep");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etoblicuos:
                    Log.d("Tag", "onClick: oblicuos");
                    args.putInt("ID",5);
                    args.putString("nombre_musculo", "Oblicuos");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etpecho:
                    Log.d("Tag", "onClick: pecho");
                    args.putInt("ID",3);
                    args.putString("nombre_musculo", "Pecho");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etabs:
                    Log.d("Tag", "onClick: abs");
                    args.putInt("ID",4);
                    args.putString("nombre_musculo", "Abs");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etantebrazos:
                    Log.d("Tag", "onClick: antebrazos");
                    args.putInt("ID",6);
                    args.putString("nombre_musculo", "Antebrazo");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etcuadriceps:
                    Log.d("Tag", "onClick: cuads");
                    args.putInt("ID",7);
                    args.putString("nombre_musculo", "Cuadriceps");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.ettrapecios:
                    Log.d("Tag", "onClick: trapecios");
                    args.putInt("ID",8);
                    args.putString("nombre_musculo", "Trapecios");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etriceps:
                    Log.d("Tag", "onClick: triceps");
                    args.putInt("ID",10);
                    args.putString("nombre_musculo", "Tricep");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etfemorales:
                    Log.d("Tag", "onClick: femorales");
                    args.putInt("ID",14);
                    args.putString("nombre_musculo", "Femoral");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etpantorrillas:
                    Log.d("Tag", "onClick: pantorrillas");
                    args.putInt("ID",15);
                    args.putString("nombre_musculo", "Pantorrilla");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etdorsales:
                    Log.d("Tag", "onClick: dorsales");
                    args.putInt("ID",9);
                    args.putString("nombre_musculo", "Dorsal");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etespaldamedia:
                    Log.d("Tag", "onClick: espalda");
                    args.putInt("ID",11);
                    args.putString("nombre_musculo", "Espalda");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    break;
                case R.id.etgluteos:
                    Log.d("Tag", "onClick: gluteos");
                    args.putInt("ID",13);
                    args.putString("nombre_musculo", "Gluteo");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;

                case R.id.etCardio:
                    Log.d("Tag", "onClick: cardio");
                    args.putInt("ID",16);
                    args.putString("nombre_musculo", "Cardio");
                    fragment.setArguments(args);
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
            }
        }
    };


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imagen = view.findViewById(R.id.imgvuelta);
        ImageView modelo =  view.findViewById(R.id.imgmodelo);
        TextView titulo = view.findViewById(R.id.tituloejercicios);
        ConstraintLayout ejerciciosfrontalesi = view.findViewById(R.id.ejerciciosizquierda);
        ConstraintLayout ejerciciosfrontalesd = view.findViewById(R.id.ejerciciosderecha);
        ConstraintLayout ejerciciostraserosi = view.findViewById(R.id.ejerciciositraseros);
        ConstraintLayout ejerciciostraserosd = view.findViewById(R.id.ejerciciosdtraseros);
        TextView TvVuelta = view.findViewById(R.id.TvVuelta);
        ejerciciosfrontalesd.setVisibility(View.VISIBLE);
        ejerciciosfrontalesi.setVisibility(View.VISIBLE);
        modelo.setImageResource(R.drawable.otisparado);


        imagen.setX(3000);
        modelo.setY(3000);
        titulo.setX(3000);

        ejerciciosfrontalesi.setX(-3000);
        ejerciciosfrontalesd.setX(3000);

        ejerciciostraserosi.setY(-3000);
        ejerciciostraserosd.setY(-3000);

        TvVuelta.setY(-3000);


        imagen.animate().translationX(0).setDuration(500).setStartDelay(0);
        TvVuelta.animate().translationY(0).setDuration(500).setStartDelay(0);
        modelo.animate().translationY(0).setDuration(500).setStartDelay(0);
        titulo.animate().translationX(0).setDuration(500).setStartDelay(0);

        ejerciciosfrontalesi.animate().translationX(0).setDuration(500).setStartDelay(0);
        ejerciciosfrontalesd.animate().translationX(0).setDuration(500).setStartDelay(0);


        final boolean[] ejercicios = {false};


        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ejercicios[0]) {

                    ejerciciostraserosi.animate().translationY(-3000).setDuration(500).setStartDelay(0);
                    ejerciciostraserosd.animate().translationY(3000).setDuration(500).setStartDelay(0);

                    ejerciciosfrontalesi.animate().translationX(0).setDuration(500).setStartDelay(0);
                    ejerciciosfrontalesd.animate().translationX(0).setDuration(500).setStartDelay(0);

                    modelo.animate().rotationBy(-360).setDuration(500).setStartDelay(0);
                    modelo.setImageResource(R.drawable.otisparado);

                    titulo.setText("Ejercicios frontales");
                    TvVuelta.setText("Mostrar ejercicios\ntraseros");
                    Log.d("Estado", "onClick: Adelante");

                    ejercicios[0] = false;
                } else {
                    ejerciciosfrontalesi.animate().translationX(-3000).setDuration(500).setStartDelay(0);
                    ejerciciosfrontalesd.animate().translationX(3000).setDuration(500).setStartDelay(0);

                    ejerciciostraserosi.setVisibility(View.VISIBLE);
                    ejerciciostraserosd.setVisibility(View.VISIBLE);
                    ejerciciostraserosi.animate().translationY(0).setDuration(500).setStartDelay(0);
                    ejerciciostraserosd.animate().translationY(0).setDuration(500).setStartDelay(0);

                    modelo.animate().rotationBy(360).setDuration(500).setStartDelay(0);
                    modelo.setImageResource(R.drawable.otisparado2);

                    titulo.setText("Ejercicios traseros");
                    TvVuelta.setText("Mostrar ejercicios\nfrontales");

                    ejercicios[0] = true;

                    Log.d("Estado", "onClick: Atras");
                }

            }
        });



        int[] editTextIds = {R.id.ethombro, R.id.etbiceps, R.id.etoblicuos,R.id.etpecho,R.id.etabs,R.id.etantebrazos,R.id.etcuadriceps,R.id.ettrapecios,R.id.etriceps,R.id.etfemorales
        ,R.id.etpantorrillas,R.id.etdorsales,R.id.etespaldamedia,R.id.etgluteos,R.id.etCardio};

        for (int id : editTextIds) {
            EditText editText = view.findViewById(id);
            editText.setOnClickListener(ejercicioseleccionado);
        }
    }

}