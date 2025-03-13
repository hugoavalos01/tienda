package com.tienda.Controller;

import com.tienda.Entity.Pedido;
import com.tienda.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Obtener todos los pedidos
    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    // Obtener un pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo pedido
    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.createPedido(pedido);
    }

    // Actualizar un pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.updatePedido(id, pedido)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        if (pedidoService.deletePedido(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
