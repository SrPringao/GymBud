package com.example.gymbud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ScrollView;

import eightbitlab.com.blurview.BlurView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymbud.db.DbHelper;
import com.example.gymbud.db.DbQuery;

import net.colindodd.gradientlayout.GradientRelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link infopersonal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infopersonal extends Fragment {





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button botonson;
    EditText editadon;

    public infopersonal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infopersonal.
     */
    // TODO: Rename and change types and number of parameters
    public static infopersonal newInstance(String param1, String param2) {
        infopersonal fragment = new infopersonal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    NestedScrollView scroll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            scroll = scroll.findViewById(R.id.scroll);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_infopersonal,container,false);



        return v;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imagen = view.findViewById(R.id.otisImg);
        GradientRelativeLayout cardpeso = view.findViewById(R.id.cardpeso);
        GradientRelativeLayout  cardimc = view.findViewById(R.id.cardimc);
        GradientRelativeLayout  cardgrasa = view.findViewById(R.id.cardgrasa);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFragment = new DatosInfoPersonal();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.navFragmentContainer, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardpeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFragment = new DatosInfoPersonal();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.navFragmentContainer, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardimc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFragment = new DatosInfoImc();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.navFragmentContainer, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardgrasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFragment = new DatosInfoTg();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.navFragmentContainer, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
}