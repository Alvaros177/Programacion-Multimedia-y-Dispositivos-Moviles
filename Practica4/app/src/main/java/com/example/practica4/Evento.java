package com.example.practica4;

public class Evento {
    private String nombre;
    private String fecha;
    private String hora;

    public Evento(String nombre, String fecha, String hora) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Getter para fecha
    public String getFecha() {
        return fecha;
    }

    // Getter para hora
    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return nombre; // Para mostrar en la lista
    }
}