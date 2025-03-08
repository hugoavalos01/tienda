package com.tienda.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CarritoProductoPK implements Serializable {

    private Long carrito_id;
    private Long producto_id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CarritoProductoPK that = (CarritoProductoPK) obj;
        return carrito_id.equals(that.carrito_id) && producto_id.equals(that.producto_id);
    }

    @Override
    public int hashCode() {
        return 31 * carrito_id.hashCode() + producto_id.hashCode();
    }
}
