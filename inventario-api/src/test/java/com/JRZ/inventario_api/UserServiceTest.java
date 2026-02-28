package com.JRZ.inventario_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
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




}
