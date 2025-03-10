package com.tienda.Repository;

import com.tienda.Entity.CarritoProducto;
import com.tienda.Entity.CarritoProductoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, CarritoProductoPK> {

    List<CarritoProducto> findByCarritoId(Long carritoId);

    void deleteById(CarritoProductoPK id);
}
