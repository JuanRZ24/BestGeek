package com.JRZ.inventario_api.controller;


import com.JRZ.inventario_api.entity.Categoria;

import com.JRZ.inventario_api.service.CategoriaService;


import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar(){
        return categoriaService.listarTodos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody @Valid  Categoria categoria){
        Categoria newCategoria = categoriaService.GuardarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity <?> Eliminar(@PathVariable Long id){
        categoriaService.EliminarCategoria(id);
        return ResponseEntity.ok(Map.of("mensaje","Borrado exitosamente"));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("{id}")
    public ResponseEntity <?> ActualizarCategoria(@PathVariable Long id, @RequestBody Categoria cambios){
        categoriaService.ActualizarCategoria(id,cambios);
        return ResponseEntity.ok(cambios);
    }

}
