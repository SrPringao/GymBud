package com.example.gymbud.Adaptadores;

import static com.example.gymbud.Entidades.IdList.containsExerciseWithId;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.Entidades.ExerciseSet;
import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.IdList;
import com.example.gymbud.R;

import java.util.ArrayList;
import java.util.HashMap;


public class AgregarEjerciciosCarritoAdapter extends RecyclerView.Adapter<AgregarEjerciciosCarritoAdapter.EjerciciosViewHolder> {

    ArrayList<Exercises> ListasEjercicios;
    ArrayList<Integer> listaDeIds = new ArrayList<>(); // Aquí se declara la lista de ids

    ArrayList<Integer> listaDeGrupos = new ArrayList<>(); // Aquí se declara la lista de grupos


    public AgregarEjerciciosCarritoAdapter(ArrayList<Exercises> ListasEjercicios) {
        this.ListasEjercicios = ListasEjercicios;

    }

    public interface EventOnItemClick {
        void OnItemClick(int posicion);
    }

    EventOnItemClick listener;

    @NonNull
    @Override
    public AgregarEjerciciosCarritoAdapter.EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ejercicios_tiendita, parent, false);
        return new EjerciciosViewHolder(view, listener);
    }

    public int setPosicion(int posicion) {
        return posicion;
    }


    @Override
    public void onBindViewHolder(@NonNull AgregarEjerciciosCarritoAdapter.EjerciciosViewHolder holder, int position) {
        Exercises exercise = ListasEjercicios.get(position);
        holder.Nombre.setText(exercise.getName());
        holder.Enfoque.setText(exercise.getFocus());


        String url = "https://francoaldrete.com/GymBud/Ejercicios/" + exercise.getId() + ".mp4";
        holder.videoView.setVideoPath(url);
//        holder.videoView.setVisibility(View.GONE);



        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0f, 0f);
                // Ocultar el ProgressBar y mostrar el video una vez que esté cargado
