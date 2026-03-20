package com.JRZ.inventario_api.config;

import com.JRZ.inventario_api.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor // Esto nos ahorra escribir el constructor para inyectar servicios
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraemos el Token (le quitamos la palabra "Bearer " que son 7 caracteres)
        jwt = authHeader.substring(7);
        
        // 3. Le pedimos al JwtService que nos diga de quién es este token
        userEmail = jwtService.extractUsername(jwt); 

        // 4. Si tenemos un email y el usuario aún no está "oficialmente" logueado en Spring...
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Traemos al usuario de la base de datos (usando su email)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // 5. ¡La prueba de fuego! ¿El token es válido para este usuario?
            if (jwtService.isTokenValid(jwt, userDetails)) {
                
                // Creamos el "pase oficial" de Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 6. ¡VISTO BUENO! Metemos al usuario al "Contexto de Seguridad"
                // A partir de aquí, toda la API sabe quién es este usuario.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 7. Pase lo que pase, dejamos que la petición siga su camino
        filterChain.doFilter(request, response);
    }
}