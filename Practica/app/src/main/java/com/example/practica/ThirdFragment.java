package com.example.practica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    private int contador = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflamos el layout
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        TextView textoContador = view.findViewById(R.id.textoContador);
        Button botonIncrementar = view.findViewById(R.id.botonIncrementar);

        // Acción del botón: incrementar contador
        botonIncrementar.setOnClickListener(v -> {
            contador++;
            textoContador.setText("Contador: " + contador);
        });

        return view;
    }
}
