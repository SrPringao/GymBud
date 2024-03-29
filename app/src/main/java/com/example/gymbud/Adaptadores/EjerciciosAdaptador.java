package com.example.gymbud.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.R;
import com.example.gymbud.Entidades.Exercises;

import java.util.ArrayList;


public class EjerciciosAdaptador extends RecyclerView.Adapter<EjerciciosAdaptador.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasEjercicios = new ArrayList<>();

    public EjerciciosAdaptador(ArrayList<Exercises> ListasEjercicios) {
        this.ListasEjercicios = ListasEjercicios;
    }

    public interface EventOnItemClick {
        public void OnItemClick(int id);

    }

    EventOnItemClick listener;

    @NonNull
    @Override
    public EjerciciosAdaptador.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicio_seleccionado, parent, false);
        return new EjerciciosViewHolder(view, listener);
    }

    public int setPosicion(int posicion) {
        return posicion;
    }

    @Override
    public void onBindViewHolder(@NonNull EjerciciosAdaptador.EjerciciosViewHolder holder, int position) {
        Exercises exercise = ListasEjercicios.get(position);
        holder.Nombre.setText(exercise.getName());
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

        public EjerciciosViewHolder(@NonNull View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            Nombre = itemView.findViewById(R.id.Testo);
        }

        @Override
        public void onClick(View view) {
            listener.OnItemClick(ListasEjercicios.get(getAdapterPosition()).getId());
        }
    }
}