package com.example.gymbud.Adaptadores;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Modulos.SeleccionEjercicios.fragment_ejercicio_seleccionado;
import com.example.gymbud.Modulos.SeleccionEjercicios.stats;
import com.example.gymbud.R;

import java.util.ArrayList;

public class EjercicioParecidoAdaptador extends RecyclerView.Adapter<EjercicioParecidoAdaptador.ViewHolder>{
    ArrayList<Exercises> Ejercicios = new ArrayList<>();
    public interface EventOnItemClick {
        public void OnItemClick(int id);

    }
    EventOnItemClick listener;
    public EjercicioParecidoAdaptador(ArrayList<Exercises> ejercicios) {
        this.Ejercicios = ejercicios;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicioparecido, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercises ejercicio = Ejercicios.get(position);
        holder.nombreEjercicio.setText(ejercicio.getName());


        Log.d("EjercicioParecido", "onBindViewHolder: " + ejercicio.getName());
    }

    @Override
    public int getItemCount() {
        return Ejercicios.size();
    }
    public void setOnClickListener(EventOnItemClick listener) {
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nombreEjercicio;
        EventOnItemClick listener;
        public ViewHolder(View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            nombreEjercicio = itemView.findViewById(R.id.NombreEjercicio);
        }

        @Override
        public void onClick(View view) {
            listener.OnItemClick(Ejercicios.get(getAdapterPosition()).getId());
        }
    }
}
