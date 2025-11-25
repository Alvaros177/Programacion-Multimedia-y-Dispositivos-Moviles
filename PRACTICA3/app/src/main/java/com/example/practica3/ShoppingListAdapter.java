package com.example.practica3;
import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> lista;

    public ShoppingListAdapter(Context context, ArrayList<Item> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() { return lista.size(); }

    @Override
    public Object getItem(int position) { return lista.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_shooping, parent, false);
        }


        Item item = lista.get(position);

        ImageView img = convertView.findViewById(R.id.imgItem);
        TextView nombre = convertView.findViewById(R.id.txtNombre);
        TextView cantidad = convertView.findViewById(R.id.txtCantidad);
        Button btnEliminar = convertView.findViewById(R.id.btnEliminar);

        img.setImageResource(item.getImagenResId());
        nombre.setText(item.getNombre());
        cantidad.setText("Cantidad: " + item.getCantidad());

        btnEliminar.setOnClickListener(v -> {
            lista.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
