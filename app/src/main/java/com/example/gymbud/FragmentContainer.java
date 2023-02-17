package com.example.gymbud;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.gymbud.Adaptadores.EjerciciosAdaptador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentContainer extends AppCompatActivity {

    MeowBottomNavigation bottomNav;

    ImageView imagen;
    int UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UID = getIntent().getExtras().getInt("UID");
        Log.d("UID", "Llego el UID " + UID);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        bottomNav = findViewById(R.id.bottomNav);



        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.icinfo));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.icrutinas));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.icejercicios));
        bottomNav.add(new MeowBottomNavigation.Model(4,R.drawable.icsucursales));

        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = new Fragment();


                if (item.getId() == 4){
                    fragment = new Sucursales();

                }else if (item.getId() == 3){
                    fragment = new Ejercicios();


                }else if (item.getId() == 2){
                    fragment = new DetallesSucursal();


                }else{
                    fragment = new infopersonal();
                }
                loadFragment(fragment);
            }
        });

        bottomNav.show(1,true);

        //set menu item on click listener
        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //display a toast
                //Toast.makeText(getApplicationContext(),"Clickeaste "+ item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        //set on reselect listener
        bottomNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //display a toast
                //Toast.makeText(getApplicationContext(),"Reselect "+ item.getId(), Toast.LENGTH_SHORT).show();
                Fragment fragment;

                if (item.getId() == 4){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragment = new Sucursales();

                }else if (item.getId() == 3){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragment = new Ejercicios();


                }else if (item.getId() == 2){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragment = new DetallesSucursal();


                }else{
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragment = new infopersonal();
                }
                loadFragment(fragment);
            }
        });

        //set count to dashboard item
//        bottomNav.setCount(3, "10");
    }

    @Override
    public void onBackPressed(){
    }




    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.navFragmentContainer,fragment,null).commit();
    }

    //Esta funcion solo retorna el UID del usuario en caso de que se necesite
    public int UIDUSR(){
        return UID;
    }

    //Esta funcion recibe la fecha guardada en los sharedPreferences y la retorna.
    public String FechaG(){
        String fecha="0";
        Context context = this;
        SharedPreferences sharedPrefs = getSharedPreferences("Fecha",context.MODE_PRIVATE);
        fecha = sharedPrefs.getString("Fecha","0");
        return fecha;
    }
    //Esta funcion guarda la fecha actual, le da formato YYYY/MM/dd y lo retorna
    public String FechaAct(){
        long ahora = System.currentTimeMillis();
        Date fechaD = new Date(ahora);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String DateT = df.format(fechaD);
        Log.d("FechaAct",DateT);
        return  DateT;
    }

    //Esta funcion realiza lo mismo que FechaAct, solo con la diferencia de que realiza un calculo para poder restar las fechas
    // Y asi poder calcular cuantos dias hay de diferencia, esta funcion retorna la fecha en cantidad de dias.
    public float FechaLONG(){
        Context context = this;//contesto
        SharedPreferences sharedPrefs = getSharedPreferences("FechaL",context.MODE_PRIVATE);
        long fecha = sharedPrefs.getLong("Fecha",0);
        long FechaC = System.currentTimeMillis();
        long diferencia = FechaC-fecha;
        long diasDesde = (long) Math.floor(diferencia/(1000*60*60*24));
        Log.d("DIASDESDE", ""+diasDesde);
        return diasDesde;
    }
}

