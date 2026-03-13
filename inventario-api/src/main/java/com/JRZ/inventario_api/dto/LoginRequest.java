package com.JRZ.inventario_api.dto;


import jakarta.validation.constraints.*;

public record LoginRequest(
    @NotBlank(message = "El email no puede estar vacio")
    @Email(message =  "Debe tener un formato de correo valido")
    
    String email,

    @NotBlank(message =  "La contraseña es obligatoria")
    String password
    ) {


    

}