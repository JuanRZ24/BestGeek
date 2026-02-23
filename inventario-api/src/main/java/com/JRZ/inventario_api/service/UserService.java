package com.JRZ.inventario_api.service;

import org.springframework.stereotype.Service;

import com.JRZ.inventario_api.entity.User;
import com.JRZ.inventario_api.repository.UserRepository;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    //metodos

    //post

    public User registrarUsuario(User user){
        System.out.println("Usuario a guardar: " + user.getEmail());
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
}
