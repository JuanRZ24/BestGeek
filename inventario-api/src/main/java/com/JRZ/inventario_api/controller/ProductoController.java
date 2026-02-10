package com.JRZ.inventario_api.controller;

import com.JRZ.inventario_api.entity.Producto;
import com.JRZ.inventario_api.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoController {
    
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    //endpoints
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto){
        //llamamos al service guardarproducto

        Producto newProducto = productoService.GuardarProducto(producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
    }

    @GetMapping
    public List<Producto> listar(){
        return productoService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void Eliminar(@PathVariable Long id){
        productoService.EliminarProducto(id);
    }

    @PatchMapping("/{id}")
    public void ActualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        productoService.ActualizarProducto(id, producto);
    }



}
