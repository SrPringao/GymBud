package com.example.gymbud;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gymbud.db.DbQuery;
import com.example.gymbud.db.Entidades.PersonInfo;
import com.example.gymbud.db.Entidades.Phrase;

import net.colindodd.gradientlayout.GradientRelativeLayout;

import java.util.Random;


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

        PersonInfo personInfo;
        TextView frase = view.findViewById(R.id.frase);
        FragmentContainer activity = (FragmentContainer) getActivity();
        int UID = activity.UIDUSR();
        String FechaG = activity.FechaG();
        String FechaAct = activity.FechaAct();
        float FechaC = activity.FechaLONG();

        ImageView imagen = view.findViewById(R.id.otisImg);
        TextView pesos = view.findViewById(R.id.Pesos);
        TextView IMC = view.findViewById(R.id.IMC);
        TextView TG = view.findViewById(R.id.TasaGrasa);
        TextView Racha = view.findViewById(R.id.diasn);
        GradientRelativeLayout cardpeso = view.findViewById(R.id.cardpeso);
        GradientRelativeLayout cardimc = view.findViewById(R.id.cardimc);
        GradientRelativeLayout cardgrasa = view.findViewById(R.id.cardgrasa);

        DbQuery dbQuery = new DbQuery(getContext());
        personInfo = dbQuery.verinfo(UID);


        rellenado(personInfo,UID,pesos,IMC,TG,Racha);
        fecha(FechaG,FechaAct,frase,FechaC);





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
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
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
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);

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
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, secondFragment);

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
    private void rellenado(PersonInfo personInfo, int UID, TextView pesos, TextView IMC, TextView TG,TextView racha){
        Context context = getContext();
        SharedPreferences sharedPrefs = context.getSharedPreferences("Fecha",context.MODE_PRIVATE);
        int RachaGuardada = sharedPrefs.getInt("RACHA",0);
        double imc = 0;
        double grasa;
        Log.d("abububub", Integer.toString(UID));

        imc = personInfo.getCurrentWeight() / Math.pow(personInfo.getHeight(), 2);
        Log.d("IMC", Double.toString(imc));
        imc = Math.round(imc);
        grasa = ((1.20*imc) + (0.23 * personInfo.getAge()) - (10.8 * personInfo.getGender()) - 5.4);
        grasa = Math.round(grasa);

        pesos.setText("Peso actual: " + personInfo.getCurrentWeight() +" | Meta de peso:"+personInfo.getWeightGoal());
        IMC.setText("IMC:"+ imc +"| Ideal:"+" 25.0 – 29.9");
        TG.setText("Tu tasa de grasa es del " + grasa+"%");
        racha.setText(String.valueOf(RachaGuardada));
    }

    private void fecha(String fecha,String DateT, TextView testoFrase,float FechaL)
    {
        Context context = getContext();
        SharedPreferences sharedPrefs = context.getSharedPreferences("Fecha",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        int Racha = sharedPrefs.getInt("RACHA",0);
        final int random = new Random().nextInt(74);
        DbQuery dbQuery = new DbQuery(getContext());
        Phrase frase;
        frase = dbQuery.verFrase(random);


        float FechaGUARDADA = sharedPrefs.getFloat("FechaDIF",0);
        float FechasDif = FechaL-FechaGUARDADA;


        Log.d("Diferencia Fechas",""+FechasDif );
        if((FechaL-FechaGUARDADA) == 1){
            Racha++;
            editor.putFloat("FechaDIF",FechaL);
            editor.putInt("RACHA", Racha);
            editor.commit();
        }else if(FechaL-FechaGUARDADA == 0){

        }else{
            Racha = 0;
            editor.putInt("RACHA", Racha);
            editor.putFloat("FechaDIF",FechaL);
            editor.commit();
        }

        if(DateT.equals(fecha)){
            int id = sharedPrefs.getInt("Id",0);
            frase = dbQuery.verFrase(id);
            testoFrase.setText(frase.getMotivation());
            Log.d("Fecha","Es el mismo dia");

        }else{
            long ahora = System.currentTimeMillis();
            testoFrase.setText(frase.getMotivation());
            editor.putString("Fecha", DateT);
            editor.putInt("Id", random);
            editor.putLong("FechaL",ahora);
            editor.commit();

        }
    }


}