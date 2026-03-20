package com.JRZ.inventario_api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


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

    // 2. Método genérico para extraer cualquier "etiqueta" (Claim) del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 5. El método que abre el sobre usando nuestra LLAVE SECRETA
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey()) // Usamos la misma llave con la que lo firmamos
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
