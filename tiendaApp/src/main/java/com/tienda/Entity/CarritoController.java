package com.tienda.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Obtener el carrito de un usuario
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Carrito> getCarritoByUsuario(@PathVariable Long usuarioId) {
        Optional<Carrito> carrito = carritoService.getCarritoByUsuario(usuarioId);
        return carrito.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo carrito para un usuario
    @PostMapping("/crear/{usuarioId}")
    public ResponseEntity<Carrito> crearCarrito(@PathVariable Long usuarioId) {
        Carrito nuevoCarrito = carritoService.crearCarrito(usuarioId);
        return ResponseEntity.ok(nuevoCarrito);
    }

    // Eliminar un carrito
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }
}
