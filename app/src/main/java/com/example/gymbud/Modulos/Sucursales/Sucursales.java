package com.example.gymbud.Modulos.Sucursales;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gymbud.Adaptadores.SucursalesAdaptador;
import com.example.gymbud.Entidades.Sucursal;
import com.example.gymbud.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        createLocationCallback();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                // Aquí puedes usar la ubicación actual
                Location location = locationResult.getLastLocation();
                Log.d("Ubicacion Callback", "Latitud: " + location.getLatitude() + " Longitud: " + location.getLongitude());
            }
        };
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar permisos si no están concedidos
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            return;
        }
        fusedLocationClient.requestLocationUpdates(createLocationRequest(), locationCallback, null /* Looper */);
    }

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // Intervalo de actualización de la ubicación
        locationRequest.setFastestInterval(5000); // Intervalo de actualización más rápido
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // Precisión alta
        return locationRequest;
    }

//
//    @Override
//    public void onPause() {
//        super.onPause();
//        stopLocationUpdates();
//    }
//
//    private void stopLocationUpdates() {
//        fusedLocationClient.removeLocationUpdates(locationCallback);
//    }

    RecyclerView recyclerView;
    ArrayList<Sucursal> SucursalesLista;
    Boolean permiso = false;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceSttate) {
        Context context = getContext();
        SucursalesLista = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerSucursales);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        //verificar si el permiso esta concedido o no y asignar valor a permiso
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            permiso = true;
        }

        Log.wtf("Permiso", permiso.toString());

        MostrarResultado(permiso);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sucursales, container, false);
    }

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final long UPDATE_INTERVAL = 5000; // 5 segundos
    private static final long FASTEST_INTERVAL = 60000; // 1 minuto


    private void MostrarResultado(Boolean permiso) {
        String url = "https://francoaldrete.com/GymBud/sucursal.php";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject Obj = (JSONObject) array.get(i);
//                                Log.d("ID", "" + Obj.getInt("id"));
                                // Agregar sucursales obtenidas a la lista
                                SucursalesLista.add(new Sucursal(
                                        Obj.getInt("id"),
                                        Obj.getInt("CurrentUsers"),
                                        Obj.getDouble("Rating"),
                                        Obj.getString("SubName"),
                                        Obj.getString("Location"),
                                        Obj.getString("ImageLink"),
                                        Obj.getString("Schedule"),
                                        Obj.getInt("ContactNumber"),
                                        Obj.getString("Latitud"),
                                        Obj.getString("Longitud")
                                ));
                                }

                            //este if es para verificar si el permiso esta concedido o no
                            if (permiso) {
                                LocationRequest locationRequest = LocationRequest.create();
                                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                                locationRequest.setInterval(UPDATE_INTERVAL);
                                locationRequest.setFastestInterval(FASTEST_INTERVAL);

                                if (getContext() == null) {
                                    Log.d("Context", "Context is null af");
                                    return;
                                }

                                // Verificar permisos
                                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                    LocationServices.getFusedLocationProviderClient(getContext()).requestLocationUpdates(locationRequest, new LocationCallback() {
                                    @Override
                                    public void onLocationResult(LocationResult locationResult) {
                                        Location location = locationResult.getLastLocation();

                                        // Obtener ubicación actual
                                        final double latActual = location.getLatitude();
                                        final double lonActual = location.getLongitude();

                                        // Comparador para ordenar la lista de sucursales
                                        Comparator<Sucursal> comparador = new Comparator<Sucursal>() {
                                            @Override
                                            public int compare(Sucursal sucursal1, Sucursal sucursal2) {
                                                double dist1 = distancia(latActual, lonActual, Double.parseDouble(sucursal1.getLatitud()), Double.parseDouble(sucursal1.getLongitud()));
                                                double dist2 = distancia(latActual, lonActual, Double.parseDouble(sucursal2.getLatitud()), Double.parseDouble(sucursal2.getLongitud()));
                                                return Double.compare(dist1, dist2);
                                            }
                                        };
                                        Collections.sort(SucursalesLista, comparador);
                                        SucursalesAdaptador adapter = new SucursalesAdaptador(SucursalesLista, new SucursalesAdaptador.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(int position) {
                                                Fragment fragment = new SucursalSeleccionada();
                                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                Bundle args = new Bundle();
                                                args.putString("Nombre", SucursalesLista.get(position).getSubName());
                                                Log.d("Nombre", SucursalesLista.get(position).getSubName());
                                                args.putString("Ubicacion", SucursalesLista.get(position).getLocation());
                                                Log.d("Ubicacion", SucursalesLista.get(position).getLocation());
                                                args.putString("Horario", SucursalesLista.get(position).getSchedule());
                                                Log.d("Horario", SucursalesLista.get(position).getSchedule());
                                                args.putString("Rating", "" + SucursalesLista.get(position).getRating());
                                                args.putInt("ID", SucursalesLista.get(position).getId());
                                                args.putString("Latitud", SucursalesLista.get(position).getLatitud());
                                                args.putString("Longitud", SucursalesLista.get(position).getLongitud());
                                                Log.d("ID", "" + SucursalesLista.get(position).getId());

                                                fragment.setArguments(args);
                                                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                                                transaction.replace(R.id.navFragmentContainer, fragment);
                                                transaction.addToBackStack(null);
                                                transaction.commit();
                                            }
                                        });
                                        recyclerView.setAdapter(adapter);
                                    }
                                }, Looper.getMainLooper());
                            }else {
//                                Toast.makeText(getContext(), "No se pudo obtener la ubicación actual", Toast.LENGTH_SHORT).show();

                                Log.d("Ubicación", "No se pudo obtener la ubicación actual");
                                // Mostrar un mensaje de error al usuario

                                SucursalesAdaptador adapter = new SucursalesAdaptador(SucursalesLista, new SucursalesAdaptador.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        Fragment fragment = new SucursalSeleccionada();
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                        Bundle args = new Bundle();
                                        args.putString("Nombre", SucursalesLista.get(position).getSubName());
                                        Log.d("Nombre", SucursalesLista.get(position).getSubName());
                                        args.putString("Ubicacion", SucursalesLista.get(position).getLocation());
                                        Log.d("Ubicacion", SucursalesLista.get(position).getLocation());
                                        args.putString("Horario", SucursalesLista.get(position).getSchedule());
                                        Log.d("Horario", SucursalesLista.get(position).getSchedule());
                                        args.putString("Rating", "" + SucursalesLista.get(position).getRating());
                                        args.putInt("ID", SucursalesLista.get(position).getId());
                                        Log.d("ID", "" + SucursalesLista.get(position).getId());

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
                Toast.makeText(getContext(), "Error al obtener las sucursales desde el servidor", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);

    }

    // Método para calcular la distancia entre dos puntos
    //Formula de Haversine
    public double distancia(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radio de la Tierra en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // Distancia en kilómetros
        return d;
    }
}