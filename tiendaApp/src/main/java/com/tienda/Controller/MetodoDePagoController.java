package com.tienda.Controller;

import com.tienda.Entity.MetodoDePago;
import com.tienda.Service.MetodoDePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/metodoDePago")
public class MetodoDePagoController {

    @Autowired
    private MetodoDePagoService metodoDePagoService;

    // Obtener todos los métodos de pago
    @GetMapping
    public List<MetodoDePago> getAllMetodosDePago() {
        return metodoDePagoService.getAllMetodosDePago();
    }

    // Obtener un método de pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<MetodoDePago> getMetodoDePagoById(@PathVariable Long id) {
        return metodoDePagoService.getMetodoDePagoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo método de pago
    @PostMapping
    public MetodoDePago createMetodoDePago(@RequestBody MetodoDePago metodoDePago) {
        return metodoDePagoService.createMetodoDePago(metodoDePago);
    }

    // Actualizar un método de pago existente
    @PutMapping("/{id}")
    public ResponseEntity<MetodoDePago> updateMetodoDePago(@PathVariable Long id, @RequestBody MetodoDePago metodoDePago) {
        return metodoDePagoService.updateMetodoDePago(id, metodoDePago)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un método de pago por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetodoDePago(@PathVariable Long id) {
        if (metodoDePagoService.deleteMetodoDePago(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
