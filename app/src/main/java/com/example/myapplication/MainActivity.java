package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategories;
    private ImageView imageViewCategory;
    private Button btnNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategories = findViewById(R.id.spinnerCategories);
        imageViewCategory = findViewById(R.id.imageViewCategory);
        btnNextActivity = findViewById(R.id.btnNextActivity);

        // Opciones de categorías basadas en el documento del proyecto
        String[] categories = {
                "Selecciona una categoría...",
                "Distance",
                "Temperature",
                "Mass and Weight",
                "Time",
                "Digital Information",
                "Currency"
        };

        // Configurar el adaptador del Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinnerCategories.setAdapter(adapter);

        // Detectar cuando el usuario cambia de categoría en el Spinner
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();

                // Cargar imagen usando Bitmap según la categoría seleccionada
                cargarImagenBitmap(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Sin selección
            }
        });

        // Configurar el botón para pasar a la Segunda Actividad
        btnNextActivity.setOnClickListener(v -> {
            String selectedCategory = spinnerCategories.getSelectedItem().toString();

            // Validar que se haya seleccionado una categoría válida (no la opción por defecto)
            if (spinnerCategories.getSelectedItemPosition() == 0) {
                // Requisito: Usar Snackbar en lugar de Toast para mensajes emergentes
                Snackbar.make(v, "Por favor, selecciona una categoría válida", Snackbar.LENGTH_SHORT).show();
            } else {
                // Requisito: Pasar la categoría seleccionada a la Activity 2 usando un Intent
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("CATEGORY_EXTRA", selectedCategory);
                startActivity(intent);
            }
        });
    }

    // Método para cargar la imagen con Bitmap usando los iconos que acabas de crear
    private void cargarImagenBitmap(String category) {
        int drawableResId;

        switch (category) {
            case "Distance":
                drawableResId = R.drawable.img_distance;
                break;
            case "Temperature":
                drawableResId = R.drawable.thermostat;
                break;
            case "Mass and Weight":
                drawableResId = R.drawable.scale;
                break;
            case "Time":
                drawableResId = R.drawable.outline_alarm_24;
                break;
            case "Digital Information":
                drawableResId = R.drawable.storage;
                break;
            case "Currency":
                drawableResId = R.drawable.payments;
                break;
            default:
                drawableResId = android.R.drawable.ic_menu_help;
                break;
        }

        // Cargar la imagen utilizando la clase Bitmap y BitmapFactory como exige el proyecto
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableResId);
        imageViewCategory.setImageBitmap(bitmap);
    }
}