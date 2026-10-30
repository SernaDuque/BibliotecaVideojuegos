# Biblioteca de videojuegos

Este proyecto es una API REST sencilla para registrar videojuegos, registrar clientes y prestar juegos.

## Tecnologias

- Java 17
- Spring Boot 3
- Maven
- Datos guardados en listas `ArrayList` (en memoria)

## Como ejecutar en Visual Studio Code

1. Tener instalado Java 17 o una version superior.
2. Instalar en VS Code el paquete **Extension Pack for Java** y **Spring Boot Extension Pack**.
3. Abrir la carpeta principal `BibliotecaVideojuegos` en VS Code.
4. Esperar a que Maven descargue las dependencias.
5. Abrir `BibliotecaVideojuegosApplication.java` y ejecutar el metodo `main` con la opcion **Run**.

Tambien se puede ejecutar desde una terminal ubicada en la carpeta principal, si Maven esta instalado:

Tambien se puede ejecutar desde una terminal ubicada en la carpeta del proyecto:

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

**Importante:** como se usan listas en memoria, los juegos y clientes se borran cuando se apaga la aplicacion.

## Organización

- `modelo`: clases principales del proyecto.
- `dto`: clases que se usan para recibir y responder datos de los prestamos.
- `servicio`: contiene las reglas del sistema.
- `controlador`: contiene las rutas de la API.

`ContenidoDigital` es la clase abstracta. `JuegoFisico` y `JuegoDigital` heredan sus datos y calculan el precio de renta de forma diferente. `Cliente` guarda los juegos que tiene prestados y permite revisar si puede alquilar otro.

## Pruebas de los endpoints

Se recomienda probar las rutas en Postman o Bruno usando el metodo y el JSON indicados.

### 1. Registrar un juego fisico

`POST http://localhost:8080/api/juegos/fisico`

```json
{
  "titulo": "FIFA 25",
  "anioLanzamiento": 2024,
  "precioBase": 5.0,
  "pesoGramos": 120
}
```

### 2. Registrar un juego digital

`POST http://localhost:8080/api/juegos/digital`

```json
{
  "titulo": "Minecraft",
  "anioLanzamiento": 2011,
  "precioBase": 4.0,
  "tamanoGB": 60
}
```

### 3. Listar juegos disponibles

`GET http://localhost:8080/api/juegos`

### 4. Registrar un cliente

`POST http://localhost:8080/api/clientes`

```json
{
  "nombre": "Juan Perez",
  "email": "juan@gmail.com"
}
```

### 5. Alquilar un juego

`POST http://localhost:8080/api/prestamos/alquilar`

```json
{
  "clienteId": 1,
  "juegoId": 1,
  "dias": 3
}
```

La respuesta muestra el nombre del cliente, el juego, los dias y el costo total.

## Reglas incluidas

- Un juego fisico cobra el precio base por cada dia y suma 2.00 por envio.
- Un juego digital cobra el precio base por cada dia. Si ocupa mas de 50 GB, se descuenta el 10%.
- Un cliente puede tener hasta 3 juegos prestados al mismo tiempo.
- Un juego que ya fue alquilado deja de aparecer en la lista de disponibles.

## Funcionalidades

- Registrar juegos físicos.
- Registrar juegos digitales.
- Consultar los juegos disponibles.
- Registrar clientes.
- Alquilar juegos a los clientes.
- Calcular el precio del alquiler según el tipo de juego.
- Validar la disponibilidad de los juegos.
- Limitar los préstamos a tres juegos por cliente.