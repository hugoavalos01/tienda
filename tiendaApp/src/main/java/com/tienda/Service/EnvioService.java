package com.tienda.Service;

import com.tienda.Entity.Envio;
import com.tienda.Repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository; // Asumo que tienes un repositorio para la entidad Envio

    // Obtener todos los envíos
    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();
    }

    // Obtener un envío por ID
    public Optional<Envio> getEnvioById(Long id) {
        return envioRepository.findById(id);
    }

    // Crear un nuevo envío
    public Envio createEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    // Actualizar un envío
    public Optional<Envio> updateEnvio(Long id, Envio envio) {
        if (envioRepository.existsById(id)) {
            envio.setId(id);
            return Optional.of(envioRepository.save(envio));
        } else {
            return Optional.empty();
        }
    }

    // Eliminar un envío por ID
    public boolean deleteEnvio(Long id) {
        if (envioRepository.existsById(id)) {
            envioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
