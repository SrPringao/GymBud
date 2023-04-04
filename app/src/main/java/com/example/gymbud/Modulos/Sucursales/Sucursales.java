package com.example.gymbud.Modulos.Sucursales;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gymbud.Adaptadores.SucursalesAdaptador;
import com.example.gymbud.Entidades.Sucursal;
import com.example.gymbud.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sucursales#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sucursales extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sucursales() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sucursal.
     */
    // TODO: Rename and change types and number of parameters
    public static Sucursales newInstance(String param1, String param2) {
        Sucursales fragment = new Sucursales();
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
    RecyclerView recyclerView;
    ArrayList<Sucursal> SucursalesLista;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceSttate){
        Context context = getContext();
        SucursalesLista = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerSucursales);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        MostrarResultado();


        SucursalesAdaptador adaptador = new SucursalesAdaptador(SucursalesLista, new SucursalesAdaptador.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sucursales, container, false);
    }

    private void MostrarResultado(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://francoaldrete.com/GymBud/sucursal.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                                for (int i = 0;i<array.length();i++) {
                                    JSONObject Obj = (JSONObject) array.get(i);
                                    Log.d("ID",""+Obj.getInt("id"));
                                    SucursalesLista.add(new Sucursal(
                                            Obj.getInt("id"),
                                            Obj.getInt("CurrentUsers"),
                                            Obj.getInt("Rating"),
                                            Obj.getString("SubName"),
                                            Obj.getString("Location"),
                                            Obj.getString("ImageLink"),
                                            Obj.getString("Schedule"),
                                            Obj.getInt("ContactNumber")
                                    ));
                                    SucursalesAdaptador adapter = new SucursalesAdaptador(SucursalesLista, new SucursalesAdaptador.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            Fragment fragment = new SucursalSeleccionada();
                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            Bundle args = new Bundle();
                                            args.putString("Nombre", SucursalesLista.get(position).getSubName());
                                            Log.d("Nombre", SucursalesLista.get(position).getSubName());
                                            args.putInt("ID", SucursalesLista.get(position).getId());
                                            Log.d("ID", ""+SucursalesLista.get(position).getId());
                                            fragment.setArguments(args);
                                            transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                                            transaction.replace(R.id.navFragmentContainer, fragment);
                                            transaction.addToBackStack(null);
                                            transaction.commit();
                                        }
                                    });
                                    recyclerView.setAdapter(adapter);
                                }
                        }catch (JSONException e){
                            Log.d("NO SIRVIO", "NO SIRVIO");
                            Log.d("JSONException", ""+e);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);

    }
}