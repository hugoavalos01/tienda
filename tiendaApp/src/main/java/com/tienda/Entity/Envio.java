package com.tienda.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Envio")
@Data
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    private String fechaEntrega;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
