package com.example.practica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica.R;

import java.util.List;

public class MiAdapter extends RecyclerView.Adapter<MiAdapter.ViewHolder> {

    private List<String> datos;

    public MiAdapter(List<String> datos) {
        this.datos = datos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textoItem;
        public ViewHolder(View itemView) {
            super(itemView);
            textoItem = itemView.findViewById(R.id.textoItem);
        }
    }

    @NonNull
    @Override
    public MiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdapter.ViewHolder holder, int position) {
        holder.textoItem.setText(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}

