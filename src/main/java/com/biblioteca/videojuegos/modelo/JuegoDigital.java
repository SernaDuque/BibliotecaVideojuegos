package com.biblioteca.videojuegos.modelo;

public class JuegoDigital extends ContenidoDigital {

    private double tamanoGB;

    public JuegoDigital() {
    }

    public JuegoDigital(Long id, String titulo, int anioLanzamiento,
                        double precioBase, boolean disponible, double tamanoGB) {
        super(id, titulo, anioLanzamiento, precioBase, disponible);
        this.tamanoGB = tamanoGB;
    }

    @Override
    public double calcularPrecioRenta(int dias) {
        double total = getPrecioBase() * dias;

        if (tamanoGB > 50) {
            total = total * 0.90;
        }

        return total;
    }

    public double getTamanoGB() {
        return tamanoGB;
    }

    public void setTamanoGB(double tamanoGB) {
        this.tamanoGB = tamanoGB;
    }
}
