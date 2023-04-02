package com.example.gymbud.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Ejercicios;
import com.example.gymbud.FragmentContainer;
import com.example.gymbud.R;
import com.example.gymbud.db.Entidades.Exercises;
import com.example.gymbud.fragment_ejercicio_seleccionado;

import java.util.ArrayList;


public class CarritoEjerciciosAdapter extends RecyclerView.Adapter<CarritoEjerciciosAdapter.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasEjercicios;

    public CarritoEjerciciosAdapter(ArrayList<Exercises> ListasEjercicios) {
        this.ListasEjercicios = ListasEjercicios;
    }

    public interface EventOnItemClick {
        public void OnItemClick(int id);

    }

    EventOnItemClick listener;

    @NonNull
    @Override
    public CarritoEjerciciosAdapter.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicios_seleccionados_tiendita, parent, false);
        return new EjerciciosViewHolder(view, listener);
    }

    public int setPosicion(int posicion) {
        return posicion;
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoEjerciciosAdapter.EjerciciosViewHolder holder, int position) {
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
            Nombre = itemView.findViewById(R.id.TiNombreEjercicio);
        }

        @Override
        public void onClick(View view) {
        }
    }
}