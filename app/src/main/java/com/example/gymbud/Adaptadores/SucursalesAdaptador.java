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

public class SucursalesAdaptador extends RecyclerView.Adapter<SucursalesAdaptador.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasSucursales;
    public SucursalesAdaptador(ArrayList<Exercises> ListasSucursales){
        this.ListasSucursales = ListasSucursales;
    }
    public interface EventOnItemClick{
        public void OnItemClick(int posicion);
    }
    EventOnItemClick listener;

    @NonNull
    @Override
    public SucursalesAdaptador.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,null,false);

        return new EjerciciosViewHolder(view,listener);
    }
    public int setPosicion(int posicion){
        return posicion;
    }
    @Override
    public void onBindViewHolder(@NonNull SucursalesAdaptador.EjerciciosViewHolder holder, int position) {
        holder.Nombre.setText(ListasSucursales.get(position).getName());
        setPosicion(position);

    }
    public void setOnClickListener(EventOnItemClick listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return ListasSucursales.size();
    }

    public static class EjerciciosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EventOnItemClick listener;
        int posicion = getAdapterPosition();
        static TextView Nombre;
        public EjerciciosViewHolder(@NonNull View itemView,EventOnItemClick listener){
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener=listener;
            Nombre = itemView.findViewById(R.id.Testo);
        }


        @Override
        public void onClick(View view) {
            listener.OnItemClick(getAdapterPosition()+1);
        }
    }

}
