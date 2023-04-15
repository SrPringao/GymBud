package com.example.gymbud.Modulos.Sucursales;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.gymbud.Adaptadores.SucursalesAdaptador;
import com.example.gymbud.Db.DbHelper;
import com.example.gymbud.Entidades.Sucursal;
import com.example.gymbud.FragmentContainer;
import com.example.gymbud.Modulos.CreacionDeRutinas.RutinasPersonalizadas.Tiendita;
import com.example.gymbud.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SucursalSeleccionada#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SucursalSeleccionada extends Fragment implements OnMapReadyCallback //implements OnMapReadyCallback
 {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SucursalSeleccionada() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SucursalSeleccionada.
     */
    // TODO: Rename and change types and number of parameters
    public static SucursalSeleccionada newInstance(String param1, String param2) {
        SucursalSeleccionada fragment = new SucursalSeleccionada();
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

        return inflater.inflate(R.layout.fragment_sucursal_seleccionada, container,false);

    }

    TextView Sucursal,Personas,Ubicacion,TextoRating;
    public int[] imagenes(String sucursal){
        int [] mImages = new int[]{};
        switch (sucursal){
            case "La Calma":
                mImages = new int[]{
                        R.drawable.lacalma1,R.drawable.lacalma2,R.drawable.lacalma3,R.drawable.lacalma4
                };
                break;
            case "Javier Mina":
                mImages = new int[]{
                        R.drawable.javiermina1,R.drawable.javiermina2,R.drawable.javiermina3,R.drawable.javiermina4
                };
                break;
            case "Mariano Otero":
                mImages = new int[]{
                        R.drawable.marianootero1,R.drawable.marianootero2,R.drawable.marianootero3,R.drawable.marianootero4
                };
                break;
            case "Clouthier":
                mImages = new int[]{
                        R.drawable.clouthier1,R.drawable.clouthier2,R.drawable.clouthier3,R.drawable.clouthier4
                };
                break;
            case "Belisario":
                mImages = new int[]{
                       R.drawable.belisario1,R.drawable.belisario2,R.drawable.belisario3,R.drawable.belisario4
                };
                break;
            case "Chapalita":
                mImages = new int[]{
                        R.drawable.chapalita1,R.drawable.chapalita2,R.drawable.chapalita3,R.drawable.chapalita4
                };
                break;
                case "Lázaro Cárdenas":
                mImages = new int[]{
                        R.drawable.lazarocardenas1,R.drawable.lazarocardenas2,R.drawable.lazarocardenas3,R.drawable.lazarocardenas4
                };
        }
        return mImages;
    }
    
     int[] Imagenes = {};
    ImageView botonBack,imagen1,imagen2,imagen3,imagen4,imagen5,imagen6;
    RatingBar ratingBarAvg, ratingBarMaquinas, ratingBarStaff, ratingBarVestidores;
    GoogleMap mMap;
    String calificacion1,calificacion2,calificacion3;
    Button botonCalificacion;

    String sucursal;
    String Ubi;

    int id;


     public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //id de los elementos de la vista
        botonBack = view.findViewById(R.id.botonbackselec);
        ratingBarAvg = view.findViewById(R.id.ssRatingBarAvg);
        ratingBarMaquinas = view.findViewById(R.id.ssRatingBarMaquinas);
        ratingBarStaff = view.findViewById(R.id.ssRatingBarStaff);
        ratingBarVestidores = view.findViewById(R.id.ssRatingBarVestidores);
        Sucursal = (TextView) view.findViewById(R.id.TituloSucursales);
        Ubicacion = (TextView) view.findViewById(R.id.ssUbicacionSucursales);
        CarouselView carouselView = view.findViewById(R.id.carouselView);
        botonCalificacion = view.findViewById(R.id.ssBotonCalificacion);
        TextoRating = view.findViewById(R.id.ssTvratingValue);

        //obtener los datos de la sucursal seleccionada
         Extras(view);
         Bundle mbundle = getArguments();
         Imagenes = imagenes(mbundle.getString("Nombre"));
         sucursal= mbundle.getString("Nombre");
         Ubi= mbundle.getString("Ubicacion");
         String Rating= mbundle.getString("Rating");
         String Horario= mbundle.getString("Horario");
         id = mbundle.getInt("ID");

         FragmentContainer activity = (FragmentContainer) getActivity();
         int UID = activity.UIDUSR();
         Log.d("id recibida", String.valueOf(id));



         //asignar los datos a los elementos de la vista
         Sucursal.setText(sucursal);
         Ubicacion.setText(Ubi);

         Rating(id);
         carouselView.setImageListener(imageListener);
         carouselView.setPageCount(Imagenes.length);


        botonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Sucursales();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        botonCalificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Request to server add values to database with php
                calificacion1 = String.valueOf(ratingBarMaquinas.getRating());
                calificacion2 = String.valueOf(ratingBarStaff.getRating());
                calificacion3 = String.valueOf(ratingBarVestidores.getRating());

                Toast.makeText(getContext(), "Calificación enviada", Toast.LENGTH_SHORT).show();
                Log.d("Calificacion",calificacion1);
                Log.d("Calificacion",calificacion2);
                Log.d("Calificacion",calificacion3);

                //enviar calificaciones a la base de datos
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://francoaldrete.com/GymBud/rating.php?uid="+UID+"&sucursal="+id+"&cal1="+calificacion1+"&cal2="+calificacion2+"&cal3="+calificacion3,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                                Log.d("Response",response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                                Log.d("Error",error.toString());
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("uid",String.valueOf(UID));
                        params.put("sucursal",String.valueOf(id));
                        params.put("calificacion1",calificacion1);
                        params.put("calificacion2",calificacion2);
                        params.put("calificacion3",calificacion3);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(stringRequest);


                Fragment fragment = new SucursalSeleccionada();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putString("Nombre", sucursal);
                args.putString("Ubicacion",Ubi);
                args.putInt("ID",id);


                fragment.setArguments(args);
                transaction.setCustomAnimations(R.anim.pop_in, R.anim.pop_out);
                transaction.replace(R.id.navFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        personas(view);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


     ImageListener imageListener = new ImageListener() {
         @Override
         public void setImageForPosition(int position, ImageView imageView) {
             imageView.setImageResource(Imagenes[position]);
         }
     };
     @Override
     public void onMapReady(GoogleMap googleMap) {
         Bundle mbundle = getArguments();
         String sucursal=mbundle.getString("Nombre","NO DATA");

         double Lat=20.637847141785972
                 ,Long=-103.41884252155718;
         switch(sucursal){
             case "La Calma":
                 Lat=20.637847141785972;
                 Long=-103.41884252155718;
                 break;
             case "Javier Mina":
                 Lat=20.667294198684644;
                 Long=-103.31336553875106;
                 break;
             case "Mariano Otero":
                 Lat=20.632611820270494;
                 Long=-103.4253400851146;
                 break;
             case "Clouthier":
                 Lat=20.671056271119884;
                 Long=-103.41746674272368;
                 break;
             case "Belisario":
                 Lat=20.69425458524266;
                 Long=-103.32276057242636;
                 break;
             case "Chapalita":
                 Lat=20.664563545261345;
                 Long=-103.4106651913678;
                 break;
             case "Lázaro Cárdenas":
                 Lat=20.669517605369627;
                 Long=-103.40420645405732;
                 break;

         }
         mMap = googleMap;
         LatLng Gym = new LatLng(Lat, Long);
         mMap.addMarker(new MarkerOptions()
                 .position(Gym)
                 .title("Gym")
                 .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

         mMap.moveCamera(CameraUpdateFactory.newLatLng(Gym));
     }
     public  void personas(View view){
         Bundle mbundle = getArguments();
         String sucursal=mbundle.getString("Nombre","NO DATA");
         int NumSucursal=1;
         switch(sucursal){
             case "La Calma":
                 NumSucursal=1;
                 break;
             case "Javier Mina":
                 NumSucursal=2;
                 break;
             case "Mariano Otero":
                 NumSucursal=3;
                 break;
             case "Clouthier":
                 NumSucursal=4;
                 break;
             case "Belisario":
                 NumSucursal=5;
                 break;
             case "Chapalita":
                 NumSucursal=5;
                 break;
             case "Lázaro Cárdenas":
                 NumSucursal=6;
                 break;

         }
         String url = "https://francoaldrete.com/GymBud/count.php?sucursal=";
         url += NumSucursal;
         StringRequest sRequest = new StringRequest(Request.Method.POST, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String respuesta) {
                         Personas = view.findViewById(R.id.Personas);
                         Personas.setText(respuesta);
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Log.d("FALLO",""+error);
                     }
                 });
         RequestQueue lanzarPeticion = Volley.newRequestQueue(getContext());
         lanzarPeticion.add(sRequest);
         lanzarPeticion.start();
     }
     public void Extras(View view){

         Bundle mbundle = getArguments();
         String sucursal=mbundle.getString("Nombre","NO DATA");
         int NumSucursal=1;
         switch(sucursal){
             case "La Calma":
                 NumSucursal=1;
                 break;
             case "Javier Mina":
                 NumSucursal=2;
                 break;
             case "Mariano Otero":
                 NumSucursal=3;
                 break;
             case "Clouthier":
                 NumSucursal=4;
                 break;
             case "Belisario":
                 NumSucursal=5;
                 break;
             case "Chapalita":
                 NumSucursal=5;
                 break;
             case "Lázaro Cárdenas":
                 NumSucursal=6;
                 break;

         }

         String url = "https://francoaldrete.com/GymBud/extra.php?sucursal=";
         url += NumSucursal;
         Log.d("Extras", "URL: " + url);
         JsonArrayRequest extra = new JsonArrayRequest(Request.Method.GET, url, null,
                 new Response.Listener<JSONArray>() {
                     @Override
                     public void onResponse(JSONArray response) {
                         try {
                             for (int i = 0; i < response.length(); i++) {
                                 JSONObject jsonObject = response.getJSONObject(i);
                                 String serviceId = jsonObject.getString("ServiceId");
                                 switch (serviceId){
                                     case "1":
                                         switch (i){
                                             case 0:
                                                 imagen1 = view.findViewById(R.id.Extra1);
                                                 imagen1.setImageResource(R.drawable.ic_crossfit);
                                                 imagen1.setVisibility(View.VISIBLE);
                                                 break;
                                             case 1:
                                                 imagen2 = view.findViewById(R.id.Extra2);
                                                 imagen2.setImageResource(R.drawable.ic_crossfit);
                                                 imagen2.setVisibility(View.VISIBLE);
                                                 break;
                                             case 2:
                                                 imagen3 = view.findViewById(R.id.Extra3);
                                                 imagen3.setImageResource(R.drawable.ic_crossfit);
                                                 imagen3.setVisibility(View.VISIBLE);
                                                 break;
                                             case 3:
                                                 imagen4 = view.findViewById(R.id.Extra4);
                                                 imagen4.setImageResource(R.drawable.ic_crossfit);
                                                 imagen4.setVisibility(View.VISIBLE);
                                                 break;
                                             case 4:
                                                 imagen5 = view.findViewById(R.id.Extra5);
                                                 imagen5.setImageResource(R.drawable.ic_crossfit);
                                                 imagen5.setVisibility(View.VISIBLE);
                                                 break;
                                             case 5:
                                                 imagen6 = view.findViewById(R.id.Extra6);
                                                 imagen6.setImageResource(R.drawable.ic_crossfit);
                                                 imagen6.setVisibility(View.VISIBLE);
                                                 break;
                                         }
                                         break;
                                     case "2":
                                         switch (i){
                                             case 0:
                                                 imagen1 = view.findViewById(R.id.Extra1);
                                                 imagen1.setImageResource(R.drawable.ic_telas);
                                                 imagen1.setVisibility(View.VISIBLE);
                                                 break;
                                             case 1:
                                                 imagen2 = view.findViewById(R.id.Extra2);
                                                 imagen2.setImageResource(R.drawable.ic_telas);
                                                 imagen2.setVisibility(View.VISIBLE);
                                                 break;
                                             case 2:
                                                 imagen3 = view.findViewById(R.id.Extra3);
                                                 imagen3.setImageResource(R.drawable.ic_telas);
                                                 imagen3.setVisibility(View.VISIBLE);
                                                 break;
                                             case 3:
                                                 imagen4 = view.findViewById(R.id.Extra4);
                                                 imagen4.setImageResource(R.drawable.ic_telas);
                                                 imagen4.setVisibility(View.VISIBLE);
                                                 break;
                                             case 4:
                                                 imagen5 = view.findViewById(R.id.Extra5);
                                                 imagen5.setImageResource(R.drawable.ic_telas);
                                                 imagen5.setVisibility(View.VISIBLE);
                                                 break;
                                             case 5:
                                                 imagen6 = view.findViewById(R.id.Extra6);
                                                 imagen6.setImageResource(R.drawable.ic_telas);
                                                 imagen6.setVisibility(View.VISIBLE);
                                                 break;
                                         }
                                         break;
                                     case "3":
                                         switch (i){
                                             case 0:
                                                 imagen1 = view.findViewById(R.id.Extra1);
                                                 imagen1.setImageResource(R.drawable.ic_alberca2);
                                                 imagen1.setVisibility(View.VISIBLE);
                                                 break;
                                             case 1:
                                                 imagen2 = view.findViewById(R.id.Extra2);
                                                 imagen2.setImageResource(R.drawable.ic_alberca2);
                                                 imagen2.setVisibility(View.VISIBLE);
                                                 break;
                                             case 2:
                                                 imagen3 = view.findViewById(R.id.Extra3);
                                                 imagen3.setImageResource(R.drawable.ic_alberca2);
                                                 imagen3.setVisibility(View.VISIBLE);
                                                 break;
                                             case 3:
                                                 imagen4 = view.findViewById(R.id.Extra4);
                                                 imagen4.setImageResource(R.drawable.ic_alberca2);
                                                 imagen4.setVisibility(View.VISIBLE);
                                                 break;
                                             case 4:
                                                 imagen5 = view.findViewById(R.id.Extra5);
                                                 imagen5.setImageResource(R.drawable.ic_alberca2);
                                                 imagen5.setVisibility(View.VISIBLE);
                                                 break;
                                             case 5:
                                                 imagen6 = view.findViewById(R.id.Extra6);
                                                 imagen6.setImageResource(R.drawable.ic_alberca2);
                                                 imagen6.setVisibility(View.VISIBLE);
                                                 break;
                                         }
                                         break;
                                     case "4":
                                         switch (i){
                                             case 0:
                                                 imagen1 = view.findViewById(R.id.Extra1);
                                                 imagen1.setImageResource(R.drawable.ic_box1);
                                                 imagen1.setVisibility(View.VISIBLE);
                                                 break;
                                             case 1:
                                                 imagen2 = view.findViewById(R.id.Extra2);
                                                 imagen2.setImageResource(R.drawable.ic_box1);
                                                 imagen2.setVisibility(View.VISIBLE);
                                                 break;
                                             case 2:
                                                 imagen3 = view.findViewById(R.id.Extra3);
                                                 imagen3.setImageResource(R.drawable.ic_box1);
                                                 imagen3.setVisibility(View.VISIBLE);
                                                 break;
                                             case 3:
                                                 imagen4 = view.findViewById(R.id.Extra4);
                                                 imagen4.setImageResource(R.drawable.ic_box1);
                                                 imagen4.setVisibility(View.VISIBLE);
                                                 break;
                                             case 4:
                                                 imagen5 = view.findViewById(R.id.Extra5);
                                                 imagen5.setImageResource(R.drawable.ic_box1);
                                                 imagen5.setVisibility(View.VISIBLE);
                                                 break;
                                             case 5:
                                                 imagen6 = view.findViewById(R.id.Extra6);
                                                 imagen6.setImageResource(R.drawable.ic_box1);
                                                 imagen6.setVisibility(View.VISIBLE);
                                                 break;
                                         }
                                         break;
                                     case "5":
                                         switch (i){
                                             case 0:
                                                 imagen1 = view.findViewById(R.id.Extra1);
                                                 imagen1.setImageResource(R.drawable.ic_sauna);
                                                 imagen1.setVisibility(View.VISIBLE);
                                                 break;
                                             case 1:
                                                 imagen2 = view.findViewById(R.id.Extra2);
                                                 imagen2.setImageResource(R.drawable.ic_sauna);
                                                 imagen2.setVisibility(View.VISIBLE);
                                                 break;
                                             case 2:
                                                 imagen3 = view.findViewById(R.id.Extra3);
                                                 imagen3.setImageResource(R.drawable.ic_sauna);
                                                 imagen3.setVisibility(View.VISIBLE);
                                                 break;
                                             case 3:
                                                 imagen4 = view.findViewById(R.id.Extra4);
                                                 imagen4.setImageResource(R.drawable.ic_sauna);
                                                 imagen4.setVisibility(View.VISIBLE);
                                                 break;
                                             case 4:
                                                 imagen5 = view.findViewById(R.id.Extra5);
                                                 imagen5.setImageResource(R.drawable.ic_sauna);
                                                 imagen5.setVisibility(View.VISIBLE);
                                                 break;
                                             case 5:
                                                 imagen6 = view.findViewById(R.id.Extra6);
                                                 imagen6.setImageResource(R.drawable.ic_sauna);
                                                 imagen6.setVisibility(View.VISIBLE);
                                                 break;
                                         }
                                         break;
                                     case "6":
                                         switch (i){
                                             case 0:
                                                 imagen1 = view.findViewById(R.id.Extra1);
                                                 imagen1.setImageResource(R.drawable.ic_masaje2);
                                                 imagen1.setVisibility(View.VISIBLE);
                                                 break;
                                             case 1:
                                                 imagen2 = view.findViewById(R.id.Extra2);
                                                 imagen2.setImageResource(R.drawable.ic_masaje2);
                                                 imagen2.setVisibility(View.VISIBLE);
                                                 break;
                                             case 2:
                                                 imagen3 = view.findViewById(R.id.Extra3);
                                                 imagen3.setImageResource(R.drawable.ic_masaje2);
                                                 imagen3.setVisibility(View.VISIBLE);
                                                 break;
                                             case 3:
                                                 imagen4 = view.findViewById(R.id.Extra4);
                                                 imagen4.setImageResource(R.drawable.ic_masaje2);
                                                 imagen4.setVisibility(View.VISIBLE);
                                                 break;
                                             case 4:
                                                 imagen5 = view.findViewById(R.id.Extra5);
                                                 imagen5.setImageResource(R.drawable.ic_masaje2);
                                                 imagen5.setVisibility(View.VISIBLE);
                                                 break;
                                             case 5:
                                                 imagen6 = view.findViewById(R.id.Extra6);
                                                 imagen6.setImageResource(R.drawable.ic_masaje2);
                                                 imagen6.setVisibility(View.VISIBLE);
                                                 break;
                                         }
                                         break;
                                 }
                                 Log.d("Extras", "ServiceId: " + serviceId);
                             }
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Log.d("Extras", "Error: " + error);
                     }
                 });
         RequestQueue lanzarPeticion = Volley.newRequestQueue(getContext());
         lanzarPeticion.add(extra);
         lanzarPeticion.start();
     }


     private void Rating(int id){
         Log.d("Actualizacion", "Se entro a la funcion de rating");
         RequestQueue queue = Volley.newRequestQueue(getContext());
         String url = "https://francoaldrete.com/GymBud/sucursal.php";

         Sucursal sucursal = new Sucursal();

         StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         try {
                             JSONArray array = new JSONArray(response);
                             sucursal.setRating(Float.parseFloat(array.getJSONObject(id-1).getString("Rating")));

                             String rating = String.format("%.1f", Float.parseFloat(String.valueOf(sucursal.getRating())));
                             TextoRating.setText(rating);
                             ratingBarAvg.setRating(Float.parseFloat(rating));
                             Log.d("Sucursal", "Rating Macabro con id: "+ id +" Rating:" + rating);

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
