package com.example.gymbud.Modulos.SeleccionEjercicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gymbud.Db.DbHelper;
import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.FragmentContainer;
import com.example.gymbud.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link registropeso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class registropeso extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public registropeso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment registropeso.
     */
    // TODO: Rename and change types and number of parameters
    public static registropeso newInstance(String param1, String param2) {
        registropeso fragment = new registropeso();
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
        return inflater.inflate(R.layout.fragment_registropeso, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        FragmentContainer activity = (FragmentContainer) getActivity();
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Bundle args = getArguments();
        int id = args.getInt("id");
        int ID = args.getInt("ID");
        String musculo = args.getString("Musculo");
        ImageView Back = view.findViewById(R.id.botonback);
        EditText CargaR, RepsR, RepsR2, TiempoR;
        CargaR = view.findViewById(R.id.CargaR);
        RepsR = view.findViewById(R.id.RepsR);
        RepsR2 = view.findViewById(R.id.RepsR2);
        TiempoR = view.findViewById(R.id.TiempoR);
        Button Guardar = view.findViewById(R.id.GuardadoR);
        //Este boton esta vinculado a la imagen de la flecha hacia atras y lo unico que hace es regresarte a la pantalla anterior,
        //mandando los datos id,ID y musculo a esta pantalla anterior para despues utilizarlos y evitar la perdida del musculo seleccionado o
        //el ejercicio seleccionado, todos estos se mandan en un bundle.
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new stats();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                args.putInt("id",id);
                args.putInt("Id",ID);
                args.putString("Musculo",musculo);
                fragment.setArguments(args);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        //Esta funcion esta vinculada al boton guardar que lo que hace es recibir todos los datos insertados en los editText, para despues
        //Realizar una insert a la base de datos en la tabla stats con todos los datos ya registrados anteriormente, tambien se llama a la funcion FechaG para
        //Obtener la fecha actual del celular y registrarla.
        //Tambien Envia el id,ID y el musculo para cargar el ejercicio seleccionado y poder regresar de manera correcta en un bundle y realiza la
        //transaccion de los fragments.
        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            int carga = Integer.parseInt(CargaR.getText().toString());
            int reps = Integer.parseInt(RepsR.getText().toString());
            int reps2 = Integer.parseInt(RepsR2.getText().toString());
            float Time = Float.parseFloat(TiempoR.getText().toString());
                String FechaAct = activity.FechaAct();
            //    String update = "UPDATE STATS SET Weight = "+carga+",Reps = "+reps+",Reps2 = "+reps2+",Time = "+Time+",Date = "+FechaG+ "+ WHERE ID_Ejercicio = " + id;

                DbQuery dbQuery = new DbQuery(context);
                long query = dbQuery.StatsInsert(carga,reps,reps2,Time,FechaAct,id);
                Fragment fragment = new stats();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                args.putInt("id",id);
                args.putInt("Id",ID);
                args.putString("Musculo",musculo);
                fragment.setArguments(args);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}