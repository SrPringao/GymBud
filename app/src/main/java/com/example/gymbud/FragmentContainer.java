package com.example.gymbud;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.gymbud.Entidades.UrlDataSingleton;
import com.example.gymbud.Modulos.CreacionDeRutinas.Rutinas;
import com.example.gymbud.Modulos.InfoPersonal.FragmentInfoPersonal;
import com.example.gymbud.Modulos.SeleccionEjercicios.Ejercicios;
import com.example.gymbud.Modulos.Sucursales.Sucursales;

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

        //esto hace que cuando se abra el teclado no se mueva la pantalla
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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
                    fragment = new Rutinas();
                }else{
                    fragment = new FragmentInfoPersonal();
                }
                loadFragment(fragment);
            }
        });

        bottomNav.show(1,true);

        //set menu item on click listener
        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

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
                    UrlDataSingleton.getInstance().setId(null);


                }else if (item.getId() == 3){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragment = new Ejercicios();

                    UrlDataSingleton.getInstance().setId(null);


                }else if (item.getId() == 2){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }


                    fragment = new Rutinas();

                    UrlDataSingleton.getInstance().setId(null);

                }else{
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    UrlDataSingleton.getInstance().setId(null);
                    fragment = new FragmentInfoPersonal();
                }
                loadFragment(fragment);
            }
        });

    }

    @Override
    public void onBackPressed(){
    }




    private void loadFragment(Fragment fragment){
        //replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.navFragmentContainer,fragment,null)
                .commit();

    }

    //Esta funcion solo retorna el UID del usuario en caso de que se necesite
    public int UIDUSR(){
        return UID;
    }

    //Esta funcion recibe la fecha guardada en los sharedPreferences y la retorna.
    public String FechaG(){
        String fecha="0";
        Context context = this;
        SharedPreferences sharedPrefs = getSharedPreferences("Fecha", MODE_PRIVATE);
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
        SharedPreferences sharedPrefs = getSharedPreferences("FechaL", MODE_PRIVATE);
        long fecha = sharedPrefs.getLong("Fecha",0);
        long FechaC = System.currentTimeMillis();
        long diferencia = FechaC-fecha;
        long diasDesde = (long) Math.floor(diferencia/(1000*60*60*24));
        Log.d("DIASDESDE", ""+diasDesde);
        return diasDesde;
    }
}

