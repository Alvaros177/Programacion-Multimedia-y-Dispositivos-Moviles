package com.example.practica3;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageSpinnerAdapter extends BaseAdapter {

    Context context;
    int[] imagenes;

    public ImageSpinnerAdapter(Context context, int[] imagenes) {
        this.context = context;
        this.imagenes = imagenes;
    }

    @Override
    public int getCount() {
        return imagenes.length;
    }

    @Override
    public Object getItem(int i) {
        return imagenes[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.spinner_item, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.imgSpinner);
        img.setImageResource(imagenes[i]);

        return convertView;
    }
}