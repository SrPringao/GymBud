package com.example.gymbud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SucursalSeleccionada#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SucursalSeleccionada extends Fragment implements OnMapReadyCallback //implements OnMapReadyCallback
 {
    MapView mapView;
    GoogleMap mMap;
    View vista;
    Bundle mBundle;

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

        return inflater.inflate(R.layout.fragment_sucursal_seleccionada, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // You can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Sucursal Seleccionada");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera}
    //La calma
    //20.637847141785972, -103.41884252155718
    //Javier Mina
    //20.667294198684644, -103.31336553875106
    //Mariano Otero
    //20.632611820270494, -103.4253400851146
    //Clouthier
    //20.671056271119884, -103.41746674272368
    //Belisario
    //20.69425458524266, -103.32276057242636
    //Chapalita
    //20.664563545261345, -103.4106651913678
    //Lazaro Cardenas
    //20.669517605369627, -103.40420645405732
        LatLng Gym = new LatLng(20.637847141785972, -103.41884252155718);
        mMap.addMarker(new MarkerOptions()
                .position(Gym)
                .title("Gym"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Gym));
    }
}