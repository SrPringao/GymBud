package com.example.gymbud.Adaptadores;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.R;
import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.IdList;

import java.util.ArrayList;
import java.util.List;


public class VerEliminarCarritoAdapter extends RecyclerView.Adapter<VerEliminarCarritoAdapter.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasEjercicios;
    ArrayList<Integer> listaSeries = new ArrayList<>();
    ArrayList<Integer>listaReps = new ArrayList<>();
    TextView tiempoEstimadoTextView;





    public VerEliminarCarritoAdapter(ArrayList<Exercises> ListasEjercicios, TextView tiempoEstimadoTextView) {
        this.ListasEjercicios = ListasEjercicios;
        this.tiempoEstimadoTextView = tiempoEstimadoTextView;

        //añadir a la lista de series y reps el numero de series y reps que tiene cada ejercicio
        for (int i = 0; i < ListasEjercicios.size(); i++) {
            listaSeries.add(ListasEjercicios.get(i).getSets());
            listaReps.add(ListasEjercicios.get(i).getReps());
        }

        tiempoEstimadoTextView.setText(String.valueOf(tiempoEstimadoFunc(listaSeries, listaReps)));
        Log.d("Lista de series", listaSeries.toString());
        Log.d("Lista de reps", listaReps.toString());
    }


    public interface EventOnItemClick {
        public void OnItemClick(int id);

    }

    EventOnItemClick listener;

    @NonNull
    @Override
    public VerEliminarCarritoAdapter.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicios_seleccionados_tiendita, parent, false);
        return new EjerciciosViewHolder(view, listener);
    }

    public int setPosicion(int posicion) {
        return posicion;
    }

    @Override
    public void onBindViewHolder(@NonNull VerEliminarCarritoAdapter.EjerciciosViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Exercises exercise = ListasEjercicios.get(position);
        holder.Nombre.setText(exercise.getName());
        holder.sets.setText(String.valueOf(exercise.getSets()));
        holder.reps.setText(String.valueOf(exercise.getReps()));

        holder.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Elimina el elemento de la lista de ejercicios y actualiza la posición de los demás elementos
                ListasEjercicios.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, ListasEjercicios.size());
                // Elimina el elemento de la lista de ids
                int exerciseId = exercise.getId();
                IdList.removeId(exerciseId);

                // Elimina el elemento de la lista de series y reps
                listaSeries.remove(position);
                listaReps.remove(position);

                tiempoEstimadoTextView.setText(String.valueOf(tiempoEstimadoFunc(listaSeries, listaReps)));

                Log.d("Lista de series", listaSeries.toString());
                Log.d("Lista de reps", listaReps.toString());
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

    public class EjerciciosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EventOnItemClick listener;
        final TextView Nombre;
        final TextView sets;
        final TextView reps;
        final ImageView Button;

        public EjerciciosViewHolder(@NonNull View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            Nombre = itemView.findViewById(R.id.TiNombreEjercicio);
            sets = itemView.findViewById(R.id.TiSeries);
            reps = itemView.findViewById(R.id.TiRepes);
            Button = itemView.findViewById(R.id.TiClearIcon);
//            tiempoEstimado = itemView.findViewById(R.id.TeTiempoEstimado);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public int tiempoEstimadoFunc(ArrayList<Integer> listaSeries, ArrayList<Integer> listaReps){
        double tiempo = 0;
        double tiempoDescanso = 0;

        if (listaSeries.size() == 0 || listaReps.size() == 0){
            return 0;
        }else {
            //tiempo de descanso entre ejercicios
            //tomando en cuenta que se descansara 2 minutos entre ejercicios mas tiempos muertos
            tiempoDescanso += listaSeries.size() * 2;
            Log.d("Tiempo entre ejercicios", String.valueOf(tiempoDescanso));

            //tiempo de descansos entre series
            //tomando en cuenta que se descansara 1 minuto y medio entre series mas tiempos muertos
            for (int i = 0; i < listaSeries.size(); i++) {
                tiempoDescanso += listaSeries.get(i)*1.5;
            }

            Log.d("Tiempo de descanso total", String.valueOf(tiempoDescanso));

            //total de repeticiones
            //tomando en cuenta que por cada repeticion se tarda 5 segundos
            for (int i = 0; i < listaReps.size(); i++) {
                tiempo += (((listaReps.get(i) * listaSeries.get(i)) * 5)/60f );
            }

            Log.d("Tiempo de repeticiones", String.valueOf(tiempo));

            //tiempo total
            Log.d("Tiempo total", String.valueOf(tiempo + tiempoDescanso));

            return (int) (tiempo + tiempoDescanso);
        }
    }
}