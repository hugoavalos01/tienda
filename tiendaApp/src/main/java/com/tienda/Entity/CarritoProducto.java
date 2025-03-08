package com.tienda.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CarritoProducto")
@Data
public class CarritoProducto {

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "carrito_id", insertable = false, updatable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "producto_id", insertable = false, updatable = false)
    private Producto producto;


    @EmbeddedId
    private CarritoProductoPK id;


}
