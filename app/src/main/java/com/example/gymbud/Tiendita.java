package com.example.gymbud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gymbud.Adaptadores.CarritoEjerciciosAdapter;
import com.example.gymbud.Adaptadores.TienditaAdaptador;
import com.example.gymbud.db.DbQuery;
import com.example.gymbud.db.Entidades.IdList;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tiendita#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tiendita extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tiendita() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tiendita.
     */
    // TODO: Rename and change types and number of parameters
    public static Tiendita newInstance(String param1, String param2) {
        Tiendita fragment = new Tiendita();
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
        return inflater.inflate(R.layout.fragment_tiendita, container, false);
    }


    ArrayList<Integer> listaIds = new ArrayList<>();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // You can now set the text of a TextView
        ImageView imagenatras = view.findViewById(R.id.TiButtonBack);

        RecyclerView recyclerView = view.findViewById(R.id.TiRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DbQuery dbQuery = new DbQuery (getContext());
        listaIds = IdList.getInstance();
        CarritoEjerciciosAdapter adapter = new CarritoEjerciciosAdapter(dbQuery.MostrarEjerciciosCarro(listaIds));
        Log.d ("Ejercicios", dbQuery.MostrarEjerciciosCarro(listaIds).toString());
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