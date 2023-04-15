package com.example.gymbud.Modulos.InfoPersonal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.Entidades.PersonInfo;
import com.example.gymbud.Entidades.Phrase;
import com.example.gymbud.FragmentContainer;
import com.example.gymbud.Modulos.CreacionDeRutinas.RutinasAutomaticas.Encuesta;
import com.example.gymbud.Modulos.CreacionDeRutinas.RutinasPersonalizadas.CreacionDeRutinas;
import com.example.gymbud.Modulos.Login.MainActivity;
import com.example.gymbud.Modulos.VerRutinas.VerRutinas;
import com.example.gymbud.R;

import net.colindodd.gradientlayout.GradientRelativeLayout;

import java.util.Calendar;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInfoPersonal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInfoPersonal extends Fragment {





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentInfoPersonal() {
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
    public static FragmentInfoPersonal newInstance(String param1, String param2) {
        FragmentInfoPersonal fragment = new FragmentInfoPersonal();
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
    ProgressBar progressBar1,progressBar2;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar1 = view.findViewById(R.id.progress);
        progressBar2 = view.findViewById(R.id.progress2);
        PersonInfo personInfo;
        TextView frase = view.findViewById(R.id.frase);
        FragmentContainer activity = (FragmentContainer) getActivity();
        int UID = activity.UIDUSR();
        String FechaG = activity.FechaG();
        String FechaAct = activity.FechaAct();
        float FechaC = activity.FechaLONG();
        ImageView imgLogout = view.findViewById(R.id.imglogout);

        ImageView imagen = view.findViewById(R.id.otisImg);
        TextView pesos = view.findViewById(R.id.Pesos);
        TextView IMC = view.findViewById(R.id.IMC);
        TextView TG = view.findViewById(R.id.TasaGrasa);
        TextView Racha = view.findViewById(R.id.diasn);
        GradientRelativeLayout cardpeso = view.findViewById(R.id.cardpeso);
        GradientRelativeLayout cardimc = view.findViewById(R.id.cardimc);
        GradientRelativeLayout cardgrasa = view.findViewById(R.id.cardgrasa);
        GradientRelativeLayout cardRutina = view.findViewById(R.id.cardRutina);

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int numberDayOfWeek = ((dayOfWeek - Calendar.SUNDAY + 7) % 7);
        if (numberDayOfWeek == 0) {
            numberDayOfWeek = 7;
        }


        Log.d("dia de la semana ", String.valueOf(numberDayOfWeek));


        DbQuery dbQuery = new DbQuery(getContext());
        personInfo = dbQuery.verinfo(UID);

//        Log.d("Entrando a la query con ", String.valueOf(numberDayOfWeek));

        View separdor = view.findViewById(R.id.separadorGM);
        ImageView imgGM1 = view.findViewById(R.id.imagenGM1);
        ImageView imgGM2 = view.findViewById(R.id.imagenGM2);
        TextView textoGMROutine = view.findViewById(R.id.textViewSub2Title);


        String dayName="";
        TypedArray imagenes = getResources().obtainTypedArray(R.array.imagenes);

        if (!dbQuery.routineDayAlreadyFilled(numberDayOfWeek)) {
            dayName = "El dia "+ getResources().getStringArray(R.array.DiasSemana)[numberDayOfWeek - 1] + " no tiene rutina asignada";
            textoGMROutine.setText(dayName);
            Log.d("Error sin rutina", "El dia no tiene ninguna rutina asignada");
            separdor.setVisibility(View.GONE);
            imgGM2.setVisibility(View.GONE);
            //layout_centerInParent = true to img1
            imgGM1.setVisibility(View.VISIBLE);
            imgGM1.setImageResource(R.drawable.icmg_cruz);
            //center image in parent
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imgGM1.getLayoutParams();
            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            imgGM1.setLayoutParams(params);

            imgGM1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment firstFragment = new CreacionDeRutinas();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                    transaction.replace(R.id.navFragmentContainer, firstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

        }else {
            try {
                //using strings.xml set day name to textview
                dayName = "Rutina del dia " + getResources().getStringArray(R.array.DiasSemana)[numberDayOfWeek - 1];
                textoGMROutine.setText(dayName);
                List<Integer> Repetidos = dbQuery.gruposRepetidos(numberDayOfWeek);

                if (Repetidos.size() <= 1) {
                    int index = Repetidos.get(0) - 1;
                    separdor.setVisibility(View.GONE);
                    imgGM2.setVisibility(View.GONE);
                    Drawable drawable = imagenes.getDrawable(index);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imgGM1.getLayoutParams();
                    params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                    imgGM1.setLayoutParams(params);
                    imgGM1.setImageDrawable(drawable);
                    imagenes.recycle();
                } else {
                    int index1 = Repetidos.get(0) - 1;
                    int index2 = Repetidos.get(1) - 1;
                    separdor.setVisibility(View.VISIBLE);
                    imgGM2.setVisibility(View.VISIBLE);

                    Drawable drawable1 = imagenes.getDrawable(index1);
                    Drawable drawable2 = imagenes.getDrawable(index2);
                    imgGM1.setImageDrawable(drawable1);
                    imgGM2.setImageDrawable(drawable2);

                    imagenes.recycle();
                }
            }catch (Exception e){
                Log.d("Error", "Error al cargar las imagenes");
            }
        }

        //get routine by day
        //dbQuery.getRoutineByDay(numberDayOfWeek);

        int finalNumberDayOfWeek = numberDayOfWeek;
        cardRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("dia", finalNumberDayOfWeek);
                Fragment secondFragment = new VerRutinas();
                secondFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rellenado(personInfo,UID,pesos,IMC,TG,Racha);
        fecha(FechaG,FechaAct,frase,FechaC);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFragment = new Encuesta();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        //setonclick en caso de que presiones la imagen de logout
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //guardamos en shared preferences que el usuario no esta logeado
                SharedPreferences preferences = getActivity().getSharedPreferences("MainArchivo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("UID", 0);
                editor.commit();

                //mandamos al usuario a la pantalla de login
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                }
        });


        //setOnClick listener en caso de que presiones la tarjeta de peso, te manda a DatosInfoPersonal
        cardpeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFragment = new CardInfoPeso();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        //setOnClick listener en caso de que presiones la tarjeta de peso, te manda a DatosInfoIMC
        cardimc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFragment = new CardInfoImc();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);

                transaction.replace(R.id.navFragmentContainer, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //setOnClick listener en caso de que presiones la tarjeta de peso, te manda a DatosTg
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

    //Esta funcion lo que hace es rellenar las tarjetas con la informacion ya guardada en la base de datos anteriormente, recibe un objeto tipo PersonInfo
    //recibe el UID que es el id del usuario en la base de datos, y los textview de las tarjetas, despues realiza el calculo del imc con el peso y la altura guardada
    //en la bd, tambien calcula la grasa corporal con una funcion que usa el imc del usuario previamente calculado, su edad y su genero
    //Para finalizar coloca los calculos realizados en su tarjeta correspondiente.
    private void rellenado(PersonInfo personInfo, int UID, TextView pesos, TextView IMC, TextView TG,TextView racha){
        Context context = getContext();
        SharedPreferences sharedPrefs = context.getSharedPreferences("Fecha", Context.MODE_PRIVATE);
        int RachaGuardada = sharedPrefs.getInt("RACHA",0);
        double imc = 0;
        double grasa;
       // Log.d("abububub", Integer.toString(UID));

        imc = personInfo.getCurrentWeight() / Math.pow(personInfo.getHeight(), 2);
      //  Log.d("IMC", Double.toString(imc));
        imc = Math.round(imc);
        grasa = ((1.20*imc) + (0.23 * personInfo.getAge()) - (10.8 * personInfo.getGender()) - 5.4);
        grasa = Math.round(grasa);

        pesos.setText("Peso actual: " + personInfo.getCurrentWeight() +" | Meta de peso:"+personInfo.getWeightGoal());
        IMC.setText("IMC:"+ imc +"| Ideal:"+" 18.5 – 24.9");
        TG.setText("Tu tasa de grasa es del " + grasa+"%");
        racha.setText(String.valueOf(RachaGuardada));
        if(personInfo.getCurrentWeight()!=0 && personInfo.getWeightGoal()!=0) {
            int Progreso = (int) ((personInfo.getCurrentWeight() * 100) / personInfo.getWeightGoal());

            if(Progreso>100){
                Progreso=100-(Progreso-100);
            }
            progressBar1.setProgress(Progreso);
            Log.d("Peso", Integer.toString(Progreso));
        }
        if(imc>0){
            int ProgresoIMC = (int) (imc * 100 / 24.9);
            if(ProgresoIMC>100){
                ProgresoIMC=100-(ProgresoIMC-100);
            }
            Log.d("IMC", Integer.toString(ProgresoIMC));
            progressBar2.setProgress(ProgresoIMC);
        }

    }

    //Esta funcion realiza una comparacion con la fecha previamente registrada en los shared preferencees que seria la ultima fecha que el usuario
    //ingreso a la aplicacion, y despues realiza una comparacion con la fecha actual y si la resta de estas fechas es mayor a uno se reinicia la racha de asistencias a 0
    //Si la resta es igual a uno se intercambia la fecha guardada con la actual y se agrega uno a la racha, despues se guardan los cambios, si la resta es
    //tambien esta misma funcion realiza un cambio en la frase del dia si la fecha actual es diferente de la fecha guardada, si asi es se llama la funcion verFrase
    //E ingresa el texto en el textview de la frase
    //Si la fecha es la misma recibe el id de la frase guardado en los shared preferences y hace la query con el id guardado
    private void fecha(String fecha,String DateT, TextView testoFrase, float FechaL)
    {
        Context context = getContext();
        SharedPreferences sharedPrefs = context.getSharedPreferences("Fecha", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        int Racha = sharedPrefs.getInt("RACHA",0);
        final int random = new Random().nextInt(74);
        DbQuery dbQuery = new DbQuery(getContext());
        Phrase frase;
        frase = dbQuery.verFrase(random);


        float FechaGUARDADA = sharedPrefs.getFloat("FechaDIF",0);


       // Log.d("Diferencia Fechas",""+FechasDif );
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
       //     Log.d("Fecha","Es el mismo dia");

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