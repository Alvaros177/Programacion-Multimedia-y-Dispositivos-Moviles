package com.example.animacionconbotones;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    public void onStart() {
        super.onStart();
        Button boton1 = findViewById(R.id.btnTranslate);
        Button boton2 = findViewById(R.id.btnRotate);
        Button boton3 = findViewById(R.id.btnDetener);
        TextView texto = findViewById(R.id.texto);
        boton1.setOnClickListener((View.OnClickListener) this);
        boton2.setOnClickListener((View.OnClickListener) this);
        boton3.setOnClickListener((View.OnClickListener) this);
        Animation Translate = AnimationUtils.loadAnimation(this, R.anim.animacion);
        Animation Rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                texto.startAnimation(Translate);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.startAnimation(Rotate);
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.clearAnimation();
            }
        });

    }

}


