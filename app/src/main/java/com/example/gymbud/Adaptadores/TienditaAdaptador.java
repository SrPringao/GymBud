package com.example.gymbud.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.R;
import com.example.gymbud.db.Entidades.Exercises;

import java.util.ArrayList;


public class TienditaAdaptador extends RecyclerView.Adapter<TienditaAdaptador.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasEjercicios;

    public TienditaAdaptador(ArrayList<Exercises> ListasEjercicios) {
        this.ListasEjercicios = ListasEjercicios;
    }

    public interface EventOnItemClick {
        void OnItemClick(int posicion);
    }

    EventOnItemClick listener;

    @NonNull
    @Override
    public TienditaAdaptador.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicios_tiendita, parent, false);
        return new EjerciciosViewHolder(view, listener);
    }

    public int setPosicion(int posicion) {
        return posicion;
    }

    @Override
    public void onBindViewHolder(@NonNull TienditaAdaptador.EjerciciosViewHolder holder, int position) {
        Exercises exercise = ListasEjercicios.get(position);
        holder.Nombre.setText(exercise.getName());
        holder.Enfoque.setText(exercise.getFocus());
    }


    @Override
    public int getItemCount() {
        return ListasEjercicios.size();
    }

    public void setOnClickListener(EventOnItemClick listener) {
        this.listener = listener;
    }

    public static class EjerciciosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EventOnItemClick listener;
        final TextView Nombre;
        final TextView Enfoque;

        public EjerciciosViewHolder(@NonNull View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            Nombre = itemView.findViewById(R.id.CVET1);
            Enfoque = itemView.findViewById(R.id.CVET2);
        }

        @Override
        public void onClick(View view) {

        }
    }
}