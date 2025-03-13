package com.tienda.Controller;

import com.tienda.Entity.Usuario;
import com.tienda.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Si no hay usuarios, respuesta 204 No Content
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK); // Si hay usuarios, respuesta 200 OK
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        // Verificamos si el usuario existe
        if (usuarioService.obtenerUsuarioPorId(id).isPresent()) {
            Usuario usuario = usuarioService.obtenerUsuarioPorId(id).get();
            return new ResponseEntity<>(usuario, HttpStatus.OK); // Respuesta 200 OK si el usuario existe
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Respuesta 404 Not Found si el usuario no existe
        }
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        try {
            // Creación del nuevo usuario
            Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED); // Respuesta 201 Created al crear el usuario
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Respuesta 400 Bad Request en caso de error
        }
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        // Verificamos si el usuario existe
        if (usuarioService.obtenerUsuarioPorId(id).isPresent()) {
            // Si existe, actualizamos el usuario
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario).get();
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK); // Respuesta 200 OK si la actualización es exitosa
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Respuesta 404 Not Found si no se encuentra el usuario
        }
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        // Verificamos si el usuario existe
        if (usuarioService.obtenerUsuarioPorId(id).isPresent()) {
            // Si el usuario existe, lo eliminamos
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Respuesta 204 No Content si la eliminación es exitosa
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Respuesta 404 Not Found si no se encuentra el usuario
        }
    }
}
