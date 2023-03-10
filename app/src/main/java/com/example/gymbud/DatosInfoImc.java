package com.example.gymbud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.gymbud.db.DbHelper;

import net.colindodd.gradientlayout.GradientRelativeLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatosInfoImc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosInfoImc extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DatosInfoImc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosInfoImc.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosInfoImc newInstance(String param1, String param2) {
        DatosInfoImc fragment = new DatosInfoImc();
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

    ImageView imagen;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos_info_imc, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentContainer activity = (FragmentContainer) getActivity();

        ImageView imagen = view.findViewById(R.id.botonback2);
        Button boton = view.findViewById(R.id.Calc);
        EditText testo = view.findViewById(R.id.etInfo1);

        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Esta funcion se asegura que los 2 campos esten llenos, si no le manda un mensaje al usuario, despues llama la funcion UIDUSR para recibir el UID y realiza
        //un update en PERSONINFO cambiando la altura dependiendo del uid recibido, despues te regresa al fragment anterior
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(testo.getText().toString().isEmpty() ){
                    Toast.makeText(getContext(),"Llena el campo requerido",Toast.LENGTH_SHORT).show();

                }else{

                    int UID = activity.UIDUSR();
                    String update = "UPDATE PERSONINFO SET Height = "+testo.getText().toString()+ " WHERE UserId = " + UID;
                    Log.d("UPDATE", update);
                    db.execSQL(update);

                    Fragment firstFragment = new infopersonal();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                    transaction.replace(R.id.navFragmentContainer, firstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });
        //Este boton te regresa al fragment anterior
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment firstFragment = new infopersonal();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }


}