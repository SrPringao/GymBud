package com.example.gymbud.Modulos.SeleccionEjercicios;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gymbud.Db.DbHelper;
import com.example.gymbud.Db.DbQuery;
import com.example.gymbud.Entidades.Stats;
import com.example.gymbud.FragmentContainer;
import com.example.gymbud.R;

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

    ArrayList<String> ListaStats;
    ArrayList<Stats> StatsLista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_stats, container, false);
        fechas = (Spinner) view.findViewById(R.id.SpinnerProgre);


        Bundle args = getArguments();
        int id = args.getInt("id");//id del ejercicio seleccionado
        FragmentContainer activity = (FragmentContainer) getActivity();
        Long UID = activity.UIDUSR();
        ConsultarDatos(id, UID);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.spinner_item, ListaStats);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        fechas.setPadding(10, 0, 10, 0);
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
        TextView Carga, Reps, Tiempo, Reps2, rm;
        ImageView Back = view.findViewById(R.id.botonback);
        Button agregar = view.findViewById(R.id.fsButtonAgregar);
        Bundle args = getArguments();
        int id = args.getInt("id");//id del ejercicio seleccionado
        int ID = args.getInt("ID");//id del musculo seleccionado
        String musculo = args.getString("Musculo");//Nombre del musculo seleccionado

        FragmentContainer activity = (FragmentContainer) getActivity();
        Long UID = activity.UIDUSR();

        fechas = (Spinner) view.findViewById(R.id.SpinnerProgre);
        Carga = view.findViewById(R.id.Carga);
        Reps = view.findViewById(R.id.Repeticiones);
        Reps2 = view.findViewById(R.id.Repeticiones2);
        Tiempo = view.findViewById(R.id.Tiempo);
        rm = view.findViewById(R.id.rm);

        fechas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                Carga.setText("" + StatsLista.get(position).getWeight() + " Lb");
                Reps.setText("" + StatsLista.get(position).getReps());
                Reps2.setText("" + StatsLista.get(position).getReps2());
                Tiempo.setText("" + StatsLista.get(position).getTime() + " mins");
                double RM = ((0.0333 * (StatsLista.get(position).getWeight())*0.453592) * StatsLista.get(position).getReps() + (StatsLista.get(position).getWeight())*0.453592);
                rm.setText(RM + " Lb");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        try {
            stats = dbQuery.verStats(id, UID); //Esta es la query verstats con el id del ejercicio seleccionado
            //Este if ingresa los datos registrados de la bd en caso de que si haya algo, si no inserta 0 en todo en el id del ejercicio
            if (stats != null) {
                Carga.setText("" + stats.getWeight());
                Reps.setText("" + stats.getReps());
                Reps2.setText("" + stats.getReps2());
                Tiempo.setText("" + stats.getTime());


            } else {
               /* FragmentContainer activity = (FragmentContainer) getActivity();
                String FechaAct = activity.FechaAct();
                long query = dbQuery.StatsInsert(0,0,0,0,FechaAct,id); //Es la query del insert
                Log.d("INSERT", "Se inserto");*/
            }
        } catch (Exception ex) {
            Log.d("Error", "No hay stats que sacar");
        }


//En caso de que se presione el boton de regresar te envia a la pantalla anterior con id,ID y el nombre del musculo
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new registropeso();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                args.putInt("id", id);
                args.putInt("ID", ID);
                args.putString("Musculo", musculo);
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
                args.putInt("id", id);
                args.putInt("ID", ID);
                args.putString("Musculo", musculo);
                fragment.setArguments(args);
                Log.d("ID", id + "");
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        //
        //Todo esto es sobre la grafica

        LineChartView grafica; //Aqui declaramos el objeto de la grafica que es un Line Chart
        grafica = view.findViewById(R.id.Grafica); //Encontramos el objeto en el fragment

        List<PointValue> values = new ArrayList<PointValue>();
        PointValue tempPointValue;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String[] Fechas = new String[StatsLista.size()];
        float[] axisData = new float[StatsLista.size()];
        for (int i = 0; i < StatsLista.size(); i++) {
            String FECHABD = StatsLista.get(i).getDate();
            Log.d("PESO", "" + StatsLista.get(i).getWeight());
            Fechas[i] = StatsLista.get(i).getDate();
            tempPointValue = new PointValue(i, StatsLista.get(i).getWeight());
            values.add(tempPointValue);

        }
        //Le ingresamos los datos del eje X

        int[] yAxisData = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150}; //Le ingresamos los datos del eje Y
        List<AxisValue> yAxisValues = new ArrayList(); //Creamos una arraylist para los puntos en el eje Y
        List<AxisValue> axisValues = new ArrayList(); //Creamos un arraylist  para los puntos en el eje X
        AxisValue tempAxisValue;

        Line line = new Line(values).setColor(Color.parseColor("#E1C675")).setHasLabels(true).setCubic(false); //Le ponemos el color que queramos a la grafica


        for (int i = 0; i < axisData.length; i++) { //Este for itera todos los valores en el eje x en el arraylist y los ingresa a la grafica
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setLabel(Fechas[i]);
            axisValues.add(tempAxisValue);
        }
        for (int i = 0; i < 15; i++) { //Este for itera todos los valores en el eje y en el arraylist y los ingresa a la grafica
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setValue(yAxisData[i]);
            yAxisValues.add(tempAxisValue);
        }

        List lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis(axisValues);
        axis.setTextSize(10);
        axis.setName("Fecha");
        axis.setTextColor(Color.parseColor("#E1C675"));
        data.setValueLabelTextSize(10);
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis(yAxisValues);
        yAxis.setTextSize(10);
        yAxis.setName("Peso");
        yAxis.setTextColor(Color.parseColor("#FCFCFC"));
        data.setValueLabelTextSize(10);
        data.setAxisYLeft(yAxis);

        grafica.setLineChartData(data);
        Viewport viewport = new Viewport(grafica.getMaximumViewport());
        viewport.top = 110;
        grafica.setMaximumViewport(viewport);
        grafica.setCurrentViewport(viewport);
    }

    //Funcion para recuperar la info de la base de datos e insertarla al spinner
    private void ConsultarDatos(int id, Long usr) {
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Stats stats = null;
        StatsLista = new ArrayList<Stats>();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + DbQuery.TABLE_STATS + " WHERE IdEjercicio =" + id + " AND IdUsr= " + usr, null);

        while (cursor.moveToNext()) {
            stats = new Stats();
            stats.setID_Stats(cursor.getInt(0));
            stats.setWeight(cursor.getInt(1));
            stats.setReps(cursor.getInt(2));
            stats.setReps2(cursor.getInt(3));
            stats.setTime(cursor.getFloat(4));
            stats.setDate(cursor.getString(5));
            stats.setIdEjercicio(cursor.getInt(6));
            StatsLista.add(stats);
        }
        ObtenerLista();
    }

    private void ObtenerLista() {
        ListaStats = new ArrayList<String>();
        for (int i = 0; i < StatsLista.size(); i++) {
            ListaStats.add(StatsLista.get(i).getDate());
        }
    }
}