package com.example.gymbud.Modulos.VerRutinas;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.gymbud.Adaptadores.VerRutinaDelDiaAdapter;
import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.Entidades.Routine;
import com.example.gymbud.Modulos.CreacionDeRutinas.RutinasPersonalizadas.CreacionDeRutinas;
import com.example.gymbud.Modulos.InfoPersonal.FragmentInfoPersonal;
import com.example.gymbud.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerRutinas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerRutinas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VerRutinas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerRutinas.
     */
    // TODO: Rename and change types and number of parameters
    public static VerRutinas newInstance(String param1, String param2) {
        VerRutinas fragment = new VerRutinas();
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
        return inflater.inflate(R.layout.fragment_ver_rutinas, container, false);
    }

    @Override
    @SuppressLint("UseCompatLoadingForDrawables")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinner = view.findViewById(R.id.VRSpinner);
        ArrayAdapter<CharSequence> adap1 = ArrayAdapter.createFromResource
                (getActivity(), R.array.DiasSemana, android.R.layout.simple_spinner_item);

        adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setBackground(getResources().getDrawable(R.drawable.spinnerbackground));
        spinner.setPopupBackgroundResource(R.drawable.pop_up_background);
        spinner.setPadding(10, 10, 10, 10);

        spinner.setAdapter(adap1);

        Bundle bundle = getArguments();
        int numberDayOfWeek = bundle.getInt("dia") - 1;
//        Log.d("Dia recibido: ", String.valueOf(numberDayOfWeek));
        spinner.setSelection(numberDayOfWeek);

        ImageView botonBack = view.findViewById(R.id.VRButtonBack);
        botonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstFragment = new FragmentInfoPersonal();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.VRRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DbQuery dbQuery = new DbQuery (getContext());
        VerRutinaDelDiaAdapter adapter = new VerRutinaDelDiaAdapter(dbQuery.getRoutineByDay(numberDayOfWeek));
        recyclerView.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int newDayOfWeek = position + 1;
//                Log.d("Dia seleccionado: ", String.valueOf(newDayOfWeek));
                updateRecyclerView(newDayOfWeek);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void updateRecyclerView(int dayOfWeek) {
        // Actualiza el valor de numberDayOfWeek
        int numberDayOfWeek = dayOfWeek;

        // Actualiza el recycler view
        RecyclerView recyclerView = getView().findViewById(R.id.VRRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DbQuery dbQuery = new DbQuery (getContext());
        VerRutinaDelDiaAdapter adapter = new VerRutinaDelDiaAdapter(dbQuery.getRoutineByDay(numberDayOfWeek));
        recyclerView.setAdapter(adapter);
    }


}