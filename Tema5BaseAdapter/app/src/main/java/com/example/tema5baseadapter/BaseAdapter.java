package com.example.tema5baseadapter;

import android.content.Context;
import android.graphics.pdf.models.ListItem;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

abstract class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> items;

    private int selectedPosition = -1;

    public CustomAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();

    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent);
        }
        ImageView imageView = convertView.findViewById(R.id.itemImage);
        TextView tittleTextView = convertView.findViewById(R.id.itemTittle);
        TextView contentTextView = convertView.findViewById(R.id.itemContent);
        RadioButton radiobutton = convertView.findViewById(R.id.itemRadioButton);

        ListItem item = items.get(position);
        imageView.setImageResource(item.getImageResId());
        tittleTextView.setText(item.getTitle());
        contentTextView.setText(item.getContent());

        radiobutton.setChecked(position == selectedPosition);
        radiobutton.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
        });
        return convertView;
    }

    }





