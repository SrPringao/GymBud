package com.example.gymbud.Adaptadores;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.R;
import com.example.gymbud.db.Entidades.Exercises;
import com.example.gymbud.db.Entidades.IdList;

import java.util.ArrayList;



public class TienditaAdaptador extends RecyclerView.Adapter<TienditaAdaptador.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasEjercicios;
    ArrayList<Integer> listaDeIds = new ArrayList<>(); // Aquí se declara la lista de ids


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
        holder.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int exerciseId = exercise.getId();
                listaDeIds.add(exerciseId);
                Log.d("Lista de ids", listaDeIds.toString());
                Toast.makeText(view.getContext(), "Ejercicio agregado a la lista", Toast.LENGTH_SHORT).show();
                IdList.getInstance().add(exerciseId);

                //toast that shows the current list of ids
//                Toast.makeText(view.getContext(), listaDeIds.toString(), Toast.LENGTH_SHORT).show();
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

    public static class EjerciciosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EventOnItemClick listener;
        final TextView Nombre;
        final TextView Enfoque;
        final Button Button;



        public EjerciciosViewHolder(@NonNull View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            Nombre = itemView.findViewById(R.id.CVET1);
            Enfoque = itemView.findViewById(R.id.CVET2);
            Button = itemView.findViewById(R.id.CVEBotonAgregar);
        }

        @Override
        public void onClick(View view) {

        }
    }
}