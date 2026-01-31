package com.JRZ.inventario_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name="VideoJuegos")
public class VideoJuego {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nombre;

    private Double precio;

    private Integer stock;


    // constructores

    public VideoJuego() {

    }

    public VideoJuego(String nombre, Double precio, Integer stock){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    //getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void   setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void   setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void    setStock(Integer stock) { this.stock = stock; }
    



}
