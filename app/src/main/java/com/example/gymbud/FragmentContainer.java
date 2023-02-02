package com.example.gymbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

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



        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_person_24));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_person_24));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_person_24));
        bottomNav.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_person_24));


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
                Toast.makeText(getApplicationContext(),"Reselect "+ item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        //set count to dashboard item
//        bottomNav.setCount(3, "10");
    }



    @Override
    public void onBackPressed() {

    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.navFragmentContainer,fragment,null).commit();
    }
    public int UIDUSR(){
        return UID;
    }
}