package com.tienda.Service;

import com.tienda.Entity.Usuario;
import com.tienda.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        // Se pueden agregar validaciones adicionales aquí si es necesario
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario existente
    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuario) {
        // Verifica si el usuario existe
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);  // Asegura que el ID esté asignado correctamente
            return Optional.of(usuarioRepository.save(usuario));
        } else {
            return Optional.empty();  // Si no se encuentra el usuario, retorna un Optional vacío
        }
    }

    // Eliminar un usuario por ID
    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);  // Elimina el usuario
            return true;
        } else {
            return false;  // Si no se encuentra el usuario, no puede eliminarse
        }
    }
}
