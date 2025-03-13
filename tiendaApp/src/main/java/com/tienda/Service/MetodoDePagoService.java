package com.tienda.Service;

import com.tienda.Entity.MetodoDePago;
import com.tienda.Repository.MetodoDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoDePagoService {

    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;

    // Obtener todos los métodos de pago
    public List<MetodoDePago> getAllMetodosDePago() {
        return metodoDePagoRepository.findAll();
    }

    // Obtener un método de pago por ID
    public Optional<MetodoDePago> getMetodoDePagoById(Long id) {
        return metodoDePagoRepository.findById(id);
    }

    // Crear un nuevo método de pago
    public MetodoDePago createMetodoDePago(MetodoDePago metodoDePago) {
        // Aquí puedes agregar validaciones antes de guardar el método de pago, si es necesario
        return metodoDePagoRepository.save(metodoDePago);
    }

    // Actualizar un método de pago
    public Optional<MetodoDePago> updateMetodoDePago(Long id, MetodoDePago metodoDePago) {
        // Verifica si el método de pago existe
        if (metodoDePagoRepository.existsById(id)) {
            metodoDePago.setId(id);  // Asegúrate de que el ID esté correctamente asignado
            return Optional.of(metodoDePagoRepository.save(metodoDePago));
        } else {
            return Optional.empty();  // Si no existe, retorna un Optional vacío
        }
    }

    // Eliminar un método de pago por ID
    public boolean deleteMetodoDePago(Long id) {
        if (metodoDePagoRepository.existsById(id)) {
            metodoDePagoRepository.deleteById(id);  // Elimina el método de pago
            return true;
        } else {
            return false;  // Si no existe, no se puede eliminar
        }
    }
}
