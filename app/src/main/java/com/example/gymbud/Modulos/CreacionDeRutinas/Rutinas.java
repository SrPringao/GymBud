package com.example.gymbud.Modulos.CreacionDeRutinas;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymbud.FragmentContainer;
import com.example.gymbud.Modulos.CreacionDeRutinas.RutinasPersonalizadas.CreacionDeRutinas;
import com.example.gymbud.Modulos.CreacionDeRutinas.RutinasAutomaticas.Encuesta;
import com.example.gymbud.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Rutinas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Rutinas extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Rutinas() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Rutinas newInstance(String param1, String param2) {
        Rutinas fragment = new Rutinas();
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
        return inflater.inflate(R.layout.fragment_rutinas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentContainer activity = (FragmentContainer) getActivity();

        Button botonAutomatica = view.findViewById(R.id.botonRutinaAutomatica);
        Button botonPersonalizada = view.findViewById(R.id.botonRutinaPersonalizada);
        CardView card1 = view.findViewById(R.id.tarjetaRutinaPersonalizada);
        CardView card2 = view.findViewById(R.id.tarjetaRutinaAutomatica);

        // Animacion de las tarjetas

        card1.setX(3000);
        card2.setX(-3000);



        card1.animate().translationX(0).setDuration(500).setStartDelay(0);
        card2.animate().translationX(0).setDuration(500).setStartDelay(0);



        botonAutomatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Cualquier rutina creada con anterioridad sera remplazada");
                builder.setMessage("¿Desea continuar?");

                builder.setPositiveButton("Si", (dialog, which) -> {
                    dialog.dismiss();
                    Fragment firstFragment = new Encuesta();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, firstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                });
                builder.setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                });
                builder.show();
            }
        });

        botonPersonalizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstFragment = new CreacionDeRutinas();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

}