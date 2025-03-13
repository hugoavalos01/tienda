package com.tienda.Controller;

import com.tienda.Entity.DetallesPedido;
import com.tienda.Service.DetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/detallespedido")
public class DetallesPedidoController {

    @Autowired
    private DetallesPedidoService detallesPedidoService;

    @GetMapping
    public List<DetallesPedido> getAllDetallesPedido() {
        return detallesPedidoService.getAllDetallesPedido();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesPedido> getDetallesPedidoById(@PathVariable Long id) {
        return detallesPedidoService.getDetallesPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetallesPedido createDetallesPedido(@RequestBody DetallesPedido detallesPedido) {
        return detallesPedidoService.createDetallesPedido(detallesPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallesPedido> updateDetallesPedido(@PathVariable Long id, @RequestBody DetallesPedido detallesPedido) {
        return detallesPedidoService.updateDetallesPedido(id, detallesPedido)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetallesPedido(@PathVariable Long id) {
        if (detallesPedidoService.deleteDetallesPedido(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
