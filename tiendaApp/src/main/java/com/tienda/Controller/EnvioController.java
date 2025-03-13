package com.tienda.Controller;

import com.tienda.Entity.Envio;
import com.tienda.Service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> getAllEnvios() {
        return envioService.getAllEnvios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> getEnvioById(@PathVariable Long id) {
        return envioService.getEnvioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Envio createEnvio(@RequestBody Envio envio) {
        return envioService.createEnvio(envio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> updateEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        return envioService.updateEnvio(id, envio)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable Long id) {
        if (envioService.deleteEnvio(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
