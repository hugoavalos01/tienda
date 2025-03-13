package com.tienda.Controller;

import com.tienda.Entity.Resena;
import com.tienda.Service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/resena")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    // Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<Resena>> obtenerTodasLasResenas() {
        List<Resena> resenas = resenaService.getAllResenas();
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    // Obtener una reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerResenaPorId(@PathVariable Long id) {
        return resenaService.getResenaById(id)
                .map(resena -> new ResponseEntity<>(resena, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva reseña
    @PostMapping
    public ResponseEntity<Resena> crearResena(@RequestBody Resena resena) {
        Resena nuevaResena = resenaService.createResena(resena);
        return new ResponseEntity<>(nuevaResena, HttpStatus.CREATED);
    }

    // Actualizar una reseña existente
    @PutMapping("/{id}")
    public ResponseEntity<Resena> actualizarResena(@PathVariable Long id, @RequestBody Resena resena) {
        return resenaService.updateResena(id, resena)
                .map(updatedResena -> new ResponseEntity<>(updatedResena, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar una reseña por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        if (resenaService.deleteResena(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
