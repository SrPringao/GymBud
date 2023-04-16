package com.example.gymbud.Modulos.InfoPersonal;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gymbud.Db.DbHelper;
import com.example.gymbud.FragmentContainer;
import com.example.gymbud.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatosInfoTg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosInfoTg extends Fragment {
    Spinner opciones;
    int sexo = 0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DatosInfoTg() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosInfoTg.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosInfoTg newInstance(String param1, String param2) {
        DatosInfoTg fragment = new DatosInfoTg();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_datos_info_tg, container, false);


        opciones = (Spinner)view.findViewById(R.id.SpinnerSex);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentContainer activity = (FragmentContainer) getActivity();

        ImageView imagen = view.findViewById(R.id.botonback3);
        Button boton = view.findViewById(R.id.Calc);
        EditText Edad = view.findViewById(R.id.etInfo2);

        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Esta funcion se asegura que todos los campos esten llenos y analiza la info del spinner dependiendo del sexo seleccionado, para despues realizar un update
        //de los datos ya anteriormente registrados con los datos nuevamente ingresados con un string, usando db.exercSQL como solo necesitamos insertarlo y no
        //recibir nada podemos realizarlo de esta manera sencilla
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Edad.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Ingresa tu edad", Toast.LENGTH_SHORT).show();
                } else {


                if( 0 == opciones.getSelectedItemId()){
                    sexo = 1;
                }else if(1==opciones.getSelectedItemId()){
                    sexo = 2;
                }
                    int UID = activity.UIDUSR();
                String update = "UPDATE PERSONINFO SET Age = " + Integer.parseInt(Edad.getText().toString()) + ",Gender = " + sexo + " WHERE UserId = " + UID;
                Log.d("Edad registrada", Edad.getText().toString());
                Log.d("Query update", update);
                Log.d("UPDATE", update);
                db.execSQL(update);


                    Fragment firstFragment = new FragmentInfoPersonal();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                    transaction.replace(R.id.navFragmentContainer, firstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

        //Esta funcion te regresa al fragmento anterior
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment firstFragment = new FragmentInfoPersonal();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
}