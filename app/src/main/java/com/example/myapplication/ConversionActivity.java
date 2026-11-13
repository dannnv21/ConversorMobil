package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.text.DecimalFormat;
import java.util.HashMap;

public class ConversionActivity extends AppCompatActivity {

    TextView txtCategoria, txtResultado;
    EditText etValor;
    Spinner spinnerOrigen, spinnerDestino;
    Button btnConvertir, btnVolver;

    HashMap<String, Unidad[]> categorias = new HashMap<>();
    Unidad[] unidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        txtCategoria = findViewById(R.id.txtCategoria);
        txtResultado = findViewById(R.id.txtResultado);
        etValor = findViewById(R.id.etValor);
        spinnerOrigen = findViewById(R.id.spinnerOrigen);
        spinnerDestino = findViewById(R.id.spinnerDestino);
        btnConvertir = findViewById(R.id.btnConvertir);
        btnVolver = findViewById(R.id.btnVolver);

        cargarUnidades();

        String categoria = getIntent().getStringExtra("categoria");
        txtCategoria.setText(categoria);
        unidades = categorias.get(categoria);

        if (unidades == null) {
            btnConvertir.setEnabled(false);
            return;
        }

        String[] nombres = new String[unidades.length];
        for (int i = 0; i < unidades.length; i++) {
            nombres[i] = unidades[i].nombre;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, nombres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigen.setAdapter(adapter);
        spinnerDestino.setAdapter(adapter);
        spinnerDestino.setSelection(1);

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir(v);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void convertir(View v) {
        String texto = etValor.getText().toString().trim().replace(",", ".");

        if (texto.isEmpty()) {
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            return;
        }

        Unidad origen = unidades[spinnerOrigen.getSelectedItemPosition()];
        Unidad destino = unidades[spinnerDestino.getSelectedItemPosition()];

        if (origen == destino) {
            return;
        }

        double resultado = destino.desdeBase(origen.aBase(valor));

        DecimalFormat formato = new DecimalFormat("#,##0.####");
        txtResultado.setText(formato.format(resultado) + " " + destino.nombre);

    }

    private void cargarUnidades() {
        categorias.put("Distancia", new Unidad[]{
                new Unidad("Metros", 1),
                new Unidad("Kilómetros", 1000),
                new Unidad("Centímetros", 0.01),
                new Unidad("Milímetros", 0.001),
                new Unidad("Millas", 1609.344),
                new Unidad("Yardas", 0.9144),
                new Unidad("Pies", 0.3048),
                new Unidad("Pulgadas", 0.0254)
        });

        categorias.put("Volumen", new Unidad[]{
                new Unidad("Litros", 1),
                new Unidad("Mililitros", 0.001),
                new Unidad("Metros cúbicos", 1000),
                new Unidad("Galones (EE. UU.)", 3.785411784),
                new Unidad("Tazas", 0.2365882365)
        });

        categorias.put("Masa", new Unidad[]{
                new Unidad("Kilogramos", 1),
                new Unidad("Gramos", 0.001),
                new Unidad("Miligramos", 0.000001),
                new Unidad("Toneladas", 1000),
                new Unidad("Libras", 0.45359237),
                new Unidad("Onzas", 0.028349523125)
        });

        categorias.put("Tiempo", new Unidad[]{
                new Unidad("Segundos", 1),
                new Unidad("Minutos", 60),
                new Unidad("Horas", 3600),
                new Unidad("Días", 86400),
                new Unidad("Semanas", 604800)
        });

        categorias.put("Datos", new Unidad[]{
                new Unidad("Bits", 0.125),
                new Unidad("Bytes", 1),
                new Unidad("Kilobytes", 1024),
                new Unidad("Megabytes", 1048576),
                new Unidad("Gigabytes", 1073741824),
                new Unidad("Terabytes", 1099511627776.0)
        });
    }
}