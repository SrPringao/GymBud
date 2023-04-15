package com.example.gymbud.Modulos.CreacionDeRutinas.RutinasPersonalizadas;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymbud.Adaptadores.VerEliminarCarritoAdapter;
import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.Entidades.ExerciseSet;
import com.example.gymbud.Entidades.IdList;
import com.example.gymbud.Entidades.Routine;
import com.example.gymbud.R;

import java.util.ArrayList;
import java.util.List;

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


    ArrayList<ExerciseSet> listaIds = new ArrayList<>();


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // You can now set the text of a TextView
        ImageView imagenatras = view.findViewById(R.id.TiButtonBack);
        TextView tvTiempo = view.findViewById(R.id.TeTiempoEstimado);
        RecyclerView recyclerView = view.findViewById(R.id.TiRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Spinner spinner = view.findViewById(R.id.TiWeekSelector);
        Button button = view.findViewById(R.id.TiConfirmButton);
        ArrayAdapter<CharSequence> adap1 = ArrayAdapter.createFromResource
                (getActivity(), R.array.DiasSemana, android.R.layout.simple_spinner_item);

        adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setSelection(0);
        spinner.setBackground(getResources().getDrawable(R.drawable.spinnerbackground));
        spinner.setPopupBackgroundResource(R.drawable.pop_up_background);
        spinner.setPadding(10, 10, 10, 10);

        spinner.setAdapter(adap1);

          DbQuery dbQuery = new DbQuery (getContext());
          listaIds = IdList.getInstance();



          VerEliminarCarritoAdapter adapter = new VerEliminarCarritoAdapter(dbQuery.MostrarEjercicios(listaIds),tvTiempo);
          //Log.d ("Ejercicios", dbQuery.MostrarEjercicios(listaIds).toString());
          recyclerView.setAdapter(adapter);


          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //si la lista esta vacia, mostrar mensaje de que tienes que agregar ejercicios
                    if (listaIds.isEmpty()) {
                        Toast.makeText(getContext(), "No puedes agregar rutinas vacias, agrega al menos un ejercicio", Toast.LENGTH_SHORT).show();
                        Fragment firstFragment = new CreacionDeRutinas();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                        transaction.replace(R.id.navFragmentContainer, firstFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }else {
                        String day = spinner.getSelectedItem().toString();
                        int dayOfWeek = spinner.getSelectedItemPosition() + 1;
                        Routine routine = new Routine("Rutina " + day, listaIds, dayOfWeek);
//                        Log.d("El dia de la semana " + day + "ya cuenta con una rutina", dbQuery.routineDayAlreadyFilled(dayOfWeek) + "");

                        //si el dia de la semana ya cuenta con una rutina, preguntar al usuario si le gustaria reemplazarla o no
                        if (dbQuery.routineDayAlreadyFilled(dayOfWeek)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Ya existe una rutina para el dia " + day);
                            builder.setMessage("¿Desea reemplazarla?");

                            builder.setPositiveButton("Si", (dialog, which) -> {
                                dbQuery.insertRoutine(routine);
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Rutina del dia " + day+ " actualizada correctamente", Toast.LENGTH_SHORT).show();
                                listaIds.clear();
                                Fragment firstFragment = new CreacionDeRutinas();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                                transaction.replace(R.id.navFragmentContainer, firstFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            });
                            builder.setNegativeButton("No", (dialog, which) -> {
                                dialog.dismiss();
                            });
                            builder.show();
                        }else {
                            //toast de rutina agregada
                            dbQuery.insertRoutine(routine);
                            Toast.makeText(getContext(), "Rutina del dia " + day+ " agregada correctamente", Toast.LENGTH_SHORT).show();
                            listaIds.clear();
                            Fragment firstFragment = new CreacionDeRutinas();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                            transaction.replace(R.id.navFragmentContainer, firstFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        }
                    }


              }
          });
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