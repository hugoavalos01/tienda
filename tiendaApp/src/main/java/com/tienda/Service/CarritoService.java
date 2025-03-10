package com.tienda.Service;

import com.tienda.Entity.Carrito;
import com.tienda.Entity.Usuario;
import com.tienda.Repository.CarritoRepository;
import com.tienda.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener carrito por usuario
    public Optional<Carrito> getCarritoByUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Carrito> obtenerCarritoPorId(Long carritoId) {
        return carritoRepository.findById(carritoId);
    }

    // Crear un carrito para un usuario
    public Carrito crearCarrito(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            Carrito carrito = new Carrito();
            carrito.setUsuario(usuario.get());
            return carritoRepository.save(carrito);
        }
        throw new RuntimeException("Usuario no encontrado");
    }

    // Eliminar un carrito
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }
}
