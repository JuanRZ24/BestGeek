package com.JRZ.inventario_api.service;

import com.JRZ.inventario_api.entity.Categoria;

import org.springframework.stereotype.Service;
import com.JRZ.inventario_api.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }


public Categoria GuardarCategoria(Categoria categoria){
    // Imprime esto para ver si el JSON realmente se está mapeando
    System.out.println("LOG DEBUG - Objeto recibido: " + categoria);
    System.out.println("LOG DEBUG - Nombre recibido: " + categoria.getNombre());
    
    return categoriaRepository.save(categoria);
}

    //listar categorias

    public List<Categoria> listarTodos(){
        return categoriaRepository.findAll();
    }

    //eliminar categoria

    public void EliminarCategoria(Long id){
        categoriaRepository.deleteById(id);
    }

    public void ActualizarCategoria(Long id, Categoria cambios){
        categoriaRepository.findById(id).ifPresent(productoExistente -> {
            if (cambios.getNombre() != null){
                productoExistente.setNombre(cambios.getNombre());
            }
            categoriaRepository.save(productoExistente);
        });
    }




}
