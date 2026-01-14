package com.example.menubar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.*;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCuenta;
    private CheckBox checkBoxPropina;
    private SeekBar seekBarPropina;
    private TextView textViewPorcentaje, textViewResultado;
    private RadioGroup radioGroupPago;
    private RatingBar ratingBar;
    private Button buttonCalcular;
    private AutoCompleteTextView autoCamareros;

    private int porcentajePropina = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        editTextCuenta = findViewById(R.id.editTextCuenta);
        checkBoxPropina = findViewById(R.id.checkBoxPropina);
        seekBarPropina = findViewById(R.id.seekBarPropina);
        textViewPorcentaje = findViewById(R.id.textViewPorcentaje);
        textViewResultado = findViewById(R.id.textViewResultado);
        radioGroupPago = findViewById(R.id.radioGroupPago);
        ratingBar = findViewById(R.id.ratingBar);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        autoCamareros = findViewById(R.id.autoCamareros);


        String[] camareros = {"Carlos", "Carla", "Carmen", "Daniel", "Dario", "David"};

        ArrayAdapter<String> adapterCamareros =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, camareros);

        autoCamareros.setAdapter(adapterCamareros);
        autoCamareros.setThreshold(3);


        seekBarPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentajePropina = progress;
                textViewPorcentaje.setText("Porcentaje de propina: " + porcentajePropina + "%");
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotal();
            }
        });
    }

    private void calcularTotal() {
        String textoCuenta = editTextCuenta.getText().toString().trim();

        if (textoCuenta.isEmpty()) {
            textViewResultado.setText(" Falta introducir el valor de la cuenta.");
            return;
        }

        double cuenta;
        try {
            cuenta = Double.parseDouble(textoCuenta);
        } catch (NumberFormatException e) {
            textViewResultado.setText(" Formato no válido. Introduce solo números.");
            return;
        }

        if (cuenta <= 0) {
            textViewResultado.setText(" El valor de la cuenta debe ser mayor que 0.");
            return;
        }

        double total = cuenta;
        if (checkBoxPropina.isChecked()) {
            total += cuenta * (porcentajePropina / 100.0);
        }

        int idSeleccionado = radioGroupPago.getCheckedRadioButtonId();
        String metodoPago = "";
        if (idSeleccionado == R.id.radioEfectivo) {
            metodoPago = "Efectivo";
        } else if (idSeleccionado == R.id.radioTarjeta) {
            metodoPago = "Tarjeta";
        }

        float estrellas = ratingBar.getRating();


        String camarero = autoCamareros.getText().toString();
        if (camarero.isEmpty()) {
            camarero = "No especificado";
        }

        String resultado = String.format(
                "Total a pagar: %.2f €\nMétodo de pago: %s\nCalificación: %.1f estrellas\nCamarero: %s",
                total, metodoPago, estrellas, camarero
        );

        textViewResultado.setText(resultado);
    }
}
