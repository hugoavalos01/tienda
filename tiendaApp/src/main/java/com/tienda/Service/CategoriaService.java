package com.tienda.Service;

import com.tienda.Entity.Categoria;
import com.tienda.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> updateCategoria(Long id, Categoria categoria) {
        return categoriaRepository.findById(id).map(existingCategoria -> {
            existingCategoria.setNombre(categoria.getNombre());
            existingCategoria.setDescripcion(categoria.getDescripcion());
            return categoriaRepository.save(existingCategoria);
        });
    }

    public boolean deleteCategoria(Long id) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoriaRepository.delete(categoria);
            return true;
        }).orElse(false);
    }
}
