package com.JRZ.inventario_api.controller;

import com.JRZ.inventario_api.entity.Producto;
import com.JRZ.inventario_api.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/productos")
public class ProductoController {
    
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    //endpoints
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity <?> Eliminar(@PathVariable Long id){
        productoService.EliminarProducto(id);
        return ResponseEntity.ok(Map.of("mensaje","Borrado exitosamente"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity <?> ActualizarProducto(@PathVariable Long id, @RequestBody Producto cambios){
        productoService.ActualizarProducto(id, cambios);
        return ResponseEntity.ok(cambios);
    }



}
