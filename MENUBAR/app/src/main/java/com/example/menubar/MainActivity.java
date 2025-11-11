package com.example.menubar;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

        // Cambiar el texto del porcentaje al mover el SeekBar
        seekBarPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentajePropina = progress;
                textViewPorcentaje.setText("Porcentaje de propina: " + porcentajePropina + "%");
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Acción del botón
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotal();
            }
        });
    }

    private void calcularTotal() {
        String textoCuenta = editTextCuenta.getText().toString().trim();

        // Validar campo vacío
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

        // Validar valor mínimo
        if (cuenta <= 0) {
            textViewResultado.setText(" El valor de la cuenta debe ser mayor que 0.");
            return;
        }

        // Calcular total con propina si está marcado
        double total = cuenta;
        if (checkBoxPropina.isChecked()) {
            total += cuenta * (porcentajePropina / 100.0);
        }

        // Método de pago
        int idSeleccionado = radioGroupPago.getCheckedRadioButtonId();
        String metodoPago = "";
        if (idSeleccionado == R.id.radioEfectivo) {
            metodoPago = "Efectivo";
        } else if (idSeleccionado == R.id.radioTarjeta) {
            metodoPago = "Tarjeta";
        }

        // Calificación
        float estrellas = ratingBar.getRating();

        // Mostrar resultado
        String resultado = String.format("💰 Total a pagar: %.2f €\nMétodo de pago: %s\n⭐ Calificación: %.1f estrellas",
                total, metodoPago, estrellas);
        textViewResultado.setText(resultado);
    }
}
