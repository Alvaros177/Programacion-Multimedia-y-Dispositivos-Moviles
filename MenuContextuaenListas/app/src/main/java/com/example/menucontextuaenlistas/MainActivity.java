package com.example.menucontextuaenlistas;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> itemlist;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        itemList = new ArrayList<>();
        itemList.add ("Elemento 1 ");
        itemList.add ("Elemento 2 ");
        itemList.add ("Elemento 3 ");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemlist);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);


    }
}