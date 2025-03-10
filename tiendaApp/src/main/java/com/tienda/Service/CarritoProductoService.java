package com.tienda.Service;

import com.tienda.Entity.Carrito;
import com.tienda.Entity.CarritoProducto;
import com.tienda.Entity.CarritoProductoPK;
import com.tienda.Entity.Producto;
import com.tienda.Repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoProductoService {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private CarritoService carritoService; // Para gestionar carritos
    @Autowired
    private ProductoService productoService; // Para gestionar productos

    // Obtener todos los productos dentro de un carrito
    public List<CarritoProducto> obtenerProductosEnCarrito(Long carritoId) {
        return carritoProductoRepository.findByCarritoId(carritoId);
    }

    // Obtener un producto específico en un carrito
    public Optional<CarritoProducto> obtenerProductoEnCarrito(Long carritoId, Long productoId) {
        CarritoProductoPK id = new CarritoProductoPK(carritoId, productoId);
        return carritoProductoRepository.findById(id);
    }

    // Agregar un producto al carrito
    public CarritoProducto agregarProductoAlCarrito(Long carritoId, Long productoId, Integer cantidad) {
        Carrito carrito = carritoService.obtenerCarritoPorId(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Producto producto = productoService.obtenerProductoPorId(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verificar si el producto ya está en el carrito
        CarritoProductoPK id = new CarritoProductoPK(carritoId, productoId);
        Optional<CarritoProducto> existingCarritoProducto = carritoProductoRepository.findById(id);

        if (existingCarritoProducto.isPresent()) {
            // Si el producto ya existe en el carrito, actualizamos la cantidad
            CarritoProducto carritoProducto = existingCarritoProducto.get();
            carritoProducto.setCantidad(carritoProducto.getCantidad() + cantidad);
            return carritoProductoRepository.save(carritoProducto);
        } else {
            // Si no existe, agregamos un nuevo producto al carrito
            CarritoProducto carritoProducto = new CarritoProducto();
            carritoProducto.setCarrito(carrito);
            carritoProducto.setProducto(producto);
            carritoProducto.setCantidad(cantidad);
            carritoProducto.setId(id);
            return carritoProductoRepository.save(carritoProducto);
        }
    }

    // Eliminar un producto del carrito
    public void eliminarProductoDelCarrito(Long carritoId, Long productoId) {
        CarritoProductoPK id = new CarritoProductoPK(carritoId, productoId);
        carritoProductoRepository.deleteById(id);
    }

    // Eliminar todos los productos de un carrito
    public void vaciarCarrito(Long carritoId) {
        List<CarritoProducto> productos = carritoProductoRepository.findByCarritoId(carritoId);
        carritoProductoRepository.deleteAll(productos);
    }
}
