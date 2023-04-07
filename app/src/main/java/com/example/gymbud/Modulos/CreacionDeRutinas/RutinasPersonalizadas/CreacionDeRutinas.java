package com.example.gymbud.Modulos.CreacionDeRutinas.RutinasPersonalizadas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gymbud.Entidades.ExerciseSet;
import com.example.gymbud.Entidades.IdList;
import com.example.gymbud.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreacionDeRutinas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreacionDeRutinas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreacionDeRutinas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreacionDeRutinas.
     */
    // TODO: Rename and change types and number of parameters
    public static CreacionDeRutinas newInstance(String param1, String param2) {
        CreacionDeRutinas fragment = new CreacionDeRutinas();
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
        return inflater.inflate(R.layout.fragment_creacion_de_rutinas, container, false);
    }

    Spinner spinner;
    String item;
    Button btn;
    int pos;

    ArrayList<ExerciseSet> listaIds = new ArrayList<>();
    private ImageView imageViewToolbar;



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // You can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Creacion de Rutinas");


        listaIds = IdList.getInstance();
        Log.d("Lista en pantalla de seleccion", "onViewCreated: " + listaIds);


        Fragment fragment = new DetallesEjerciciosTiendita();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle args = new Bundle();

        spinner = view.findViewById(R.id.CRSpinner);
        btn = view.findViewById(R.id.CRButton);
        imageViewToolbar = view.findViewById(R.id.TBlogoTienda);


        ArrayAdapter<CharSequence> adap1 = ArrayAdapter.createFromResource
                (getActivity(), R.array.ListaEjercicios, android.R.layout.simple_spinner_item);

        adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply rounded background to spinner
        spinner.setBackground(getResources().getDrawable(R.drawable.spinnerbackground));
        spinner.setPopupBackgroundResource(R.drawable.pop_up_background);
        spinner.setPadding(10, 10, 10, 10);

        imageViewToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Tiendita();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        spinner.setAdapter(adap1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                pos = i+1;

                if (pos >= 12){
                    pos+=1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast toast = Toast.makeText(getActivity(), "No selecciono nada", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                args.putString("Ejercicio", item);
                args.putInt("id", pos);
                fragment.setArguments(args);
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
}