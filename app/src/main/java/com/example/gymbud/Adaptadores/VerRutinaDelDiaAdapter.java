package com.example.gymbud.Adaptadores;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Entidades.ExerciseSet;
import com.example.gymbud.Entidades.Routine;
import com.example.gymbud.R;
import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.IdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VerRutinaDelDiaAdapter extends RecyclerView.Adapter<VerRutinaDelDiaAdapter.EjerciciosViewHolder> {

    Routine routine;
    List<ExerciseSet> ListasEjercicios;


    public VerRutinaDelDiaAdapter(Routine routine) {
        this.routine = routine;
        ListasEjercicios = routine.getExerciseList();
    }

    public interface EventOnItemClick {
        public void OnItemClick(int id);

    }

    EventOnItemClick listener;

    @NonNull
    @Override
    public VerRutinaDelDiaAdapter.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ver_rutinas, parent, false);
        return new EjerciciosViewHolder(view, listener);
    }

    public int setPosicion(int posicion) {
        return posicion;
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

        Log.d("VerRutinaDelDiaAdapter ", "muscleGroup:  " + muscleGroup);
        holder.GrupoMuscular.setText("Grupo muscular: "+muscleGroups.get(muscleGroup-1));

    }

    @Override
    public int getItemCount() {
        return ListasEjercicios.size();
    }

    public void setOnClickListener(EventOnItemClick listener) {
        this.listener = listener;
    }

    public class EjerciciosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EventOnItemClick listener;
        final TextView Nombre;
        final TextView Sets;
        final TextView Reps;

        final TextView GrupoMuscular;

        public EjerciciosViewHolder(@NonNull View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            Nombre = itemView.findViewById(R.id.VRCardNombreEjercicio);
            Sets = itemView.findViewById(R.id.VRCardSeries);
            Reps = itemView.findViewById(R.id.VRCardRepes);
            GrupoMuscular = itemView.findViewById(R.id.VRCardGrupoMuscular);
        }

        @Override
        public void onClick(View view) {
        }
    }
}