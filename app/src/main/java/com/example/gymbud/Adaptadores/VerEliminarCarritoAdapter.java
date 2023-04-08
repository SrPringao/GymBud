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

import com.example.gymbud.R;
import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.IdList;

import java.util.ArrayList;


public class VerEliminarCarritoAdapter extends RecyclerView.Adapter<VerEliminarCarritoAdapter.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasEjercicios;

    public VerEliminarCarritoAdapter(ArrayList<Exercises> ListasEjercicios) {
        this.ListasEjercicios = ListasEjercicios;
    }

    public interface EventOnItemClick {
        public void OnItemClick(int id);

    }

    EventOnItemClick listener;

    @NonNull
    @Override
    public VerEliminarCarritoAdapter.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicios_seleccionados_tiendita, parent, false);
        return new EjerciciosViewHolder(view, listener);
    }

    public int setPosicion(int posicion) {
        return posicion;
    }

    @Override
    public void onBindViewHolder(@NonNull VerEliminarCarritoAdapter.EjerciciosViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Exercises exercise = ListasEjercicios.get(position);
        holder.Nombre.setText(exercise.getName());
        holder.sets.setText(String.valueOf(exercise.getSets()));
        holder.reps.setText(String.valueOf(exercise.getReps()));

        Log.d("VerEliminarCarritoAdapter ", "id:  " + exercise.getId());
        Log.d("VerEliminarCarritoAdapter ", "nombre:  " + exercise.getName());
        Log.d("VerEliminarCarritoAdapter ", "series:  " + exercise.getSets());
        Log.d("VerEliminarCarritoAdapter ", "repes:  " + exercise.getReps());
        Log.d("VerEliminarCarritoAdapter ", "musculo:  " + exercise.getMuscularGroup());

        holder.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Elimina el elemento de la lista de ejercicios y actualiza la posición de los demás elementos
                ListasEjercicios.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, ListasEjercicios.size());
                // Elimina el elemento de la lista de ids
                int exerciseId = exercise.getId();
                IdList.removeId(exerciseId);
            }
        });
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


        final ImageView Button;

        public EjerciciosViewHolder(@NonNull View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            Nombre = itemView.findViewById(R.id.TiNombreEjercicio);
            sets = itemView.findViewById(R.id.TiSeries);
            reps = itemView.findViewById(R.id.TiRepes);
            Button = itemView.findViewById(R.id.TiClearIcon);
        }

        @Override
        public void onClick(View view) {
        }
    }
}