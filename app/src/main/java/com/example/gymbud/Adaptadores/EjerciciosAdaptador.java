package com.example.gymbud.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Ejercicios;
import com.example.gymbud.R;
import com.example.gymbud.db.Entidades.Exercises;
import com.example.gymbud.fragment_ejercicio_seleccionado;

import java.util.ArrayList;

public class EjerciciosAdaptador extends RecyclerView.Adapter<EjerciciosAdaptador.EjerciciosViewHolder> {

    ArrayList<Exercises> ListaEjercicios;
    public EjerciciosAdaptador(ArrayList<Exercises> ListaEjercicios){
        this.ListaEjercicios = ListaEjercicios;
    }
    public interface EventOnItemClick{
        public void OnItemClick(int posicion);
    }
    EventOnItemClick listener;

    @NonNull
    @Override
    public EjerciciosAdaptador.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,null,false);

        return new EjerciciosViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EjerciciosAdaptador.EjerciciosViewHolder holder, int position) {
        holder.Nombre.setText(ListaEjercicios.get(position).getName());

    }
    public void setOnClickListener(EventOnItemClick listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return ListaEjercicios.size();
    }

    public static class EjerciciosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EventOnItemClick listener;
        int posicion;
        static TextView Nombre;
        public EjerciciosViewHolder(@NonNull View itemView,EventOnItemClick listener){
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener=listener;
            Nombre = itemView.findViewById(R.id.Testo);
        }
        public void setPosicion(int posicion){
            this.posicion = posicion;
        }
        @Override
        public void onClick(View view) {
            listener.OnItemClick(posicion);
        }
    }

}
