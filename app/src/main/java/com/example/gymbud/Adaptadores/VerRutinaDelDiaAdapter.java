package com.example.gymbud.Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.Entidades.ExerciseSet;
import com.example.gymbud.Entidades.Routine;
import com.example.gymbud.FragmentContainer;
import com.example.gymbud.Modulos.InfoPersonal.FragmentInfoPersonal;
import com.example.gymbud.Modulos.Login.MainActivity;
import com.example.gymbud.Modulos.SeleccionEjercicios.fragment_ejercicio_seleccionado;
import com.example.gymbud.R;
import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.IdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class VerRutinaDelDiaAdapter extends RecyclerView.Adapter<VerRutinaDelDiaAdapter.EjerciciosViewHolder> {

    Routine routine;
    List<ExerciseSet> ListasEjercicios;


FragmentManager fragmentManager;
    public VerRutinaDelDiaAdapter(Routine routine, FragmentManager fragmentManager) {
        this.routine = routine;
        this.fragmentManager =fragmentManager ;
        if (routine == null) {
            ListasEjercicios = new ArrayList<>();
        } else {
            ListasEjercicios = routine.getExerciseList();
        }
    }

    @NonNull
    @Override
    public VerRutinaDelDiaAdapter.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ver_rutinas, parent, false);
        return new EjerciciosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerRutinaDelDiaAdapter.EjerciciosViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ExerciseSet exercise = ListasEjercicios.get(position);
        holder.Nombre.setText(exercise.getName());
        holder.Sets.setText(String.valueOf(exercise.getNumSeries()));
        holder.Reps.setText(String.valueOf(exercise.getNumReps()));

        int muscleGroup = exercise.getMuscleGroup();

        List<String> muscleGroups = Arrays.asList(holder.itemView.getResources().getStringArray(R.array.ListaEjercicios));

        if (muscleGroup > 11){
            muscleGroup -= 1;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Integer, String> Tren = new HashMap<>();
                Tren.put( 1,"Hombro");
                Tren.put( 2,"Bicep");
                Tren.put( 3,"Pecho");
                Tren.put( 4,"Abs");
                Tren.put( 5,"Oblicuos");
                Tren.put( 6,"Antebrazo");
                Tren.put( 7,"Cuadriceps");
                Tren.put( 8,"Trapecios");
                Tren.put( 9,"Dorsal");
                Tren.put( 10,"Triceps");
                Tren.put( 11,"Espalda media");
                Tren.put( 13,"Gluteo");
                Tren.put( 14,"Femoral");
                Tren.put(15,"Pantorrilla");
                Fragment fragment = new fragment_ejercicio_seleccionado();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("id", ListasEjercicios.get(position).getId());
                args.putInt("ID", ListasEjercicios.get(position).getMuscleGroup());
                args.putString("Musculo", Tren.get(ListasEjercicios.get(position).getMuscleGroup()));

                fragment.setArguments(args);
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Log.d("VerRutinaDelDiaAdapter ", "muscleGroup:  " + muscleGroup);
        holder.GrupoMuscular.setText("Grupo muscular: "+muscleGroups.get(muscleGroup-1));

    }

    @Override
    public int getItemCount() {
        return ListasEjercicios.size();
    }

    public class EjerciciosViewHolder extends RecyclerView.ViewHolder  {
        final TextView Nombre;
        final TextView Sets;
        final TextView Reps;

        final TextView GrupoMuscular;

        public EjerciciosViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.VRCardNombreEjercicio);
            Sets = itemView.findViewById(R.id.VRCardSeries);
            Reps = itemView.findViewById(R.id.VRCardRepes);
            GrupoMuscular = itemView.findViewById(R.id.VRCardGrupoMuscular);
        }
    }
}