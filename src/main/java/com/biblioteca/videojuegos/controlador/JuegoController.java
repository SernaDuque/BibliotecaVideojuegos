package com.biblioteca.videojuegos.controlador;

import com.biblioteca.videojuegos.modelo.ContenidoDigital;
import com.biblioteca.videojuegos.modelo.JuegoDigital;
import com.biblioteca.videojuegos.modelo.JuegoFisico;
import com.biblioteca.videojuegos.servicio.BibliotecaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    private final BibliotecaService bibliotecaService;

    public JuegoController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @PostMapping("/fisico")
    public JuegoFisico registrarFisico(@RequestBody JuegoFisico juego) {
        return bibliotecaService.registrarJuegoFisico(juego);
    }

    @PostMapping("/digital")
    public JuegoDigital registrarDigital(@RequestBody JuegoDigital juego) {
        return bibliotecaService.registrarJuegoDigital(juego);
    }

    @GetMapping
    public List<ContenidoDigital> listarDisponibles() {
        return bibliotecaService.listarJuegosDisponibles();
    }
}
