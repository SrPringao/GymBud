package com.example.gymbud.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gymbud.R;
import com.example.gymbud.Entidades.Sucursal;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SucursalesAdaptador extends RecyclerView.Adapter<SucursalesAdaptador.SucursalesViewHolder> {
    ArrayList<Sucursal> ListasSucursales;
    private Context context;
    final SucursalesAdaptador.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public SucursalesAdaptador(ArrayList<Sucursal> ListasSucursales,SucursalesAdaptador.OnItemClickListener listener){
        this.ListasSucursales = ListasSucursales;
        this.listener = listener;
    }


    @NonNull
    @Override
    public SucursalesAdaptador.SucursalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gimnasios,parent,false);
        return new SucursalesViewHolder(view);
    }
    public int setPosicion(int posicion){
        return posicion;
    }



    @Override
    public void onBindViewHolder(@NonNull SucursalesAdaptador.SucursalesViewHolder holder, int position) {
        holder.SubName.setText(ListasSucursales.get(position).getSubName());
        holder.Location.setText(ListasSucursales.get(position).getLocation());
        holder.rating.setRating(ListasSucursales.get(position).getRating());
        String url = ListasSucursales.get(position).getImageLink();

        Picasso.get().load(url)
                .placeholder(R.drawable.backgroundcornerinverted) // imagen de relleno
                .error(R.drawable.backgroundcornerinverted) // imagen de error si no se puede cargar la imagen
                .into(holder.imagen);

//        Glide.with(holder.itemView.getContext())
//                .load(url)
//                .placeholder(R.drawable.loading_icon)
//                .into(holder.imagen);


        Log.d("Recycler Sucursales: " + position, "onBindViewHolder: " + ListasSucursales.get(position).getRating());

        setPosicion(position);
    }



    @Override
    public int getItemCount() {
        return ListasSucursales.size();
    }

    public class SucursalesViewHolder extends RecyclerView.ViewHolder {
        int posicion = getAdapterPosition();
        TextView SubName,Location;
        RatingBar rating;
        ShapeableImageView imagen;

        public SucursalesViewHolder(@NonNull View itemView){
            super(itemView);
            SubName = itemView.findViewById(R.id.SubName);
            Location = itemView.findViewById(R.id.Location);
            rating = itemView.findViewById(R.id.Rating);
            imagen = itemView.findViewById(R.id.shapeableImageView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(view.getContext(), "Posicion: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

}
