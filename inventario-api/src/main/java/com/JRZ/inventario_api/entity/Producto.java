package com.JRZ.inventario_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.time.LocalDateTime; // Más moderno y eficiente que java.sql
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
    //relacion
    @ManyToOne(fetch = FetchType.EAGER) // trae la categoria precargada junto con el producto
    @JoinColumn(name = "idCategoria") //nombre de la coplumna en la db
    private Categoria categoria;

    private LocalDateTime fechaPublicacion;
    @PositiveOrZero
    private Integer stock;





}
