package com.example.gymbud.Adaptadores;

import static com.example.gymbud.Entidades.IdList.containsExerciseWithId;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbud.R;
import com.example.gymbud.Entidades.ExerciseSet;
import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.IdList;

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


                if (containsExerciseWithId(IdList.getInstance(), exerciseId)){
                    Toast.makeText(view.getContext(), "El ejercicio ya fue agregado previamente", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    //si no está, se agrega
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());


                    //Establecer título del diálogo y centrarlo

                    final ExerciseSet[] exerciseSet = {new ExerciseSet(exerciseId, 0, 0)};

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
                    seriesEditText.setText("");
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
                    repsEditText.setText("");
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


                            //add exercise to the list
                            listaDeIds.add(exerciseId);

                            exerciseSet[0] = new ExerciseSet(exerciseId, numSeries, numReps);
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