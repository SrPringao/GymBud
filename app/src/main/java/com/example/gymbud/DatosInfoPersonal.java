package com.example.gymbud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.gymbud.db.DbHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatosInfoPersonal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosInfoPersonal extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "UID";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int UID;
    private String mParam2;

    public DatosInfoPersonal() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param UID Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosInfoPersonal.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosInfoPersonal newInstance(String UID, String param2) {
        DatosInfoPersonal fragment = new DatosInfoPersonal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, UID);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
           // UID = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
       // int UID = getArguments().getInt("UID");
        return inflater.inflate(R.layout.fragment_datos_info_personal, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FragmentContainer activity = (FragmentContainer) getActivity();

        super.onViewCreated(view, savedInstanceState);
        ImageView imagen = view.findViewById(R.id.botonback1);
        Button BotonCalc = view.findViewById(R.id.Calc);
        EditText ETP,ETP2;
        ETP = view.findViewById(R.id.etInfo1);
        ETP2 = view.findViewById(R.id.etInfo2);

        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        BotonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ETP.getText().toString().isEmpty() || ETP2.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(),"Llena los 2 campos",Toast.LENGTH_SHORT).show();
                } else {


                    int UID = activity.UIDUSR();
                    Log.d("USR", "EL uid que llego es " + UID);
                    Float PAct = Float.parseFloat(ETP.getText().toString());
                    Float MP = Float.parseFloat(ETP2.getText().toString());

                    String update = "UPDATE PERSONINFO SET CurrentWeight = " + PAct + ",WeightGoal = " + MP + " WHERE UserId = " + UID;
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