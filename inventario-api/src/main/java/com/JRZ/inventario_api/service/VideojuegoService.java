package com.JRZ.inventario_api.service;

import com.JRZ.inventario_api.entity.VideoJuego;
import org.springframework.stereotype.Service;
import com.JRZ.inventario_api.repository.VideoJuegoRepository;

import java.util.List;

@Service
public class VideojuegoService {
    
    private final VideoJuegoRepository videojuegoRepository;


    //inyeccion por constructor
    public VideojuegoService(VideoJuegoRepository videoJuegoRepository){
        this.videojuegoRepository = videoJuegoRepository;
    }

    // metodo 1: guardar un producto
    public VideoJuego  guardarVideoJuego(VideoJuego videojuego){
        return videojuegoRepository.save(videojuego);
    }

    public List<VideoJuego> listarTodos(){
        return videojuegoRepository.findAll();
    }

}
