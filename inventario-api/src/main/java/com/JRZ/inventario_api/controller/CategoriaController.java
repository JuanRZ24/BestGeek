package com.JRZ.inventario_api.controller;


import com.JRZ.inventario_api.entity.Categoria;

import com.JRZ.inventario_api.service.CategoriaService;


import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody @Valid  Categoria categoria){
        Categoria newCategoria = categoriaService.GuardarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
    }

    @DeleteMapping("/{id}")
    public void Eliminar(@PathVariable Long id){
        categoriaService.EliminarCategoria(id);
    }

    @PatchMapping("{id}")
    public void ActualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        categoriaService.ActualizarCategoria(id,categoria);
    }

}
