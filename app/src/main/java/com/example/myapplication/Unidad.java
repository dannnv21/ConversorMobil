package com.example.myapplication;

public class Unidad {

    String nombre;
    double factor;
    double desplazamiento;

    public Unidad(String nombre, double factor) {
        this.nombre = nombre;
        this.factor = factor;
        this.desplazamiento = 0;
    }

    public Unidad(String nombre, double factor, double desplazamiento) {
        this.nombre = nombre;
        this.factor = factor;
        this.desplazamiento = desplazamiento;
    }

    public double aBase(double valor) {
        return valor * factor + desplazamiento;
    }

    public double desdeBase(double valor) {
        return (valor - desplazamiento) / factor;
    }
}