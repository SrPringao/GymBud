package com.example.gymbud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class FragmentContainer extends AppCompatActivity {

    MeowBottomNavigation bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                Fragment fragment;

                if (item.getId() == 4){
                    fragment = new Sucursales();

                }else if (item.getId() == 3){
                    fragment = new Ejercicios();


                }else if (item.getId() == 2){
                    fragment = new Rutinas();


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

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.navFragmentContainer,fragment,null).commit();
    }
}