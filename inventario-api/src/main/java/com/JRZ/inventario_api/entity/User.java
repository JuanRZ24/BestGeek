package com.JRZ.inventario_api.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashPassword;

    @Column(name = "phone_number")
    private String phoneNumber;

    // En lugar de Long idRole, usamos la relación real
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    private Role role; 


    // --- MÉTODOS OBLIGATORIOS DE USERDETAILS ---

    // 1. ¿Qué roles tiene este usuario? (Por ahora le damos el rol de usuario normal)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.getName()));
    }

    // 2. ¿Cuál es la contraseña encriptada? (Asegúrate de devolver tu variable real de password)
    @Override
    public String getPassword() {
        return this.hashPassword; // O this.password, dependiendo de cómo llamaste a tu variable
    }

    // 3. ¿Cuál es el "nombre de usuario" para loguearse? (En nuestro caso es el correo)
    @Override
    public String getUsername() {
        return this.email;
    }

    // 4. Los siguientes 4 métodos le dicen a Spring que la cuenta está activa y sin bloqueos
    @Override
    public boolean isAccountNonExpired() {
        return true; // La cuenta no expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // La cuenta no está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Las credenciales no expiran
    }

    @Override
    public boolean isEnabled() {
        return true; // El usuario está habilitado
}
}