package com.JRZ.inventario_api.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "productos")
@Data
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private Double precio;


}
