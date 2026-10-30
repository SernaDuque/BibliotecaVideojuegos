package com.biblioteca.videojuegos.controlador;

import com.biblioteca.videojuegos.modelo.Cliente;
import com.biblioteca.videojuegos.servicio.BibliotecaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final BibliotecaService bibliotecaService;

    public ClienteController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @PostMapping
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        return bibliotecaService.registrarCliente(cliente);
    }
}
