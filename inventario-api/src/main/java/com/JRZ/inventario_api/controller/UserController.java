package com.JRZ.inventario_api.controller;


import com.JRZ.inventario_api.service.UserService;

import jakarta.validation.Valid;

import com.JRZ.inventario_api.dto.RegisterRequest;
import com.JRZ.inventario_api.entity.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/usuarios")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping
    public List<User> listar(){
        return userService.ObtenerUsuarios();
    }

    @PostMapping
    public ResponseEntity<User> crear(@RequestBody @Valid RegisterRequest request){
        
        User newUser = userService.registrarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }



    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == id")
    @DeleteMapping("/{id}")
    public ResponseEntity <?> eliminar(@PathVariable Long id){
        userService.EliminarUsuario(id);
        return ResponseEntity.ok(Map.of("mensaje", "Borrado exitosamente"));
    }


    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == id")
    @PatchMapping("{id}")
    public ResponseEntity <?> actualizarUser(@PathVariable Long id, @RequestBody User cambios){
       userService.actualizarUser(id, cambios);
        return ResponseEntity.ok(cambios);
    }


    



}
