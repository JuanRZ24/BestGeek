package com.JRZ.inventario_api.controller;

import com.JRZ.inventario_api.entity.VideoJuego;
import com.JRZ.inventario_api.service.VideojuegoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class VideoJuegoController {
    
    private final VideojuegoService videojuegoService;

    //inyeccion del servicio

    public VideoJuegoController(VideojuegoService videojuegoService){
        this.videojuegoService = videojuegoService;
    }

    // endpoints

    @PostMapping
    public ResponseEntity<VideoJuego> crear(@RequestBody VideoJuego videoJuego){

        //llamamos al service guardarvideojuego

        VideoJuego nuevoVideoJuego = videojuegoService.guardarVideoJuego(videoJuego);


        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVideoJuego);
    }

    @GetMapping
    public List<VideoJuego> listar() {
        // Retorna la lista directa (Spring lo convierte a JSON automáticamente)
        return videojuegoService.listarTodos();
    }

}
