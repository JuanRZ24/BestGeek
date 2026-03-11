package com.JRZ.inventario_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.JRZ.inventario_api.entity.User;
import com.JRZ.inventario_api.repository.UserRepository;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }


    //metodos

    //post

    public User registrarUsuario(User user){
        System.out.println("Usuario a guardar: " + user.getEmail());
        String encodedpass = passwordEncoder.encode(user.getHashPassword());
        user.setHashPassword(encodedpass);
        return userRepository.save(user);
    }


    //get
    public List<User> ObtenerUsuarios(){
        return userRepository.findAll();
    }

    //delete
    public void EliminarUsuario(Long id){
        userRepository.deleteById(id);
    }

    //put
    public void actualizarUser(Long id, User cambios){
        userRepository.findById(id).ifPresent(userExistente -> {
            if (cambios.getNombre()!= null){
                userExistente.setNombre(cambios.getNombre());
            }

            userRepository.save(userExistente);
        });
    }


    public User login(String email, String passwordPlana) {
        User usuarioEncontrado = userRepository.findByEmail(email)
            .orElseThrow(()-> new RuntimeException("Credenciales incorrectas"));

        boolean esValida = passwordEncoder.matches(passwordPlana, usuarioEncontrado.getHashPassword());

        if (!esValida) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return usuarioEncontrado;
    }
}
