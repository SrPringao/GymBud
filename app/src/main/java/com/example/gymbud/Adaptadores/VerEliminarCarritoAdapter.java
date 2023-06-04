package com.example.gymbud.Adaptadores;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.IdList;
import com.example.gymbud.R;

import java.util.ArrayList;


public class VerEliminarCarritoAdapter extends RecyclerView.Adapter<VerEliminarCarritoAdapter.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasEjercicios;
    ArrayList<Integer> listaSeries = new ArrayList<>();
    ArrayList<Integer>listaReps = new ArrayList<>();
    ArrayList<Integer>listaTiempo = new ArrayList<>();
    ArrayList<String>listaNombres = new ArrayList<>();
    TextView tiempoEstimadoTextView;


    public VerEliminarCarritoAdapter(ArrayList<Exercises> ListasEjercicios, TextView tiempoEstimadoTextView) {
        this.ListasEjercicios = ListasEjercicios;
        this.tiempoEstimadoTextView = tiempoEstimadoTextView;

        //añadir a la lista de series y reps el numero de series y reps que tiene cada ejercicio
        for (int i = 0; i < ListasEjercicios.size(); i++) {
            listaNombres.add(ListasEjercicios.get(i).getName());
            listaSeries.add(ListasEjercicios.get(i).getSets());
            listaReps.add(ListasEjercicios.get(i).getReps());
            listaTiempo.add(ListasEjercicios.get(i).getTime());
        }

        tiempoEstimadoTextView.setText(String.valueOf(tiempoEstimadoFunc(listaSeries, listaReps, listaTiempo)));
        Log.d("Lista de ejercicios" , listaNombres.toString());
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VerEliminarCarritoAdapter.EjerciciosViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Exercises exercise = ListasEjercicios.get(position);
        holder.Nombre.setText(exercise.getName());

        if (exercise.getSets() == 0 && exercise.getReps() == 0){

            holder.sets.setVisibility(View.INVISIBLE);
            holder.reps.setVisibility(View.INVISIBLE);
            holder.ekis.setText(String.valueOf(exercise.getTime()) + " Minutos");
            holder.ekis.setTextSize(25);
        } else {
            holder.sets.setVisibility(View.VISIBLE); // Mostrar sets
            holder.reps.setVisibility(View.VISIBLE); // Mostrar reps
            holder.sets.setText(String.valueOf(exercise.getSets()));
            holder.reps.setText(String.valueOf(exercise.getReps()));
            holder.ekis.setText("X"); // Limpiar texto

        }


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
//                Log id eliminado
                Log.d("Id eliminado", String.valueOf(exerciseId));

                listaNombres.remove(position);
                listaSeries.remove(position);
                listaReps.remove(position);
                listaTiempo.remove(position);

                tiempoEstimadoTextView.setText(String.valueOf(tiempoEstimadoFunc(listaSeries, listaReps, listaTiempo)));

                Log.d("Lista de ejercicios" , listaNombres.toString());
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
        final TextView ekis;
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
            ekis = itemView.findViewById(R.id.TiX);
            Button = itemView.findViewById(R.id.TiClearIcon);
//            tiempoEstimado = itemView.findViewById(R.id.TeTiempoEstimado);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public int tiempoEstimadoFunc(ArrayList<Integer> listaSeries, ArrayList<Integer> listaReps, ArrayList<Integer> listaTiempo){
        double tiempo = 0;
        double tiempoDescanso = 0;

        if (listaSeries.size() == 0 || listaReps.size() == 0){
            return 0;
        }else {
            //solo por agregar un ejercicio ya son 2 minutos por default contando los tiempos muertos

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



            //tiempo total
            return (int) (tiempo + tiempoDescanso+ listaTiempo.stream().mapToInt(Integer::intValue).sum());
        }
    }
}