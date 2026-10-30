package com.biblioteca.videojuegos.controlador;

import com.biblioteca.videojuegos.dto.PrestamoRequest;
import com.biblioteca.videojuegos.dto.PrestamoRespuesta;
import com.biblioteca.videojuegos.servicio.BibliotecaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final BibliotecaService bibliotecaService;

    public PrestamoController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @PostMapping("/alquilar")
    public PrestamoRespuesta alquilar(@RequestBody PrestamoRequest solicitud) {
        return bibliotecaService.alquilarJuego(
                solicitud.getClienteId(),
                solicitud.getJuegoId(),
                solicitud.getDias()
        );
    }
}
