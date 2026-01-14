package com.example.optionsmenu;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView etiqueta = findViewById(R.id.textView);
        registerForContextMenu(etiqueta);

         MaterialToolbar toolbar = findViewById(R.id.topAppBar);
         setSupportActionBar(toolbar);

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

    }
@Override
public boolean onContextItemSelected (MenuItem item) {

    int id = item.getItemId();

    if (id == R.id.action_settings) {
        Toast.makeText(this, "Abrir Configuracion", Toast.LENGTH_SHORT).show();
        return true;
    } else if (id == R.id.action_help) {
        Toast.makeText(this, "Abrir Ayuda", Toast.LENGTH_SHORT).show();
        return true;
    } else if (id == R.id.exit) {
        Toast.makeText(this, "Le damos al boton de salir", Toast.LENGTH_SHORT).show();
        return true;
    }
    return super.onOptionsItemSelected(item);
}







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refrescar) {
            Toast.makeText(this, "Refrescando...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_ajustes) {
            Toast.makeText(this, "Ajustes...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_info) {
            Toast.makeText(this, "Informacion...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_acercade) {
            Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}