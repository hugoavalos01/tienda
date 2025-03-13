package com.tienda.Service;

import com.tienda.Entity.DetallesPedido;
import com.tienda.Repository.DetallesPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallesPedidoService {

    @Autowired
    private DetallesPedidoRepository detallesPedidoRepository;

    public List<DetallesPedido> getAllDetallesPedido() {
        return detallesPedidoRepository.findAll();
    }

    public Optional<DetallesPedido> getDetallesPedidoById(Long id) {
        return detallesPedidoRepository.findById(id);
    }

    public DetallesPedido createDetallesPedido(DetallesPedido detallesPedido) {
        return detallesPedidoRepository.save(detallesPedido);
    }

    public Optional<DetallesPedido> updateDetallesPedido(Long id, DetallesPedido detallesPedido) {
        return detallesPedidoRepository.findById(id).map(existingDetalles -> {
            existingDetalles.setCantidad(detallesPedido.getCantidad());
            existingDetalles.setPrecioUnitario(detallesPedido.getPrecioUnitario());
            existingDetalles.setPedido(detallesPedido.getPedido());
            existingDetalles.setProducto(detallesPedido.getProducto());
            return detallesPedidoRepository.save(existingDetalles);
        });
    }

    public boolean deleteDetallesPedido(Long id) {
        return detallesPedidoRepository.findById(id).map(detallesPedido -> {
            detallesPedidoRepository.delete(detallesPedido);
            return true;
        }).orElse(false);
    }
}
