package com.example.practica;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {

    private TextInputLayout textInputLayout;
    private TextInputEditText editText;
    private Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        textInputLayout = view.findViewById(R.id.textInputLayout);
        editText = view.findViewById(R.id.editText);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (TextUtils.isEmpty(text)) {
                textInputLayout.setError("Este campo es obligatorio");
            } else {
                textInputLayout.setError(null);
                Snackbar.make(view, "Formulario enviado: " + text, Snackbar.LENGTH_LONG)
                        .setAction("OK", click -> {})
                        .show();
            }
        });

        return view;
    }
}