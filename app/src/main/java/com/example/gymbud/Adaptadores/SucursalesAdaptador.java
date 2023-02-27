package com.example.gymbud.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.R;
import com.example.gymbud.db.Entidades.Sucursal;

import java.util.ArrayList;

public class SucursalesAdaptador extends RecyclerView.Adapter<SucursalesAdaptador.SucursalesViewHolder> {

    ArrayList<Sucursal> ListasSucursales;
    public SucursalesAdaptador(ArrayList<Sucursal> ListasSucursales){
        this.ListasSucursales = ListasSucursales;
    }
    public interface EventOnItemClick{
        public void OnItemClick(int posicion);
    }
    EventOnItemClick listener;

    @NonNull
    @Override
    public SucursalesAdaptador.SucursalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gimnasios,null,false);

        return new SucursalesViewHolder(view,listener);
    }
    public int setPosicion(int posicion){
        return posicion;
    }


    @Override
    public void onBindViewHolder(@NonNull SucursalesAdaptador.SucursalesViewHolder holder, int position) {
        holder.SubName.setText(ListasSucursales.get(position).getSubName());
        holder.Location.setText(ListasSucursales.get(position).getLocation());
        setPosicion(position);

    }


    public void setOnClickListener(EventOnItemClick listener){
        this.listener=listener;
    }



    @Override
    public int getItemCount() {
        return ListasSucursales.size();
    }

    public static class SucursalesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EventOnItemClick listener;
        int posicion = getAdapterPosition();
        TextView SubName,Location;
        RatingBar rating;
        ImageView imagen;

        public SucursalesViewHolder(@NonNull View itemView,EventOnItemClick listener){
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener=listener;
            SubName = itemView.findViewById(R.id.SubName);
            Location = itemView.findViewById(R.id.Location);
            rating = itemView.findViewById(R.id.Rating);
        }


        @Override
        public void onClick(View view) {
            listener.OnItemClick(getAdapterPosition()+1);
        }
    }

}
