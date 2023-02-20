package com.example.gymbud;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gymbud.db.DbQuery;
import com.example.gymbud.db.Entidades.Stats;

import java.util.ArrayList;
import java.util.List;


import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link stats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class stats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public stats() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment stats.
     */
    // TODO: Rename and change types and number of parameters
    public static stats newInstance(String param1, String param2) {
        stats fragment = new stats();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view = inflater.inflate(R.layout.fragment_stats, container, false);

        fechas = (Spinner) view.findViewById(R.id.SpinnerProgre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.opciones, android.R.layout.simple_spinner_item);
        fechas.setAdapter(adapter);
        return view;
    }
    Spinner fechas;
Stats stats;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        DbQuery dbQuery = new DbQuery(context);
        TextView Carga,Reps,Tiempo,Reps2;
        ImageView Back = view.findViewById(R.id.botonback);
        ImageView agregar = view.findViewById(R.id.Agregar);
        Bundle args = getArguments();
        int id = args.getInt("id");//id del ejercicio seleccionado
        int ID = args.getInt("ID");//id del musculo seleccionado
        String musculo = args.getString("Musculo");//Nombre del musculo seleccionado
       
        Carga = view.findViewById(R.id.Carga);
        Reps = view.findViewById(R.id.Repeticiones);
        Reps2 = view.findViewById(R.id.Repeticiones2);
        Tiempo = view.findViewById(R.id.Tiempo);


        try {
            stats = dbQuery.verStats(id); //Esta es la query verstats con el id del ejercicio seleccionado
            //Este if ingresa los datos registrados de la bd en caso de que si haya algo, si no inserta 0 en todo en el id del ejercicio
            if(stats != null) {
                Carga.setText("" + stats.getWeight());
                Reps.setText("" + stats.getReps());
                Reps2.setText("" + stats.getReps2());
                Tiempo.setText("" + stats.getTime());
            }else{

                long query = dbQuery.StatsInsert(0,0,0,0,"0/0/0",id); //Es la query del insert
                Log.d("INSERT", "Se inserto");
            }
        }catch (Exception ex){
            Log.d("Error", "No hay stats que sacar");
        }



//En caso de que se presione el boton de regresar te envia a la pantalla anterior con id,ID y el nombre del musculo
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new registropeso();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                args.putInt("id",id);
                args.putInt("ID",ID);
                args.putString("Musculo",musculo);
                fragment.setArguments(args);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
//En caso de que se presione el boton de regresar te envia a la pantalla anterior con id,ID y el nombre del musculo
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new fragment_ejercicio_seleccionado();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                args.putInt("id",id);
                args.putInt("ID",ID);
                args.putString("Musculo",musculo);
                fragment.setArguments(args);
                Log.d("ID", id+"");
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //Todo esto es sobre la grafica

        LineChartView grafica; //Aqui declaramos el objeto de la grafica que es un Line Chart
        grafica = view.findViewById(R.id.Grafica); //Encontramos el objeto en el fragment
        String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
                "Oct", "Nov", "Dec"}; //Le ingresamos los datos del eje X
        int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18}; //Le ingresamos los datos del eje Y

        List yAxisValues = new ArrayList(); //Creamos una arraylist para los puntos en el eje Y
        List axisValues = new ArrayList(); //Creamos un arraylist  para los puntos en el eje X


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0")); //Le ponemos el color que queramos a la grafica

        for (int i = 0; i < axisData.length; i++) { //Este for itera todos los valores en el eje x en el arraylist y los ingresa a la grafica
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) { //Este for itera todos los valores en el eje y en el arraylist y los ingresa a la grafica
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Sales in millions");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        grafica.setLineChartData(data);
        Viewport viewport = new Viewport(grafica.getMaximumViewport());
        viewport.top = 110;
        grafica.setMaximumViewport(viewport);
        grafica.setCurrentViewport(viewport);
    }
}