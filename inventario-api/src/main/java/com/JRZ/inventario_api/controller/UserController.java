package com.JRZ.inventario_api.controller;


import com.JRZ.inventario_api.service.UserService;

import jakarta.validation.Valid;

import com.JRZ.inventario_api.entity.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<User> crear(@RequestBody @Valid User user){
        System.out.println("DEBUG - Nombre: " + user.getNombre());
        System.out.println("DEBUG - Email: " + user.getEmail());
        User newUser = userService.registrarUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        userService.EliminarUsuario(id);
    }

    @PatchMapping("{id}")
    public void actualizarUser(@PathVariable Long id, @RequestBody User user){
        userService.actualizarUser(id, user);
    }


    



}
