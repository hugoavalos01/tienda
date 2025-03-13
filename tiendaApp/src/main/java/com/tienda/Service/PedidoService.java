package com.tienda.Service;

import com.tienda.Entity.Pedido;
import com.tienda.Repository.MetodoDePagoRepository;
import com.tienda.Repository.PedidoRepository;
import com.tienda.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;

    // Obtener todos los pedidos
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    // Obtener un pedido por ID
    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    // Crear un nuevo pedido
    public Pedido createPedido(Pedido pedido) {

        if (usuarioRepository.existsById(pedido.getUsuario().getId()) && metodoDePagoRepository.existsById(pedido.getMetodoDePago().getId())) {
            return pedidoRepository.save(pedido);
        } else {
            throw new RuntimeException("Usuario o Método de Pago no encontrado");
        }
    }

    // Actualizar un pedido existente
    public Optional<Pedido> updatePedido(Long id, Pedido pedido) {
        if (pedidoRepository.existsById(id)) {
            if (usuarioRepository.existsById(pedido.getUsuario().getId()) && metodoDePagoRepository.existsById(pedido.getMetodoDePago().getId())) {
                pedido.setId(id);
                return Optional.of(pedidoRepository.save(pedido));
            } else {
                throw new RuntimeException("Usuario o Método de Pago no encontrado");
            }
        } else {
            return Optional.empty();
        }
    }

    // Eliminar un pedido por ID
    public boolean deletePedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
