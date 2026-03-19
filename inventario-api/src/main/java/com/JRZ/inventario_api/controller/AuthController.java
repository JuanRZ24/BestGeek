package com.JRZ.inventario_api.controller;

import com.JRZ.inventario_api.dto.LoginRequest;
import com.JRZ.inventario_api.entity.User;
import com.JRZ.inventario_api.exception.InvalidCredentialsException;
import com.JRZ.inventario_api.service.JwtService;
import com.JRZ.inventario_api.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Le dice a Spring que esta clase responderá con JSON
@RequestMapping("/api/auth") // La ruta base para todo este controlador
public class AuthController {

    private final UserService userService;
    private final JwtService  jwtService;

    // Inyectamos el servicio igual que lo hicimos antes
    public AuthController(UserService userService,JwtService  jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login") 
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            
            User usuarioLogueado = userService.login(request.email(), request.password());
            
            String tokenGenerado = jwtService.generateToken(usuarioLogueado.getEmail());
            
            return ResponseEntity.ok(tokenGenerado);
            
        } catch (InvalidCredentialsException e) {
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}