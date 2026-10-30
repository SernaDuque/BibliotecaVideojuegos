package com.biblioteca.videojuegos.servicio;

import com.biblioteca.videojuegos.dto.PrestamoRespuesta;
import com.biblioteca.videojuegos.modelo.Cliente;
import com.biblioteca.videojuegos.modelo.ContenidoDigital;
import com.biblioteca.videojuegos.modelo.JuegoDigital;
import com.biblioteca.videojuegos.modelo.JuegoFisico;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class BibliotecaService {

    private final List<ContenidoDigital> juegos = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();

    private final AtomicLong contadorJuegos = new AtomicLong(1);
    private final AtomicLong contadorClientes = new AtomicLong(1);

    public JuegoFisico registrarJuegoFisico(JuegoFisico juego) {
        validarJuego(juego.getTitulo(), juego.getAnioLanzamiento(), juego.getPrecioBase());
        juego.setId(contadorJuegos.getAndIncrement());
        juego.setDisponible(true);
        juegos.add(juego);
        return juego;
    }

    public JuegoDigital registrarJuegoDigital(JuegoDigital juego) {
        validarJuego(juego.getTitulo(), juego.getAnioLanzamiento(), juego.getPrecioBase());
        juego.setId(contadorJuegos.getAndIncrement());
        juego.setDisponible(true);
        juegos.add(juego);
        return juego;
    }

    private void validarJuego(String titulo, int anio, double precio) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El titulo es obligatorio");
        }
        if (anio <= 0 || precio < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Revisa el año y el precio del juego");
        }
    }

    public List<ContenidoDigital> listarJuegosDisponibles() {
    List<ContenidoDigital> disponibles = new ArrayList<>();

    for (ContenidoDigital juego : juegos) {
        if (juego.isDisponible()) {
            disponibles.add(juego);
        }
    }

    return disponibles;
}

    public Cliente registrarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()
                || cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre y el email son obligatorios");
        }

        cliente.setId(contadorClientes.getAndIncrement());
        cliente.setJuegosPrestados(new ArrayList<>());
        clientes.add(cliente);
        return cliente;
    }

    public PrestamoRespuesta alquilarJuego(Long clienteId, Long juegoId, int dias) {
        if (dias <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los dias deben ser mayores que cero");
        }

        Cliente cliente = buscarCliente(clienteId);
        ContenidoDigital juego = buscarJuego(juegoId);

        if (!juego.isDisponible()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El juego no esta disponible");
        }

        if (!cliente.puedeAlquilar()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente ya tiene 3 juegos prestados");
        }

        double costo = juego.calcularPrecioRenta(dias);
        juego.setDisponible(false);
        cliente.agregarJuego(juego);

        return new PrestamoRespuesta(
                "Prestamo realizado correctamente",
                cliente.getNombre(),
                juego.getTitulo(),
                dias,
                Math.round(costo * 100.0) / 100.0
        );
    }

    private Cliente buscarCliente(Long id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el cliente"));
    }

    private ContenidoDigital buscarJuego(Long id) {
        return juegos.stream()
                .filter(juego -> juego.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el juego"));
    }
}
