package com.JRZ.inventario_api.repository;

import com.JRZ.inventario_api.entity.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoJuegoRepository extends JpaRepository<VideoJuego, Long> {
    
}
