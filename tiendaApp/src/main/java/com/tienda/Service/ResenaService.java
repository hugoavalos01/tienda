package com.tienda.Service;

import com.tienda.Entity.Resena;
import com.tienda.Repository.ProductoRepository;
import com.tienda.Repository.ResenaRepository;
import com.tienda.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todas las reseñas
    public List<Resena> getAllResenas() {
        return resenaRepository.findAll();
    }

    // Obtener una reseña por ID
    public Optional<Resena> getResenaById(Long id) {
        return resenaRepository.findById(id);
    }

    // Crear una nueva reseña
    public Resena createResena(Resena resena) {
        // Aquí puedes agregar validaciones adicionales, como verificar que el usuario y el producto existan
        if (usuarioRepository.existsById(resena.getUsuario().getId()) && productoRepository.existsById(resena.getProducto().getId())) {
            return resenaRepository.save(resena);  // Guarda la nueva reseña
        } else {
            throw new RuntimeException("Usuario o Producto no encontrado");  // Lanza excepción si no existe el usuario o el producto
        }
    }

    // Actualizar una reseña existente
    public Optional<Resena> updateResena(Long id, Resena resena) {
        // Verifica si la reseña existe
        if (resenaRepository.existsById(id)) {
            // Verifica que el usuario y el producto existan
            if (usuarioRepository.existsById(resena.getUsuario().getId()) && productoRepository.existsById(resena.getProducto().getId())) {
                resena.setId(id);  // Asegura que el ID esté correctamente asignado
                return Optional.of(resenaRepository.save(resena));  // Guarda la reseña actualizada
            } else {
                throw new RuntimeException("Usuario o Producto no encontrado");
            }
        } else {
            return Optional.empty();  // Si no existe la reseña, retorna un Optional vacío
        }
    }

    // Eliminar una reseña por ID
    public boolean deleteResena(Long id) {
        // Verifica si la reseña existe
        if (resenaRepository.existsById(id)) {
            resenaRepository.deleteById(id);  // Elimina la reseña
            return true;
        } else {
            return false;  // Si no existe la reseña, no puede eliminarse
        }
    }
}
