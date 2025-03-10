package com.tienda.Service;

import com.tienda.Entity.Producto;
import com.tienda.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener un producto por su ID
    public Optional<Producto> obtenerProductoPorId(Long productoId) {
        return productoRepository.findById(productoId);
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Crear un nuevo producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        if (productoRepository.existsById(id)) {
            productoActualizado.setId(id);
            return productoRepository.save(productoActualizado);
        }
        throw new RuntimeException("Producto no encontrado con ID: " + id);
    }

    // Eliminar un producto
    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }
}
