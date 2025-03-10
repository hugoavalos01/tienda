package com.tienda.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/carritoproducto")
public class CarritoProductoController {

    @Autowired
    private CarritoProductoService carritoProductoService;

    // Obtener todos los productos en un carrito
    @GetMapping("/{carritoId}")
    public ResponseEntity<List<CarritoProducto>> obtenerProductosEnCarrito(@PathVariable Long carritoId) {
        List<CarritoProducto> productos = carritoProductoService.obtenerProductosEnCarrito(carritoId);

        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si no hay productos, devuelve 404
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Obtener un producto específico en un carrito
    @GetMapping("/{carritoId}/{productoId}")
    public ResponseEntity<CarritoProducto> obtenerProductoEnCarrito(@PathVariable Long carritoId, @PathVariable Long productoId) {
        Optional<CarritoProducto> carritoProducto = carritoProductoService.obtenerProductoEnCarrito(carritoId, productoId);

        if (carritoProducto.isPresent()) {
            return new ResponseEntity<>(carritoProducto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Producto no encontrado en el carrito
        }
    }

    // Agregar un producto al carrito
    @PostMapping("/{carritoId}/{productoId}")
    public ResponseEntity<CarritoProducto> agregarProductoAlCarrito(
            @PathVariable Long carritoId,
            @PathVariable Long productoId,
            @RequestParam Integer cantidad) {

        try {
            CarritoProducto carritoProducto = carritoProductoService.agregarProductoAlCarrito(carritoId, productoId, cantidad);
            return new ResponseEntity<>(carritoProducto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // En caso de error en la operación
        }
    }

    // Eliminar un producto del carrito
    @DeleteMapping("/{carritoId}/{productoId}")
    public ResponseEntity<Void> eliminarProductoDelCarrito(@PathVariable Long carritoId, @PathVariable Long productoId) {
        try {
            carritoProductoService.eliminarProductoDelCarrito(carritoId, productoId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Producto eliminado correctamente
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // En caso de que el producto no exista
        }
    }

    // Vaciar el carrito (eliminar todos los productos)
    @DeleteMapping("/{carritoId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long carritoId) {
        try {
            carritoProductoService.vaciarCarrito(carritoId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Carrito vacío correctamente
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // En caso de que el carrito no exista
        }
    }
}
