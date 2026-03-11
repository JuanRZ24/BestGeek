package com.JRZ.inventario_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.JRZ.inventario_api.repository.UserRepository;
import com.JRZ.inventario_api.service.UserService;

import com.JRZ.inventario_api.entity.User;
import com.JRZ.inventario_api.exception.InvalidCredentialsException;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void WhenRegisterUser_ThenHashPasswordAndSave(){
        User user = new User();
        user.setHashPassword("12345");

        // Cambia esto a minúsculas
        when(passwordEncoder.encode("12345")).thenReturn("hash_seguro");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User usuarioGuardado = userService.registrarUsuario(user);

        assertNotNull(usuarioGuardado);
        assertEquals("hash_seguro", usuarioGuardado.getHashPassword()); // Ahora sí coinciden
        verify(userRepository, times(1)).save(user);
        }

    @Test
    void WhenLoginUser_ThenGetUser(){
        // 1. Preparacion
        String email     = "123@mail.com";
        String passPlana = "12345";
        User usuarioSimulado = new User();
        usuarioSimulado.setEmail(email);
        usuarioSimulado.setHashPassword("hash_seguro_bd");

        //mocks:

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(usuarioSimulado));
        when(passwordEncoder.matches(passPlana,"hash_seguro_bd")).thenReturn(true);
        
        //act

        User resultado = userService.login(email, passPlana);

        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());

        //verificamos
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test 
    void WhenLoginUser_BadCredentials(){
        //preparar
        String email     = "123@gmail.com";
        String passplana = "12345";
        User usuarioSimulado = new User();
        usuarioSimulado.setEmail(email);
        usuarioSimulado.setHashPassword("hash_seguro_bd");


        //mocks
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(usuarioSimulado));
        when(passwordEncoder.matches(passplana,"hash_seguro_bd")).thenReturn(false);

        //act
        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, ()-> {
            userService.login(email,passplana);
        });

        assertEquals("Credenciales incorrectas", exception.getMessage());

        verify(userRepository, times(1)).findByEmail(email);

    }




}
