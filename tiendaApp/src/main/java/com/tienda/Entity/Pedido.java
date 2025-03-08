package com.tienda.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Table(name = "Pedido")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private String estado;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id")
    private MetodoDePago metodoDePago;

    @OneToMany(mappedBy = "pedido")
    private List<DetallesPedido> detalles;

}
