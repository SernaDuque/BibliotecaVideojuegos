package com.biblioteca.videojuegos.modelo;

public class JuegoFisico extends ContenidoDigital {

    private double pesoGramos;

    public JuegoFisico() {
    }

    public JuegoFisico(Long id, String titulo, int anioLanzamiento,
                       double precioBase, boolean disponible, double pesoGramos) {
        super(id, titulo, anioLanzamiento, precioBase, disponible);
        this.pesoGramos = pesoGramos;
    }

    @Override
    public double calcularPrecioRenta(int dias) {
        return (getPrecioBase() * dias) + 2.00;
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }
}
