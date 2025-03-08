package com.tienda.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Carrito")
@Data
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

}
