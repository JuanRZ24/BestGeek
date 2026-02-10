package com.JRZ.inventario_api.service;


import com.JRZ.inventario_api.entity.Producto;
import org.springframework.stereotype.Service;
import com.JRZ.inventario_api.repository.ProductoRepository;

import java.util.List;
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }


    //guardar producto
    public Producto GuardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    //listar productos

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }

    public void EliminarProducto(Long id){
        productoRepository.deleteById(id);
    }

    public void ActualizarProducto(Long id, Producto cambios){
        productoRepository.findById(id).ifPresent(productoExistente -> {
            if (cambios.getNombre() != null){
                productoExistente.setNombre(cambios.getNombre());
            }
            if (cambios.getPrecio() != null){
                productoExistente.setPrecio(cambios.getPrecio());
            }
            productoRepository.save(productoExistente);
        });
    }
    
}
