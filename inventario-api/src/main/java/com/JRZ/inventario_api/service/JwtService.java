package com.JRZ.inventario_api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Service
public class JwtService {
    
// Nota: En un proyecto real, esto se saca de aquí y se pone en el application.properties   
    private static final String SECRET_KEY = "Olasicomoestanclaroqueondaquebuenalololol";


    //creamos el token
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)//identificador de la persona que posee el token
                .setIssuedAt(new Date(System.currentTimeMillis()))// a que hora se genero
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *15))
                .signWith(getSignKey(), SignatureAlgorithm.HS256) //el sello criptografico
                .compact(); //covertir a string
    }


    private Key getSignKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

}
