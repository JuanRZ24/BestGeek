package com.JRZ.inventario_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "Debe tener un formato de correo válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") // ¡Regla extra recomendada!
        String hashPassword,

        @NotBlank(message =  "El numero de telefono es obligatorio")
        @Size(min = 11, message = "el numero no es valido")
        String telefono
) {
}