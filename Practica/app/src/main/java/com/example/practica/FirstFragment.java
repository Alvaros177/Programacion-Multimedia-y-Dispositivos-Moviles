package com.example.practica;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practica.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FirstFragment extends Fragment {

    private TextInputLayout tilNombre, tilEmail, tilPassword;
    private TextInputEditText etNombre, etEmail, etPassword;
    private Spinner spinnerTipo;
    private Button btnEnviar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        tilNombre = view.findViewById(R.id.tilNombre);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);

        etNombre = view.findViewById(R.id.etNombre);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        spinnerTipo = view.findViewById(R.id.spinnerTipo);
        btnEnviar = view.findViewById(R.id.btnEnviar);

        // Spinner
        String[] tipos = {"Usuario", "Administrador", "Invitado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                tipos
        );
        spinnerTipo.setAdapter(adapter);

        btnEnviar.setOnClickListener(v -> validarFormulario(view));

        return view;
    }

    private void validarFormulario(View view) {

        tilNombre.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);

        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        boolean valido = true;

        if (nombre.isEmpty()) {
            tilNombre.setError("El nombre es obligatorio");
            valido = false;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Email no válido");
            valido = false;
        }

        if (password.length() < 6) {
            tilPassword.setError("Mínimo 6 caracteres");
            valido = false;
        }

        if (valido) {
            Snackbar.make(view,
                            "Formulario enviado correctamente",
                            Snackbar.LENGTH_LONG)
                    .setAction("OK", v -> {})
                    .show();
        }
    }
}