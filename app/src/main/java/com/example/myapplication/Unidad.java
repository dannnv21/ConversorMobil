package com.example.myapplication;

public class Unidad {

    String nombre;
    double factor;

    public Unidad(String nombre, double factor) {
        this.nombre = nombre;
        this.factor = factor;
    }

    public double aBase(double valor) {
        return valor * factor;
    }

    public double desdeBase(double valor) {
        return valor / factor;
    }
}
