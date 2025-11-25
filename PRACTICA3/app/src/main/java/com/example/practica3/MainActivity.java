package com.example.practica3;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Item> lista;
    ShoppingListAdapter adapter;
    int[] imagenes = {
            R.drawable.frutas,
            R.drawable.verduras,
            R.drawable.pan,
            R.drawable.leche
    };

    int imagenSeleccionada = imagenes[0];
    int itemSeleccionadoContextMenu = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = new ArrayList<>();

        Spinner spinner = findViewById(R.id.spinnerImagenes);
        EditText edtNombre = findViewById(R.id.edtNombre);
        EditText edtCantidad = findViewById(R.id.edtCantidad);
        Button btnAgregar = findViewById(R.id.btnAgregar);
        ListView listView = findViewById(R.id.listView);

        adapter = new ShoppingListAdapter(this, lista);
        listView.setAdapter(adapter);

        ImageSpinnerAdapter spinnerAdapter =
                new ImageSpinnerAdapter(this, imagenes);

        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                imagenSeleccionada = imagenes[pos];
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnAgregar.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString();
            int cantidad = Integer.parseInt(edtCantidad.getText().toString());
            lista.add(new Item(nombre, cantidad, imagenSeleccionada));
            adapter.notifyDataSetChanged();
        });

        registerForContextMenu(listView);

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            itemSeleccionadoContextMenu = position;
            return false;
        });
    }

    // Crear menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Añadir");
        menu.add("Eliminar");
    }

    // Acciones del menú contextual
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals("Eliminar")) {
            lista.remove(itemSeleccionadoContextMenu);
            adapter.notifyDataSetChanged();
        }
        if(item.getTitle().equals("Añadir")) {
            // Añadir una copia
            Item it = lista.get(itemSeleccionadoContextMenu);
            lista.add(new Item(it.getNombre(), it.getCantidad(), it.getImagenResId()));
            adapter.notifyDataSetChanged();
        }
        return true;
    }
}




