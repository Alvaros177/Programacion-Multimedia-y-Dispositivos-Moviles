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

public class SecondFragment extends Fragment {

    private TextView tvResultado;
    private String operador = "";
    private double valor1 = Double.NaN;
    private double valor2;

    private Button  button0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        tvResultado = view.findViewById(R.id.tvResultado);

        // Botones
        int[] numeros = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

        int[] ops = {R.id.buttonSum, R.id.buttonRest, R.id.buttonMul, R.id.buttonDiv};

        for (int i = 0; i <= 9; i++) {
            Button b = view.findViewById(numeros[i]);
            final int num = i;
            b.setOnClickListener(v -> tvResultado.append(String.valueOf(num)));
        }

        // Operadores
        Button sum = view.findViewById(R.id.buttonSum);
        Button rest = view.findViewById(R.id.buttonRest);
        Button mul = view.findViewById(R.id.buttonMul);
        Button div = view.findViewById(R.id.buttonDiv);
        Button igual = view.findViewById(R.id.buttonIgual);
        Button clear = view.findViewById(R.id.buttonClear);

        View.OnClickListener opListener = v -> {
            if (!Double.isNaN(valor1)) calcular();
            operador = ((Button)v).getText().toString();
            valor1 = Double.parseDouble(tvResultado.getText().toString());
            tvResultado.setText("");
        };

        sum.setOnClickListener(opListener);
        rest.setOnClickListener(opListener);
        mul.setOnClickListener(opListener);
        div.setOnClickListener(opListener);

        igual.setOnClickListener(v -> calcular());
        clear.setOnClickListener(v -> {
            tvResultado.setText("");
            valor1 = Double.NaN;
            valor2 = Double.NaN;
            operador = "";
        });

        return view;
    }

    private void calcular() {
        if (tvResultado.getText().length() == 0) return;
        valor2 = Double.parseDouble(tvResultado.getText().toString());
        double res = 0;
        switch (operador) {
            case "+": res = valor1 + valor2; break;
            case "-": res = valor1 - valor2; break;
            case "*": res = valor1 * valor2; break;
            case "/":
                if (valor2 != 0) res = valor1 / valor2;
                else {
                    tvResultado.setText("Error");
                    return;
                }
                break;
        }
        tvResultado.setText(String.valueOf(res));
        valor1 = res;
        operador = "";
    }
}
