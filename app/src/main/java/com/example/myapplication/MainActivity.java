package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerCategorias;
    ImageView imgCategoria;
    Button btnSiguiente;

    int[] imagenes = {
            R.drawable.logo,
            R.drawable.distancia,
            R.drawable.volumen,
            R.drawable.masa,
            R.drawable.tiempo,
            R.drawable.datos
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        imgCategoria = findViewById(R.id.imgCategoria);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    ((TextView) view).setTextColor(getResources().getColor(R.color.blanco));
                }
                cambiarImagen(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = spinnerCategorias.getSelectedItemPosition();

                if (posicion == 0) {
                    return;
                }

                String categoria = spinnerCategorias.getSelectedItem().toString();

                Intent intent = new Intent(MainActivity.this, ConversionActivity.class);
                intent.putExtra("categoria", categoria);
                startActivity(intent);
            }
        });
    }

    private void cambiarImagen(int posicion) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imagenes[posicion]);
        imgCategoria.setImageBitmap(bitmap);
    }
}