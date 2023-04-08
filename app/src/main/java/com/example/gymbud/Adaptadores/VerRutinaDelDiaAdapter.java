package com.example.gymbud.Adaptadores;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicios_seleccionados_tiendita, parent, false);
        return new EjerciciosViewHolder(view, listener);
    }

    public int setPosicion(int posicion) {
        return posicion;
    }

    @Override
    public void onBindViewHolder(@NonNull VerRutinaDelDiaAdapter.EjerciciosViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ExerciseSet exercise = ListasEjercicios.get(position);
        holder.Nombre.setText(exercise.getName());
        holder.sets.setText(String.valueOf(exercise.getNumSeries()));
        holder.reps.setText(String.valueOf(exercise.getNumReps()));

        Log.d("VerRutinaDelDiaAdapter ", "id:  " + exercise.getId());
        Log.d("VerRutinaDelDiaAdapter ", "nombre:  " + exercise.getName());

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
        final TextView sets;
        final TextView reps;

        public EjerciciosViewHolder(@NonNull View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            Nombre = itemView.findViewById(R.id.TiNombreEjercicio);
            sets = itemView.findViewById(R.id.TiSeries);
            reps = itemView.findViewById(R.id.TiRepes);
        }

        @Override
        public void onClick(View view) {
        }
    }
}