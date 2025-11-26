package com.example.practica3;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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


        String[] nombresImagenes = {"Frutas", "Verduras", "Pan", "Leche"};
        ImageSpinnerAdapter spinnerAdapter =
                new ImageSpinnerAdapter(this, imagenes, nombresImagenes);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                imagenSeleccionada = imagenes[pos];


                String[] nombresImagenes = {"Frutas", "Verduras", "Pan", "Leche"};
                EditText edtNombre = findViewById(R.id.edtNombre);
                edtNombre.setText(nombresImagenes[pos]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAgregar.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString();
            String cantidadTxt = edtCantidad.getText().toString();

            if (!nombre.isEmpty() && !cantidadTxt.isEmpty()) {
                int cantidad = Integer.parseInt(cantidadTxt);
                lista.add(new Item(nombre, cantidad, imagenSeleccionada));
                adapter.notifyDataSetChanged();
            }
        });


        registerForContextMenu(listView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int index = info.position;

        if (item.getItemId() == R.id.menu_add) {

            Item original = lista.get(index);

            Item copia = new Item(original.getNombre(), original.getCantidad(), original.getImagenResId());
            lista.add(index + 1, copia);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Elemento duplicado", Toast.LENGTH_SHORT).show();
            return true;

        } else if (item.getItemId() == R.id.menu_delete) {

            lista.remove(index);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    }
