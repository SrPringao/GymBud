package com.example.gymbud.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Ejercicios;
import com.example.gymbud.R;
import com.example.gymbud.db.Entidades.Exercises;

import java.util.ArrayList;

public class EjerciciosAdaptador extends RecyclerView.Adapter<EjerciciosAdaptador.EjerciciosViewHolder> {

    ArrayList<Exercises> ListaEjercicios;
    public EjerciciosAdaptador(ArrayList<Exercises> ListaEjercicios){
        this.ListaEjercicios = ListaEjercicios;
    }

    @NonNull
    @Override
    public EjerciciosAdaptador.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,null,false);

        return new EjerciciosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EjerciciosAdaptador.EjerciciosViewHolder holder, int position) {
        holder.Nombre.setText(ListaEjercicios.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return ListaEjercicios.size();
    }

    public class EjerciciosViewHolder extends RecyclerView.ViewHolder {

        TextView Nombre;
        public EjerciciosViewHolder(@NonNull View itemView){
            super(itemView);
            Nombre = itemView.findViewById(R.id.Testo);
        }
    }
}
