package com.biblioteca.videojuegos.dto;

public class PrestamoRespuesta {

    private String mensaje;
    private String cliente;
    private String juego;
    private int dias;
    private double costoTotal;

    public PrestamoRespuesta(String mensaje, String cliente, String juego,
                             int dias, double costoTotal) {
        this.mensaje = mensaje;
        this.cliente = cliente;
        this.juego = juego;
        this.dias = dias;
        this.costoTotal = costoTotal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getCliente() {
        return cliente;
    }

    public String getJuego() {
        return juego;
    }

    public int getDias() {
        return dias;
    }

    public double getCostoTotal() {
        return costoTotal;
    }
}
