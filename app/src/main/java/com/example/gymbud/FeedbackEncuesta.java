package com.example.gymbud;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gymbud.Modulos.VerRutinas.VerRutinas;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackEncuesta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackEncuesta extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedbackEncuesta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackEncuesta.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackEncuesta newInstance(String param1, String param2) {
        FeedbackEncuesta fragment = new FeedbackEncuesta();
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
        return inflater.inflate(R.layout.fragment_feedback_encuesta, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageView img = (ImageView) getView().findViewById(R.id.FBotonback);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstFragment = new VerRutinas();
                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

                transaction.replace(R.id.navFragmentContainer, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageView imagen1 = (ImageView) getView().findViewById(R.id.Feed1);
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Recomendacion para mejorar tu experiencia durante el entrenamiento");
                builder.setMessage("Abubububuu");
                builder.setPositiveButton("Ok", (dialog, which) -> {
                     dialog.dismiss();

                });

                builder.show();
            }
        });

        ImageView imagen2 = (ImageView) getView().findViewById(R.id.Feed2);
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Recomendacion para mejorar tu experiencia durante el entrenamiento");
                builder.setMessage("Abubububuu");
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();

                });

                builder.show();
            }
        });

        ImageView imagen3 = (ImageView) getView().findViewById(R.id.Feed3);
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Recomendacion para mejorar tu experiencia durante el entrenamiento");
                builder.setMessage("Abubububuu");
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();

                });

                builder.show();
            }
        });


    }
}