//                holder.videoView.setLayoutParams(layoutParams);
                Log.wtf("Video Cargo" , "True");
                holder.videoView.start();
                holder.ProgressBar.setVisibility(View.GONE);
                holder.RelativeLayout.setVisibility(View.GONE);
            }
        });


        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // El video ha terminado de reproducirse, reiniciamos la reproducción
                holder.videoView.start();
            }
        });


        if (!holder.videoView.isPlaying()) {
            Log.wtf("El video se dejo de cargar" , "False");
            holder.ProgressBar.setVisibility(View.VISIBLE);
            holder.RelativeLayout.setVisibility(View.VISIBLE);
        }


        holder.Button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                int exerciseId = exercise.getId();
                int exerciseGroup = exercise.getMuscularGroup();
                Log.d("Tamanio de la lista", "Tamanio: " + IdList.getInstance().size() + "");

                Log.d("MuscularGroup del click", "MuscularGroup: " + exerciseGroup + "");


                if (containsExerciseWithId(IdList.getInstance(), exerciseId)) {

                    Toast.makeText(view.getContext(), "El ejercicio ya fue agregado previamente", Toast.LENGTH_SHORT).show();


                } else if (exerciseGroup == 16) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    //center setTitle
                    TextView title = new TextView(view.getContext());
                    title.setText("Agregar ejercicio");
                    title.setPadding(10, 10, 10, 20);
                    title.setGravity(Gravity.CENTER);
                    title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    builder.setCustomTitle(title);


                    LinearLayout layout = new LinearLayout(view.getContext());
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.setGravity(Gravity.CENTER_HORIZONTAL);

                    //Agregar margen entre los elementos
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 16, 0, 16);

                    //Crear línea con ambos elementos
                    LinearLayout linearLayout = new LinearLayout(view.getContext());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

                    EditText seriesEditText = new EditText(view.getContext());
                    //center text
                    seriesEditText.setGravity(Gravity.CENTER);
                    seriesEditText.setId(R.id.edittext_series);
                    seriesEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    seriesEditText.setHint("Minutos");
                    //add text as 1
                    seriesEditText.setText("10");
                    seriesEditText.setLayoutParams(params);
                    linearLayout.addView(seriesEditText);

                    TextView textView = new TextView(view.getContext());
                    textView.setText(" Minutos");
                    //add start and end margins
                    textView.setPadding(16, 0, 16, 0);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    linearLayout.addView(textView);

                    layout.addView(linearLayout);
                    //poner background a layout
                    builder.setView(layout);

                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //if series or reps are empty send a error message
                            if (seriesEditText.getText().toString().isEmpty()) {
                                Toast.makeText(view.getContext(), "Por favor ingrese un tiempo", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            int tiempo = Integer.parseInt(seriesEditText.getText().toString());
                            int muscleId = exercise.getMuscularGroup();
                            String name = exercise.getName();


                            //add exercise to the list
                            listaDeIds.add(exerciseId);
                            listaDeGrupos.add(muscleId);


                            IdList.getInstance().add(new ExerciseSet(exerciseId, name, muscleId, tiempo));

                            if (IdList.getInstance().size()+1 > 8) {
                                //popUp
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setTitle("¡Cuidado!");
                                builder.setMessage("Puede que agregar a tu rutina mas de 8 ejercicios no sea la mejor idea. ");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //go to the next activity
                                    }
                                });
                            }

                            Toast.makeText(view.getContext(), "Ejercicio agregado a la lista", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("Cancelar", null);
                    builder.show();

                } else {

                    //si no está, se agrega
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());


                    //Establecer título del diálogo y centrarlo

                    final ExerciseSet[] exerciseSet = {new ExerciseSet(exerciseId, "", 0, 0, 0, null)};

                    //center setTitle
                    TextView title = new TextView(view.getContext());
                    title.setText("Agregar ejercicio");
                    title.setPadding(10, 10, 10, 20);
                    title.setGravity(Gravity.CENTER);
                    title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    builder.setCustomTitle(title);


                    LinearLayout layout = new LinearLayout(view.getContext());

                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.setGravity(Gravity.CENTER_HORIZONTAL);

                    //Agregar margen entre los elementos
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 16, 0, 16);

                    //Crear línea con ambos elementos
                    LinearLayout linearLayout = new LinearLayout(view.getContext());

                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

                    EditText seriesEditText = new EditText(view.getContext());
                    //center text
                    seriesEditText.setGravity(Gravity.CENTER);
                    seriesEditText.setId(R.id.edittext_series);
                    seriesEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    seriesEditText.setHint("Sets");
                    //add text as 1
                    seriesEditText.setText("4");
                    seriesEditText.setLayoutParams(params);
                    linearLayout.addView(seriesEditText);

                    TextView textView = new TextView(view.getContext());
                    textView.setText("X");
                    //add start and end margins
                    textView.setPadding(16, 0, 16, 0);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                    linearLayout.addView(textView);

                    EditText repsEditText = new EditText(view.getContext());
                    repsEditText.setId(R.id.edittext_repeticiones);
                    repsEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    //center text
                    repsEditText.setGravity(Gravity.CENTER);
                    repsEditText.setHint("Reps");
                    //add text as 1
                    repsEditText.setText("10");
                    repsEditText.setLayoutParams(params);
                    linearLayout.addView(repsEditText);

                    layout.addView(linearLayout);
                    builder.setView(layout);

                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //if series or reps are empty send a error message
                            if (seriesEditText.getText().toString().isEmpty() || repsEditText.getText().toString().isEmpty()) {
                                Toast.makeText(view.getContext(), "Por favor ingrese un número de series y repeticiones", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            int numSeries = Integer.parseInt(seriesEditText.getText().toString());
                            int numReps = Integer.parseInt(repsEditText.getText().toString());
                            int muscleId = exercise.getMuscularGroup();
                            String name = exercise.getName();
                            //image as a byte array
                            String imagen = exercise.getImage();


                            //add exercise to the list
                            listaDeIds.add(exerciseId);
                            listaDeGrupos.add(muscleId);




                            if (IdList.getInstance().size()+1 > 8) {
                                //popUp
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setTitle("¡Cuidado!");
                                builder.setMessage("Puede que agregar a tu rutina mas de 8 ejercicios no sea la mejor idea. ");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //go to the next activity
                                    }
                                });
                                builder.show();
                            }

                            //if series or reps are more than 20 send a error message
                            if ( numSeries > 5) {
                                //popUp
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setTitle("¡Cuidado!");
                                builder.setMessage("Puede que agregar a tu rutina mas de 5 series no sea la mejor idea.");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //go to the next activity
                                    }
                                });
                                builder.show();
                            }

                            if ( numReps > 25) {
                                //popUp
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setTitle("¡Cuidado!");
                                builder.setMessage("Puede que agregar a tu rutina mas de 25 repeticiones no sea la mejor idea.");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //go to the next activity
                                    }
                                });
                                builder.show();
                            }


                            exerciseSet[0] = new ExerciseSet(exerciseId, name, numSeries, numReps, muscleId, imagen);
                            Log.d("Ejercicio que se guarda en el objeto", exerciseId + " " + numSeries + " " + numReps);
                            IdList.getInstance().add(exerciseSet[0]);
                            Toast.makeText(view.getContext(), "Ejercicio agregado a la lista", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("Cancelar", null);
                    
                    builder.show();
                }
            }
        });

    }


    public void checkForRepeatedValues(ArrayList<Integer> values, Context context) {

        // Creamos un HashMap para contar cuántas veces aparece cada valor
        HashMap<Integer, Integer> valueCounts = new HashMap<>();
        for (Integer value : values) {
            if (valueCounts.containsKey(value)) {
                valueCounts.put(value, valueCounts.get(value) + 1);
            } else {
                valueCounts.put(value, 1);
            }
        }

        // Buscamos los valores que se repiten más de 4 veces
        ArrayList<Integer> repeatedValues = new ArrayList<>();
        for (Integer value : valueCounts.keySet()) {
            if (valueCounts.get(value) > 4) {
                repeatedValues.add(value);
            }
        }

        // Si encontramos algún valor repetido, mostramos un Toast con los valores
        if (!repeatedValues.isEmpty()) {
            StringBuilder message = new StringBuilder();
            message.append("Los valores que se repiten más de 4 veces son: ");
            for (Integer value : repeatedValues) {
                message.append(value).append(", ");
            }
            message.delete(message.length() - 2, message.length()); // Quitamos la última coma y el espacio
            Toast.makeText(context, message.toString(), Toast.LENGTH_LONG).show();
        }
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
        final VideoView videoView;
         RelativeLayout RelativeLayout;
         ProgressBar ProgressBar;


        public EjerciciosViewHolder(@NonNull View itemView, EventOnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            Nombre = itemView.findViewById(R.id.CVET1);
            Enfoque = itemView.findViewById(R.id.CVET2);
            Button = itemView.findViewById(R.id.CVEBotonAgregar);
            videoView = itemView.findViewById(R.id.CVEGif);
            ProgressBar = itemView.findViewById(R.id.CVEProgress);
            RelativeLayout = itemView.findViewById(R.id.CVETapon);
        }

        @Override
        public void onClick(View view) {

        }
    }
}