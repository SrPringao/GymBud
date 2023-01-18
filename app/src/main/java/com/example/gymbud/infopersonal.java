package com.example.gymbud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ScrollView;

import eightbitlab.com.blurview.BlurView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymbud.db.DbHelper;
import com.example.gymbud.db.DbQuery;


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

    ScrollView scroll;

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
        botonson = v.findViewById(R.id.pruebon);
        editadon = v.findViewById(R.id.editt);


        botonson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbHelper dbHelper = new DbHelper(v.getContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String Update = "Update PHRASE SET Id = 77 WHERE Id = 1";
                //     String Insert = "INSERT INTO PHRASE (Id,Motivation) VALUES (4,'Si se puede brou')";
                //     String Delete = "DELETE FROM PHRASE WHERE Id = 4";
                db.execSQL(Update);
                Toast.makeText(v.getContext(),"Se realizo el cambio",Toast.LENGTH_SHORT);
            }
        });
        return v;

    }
}