package com.tienda.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MetodoDePago")
@Data
public class MetodoDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String numeroTarjeta;
    private String fechaVencimiento;
    private String cvv;

}
